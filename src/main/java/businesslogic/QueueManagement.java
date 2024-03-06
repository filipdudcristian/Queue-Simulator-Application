package businesslogic;

import model.Client;
import model.Queue;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QueueManagement implements Runnable{
    private int nrClients, nrQueues, minArrivalTime, maxArrivalTime;
    private int  minServiceTime, maxServiceTime, totalServiceTime;
    private int timeLimit;
    private List<Client> clientList;
    private Scheduler scheduler;
    private JTextArea textArea;
    private JTextArea textAreaClients;
    private JButton btnStart;
    private JLabel currentTimeLabel;
    public QueueManagement(int timeLimit, int nrClients, int nrQueues, int minArrivalTime, int maxArrivalTime, int minServiceTime, int maxServiceTime, JTextArea textArea, JTextArea textAreaClients ,JLabel currentTimeLabel, JButton btnStart) {
        this.timeLimit = timeLimit;
        this.nrClients = nrClients;
        this.nrQueues = nrQueues;
        this.minArrivalTime = minArrivalTime;
        this.maxArrivalTime = maxArrivalTime;
        this.minServiceTime = minServiceTime;
        this.maxServiceTime = maxServiceTime;
        this.textArea = textArea;
        this.textAreaClients = textAreaClients;
        this.currentTimeLabel = currentTimeLabel;
        this.btnStart = btnStart;
        this.scheduler = new Scheduler(nrQueues);
    }

    private void generateClients()
    {
        ArrayList<Client> clientArrayList = new ArrayList<Client>();
        for(int i = 1; i <= nrClients; i++)
        {
            int randomArrivalTime, randomServiceTime;
            randomArrivalTime = (int)Math.floor(Math.random() *(maxArrivalTime - minArrivalTime + 1) + minArrivalTime);
            randomServiceTime = (int)Math.floor(Math.random() *(maxServiceTime - minServiceTime + 1) + minServiceTime);
            Client tempClient = new Client(i, randomArrivalTime, randomServiceTime);
            clientArrayList.add(tempClient);
            totalServiceTime = totalServiceTime + randomServiceTime;
        }
        Collections.sort(clientArrayList);
        clientList = new ArrayList<>();
        System.out.println("LISTA CLIENTILOR:\n");
        for (Client c: clientArrayList)
            System.out.println(c + "\n");
        System.out.println("\n");
        clientList.addAll(clientArrayList);
    }


    public void run() {

        int realTime = 0;
        int queueId;
        int peakTimeClients = 0, peakTime = 0;
        float avgWaitingTime = 0, avgProcessingTime = 0;

        try {
            generateClients();
            FileWriter myWriter = new FileWriter("log.txt");

            while(realTime <= timeLimit) {

                ArrayList<Client> removeClients = new ArrayList<>();

                for (Client c : clientList)
                {
                    if (c.getArrivalTime() == realTime)
                    {
                        queueId = scheduler.dispatchClient(c);
                        removeClients.add(c);
                        avgWaitingTime = avgWaitingTime + (scheduler.getQueues().get(queueId).getWaitingPeriod().get() - c.getServiceTime());
                        avgProcessingTime = avgProcessingTime + c.getServiceTime();
                    }
                }
                for (Client c: removeClients)
                {
                    clientList.remove(c);
                }

                System.out.println("TIMPUL CURENT: " + realTime + "\n");
                myWriter.write("TIMPUL CURENT: " + realTime + "\n");

                myWriter.write("Clienti: " + clientList.toString()+"\n");
                textAreaClients.setText(clientList.toString());
                System.out.println(scheduler.printQueues());
                textArea.setText(scheduler.printQueues());
                myWriter.write(scheduler.printQueues());

                currentTimeLabel.setText("Current time: " + realTime);


                int maxClients = 0;
                for (Queue q : scheduler.getQueues()) {
                    maxClients = maxClients + q.getClients().size();
                }
                if (maxClients > peakTime) {
                    peakTimeClients = maxClients;
                    peakTime = realTime;
                }


                for (Queue q : scheduler.getQueues()) {
                    if (q.getWaitingPeriod().get() > 0)
                        q.setWaitingPeriod(q.getWaitingPeriod().get() - 1);
                }

                realTime++;

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

            avgWaitingTime = avgWaitingTime/nrClients;
            avgProcessingTime = avgProcessingTime/nrClients;

            myWriter.write("Timpul mediu de asteptare a fost: " + avgWaitingTime + "\n" +
                               "Timpul mediu de procesare a fost: " + avgProcessingTime + "\n" +
                               "Ora de varf a fost: " + peakTime + "cu un numar de " + peakTimeClients + " clienti" + "\n");

            textArea.setText("Timpul mediu de asteptare a fost: " + avgWaitingTime + "\n" +
                             "Timpul mediu de procesare a fost: " + avgProcessingTime + "\n" +
                             "Ora de varf a fost: " + peakTime + " cu un numar de " + peakTimeClients + " clienti" + "\n");

            myWriter.close();
            for(Queue sv: scheduler.getQueues()) {
                sv.getThread().interrupt();
            }
            btnStart.setEnabled(true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}


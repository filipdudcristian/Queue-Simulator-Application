package model;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Queue implements Runnable {

    private BlockingQueue<Client> clientQueue;
    private AtomicInteger waitingPeriod;
    private Thread thread;


    public Queue() {
        this.clientQueue = new ArrayBlockingQueue<Client>(1000);
        this.waitingPeriod = new AtomicInteger(0);
        this.thread = new Thread(this);
        thread.start();
    }
    public void addClient(Client newClient)
    {
        clientQueue.add(newClient);
        waitingPeriod.addAndGet(newClient.getServiceTime());
    }

    public void run() {
        while(true)
        {
            if (clientQueue.size()>0)
            {
                Client x = this.clientQueue.peek();
                try {
                    thread.sleep(x.getServiceTime()*1000);
                } catch (InterruptedException e) {

                }
                clientQueue.remove();
            }
        }
    }

    public ArrayList<Client> getClients()
    {
        ArrayList<Client> clients = new ArrayList<Client>();
        for (Client c: clientQueue)
        {
            clients.add(c);
        }
        return clients;
    }

    public AtomicInteger getWaitingPeriod() {
        return waitingPeriod;
    }

    public void setWaitingPeriod(int waitingPeriod) {
        this.waitingPeriod.set(waitingPeriod);
    }

    public Thread getThread() {
        return thread;
    }


    @Override
    public String toString() {
        String clientsString = new String();
        for (Client c: clientQueue)
        {
            clientsString = clientsString + c.getID();
        }

        return clientsString;
    }
}

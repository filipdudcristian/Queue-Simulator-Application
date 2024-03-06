package businesslogic;

import model.Client;
import model.Queue;

import java.util.ArrayList;
import java.util.List;

public class Scheduler {
    private List<Queue> queues;
    private int maxNoQueues;

    TimeStrategy strategy;

    public Scheduler(int maxNoQueues) {
        this.maxNoQueues = maxNoQueues;
        this.queues = new ArrayList<Queue>();
        this.strategy = new TimeStrategy();

        int k=0;
        while(k < maxNoQueues){
            queues.add(new Queue());
            k++;
        }
    }

    public int dispatchClient(Client c)
    {
        return strategy.addTask(queues, c);
    }
    public List<Queue> getQueues() {
        return queues;
    }

    public String printQueues()
    {
        String queuesString = new String();
        for (Queue q: queues)
        {
            queuesString = queuesString + "Queue " + (queues.indexOf(q) + 1) + ", ";
            queuesString = queuesString + "waiting period: " + q.getWaitingPeriod() + ": ";
            for(Client c : q.getClients())
            {
                queuesString = queuesString + " " + c + " " ;
            }
            queuesString = queuesString + "\n";
        }
        queuesString = queuesString + "\n";
        return  queuesString;
    }
}

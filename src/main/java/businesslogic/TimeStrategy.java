package businesslogic;

import model.Client;
import model.Queue;

import java.util.List;

public class TimeStrategy {
    public int addTask(List<Queue> queues, Client c)
    {
        int minWaitingPeriod = queues.get(0).getWaitingPeriod().get();
        Queue minQueue = queues.get(0);
        for (Queue q: queues)
        {
            if(q.getWaitingPeriod().get() < minWaitingPeriod)
            {
                minWaitingPeriod = q.getWaitingPeriod().get();
                minQueue = q;
            }
        }
        minQueue.addClient(c);
        return queues.indexOf(minQueue);
    }
}

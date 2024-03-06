package model;

public class Client implements Comparable{
    private int ID, arrivalTime, serviceTime;

    public Client(int ID, int arrivalTime, int processingTime) {
        this.ID = ID;
        this.arrivalTime = arrivalTime;
        this.serviceTime = processingTime;
    }

    public int compareTo(Object o) {

        if( ((Client)o).getArrivalTime() > this.arrivalTime)
            return -1;
        if( ((Client)o).getArrivalTime() < this.arrivalTime)
            return 1;
        return 0;
    }
    public int getID() {
        return ID;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getServiceTime() {
        return serviceTime;
    }

    @Override
    public String toString() {
        return "(" + ID + ", " + arrivalTime + ", " + serviceTime + ")";
    }
}

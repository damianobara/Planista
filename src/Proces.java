public class Proces {
    public int id;
    public int arrivalTime;
    public double remainingTime;

    Proces(int id, int arrivalTime, double remainingTime){
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.remainingTime = remainingTime;
    }

    Proces(Proces proces){
        this.id = proces.id;
        this.arrivalTime = proces.arrivalTime;
        this.remainingTime = proces.remainingTime;
    }



}

public class ProcesOutput {
    public int id;
    public int arrivalTime;
    public double finishTime;

    ProcesOutput(int id, int arrivalTime, double finishTime){
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.finishTime = finishTime;
    }

    ProcesOutput(ProcesOutput proces){
        this.id = proces.id;
        this.arrivalTime = proces.arrivalTime;
        this.finishTime = proces.finishTime;
    }
}

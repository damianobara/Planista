import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

//Round Robin
public class RR extends Strategia{
    int q;

    RR(int q){
        this.q = q;
    }


    public PriorityQueue<Proces> CreateQueues(LinkedList<Proces> ll){
        PriorityQueue<Proces> future = new PriorityQueue<Proces>(new arrivalTimeThenId());
        int i = 0;
        Proces p;
        while (i < ll.size()){
            p = new Proces(ll.get(i));
            future.add(p);
            i++;
        }
        return future;
    }

    public DaneStatystyczne SymulujStrategię(LinkedList<Proces> ll){

        //System.out.print("Symuluję strategię\n");

        LinkedList<Proces> present = new LinkedList<Proces>();
        PriorityQueue<Proces> future = CreateQueues(ll);
        LinkedList<ProcesOutput> wynik = new LinkedList<>();

        Proces presentPeek;
        Proces futurePeek;
        ProcesOutput out;

        double sumaOczekiwana = 0;

        int j;
        int dif;
        int oldTime;
        //główna pętla
        while (!future.isEmpty() || !present.isEmpty()){
            presentPeek = present.peek();
            futurePeek = future.peek();

            if (presentPeek == null) {
                time = futurePeek.arrivalTime;
                present.add(future.remove());
                while (future.peek() != null && future.peek().arrivalTime  == present.peek().arrivalTime) {
                    present.add(future.remove());
                }
            }
            else if(futurePeek == null){
                if (presentPeek.remainingTime > q){
                    time += q;
                    sumaOczekiwana += q * (present.size() - 1);
                    presentPeek.remainingTime -= q;
                    present.add(present.remove());
                }
                else if (presentPeek.remainingTime <= q){
                    time += presentPeek.remainingTime;
                    sumaOczekiwana += presentPeek.remainingTime* (present.size() - 1);
                    out = new ProcesOutput(presentPeek.id, presentPeek.arrivalTime, time);
                    wynik.add(out);
                    present.remove();
                }
            }

            else if (q <= presentPeek.remainingTime && q <= futurePeek.arrivalTime - time){
                time += q;
                sumaOczekiwana += q * (present.size() - 1);
                presentPeek.remainingTime -= q;
                if (q < presentPeek.remainingTime) present.add(present.remove());
                else {
                    out = new ProcesOutput(presentPeek.id, presentPeek.arrivalTime, time);
                    wynik.add(out);
                    present.remove();
                }
                while (future.peek() != null && future.peek().arrivalTime == time) {
                    present.add(future.remove());
                }
            }

            else if (presentPeek.remainingTime <= q  && q <= futurePeek.arrivalTime - time){
                time += presentPeek.remainingTime;
                sumaOczekiwana += presentPeek.remainingTime * (present.size() - 1);
                out = new ProcesOutput(presentPeek.id, presentPeek.arrivalTime, time);
                wynik.add(out);
                present.remove();
                while (future.peek() != null && future.peek().arrivalTime == time) {
                    present.add(future.remove());
                }
            }
            else if (futurePeek.arrivalTime - time <= q && q <= presentPeek.remainingTime) {
                // = futurePeek.arrivalTime;
                time += q;
                sumaOczekiwana += q * (present.size() - 1);
                while (future.peek() != null && future.peek().arrivalTime < time) {
                    sumaOczekiwana += time - future.peek().arrivalTime;
                    present.add(future.remove());
                }
                if (q == presentPeek.remainingTime) {
                    out = new ProcesOutput(presentPeek.id, presentPeek.arrivalTime, time);
                    wynik.add(out);
                    present.remove();
                }
                else{
                    presentPeek.remainingTime -= q;
                    present.add(present.remove());
                }

                while (future.peek() != null && future.peek().arrivalTime == time) {
                    present.add(future.remove());
                }
            }
            else if (futurePeek.arrivalTime - time <= presentPeek.remainingTime && presentPeek.remainingTime <= q) {
                time += presentPeek.remainingTime;
                sumaOczekiwana += presentPeek.remainingTime * (present.size() - 1);

                while (future.peek() != null && future.peek().arrivalTime <= time) {
                    sumaOczekiwana += time - future.peek().arrivalTime;
                    present.add(future.remove());
                }
                out = new ProcesOutput(presentPeek.id, presentPeek.arrivalTime, time);
                wynik.add(out);
                present.remove();
            }
            else if (presentPeek.remainingTime <= futurePeek.arrivalTime - time && futurePeek.arrivalTime - time <= q) {
                time += presentPeek.remainingTime;
                sumaOczekiwana += presentPeek.remainingTime * (present.size() - 1);
                out = new ProcesOutput(presentPeek.id, presentPeek.arrivalTime, time);
                wynik.add(out);
                present.remove();

                while (future.peek() != null && future.peek().arrivalTime == time) {
                    present.add(future.remove());
                }
            }
        }
        DaneStatystyczne dane = new DaneStatystyczne(wynik, sumaOczekiwana);
        return dane;
    }

}

import java.util.LinkedList;
import java.util.PriorityQueue;

//Shortest Job First
public class SJF extends Strategia{
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

        PriorityQueue<Proces> present = new PriorityQueue<Proces>(new RemainingTimeThenId());
        PriorityQueue<Proces> future = CreateQueues(ll);
        LinkedList<ProcesOutput> wynik = new LinkedList<>();

        Proces presentPeek;
        Proces futurePeek;
        ProcesOutput out;
        int j;
        double sumaOczekiwana = 0;
        //główna pętla
        while (!future.isEmpty() || !present.isEmpty()) {
            presentPeek = present.peek();
            futurePeek = future.peek();

            if (presentPeek == null) {
                time = futurePeek.arrivalTime;
                present.add(future.remove());
                while (future.peek() != null && future.peek().arrivalTime == present.peek().arrivalTime) {
                    present.add(future.remove());
                }

            } else if (futurePeek == null) {
                time += presentPeek.remainingTime;
                sumaOczekiwana += presentPeek.remainingTime * (present.size() - 1);
                out = new ProcesOutput(presentPeek.id, presentPeek.arrivalTime, time);
                wynik.add(out);
                present.remove();
            } else {
                time += presentPeek.remainingTime;
                sumaOczekiwana += presentPeek.remainingTime * (present.size() - 1);
                out = new ProcesOutput(presentPeek.id, presentPeek.arrivalTime, time);
                wynik.add(out);
                present.remove();
                while (future.peek() != null && future.peek().arrivalTime <= time) {
                    sumaOczekiwana += time - future.peek().arrivalTime;
                    present.add(future.remove());
                }
            }
        }
        DaneStatystyczne dane = new DaneStatystyczne(wynik, sumaOczekiwana);
        return dane;
    }
}

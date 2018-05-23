import java.util.LinkedList;
import java.util.PriorityQueue;

//Shortest Remaining Time
public class SRT extends Strategia{
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

        double sumaOczekiwana = 0;
        int j;
        double oldTime;
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

            } else if (futurePeek.arrivalTime - time < presentPeek.remainingTime) {
                presentPeek.remainingTime = presentPeek.remainingTime + time - futurePeek.arrivalTime;
                sumaOczekiwana += (futurePeek.arrivalTime - time) * (present.size() - 1);
                time = futurePeek.arrivalTime;

                present.add(future.remove());
                while (future.peek() != null && future.peek().arrivalTime == time) {
                    present.add(future.remove());
                }
            } else if (futurePeek.arrivalTime - time > presentPeek.remainingTime) {
                time += presentPeek.remainingTime;
                sumaOczekiwana += presentPeek.remainingTime * (present.size() - 1);
                out = new ProcesOutput(presentPeek.id, presentPeek.arrivalTime, time);
                wynik.add(out);
                present.remove();
            }
            else{
                sumaOczekiwana += presentPeek.remainingTime * (present.size() - 1);
                presentPeek.remainingTime = presentPeek.remainingTime + time - futurePeek.arrivalTime;
                time = futurePeek.arrivalTime;

                out = new ProcesOutput(presentPeek.id, presentPeek.arrivalTime, time);
                wynik.add(out);
                present.remove();

                present.add(future.remove());
                while (future.peek() != null && future.peek().arrivalTime == time) {
                    present.add(future.remove());
                }
            }
        }
        DaneStatystyczne dane = new DaneStatystyczne(wynik, sumaOczekiwana);
        return dane;
    }
}

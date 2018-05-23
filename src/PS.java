import java.util.LinkedList;
import java.util.PriorityQueue;

//Procesor Sharing
public class PS extends Strategia {
    public double epsilon;

    public PS(double epsilon) {
        this.epsilon = epsilon;
    }

    public PriorityQueue<Proces> CreateQueues(LinkedList<Proces> ll) {
        PriorityQueue<Proces> future = new PriorityQueue<Proces>(new arrivalTimeThenId());
        int i = 0;
        Proces p;
        while (i < ll.size()) {
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
        double remain;
        double sumaOczekiwana = 0;
        //główna pętla
        while (!future.isEmpty() || !present.isEmpty()) {
            presentPeek = present.peek();
            futurePeek = future.peek();

            if (presentPeek == null) {
                time = futurePeek.arrivalTime;
                present.add(future.remove());
                while (future.peek() != null && future.peek().arrivalTime == time) {
                    present.add(future.remove());
                }
            }

            else if (futurePeek == null) {
                time += (presentPeek.remainingTime) * present.size();

                //wszędzie tak trzeba
                remain = presentPeek.remainingTime;
                for (Proces p : present) {
                    p.remainingTime -= remain;
                }

                //zmienione z == 0 na < epsilon
                while (present.peek() != null && present.peek().remainingTime < epsilon) {
                    out = new ProcesOutput(present.peek().id, present.peek().arrivalTime, time);
                    wynik.add(out);
                    present.remove();
                }
            }


            else if (futurePeek.arrivalTime - time < presentPeek.remainingTime * present.size()) {
                for (Proces p : present) {
                    p.remainingTime += (time - futurePeek.arrivalTime) / present.size();
                }
                time = futurePeek.arrivalTime;

                present.add(future.remove());
                while (future.peek() != null && future.peek().arrivalTime <= epsilon) {
                    present.add(future.remove());
                }
            }


            else if (futurePeek.arrivalTime - time > presentPeek.remainingTime * present.size()) {
                time += presentPeek.remainingTime * present.size();
                remain = presentPeek.remainingTime;
                for (Proces p : present) {
                    p.remainingTime -= remain;
                }
                out = new ProcesOutput(presentPeek.id, presentPeek.arrivalTime, time);
                wynik.add(out);
                present.remove();
                while (present.peek() != null && present.peek().remainingTime <= epsilon) {
                    out = new ProcesOutput(presentPeek.id, presentPeek.arrivalTime, time);
                    wynik.add(out);
                    present.remove();
                }
            }
            else {
                time = futurePeek.arrivalTime;
                remain = presentPeek.remainingTime;
                for (Proces p : present) {
                    p.remainingTime -= remain;
                }

                while (present.peek() != null && present.peek().remainingTime <= epsilon) {
                    out = new ProcesOutput(presentPeek.id, presentPeek.arrivalTime, time);
                    wynik.add(out);
                    present.remove();
                }
                while (future.peek() != null && future.peek().arrivalTime == time) {
                    present.add(future.remove());
                }
            }
        }
        DaneStatystyczne dane = new DaneStatystyczne(wynik, sumaOczekiwana);
        return dane;    }
}

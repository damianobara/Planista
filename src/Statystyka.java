import java.util.LinkedList;
import java.util.PriorityQueue;

public abstract class Statystyka {


    Statystyka (){
    }

    /*public PriorityQueue<ProcesOutput> CreateQueues(LinkedList<ProcesOutput> ll){
        PriorityQueue<Proces> future = new PriorityQueue<Proces>(new arrivalTimeThenId());
        int i = 0;
        ProcesOutput p;
        while (i < ll.size()){
            p = new ProcesOutput(ll.get(i));
            future.add(p);
            i++;
        }
        return future;
    }*/

    public abstract DaneStatystyczne Przelicz(DaneStatystyczne dane);
}

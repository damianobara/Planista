import java.util.LinkedList;
import java.util.PriorityQueue;

public class Output {

    public PriorityQueue<ProcesOutput> sortOutput(LinkedList<ProcesOutput> ll){
        PriorityQueue<ProcesOutput> result = new PriorityQueue<ProcesOutput>(new EndTimeThenId());
        int i = 0;
        ProcesOutput p;
        while (i < ll.size()){
            p = new ProcesOutput(ll.get(i));
            result.add(p);
            i++;
        }
        return result;
    }

    public void Out(LinkedList<ProcesOutput> ll, double oczekiwania, double wykonania){
        PriorityQueue<ProcesOutput> sorted = sortOutput(ll);
        ProcesOutput p = sorted.poll();
        while(p != null){
            System.out.print("[" + p.id + " " + p.arrivalTime + " " +
                    String.format(java.util.Locale.ROOT, "%.2f", p.finishTime) + "]");
            p = sorted.poll();
        }
        System.out.print("\nŚredni czas obrotu: " + String.format(java.util.Locale.ROOT, "%.2f", wykonania) + "]\n");
        System.out.print("Średni czas oczekiwania: " + String.format(java.util.Locale.ROOT, "%.2f", oczekiwania) + "]\n\n");
    }
}

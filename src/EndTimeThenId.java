import java.util.Comparator;


public class EndTimeThenId implements Comparator<ProcesOutput> {

    public EndTimeThenId() {
    }

    @Override
    public int compare(ProcesOutput p1, ProcesOutput p2) {
        if (p1.finishTime > p2.finishTime) return 1;
        if (p1.finishTime < p2.finishTime) return -1;
        if (p1.finishTime == p2.finishTime) {
            if (p1.id > p2.id) return 1;
            if (p2.id < p2.id) return -1;
            return 0;
        }
        return 0;
    }
}


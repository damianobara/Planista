import java.util.Comparator;

public class arrivalTimeThenId implements Comparator<Proces>{

    public arrivalTimeThenId() {
    }

    @Override
    public int compare(Proces p1, Proces p2) {
        if (p1.arrivalTime > p2.arrivalTime) return 1;
        if (p1.arrivalTime < p2.arrivalTime) return -1;
        if (p1.arrivalTime == p2.arrivalTime) {
            if (p1.id > p2.id) return 1;
            if (p2.id < p2.id) return -1;
            return 0;
        }
        return 0;
    }
}


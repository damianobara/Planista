import java.util.Comparator;

public class RemainingTimeThenId implements Comparator<Proces>{

    public RemainingTimeThenId() {
    }

    @Override
    public int compare(Proces p1, Proces p2) {
        if (p1.remainingTime > p2.remainingTime) return 1;
        if (p1.remainingTime < p2.remainingTime) return -1;
        if (p1.remainingTime == p2.remainingTime) {
            if (p1.id > p2.id) return 1;
            if (p2.id < p2.id) return -1;
            return 0;
        }
        return 0;
    }
}
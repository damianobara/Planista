import java.util.LinkedList;

public abstract class Strategia {

    public double time;

    public Strategia(){
        this.time = 0;
    }

    public abstract DaneStatystyczne SymulujStrategię(LinkedList<Proces> ll);
}

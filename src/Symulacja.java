import java.util.LinkedList;

public class Symulacja {
    public Strategia strategia;

    DaneStatystyczne dane;
    LinkedList<Proces> input;


    public Symulacja(Strategia strategia, LinkedList<Proces> input){
        this.input = input;
        this.strategia = strategia;
        dane = null;
    }

    public DaneStatystyczne symuluj() {
        return strategia.SymulujStrategię(input);
    }



}

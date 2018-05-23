import java.util.LinkedList;

public class DaneStatystyczne {
    public LinkedList<ProcesOutput> wyniki;
    double suma;

    public DaneStatystyczne(LinkedList<ProcesOutput> wyniki, double suma) {
        this.wyniki = wyniki;
        this.suma = suma;
    }
}

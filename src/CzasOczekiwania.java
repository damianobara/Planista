import java.util.LinkedList;

public class CzasOczekiwania extends Statystyka{

    CzasOczekiwania(){
    }

    @Override
    public DaneStatystyczne Przelicz(DaneStatystyczne dane){
        double czasOczekiwania = dane.suma/dane.wyniki.size();
        DaneStatystyczne stats = new DaneStatystyczne(dane.wyniki, czasOczekiwania);

        return stats;
    }

}

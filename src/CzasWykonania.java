public class CzasWykonania extends Statystyka{

    public DaneStatystyczne Przelicz(DaneStatystyczne dane){

        double CzasWykonywania = 0;
        for (ProcesOutput p : dane.wyniki) {
            CzasWykonywania += (p.finishTime - p.arrivalTime);
        }
        CzasWykonywania = CzasWykonywania/dane.wyniki.size();
        DaneStatystyczne stats = new DaneStatystyczne(dane.wyniki, CzasWykonywania);
        return stats;
    }
}

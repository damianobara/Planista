import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.LinkedList;


public class Main {

    public static void main(String[] args) {
        Input input = new Input();
        InputList inputlist = new InputList(null, null);
        if (args.length == 0) {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                inputlist = input.read(reader);
            } catch (Exception e) {
            }
        }
        else if (args.length == 1) {
            try {
                File file = new File(args[0]);
                if (!file.exists()){
                    System.out.println("Brak pliku o podanej nazwie");
                    System.exit(1);
                }
                BufferedReader reader = null;
                reader = new BufferedReader(new FileReader(file));
                inputlist = input.read(reader);
            } catch (Exception e) {
            }
        }
        else {
            System.out.println("Za dużo argumentów");
            System.exit(1);
        }
        LinkedList<Proces> procesList = inputlist.lp;
        LinkedList<Integer> qList = inputlist.li;

        FCFS fcfs = new FCFS();
        SJF sfj = new SJF();
        SRT srt = new SRT();

        double e = 0.01;
        PS ps = new PS(e);

        /*int ileQ = inputlist.li.size();

        Integer[] liArr = (Integer) inputlist.li.toArray();

        RR[] rr = new RR[ileQ];
        Symulacja[] symulacjaRR = new Symulacja[ileQ];
        for (int k = 0; k < rr.length; k++) {
            rr[k] = new RR(liArr[k]);
            symulacjaRR[k] = new Symulacja(rr[k], procesList);
        }

        DaneStatystyczne dRR;
        for (int k = 0; k < symulacjaRR.length; k++) {
            dRR = symulacjaRR[k].symuluj();
        }*/

        DaneStatystyczne oczek;
        DaneStatystyczne wykon;

        Output output = new Output();

        CzasOczekiwania oczekiwania = new CzasOczekiwania();
        CzasWykonania wykonywania = new CzasWykonania();

        DaneStatystyczne d;

        Symulacja symulacjaFCFS = new Symulacja(fcfs, procesList);
        d = symulacjaFCFS.symuluj();
        oczek = oczekiwania.Przelicz(d);
        wykon = wykonywania.Przelicz(d);
        System.out.print("Strategia: FCFS\n");
        output.Out(oczek.wyniki, oczek.suma, wykon.suma);



        Symulacja symulacjaSJF = new Symulacja(sfj, procesList);
         d = symulacjaSJF.symuluj();
        oczek = oczekiwania.Przelicz(d);
        wykon = wykonywania.Przelicz(d);
        System.out.print("Strategia: SJF\n");
        output.Out(oczek.wyniki, oczek.suma, wykon.suma);

        Symulacja symulacjaSRT = new Symulacja(srt, procesList);
         d = symulacjaSRT.symuluj();
        oczek = oczekiwania.Przelicz(d);
        wykon = wykonywania.Przelicz(d);
        System.out.print("Strategia: SRT\n");
        output.Out(oczek.wyniki, oczek.suma, wykon.suma);

        Symulacja symulacjaPS = new Symulacja(ps, procesList);
         d = symulacjaPS.symuluj();
        oczek = oczekiwania.Przelicz(d);
        wykon = wykonywania.Przelicz(d);
        System.out.print("Strategia: PS\n");
        output.Out(oczek.wyniki, oczek.suma, wykon.suma);


        RR rr;
        Symulacja symulacjaRR;

        for (Integer i : inputlist.li) {
            rr = new RR(i);
            symulacjaRR = new Symulacja(rr, procesList);
            d = symulacjaRR.symuluj();
            oczek = oczekiwania.Przelicz(d);
            wykon = wykonywania.Przelicz(d);
            System.out.print("Strategia: RR-" + i + "\n");
            output.Out(oczek.wyniki, oczek.suma, wykon.suma);
        }

    }

    public static void testmain(String[] args){

    }
}
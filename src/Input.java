import java.io.*;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Input {

    public Input() {
    }


    public InputList read(BufferedReader reader) throws Exception {

        LinkedList procesList = new LinkedList<Proces>();
        LinkedList<Integer> qList = new LinkedList<Integer>();
        Proces proces;
        int i = 0;
        String firstString = null;
        String secondString = null;
        int arriveTime;
        int procesTime;
        int id;
        int lines = Integer.MAX_VALUE - 6;
        int q = 0;
        int j;
        String text = null;
        Pattern pattern;

        try {
            text = reader.readLine();
            while (text != null && i < lines + 4) {
                if (i == 0) {
                    pattern = Pattern.compile("^\\d+$");
                    if (!pattern.matcher(text).matches()) {
                        System.out.println("Błąd w wierszu " + i + " :");
                        System.exit(1);
                    } else {
                        lines = Integer.parseInt(text);
                    }
                } else if (i < lines + 1) {
                    pattern = Pattern.compile("^\\d+ \\d+$");
                    if (!pattern.matcher(text).matches()) {
                        System.out.println("Błąd w wierszu " + i + " :");
                        System.exit(1);
                    } else {
                        String[] parts = text.split(" ");
                        firstString = parts[0];
                        secondString = parts[1];


                        arriveTime = Integer.parseInt(firstString);
                        procesTime = Integer.parseInt(secondString);
                        id = i;
                        proces = new Proces(id, arriveTime, procesTime);
                        procesList.add(proces);
                        //dodajemy te dwie rzeczy do listy
                    }
                } else if (i == lines + 1) {
                    pattern = Pattern.compile("^\\d+$");
                    if (!pattern.matcher(text).matches()) {
                        System.out.println("Błąd w wierszu " + i + " :");
                        System.exit(1);
                    } else {
                        q = Integer.parseInt(text);
                    }
                }
                //jakieś trudniejsze
                else if (i == lines + 2) {
                    pattern = Pattern.compile("^\\d+$");
                    String[] parts = text.split(" ");
                    j = 0;
                    while (j < q) {
                        if (parts[j] == null) {
                            System.out.println("Błąd w wierszu " + i + " :");
                            System.exit(1);
                        }
                        if (!pattern.matcher(parts[j]).matches()) {
                            System.out.println("Błąd w wierszu " + i + " :");
                            System.exit(1);
                        }
                        qList.add(Integer.parseInt(parts[j]));
                        j++;
                    }
                    if (j < q) {
                        System.out.println("Błąd w wierszu " + i + " : Niewłaściwa ilość wystąpień q");
                        System.exit(1);
                    }
                } else if (i == lines + 3) {
                    System.out.println("Błąd w wierszu " + i + " : Za mała liczba linii.");
                    System.exit(1);
                }
                text = reader.readLine();
                i++;
            }
            if (i != lines + 3) {
                System.out.println("Błąd w wierszu " + i + " : Za duża liczba linii.");
                System.exit(1);
            }
        }
        catch(FileNotFoundException e1){
            System.out.println("Plik z danymi nie jest dostępny.");
            System.exit(1);
        } catch(IOException e){
            System.out.println("Plik z danymi nie jest dostępny.");
            System.exit(1);
        }
        InputList input2 = new InputList(procesList, qList);
        return input2;
    }

}



package Model;

import java.util.Comparator;

public class SJF implements Comparator<Processo> {



    @Override
    public int compare(Processo o1, Processo o2) {
        return o1.getTempoRestante() - o2.getTempoRestante();
    }
}

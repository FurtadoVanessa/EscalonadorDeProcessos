package Model;

import java.util.Comparator;

public class Processo {


    private int ID;
    private int tempoExecucao;
    private int tempoRestante;
    private int horaDeCriacao;
    private int horaDeFim;

    public Processo(int ID, int tempo, int clock){
        this.ID = ID;
        this.tempoExecucao = 0;
        this.tempoRestante = tempo;
        this.horaDeCriacao = clock;
    }

    public void executaProcesso(){
        this.tempoExecucao++;
        this.tempoRestante--;
    }

    public boolean disponivel(){
        if(tempoRestante==0){
            return false;
        }
        return true;
    }

    public int getTempoRestante(){
        return this.tempoRestante;
    }
    public int getID(){
        return this.ID;
    }

    public void setHoraDeFim(int hora){
        this.horaDeFim = hora;
    }

    public int getTempo(){
        return this.horaDeFim - this.horaDeCriacao;
    }

    public String toString(){
        return "Processo: "+this.getID()+" - "+this.getTempoRestante();
    }

}

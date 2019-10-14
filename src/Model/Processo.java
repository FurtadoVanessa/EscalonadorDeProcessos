package Model;

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

    public int getID(){
        return this.ID;
    }
}

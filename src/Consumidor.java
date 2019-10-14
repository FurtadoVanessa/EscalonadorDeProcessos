import Model.Processo;

import java.util.concurrent.BlockingQueue;

public class Consumidor implements Runnable {

    private final BlockingQueue<Processo> listaDeProcessos;
    private Contador clock = new Contador();

    public Consumidor(BlockingQueue<Processo> listaDeProcessos){
        this.listaDeProcessos = listaDeProcessos;
    }

    @Override
    public synchronized void run() {
        listaDeProcessos.element().executaProcesso();
        System.out.println("Consumiu 1 do processo " +listaDeProcessos.element().getID());

    }
}

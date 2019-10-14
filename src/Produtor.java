import Model.Processo;

import java.util.concurrent.BlockingQueue;

public class Produtor implements Runnable {

    private final BlockingQueue<Processo> listaDeProcessos;
    private Contador clock = new Contador();
    private int ID=0;

    public Produtor(BlockingQueue<Processo> b){
        this.listaDeProcessos = b;
    }


    @Override
    public synchronized void run() {
        while (listaDeProcessos.remainingCapacity()!=0){
            int tamanho = (int)( 10* Math.random() +1);
            Processo p = new Processo(ID, tamanho, clock.getClock());
            listaDeProcessos.add(p);
            System.out.println("Produziu o processo " +ID+" com tamanho "+tamanho);
            this.ID++;
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

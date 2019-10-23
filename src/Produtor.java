import Model.Processo;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

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
                System.out.println("Produziu o processo " + ID + " com tamanho " + tamanho + " com o clock de " + clock.getClock());
                this.ID++;
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

        }
    }
}

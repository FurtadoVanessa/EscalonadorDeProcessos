import Model.Processo;

import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class Consumidor implements Runnable {

    private final BlockingQueue<Processo> listaDeProcessos;
    private ArrayList<Processo> terminados = new ArrayList<>();
    private Contador clock = new Contador();
    private int tipo;

    public Consumidor(BlockingQueue<Processo> listaDeProcessos, int tipo){
        this.listaDeProcessos = listaDeProcessos;
        this.tipo = tipo;
    }

    @Override
    public synchronized void run() {
        if(tipo!=1){
            consomeFIFOeSJF();
        }else{
            consomeRoundRobin();
        }
    }

    private void consomeFIFOeSJF() {
        while(true) {
            if(listaDeProcessos.isEmpty()||clock.getClock()<=2){
                try {
                    System.out.println("Não pode consumir, tem que esperar porque o clock eh "+clock.getClock());
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (!listaDeProcessos.isEmpty()) {

                synchronized (clock) {
                    while (listaDeProcessos.element().disponivel()) {
                        listaDeProcessos.element().executaProcesso();
                        System.out.println("Consumiu 1 do processo " + listaDeProcessos.element().getID() + " com o clock valendo " + clock.getClock());

                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                listaDeProcessos.element().setHoraDeFim(clock.getClock());
                System.out.println("****************Terminou o processo " + listaDeProcessos.element().getID()+" com tempo de "+ listaDeProcessos.element().getTempo());
                terminados.add(listaDeProcessos.remove());
            }
        }
    }

    private void consomeRoundRobin(){
        while(true) {
            if(listaDeProcessos.isEmpty()||clock.getClock()<=2){
                try {
                    System.out.println("Não pode consumir, tem que esperar porque o clock eh "+clock.getClock());
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (!listaDeProcessos.isEmpty()) {
                synchronized (clock) {
                for(int i = 0; i< Escalonador.quantum; i++) {
                    if (listaDeProcessos.element().disponivel()) {

                        listaDeProcessos.element().executaProcesso();
                        System.out.println("Consumiu 1 do processo " + listaDeProcessos.element().getID() + " com o clock valendo " + clock.getClock());
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }else{
                        i = Escalonador.quantum;
                    }
                }
                System.out.println("##################################Terminou o quantum processo " + listaDeProcessos.element().getID()+" com "+listaDeProcessos.element().getTempoRestante());
                }
                Processo p = listaDeProcessos.remove();
                if(p.disponivel()){
                    listaDeProcessos.add(p);
                }else{
                    p.setHoraDeFim(clock.getClock());
                    System.out.println("****************Terminou o processo " + p.getID()+" com tempo de "+ p.getTempo());
                    terminados.add(p);
                }
            }
        }
    }
}

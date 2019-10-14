import Model.Processo;

import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class Escalonador {


    public static void main(String[] args){

        BlockingQueue<Processo> listaDeProcessos = new LinkedBlockingQueue<>(20);

        new Thread(new Contador()).start();
        new Thread(new Produtor(listaDeProcessos)).start();
        new Thread(new Consumidor(listaDeProcessos)).start();



//        while(true) {
//            if(listaDeProcessos.isEmpty()){
//                try {
//                    Thread.sleep(200);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//            if (!listaDeProcessos.isEmpty()) {
//                while (listaDeProcessos.element().disponivel()) {
//                    listaDeProcessos.element().executaProcesso();
//                    System.out.println("Consumiu um do processo " + listaDeProcessos.element().getID());
//                }
//                System.out.println("Terminou o processo " + listaDeProcessos.element().getID());
//                listaDeProcessos.remove();
//            }
//        }


    }


}

import Model.Processo;
import Model.SJF;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.util.concurrent.*;

public class Escalonador {

    public static int quantum = 5;
    public static ArrayList<Processo> terminados;
    public static int numeroDeProcessos;


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        BlockingQueue<Processo> listaDeProcessos;

        //colocar as metricas a cada clock

        Contador clock = new Contador();
        int tipo;

        System.out.println("Escolha o tipo que deseja:\n1 - FIFO\n2 - SJF\n3 - RoundRobin");
        tipo = sc.nextInt();

        if (tipo == 2) {
            listaDeProcessos = new PriorityBlockingQueue(20, new SJF());
        } else {
            listaDeProcessos = new LinkedBlockingQueue<>(20);
        }
        synchronized (clock){
            new Thread(new Contador()).start();
            new Thread(new Produtor(listaDeProcessos)).start();
            new Thread(new Consumidor(listaDeProcessos, tipo)).start();
            while (true) {
                numeroDeProcessos = listaDeProcessos.size();
                 System.out.println(listaDeProcessos);
                 try {
                     Thread.sleep(2000);
                 } catch (InterruptedException e) {
                     e.printStackTrace();
                 }
                }
        }
    }
}

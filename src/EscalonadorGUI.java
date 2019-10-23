import Model.Processo;
import Model.SJF;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class EscalonadorGUI {
    private JPanel panel1;
    private JTable cpu;
    private JButton pausar;
    private JButton FIFO;
    private JButton SJF;
    private JButton RR;
    private JButton parar;
    private JLabel clockLabel;
    private JLabel tem;
    private JLabel tm;
    private JPanel Dados;
    public static int quantum = 5;
    public static ArrayList<Processo> terminados;
    public static int numeroDeProcessos;
    private BlockingQueue<Processo> listaDeProcessos;
    private Thread produtor, consumidor, clock;
    public boolean pausa = false;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Escalonador");
        frame.setContentPane(new EscalonadorGUI().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public void iniciaThread(int tipo){
        this.clock = new Thread(new Contador());
        this.produtor = new Thread(new Produtor(listaDeProcessos));
        this.consumidor = new Thread(new Consumidor(listaDeProcessos, tipo));
        clock.start();
        produtor.start();
        consumidor.start();

        synchronized (clock){
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

public EscalonadorGUI(){

    FIFO.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            listaDeProcessos = new LinkedBlockingQueue<>(20);
            iniciaThread(0);
        }
    });

    SJF.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            listaDeProcessos = new PriorityBlockingQueue(20, new SJF());
            iniciaThread(0);
        }
    });

    RR.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            listaDeProcessos = new LinkedBlockingQueue<>(20);
            iniciaThread(1);
        }
    });

    pausar.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(pausa){
                try {
                    getClock().wait();
                    getProdutor().wait();
                    getConsumidor().wait();
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                pausa = !pausa;
            }
            else{
                getClock().yield();
                getProdutor().yield();
                getConsumidor().yield();
                pausa = !pausa;
            }
        }
    });

    parar.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
                paraThreads();
            System.out.println("Clicou em parar");
        }
    });


}

public void paraThreads() {
        getClock().interrupt();
        getProdutor().interrupt();
        getConsumidor().interrupt();
}


public Thread getProdutor(){
        return this.produtor;
}

public Thread getConsumidor(){
        return this.consumidor;
}

public Thread getClock(){
        return this.clock;
}



    




}

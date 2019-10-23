public class Contador implements Runnable{

    private static int clock;


    @Override
    public synchronized void run() {
        while(true) {
            this.clock++;
            try {
                System.out.println("Clock: " + clock);
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public int getClock(){
        return this.clock;
    }
}

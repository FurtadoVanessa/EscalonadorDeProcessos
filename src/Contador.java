public class Contador implements Runnable{

    private static int clock;


    @Override
    public void run() {
        this.clock++;
        try {
            Thread.sleep(200);
            System.out.println("Clock: "+clock);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public int getClock(){
        return this.clock;
    }
}

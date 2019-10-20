import Model.Processo;

import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;

public class ShortestJob {
    public BlockingQueue<Processo> shortest;
    public ArrayList<Processo> temp = new ArrayList<>();


    public ShortestJob(){
        for(Processo p : shortest){
            temp.add(p);
        }
        //temp.sort();
    }

}

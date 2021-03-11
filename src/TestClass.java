import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class TestClass {
    
    public static void main(String[] args) {

        Deck d = new Deck();
        d.shuffle();
        System.out.println(d.toString());
        d.sort();
        System.out.println(d.toString());
        //Testing three player distribution
        Queue<ArrayList<Card[]>> distributeThree = d.threePlayerDistribute();
        ArrayList<Card[]> test1 = distributeThree.poll();
        Play player1 = new Play("Larry",test1);
        
        //Testing two player distribution
        Queue<ArrayList<Card[]>> distributeTwo = d.twoPlayerDistribute();
        ArrayList<Card[]> test = distributeTwo.poll();
        //Play player1 = new Play("Larry",test);

        //Insertion
        
        System.out.println(player1.playerCardstoString());
           
    }
}

import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;

public class Deck{
    
    private String[] suitList = {"c","d","h","s"};
    private String[] faceList = {"A","2","3","4","5","6","7","8","9","X","J","Q","K"};
    LinkedList<Card> cards;

    Deck() {
        cards = new LinkedList<Card>();

        for(int i = 0; i < suitList.length; i++) {
            for(int j = 0; j < faceList.length; j++)
                cards.add(new Card(suitList[i],faceList[j]));
        }
        
        System.out.println("Deck of cards created!");
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public void sort() {
        Collections.sort(cards);
    }

    public Queue<ArrayList<Card[]>> twoPlayerDistribute() {
        shuffle();
        
        Queue<ArrayList<Card[]>> returnObject = new LinkedList<ArrayList<Card[]>>();
        ArrayList<Card[]> firstPlayer = new ArrayList<Card[]>();
        ArrayList<Card[]> secondPlayer = new ArrayList<Card[]>();
        Card[] tempOne = new Card[5];
        Card[] tempTwo = new Card[5];
            
        int i = 0; 
        
        while (i < 50) {

            tempOne = new Card[5];
            tempTwo = new Card[5];
            
            for(int j = 0; j < 5;j++) {
                if(cards.get(i) != null & i < 50) {
                    tempOne[j] = cards.get(i);
                    i++;
                    tempTwo[j] = cards.get(i);
                    i++;
                }
            }

            firstPlayer.add(tempOne);
            secondPlayer.add(tempTwo);
        }

        if (i == 50) {
            tempOne = new Card[5];
            tempTwo = new Card[5];
            tempOne[3] = cards.get(i);
            i++;
            tempTwo[3] = cards.get(i);
            firstPlayer.add(tempOne);
            secondPlayer.add(tempTwo);
        }   

        returnObject.add(firstPlayer);
        returnObject.add(secondPlayer);
        return returnObject;
        
    }

    public Queue<ArrayList<Card[]>> threePlayerDistribute() {
        shuffle();
        
        ArrayList<Card[]> firstPlayer = new ArrayList<Card[]>();
        ArrayList<Card[]> secondPlayer = new ArrayList<Card[]>();
        ArrayList<Card[]> thirdPlayer = new ArrayList<Card[]>();
        Card[] tempOne = new Card[5];
        Card[] tempTwo = new Card[5];
        Card[] tempThree = new Card[5];

        //cards.size() is fixed to 52 regardless
        int i = 0;
            
        while(i < 50) {
            
            tempOne = new Card[5];
            tempTwo =  new Card[5];
            tempThree = new Card[5];
            
            for(int j = 0; j < 5;j++) {
                if(i == 51) {    
                    tempOne[j] = cards.get(i);
                    i++;
                    j = 5;
                }

                else {
                    tempOne[j] = cards.get(i);
                    i++;
                    tempTwo[j] = cards.get(i);
                    i++;
                    tempThree[j] = cards.get(i);
                    i++;
                }
            }

            firstPlayer.add(tempOne);
            secondPlayer.add(tempTwo);
            thirdPlayer.add(tempThree);
            
        }


        if (i == 50) {
            tempOne = new Card[5];
            tempTwo =  new Card[5];
            tempOne[0] = cards.get(i);    
            i++;
            tempTwo[0] = cards.get(i);
            firstPlayer.add(tempOne);
            secondPlayer.add(tempTwo);
        }

        
        Queue<ArrayList<Card[]>> distribution = new LinkedList<ArrayList<Card[]>>();
        distribution.add(firstPlayer);
        distribution.add(secondPlayer);
        distribution.add(thirdPlayer);
        return distribution;
    }

    public String toString() {
        return cards.toString();
    }

}

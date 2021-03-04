import java.util.HashMap;

public class Card implements Comparable<Card> {

    private String suit;
    private String face;
    
    //Create a card
    Card(String suit, String face) {
        this.suit = suit;
        this.face = face;
    }

    public String returnSuit() {
        return suit;
    }

    public String returnFace() {
        return face;
    }

    public String toString() {
        return suit + face;
    }

    @Override
    public int compareTo(Card c) {

        //Initialize a mapping to sort the face
        HashMap<String,Integer> intFace = new HashMap<String,Integer>();
        String[] faceList = {"A","2","3","4","5","6","7","8","9","X","J","Q","K"};


        //Fill up the hashmap
        for(int i = 0; i < faceList.length; i++) {
            intFace.put(faceList[i],i+1);
        }
        
        //Run Comparator
        if (this.suit.compareTo(c.suit)>0) {
                return 1;
        }
        else if (this.suit.compareTo(c.suit)<0) {
            return -1;
        }
        else {
            if (intFace.get(this.face) > intFace.get(c.face)) 
                return 1;
            else if (intFace.get(this.face) < intFace.get(c.face))
                return -1;
            else
                return 0;
        }

    }


}

    

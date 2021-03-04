import java.util.ArrayList;

class Play {

    public String playerName;
    public ArrayList<Card[]> playerCards;
    private int score;
    public boolean winDeterminor = false;

    Play(String playerName) {
        this.playerName = playerName;
        this.score = 0;
    }

    Play(String playerName, ArrayList<Card[]> playerCards) {
        this.playerName = playerName;
        this.playerCards = playerCards;
        this.score = 0;
    }

    public void addScore(int score) {
        this.score += score; 
    }

    public void resetPlayer() {
        this.score = 0;
        this.playerCards = null;
    }

    public void resetScore() {
        this.score = 0;
    }
    
    public int getScore() {
        return score;
    }

    public ArrayList<Card[]> getArray() {
        return playerCards;
    }

    public String playerCardstoString() {
        //Generate StringBuilder
        StringBuilder sb = new StringBuilder();

        //Loop data to insert into stringBuilder
        for(int i = 0; i < playerCards.size(); i++) {
            for(int j = 0; j < playerCards.get(i).length; j++) {
                if ((playerCards.get(i)[j]) != null) {
                    sb.append(" " + playerCards.get(i)[j]);
                }
                

            }

            sb.append(",");
        }

        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }
}
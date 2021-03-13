import java.util.Scanner;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;


class Game {
    
    public static ArrayList<Object> mainCommandPrompt(String player1, String player2, String player3) {
        System.out.println("*******************");
        System.out.println("* 3-Player Phase  *");
        System.out.println("*******************");
        Scanner player = new Scanner(System.in);
        System.out.println("Player 1: " + player1);
        System.out.println("Player 2: " + player2);
        System.out.println("Player 3: " + player3);

        try {
            Thread.sleep((long)3000);
        }

        catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("");
        System.out.println("Available Cards : ");
        
        Deck round1 = new Deck();
        Queue<ArrayList<Card[]>> round1list = new LinkedList<ArrayList<Card[]>>();
        round1list = round1.threePlayerDistribute();
        System.out.print(player1 + " : ");
        ArrayList<Card[]> player1list = round1list.poll();
        Play player1Player = new Play(player1, player1list);
        System.out.print(player1Player.playerCardstoString());
        System.out.println();

        System.out.print(player2 + " : ");
        ArrayList<Card[]> player2list = round1list.poll();
        Play player2Player = new Play(player2, player2list);
        System.out.print(player2Player.playerCardstoString());
        System.out.println();

        System.out.print(player3 + " : ");
        ArrayList<Card[]> player3list = round1list.poll();
        Play player3Player = new Play(player3, player3list);
        System.out.print(player3Player.playerCardstoString());
        System.out.println();
        
        String shuff;
        do{
            System.out.println("Press s to shuffle or ENTER to start.");
            shuff = player.nextLine();
            if(shuff.isEmpty()){
                System.out.println("<Enter is pressed>");
                break;
            }
            round1 = new Deck();
            round1list = new LinkedList<ArrayList<Card[]>>();
            round1list = round1.threePlayerDistribute();
            player1list = round1list.poll();
            player1Player.playerCards = player1list;
            System.out.print(player1 + " : ");
            System.out.print(player1Player.playerCardstoString());
            System.out.println();

            //round1list = new LinkedList<ArrayList<Card[]>>();
            //round1list = round1.threePlayerDistribute();
            player2list = round1list.poll();
            player2Player.playerCards = player2list;
            System.out.print(player2 + " : ");
            System.out.print(player2Player.playerCardstoString());
            System.out.println();

            //round1list = new LinkedList<ArrayList<Card[]>>();
            //round1list = round1.threePlayerDistribute();
            player3list = round1list.poll();
            player3Player.playerCards = player3list;
            System.out.print(player3 + " : ");
            System.out.print(player3Player.playerCardstoString());
            System.out.println();

        }while(shuff.equals("s"));

        //Round 1 for 3-player phase
        int player1score = 0;
        int player2score = 0;
        int player3score = 0;
        System.out.println("*** ROUND 1 ***");
        System.out.println("Cards on Hand: ");
        player1Player.addScore(fixScore(player1list.get(0)));
        player2Player.addScore(fixScore(player2list.get(0)));
        player3Player.addScore(fixScore(player3list.get(0)));

        if(player1Player.getScore() > player2Player.getScore()) {
            if(player1Player.getScore() > player3Player.getScore()) {
                player1Player.winDeterminor = true;
            }
        }

        if(player2Player.getScore() > player1Player.getScore()){
            if(player2Player.getScore() > player3Player.getScore()) {
                player2Player.winDeterminor = true;
            }
        }

        if(player3Player.getScore() > player1Player.getScore()){
            if(player3Player.getScore() > player2Player.getScore()) {
                player3Player.winDeterminor = true;
            }
        }

        System.out.print(player1 + " : ");
        Card [] player1SortedList1 = returnSortedList(player1list.get(0));
        for(int i = 0; i < player1SortedList1.length; i++){
            System.out.print(player1SortedList1[i] + " ");
        }

        if(player1Player.winDeterminor == true)
            System.out.print(" | Point = " + player1Player.getScore() + " | Win");
        else
            System.out.print(" | Point = " + player1Player.getScore() + " | ");

        System.out.println();
        System.out.print(player2 + " : ");
        Card [] player2SortedList1 = returnSortedList(player2list.get(0));
        for(int i = 0; i < player2SortedList1.length; i++){
            System.out.print(player2SortedList1[i] + " ");
        }
        if(player2Player.winDeterminor == true)
            System.out.print(" | Point = " + player2Player.getScore() + " | Win");
        else
            System.out.print(" | Point = " + player2Player.getScore() + " | ");

        System.out.println();
        System.out.print(player3 + " : ");
        Card [] player3SortedList1 = returnSortedList(player3list.get(0));
        for(int i = 0; i < player3SortedList1.length; i++){
            System.out.print(player3SortedList1[i] + " ");
        }
        if(player3Player.winDeterminor == true)
            System.out.print(" | Point = " + player3Player.getScore() + " | Win");
        else
            System.out.print(" | Point = " + player3Player.getScore() + " | ");
        
        System.out.println("");
        System.out.println("Score: ");
        if(player1Player.getScore() > player2Player.getScore()){
            if(player1Player.getScore() > player3Player.getScore())
                player1score += player1Player.getScore();
        }

        if(player2Player.getScore() > player1Player.getScore()){
            if(player2Player.getScore() > player3Player.getScore())
                player2score += player2Player.getScore();
        }

        if(player3Player.getScore() > player1Player.getScore()){
            if(player3Player.getScore() > player2Player.getScore())
                player3score += player3Player.getScore();
        }

        if(player1Player.getScore() == player2Player.getScore()){
            player1score += player1Player.getScore();
            player2score += player2Player.getScore();
        }

        if(player2Player.getScore() == player3Player.getScore()){
            player2score += player2Player.getScore();
            player3score += player3Player.getScore();
        }

        if(player1Player.getScore() == player3Player.getScore()){
            player1score += player2Player.getScore();
            player3score += player3Player.getScore();
        }

        System.out.println(player1 + " = " + player1score);
        System.out.println(player2 + " = " + player2score);
        System.out.println(player3 + " = " + player3score);

        
        System.out.println();
        System.out.println("Available cards : ");
        player1Player.playerCards.remove(0);
        player2Player.playerCards.remove(0);
        player3Player.playerCards.remove(0);
        System.out.println(player1 + " : " + player1Player.playerCardstoString());
        System.out.println(player2 + " : " + player2Player.playerCardstoString());
        System.out.println(player3 + " : " + player3Player.playerCardstoString());
        player1Player.winDeterminor = false;
        player2Player.winDeterminor = false;
        player3Player.winDeterminor = false;

        //Round 2 for 3-player phase
        System.out.println();
        System.out.println("*** ROUND 2 ***");
        System.out.println("Cards on Hand: ");
        player1Player.resetScore();
        player2Player.resetScore();
        player3Player.resetScore();
        player1Player.addScore(fixScore(player1list.get(0)));
        player2Player.addScore(fixScore(player2list.get(0)));
        player3Player.addScore(fixScore(player3list.get(0)));

        if(player1Player.getScore() > player2Player.getScore()) {
            if(player1Player.getScore() > player3Player.getScore()) {
                player1Player.winDeterminor = true;
            }
        }

        if(player2Player.getScore() > player1Player.getScore()){
            if(player2Player.getScore() > player3Player.getScore()) {
                player2Player.winDeterminor = true;
            }
        }

        if(player3Player.getScore() > player1Player.getScore()){
            if(player3Player.getScore() > player2Player.getScore()) {
                player3Player.winDeterminor = true;
            }
        }

        System.out.print(player1 + " : ");
        Card [] player1SortedList2 = returnSortedList(player1list.get(0));
        for(int i = 0; i < player1SortedList2.length; i++){
            System.out.print(player1SortedList2[i] + " ");
        }
        if(player1Player.winDeterminor == true)
            System.out.print(" | Point = " + player1Player.getScore() + " | Win");
        else
            System.out.print(" | Point = " + player1Player.getScore() + " | ");

        System.out.println();
        System.out.print(player2 + " : ");
        Card [] player2SortedList2 = returnSortedList(player2list.get(0));
        for(int i = 0; i < player2SortedList2.length; i++){
            System.out.print(player2SortedList2[i] + " ");
        }
        if(player2Player.winDeterminor == true)
            System.out.print(" | Point = " + player2Player.getScore() + " | Win");
        else
            System.out.print(" | Point = " + player2Player.getScore() + " | ");

        System.out.println();
        System.out.print(player3 + " : ");
        Card [] player3SortedList2 = returnSortedList(player3list.get(0));
        for(int i = 0; i < player3SortedList2.length; i++){
            System.out.print(player3SortedList2[i] + " ");
        }
        if(player3Player.winDeterminor == true)
            System.out.print(" | Point = " + player3Player.getScore() + " | Win");
        else
            System.out.print(" | Point = " + player3Player.getScore() + " | ");
        System.out.println();

        System.out.println("Score: ");
        if(player1Player.getScore() > player2Player.getScore()){
            if(player1Player.getScore() > player3Player.getScore())
                player1score += player1Player.getScore();
        }

        if(player2Player.getScore() > player1Player.getScore()){
            if(player2Player.getScore() > player3Player.getScore())
                player2score += player2Player.getScore();
        }

        if(player3Player.getScore() > player1Player.getScore()){
            if(player3Player.getScore() > player2Player.getScore())
                player3score += player3Player.getScore();
        }

        if(player1Player.getScore() == player2Player.getScore()){
            player1score += player1Player.getScore();
            player2score += player2Player.getScore();
        }

        if(player2Player.getScore() == player3Player.getScore()){
            player2score += player2Player.getScore();
            player3score += player3Player.getScore();
        }

        if(player1Player.getScore() == player3Player.getScore()){
            player1score += player2Player.getScore();
            player3score += player3Player.getScore();
        }

        System.out.println(player1 + " = " + player1score);
        System.out.println(player2 + " = " + player2score);
        System.out.println(player3 + " = " + player3score);
        System.out.println();

        System.out.println("Available cards : ");
        player1Player.playerCards.remove(0);
        player2Player.playerCards.remove(0);
        player3Player.playerCards.remove(0);
        System.out.println(player1 + ":" + player1Player.playerCardstoString());
        System.out.println(player2 + ":" + player2Player.playerCardstoString());
        System.out.println(player3 + ":" + player3Player.playerCardstoString());
        player1Player.winDeterminor = false;
        player2Player.winDeterminor = false;
        player3Player.winDeterminor = false;

        //Round 3 for 3-player phase
        System.out.println();
        System.out.println("*** ROUND 3 ***");
        System.out.println("Cards on Hand: ");
        player1Player.resetScore();
        player2Player.resetScore();
        player3Player.resetScore();
        player1Player.addScore(fixScore(player1list.get(0)));
        player2Player.addScore(fixScore(player2list.get(0)));
        player3Player.addScore(fixScore(player3list.get(0)));

        if(player1Player.getScore() > player2Player.getScore()) {
            if(player1Player.getScore() > player3Player.getScore()) {
                player1Player.winDeterminor = true;
            }
        }

        if(player2Player.getScore() > player1Player.getScore()){
            if(player2Player.getScore() > player3Player.getScore()) {
                player2Player.winDeterminor = true;
            }
        }

        if(player3Player.getScore() > player1Player.getScore()){
            if(player3Player.getScore() > player2Player.getScore()) {
                player3Player.winDeterminor = true;
            }
        }

        System.out.print(player1 + " : ");
        Card [] player1SortedList3 = returnSortedList(player1list.get(0));
        for(int i = 0; i < player1SortedList3.length; i++){
            System.out.print(player1SortedList3[i] + " ");
        }
        if(player1Player.winDeterminor == true)
            System.out.print(" | Point = " + player1Player.getScore() + " | Win");
        else
            System.out.print(" | Point = " + player1Player.getScore() + " | ");

        System.out.println();
        System.out.print(player2 + " : ");
        Card [] player2SortedList3 = returnSortedList(player2list.get(0));
        for(int i = 0; i < player2SortedList3.length; i++){
            System.out.print(player2SortedList3[i] + " ");
        }
        if(player2Player.winDeterminor == true)
            System.out.print(" | Point = " + player2Player.getScore() + " | Win");
        else
            System.out.print(" | Point = " + player2Player.getScore() + " | ");

        System.out.println();
        System.out.print(player3 + " : ");
        Card [] player3SortedList3 = returnSortedList(player3list.get(0));
        for(int i = 0; i < player3SortedList3.length; i++){
            System.out.print(player3SortedList3[i] + " ");
        }
        if(player3Player.winDeterminor == true)
            System.out.print(" | Point = " + player3Player.getScore() + " | Win");
        else
            System.out.print(" | Point = " + player3Player.getScore() + " | ");
        
        System.out.println();
        System.out.println("Score: ");
        if(player1Player.getScore() > player2Player.getScore()){
            if(player1Player.getScore() > player3Player.getScore())
                player1score += player1Player.getScore();
        }

        if(player2Player.getScore() > player1Player.getScore()){
            if(player2Player.getScore() > player3Player.getScore())
                player2score += player2Player.getScore();
        }

        if(player3Player.getScore() > player1Player.getScore()){
            if(player3Player.getScore() > player2Player.getScore())
                player3score += player3Player.getScore();
        }

        if(player1Player.getScore() == player2Player.getScore()){
            player1score += player1Player.getScore();
            player2score += player2Player.getScore();
        }

        if(player2Player.getScore() == player3Player.getScore()){
            player2score += player2Player.getScore();
            player3score += player3Player.getScore();
        }

        if(player1Player.getScore() == player3Player.getScore()){
            player1score += player2Player.getScore();
            player3score += player3Player.getScore();
        }

        System.out.println(player1 + " = " + player1score);
        System.out.println(player2 + " = " + player2score);
        System.out.println(player3 + " = " + player3score);
        System.out.println();
        System.out.println("Available cards : ");
        player1Player.playerCards.remove(0);
        player2Player.playerCards.remove(0);
        player3Player.playerCards.remove(0);
        System.out.println(player1 + ":" + player1Player.playerCardstoString());
        System.out.println(player2 + ":" + player2Player.playerCardstoString());
        System.out.println(player3 + ":" + player3Player.playerCardstoString());
        player1Player.winDeterminor = false;
        player2Player.winDeterminor = false;
        player3Player.winDeterminor = false;

        int[] playerScores = {player1score,player2score,player3score};
        Play[] currentPlayers = {player1Player,player2Player,player3Player};
        ArrayList<Object> returnobject = new ArrayList<>();
        returnobject.add(playerScores);
        returnobject.add(currentPlayers);

        return returnobject;
    }
    
    public static Play[] phase2(Play player1, Play player2) {
        Deck round2 = new Deck();
        System.out.println("*******************");
        System.out.println("* 2-Player Phase  *");
        System.out.println("*******************");
        Scanner player = new Scanner(System.in);
        Queue<ArrayList<Card[]>> round2list = new LinkedList<ArrayList<Card[]>>();
        round2list = round2.twoPlayerDistribute();
        System.out.print(player1.playerName + " : ");
        ArrayList<Card[]> player1list = round2list.poll();
        Play player1Player = new Play(player1.playerName, player1list);
        System.out.print(player1Player.playerCardstoString());
        System.out.println();

        System.out.print(player2.playerName + " : ");
        ArrayList<Card[]> player2list = round2list.poll();
        Play player2Player = new Play(player2.playerName, player2list);
        System.out.print(player2Player.playerCardstoString());
        System.out.println();

        String shuff;
        do{
            System.out.println("Press s to shuffle or ENTER to start.");
            shuff = player.nextLine();
            if(shuff.isEmpty()){
                System.out.println("<Enter is pressed>");
                break;
            }

            round2 = new Deck();
            round2list = new LinkedList<ArrayList<Card[]>>();
            round2list = round2.twoPlayerDistribute();
            player1list = round2list.poll();
            player1Player.playerCards = player1list;
            System.out.print(player1.playerName + " : ");
            System.out.print(player1Player.playerCardstoString());
            System.out.println();

            round2list = new LinkedList<ArrayList<Card[]>>();
            round2list = round2.twoPlayerDistribute();
            player2list = round2list.poll();
            player2Player.playerCards = player2list;
            System.out.print(player2.playerName + " : ");
            System.out.print(player2Player.playerCardstoString());
            System.out.println();
        }while(shuff.equals("s"));

        //Round 1 for 2-player phase
        int player1score2 = 0;
        int player2score2 = 0;
        System.out.println("*** ROUND 1 ***");
        System.out.println("Cards on Hand: ");
        player1Player.addScore(fixScore(player1list.get(0)));
        player2Player.addScore(fixScore(player2list.get(0)));

        if (player1Player.getScore() > player2Player.getScore())
            player1Player.winDeterminor = true;

        else
            player2Player.winDeterminor = true;

        System.out.print(player1.playerName + " : ");
        Card [] player1SortedList1 = returnSortedList(player1list.get(0));
        for(int i = 0; i < player1SortedList1.length; i++){
            System.out.print(player1SortedList1[i] + " ");
        }
        if (player1Player.winDeterminor == true)
            System.out.print(" | Point = " + player1Player.getScore() + " | Win");
        else
            System.out.print(" | Point = " + player1Player.getScore() + " | ");
        
        System.out.println();
        System.out.print(player2.playerName + " : ");
        Card [] player2SortedList1 = returnSortedList(player2list.get(0));
        
        for(int i = 0; i < player2SortedList1.length; i++){
            System.out.print(player2SortedList1[i] + " ");
        }
        if (player2Player.winDeterminor == true)
            System.out.print(" | Point = " + player2Player.getScore() + " | Win");
        else
            System.out.print(" | Point = " + player2Player.getScore() + " | ");

        System.out.println("");
        System.out.println("Score: ");
        if(player1Player.getScore() > player2Player.getScore())
            player1score2 += player1Player.getScore();
        else if(player2Player.getScore() > player1Player.getScore())
            player2score2 += player2Player.getScore();
        else if(player2Player.getScore() == player1Player.getScore()) {   
            player1score2 += player1Player.getScore();
            player2score2 += player2Player.getScore();
        }

        System.out.println(player1.playerName + " = " + player1score2);
        System.out.println(player2.playerName + " = " + player2score2);

        System.out.println();
        System.out.println("Available cards : ");
        player1Player.playerCards.remove(0);
        player2Player.playerCards.remove(0);
        System.out.println(player1.playerName + " : " + player1Player.playerCardstoString());
        System.out.println(player2.playerName + " : " + player2Player.playerCardstoString());
        player1Player.winDeterminor = false;
        player2Player.winDeterminor = false;

        System.out.println();
        System.out.println("*** ROUND 2 ***");
        System.out.println("Cards on Hand: ");
        player1Player.resetScore();
        player2Player.resetScore();
        player1Player.addScore(fixScore(player1list.get(0)));
        player2Player.addScore(fixScore(player2list.get(0)));

        if (player1Player.getScore() > player2Player.getScore())
            player1Player.winDeterminor = true;

        else
            player2Player.winDeterminor = true;

        System.out.print(player1.playerName + " : ");
        Card [] player1SortedList2 = returnSortedList(player1list.get(0));
        for(int i = 0; i < player1SortedList2.length; i++){
            System.out.print(player1SortedList2[i] + " ");
        }
        if (player1Player.winDeterminor == true)
            System.out.print(" | Point = " + player1Player.getScore() + " | Win");
        else
            System.out.print(" | Point = " + player1Player.getScore() + " | ");
            
        System.out.println();
        System.out.print(player2.playerName + " : ");
        Card [] player2SortedList2 = returnSortedList(player2list.get(0));
        for(int i = 0; i < player2SortedList2.length; i++){
            System.out.print(player2SortedList2[i] + " ");
        }
        if(player2Player.winDeterminor == true)
            System.out.print(" | Point = " + player2Player.getScore() + " | Win");
        else
            System.out.print(" | Point = " + player2Player.getScore() + " | ");

        System.out.println("");
        System.out.println("Score: ");
        if(player1Player.getScore() > player2Player.getScore())
            player1score2 += player1Player.getScore();
        else if(player2Player.getScore() > player1Player.getScore())
            player2score2 += player2Player.getScore();
        else if(player2Player.getScore() == player1Player.getScore()){ 
            player1score2 += player1Player.getScore();
            player2score2 += player2Player.getScore();
        }
        System.out.println(player1.playerName + " = " + player1score2);
        System.out.println(player2.playerName + " = " + player2score2);

        System.out.println();
        System.out.println("Available cards : ");
        player1Player.playerCards.remove(0);
        player2Player.playerCards.remove(0);
        System.out.println(player1.playerName + " : " + player1Player.playerCardstoString());
        System.out.println(player2.playerName + " : " + player2Player.playerCardstoString());
        player1Player.winDeterminor = false;
        player2Player.winDeterminor = false;

        System.out.println();
        System.out.println("*** ROUND 3 ***");
        System.out.println("Cards on Hand: ");
        player1Player.resetScore();
        player2Player.resetScore();
        player1Player.addScore(fixScore(player1list.get(0)));
        player2Player.addScore(fixScore(player2list.get(0)));

        if (player1Player.getScore() > player2Player.getScore())
            player1Player.winDeterminor = true;

        else
            player2Player.winDeterminor = true;

        System.out.print(player1.playerName + " : ");
        Card [] player1SortedList3 = returnSortedList(player1list.get(0));
        for(int i = 0; i < player1SortedList3.length; i++){
            System.out.print(player1SortedList3[i] + " ");
        }
        if (player1Player.winDeterminor == true)
            System.out.print(" | Point = " + player1Player.getScore() + " | Win");
        else
            System.out.print(" | Point = " + player1Player.getScore() + " | ");
                
        System.out.println();
        System.out.print(player2.playerName + " : ");
        Card [] player2SortedList3 = returnSortedList(player2list.get(0));
        for(int i = 0; i < player2SortedList3.length; i++){
            System.out.print(player2SortedList3[i] + " ");
        }
        if(player2Player.winDeterminor == true)
            System.out.print(" | Point = " + player2Player.getScore() + " | Win");
        else
            System.out.print(" | Point = " + player2Player.getScore() + " | ");
            
        System.out.println("");
        System.out.println("Score: ");
        if(player1Player.getScore() > player2Player.getScore())
            player1score2 += player1Player.getScore();
        else if(player2Player.getScore() > player1Player.getScore())
            player2score2 += player2Player.getScore();
        else if(player2Player.getScore() == player1Player.getScore()) {   
            player1score2 += player1Player.getScore();
            player2score2 += player2Player.getScore();
        }
        System.out.println(player1.playerName + " = " + player1score2);
        System.out.println(player2.playerName + " = " + player2score2);

        System.out.println();
        System.out.println("Available cards : ");
        player1Player.playerCards.remove(0);
        player2Player.playerCards.remove(0);
        System.out.println(player1.playerName + " : " + player1Player.playerCardstoString());
        System.out.println(player2.playerName + " : " + player2Player.playerCardstoString());
        player1Player.winDeterminor = false;
        player2Player.winDeterminor = false;

        System.out.println();
        System.out.println("*** ROUND 4 ***");
        System.out.println("Cards on Hand: ");
        player1Player.resetScore();
        player2Player.resetScore();
        player1Player.addScore(fixScore(player1list.get(0)));
        player2Player.addScore(fixScore(player2list.get(0)));   
        
        if (player1Player.getScore() > player2Player.getScore())
            player1Player.winDeterminor = true;

        else
            player2Player.winDeterminor = true;

        System.out.print(player1.playerName + " : ");
        Card [] player1SortedList4 = returnSortedList(player1list.get(0));
        for(int i = 0; i < player1SortedList4.length; i++){
            System.out.print(player1SortedList4[i] + " ");
        }
        if (player1Player.winDeterminor == true)
            System.out.print(" | Point = " + player1Player.getScore() + " | Win");
        else
            System.out.print(" | Point = " + player1Player.getScore() + " | ");

        System.out.println();
        System.out.print(player2.playerName + " : ");
        Card [] player2SortedList4 = returnSortedList(player2list.get(0));
        for(int i = 0; i < player2SortedList4.length; i++){
            System.out.print(player2SortedList4[i] + " ");
        }
        if(player2Player.winDeterminor == true)
            System.out.print(" | Point = " + player2Player.getScore() + " | Win");
        else
            System.out.print(" | Point = " + player2Player.getScore() + " | ");

        System.out.println("");
        System.out.println("Score: ");
        if(player1Player.getScore() > player2Player.getScore())
            player1score2 += player1Player.getScore();
        else if(player2Player.getScore() > player1Player.getScore())
            player2score2 += player2Player.getScore();
        else if(player2Player.getScore() == player1Player.getScore()) {   
            player1score2 += player1Player.getScore();
            player2score2 += player2Player.getScore();
        }
        System.out.println(player1.playerName + " = " + player1score2);
        System.out.println(player2.playerName + " = " + player2score2);
    
        System.out.println();
        System.out.println("Available cards : ");
        player1Player.playerCards.remove(0);
        player2Player.playerCards.remove(0);
        System.out.println(player1.playerName + " : " + player1Player.playerCardstoString());
        System.out.println(player2.playerName + " : " + player2Player.playerCardstoString());
        player1Player.winDeterminor = false;
        player2Player.winDeterminor = false;

        //Update Scores
        player1Player.resetScore();
        player2Player.resetScore();
        player1Player.addScore(player1score2);
        player2Player.addScore(player2score2);
        Play[] getPeople = {player1Player,player2Player};

        if(player1score2 > player2score2)
            System.out.println("***** " + player1.playerName + " is the WINNER! *****");
        else
            System.out.println("***** " + player2.playerName + " is the WINNER! *****");
        return getPeople;
    }

    public static int fixScore(Card[] cards) {
        
        //Initialize a mapping to sort the face
        HashMap<String,Integer> intFace = new HashMap<String,Integer>();
        String[] faceList = {"A","2","3","4","5","6","7","8","9","X","J","Q","K"};
        int[] returnScore = new int[5];
        int tempScore = 0;

        //Fill up the hashmap
        for(int i = 0; i < faceList.length; i++) {
            if (i < 10)
                intFace.put(faceList[i],i+1);
            else
                intFace.put(faceList[i],10);
        }

        //Get place to seperate the suit
        ArrayList<Card[]> suits = new ArrayList<Card[]>();
        Card[] tempsamesuit;
        String[] suitList = {"c","d","h","s"};
        int index = 0;
        for(int i = 0; i < 4; i++) {
            tempsamesuit = new Card[5];
            for(int j = 0; j < 5; j++) {
                if (suitList[i].equals(cards[j].returnSuit()))
                    tempsamesuit[index] = cards[j];
                    index++;
            }
            index = 0;
            suits.add(tempsamesuit);
        }
        
        //Iterate the seperated cards and add the score
        for(int i = 0; i < suits.size(); i++) {
            for(int j = 0; j < suits.get(i).length;j++) {
                if((suits.get(i)[j]) != null)
                    tempScore += intFace.get(suits.get(i)[j].returnFace());
            }
            returnScore[i] = tempScore;
            tempScore = 0;
        }
        
        //Find the highest score
        int highestScore = 0;
        for(int i = 0; i < returnScore.length; i++) {
            if (returnScore[i] >= highestScore)
                highestScore = returnScore[i];
        }
        return highestScore;
    }

    public static Card [] returnSortedList(Card [] initialSort) {
        ArrayList<Card> sortedList = new ArrayList<Card>();
        for(int i = 0; i < initialSort.length; i++){
            sortedList.add(initialSort[i]);
        }
        Collections.sort(sortedList);
        Card [] sortedArray = new Card [5];
        for(int i = 0; i < sortedList.size(); i++){
            sortedArray[i] = sortedList.get(i);
        }
        return sortedArray;
    }
}

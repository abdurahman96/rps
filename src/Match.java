
import java.util.ArrayList;


public class Match {

    private Player playerOne;
    private Player playerTwo;
    private boolean isComplete;
    private int matchNumber;
    private int roundNumber;
    
    public Match(int roundNumber, int matchNumber, Player playerOne, Player playerTwo) {
    this.roundNumber = roundNumber;    
    this.matchNumber = matchNumber;
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        isComplete = false;
    }

    public Player getPlayerOne() {
        return playerOne;
    }

    public Player getPlayerTwo() {
        return playerTwo;
    }
    
    public boolean isOver(){
        return isComplete;
    }
    
    public void markAsComplete(){
        isComplete = true;
    }
    
    public ArrayList<Player> getPlayers(){
        ArrayList<Player> players = new ArrayList<>();
        players.add(playerOne);
        players.add(playerTwo);
        return players;
    }
    
    
   
    @Override
    public String toString(){
        return String.format(
                "Round # : %02d Match # %02d:\n\t%-20s v %20s\n\n",
                    roundNumber, matchNumber, playerOne.toString(),
                    playerTwo.toString());
    }
    
}


import java.util.ArrayList;


public class Match {

    private Player playerOne;
    private Player playerTwo;
    private boolean isComplete;
    private int matchNumber;
    private int roundNumber;
    
    //Constructor
    public Match(int roundNumber, int matchNumber, Player playerOne, Player playerTwo) {
    this.roundNumber = roundNumber;    
    this.matchNumber = matchNumber;
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        isComplete = false;
    }

    //getters
    public Player getPlayerOne() {
        return playerOne;
    }

    public Player getPlayerTwo() {
        return playerTwo;
    }
    
    /* isOver
     * public boolean isOver()
     * returns whether if the match is over
     * parameter - none
     * precondition: isComplete is declared
     * postcondition: returned whether if the match is complete
     */
    public boolean isOver(){
        return isComplete;
    }
    
    /* markAsComplete()
     * public void markAsComplete()
     * sets isComplete to true
     * parameters - none
     * precondition: none
     * postcondition: is complete is made true
     */
    public void markAsComplete(){
        isComplete = true;
    }
    
    /* getPlayers
     * public ArrayList<Player> getPlayers()
     * turns the two players into a array list and returns it
     * parameters - none
     * precondition: the players are defined
     * postcondition: the players are returned as an array list
     */
    public ArrayList<Player> getPlayers(){
        ArrayList<Player> players = new ArrayList<>();
        players.add(playerOne);
        players.add(playerTwo);
        return players;
    }
    
    /* toString
     * public String toString()
     * returns everything as a string
     * parameters - none
     * precondition: none
     * postcondition: returned a string of the variables
     */
    @Override
    public String toString(){
        return String.format(
                "Round # : %02d Match # %02d:\n\t%-20s v %20s\n\n",
                    roundNumber, matchNumber, playerOne.toString(),
                    playerTwo.toString());
    }
    
}


// necessary imports
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

// this class represents a Tournament of the Game
public class Tournament {

    // current round of the tournament
    int round;
    // total players in this tournament
    int totalPlayers;
    // this Linked List stores the heirarchy of matches in this tournaments
    LinkedList<Player> matchesSchedule;
    int matchCounter;

    // constructor
    Tournament(ArrayList<Player> playersList) {
        totalPlayers = playersList.size();
        matchesSchedule = scheduleMatches(playersList);
        round = 1;
        matchCounter = 1;
    }

    /* scheduleMatches
     * private LinkedList<Player> scheduleMatches(ArrayList<Player> players)
     * randomly schedule matches for this tournament
     * parameter - ArrayList<Player> players
     * precondition: Array list is declared and has players in it
     * postcondition: matches are scheduled
     */
    private LinkedList<Player> scheduleMatches(ArrayList<Player> players) {
        LinkedList<Player> schedule = new LinkedList<>();
        Random random = new Random();
        // create match matchesSchedule
        while (!players.isEmpty()) {
            int num = random.nextInt(players.size());
            schedule.add(players.remove(num));
        }
        return schedule;
    }
    
    /* getFirstRoundDetails
     * public String getFirstRoundDetails()
     * gets first round details and prints them
     * parameters - none
     * precondition: a round has happened
     * postcondition: the details are printed out
     */
    public String getFirstRoundDetails() {
        String output = String.format("Round # : %02d \n", round);
        int counter = 1;
        for (int i = matchesSchedule.size() - 1; i >= 0;) {
            Player playerOne = matchesSchedule.get(i--);
            Player playerTwo = matchesSchedule.get(i--);
            output += String.format("Match # %02d:\n\t%-20s v %20s\n",
                    counter++, playerOne, playerTwo);
        }
        return output + "\n------------------\n\n";
    }

    /* nextMatch
     * public Match nextMatch()
     * start the tournament
     * parameter - none
     * precondition: the tournament variables are set up
     * Postcondition: match is started
     */
    public Match nextMatch() {
        
        if (matchCounter <= (totalPlayers / 2 * round)) {
            matchCounter++;
        } else {
            ArrayList<Player> players = new ArrayList<>(matchesSchedule.size());
            for (int j = 0; j < matchesSchedule.size(); j++) {
                Player temp = matchesSchedule.get(j);
                temp.setIsHuman(true);
                players.add(temp);
            }
            matchesSchedule = scheduleMatches(players);
            round++;
            matchCounter = 2;
        }
        Match tempMatch = null;
        if (!isComplete()) {
            tempMatch = new Match(round, matchCounter - 1, matchesSchedule.removeLast(), matchesSchedule.removeLast());
        }
        
        return tempMatch;
    }
    
    /* moveWinnerToNextRound
     * public void moveWinnerToNextRound(Player winner)
     * moves winners to next round
     * parameter - Player winner
     * precondition: there was a winner
     * postcondition: winner is moved to next round
     */
    public void moveWinnerToNextRound(Player winner) {
        matchesSchedule.addFirst(winner);
    }
    
    /* isComplete
     * public boolean isComplete()
     * checks whether all the matches have been done
     * parameter - none
     * precondition: matchesSchedule exists
     * postcondition: returned whether all the matches are done
     */
    public boolean isComplete() {
        return matchesSchedule == null || matchesSchedule.isEmpty();
    }
}


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

    // randomly schedule matches for this tournament
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

    // start the tournament
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
    
    public void moveWinnerToNextRound(Player winner) {
        matchesSchedule.addFirst(winner);
    }
    
    public boolean isComplete() {
        return matchesSchedule == null || matchesSchedule.isEmpty();
    }
}

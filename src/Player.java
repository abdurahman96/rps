
// this class represents a player of the game

public class Player {
    // name of the player
    private String name;
    // sign the player selects in a game turn .i.e. R, P or S
    private String sign;
    // this tells whether the player is computer or human
    private boolean isHuman;
    // this tells whether the player lost, won or draw a game turn against his opponents
    /*
        0 = Draw
        1 = Won
        -1 = Lost
    */
    private int win;

    private int gamesWonCount;
    private int gamesLostCount;
    
    // constructor
    public Player(String name, boolean isHuman) {
        this.name = name;
        this.isHuman = isHuman;
        win = 0;
        sign = "";
        gamesWonCount = 0;
        gamesLostCount = 0;
                
    }

    /* isHuman
     * public boolean isHuman()
     * this method tells that whether the player is human or computer
     * parameters - none
     * precondition: is human is declared
     * postcondition: returns whether isHuman is true or false
     */
    public boolean isHuman() {
        return isHuman;
    }
    
    //getters and setters
    public String getName() {
        return name;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    /* haveLost
     * public boolean haveLost()
     * sets the player to have lost
     * parameters - none
     * precondition: none
     * postcondition: win is set to -1
     */
    public boolean haveLost() {
        return win == -1;
    }
    
    /* getGameStatus
     * public int getGameStatus
     * this method tells the current status of the player in the game
     *
     *   0 = Draw
     *   1 = Won
     *   -1 = Lost
     *   
     *   parameters - none
     *   precondition: win is set to 1, 0 , or -1
     *   postcondition: returns win
    */
    public int getGameStatus(){
        return win;
    }

    /*setGameStatus
     * this method sets the current status of the player in the game
     * 
     *    0 = Draw
     *    1 = Won
     *    -1 = Lost
     *    
     *    parameters - none
     *    precondition: none
     *    postcondition: win is set to 1, 0, or -1
    */
    public void setGameStatus(int winStatus){
        win = winStatus;
    }
    
    /* toString
     * public String toString()
     * string representation of the player
     * parameters - none
     * precondition: none
     * postcondition: returns a string with all the variables
     */
    @Override
    public String toString(){
        return String.format("%s %s",name,!isHuman ? ("(Computer)") : "");
    }

    //getter
    public int getGamesWonCount() {
        return gamesWonCount;
    }

    /* incrementGamesWonCount
     * public void incrementGamesWonCount()
     * increments games won count
     * parameters - none
     * precondition: gamesWonCount declared
     * postcondition: gamesWonCound incremented
     */
    public void incrementGamesWonCount() {
        this.gamesWonCount++;
    }

    //getter
    public int getGamesLostCount() {
        return gamesLostCount;
    }

    /* incrementGamesLostCount
     * public void incrementGamesLostCount()
     * increments games lost count
     * parameters - none
     * precondition: gamesLostCount declared
     * postcondition: gamesLostCound incremented
     */
    public void incrementGamesLostCount() {
        this.gamesLostCount++;
    }

    //setter
    public void setIsHuman(boolean isHuman) {
        this.isHuman = isHuman;
    }
    
    /* stats
     * public String stats()
     * returns stats as a string
     * parameters - none
     * precondition: none
     * postcondition: returned a string with the stats
     */
    public String stats(){
        return String.format("Name: %s, Games Won: %d, Games Lost: %d",
                name, gamesWonCount,gamesLostCount);
    }
    
}

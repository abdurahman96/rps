
// necessary imports
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class Game extends Application {

    // constant values
    private final String ROCK = "Rock";
    private final String PAPER = "Paper";
    private final String SCISSORS = "Scissors";

    // gui elements
    private ObservableList<Player> allPlayersList;
    private TextArea outputArea;
    private RadioButton rockButton;
    private RadioButton paperButton;
    private RadioButton scissorsButton;
    private RadioButton singleGame;
    private RadioButton tournamentGame;
    private Label turnLabel;
    private Button startGameButton;
    private Button submitButton;
    private TableView<Player> playerTable;
    private Button addPlayerButton;
    private Button removePlayerButton;
    private Tournament rpsGame;
    private Match currentMatch;

    /*
     * The main method
     * public static void main(String[] args)
     * the main method
     * @param args the command line arguments
     * precondition: none
     * postcondition: the program starts
     */
    public static void main(String[] args) {

        launch(args);

    }

    /* match
     * public void match(Match match)
     * this method contains the logic of game to decide the winners and lossers
     * parameters - Match Match has players
     * precondition: The players are declared
     * postcondition: the match has been decided
     */
    public void match(Match match) {

        Player p1 = match.getPlayerOne();
        Player p2 = match.getPlayerTwo();

        // if both players have same choices, game is draw
        if (p1.getSign().equalsIgnoreCase(p2.getSign())) {
            p1.setGameStatus(0);
            p2.setGameStatus(0);
        } else if (p1.getSign().equalsIgnoreCase(ROCK)) {
            // Paper folds the rock
            if (p2.getSign().equalsIgnoreCase(PAPER)) {
                p2.setGameStatus(1);
                p1.setGameStatus(-1);
            } // Rock crashes the scissors
            else if (p2.getSign().equalsIgnoreCase(SCISSORS)) {
                p1.setGameStatus(1);
                p2.setGameStatus(-1);
            }
        } else if (p1.getSign().equalsIgnoreCase(PAPER)) {
            // Paper folds the rock
            if (p2.getSign().equalsIgnoreCase(ROCK)) {
                p1.setGameStatus(1);
                p2.setGameStatus(-1);
            } // Scissors cuts the paper
            else if (p2.getSign().equalsIgnoreCase(SCISSORS)) {
                p2.setGameStatus(1);
                p1.setGameStatus(-1);
            }
        } else if (p1.getSign().equalsIgnoreCase(SCISSORS)) {
            // Rock crashes the scissors
            if (p2.getSign().equalsIgnoreCase(ROCK)) {
                p2.setGameStatus(1);
                p1.setGameStatus(-1);
            } // Scissors cuts the paper
            else if (p2.getSign().equalsIgnoreCase(PAPER)) {
                p1.setGameStatus(1);
                p2.setGameStatus(-1);
            }
        }

    }

    /* getUserSymbol
     * private String getUserSymbol()
     * get the choice of user
     * parameters: none
     * precondition: none
     * postcondition: returns the choice
     */
    private String getUserSymbol() {
        String userChoice = null;
        if (rockButton.isSelected()) {
            userChoice = ROCK;
        } else if (paperButton.isSelected()) {
            userChoice = PAPER;
        } else if (scissorsButton.isSelected()) {
            userChoice = SCISSORS;
        }
        return userChoice;
    }

    /* getComputerSymbol
     * private String getComputerSymbol
     * select randomly and return the choice of a computer player
     * parameters - none
     * preconditions: none
     * postconditions: the computer's choice is returned
     */
    private String getComputerSymbol() {
        int num;
        Random randomFactory = new Random();
        num = randomFactory.nextInt(3);
        String sign = "";
        switch (num) {
            case 0:
                sign = ROCK;
                break;
            case 1:
                sign = PAPER;
                break;
            case 2:
                sign = SCISSORS;
                break;
            default:
                break;
        }
        return sign;
    }
    
    /* start
     * public void start(Stage primaryStage) throws Exception
     * creates and controls the GUI
     * parameters - Stage primaryStage
     * precondition: none
     * postcondition: the GUI is created and displayed
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        allPlayersList = FXCollections.observableArrayList();
        GridPane grid = new GridPane();
        grid.setVgap(20);
        grid.setHgap(20);
        grid.setAlignment(Pos.CENTER);

        Label gameTypeLabel = new Label("Game Type: ");
        singleGame = new RadioButton("Single Game");
        tournamentGame = new RadioButton("Tournament");
        Label nameLabel = new Label("Enter player name: ");
        TextField inputName = new TextField();
        playerTable = new TableView<>();
        playerTable.setItems(allPlayersList);
        playerTable.setPrefHeight(200);
        TableColumn<Player, String> playerNameCol = new TableColumn<>("Player Name");
        playerTable.getColumns().add(playerNameCol);
        playerNameCol.setCellValueFactory(new PropertyValueFactory("name"));

        addPlayerButton = new Button("Add Player");
        removePlayerButton = new Button("Remove Player");
        startGameButton = new Button("Start Game");

        turnLabel = new Label("Your Turn");
        rockButton = new RadioButton("Rock");
        paperButton = new RadioButton("Paper");
        scissorsButton = new RadioButton("Scissors");
        submitButton = new Button("Submit Choice");
        ToggleGroup userChoiceGroup = new ToggleGroup();
        rockButton.setToggleGroup(userChoiceGroup);
        paperButton.setToggleGroup(userChoiceGroup);
        scissorsButton.setToggleGroup(userChoiceGroup);

        ToggleGroup gameTypeGroup = new ToggleGroup();
        singleGame.setToggleGroup(gameTypeGroup);
        tournamentGame.setToggleGroup(gameTypeGroup);
        singleGame.fire();

        grid.addRow(0, gameTypeLabel, singleGame, tournamentGame);
        grid.addRow(1, nameLabel, inputName);
        GridPane.setColumnSpan(inputName, 2);
        grid.addRow(3, addPlayerButton);
        grid.addRow(4, playerTable);
        GridPane.setColumnSpan(playerTable, 3);
        grid.addRow(5, startGameButton, removePlayerButton);
        grid.addRow(6, turnLabel);
        GridPane.setColumnSpan(turnLabel, 3);
        grid.addRow(7, rockButton, paperButton, scissorsButton);
        grid.addRow(8, submitButton);
        GridPane.setColumnSpan(submitButton, 3);
        GridPane.setColumnSpan(startGameButton, 3);

        outputArea = new TextArea();
        outputArea.setEditable(false);

        BorderPane root = new BorderPane();
        root.setCenter(grid);
        root.setBottom(outputArea);

        Scene scene = new Scene(root, 700, 900);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Rock Paper Scissors Game");
        primaryStage.show();

        initializeGame();

        tournamentGame.setOnAction(action -> {
            setPlayerButtonsDisableStatus(false);

        });
        singleGame.setOnAction(action -> {
            setPlayerButtonsDisableStatus(true);
        });

        addPlayerButton.setOnAction(action -> {
            String name = inputName.getText().trim();
            if (!name.isEmpty()) {
                Player tempPlayer = new Player(name, true);
                allPlayersList.add(tempPlayer);

            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Enter player Name.");
                alert.setHeaderText("No Name");
                alert.setTitle("Tournament");
                alert.showAndWait();
            }
        });

        removePlayerButton.setOnAction(action -> {
            Player tempPlayer = playerTable.getSelectionModel().getSelectedItem();
            if (tempPlayer != null) {
                allPlayersList.remove(tempPlayer);

            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Select a player from table to remove.");
                alert.setHeaderText("No Player Selected to Delete");
                alert.setTitle("Delete Player");
                alert.showAndWait();
            }
        });

        startGameButton.setOnAction(action -> {
            String name = inputName.getText().trim();
            outputArea.clear();

            if (singleGame.isSelected()) {
                if (!name.isEmpty()) {
                    allPlayersList.add(new Player(name, true));
                    allPlayersList.add(new Player("Computer", false));
                    setChoiceButtonsVisibility(true);
                    setGameTypeButtonsDisableStatus(true);
                    ArrayList<Player> matchPlayers = new ArrayList<>();
                    matchPlayers.addAll(allPlayersList);
                    rpsGame = new Tournament(matchPlayers);
                    outputArea.appendText(rpsGame.getFirstRoundDetails());
                    currentMatch = rpsGame.nextMatch();
                    outputArea.appendText(currentMatch.toString());

                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING, "Enter Your Name.");
                    alert.setHeaderText("No Name");
                    alert.setTitle("Single Game (Player vs Computer)");
                    alert.showAndWait();
                }
            } else {
                int totalPlayers = allPlayersList.size();
                if (totalPlayers == 2 || totalPlayers == 4 || totalPlayers == 8) {
                    setChoiceButtonsVisibility(true);
                    setGameTypeButtonsDisableStatus(true);
                    ArrayList<Player> matchPlayers = new ArrayList<>();
                    matchPlayers.addAll(allPlayersList);
                    rpsGame = new Tournament(matchPlayers);
                    outputArea.appendText(rpsGame.getFirstRoundDetails());
                    currentMatch = rpsGame.nextMatch();
                    currentMatch.getPlayerTwo().setIsHuman(false);
                    outputArea.appendText(currentMatch.toString());
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING, "There can be only 2, 4 or 8 Players in a tournament.");
                    alert.setHeaderText("Try Adding/Remove some players");
                    alert.setTitle("Tournament");
                    alert.showAndWait();
                }
            }

        }
        );

        submitButton.setOnAction(action -> {

            if (getUserSymbol() == null) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION,
                        "Select your choice");
                alert.setHeaderText("Please select a choice");
                alert.setTitle("Rock Paper Scissor");
                alert.showAndWait();
                return;
            }

            ArrayList<Player> matchPlayers = currentMatch.getPlayers();
            for (int i = 0; i < matchPlayers.size(); i++) {
                Player tempPlayer = matchPlayers.get(i);
                // turn of human player
                if (tempPlayer.isHuman()) {
                    tempPlayer.setSign(getUserSymbol());
                } // turn of computer player
                else {
                    tempPlayer.setSign(getComputerSymbol());
                }
                // print the choice of each player
                outputArea.appendText(String.format("\"%s\" chooses %s\n", tempPlayer.toString(), tempPlayer.getSign()));
            }

            // decide winners and lossers for this turn
            match(currentMatch);
            // remove lossers from game
            for (int i = 0; i < matchPlayers.size(); i++) {
                Player tempPlayer = matchPlayers.get(i);
                if (tempPlayer.haveLost()) {
                    currentMatch.markAsComplete();
                    matchPlayers.remove(tempPlayer);
                    outputArea.appendText(String.format("\"%s\" is out of game. %<s`s choice was \"%s\"\n\n", tempPlayer.getName(), tempPlayer.getSign()));
                    i = -1;
                    tempPlayer.incrementGamesLostCount();
                }
            }
            if (!currentMatch.isOver()) {
                outputArea.appendText("Game Draw.\n\nIt`s your turn again.\n\n");
            } else {
                Player winner = matchPlayers.get(0);
                winner.incrementGamesWonCount();

                if (!rpsGame.isComplete()) {
                    rpsGame.moveWinnerToNextRound(winner);
                    currentMatch = rpsGame.nextMatch();
                    currentMatch.getPlayerTwo().setIsHuman(false);
                    outputArea.appendText(currentMatch.toString());
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION,
                            String.format("Winner : %s", winner));
                    alert.setHeaderText("Game End");
                    alert.setTitle("Rock Paper Scissor");
                    alert.showAndWait();
                    saveGameStats();
                    showGameStats();
                    initializeGame();
                }

            }

            clearUserSelection();
        });
    }

    /* initializeGame
     * private void initializeGame()
     * initializes the game, and deactivates certain buttons
     * parameters - none
     * precondition: none
     * postcondition: game is started and buttons are deactivated
     */
    private void initializeGame() {
        singleGame.setSelected(true);
        setChoiceButtonsVisibility(false);
        setGameTypeButtonsDisableStatus(false);
        setPlayerButtonsDisableStatus(true);

    }

    /* setChoiceButtonsVisibility
     * private void setChoiceButtonsVisibility(boolean status)
     * turns on or off certain buttons
     * parameters - boolean status
     * precondition: none
     * postcondition: buttons are turned on or off
     */
    private void setChoiceButtonsVisibility(boolean status) {

        turnLabel.setVisible(status);
        rockButton.setVisible(status);
        paperButton.setVisible(status);
        scissorsButton.setVisible(status);
        submitButton.setVisible(status);
    }

    /* clearUserSelection
     * private void clearUserSelection()
     * clears the user selection
     * Parameters - none
     * precondition: none
     * postcondition: user selection is cleared
     */
    private void clearUserSelection() {
        rockButton.setSelected(false);
        paperButton.setScaleShape(false);
        scissorsButton.setSelected(false);
    }

    /* setGameTypeButtonDisableStatus
     * private void setGameTypeButtonDisableStatus(boolean status)
     * turns on or off buttons based on what game type is selected
     * parameters - boolean status
     * precondition: none
     * postcondition: buttons are on or off based on game type
     */
    private void setGameTypeButtonsDisableStatus(boolean status) {
        startGameButton.setDisable(status);
        singleGame.setDisable(status);
        tournamentGame.setDisable(status);
    }

    /* setPlayerButtonsDisableStatus
     * private void setPlayerButtonsDisableStatus(boolean status)
     * turns on or off the add and remove players buttons and clears the player list
     * parameter - boolean status
     * precondition: none
     * postcondition: buttons are on or off, and player list is clear
     */
    private void setPlayerButtonsDisableStatus(boolean status) {
        allPlayersList.clear();
        addPlayerButton.setDisable(status);
        removePlayerButton.setDisable(status);
    }
    
    /* showGameStats
     * private void showGameStats()
     * show game stats
     * parameters - none
     * precondition: there are players in the list
     * postcondition - the stats are shown in a window
     */
    private void showGameStats() {
        Stage stage = new Stage();
        TableView<Player> statsTable = new TableView<>();
        statsTable.setItems(allPlayersList);
        TableColumn<Player, String> nameCol = new TableColumn<>("Player Name");
        TableColumn<Player, Integer> wonCol = new TableColumn<>("Games Won");
        TableColumn<Player, Integer> lostCol = new TableColumn<>("Games Lost");
        statsTable.getColumns().addAll(nameCol, wonCol, lostCol);
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        wonCol.setCellValueFactory(new PropertyValueFactory<>("gamesWonCount"));
        lostCol.setCellValueFactory(new PropertyValueFactory<>("gamesLostCount"));

        stage.initModality(Modality.APPLICATION_MODAL);
        Scene scene = new Scene(statsTable);
        stage.setScene(scene);
        stage.setTitle("Player`s Stats");
        stage.showAndWait();

    }

    /* saveGameStats
     * private void saveGameStats()
     * save the game stats to files
     * parameters - none
     * precondition: a game has been run, and the players list has players in it
     * postcondition: new files containing the logs and stats are created
     */
    private void saveGameStats() {
    	
    	int a = 0;
    	
        File stats = new File("stats.txt");
        File log = new File("Log.txt");
        boolean exists = stats.exists();
        while(exists)
        {
        	stats.renameTo(new File("stats" + a + ".txt"));
        	log.renameTo(new File("Log" + a + ".txt"));
        	
        	a++;
        	exists = stats.exists();
        	
        	
        }
    	
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(log));
            writer.write(outputArea.getText());
            writer.close();
            writer = new BufferedWriter(new FileWriter(stats));
            for (int i = 0; i < allPlayersList.size(); i++) {
                writer.write(allPlayersList.get(i).stats());
                writer.newLine();
            }
            writer.close();

        } catch (IOException ex) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION,
                    "Error while saving game information");
            alert.setHeaderText("IOException");
            alert.setTitle("Rock Paper Scissor");
            alert.showAndWait();
        }
    }
}

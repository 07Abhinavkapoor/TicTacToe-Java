import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ComputerPlayer extends Player{
    private final boolean smart; // To change the difficulty level when playing against a ComputerPlayer

    public ComputerPlayer(String letter){
        this(letter, false);
    }

    public ComputerPlayer(String letter, boolean smart){
        super(letter);
        this.smart = smart;
    }

    @Override
    public int getMove(TicTacToe game){
        if(!smart){
            Random random = new Random();
            return game.availableOptions().get(random.nextInt(game.numberOfEmptySquares()));
//            a number will be selected at random from 0 to numberOfEmptySquares and then item corresponding to that index from the list will be selected and returned.
        }

        return miniMax(game, getLetter()).get("position");
    }

    private Map<String, Integer> miniMax(TicTacToe gameState, String player){
//      This method is created to find the square/move which will maximize the chances of winning for the maxPlayer.
        String maxPlayer = getLetter();
        String otherPlayer = (player.equals("X")) ? "O" : "X";

//        Exit condition - Start
        if(gameState.getTheWinner() != null){
                int sign = (gameState.getTheWinner().equals(maxPlayer)) ? 1 : -1;
                int val = 1 + gameState.numberOfEmptySquares();

                Map<String, Integer> result = new HashMap<>();
                result.put("position", null);
                result.put("score", val*sign);
                return result;
        }
        else if(!gameState.hasEmptySquares()){
            Map<String, Integer> result = new HashMap<>();
            result.put("position", null);
            result.put("score", 0);
            return result;
        }
//        Exit condition - End

        Map<String, Integer> best = new HashMap<>();
        best.put("position", null);

        if(player.equals(maxPlayer)){
//            Since we are to maximize the score for the maxPlayer hence we are setting the value for it's score to be minimum.
            best.put("score", Integer.MIN_VALUE);
        }
        else{
//            Since we are to minimize the score for the non-maxPlayer hence we are setting the value for it's score to be maximum.
            best.put("score", Integer.MAX_VALUE);
        }

//        Now, the next step is to try every possible move and select the one which makes us win the game as soon as possible.
        for(int move : gameState.availableOptions()){
//            First we make the move.
            gameState.makeMove(move, player);

//            Then we try to play the game until the end, after that move and get the result.
            Map<String, Integer> simulation = miniMax(gameState, otherPlayer);

//            Next, we undo the move and store the position which let us to that score.
            gameState.undoMove(move);
            gameState.setNoWinner();
            simulation.replace("position", move);

//            Finally, we check if for maxPlayer the score is greater than the current score and for min player if the score is less than the current score. if yes we update the best result.
            if(player.equals(maxPlayer)){
//                Maximizing the score for maxPlayer
                if(simulation.get("score") > best.get("score"))
                    best = simulation;
            }
            else{
//                Minimizing the score for non-maxPlayer
                if (simulation.get("score") < best.get("score"))
                    best = simulation;
            }
        }

//        After checking each move, at last we return the stats of the best move.
        return best;
    }
}

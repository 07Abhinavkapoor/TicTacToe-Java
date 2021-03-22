public class Play {
    private static final String TEXT_RESET = "\u001B[0m";
    private static final String TEXT_GREEN = "\u001B[1:32m";

    public static void playGame(TicTacToe game, Player x_player, Player o_player){
        String letter = "X";
        while(game.hasEmptySquares()){
            game.printBoard();
            int move;

            if(letter.equals("X"))
                move = x_player.getMove(game);
            else
                move = o_player.getMove(game);

            game.makeMove(move, letter);
            System.out.printf("%s made a move at square %d\n", letter, move);

            if (game.getTheWinner() != null){
                String response = String.format("%s won the game with %d squares left\n", game.getTheWinner(), game.numberOfEmptySquares());
                System.out.println(TEXT_GREEN + response + TEXT_RESET);
                System.out.println("Final State");
                game.printBoard();
                return;
            }

            letter = changeThePlayer(letter);
        }

        System.out.println(TEXT_GREEN + "It's a Tie." + TEXT_RESET);
        System.out.println("Final State");
        game.printBoard();
    }

    private static String changeThePlayer(String letter){
        return letter.equals("X") ? "O" : "X";
    }
}

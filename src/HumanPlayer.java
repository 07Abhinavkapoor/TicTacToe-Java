import java.util.Scanner;

public class HumanPlayer extends Player{
    public HumanPlayer(String letter){
        super(letter);
    }

    @Override
    public int getMove(TicTacToe game){
        boolean validMove = false;
        int move = 0;
        Scanner sc = new Scanner(System.in);

        if(game.numberOfEmptySquares() == 1){
            System.out.println("Only 1 square was left so, automatically made the move");
            return game.availableOptions().get(0);
        }

        while(!validMove){
            System.out.printf("%s's turn. Enter your choice: \n", getLetter());
            move = sc.nextInt();

            if(game.availableOptions().contains(move))
                validMove = true;
            else
                System.out.println("Invalid Move.");
        }

        return move;
    }
}

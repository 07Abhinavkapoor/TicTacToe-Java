import java.util.ArrayList;
import java.util.List;

public class TicTacToe {
    private static final String TEXT_RESET = "\u001B[0m";
    private static final String TEXT_RED = "\u001B[31m";
    private static final String TEXT_BLUE = "\u001B[34m";

    private String[] board = new String[9];
    private String theWinner = null;

    public void printBoard(){
        for(int i=0; i<3; i++){
            String[] rowVals = new String[3];
            int count = 0;
            for(int j=i*3; j<(i+1)*3; j++){
                rowVals[count++] = (board[j] == null) ? String.valueOf(j) : (board[j].equals("X") ? TEXT_BLUE + "X" + TEXT_RESET : TEXT_RED + "O" + TEXT_RESET);
            }

            System.out.println("| " + String.join(" | ", rowVals) + " |");
        }
    }

    public List<Integer> availableOptions(){
        List<Integer> options = new ArrayList<>();
        for(int i=0; i<9; i++){
            if(board[i] == null)
                options.add(i);
        }

        return options;
    }

    public int numberOfEmptySquares(){
        return availableOptions().size();
    }

    public boolean hasEmptySquares(){
        return (numberOfEmptySquares() != 0);
    }

    public void makeMove(int square, String letter){
//        To make the move of the letter at the specified square.
        if(board[square] == null){
            board[square] = letter;
            if(isWinner(square, letter))
                theWinner = letter;
        }
    }

    private boolean isWinner(int square, String letter){
//        First we will check if all the squares in the row are filled with same letter or not.
        if(allSameInRow(square, letter))
            return true;
//        Next, we will check for column
        else if(allSameInCol(square, letter))
            return true;
//        Next, we will check for diagonals
        else return allSameInDiagonal(square, letter);
    }

    private boolean allSameInRow(int square, String letter){
        int row = Math.floorDiv(square, 3);
        int[] rowIndexes = new int[3];

        for(int i=0; i<3; i++){
            rowIndexes[i] = (row*3) + i;
        }

        return checkSameForIndexes(rowIndexes, letter);
    }

    private boolean allSameInCol(int square, String letter){
        int col = square % 3;
        int[] colIndexes = new int[3];
        for(int i=0; i<3; i++){
            colIndexes[i] = (i*3) + col;
        }

        return checkSameForIndexes(colIndexes, letter);
    }

    private boolean allSameInDiagonal(int square, String letter){
        if(square % 2 == 0)
            return allSameInLeftDiagonal(letter) || allSameInRightDiagonal(letter);

        return false;
    }

    private boolean allSameInLeftDiagonal(String letter){
        int[] diagonalIndexes = {0, 4, 8};
        return checkSameForIndexes(diagonalIndexes, letter);
    }

    private boolean allSameInRightDiagonal(String letter){
        int[] diagonalIndexes ={2, 4, 6};
        return checkSameForIndexes(diagonalIndexes, letter);
    }

    private boolean checkSameForIndexes(int[] indexes, String letter){
        for(int i : indexes){
            if(board[i] != letter)
                return false;
        }

        return true;
    }

    public void undoMove(int square){
        board[square] = null;
    }

    public void setNoWinner(){
        theWinner = null;
    }

    public String getTheWinner(){
        return theWinner;
    }
}

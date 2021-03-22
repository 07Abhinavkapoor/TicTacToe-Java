import java.util.Scanner;

public class Main {
    private static final String TEXT_RESET = "\u001B[0m";
    private static final String TEXT_GREEN = "\u001B[1:32m";
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        start();
    }

    public static void start(){
        int choice = menu();

        TicTacToe ticTacToe = new TicTacToe();
        Player x_player = new HumanPlayer("X");
        Player o_player = null;

        switch(choice){
//            To select the second player, based on the user's choice.
            case 1:
//                Human vs Human
                o_player = new HumanPlayer("O");
                break;
            case 2:
//                Human vs Computer(Easy)
                o_player = new ComputerPlayer("O");
                break;
            case 3:
//                Human vs Computer(Hard)
                o_player = new ComputerPlayer("O", true);
        }

        Play.playGame(ticTacToe, x_player, o_player);
    }

    public static int menu(){
        System.out.println(TEXT_GREEN + "\t\t\tTIC-TAC-TOE" + TEXT_RESET);
        System.out.println("\t\tMENU");
        System.out.println("\t1. Human Player vs Human Player");
        System.out.println("\t2. Human Player vs Computer Player");
        System.out.println("\t3. Quit");

        int choice = 0;
        boolean validChoice = false;

        while(!validChoice){
            try{
                System.out.print("\nEnter your choice:\n> ");
                choice = sc.nextInt();

                if(choice == 3)
                    System.exit(0);

                if(choice <= 0 || choice > 3)
                    throw new IllegalArgumentException();

                validChoice = true;
            }
            catch(IllegalArgumentException e){
                System.out.println("Invalid Input. Try Again");
            }
        }

        if(choice == 2)
//            If human vs computer is selected, will ask for difficulty level.
            return difficultyLevel();

        return choice;
//        will return 1 if human vs human is selected.
    }

    private static int difficultyLevel(){
        System.out.println("\t\tChoose the difficulty Level");
        System.out.println("\t1. Easy");
        System.out.println("\t2. Hard");

        int choice = 0;
        boolean validChoice = false;

        while(!validChoice){
            try{
                System.out.print("\nEnter your choice:\n> ");
                choice = sc.nextInt();

                if(choice <= 0 || choice >2)
                    throw new IllegalArgumentException();

                validChoice = true;
            }
            catch(IllegalArgumentException e){
                System.out.println("Invalid Input. Try Again.");
            }
        }

        return choice + 1;
//        will return 2 for easy, and 3 for hard.
    }
}

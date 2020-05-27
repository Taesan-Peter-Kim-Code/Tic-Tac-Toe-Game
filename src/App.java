import java.util.Random;
import java.util.Scanner;

public class App {
    
    static int playerScore = 0;
    static int computerScore = 0;
    static Scanner in = new Scanner(System.in);

    public static void main(final String[] args) throws Exception {
        final char [][] tictactoeChart = {{'_','|','_','|','_'},{'_','|','_','|','_'},{' ','|',' ','|',' '}};
        printChart(tictactoeChart);
        boolean gameOver = false;
        boolean playAgain = true;

        // play game
        while(playAgain) {
            while(!gameOver) {
                playerMove(tictactoeChart);
                gameOver = isGameOver(tictactoeChart);
                if (gameOver) {
                    break;
                }
                computerMove(tictactoeChart);
                gameOver = isGameOver(tictactoeChart);
                if (gameOver) {
                    break;
                }
            }
            System.out.println("Player Score: " + playerScore);
            System.out.println("Computer Score: " + computerScore);
            System.out.println("Would you like to play again? Y/N");
            in.nextLine();
            String result = in.nextLine();

            if (result.equalsIgnoreCase("Y")) {
                playAgain = true;
                System.out.println("Get ready! Let's play again!");
                resetBoard(tictactoeChart);
                gameOver = false;
                printChart(tictactoeChart);
            }
            if (result.equalsIgnoreCase("N")) {
                playAgain = false;
                System.out.println("Thank you for playing! :)");

            }
        }
    }
    /**
     * print out the game chart like
     * 
     *              _|_|_
     *              _|_|_
     *               | | 
     * 
     * @param tictactoeChart
     */
    public static void printChart(char [][] tictactoeChart) {
        // print out the game chart by using two for loops 
        for (final char[] row : tictactoeChart) {
            for (final char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }

        
    }


    /**
     * update the game chart according to the player's input
     * @param position
     * @param player
     * @param tictactoeChart
     */
    public static void updateChart(int position, int player, char [][] tictactoeChart) {
        
        char character;

        // set two players
        if (player == 1) {
            character = 'X';
        } else {
            character = 'O';
        }

        // multiple cases of updating the chart according to the player's input
        switch (position) {
            case  1: 
                tictactoeChart[0][0] = character;
                printChart(tictactoeChart);
                break;
            
            case  2: 
                tictactoeChart[0][2] = character;
                printChart(tictactoeChart);
                break;

            case  3: 
                tictactoeChart[0][4] = character;
                printChart(tictactoeChart);
                break;

            case  4: 
                tictactoeChart[1][0] = character;
                printChart(tictactoeChart);
                break;

            case  5: 
                tictactoeChart[1][2] = character;
                printChart(tictactoeChart);
                break;

            case  6: 
                tictactoeChart[1][4] = character;
                printChart(tictactoeChart);
                break;

            case  7: 
                tictactoeChart[2][0] = character;
                printChart(tictactoeChart);
                break;

            case  8: 
                tictactoeChart[2][2] = character;
                printChart(tictactoeChart);
                break;
            
            case  9: 
                tictactoeChart[2][4] = character;
                printChart(tictactoeChart);
                break;
            
            default:
                break;
        }

    }

    /**
     * method to get player's input and update the movement
     * @param tictactoeChart
     */
    public static void playerMove(char[][] tictactoeChart) {
        System.out.println("Please write a number between 1 to 9 to make a move");

        int move = in.nextInt();

        // check if the input is valid or not
        boolean result = isValidInput(move, tictactoeChart);
        while(!result) {
            System.out.println("Sorry! Invalid Move. Try again");
            move = in.nextInt();
            result = isValidInput(move, tictactoeChart);
        }

        System.out.println("Player moved at position " + move);

        updateChart(move, 1, tictactoeChart);
    }

    /**
     *  method to check if the player's input valid or invalid
     * @param move
     * @param tictactoeChart
     * @return
     */
    public static boolean isValidInput(int move, char[][] tictactoeChart) {

        switch (move) {
            case 1:
                if (tictactoeChart[0][0] == '_') {
                    return true;
                } else {
                    return false;
                }
            case 2:
                if (tictactoeChart[0][2] == '_') {
                    return true;
                } else {
                    return false;
                }
            case 3:
                if (tictactoeChart[0][4] == '_') {
                    return true;
                } else {
                    return false;
                }
            case 4:
                if (tictactoeChart[1][0] == '_') {
                    return true;
                } else {
                    return false;
                }
            case 5:
                if (tictactoeChart[1][2] == '_') {
                    return true;
                } else {
                    return false;
                }
            case 6:
                if (tictactoeChart[1][4] == '_') {
                    return true;
                } else {
                    return false;
                }
            case 7:
                if (tictactoeChart[2][0] == ' ') {
                    return true;
                } else {
                    return false;
                }
            case 8:
                if (tictactoeChart[2][2] == ' ') {
                    return true;
                } else {
                    return false;
                }
            case 9:
                if (tictactoeChart[2][4] == ' ') {
                    return true;
                } else {
                    return false;
                }

            default:
                return false;
        }

    }

    /**
     * get a movement from computer
     * @param tictactoeChart
     */
    public static void computerMove(char[][] tictactoeChart) {

        Random rand = new Random();
        int move = rand.nextInt(9) + 1;

        boolean result = isValidInput(move, tictactoeChart);

        // check if the computer's input is valid or not
        while(!result) {
            move = rand.nextInt(9) + 1;
            result = isValidInput(move, tictactoeChart);
        }

        System.out.println("Computer moved at position " + move);
        updateChart(move, 2, tictactoeChart);
    }

    /**
     * check the game is over or not. And print out the winner and score
     * @param tictactoeChart
     * @return
     */
    public static boolean isGameOver(char [][] tictactoeChart) {

        //horizontal player wins
        if(tictactoeChart[0][0] == 'X' && tictactoeChart[0][2] == 'X' && tictactoeChart[0][4] =='X') {
            playerScore++;
            System.out.println("Player Wins | Score: " + playerScore);
            return true;
        }
        if(tictactoeChart[1][0] == 'X' && tictactoeChart[1][2] == 'X' && tictactoeChart[1][4] =='X') {
            playerScore++;
            System.out.println("Player Wins | Score: " + playerScore);
            return true;
        }
        if(tictactoeChart[2][0] == 'X' && tictactoeChart[2][2] == 'X' && tictactoeChart[2][4] =='X') {
            playerScore++;
            System.out.println("Player Wins | Score: " + playerScore);
            return true;
        }

        //horizontal computer wins
        if(tictactoeChart[0][0] == 'O' && tictactoeChart[0][2] == 'O' && tictactoeChart[0][4] =='O') {
            computerScore++;
            System.out.println("Computer Wins | Score: " + computerScore);
            return true;
        }
        if(tictactoeChart[1][0] == 'O' && tictactoeChart[1][2] == 'O' && tictactoeChart[1][4] =='O') {
            computerScore++;
            System.out.println("Computer Wins | Score: " + computerScore);
            return true;
        }
        if(tictactoeChart[0][4] == 'O' && tictactoeChart[1][4] == 'O' && tictactoeChart[2][4] =='O') {
            computerScore++;
            System.out.println("Computer Wins | Score: " + computerScore);
            return true;
        }

        //vertical player wins
        if(tictactoeChart[0][0] == 'X' && tictactoeChart[1][0] == 'X' && tictactoeChart[2][0] =='X') {
            playerScore++;
            System.out.println("Player Wins | Score: " + playerScore);
            return true;
        }
        if(tictactoeChart[0][2] == 'X' && tictactoeChart[1][2] == 'X' && tictactoeChart[2][2] =='X') {
            playerScore++;
            System.out.println("Player Wins | Score: " + playerScore);
            return true;
        }
        if(tictactoeChart[0][4] == 'X' && tictactoeChart[1][4] == 'X' && tictactoeChart[2][4] =='X') {
            playerScore++;
            System.out.println("Player Wins | Score: " + playerScore);
            return true;
        }
        
        //vertical computer wins
        if(tictactoeChart[0][0] == 'O' && tictactoeChart[1][0] == 'O' && tictactoeChart[2][0] =='O') {
            computerScore++;
            System.out.println("Computer Wins | Score: " + computerScore);
            return true;
        }
        if(tictactoeChart[0][2] == 'O' && tictactoeChart[1][2] == 'O' && tictactoeChart[2][2] =='O') {
            computerScore++;
            System.out.println("Computer Wins | Score: " + computerScore);
            return true;
        }
        if(tictactoeChart[0][4] == 'O' && tictactoeChart[1][4] == 'O' && tictactoeChart[2][4] =='O') {
            computerScore++;
            System.out.println("Computer Wins | Score: " + computerScore);
            return true;
        }

        //diagonal player wins
        if(tictactoeChart[0][0] == 'X' && tictactoeChart[1][2] == 'X' && tictactoeChart[2][4] =='X') {
            playerScore++;
            System.out.println("Player Wins | Score: " + playerScore);
            return true;
        }
        if(tictactoeChart[2][4] == 'X' && tictactoeChart[1][2] == 'X' && tictactoeChart[0][4] =='X') {
            playerScore++;
            System.out.println("Player Wins | Score: " + playerScore);
            return true;
        }

        //diagonal computer wins
        if(tictactoeChart[0][0] == 'O' && tictactoeChart[1][2] == 'O' && tictactoeChart[2][4] =='O') {
            computerScore++;
            System.out.println("Computer Wins | Score: " + computerScore);
            return true;
        }
        if(tictactoeChart[2][4] == 'O' && tictactoeChart[1][2] == 'O' && tictactoeChart[0][4] =='O') {
            computerScore++;
            System.out.println("Computer Wins | Score: " + computerScore);
            return true;
        }

        //if the game is tie, print out "Its a tie"
        if (tictactoeChart[0][0] != '_' && tictactoeChart[0][2] != '_' && tictactoeChart[0][4] != '_' && 
            tictactoeChart[1][0] != '_' && tictactoeChart[1][2] != '_' && tictactoeChart[1][4] != '_' && 
            tictactoeChart[2][0] != ' ' && tictactoeChart[2][2] != ' ' && tictactoeChart[2][4] != ' ') {

                System.out.println("Its a tie");
                return true;
            }   
        return false;
    }

    /**
     * reset the whole game
     * @param tictactoeChart
     */
    public static void resetBoard(char[][] tictactoeChart) {
        tictactoeChart[0][0] = '_';
        tictactoeChart[0][2] = '_';
        tictactoeChart[0][4] = '_';
        tictactoeChart[1][0] = '_';
        tictactoeChart[1][2] = '_';
        tictactoeChart[1][4] = '_';
        tictactoeChart[2][0] = ' ';
        tictactoeChart[2][2] = ' ';
        tictactoeChart[2][4] = ' ';
    }
}

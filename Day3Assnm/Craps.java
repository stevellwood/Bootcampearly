
package gov.ssa.assignment3;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Craps {
	// Number of times to play
	public static final int NUM_TIMES_TO_PLAY = 500;
	
	// Menu
	public static final String MENU = "Welcome to the Craps program! Please select an option:\n" +
	                                  "\t1) Simulator\n" +
	                                  "\t2) Interactive\n" +
	                                  "\t3) Quit\n\n" +
	                                  "Select the number (1, 2, or 3) of the option you wish and press enter: ";
	
	public static final String MENU_ERROR = "You have entered an invalid choice. Valid options are: 1 2 3\n";
	
	public static final String MENU_EXIT = "Thanks for using the program. See you later!";
	
	// Menu options
	public static final String OPTION_SIMULATOR = "1";
	public static final String OPTION_INTERACTIVE = "2";
	public static final String OPTION_QUIT = "3";
	
	// Interactive messages
	public static final String ROLL_DICE_MESSAGE = "Press any key and then enter to roll the dice!";
	public static final String ROLL_AGAIN_MESSAGE = "Would you like to play again? Enter y or n: ";
	public static final String ROLL_AGAIN_ERROR_MESSAGE = "You have entered an invalid option. Enter either y or n.\n";	
	
	// Important roll sums
	public static final int ROLL_TWO = 2;
	public static final int ROLL_THREE = 3;
	public static final int ROLL_SEVEN = 7;
	public static final int ROLL_TWELVE = 12;
	
	// Game result messages
	public static final String WIN_FIRST_ROLL = "You got a 7 on the first roll! Winner, winner, chicken dinner!";
	public static final String LOSE_FIRST_ROLL = "You got either a 2, 3, or 12 on the first roll! You crapped out!";
	public static final String SHOW_POINT = "Your point for this game is ";
	public static final String ROLL_POINT = "You rolled the point! You win!";
	public static final String ROLL_SEVEN_LOSE = "You rolled a 7 before you rolled the point! You crapped out!";
	
	public static void main(String[] args) throws Exception {
		// Set up for reading keyboard input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String option = "";
		
		// Controls whether to keep choosing an option
		boolean keepChoosing = true;
		
		// Either play the maximum number of games or until we run out of money
		do {
			System.out.print(MENU);
			option = br.readLine();
			System.out.println();
			
			switch(option) {
				case OPTION_SIMULATOR:
					// go to simulator
					doSimulation();
					break;
					
				case OPTION_INTERACTIVE:
					// go to interactive
					doInteractive();
					break;
				
				case OPTION_QUIT:
					keepChoosing = false;
					System.out.print(MENU_EXIT);
					break;
					
				default:
					System.out.println(MENU_ERROR);
			}
			
		} while(keepChoosing);
	}
	
	// This mode runs the game for a set number of rounds or until the user is broke
	public static void doSimulation() throws Exception {
		Player player = new Player();
		
		for(int ctr = 1; ctr <= NUM_TIMES_TO_PLAY; ctr++) {
		
			if(player.bet()) {
				System.out.println("Game #" + ctr + ":");
				System.out.println("===========================");
				
				// No interaction necessary for simulation mode
				boolean result = playGame(false);
				
				// Take the appropriate action depending on whether the player won or lost
				if(result) {
					player.winGame();
				} else {
					player.loseGame();
				}
				
				System.out.println();
			} 
			// No reason to keep playing if the player is out of funds
			else {
				break;
			}			
		}
		
		// Print the statistics for this session
		printStatistics(player);
	}
	
	// This mode plays one game at a time through user interaction. After each game, the user is
	// asked whether he/she wants to continue.
	public static void doInteractive() throws Exception {
		Player player = new Player();
		boolean eligibleToPlay = true;
		boolean keepPlaying = true;
		
		// Set up for reading keyboard input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String option = "";
		
		do {
			System.out.println("You currently have $" + player.getAmount());
			
			if(player.bet()) {
				System.out.println("You bet $" + Player.BET_AMT + " and now have $" + player.getAmount() + "\n");
				
				boolean result = playGame(true);
				
				// Take appropriate action depending on the result of the game
				if(result) {
					player.winGame();
					System.out.println("You won $" + Player.WIN_AMT + " and now have $" + player.getAmount() + "\n");
				} else {
					player.loseGame();
					System.out.println();
				}
				
			} else {
				eligibleToPlay = false;				
			}
			
			boolean validOption = false;
			
			// Find out whether the player wants to play another game
			do {
				System.out.print(ROLL_AGAIN_MESSAGE);
				option = br.readLine();
				
				if((option.equalsIgnoreCase("y") || option.equalsIgnoreCase("n"))) {
					validOption = true;
				} else {
					System.out.println(ROLL_AGAIN_ERROR_MESSAGE);
				}
			} while(!validOption);
			
			if(option.equalsIgnoreCase("n")) {
				keepPlaying = false;
			}
			
		} while (eligibleToPlay && keepPlaying);
		
		// Print the statistics for this session
		printStatistics(player);
	}
	
	// Play one craps game.
	// Returns true if the game is won and false otherwise
	public static boolean playGame(boolean isInteractive) throws Exception {
		// Set up dice
		Die die1 = new Die();
		Die die2 = new Die();
		
		// Set up for reading keyboard input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				
		// Keeps track of the number of rolls in the current game
		int rollNumber = 1;
		
		// Keeps track of whether the game is won or lost
		boolean isAWinner = true;
		
		// Do first roll
		
		// If interactive, wait for key press - don't care what key it is
		if(isInteractive) {
			System.out.print(ROLL_DICE_MESSAGE);
			br.readLine();
			System.out.println();
		}
		
		int totalDieRoll = rollDice(die1, die2, rollNumber);
		
		// Win if first roll a 7
		if(totalDieRoll == ROLL_SEVEN) {
			System.out.println(WIN_FIRST_ROLL);
			isAWinner = true;
		}
		// Lose if first roll is 2, 3, or 12
		else if((totalDieRoll == ROLL_TWO) || (totalDieRoll == ROLL_THREE) || (totalDieRoll == ROLL_TWELVE)) {
			System.out.println(LOSE_FIRST_ROLL);
			isAWinner = false;
		}		
		// Else roll sum becomes the point and we keep playing until we either reach the point or a 7
		else {
			// Store the point for this game
			int point = totalDieRoll;
			
			System.out.println(SHOW_POINT + point);
						
			do {
				rollNumber++;
				
				// If interactive, wait for key press - don't care what key it is
				if(isInteractive) {
					System.out.print(ROLL_DICE_MESSAGE);
					br.readLine();
					System.out.println();
				}
				
				totalDieRoll = rollDice(die1, die2, rollNumber);
				
				// Display an appropriate status message if necessary
				if(totalDieRoll == point) {
					System.out.println(ROLL_POINT);
					isAWinner = true;
				} else if(totalDieRoll == ROLL_SEVEN) {
					System.out.println(ROLL_SEVEN_LOSE);
					isAWinner = false;
				}
				
			} while( (totalDieRoll != ROLL_SEVEN) && (totalDieRoll != point) );
		}
		
		return isAWinner;
	}
	
	// Rolls the dice, prints out the result, and returns the sum
	// Parameters: die1 => first die; die2 => second die; rollNumber => the current roll number
	// Return: the result of the roll
	public static int rollDice(Die die1, Die die2, int rollNumber) {
		int die1Roll = die1.rollDie();
		int die2Roll = die2.rollDie();
		int totalDieRoll = die1Roll + die2Roll;
	
		System.out.println("Result of roll " + rollNumber + ": [" + die1Roll + "][" + die2Roll + "] = " + totalDieRoll);
		
		return totalDieRoll;
	}
	
	// Prints the statistics
	public static void printStatistics(Player player) {
		System.out.println("Statistics for this session:");
		System.out.println("\tAmount: $" + player.getAmount());
		System.out.println("\tWins: " + player.getNumberWins());
		System.out.println("\tLosses: " + player.getNumberLosses());
		System.out.println("\tTotal games played: " + (player.getNumberWins() + player.getNumberLosses()));
		System.out.println();
	}
}

// A class to represent a die
class Die {
	// Playing with a 6-sided die - this could be changed to represent other sided dies
	public static final int MAX_DIE_VALUE = 6;
		
	public int rollDie() {
		// Translate the roll of 0-MAX_DIE_VALUE-1 into the range 1-MAX_DIE_VALUE
		return (int)(Math.random()*MAX_DIE_VALUE) + 1;
	}	
}

// A class to hold the results of the set of games
class Player {
	// Start with $100
	public static final int START_AMT = 100;
	
	// Bankrupt message
	public static final String NO_MORE_MONEY = "You can't bet - you're out of funds!\n";
		
	// Bet for each game is BET_AMT. If we win, we get WIN_AMT; otherwise, we get nothing.
	public static final int WIN_AMT = 2;
	public static final int BET_AMT = 1;
	
	private int amount = START_AMT;
	private int numberWins = 0;
	private int numberLosses = 0;
	
	public int getAmount() {
		return amount;
	}
	
	public int getNumberWins() {
		return numberWins;
	}
	
	public int getNumberLosses() {
		return numberLosses;
	}
	
	// The bet is deducted from the player's amount before playing
	// Returns: true if the player still has funds and false if the player is broke
	public boolean bet() {
		if(amount > 0) {
			amount -= BET_AMT;
			return true;
		} else {
			System.out.println(NO_MORE_MONEY);
			return false;
		}
	}
	
	// A win increases the player's amount and the number of wins
	public void winGame() {
		amount += WIN_AMT;
		numberWins++;
	}
	
	// A loss increases the number of losses
	public void loseGame() {
		numberLosses++;
	}
}
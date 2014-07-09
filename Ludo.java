import java.util.*;

public class Ludo {
	Board board;
	Player[] players;

	public Ludo(int numOfPlayers) {
		board = new Board();
		players = new Player[numOfPlayers]();
		
		player[0] = new Player(0, true)
		for (int i = 1; i < numOfPlayers; i++) {
			players[i] = new Player(i, false);
		}
	}

	public void checkHit(int position) {
		int playerAtPos = getterCommonBoard(position);
		if (playerAtPos != -1) {
			players[playerAtPos].resetTokenAtPos(position);
		}
	}

	public static void main(String[] arg) {
		int turn = 0;

		String choice;
		System.out.println("Enter the number of Players");
		Scanner scan = new Scanner(System.in);
		int numOfPlayers = scan.nextInt();
		Ludo ludo = new Ludo(numOfPlayers);
		Player players[] = ludo.getBoard().getPlayers();
		int unfinishedPlayers = ludo.getBoard().playersUnfinished().length;
		while (players[GAME_CONFIG.HUMAN_ID].isUnfinished()
				&& unfinishedPlayers > 1) {
			turn = turn % numOfPlayers;

			if (players[turn].isUnfinished()) {
				System.out.println("Player " + (turn + 1) + " : ");
				int diceNumber = ludo.rollDice();
				if (players[turn].canMove(diceNumber)) {
					Token tokens[] = players[turn].getTokens();

					if (players[turn].getId() == GAME_CONFIG.HUMAN_ID) {
						ludo.getBoard().display();
						System.out.println(" Dice Value: " + diceNumber);
						if (diceNumber == 6) {
							System.out
									.println(" Want to unlock a token? (y/n):  ");
							choice = scan.next();
							if (choice.equals("y")) {
								ludo.unlockAToken(players[turn]);
							} else {
								if (!(ludo.makeAValidMoveForHuman(tokens,
										diceNumber))) {
									System.out
											.println(" No Move Possible : Next Player's chance! ");
								}
							}
						} else {
							if (!(ludo.makeAValidMoveForHuman(tokens,
									diceNumber))) {
								System.out
										.println(" No Move Possible : Next Player's chance! ");
							}
						}
					} else {
						System.out.println(" Computer Dice Value: "
								+ diceNumber);

						if (diceNumber == 6) {
							ludo.unlockAToken(players[turn]);
						}

						else {
							if (!(ludo
									.makeAValidMoveForComp(tokens, diceNumber))) {
								System.out
										.println(" No Move Possible : Next Player's chance! ");
							} else {
								ludo.getBoard().display();
							}

						}
					}

				} else {
					System.out.println(" Dice Value: " + diceNumber);
					System.out.println("No Move Possible");
				}
			}

			players[turn].updateIsUnfinished();
			turn++;

			unfinishedPlayers = ludo.getBoard().playersUnfinished().length;
		}
		if (players[GAME_CONFIG.HUMAN_ID].isUnfinished()) {
			System.out.println("You Lost !!");
		} else {
			System.out.println("You Win !!");
		}
	}
}

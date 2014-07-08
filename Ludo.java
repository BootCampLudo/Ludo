import java.util.*;

public class Ludo {
	Board board;

	public Ludo(int numberOfPlayes) {
		board = new Board(numberOfPlayes);
	}

	public Board getBoard() {
		return board;
	}

	public int rollDice() {
		Random rand = new Random();
		return rand.nextInt(6) + 1;
	}

	public void unlockAToken(Player player) {// remove IO
		int tokenId = player.findLockedToken();
		if (tokenId == 0) {
			System.out.println("All Tokens are unlocked"); 
		} else {
			player.unlockToken(tokenId);
			player.moveTokenByStep(tokenId, 6);
		}

	}

	public void checkHit(Token[] token, int tokenId) {
		Player players[] = board.getPlayers();
		for (int i = 0; i < players.length; i++) {
			if (token[tokenId].getPlayerId() == players[i].getId())
				break;

			Token tokens[] = players[i].getTokens();
			for (int j = 0; j < 4; j++) {
				if ((tokens[j].getCoordinate() % 60) == (token[tokenId].getCoordinate() % 60)
						&& tokens[j].getState() != GAME_CONFIG.SAFE) {
					System.out.println("Token id " + tokens[j].getId() + " of player " + tokens[i].getPlayerId() + " HIT");
					tokens[j].reset();
				}
			}
		}
	}

	public boolean makeAValidMoveForHuman(Token[] token, int diceNumber) {//remove io
		int tokenId;
		Random rand = new Random();
		Scanner scan = new Scanner(System.in);

		System.out.println("Enter the tokenId to be moved: (1/2/3/4)");
		tokenId = scan.nextInt() - 1;

		int[] visited = new int[4];
		for (int i = 0; i < 4; i++) {
			visited[i] = 0;
		}
		
		int count = 4;
		while (count > 0) {
			if (visited[tokenId] != 1) {
				if (token[tokenId].canMove(diceNumber)) {
					token[tokenId].moveToken(diceNumber);
					checkHit(token, tokenId);
					return true;
				} else {
					visited[tokenId] = 1;

					tokenId = rand.nextInt(4);

				}
			} else {
				count++;
				System.out.println(" Invalid Move! Try Again ");
				System.out.println("Enter the tokenId to be moved: (1/2/3/4)          ");
				tokenId = scan.nextInt() - 1;

			}
			count--;
		}
		return false;
	}

	public boolean makeAValidMoveForComp(Token[] token, int diceNumber) {
		int tokenId;
		Random rand = new Random();

		tokenId = rand.nextInt(4);

		int[] visited = new int[4];
		for (int i = 0; i < 4; i++) {
			visited[i] = 0;
		}

		int count = 4;
		while (count > 0) {
			if (visited[tokenId] != 1) {
				if (token[tokenId].canMove(diceNumber)) {
					token[tokenId].moveToken(diceNumber);
					checkHit(token, tokenId);
					return true;
				} else {
					visited[tokenId] = 1;
					tokenId = rand.nextInt(4);
				}
			}

			else {
				count++;

				tokenId = rand.nextInt(4);

			}
			count--;
		}
		return false;
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
									System.out.println(" No Move Possible : Next Player's chance! ");
								}
							}
						} else {
							if (!(ludo.makeAValidMoveForHuman(tokens,
									diceNumber))) {
								System.out.println(" No Move Possible : Next Player's chance! ");
							}
						}
					} else {
						System.out.println(" Computer Dice Value: " + diceNumber);

						if (diceNumber == 6) {
							ludo.unlockAToken(players[turn]);
						}

						else {
							if (!(ludo
									.makeAValidMoveForComp(tokens, diceNumber))) {
								System.out.println(" No Move Possible : Next Player's chance! ");
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

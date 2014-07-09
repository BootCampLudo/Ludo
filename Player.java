import java.util.Random;
import java.util.Scanner;

public class Player {

	private int id;
	private boolean unfinished;
	private Token[] tokens;
	private boolean isHuman;

	public Player(int id, boolean isHuman) {
		this.id = id;
		this.tokens = new Token[4];
		for (int i = 0; i < 4; i++) {

			tokens[i] = new Token(id);

		}

		this.unfinished = true;
		this.isHuman = isHuman;
	}

	public Token[] getTokens() {
		return tokens;
	}

	public boolean isUnfinished() {
		return unfinished;
	}

	public int getId() {
		return id;
	}

	public void resetTokenAtPos(int pos) {
		int[] allPos = getAllTokenPos();
		for (int i = 0; i < 4; i++) {
			if (allPos[i] == pos) {
				tokens[i].reset();
				break;
			}
		}
	}

	public void updateIsUnfinished() {
		int countTokensFinished = 0;
		for (int i = 0; i < 4; i++) {
			if (tokens[i].isComplete()) {
				countTokensFinished++;
			} else {
				break;
			}

		}
		if (countTokensFinished != 4) {
			unfinished = false;
		}
	}

	public void unlockToken(int id) {
		Token t = tokens[id];
		if (t != null) {
			t.Free();
		}
	}

	public void moveTokenByStep(int tokenId, int steps) {
		Token t = tokens[id];
		t.moveToken(steps);
	}

	public int findLockedToken() {
		for (int i = 0; i < tokens.length; i++) {
			if (!tokens[i].isFree()) {
				return i;
			}
		}
		return 0;
	}

	public int rollDice() {
		Random rand = new Random();
		return rand.nextInt(6) + 1;
	}

	public int[] getAllTokenPos() {
		int[] tokPositions = new int[4];
		for (int i = 0; i < 4; i++) {
			tokPositions[i] = tokens[i].getPosition();
		}
		return tokPositions;
	}

	public boolean checkClash(int steps, int id) {
		int[] StepsMovedPlayers = new int[4];
		for (int i = 0; i < 4; i++) {
			StepsMovedPlayers[i] = totalSteps(i);
		}
		for (int j = 0; j < 4; j++) {
			if ((StepsMovedPlayers[id] + steps) == StepsMovedPlayers[j]) {
				return true;
			}
		}
		return false;
	}

	public int totalSteps(int id) {
		return ((tokens[id].getPosition() - (id * 13) % 56));
	}

	public boolean canMove(int steps, int id) {
		return (!checkClash(steps, id) && tokens[id].isFree());
	}

	public int makeValidMove(int diceNumber) {
		int id;
		Random rand = new Random();
		Scanner scan = new Scanner(System.in);
		String choice = "y";
		if (this.isHuman) {

			System.out.println("Enter the tokenId to be moved: (1/2/3/4)");
			id = scan.nextInt() - 1;
		} else {
			id = rand.nextInt(4);
		}
		if (diceNumber == 6 ) {
			System.out.println(" Want to unlock a token? (y/n):  ");
			choice = scan.next();
			if(choice.equal("y")){
				int tokenId = findLockedToken();
				unlockToken(tokenId);
				return this.id * 13;
			}
		}
		int[] visited = new int[4];
		for (int i = 0; i < 4; i++) {
			visited[i] = 0;
		}

		int count = 4;
		while (count > 0) {
			if (visited[id] != 1) {
				if (this.canMove(diceNumber, id)) {
					this.moveTokenByStep(id, diceNumber);
					return tokens[id].getPosition();
				}
			} else {
				count++;
				id = rand.nextInt(4);
				if (this.isHuman) {
					System.out.println(" Invalid Move! Try Again ");
					System.out.println("Enter the tokenId to be moved: (1/2/3/4)          ");
					id = scan.nextInt() - 1;
				}

			}
			count--;
		}
		return -1;
	}

}

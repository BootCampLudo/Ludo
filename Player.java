import java.util.Random;

public class Player {

	private int id;
	private boolean isUnfinished;
	private Token[] tokens;
	private boolean isHuman;

	public Player(int id,boolean isHuman) {
		this.id = id;
		this.tokens = new Token[4] ;
		for (int i = 0; i < 4; i++) {

			tokens[i] = new Token(id);

			}


		this.isUnfinished = true;
		this.isHuman=isHuman;
	}

	public Token[] getTokens() {
		return tokens;
	}

	public boolean isUnfinished() {
		return isUnfinished;
	}

	public int getId() {
		return id;
	}

	public void updateIsUnfinished() {
		int countTokensFinished = 0;
		for (int i = 0; i < 4; i++) {
			if (tokens[i].isFinished()) {
				countTokensFinished++;
			} else {
				break;
			}

		}
		if (countTokensFinished != 4) {
			isUnfinished = false;
		}
	}

	public void unlockToken(int id) {
		Token t = tokens[id];
		if (t != null) {
			t.free();
		}
	}

/*	private Token findTokenById(int id) {
			if (id < 4) {
				return tokens[id];
			}
		return null;
	}
*/
	public moveTokenByStep(int tokenId, int steps) {
		if(canMove(steps, tokenId){
			Token t = tokens[id];
		
			t.moveToken(steps);
		}
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
	public int[] allTokenPos(){
		int[] tokPositions = new int[4];
		for(int i = 0; i < 4 ;i++){
			tokPositions[i]= tokens[i].getPosition();
		}
		return tokPositions;
	}
	public boolean checkClash(int steps, int id){
		int[] positions = allTokenPos();
		int[] StepsMovedPlayers = new int[4];
		for(int i = 0; i < 4; i++){
			StepsMovedPlayers[i] = totalSteps(i);
		}
		for(int j = 0; j < 4; j++){
			if((StepsMovedPlayers[id] + steps) == StepsMovedPlayer[i]){
				return true;
			}
		}
		return false;
	}
	public int totalSteps(int id){
		return ((token[id].getposition() - (id*13) % 56);
	}
	public boolean canMove(int steps,int id) {
		
		
			return (!checkClash(steps, id) && tokens[id].isfree());
			
	}
}

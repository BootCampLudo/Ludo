public class Board {
	private Player players[];
	public Board(int numberOfPlayers) {
		players = new Player[numberOfPlayers];
		for (int i = 0; i<numberOfPlayers; i++) {
			Token tokens[] = new Token[4];
			
			for (int j = 0; j<4; j++) {
				tokens[j] = new Token(j+1,i);
			}

			players[i] = new Player(i,tokens);
		}
	}
	public Player[] getPlayers() {
		return players;
	}
	public Player[] playersUnfinished() {
		int UnfinishedCount = 0; 
		for (int i=0;i <players.length; i++) {
			if (players[i].isUnfinished()) {
				UnfinishedCount++;
			}
		}
		if (UnfinishedCount == 0)
			return null;
		Player UnfinishedPlayers[] = new Player[UnfinishedCount];
		for (int i=0;i <players.length; i++) {
			if (players[i].isUnfinished()) {
				UnfinishedPlayers[i] = players[i];
			}
		}
		return UnfinishedPlayers;
	}
	public boolean isHumanUnfinished() {
		for (int i=0; i < players.length; i++) {
			if(players[i].getId() == GAME_CONFIG.HUMAN_ID)
				return players[i].isUnfinished();
		}
		return false;
	}
	public void display() {	
		for (int i = 0; i < players.length; i++) {
			if(players[i].isUnfinished()) {
				System.out.println("Player ID:" +(players[i].getId() + 1));
				Token playerTokens[] = players[i].getTokens();
				for (int j = 0; j < playerTokens.length; j++) {
					int coordinate = playerTokens[j].getCoordinate();
					int state = playerTokens[j].getState();
					int tokenId = playerTokens[j].getId();
					int playerId = playerTokens[j].getPlayerId();
					System.out.println("ID:" + tokenId + " State:"+ state + " Coordinate:" + (coordinate - playerId*13));
				}
				System.out.println("------------------------------------------------");
			}
		}
	}
}

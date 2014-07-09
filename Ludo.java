public class Ludo {
	private Board board;
	private Player[] players;

	public Ludo(int numOfPlayers) {
		board = new Board();
		players = new Player[numOfPlayers];
		
		players[0] = new Player(0, true);
		for (int i = 1; i < numOfPlayers; i++) {
			players[i] = new Player(i, false);
		}
	}

	public void checkHit(int position) {
		int playerAtPos = board.getPlayerAtPosCommonBoard(position);
		if (playerAtPos != -1) {
			players[playerAtPos].resetTokenAtPos(position);
		}
	}

	public void play(int numOfPlayers) {
		int turn = 0;
		int unfinishedPlayers = numOfPlayers;
		while (players[0].isUnfinished()
				&& unfinishedPlayers > 1) {
			turn = turn % numOfPlayers;

			if (players[turn].isUnfinished()) {
				int pos = players[turn].makeValidMove(players[turn].rollDice());
				if (pos != -1) {
					if (pos < turn * 13 - 1) {
						checkHit(pos);
						board.putPlayerCommonBoard(turn, pos);
					}
					else {
						board.putPlayerHomeBoard(turn, pos - (turn * 13 - 1));
					}
				}
				else {
					System.out.println("Move not possible. Next player"); //Move not possible
				}
				if (!players[turn].isUnfinished()) {
					unfinishedPlayers--;
				}
			}
			turn++;
		}
		if (players[0].isUnfinished()) {
			System.out.println("You Lost !!");
		} else {
			System.out.println("You Win !!");
		}
	}
	
	public static void main(String[] arg) {
		int numOfPlayers = 4;
		Ludo ludo = new Ludo(numOfPlayers);
		ludo.play(numOfPlayers);
	}
}

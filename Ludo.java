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

		int numOfPlayers = 4;
		Ludo ludo = new Ludo(numOfPlayers);
		int unfinishedPlayers = numOfPlayers;
		while (players[0].isUnfinished()
				&& unfinishedPlayers > 1) {
			turn = turn % numOfPlayers;

			if (players[turn].isUnfinished()) {
				int pos = player.move();
				if (pos != -1) {
					if (pos < offset - 1) {
						checkHit(pos);
						board.setterCommonBoard(turn, pos);
					}
					else {
						board.setterHomeBoard(turn, pos - (offset - 1));
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
}

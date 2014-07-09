import java.util.*;

public class Board {
	private int[] commonBoard;
	private int[][] homeBoard;

	public Board() {
		this.commonBoard = new int[52];
		for (int i = 0; i < 52; i++)
			commonBoard[i] = -1;
		this.homeBoard = new int[4][5];
		for (int i = 0; i < 4; i++)
			for (int j = 0; j < 5; j++)
				homeBoard[i][j] = -1;

	}

	public void putPlayerCommonBoard(int playerid, int pos) {
		this.commonBoard[pos] = playerid;
	}

	public void putPlayerHomeBoard(int playerid, int pos) {
		this.homeBoard[playerid][pos] = playerid;
	}

	public int PlayerAtPosCommonBoard(int pos) {
		return this.commonBoard[pos];
	}

	public int PlayerAtPosHomeBoard(int playerid, int pos) {
		return this.homeBoard[playerid][pos];
	}

}

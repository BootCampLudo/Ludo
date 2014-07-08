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

	public void setterCommonBoard(int playerid, int index) {
		this.commonBoard[index] = playerid;
	}

	public void setterHomeBoard(int playerid, int index) {
		this.homeBoard[playerid][index] = playerid;
	}

	public int getterCommonBoard(int index) {
		return this.commonBoard[index];
	}

	public int getterHomeBoard(int playerid, int index) {
		return this.homeBoard[playerid][index];
	}

}

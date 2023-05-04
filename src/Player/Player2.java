package Player;

import Borad.Board;
import disk.DiskColor;

public class Player2 extends Player {
	private static String name = "Player2";

	public Player2(Board board) {
		super(board, name, DiskColor.Black, DiskColor.White);
	}

}

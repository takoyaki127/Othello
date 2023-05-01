package Player;

import Borad.Board;
import disk.Disks;

public class Player2 extends Player {
	private static String name = "Player2";

	public Player2(Board board) {
		super(board, name, Disks.Black, Disks.White);
	}

}

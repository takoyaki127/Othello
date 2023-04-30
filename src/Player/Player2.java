package Player;

import Borad.Board;
import disk.BlackDisk;
import disk.WhiteDisk;

public class Player2 extends Player {
	private static String name = "Player2";

	public Player2(Board board) {
		super(board, name, new BlackDisk(), new WhiteDisk());
	}

}

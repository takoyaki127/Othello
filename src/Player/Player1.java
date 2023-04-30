package Player;

import Borad.Board;
import disk.BlackDisk;
import disk.WhiteDisk;

public class Player1 extends Player {
	private static String name = "Player1";

	public Player1(Board board) {
		super(board, name, new WhiteDisk(), new BlackDisk());
	}

}

package Player;

import Borad.Board;
import disk.Disks;

public class Player1 extends Player {
	private static String name = "Player1";

	public Player1(Board board) {
		super(board, name, Disks.White, Disks.Black);
	}

}

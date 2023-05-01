package Player;

import Borad.Board;
import disk.Disks;

public class Player implements Put {
	private Board board;
	private String name;
	private Disks myDisk, enemyDisk;

	public Player(Board board, String name, Disks myDisk, Disks enemyDisk) {
		this.board = board;
		this.myDisk = myDisk;
		this.enemyDisk = enemyDisk;
		this.name = name;
	}

	public String name() {
		return name;
	}

	public Disks mycolor() {
		return myDisk;
	}

	public Disks enemyColor() {
		return enemyDisk;
	}

	public boolean put(int x, int y) {
		if (board.put(this, x, y)) {
			return true;
		}
		return false;
	}

	public void win() {
		System.out.println(name + " Win!!");
	}
}

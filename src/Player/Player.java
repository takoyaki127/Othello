package Player;

import Borad.Board;
import disk.DiskColor;

public class Player implements Put {
	private Board board;
	private String name;
	private DiskColor myDisk, enemyDisk;

	public Player(Board board, String name, DiskColor myDisk, DiskColor enemyDisk) {
		this.board = board;
		this.myDisk = myDisk;
		this.enemyDisk = enemyDisk;
		this.name = name;
	}

	public String name() {
		return name;
	}

	public DiskColor mycolor() {
		return myDisk;
	}

	public DiskColor enemyColor() {
		return enemyDisk;
	}

	public boolean put(int x, int y) {
		return board.put(this, x, y);
	}

	public void win() {
		System.out.println(name + " Win!!");
	}
}

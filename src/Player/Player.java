package Player;

import Borad.Board;
import disk.DiskColor;

public class Player {
	private static int count = 0;
	private Board board;
	private String name;
	private DiskColor myDisk, enemyDisk;

	private Player(Board board, String name, DiskColor myDisk, DiskColor enemyDisk) {
		this.board = board;
		this.myDisk = myDisk;
		this.enemyDisk = enemyDisk;
		this.name = name;
	}

	public static Player getInstance(String name, Board board) {
		count++;
		if (count == 1) {
			return new Player(board, name, DiskColor.White, DiskColor.Black);
		}
		if (count == 2) {
			return new Player(board, name, DiskColor.Black, DiskColor.White);
		}
		throw new UnsupportedOperationException(); // Playerのインスタンスは２人まで生成できるようにする
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
		return board.playerPut(this, x, y);
	}

	public void win() {
		System.out.println(name + " Win!!");
	}
}

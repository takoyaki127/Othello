package Player;

import Borad.Board;
import disk.Disk;

public class Player implements Put{
	private Board board;
	private String name;
	private Disk myDisk, enemyDisk;
	
	public Player(Board board, String name, Disk myDisk, Disk enemyDisk) {
		this.board = board;
		this.myDisk = myDisk;
		this.enemyDisk = enemyDisk;
		this.name = name;
	}
	
	public String name() {
		return name;
	}
	
	public int mycolor() {
		return myDisk.color();
	}
	
	public int enemyColor() {
		return enemyDisk.color();
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

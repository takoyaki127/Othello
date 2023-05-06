package Borad;

import Player.Player;
import disk.DiskColor;

public class Board {
	private DiskColor[][] board;

	public Board(int width, int height) {
		this.board = new DiskColor[height][width];
		setup();
	}

	private void setup() {
		int row_center = (height() - 1) / 2;
		int col_center = (width() - 1) / 2;

		putDisk(DiskColor.White, col_center, row_center);
		putDisk(DiskColor.White, col_center + 1, row_center + 1);

		putDisk(DiskColor.Black, col_center + 1, row_center);
		putDisk(DiskColor.Black, col_center, row_center + 1);
	}

	public int height() {
		return this.board.length;
	}

	public int width() {
		return this.board[0].length;
	}

	public boolean playerPut(Player player, int x, int y) {
		if (roundUpdate(x, y, player)) {
			putDisk(player.mycolor(), x, y);
			return true;
		}
		return false;
	}

	private void putDisk(DiskColor disk, int x, int y) {
		try {
			board[y][x] = disk;
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
		}
	}

	public DiskColor get(int x, int y) {
		try {
			return board[y][x];
		} catch (IndexOutOfBoundsException e) {
			return null;
		}
	}

	private boolean roundUpdate(int x, int y, Player player) {
		if (get(x, y) != null) {
			return false;
		}

		boolean isValidMove = false;
		for (int dy = -1; dy < 2; dy++) {
			for (int dx = -1; dx < 2; dx++) {
				if (directUpdate(x, y, dx, dy, player)) {
					isValidMove = true;
				}
			}
		}
		return isValidMove;
	}

	private boolean directUpdate(int x, int y, int dx, int dy, Player player) {
		if (get(x + dx, y + dy) == player.enemyColor()) {
			if (get(x + dx + dx, y + dy + dy) == player.mycolor()) {
				putDisk(player.mycolor(), x + dx, y + dy);
				return true;
			}
			if (directUpdate(x + dx, y + dy, dx, dy, player)) {
				putDisk(player.mycolor(), x + dx, y + dy);
				return true;
			}
		}
		return false;
	}

	public boolean isGameContinued(Player[] players) {
		for (Player player : players) {
			if (playerCanPut(player)) {
				return true;
			}
		}
		return false;
	}

	private boolean playerCanPut(Player player) {
		for (int y = 0; y < board.length; y++) {
			for (int x = 0; x < board[y].length; x++) {
				if (canPut(x, y, player)) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean canPut(int x, int y, Player player) {
		if (get(x, y) != null) {
			return false;
		}
		return roundSerch(x, y, player);
	}

	private boolean roundSerch(int x, int y, Player player) {
		for (int dy = -1; dy < 2; dy++) {
			for (int dx = -1; dx < 2; dx++) {
				if (directSerch(x, y, dx, dy, player)) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean directSerch(int x, int y, int dx, int dy, Player player) {
		if (get(x + dx, y + dy) == player.enemyColor()) {
			if (get(x + dx + dx, y + dy + dy) == player.mycolor()) {
				return true;
			}
			if (directSerch(x + dx, y + dy, dx, dy, player)) {
				return true;
			}
		}
		return false;
	}

	private int diskCount(Player player) {
		int count = 0;
		for (int y = 0; y < board.length; y++) {
			for (int x = 0; x < board[y].length; x++) {
				if (get(x, y) == player.mycolor()) {
					count++;
				}
			}
		}
		return count;
	}

	public Player result(Player[] players) {
		int max = -1;
		int diskCount;
		int drawCount = 1;
		Player winner = null;
		for (Player player : players) {
			diskCount = diskCount(player);
			if (max == diskCount) {
				drawCount++;
			}
			if (max < diskCount) {
				max = diskCount;
				winner = player;
				drawCount = 1;
			}
		}
		if (drawCount >= 2) {
			return null;
		}
		return winner;
	}
}

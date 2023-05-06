package Borad;

import Player.Player;
import disk.DiskColor;

public class Board {
	private DiskColor[][] board;

	public Board(int width, int height) {
		this.board = new DiskColor[height][width];
		setup();
	}

	public boolean put(Player player, int x, int y) {
		if (update(x, y, player)) {
			board[y][x] = player.mycolor();
			return true;
		}
		return false;
	}

	private void put(DiskColor disk, int x, int y) {
		board[y][x] = disk;
	}

	public DiskColor get(int x, int y) {
		try {
			return board[y][x];
		} catch (Exception e) {
			return null;
		}
	}

	private void setup() {
		int row_center = board.length / 2 - 1;
		int col_center = board[0].length / 2 - 1;

		put(DiskColor.White, row_center, col_center);
		put(DiskColor.White, row_center + 1, col_center + 1);

		put(DiskColor.Black, row_center + 1, col_center);
		put(DiskColor.Black, row_center, col_center + 1);
	}

	private boolean update(int x, int y, Player player) {
		boolean isValidMove = false;
		if (get(x, y) != null) {
			return false;
		}
		for (int dy = -1; dy < 2; dy++) {
			for (int dx = -1; dx < 2; dx++) {
				if (dx == 0 && dy == 0) {
					continue;
				}
				if (validateMove(x, y, dx, dy, player)) {
					isValidMove = true;
				}

			}
		}
		return isValidMove;
	}

	private boolean validateMove(int x, int y, int dx, int dy, Player player) {
		int x_next = x + dx;
		int y_next = y + dy;
		if (get(x_next, y_next) == player.enemyColor()) {
			if (get(x_next + dx, y_next + dy) == player.mycolor()) {
				board[y_next][x_next] = player.mycolor();
				return true;
			}
			if (validateMove(x_next, y_next, dx, dy, player)) {
				board[y_next][x_next] = player.mycolor();
				return true;
			}
		}
		return false;
	}

	public boolean isGameContinued(Player[] players) {
		int count = 0;
		for (int i = 0; i < board.length; i++) {
			for (int k = 0; k < board[0].length; k++) {
				if (board[i][k] == null) {
					count++;
				}
			}
		}
		if (count == 0) {
			return false;
		}
		return allPlayerCanPut(players);
	}

	private boolean allPlayerCanPut(Player[] players) {
		for (Player player : players) {
			for (int y = 0; y < board.length; y++) {
				for (int x = 0; x < board[y].length; x++) {
					if (canPut(x, y, player)) {
						return true;
					}
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
		for (int i = 0; i < board.length; i++) {
			for (int k = 0; k < board[i].length; k++) {
				if (board[i][k] == player.mycolor()) {
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
			System.out.println("draw");
			return null;
		}
		winner.win();
		return winner;
	}

	public int height() {
		return this.board.length;
	}

	public int width() {
		return this.board[0].length;
	}
}

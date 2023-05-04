package Borad;

import Player.Player;
import disk.DiskColor;

public class Board {
	private DiskColor[][] board;

	public Board(int width, int height) {
		this.board = new DiskColor[height][width];
		setup();
	}

	public void display() {
		System.out.print("  |");
		for (int i = 0; i < board[0].length; i++) {
			System.out.print(" " + i);
		}
		System.out.print("\n---");
		for (int i = 0; i < board[0].length; i++) {
			System.out.print("--");
		}
		System.out.println();
		for (int y = 0; y < board.length; y++) {
			System.out.print(" " + y + "|");
			for (int x = 0; x < board[y].length; x++) {
				if (board[y][x] == null) {
					System.out.print(" _");
				} else {
					System.out.print(" " + ordinal(x, y));
				}
			}
			System.out.println();
		}
		System.out.println();
	}

	private String ordinal(int x, int y) {
		if (board[y][x] == DiskColor.White) {
			return "●";
		}
		return "○";
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

	public boolean isGameContinued() {
		// Todo: 誰も石を置けなくなったときにfalseを返す処理を追加
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
		return true;
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

	public void result(Player[] players) {
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
			return;
		}
		winner.win();
		return;
	}

	public int height() {
		return this.board.length;
	}

	public int width() {
		return this.board[0].length;
	}
}

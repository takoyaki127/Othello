import java.util.Scanner;

import Borad.Board;
import Player.Player;
import Player.Player1;
import Player.Player2;
import Screen.MyPanel;

public class Game {
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		Board board = new Board(8, 8);
//		board.display();
		

		// プレイヤーを生成
		Player player1 = new Player1(board);
		Player player2 = new Player2(board);
		Player[] players = { player1, player2 };
		
		new MyPanel("Othello",board, players);

		// ゲーム中
//		while (board.isGameContinued()) {
//			inputDiskPosition(player1, board);
//			inputDiskPosition(player2, board);
//		}

		// 結果
//		board.result(players);

		sc.close();
	}

	private static boolean passCheck(int x, int y) {
		if (x < 0 || y < 0) {
			return true;
		}
		return false;
	}

	private static int[] scan(Player player) {
		int[] pos = new int[2];
		while (true) {
			System.out.println("負の値を入力でパス");
			System.out.print(player.name() + "->");
			try {
				pos[0] = Integer.parseInt(sc.next());
				pos[1] = Integer.parseInt(sc.next());
				break;
			} catch (Exception e) {
				System.out.println("不正な値です\n");
			}
		}
		return pos;
	}

	private static void inputDiskPosition(Player player, Board board) {
		boolean putDisk = false;
		while (board.isGameContinued() && putDisk == false) {
			int[] pos = scan(player);
			if (passCheck(pos[0], pos[1])) {
				break;
			}
			putDisk = player.put(pos[0], pos[1]);
		}
		board.display();
	}
}

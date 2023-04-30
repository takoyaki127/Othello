import java.util.Scanner;

import Borad.Board;
import Player.Player;
import Player.Player1;
import Player.Player2;

public class Game {
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		Board board = new Board(4, 4);
		board.display();

		// プレイヤーを生成
		Player player1 = new Player1(board);
		Player player2 = new Player2(board);

		// ゲーム中
		while (board.isGameContinued()) {
			inputDiskPosition(player1, board);
			inputDiskPosition(player2, board);
		}

		// 結果
		Player[] players = { player1, player2 };
		board.result(players);

		sc.close();
	}

	private static boolean passCheck(int x, int y) {
		if (x < 0 || y < 0) {
			return true;
		}
		return false;
	}

	private static int[] scan() {
		// Todo: エラー時の処理を追加する
		int[] pos = new int[2];
		pos[0] = sc.nextInt();
		pos[1] = sc.nextInt();

		return pos;
	}

	private static void inputDiskPosition(Player player, Board board) {
		boolean putDisk = false;
		while (board.isGameContinued() && putDisk == false) {
			System.out.print(player.name() + "->");
			int[] pos = scan();
			if (passCheck(pos[0], pos[1])) {
				break;
			}
			putDisk = player.put(pos[0], pos[1]);
		}
		board.display();
	}
}

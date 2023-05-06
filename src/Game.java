import Borad.Board;
import Player.Player;
import Screen.MyPanel;

public class Game {

	public static void main(String[] args) {
		Board board = new Board(8, 8);

		// プレイヤーを生成
		Player player1 = Player.getInstance("Player1", board);
		Player player2 = Player.getInstance("Player2", board);
		Player[] players = { player1, player2 };

		new MyPanel("Othello", board, players);
	}

}

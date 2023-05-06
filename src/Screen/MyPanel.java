package Screen;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Borad.Board;
import Player.Player;
import disk.Disk;
import disk.DiskColor;

public class MyPanel extends JPanel implements MouseListener, ActionListener {
	private static final long serialVersionUID = 1L;
	private Board board;
	private Player[] players;
	private Disk disk;
	private Cell cell;
	private JButton passButton;
	private JLabel state;
	private int turn = 0;

	public MyPanel(String title, Board board, Player[] players) {
		super(true);
		this.board = board;
		this.players = players;

		JFrame frame = new JFrame(title);
		Container frmContentPane = frame.getContentPane();
		frmContentPane.add(this);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(1000, 100, 600, 670);

		passButton = new JButton("Pass");
		passButton.setFont(new Font("メイリオ", Font.PLAIN, 15));
		passButton.addActionListener(this);
		add(passButton);
		frmContentPane.add(passButton, BorderLayout.SOUTH);

		state = new JLabel("");
		state.setHorizontalAlignment(JLabel.CENTER);
		state.setFont(new Font("メイリオ", Font.BOLD, 20));
		playerNameSetLabel();
		frmContentPane.add(state, BorderLayout.NORTH);

		addMouseListener(this);
		frame.setVisible(true);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.cell = getCell();
		drawLines(g);
		drawDisks(g);
	}

	private Cell getCell() {
		int height = getHeight() / board.height();
		int width = getWidth() / board.width();
		return new Cell(height, width);
	}

	private void drawLines(Graphics g) {
		drawHorizontalLine(g);
		drawVerticalLine(g);
	}

	private void drawHorizontalLine(Graphics g) {
		for (int i = 0; i < board.height(); i++) {
			int y = cell.height() * i;
			g.drawLine(0, y, getWidth(), y);
		}
	}

	private void drawVerticalLine(Graphics g) {
		for (int i = 1; i < board.width(); i++) {
			int x = cell.width() * i;
			g.drawLine(x, 0, x, getHeight());
		}
	}

	private void playerNameSetLabel() {
		if (turn % 2 == 0) {
			state.setText(players[0].name());
			return;
		}
		state.setText(players[1].name());
	}

	private int[] getCellAt(Point point) {
		int xCellNum = (int) (point.getX() / cell.width());
		int yCellNum = (int) (point.getY() / cell.height());
		int[] pos = { xCellNum, yCellNum };
		return pos;
	}

	private void drawDisks(Graphics g) {
		createDisk(0.7);
		double margin = ((1 - disk.size()) / 2);
		for (int y = 0; y < board.height(); y++) {
			for (int x = 0; x < board.width(); x++) {
				int xdisk = (int) ((x + margin) * cell.width());
				int ydisk = (int) ((y + margin) * cell.height());
				drawDisk(g, xdisk, ydisk, x, y);
			}
		}
	}

	private void createDisk(double diskSize) {
		this.disk = new Disk(diskSize, cell);
	}

	private void drawDisk(Graphics g, int x, int y, int xNum, int yNum) {
		if (board.get(xNum, yNum) == DiskColor.White) {
			g.drawOval(x, y, disk.x(), disk.y());
		} else if (board.get(xNum, yNum) == DiskColor.Black) {
			g.fillOval(x, y, disk.x(), disk.y());
		}
	}

	private void finish() {
		if (board.isGameContinued(players) == false) {
			Player winner = board.result(players);
			if (winner == null) {
				state.setText("Draw");
				return;
			}
			state.setText(winner.name() + " Win !!");
			return;
		}
		playerNameSetLabel();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int[] pos = getCellAt(e.getPoint());
		// System.out.println(pos[0]+ "" + pos[1]);

		int player_num = turn % players.length;
		boolean isValidMove = players[player_num].put(pos[0], pos[1]);
		if (isValidMove) {
			turn++;
			finish();
			repaint();
			return;
		}
		return;
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (passButton.equals(e.getSource())) {
			turn++;
			playerNameSetLabel();
			repaint();
		}
	}
}

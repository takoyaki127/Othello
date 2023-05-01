package Screen;

import java.awt.Container;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Borad.Board;
import Player.Player;
import disk.Disks;

public class MyPanel extends JPanel implements MouseListener {
	private Board board;
	private int cellHeight, cellWidth;
	private Player[] players;
	private int diskXSize, diskYSize;
	private double diskSize = 0.7;

	public MyPanel(String title, Board board, Player[] players) {
		super(true);
		this.board = board;
		this.players = players;
		JFrame frame = new JFrame(title);
		Container frmContentPane = frame.getContentPane();
		frmContentPane.add(this);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridLayout());
		frame.setBounds(1000, 100, 600, 600);

		addMouseListener(this);

		frame.setVisible(true);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawHorizontalLine(g);
		drawVerticalLine(g);
		drawDisks(g);
	}

	private void drawHorizontalLine(Graphics g) {
		cellHeight = getHeight() / board.height();
		for (int i = 1; i < board.height(); i++) {
			int y = cellHeight * i;
			g.drawLine(0, y, getWidth(), y);
		}
		diskYSize = (int) (cellHeight * diskSize);
	}

	private void drawVerticalLine(Graphics g) {
		cellWidth = getWidth() / board.width();
		for (int i = 1; i < board.width(); i++) {
			int x = cellWidth * i;
			g.drawLine(x, 0, x, getHeight());
		}
		diskXSize = (int) (cellWidth * diskSize);
	}

	int count = 0;

	@Override
	public void mouseClicked(MouseEvent e) {
		int[] pos = getCellAt(e.getPoint());
		// System.out.println(pos[0]+ "" + pos[1]);

		boolean isValidMove = false;
		if (count % players.length == 0) {
			isValidMove = players[0].put(pos[0], pos[1]);
		} else {
			isValidMove = players[1].put(pos[0], pos[1]);
		}
		if (isValidMove) {
			count++;
			repaint();
			finish();
		}

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

	private int[] getCellAt(Point point) {
		int xCellNum = (int) (point.getX() / cellWidth);
		int yCellNum = (int) (point.getY() / cellHeight);
		int[] pos = { xCellNum, yCellNum };
		return pos;
	}

	private void drawDisks(Graphics g) {
		double margin = ((1 - diskSize) / 2);
		for (int y = 0; y < board.height(); y++) {
			for (int x = 0; x < board.width(); x++) {
				int xdisk = (int) ((x + margin) * cellWidth);
				int ydisk = (int) ((y + margin) * cellHeight);
				drawDisk(g, xdisk, ydisk, x, y);
			}
		}
	}

	private void drawDisk(Graphics g, int x, int y, int xNum, int yNum) {
		if (board.get(xNum, yNum) == Disks.White) {
			g.drawOval(x, y, diskXSize, diskYSize);
		} else if (board.get(xNum, yNum) == Disks.Black) {
			g.fillOval(x, y, diskXSize, diskYSize);
		}
	}

	private void finish() {
		if (board.isGameContinued() == false) {
			board.result(players);
		}
	}
}

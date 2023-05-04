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
import disk.Disk;
import disk.DiskColor;

public class MyPanel extends JPanel implements MouseListener {
	private static final long serialVersionUID = 1L;
	private Board board;
	private Player[] players;
	private Disk disk = new Disk(0.7);
	private Cell cell;

	public MyPanel(String title, Board board, Player[] players) {
		super(true);
		this.board = board;
		this.players = players;
		
		JFrame frame = new JFrame(title);
		Container frmContentPane = frame.getContentPane();
		frmContentPane.add(this);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(1000, 100, 600, 600);
		frame.setVisible(true);

		setLayout(new GridLayout());

		addMouseListener(this);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawLines(g);
		drawDisks(g);
	}
	
	private void drawLines(Graphics g) {
		cell = getCell();
		drawHorizontalLine(g, cell);
		drawVerticalLine(g, cell);
	}
	
	private Cell getCell() {
		int height = getHeight() / board.height();
		int width = getWidth() / board.width();
		return new Cell(height,width);
	}

	private void drawHorizontalLine(Graphics g, Cell cell) {
		for (int i = 1; i < board.height(); i++) {
			int y = cell.height() * i;
			g.drawLine(0, y, getWidth(), y);
		}
		disk.setY(cell.height());
	}

	private void drawVerticalLine(Graphics g, Cell cell) {
		for (int i = 1; i < board.width(); i++) {
			int x = cell.width() * i;
			g.drawLine(x, 0, x, getHeight());
		}
		disk.setX(cell.width());
	}

	int turn = 0;

	@Override
	public void mouseClicked(MouseEvent e) {
		int[] pos = getCellAt(e.getPoint());
		// System.out.println(pos[0]+ "" + pos[1]);

		boolean isValidMove = false;
		if (turn % players.length == 0) {
			isValidMove = players[0].put(pos[0], pos[1]);
		} else {
			isValidMove = players[1].put(pos[0], pos[1]);
		}
		if (isValidMove) {
			turn++;
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
		int xCellNum = (int) (point.getX() / cell.width());
		int yCellNum = (int) (point.getY() / cell.height());
		int[] pos = { xCellNum, yCellNum };
		return pos;
	}

	private void drawDisks(Graphics g) {
		double margin = ((1 - disk.size()) / 2);
		for (int y = 0; y < board.height(); y++) {
			for (int x = 0; x < board.width(); x++) {
				int xdisk = (int) ((x + margin) * cell.width());
				int ydisk = (int) ((y + margin) * cell.height());
				drawDisk(g, xdisk, ydisk, x, y);
			}
		}
	}

	private void drawDisk(Graphics g, int x, int y, int xNum, int yNum) {
		if (board.get(xNum, yNum) == DiskColor.White) {
			g.drawOval(x, y, disk.x(), disk.y());
		} else if (board.get(xNum, yNum) == DiskColor.Black) {
			g.fillOval(x, y, disk.x(), disk.y());
		}
	}

	private void finish() {
		if (board.isGameContinued() == false) {
			board.result(players);
		}
	}
}

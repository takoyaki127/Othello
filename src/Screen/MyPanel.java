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
	private JLabel resultLabel;

	public MyPanel(String title, Board board, Player[] players) {
		super(true);
		this.board = board;
		this.players = players;

		JFrame frame = new JFrame(title);
		Container frmContentPane = frame.getContentPane();
		frmContentPane.add(this);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(1000, 100, 600, 630);

		passButton = new JButton("Pass");
		passButton.addActionListener(this);
		add(passButton);
		frmContentPane.add(passButton, BorderLayout.SOUTH);

		resultLabel = new JLabel("");
		resultLabel.setHorizontalAlignment(JLabel.CENTER);
		resultLabel.setFont(new Font("メイリオ", Font.PLAIN, 20));
		frmContentPane.add(resultLabel, BorderLayout.NORTH);

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
		for (int i = 1; i < board.height(); i++) {
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
				resultLabel.setText("Draw");
				return;
			}
			resultLabel.setText(winner.name() + " Win !!");
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

	@Override
	public void actionPerformed(ActionEvent e) {
		if (passButton.equals(e.getSource())) {
			turn++;
		}
	}
}

package disk;

import Screen.Cell;

public class Disk {
	private double diskSize;
	private int diskXSize, diskYSize;

	public Disk(double diskSize, Cell cell) {
		this.diskSize = diskSize;
		setY(cell);
		setX(cell);
	}

	public double size() {
		return diskSize;
	}

	private void setY(Cell cell) {
		diskYSize = (int) (cell.height() * diskSize);
	}

	public int y() {
		return diskYSize;
	}

	private void setX(Cell cell) {
		diskXSize = (int) (cell.width() * diskSize);
	}

	public int x() {
		return diskXSize;
	}

}

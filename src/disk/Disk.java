package disk;

import Screen.Cell;

public class Disk {
	private double diskSize;
	private int diskXSize, diskYSize;

	public Disk(double diskSize, Cell cell) {
		this.diskSize = diskSize;
		setYSize(cell);
		setXSize(cell);
	}

	public double size() {
		return diskSize;
	}

	private void setYSize(Cell cell) {
		diskYSize = (int) (cell.height() * diskSize);
	}

	public int y() {
		return diskYSize;
	}

	private void setXSize(Cell cell) {
		diskXSize = (int) (cell.width() * diskSize);
	}

	public int x() {
		return diskXSize;
	}

}

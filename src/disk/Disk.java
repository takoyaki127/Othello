package disk;

public class Disk {
	private double diskSize;
	private int diskXSize,diskYSize;

	public Disk(double diskSize) {
		this.diskSize = diskSize;
	}
	
	public double size() {
		return diskSize;
	}
	
	public void setY(int cellHeight) {
		diskYSize = (int) (cellHeight * diskSize);
	}
	
	public int y() {
		return diskYSize;
	}
	
	public void setX(int cellWidth) {
		diskXSize = (int) (cellWidth * diskSize);
	}
	
	public int x() {
		return diskXSize;
	}

}

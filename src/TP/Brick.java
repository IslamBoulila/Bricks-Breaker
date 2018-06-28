package TP;

public class Brick {

	private boolean active ;
	private int xCoor,yCoor;
	
	
	
	public Brick(boolean active, int xCoor, int yCoor) {
		
		this.active = active;
		this.xCoor = xCoor;
		this.yCoor = yCoor;
	}
	public int getxCoor() {
		return xCoor;
	}
	public void setxCoor(int xCoor) {
		this.xCoor = xCoor;
	}
	public int getyCoor() {
		return yCoor;
	}
	public void setyCoor(int yCoor) {
		this.yCoor = yCoor;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	
}

package screen;

public class Hole {
	private int x;
	private int y;
	private int t;

	public Hole(int x, int y) {
		this.x = x;
		this.y = y;
		this.t = 0;
	}

	public int getX() {
		return this.x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return this.y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getT() {
		return this.t;
	}

	public void setT(int t) {
		this.t = t;
	}
}

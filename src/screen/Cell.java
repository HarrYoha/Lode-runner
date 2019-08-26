package screen;

public enum Cell {
	EMP(true, " "), PLT(false, "-"), HOL(true, "U"), LAD(true, "#"), HDR(true, "¨"), MTL(false, "@");

	private boolean free;
	private String print;

	private Cell(boolean free, String print) {
		this.free = free;
		this.print = print;
	}

	public boolean isFree() {
		return this.free;
	}

	@Override
	public String toString() {
		return this.print;
	}
}

package interfaces;

public enum ItemType {
	Treasure("T");

	String print;

	private ItemType(String print) {
		this.print = print;
	}

	@Override
	public String toString() {
		return this.print;
	}
}

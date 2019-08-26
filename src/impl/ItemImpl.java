package impl;

import interfaces.Item;
import interfaces.ItemType;

public class ItemImpl implements Item {

	private int id;
	private ItemType nature;
	private int x;
	private int y;

	public ItemImpl(int id, ItemType nature, int x, int y) {
		this.id = id;
		this.nature = nature;
		this.x = x;
		this.y = y;
	}

	@Override
	public int getId() {
		return this.id;
	}

	@Override
	public ItemType getNature() {
		return this.nature;
	}

	@Override
	public int getX() {
		return this.x;
	}

	@Override
	public int getY() {
		return this.y;
	}

	@Override
	public String toString() {
		return this.nature.toString();
	}
}

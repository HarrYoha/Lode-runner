package decorators;

import interfaces.Item;
import interfaces.ItemType;

public class ItemDecorator implements Item {

	private final Item delegate;

	public ItemDecorator(Item delegate) {
		this.delegate = delegate;
	}

	@Override
	public int getId() {
		return this.delegate.getId();
	}

	@Override
	public ItemType getNature() {
		return this.delegate.getNature();
	}

	@Override
	public int getX() {
		return this.delegate.getX();
	}

	@Override
	public int getY() {
		return this.delegate.getY();
	}

}

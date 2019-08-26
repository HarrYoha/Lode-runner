package decorators;

import java.util.List;
import java.util.Set;

import interfaces.CharItem;
import interfaces.EditableScreen;
import interfaces.Environment;
import screen.Cell;
import screen.Hole;

public class EnvironmentDecorator implements Environment {


	private final Environment delegate;

	@Override
	public void setNature(int x, int y, Cell type) {
		this.delegate.setNature(x, y, type);
	}

	private final EditableScreen screen;

	public EnvironmentDecorator(Environment delegate, EditableScreen screen) {
		this.delegate = delegate;
		this.screen = screen;
	}

	@Override
	public int getHeight() {
		return this.delegate.getHeight();
	}

	@Override
	public int getWidth() {
		return this.delegate.getWidth();
	}

	@Override
	public Cell getCellNature(int x, int y) {
		return this.delegate.getCellNature(x, y);
	}

	@Override
	public void init(int x, int y) {
		this.delegate.init(x, y);
	}

	@Override
	public void dig(int x, int y) {
		this.delegate.dig(x, y);
	}

	@Override
	public Set<CharItem> getCellContent(int x, int y) {
		return this.delegate.getCellContent(x, y);
	}

	@Override
	public void fill(int x, int y) {
		this.delegate.fill(x, y);
	}

	@Override
	public void addContent(CharItem charItem) {
		this.delegate.addContent(charItem);
	}

	@Override
	public List<CharItem> getCharItemList() {
		return this.delegate.getCharItemList();
	}

	@Override
	public List<Hole> getHoles() {
		return this.delegate.getHoles();
	}

	@Override
	public void addHole(Hole hole) {
		this.delegate.addHole(hole);
	}

	@Override
	public boolean isPlayable() {
		return delegate.isPlayable();
	}

}

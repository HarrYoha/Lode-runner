package decorators;

import java.util.List;

import interfaces.Screen;
import screen.Cell;
import screen.Hole;

public class ScreenDecorator implements Screen {

	protected final Screen delegate;

	public ScreenDecorator(Screen delegate) {
		this.delegate = delegate;
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
	public void fill(int x, int y) {
		this.delegate.fill(x, y);
	}

	@Override
	public List<Hole> getHoles() {
		return this.delegate.getHoles();
	}

	@Override
	public void addHole(Hole hole) {
		this.delegate.addHole(hole);
	}

}

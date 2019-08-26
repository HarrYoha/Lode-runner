package decorators;

import interfaces.Character;
import interfaces.Environment;
import interfaces.Guard;
import interfaces.Move;

public class GuardDecorator implements Guard {
	protected final Guard delegate;

	public GuardDecorator(Guard delegate) {
		this.delegate = delegate;
	}

	@Override
	public int getId() {
		return this.delegate.getId();
	}

	@Override
	public Character getTarget() {
		return this.delegate.getTarget();
	}

	@Override
	public int getTimeInHole() {
		return this.delegate.getTimeInHole();
	}

	@Override
	public Move getBehaviour() {
		return this.delegate.getBehaviour();
	}

	@Override
	public boolean climbLeft() {
		return this.delegate.climbLeft();
	}

	@Override
	public boolean climbRight() {
		return this.delegate.climbRight();
	}

	@Override
	public void step() {
		this.delegate.step();
	}

	@Override
	public boolean containsNoneOrTreasure(int x, int y) {
		return this.delegate.containsNoneOrTreasure(x, y);
	}

	@Override
	public Environment getEnvi() {
		return this.delegate.getEnvi();
	}

	@Override
	public int getX() {
		return this.delegate.getX();
	}

	@Override
	public int getY() {
		return this.delegate.getY();
	}

	@Override
	public void setX(int x) {
		this.delegate.setX(x);
	}

	@Override
	public void setY(int y) {
		this.delegate.setY(y);
	}

	@Override
	public void init(Environment screen, int x, int y) {
		this.delegate.init(screen, x, y);
	}

	@Override
	public void goLeft() {
		this.delegate.goLeft();
	}

	@Override
	public void goRight() {
		this.delegate.goRight();
	}

	@Override
	public void goUp() {
		this.delegate.goUp();
	}

	@Override
	public void goDown() {
		this.delegate.goDown();
	}

	@Override
	public int getInitialX() {
		return this.delegate.getInitialX();
	}

	@Override
	public int getInitialY() {
		return this.delegate.getInitialY();
	}

	@Override
	public boolean willFall() {
		return this.delegate.willFall();
	}

	@Override
	public boolean InHoleWithinLimit() {
		return this.delegate.InHoleWithinLimit();
	}

	@Override
	public boolean OutOfHole() {
		return this.delegate.OutOfHole();
	}
}

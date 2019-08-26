package impl;

import interfaces.Character;
import interfaces.Environment;
import screen.Cell;

public class CharacterImpl implements Character {
	protected int x;
	protected int y;
	protected Environment screen;

	public CharacterImpl(Environment screen, int x, int y) {
		init(screen, x, y);
	}

	@Override
	public void setX(int x) {
		this.x = x;
	}

	@Override
	public void setY(int y) {
		this.y = y;
	}

	@Override
	public Environment getEnvi() {
		return this.screen;
	}

	@Override
	public int getY() {
		return this.y;
	}

	@Override
	public int getX() {
		return this.x;
	}

	@Override
	public void init(Environment screen, int x, int y) {
		this.screen = (screen);
		this.x = x;
		this.y = y;
	}

	@Override
	public void goLeft() {
		if ((this.x > 0) && getEnvi().getCellNature(this.x - 1, this.y).isFree()
				&& containsNoneOrTreasure(this.x - 1, this.y)
				&& ((!getEnvi().getCellNature(getX(), getY() - 1).isFree()
						|| (getEnvi().getCellNature(getX(), getY() - 1) == Cell.LAD))
						|| ((getEnvi().getCellNature(getX(), getY()) == Cell.LAD)
								|| (getEnvi().getCellNature(getX(), getY()) == Cell.HDR)))) {
			this.x -= 1;
		}
	}

	@Override
	public void goRight() {
		if ((this.x < this.screen.getWidth()) && getEnvi().getCellNature(this.x + 1, this.y).isFree()
				&& containsNoneOrTreasure(this.x + 1, this.y)
				&& ((!getEnvi().getCellNature(getX(), getY() - 1).isFree()
						|| (getEnvi().getCellNature(getX(), getY() - 1) == Cell.LAD))
						|| ((getEnvi().getCellNature(getX(), getY()) == Cell.LAD)
								|| (getEnvi().getCellNature(getX(), getY()) == Cell.HDR)))) {
			this.x += 1;
		}
	}

	@Override
	public void goUp() {
		if ((getEnvi().getCellNature(this.x, this.y) == Cell.LAD) && (this.y < this.screen.getHeight())
				&& getEnvi().getCellNature(this.x, this.y + 1).isFree() && containsNoneOrTreasure(this.x, this.y + 1)) {
			this.y += 1;
		}
	}

	@Override
	public void goDown() {
		if (((getEnvi().getCellNature(this.x, this.y) == Cell.LAD)
				|| (getEnvi().getCellNature(this.x, this.y - 1) == Cell.LAD)) && (this.y > 0)
				&& getEnvi().getCellNature(this.x, this.y - 1).isFree() && containsNoneOrTreasure(this.x, this.y - 1)) {
			this.y -= 1;
		}
	}

	@Override
	public boolean containsNoneOrTreasure(int x, int y) {
		if (getEnvi().getCellContent(x, y).isEmpty()) {
			return true;
		}

		for (Object o : getEnvi().getCellContent(x, y)) {
			if (!(o instanceof ItemImpl)) {
				return false;
			}
		}
		return true;
	}
}

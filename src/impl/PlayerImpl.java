package impl;

import interfaces.Engine;
import interfaces.Environment;
import interfaces.Player;
import screen.Cell;

public class PlayerImpl extends CharacterImpl implements Player {

	private final Engine engine;
	private int score = 0;

	public PlayerImpl(Environment screen, int x, int y, Engine engine) {
		super(screen, x, y);
		this.engine = engine;
	}

	@Override
	public Engine getEngine() {
		return this.engine;
	}

	@Override
	public void step() {
		if (((this.screen.getCellNature(this.x, this.y) == Cell.EMP)
				|| (this.screen.getCellNature(this.x, this.y) == Cell.HOL))
				&& this.screen.getCellNature(this.x, this.y - 1).isFree()
				&& (this.screen.getCellNature(this.x, this.y - 1) != Cell.LAD)
				&& containsNoneOrTreasure(this.x, this.y - 1)) {
			setY(getY() - 1);
		}

		switch (this.engine.getNextCommand()) {
		case Up:
			goUp();
			break;
		case Right:
			goRight();
			break;
		case Left:
			goLeft();
			break;
		case Down:
			goDown();
			break;
		// on garde ?
		case Neutral:
			break;
		case DigL:
			if ((!this.screen.getCellNature(this.x, this.y - 1).isFree()
					|| (this.screen.getCellNature(this.x, this.y - 1) == Cell.LAD))
					&& this.screen.getCellNature(this.x - 1, this.y).isFree()
					&& (this.screen.getCellNature(this.x - 1, this.y - 1) == Cell.PLT)) {
				this.screen.dig(this.x - 1, this.y - 1);
			}
			break;
		case DigR:
			if ((!this.screen.getCellNature(this.x, this.y + 1).isFree()
					|| (this.screen.getCellNature(this.x, this.y - 1) == Cell.LAD))
					&& this.screen.getCellNature(this.x + 1, this.y).isFree()
					&& (this.screen.getCellNature(this.x + 1, this.y - 1) == Cell.PLT)) {
				this.screen.dig(this.x + 1, this.y - 1);
			}
			break;
		default:

		}

	}

	@Override
	public int getScore() {
		return this.score;
	}

	@Override
	public void setScore(int score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "P";
	}
}

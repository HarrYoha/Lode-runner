package impl;

import java.util.Set;

import interfaces.CharItem;
import interfaces.Character;
import interfaces.Environment;
import interfaces.Guard;
import interfaces.Move;
import screen.Cell;

public class GuardImpl extends CharacterImpl implements Guard {
	private int id;
	private Character target;
	private Move behaviour;
	private int timeInHole = 0;
	private final int LIMIT = 5;
	private int initialX;
	private int initialY;

	public GuardImpl(Environment screen, int x, int y, int id, Character target) {
		super(screen, x, y);
		this.initialX = x;
		this.initialY = y;

		this.id = id;
		this.target = target;
	}

	@Override
	public int getInitialX() {
		return this.initialX;
	}

	@Override
	public int getInitialY() {
		return this.initialY;
	}

	@Override
	public int getId() {
		return this.id;
	}

	@Override
	public Character getTarget() {
		return this.target;
	}

	@Override
	public int getTimeInHole() {
		return this.timeInHole;
	}

	// TODO
	@Override
	public Move getBehaviour() {
		if (this.screen.getCellNature(this.x, this.y) == Cell.LAD) {

			// 3eme point des regles
			if (!this.screen.getCellNature(this.x, this.y - 1).isFree()
					|| (this.screen.getCellNature(this.x, this.y - 1).isFree()
							&& !containsNoneOrTreasure(this.x, this.y - 1))) {

				if (Math.abs(this.target.getY() - this.y) < Math.abs(this.target.getX() - this.x)) {
					if (this.target.getY() > this.y) {
						return Move.Up;
					}
					if (this.target.getY() < this.y) {
						return Move.Down;
					}
					if (this.target.getY() == this.y) {
						return Move.Neutral;
					}
				} else {
					if (this.target.getX() > this.x) {
						return Move.Right;
					}
					if (this.target.getX() < this.x) {
						return Move.Left;
					}
					if (this.target.getX() == this.x) {
						return Move.Neutral;
					}
				}

			}
			// premier point des regles
			else {
				if (this.target.getY() > this.y) {
					return Move.Up;
				}
				if (this.target.getY() < this.y) {
					return Move.Down;
				}
				if (this.target.getY() == this.y) {
					return Move.Neutral;
				}
			}
		}
		// deuxieme point des regles
		if ((this.screen.getCellNature(this.x, this.y) == Cell.HOL)
				|| (this.screen.getCellNature(this.x, this.y) == Cell.HDR)
				|| !this.screen.getCellNature(this.x, this.y - 1).isFree()
				|| (this.screen.getCellNature(this.x, this.y - 1).isFree()
						&& !containsNoneOrTreasure(this.x, this.y - 1))) {
			if (this.target.getX() < this.x) {
				return Move.Left;
			}

			if (this.target.getX() > this.x) {
				return Move.Right;
			}

			if (this.target.getX() == this.x) {
				return Move.Neutral;
			}
		}

		// a changer
		return Move.Neutral;

	}

	@Override
	public boolean climbLeft() {
		if (this.screen.getCellNature(this.x - 1, this.y + 1).isFree()
				&& (!this.screen.getCellNature(this.x - 1, this.y).isFree()
						|| (this.screen.getCellNature(this.x + 1, this.y) == Cell.LAD))) {
			this.x -= 1;
			this.y += 1;
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean climbRight() {
		if (this.screen.getCellNature(this.x + 1, this.y + 1).isFree()
				&& (!this.screen.getCellNature(this.x + 1, this.y).isFree()
						|| (this.screen.getCellNature(this.x + 1, this.y) == Cell.LAD))) {
			this.x += 1;
			this.y += 1;
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void step() {
		if ((this.screen.getCellNature(this.x, this.y) == Cell.EMP)
				&& this.screen.getCellNature(this.x, this.y - 1).isFree()
				&& (this.screen.getCellNature(this.x, this.y - 1) != Cell.LAD)
				&& containsNoneOrTreasure(this.x, this.y - 1)) {
			setY(getY() - 1);
		} else {
			if (this.screen.getCellNature(this.x, this.y) == Cell.HOL) {
				if (this.timeInHole < this.LIMIT) {
					this.timeInHole++;
				}

				if (this.timeInHole == this.LIMIT) {
					if (getBehaviour() == Move.Left) {
						if (!climbLeft()) {
							climbRight();
						}
					}

					if (getBehaviour() == Move.Right) {
						if (!climbRight()) {
							climbLeft();
						}
					}

				}
			} else {
				if (getBehaviour() == Move.Right) {
					goRight();
				}

				if (getBehaviour() == Move.Left) {
					goLeft();
				}

				if (getBehaviour() == Move.Down) {
					goDown();
				}

				if (getBehaviour() == Move.Up) {
					goUp();
				}

			}
		}
	}

	@Override
	public boolean willFall() {
		Cell natureDown = getEnvi().getCellNature(this.x, this.y - 1);
		Cell natureCurrent = getEnvi().getCellNature(this.x, this.y);
		Set<CharItem> charitems = getEnvi().getCellContent(this.x, this.y);
		boolean containsCharacter = false;
		for (CharItem charItem : charitems) {
			if (charItem instanceof Character) {
				containsCharacter = true;
				break;
			}
		}
		return ((natureDown == Cell.HOL) || (natureDown == Cell.EMP)) && !containsCharacter
				&& !((natureCurrent == Cell.LAD) || (natureCurrent != Cell.HDR));
	}

	@Override
	public boolean InHoleWithinLimit() {
		return (getEnvi().getCellNature(this.x, this.y) == Cell.HOL) && (getTimeInHole() < this.LIMIT);
	}

	@Override
	public boolean OutOfHole() {
		return (getEnvi().getCellNature(this.x, this.y) == Cell.HOL) && (getTimeInHole() == this.LIMIT);
	}

	@Override
	public String toString() {
		return "G";
	}
}

package contracts;

import decorators.ScreenDecorator;
import exceptions.PostconditionException;
import exceptions.PreconditionException;
import interfaces.Screen;
import screen.Cell;

public class ScreenContract extends ScreenDecorator {

	public ScreenContract(Screen delegate) {
		super(delegate);
	}

	public void checkInvariant() {
		// Pas d'invariant
	}

	@Override
	public int getHeight() {
		checkInvariant();
		return super.getHeight();
	}

	@Override
	public int getWidth() {
		checkInvariant();
		return super.getWidth();
	}

	@Override
	public Cell getCellNature(int x, int y) {
		// \pre 0 <= x < getWidht() && 0 <= y < getHeight()
		if (!((0 <= x) && (x < getWidth()) && (0 <= y) && (y < getHeight()))) {
			throw new PreconditionException("pre 0 < x < getWidht() && 0 < y < getHeight()");
		}
		checkInvariant();
		return super.getCellNature(x, y);
	}

	@Override
	public void init(int x, int y) {
		// \pre 0 < x && 0 < y
		if (!((0 < x) && (0 < y))) {
			throw new PreconditionException("0 < x && 0 < y");
		}

		super.init(x, y);

		checkInvariant();

		// \post getHeight() == y </br>
		if (!(getHeight() == y)) {
			throw new PostconditionException("getHeight() == y");
		}

		// \post getWidth() == x </br>
		if (!(getWidth() == x)) {
			throw new PostconditionException("getWidth() == x");
		}

		// \post \forall (w, h) in [0;getWidth()]×[0;getHeight()], getCellNature(init(x,
		// y), w, h) = EMP
		for (int w = 0; w < getWidth(); ++w) {
			for (int h = 0; h < getHeight(); ++h) {
				if (!(getCellNature(w, h) == Cell.EMP)) {
					throw new PostconditionException("getCellNature(init(x, y), w, h) = EMP");
				}
			}
		}
	}

	@Override
	public void dig(int x, int y) {
		// \pre getCellNature(x, y) = PLT
		if (!(getCellNature(x, y) == Cell.PLT)) {
			throw new PreconditionException("getCellNature(x, y) = PLT");
		}

		checkInvariant();

		// Capture cellNature_pre
		Cell[][] cellNature_pre = new Cell[getWidth()][getHeight()];
		for (int i = 0; i < getWidth(); ++i) {
			for (int j = 0; j < getHeight(); j++) {
				cellNature_pre[i][j] = getCellNature(i, j);
			}
		}

		super.dig(x, y);

		checkInvariant();

		// \post getCellNature(x, y) == HOL
		if (!(getCellNature(x, y) == Cell.HOL)) {
			throw new PostconditionException("getCellNature(x, y) == HOL");
		}

		// \post \forall (a, b) in [0 ; getWidth()]×[0 ; getHeight()] (x != a or y != b)
		// implies getCellNature(dig(x, y)), a, b) = getCellNature(a, b)@pre
		for (int a = 0; a < getWidth(); ++a) {
			for (int b = 0; b < getHeight(); b++) {
				if ((x != a) && (y != b)) {
					if (!(getCellNature(a, b) == cellNature_pre[a][b])) {
						throw new PostconditionException("getCellNature(dig(x, y)), a, b) = getCellNature(a, b)@pre");
					}
				}
			}
		}
	}

	@Override
	public void fill(int x, int y) {
		// \pre getCellNature(x, y) = HOL
		if (!(getCellNature(x, y) == Cell.HOL)) {
			throw new PreconditionException("getCellNature(x, y) = HOL");
		}

		checkInvariant();

		// Capture cellNature_pre
		Cell[][] cellNature_pre = new Cell[getWidth()][getHeight()];
		for (int i = 0; i < getWidth(); ++i) {
			for (int j = 0; j < getHeight(); j++) {
				cellNature_pre[i][j] = getCellNature(i, j);
			}
		}

		super.fill(x, y);

		checkInvariant();

		// \post getCellNature(x, y) == PLT
		if (!(getCellNature(x, y) == Cell.PLT)) {
			throw new PostconditionException("getCellNature(x, y) == PLT");
		}

		// \post \forall (a, b) in [0 ; getWidth()]×[0 ; getHeight()] (x != a or y != b)
		// implies getCellNature(fill(x, y)), a, b) = getCellNature(a, b)@pre
		for (int a = 0; a < getWidth(); ++a) {
			for (int b = 0; b < getHeight(); b++) {
				if ((x != a) && (y != b)) {
					if (!(getCellNature(a, b) == cellNature_pre[a][b])) {
						throw new PostconditionException("getCellNature(fill(x, y)), a, b) = getCellNature(a, b)@pre");
					}
				}
			}
		}
	}

}

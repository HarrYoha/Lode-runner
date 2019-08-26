package contracts;

import decorators.EditableScreenDecorator;
import exceptions.InvariantException;
import exceptions.PostconditionException;
import exceptions.PreconditionException;
import interfaces.EditableScreen;
import screen.Cell;

public class EditableScreenContract extends EditableScreenDecorator {

	public EditableScreenContract(EditableScreen delegate) {
		super(delegate);
	}

	@Override
	public void init(int x, int y) {
		// \pre 0 < x && 0 < y
		if (!((0 < x) && (0 < y))) {
			throw new PreconditionException("0 < x && 0 < y");
		}

		this.delegate.init(x, y);

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

	// \inv playable() => \forall (a, b) in [0;getWidth()]×[0;getHeight()],
	// getCellNature(a, b) != HOL and \forall a in [0, getWidth()], getCellNature(a,
	// 0) == MTL
	// DOUTE Si on creuse un trou en partie, alors la l'invariant n'est plus
	// respecté,
	public void checkInvariant() {
		if (playable()) {
			for (int a = 0; a < getWidth(); a++) {
				for (int b = 0; b < getHeight(); b++) {
					if (!(getCellNature(a, b) != Cell.HOL)) {
						throw new InvariantException("getCellNature(a, b) != HOL");
					}
				}

				if (!(getCellNature(a, 0) == Cell.MTL)) {
					throw new InvariantException("getCellNature(a, 0) == MTL");
				}
			}
		}
	}

	@Override
	public boolean playable() {
		return super.playable();
	}

	@Override
	public Cell getCellNature(int x, int y) {
		// \pre 0 <= x < getWidht() && 0 <= y < getHeight()
		if (!((0 <= x) && (x < getWidth()) && (0 <= y) && (y < getHeight()))) {
			throw new PreconditionException("pre 0 < x < getWidht() && 0 < y < getHeight()");
		}
		return super.getCellNature(x, y);
	}

	@Override
	public void setNature(int x, int y, Cell type) {
		// \pre 0 < x < getWidth() && 0 < y < getHeight()
		if (!((x >= 0) && (x < getWidth()) && (y >= 0) && (y < getHeight()))) {
			throw new PreconditionException("0 < x < getWidth() && 0 < y < getHeight()");
		}

		checkInvariant();

		// Capture cells_pre
		Cell[][] cells_pre = new Cell[getWidth()][getHeight()];
		for (int i = 0; i < cells_pre.length; i++) {
			for (int j = 0; j < cells_pre[0].length; j++) {
				cells_pre[i][j] = getCellNature(i, j);
			}
		}

		this.delegate.setNature(x, y, type);

		checkInvariant();

		// \post getCellNature(x, y) = type
		if (!(getCellNature(x, y) == type)) {
			throw new PostconditionException("getNature(x, y) = type");
		}

		// \forall (a, b) in [0;getWidth()]×[0;getHeight()], (x != a or y != b) implies
		// getCellNature(setNature(x, y, type)), a, b) = getCellNature(a, b)@pre
		for (int a = 0; a < cells_pre.length; a++) {
			for (int b = 0; b < cells_pre[0].length; b++) {
				if (!(getCellNature(a, b) == getCellNature(a, b))) {
					throw new PostconditionException("getCellNature(a, b) = getCellNature(a, b)@pre");
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

		this.delegate.dig(x, y);

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

		this.delegate.fill(x, y);

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

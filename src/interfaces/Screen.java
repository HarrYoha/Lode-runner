package interfaces;

import java.util.List;

import screen.Cell;
import screen.Hole;

/**
 * Pas d'invariant
 *
 */
public interface Screen {
	int getHeight();

	int getWidth();

	List<Hole> getHoles();

	void addHole(Hole hole);

	/**
	 * \pre 0 < x < getWidht() && 0 < y < getHeight()
	 */
	Cell getCellNature(int x, int y);

	/**
	 * \pre 0 < x && 0 < y
	 *
	 * \post getHeight() == y </br>
	 * \post getWidth() == x </br>
	 * \post \forall (w, h) in [0;getWidth()]×[0;getHeight()], getCellNature(init(x,
	 * y), w, h) = EMP
	 */
	void init(int x, int y);

	/**
	 * \pre getCellNature(x, y) = PLT
	 *
	 * \post getCellNature(x, y) == HOL </br>
	 * \post \forall (a, b) in [0 ; getWidth()]×[0 ; getHeight()] (x != a or y != b)
	 * implies getCellNature(dig(x, y)), a, b) = getCellNature(a, b)@pre
	 */
	void dig(int x, int y);

	/**
	 * \pre getCellNature(x, y) = HOL
	 *
	 * \post getCellNature(x, y) == PLT </br>
	 * \post \forall (a, b) in [0;getWidth()]×[0;getHeight()], (x != a or y != b)
	 * implies getCellNature(fill(x, y)), a, b) = getCellNature(a, b)@pre
	 */
	void fill(int x, int y);

}

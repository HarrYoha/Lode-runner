package interfaces;

import screen.Cell;

/**
 * \inv playable() => \forall (a, b) in [0;getWidth()]×[0;getHeight()],
 * getCellNature(a, b) != HOL and \forall x in [0, getWidth()], getCellNature(x,
 * 0) == MTL
 */
public interface EditableScreen extends Screen {

	boolean playable();

	/**
	 * \pre 0 < x < getWidth() && 0 < y < getHeight() </br>
	 * \post getNature(x, y) = type </br>
	 * \post \forall (a, b) in [0;getWidth()]×[0;getHeight()], (x != a or y != b)
	 * implies getCellNature(setNature(x, y, type)), a, b) = getCellNature(a, b)@pre
	 */
	void setNature(int x, int y, Cell type);

	void setPlayable();
}

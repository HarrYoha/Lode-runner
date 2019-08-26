package interfaces;

import java.util.List;
import java.util.Set;

import screen.Cell;

/**
 * \inv \forall (x, y) in [0;getWidth()[×[0;getHeight()[, \forall Character c1,
 * c2 in getCellContent(x,y)2, c1 = c2</br>
 * \inv \forall (x, y) in [0;getWidth()[× [0;getHeight()[, getCellNature(x,y) in
 * {MTL, PLR} => CellContent(x,y) = {}</br>
 * \inv \forall (x, y) in [0;getWidth()[×[0;getHeight()[, exists Treasure t in
 * getCellContent(x,y) implies and getCellNature(x,y-1) in {PLT, MTL})
 */

public interface Environment extends Screen {

	/**
	 * \pre 0 ≤ y < getHeight() and 0 ≤ x < getWidth()
	 */
	Set<CharItem> getCellContent(int x, int y);

	/**
	 * \pre 0 ≤ charItem.getX() ≤ getWidth() and 0 ≤ charItem.getY() ≤ getHeight()
	 * \post addContent(charitem) implies charitem in getCharItem()
	 */
	void addContent(CharItem charItem);

	List<CharItem> getCharItemList();

	@Override
	Cell getCellNature(int x, int y);

	@Override
	int getHeight();

	@Override
	int getWidth();

	@Override
	void init(int x, int y);

	@Override
	void dig(int x, int y);

	@Override
	void fill(int x, int y);

	void setNature(int x, int y, Cell cell);

	boolean isPlayable();
}

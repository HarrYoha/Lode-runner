package impl;

import interfaces.EditableScreen;
import screen.Cell;

public class EditableScreenImpl extends ScreenImpl implements EditableScreen {

	public EditableScreenImpl() {
		super();
	}

	@Override
	public boolean playable() {
		for (int i = 0; i < getWidth(); i++) {
			for (int j = 0; j < getHeight(); j++) {
				if ((getCellNature(i, j) == Cell.HOL) || (getCellNature(i, 0) != Cell.MTL)) {
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public void setPlayable() {
		for (int j = 0; j < getWidth(); j++) {
			setNature(j, 0, Cell.MTL);
		}
	}

	@Override
	public void setNature(int x, int y, Cell type) {
		this.screen[x][y] = type;
	}
}
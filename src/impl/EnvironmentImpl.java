package impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import interfaces.CharItem;
import interfaces.EditableScreen;
import interfaces.Environment;
import screen.Cell;
import screen.Hole;

public class EnvironmentImpl implements Environment {

	private EditableScreen screen;
	private List<CharItem> charItemList;

	public EnvironmentImpl(int x, int y) {
		init(x, y);
	}

	@Override
	public int getHeight() {
		return this.screen.getHeight();
	}

	@Override
	public int getWidth() {
		return this.screen.getWidth();
	}

	@Override
	public Cell getCellNature(int x, int y) {
		if ((x < 0) || (x >= this.screen.getWidth()) || (y < 0) || (y >= this.screen.getHeight())) {
			return Cell.MTL;
		}
		return this.screen.getCellNature(x, y);
	}

	@Override
	public void init(int x, int y) {
		this.charItemList = new ArrayList<>();
		this.screen = new EditableScreenImpl();
		this.screen.init(x, y);
		this.screen.setPlayable();
	}

	@Override
	public Set<CharItem> getCellContent(int x, int y) {
		Set<CharItem> retour = new HashSet<>();
		for (CharItem charItem : this.charItemList) {
			if ((charItem.getX() == x) && (charItem.getY() == y)) {
				retour.add(charItem);
			}
		}
		return retour;
	}

	@Override
	public void dig(int x, int y) {
		this.screen.dig(x, y);
	}

	@Override
	public void fill(int x, int y) {
		this.screen.fill(x, y);
	}

	@Override
	public void addContent(CharItem charItem) {
		this.charItemList.add(charItem);
	}

	@Override
	public List<CharItem> getCharItemList() {
		return this.charItemList;
	}

	@Override
	public List<Hole> getHoles() {
		return this.screen.getHoles();
	}

	@Override
	public void addHole(Hole hole) {
		this.screen.addHole(hole);
	}

	@Override
	public void setNature(int x, int y, Cell type) {
		this.screen.setNature(x, y, type);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int y = getHeight() - 1; y >= 0; --y) {
			for (int x = 0; x < getWidth(); ++x) {
				if (!getCellContent(x, y).isEmpty()) {
					sb.append(getCellContent(x, y).iterator().next().toString());
				} else {
					sb.append(getCellNature(x, y).toString());
				}
			}
			sb.append("\n");
		}
		return sb.toString();
	}

	@Override
	public boolean isPlayable() {
		return this.screen.playable();
	}
}

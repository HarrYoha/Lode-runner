package impl;

import java.util.ArrayList;
import java.util.List;

import interfaces.Screen;
import screen.Cell;
import screen.Hole;

public class ScreenImpl implements Screen {
	protected int width;
	protected int height;
	protected Cell[][] screen;
	protected List<Hole> holes;

	public ScreenImpl() {
	}

	@Override
	public int getHeight() {
		return this.height;
	}

	@Override
	public int getWidth() {
		return this.width;
	}

	@Override
	public List<Hole> getHoles() {
		return this.holes;
	}

	@Override
	public void addHole(Hole hole) {
		this.holes.add(hole);
	}

	@Override
	public Cell getCellNature(int x, int y) {
		if ((x < 0) || (x >= getWidth()) || (y < 0) || (y >= getHeight())) {
			return Cell.MTL;
		}
		return this.screen[x][y];
	}

	@Override
	public void init(int w, int h) {
		this.width = w;
		this.height = h;
		this.screen = new Cell[this.width][this.height];
		for (int i = 0; i < getWidth(); i++) {
			for (int j = 0; j < getHeight(); j++) {
				this.screen[i][j] = Cell.EMP;
			}
		}
		this.holes = new ArrayList<>();
	}

	@Override
	public void dig(int x, int y) {
		this.screen[x][y] = Cell.HOL;
		addHole(new Hole(x, y));
	}

	@Override
	public void fill(int x, int y) {
		this.screen[x][y] = Cell.PLT;
	}

}
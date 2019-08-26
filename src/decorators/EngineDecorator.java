package decorators;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import interfaces.Command;
import interfaces.Engine;
import interfaces.Environment;
import interfaces.Guard;
import interfaces.Item;
import interfaces.Player;
import interfaces.Status;

public class EngineDecorator implements Engine {
	@Override
	public void setCommand(Command command) {
		this.delegate.setCommand(command);
	}

	private final Engine delegate;

	public EngineDecorator(Engine delegate) {
		this.delegate = delegate;
	}

	@Override
	public Environment getEnvi() {
		return this.delegate.getEnvi();
	}

	@Override
	public int getTIME_HOLE() {
		return this.delegate.getTIME_HOLE();
	}

	@Override
	public Player getPlayer() {
		return this.delegate.getPlayer();
	}

	@Override
	public void init(Environment envi, Point coordPlayer, List<Point> coordsGuards, List<Point> coordsTreasures) {
		this.delegate.init(envi, coordPlayer, coordsGuards, coordsTreasures);
	}

	@Override
	public ArrayList<Guard> getGuards() {
		return this.delegate.getGuards();
	}

	@Override
	public ArrayList<Item> getTreasures() {
		return this.delegate.getTreasures();
	}

	@Override
	public Status getStatus() {
		return this.delegate.getStatus();
	}

	@Override
	public Command getNextCommand() {
		return this.delegate.getNextCommand();
	}

	@Override
	public void step() {
		this.delegate.step();
	}

	@Override
	public boolean checkWin() {
		return this.delegate.checkWin();
	}

	@Override
	public boolean checkLoose() {
		return this.delegate.checkLoose();
	}
}

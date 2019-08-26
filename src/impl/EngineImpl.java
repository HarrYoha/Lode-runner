package impl;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import interfaces.CharItem;
import interfaces.Command;
import interfaces.Engine;
import interfaces.Environment;
import interfaces.Guard;
import interfaces.Item;
import interfaces.ItemType;
import interfaces.Player;
import interfaces.Status;
import screen.Cell;
import screen.Hole;

public class EngineImpl implements Engine {

	private Environment envi;
	private Player player;
	private ArrayList<Guard> guards;
	private ArrayList<Item> treasures;
	private Status status;
	private Command nextCommand;
	static int cptTreasure = 0;
	static int cptGuard = 0;
	private final int TIME_HOLE = 15;

	public EngineImpl(Environment envi, Point coordPlayer, List<Point> coordsGuards, List<Point> coordsTreasures) {
		init(envi, coordPlayer, coordsGuards, coordsTreasures);
	}

	@Override
	public void init(Environment envi, Point coordPlayer, List<Point> coordsGuards, List<Point> coordsTreasures) {
		this.envi = envi;
		this.player = new PlayerImpl(envi, coordPlayer.x, coordPlayer.y, this);

		this.treasures = new ArrayList<>();
		this.guards = new ArrayList<>();

		this.player.setX(coordPlayer.x);
		this.player.setY(coordPlayer.y);
		envi.addContent(this.player);
		for (Point p : coordsGuards) {
			Guard guard = new GuardImpl(envi, p.x, p.y, cptGuard++, this.player);
			this.guards.add(guard);
			envi.addContent(guard);
		}
		for (Point t : coordsTreasures) {
			Item item = new ItemImpl(cptTreasure++, ItemType.Treasure, t.x, t.y);
			this.treasures.add(item);
			envi.addContent(item);
		}
	}

	@Override
	public Environment getEnvi() {
		return this.envi;
	}

	@Override
	public Player getPlayer() {
		return this.player;
	}

	@Override
	public ArrayList<Guard> getGuards() {
		return this.guards;
	}

	@Override
	public ArrayList<Item> getTreasures() {
		return this.treasures;
	}

	@Override
	public Status getStatus() {
		return this.status;
	}

	@Override
	public Command getNextCommand() {
		return this.nextCommand;
	}

	// for testing
	public void SetCommand(Command command) {
		this.nextCommand = command;
	}

	@Override
	public boolean checkWin() {
		// TODO une fonction qui arrete le jeu
		if (this.treasures.isEmpty()) {
			System.out.println("vous avez gagné");
			this.status = Status.Win;
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean checkLoose() {
		// TODO modifer charItem pour accepter que un guarde et le joueur peuvent etre
		// present dans la meme case
		// TODO une fonction qui arrete le jeu quand on perds

		Set<CharItem> res = this.envi.getCellContent(this.player.getX(), this.player.getY());
		for (Guard guard : this.guards) {
			if (res.contains(this.player) && res.contains(guard)) {
				this.status = Status.Loss;
				return true;
			}
		}
		return false;
	}

	@Override
	public void step() {

		Item item;
		for (CharItem charItem : this.envi.getCellContent(this.player.getX(), this.player.getY())) {
			if (charItem instanceof Item) {
				item = (Item) charItem;
				for (int i = 0; i < this.treasures.size(); i++) {
					if ((item.getX() == this.treasures.get(i).getX())
							&& (item.getY() == this.treasures.get(i).getY())) {
						this.envi.getCharItemList().remove(this.treasures.get(i));
						this.treasures.remove(this.treasures.get(i));
						this.player.setScore(this.player.getScore() + 1);
					}
				}
			}
		}

		checkWin();

		checkLoose();

		for (Guard guard : this.guards) {
			guard.step();
		}

		this.player.step();

		for (int i = 0; i < this.envi.getHoles().size(); i++) {
			Hole hole = this.envi.getHoles().get(i);
			hole.setT(hole.getT() + 1);
			if (hole.getT() == this.TIME_HOLE) {
				for (CharItem charItem : this.envi.getCellContent(hole.getX(), hole.getY())) {
					if (charItem instanceof Player) {
						this.status = Status.Loss;

					}
					if (charItem instanceof Guard) {
						((Guard) charItem).setX(((Guard) charItem).getInitialX());
						((Guard) charItem).setY(((Guard) charItem).getInitialY());
					}
				}
				this.envi.setNature(hole.getX(), hole.getY(), Cell.PLT);
				this.envi.getHoles().remove(hole);
			}
		}

	}

	@Override
	public void setCommand(Command command) {
		this.nextCommand = command;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append(this.envi.toString());

		return sb.toString();
	}

	@Override
	public int getTIME_HOLE() {
		return this.TIME_HOLE;
	}

}

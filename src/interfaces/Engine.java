package interfaces;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public interface Engine {
	Environment getEnvi();

	boolean checkWin();

	boolean checkLoose();

	void init(Environment envi, Point coordPlayer, java.util.List<Point> coordsGuards, List<Point> coordsTreasures);

	Player getPlayer();

	ArrayList<Guard> getGuards();

	ArrayList<Item> getTreasures();

	int getTIME_HOLE();

	Status getStatus();

	Command getNextCommand();

	void step();

	void setCommand(Command command);
}

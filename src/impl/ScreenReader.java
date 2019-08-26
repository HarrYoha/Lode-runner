package impl;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import interfaces.Engine;
import interfaces.Environment;
import interfaces.Player;
import screen.Cell;

public class ScreenReader {

	private static int nextGuardId = 0;
	private static int nextItemId = 0;

	public static Engine readScreen(String path) {
		try {
			BufferedReader in = new BufferedReader(new FileReader(new File(path)));
			String[] size = in.readLine().split(" ");
			if (size.length != 2) {
				System.err.println("Format error");
				return null;
			}
			int x = Integer.parseInt(size[0]), y = Integer.parseInt(size[1]);
			if ((x < 0) || (y < 0)) {
				System.err.println("Size error");
				return null;
			}

			Environment envi = new EnvironmentImpl(x, y);
			Point coordPlayer = null;
			List<Point> coordsGuards = new ArrayList<>();
			List<Point> coordsTreasures = new ArrayList<>();

			boolean havePlayer = false;

			for (int lineNum = y - 1; lineNum >= 0; --lineNum) {
				String line = in.readLine();
				if (line.length() != x) {
					System.err.println("Format error");
					return null;
				}

				for (int i = 0; i < x; ++i) {
					switch (line.charAt(i)) {
					case ' ':
						break;
					case '@':
						envi.setNature(i, lineNum, Cell.MTL);
						break;
					case '-':
						envi.setNature(i, lineNum, Cell.PLT);
						break;
					case '#':
						envi.setNature(i, lineNum, Cell.LAD);
						break;
					case '¨':
						envi.setNature(i, lineNum, Cell.HDR);
						break;
					case 'P':
						coordPlayer = new Point(i, lineNum);
						havePlayer = true;
						break;
					case 'G':
						coordsGuards.add(new Point(i, lineNum));
						break;
					case 'T':
						coordsTreasures.add(new Point(i, lineNum));
						break;
					default:
						;
					}
				}
			}

			Engine engine = new EngineImpl(envi, coordPlayer, coordsGuards, coordsTreasures);

			Player player = new PlayerImpl(envi, coordPlayer.x, coordPlayer.y, engine);
			// envi.addContent(player);

			// for (Point p : coordsGuards) {
			// Guard guard = new GuardImpl(envi, p.x, p.y, nextGuardId++, player);
			// envi.addContent(guard);
			//
			// }
			//
			// for (Point p : coordsTreasures) {
			// Item item = new ItemImpl(nextItemId++, ItemType.Treasure, p.x, p.y);
			// envi.addContent(item);
			// }

			return engine;

		} catch (NumberFormatException e) {
			System.err.println("Format error");
		} catch (FileNotFoundException e) {
			System.err.println("Fichier inexistant");
		} catch (IOException e) {
			System.err.println("IOExceotion");
		}

		return null;

	}
}

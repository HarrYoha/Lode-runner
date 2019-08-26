package app;

import java.util.Scanner;

import impl.ScreenReader;
import interfaces.Command;
import interfaces.Engine;
import interfaces.Status;

public class Appli {
	public static void main(String[] args) {

		// Environment envi = new EnvironmentImpl(4, 5);
		// for (int x = 0; x < envi.getWidth(); ++x) {
		// envi.setNature(x, 0, Cell.MTL);
		// }
		//
		// Point coordPlayer = new Point(1, 1);
		// List<Point> coordsGuards = new ArrayList<>();
		// coordsGuards.add(new Point(3, 1));
		// List<Point> coordsTreasures = new ArrayList<>();
		//
		// Engine e = new EngineImpl(envi, coordPlayer, coordsGuards, coordsTreasures);

		int level = 1;
		while (true) {

			Engine e = ScreenReader.readScreen("map/level" + level + ".txt");
			if (e == null) {
				System.err.println("Fin du jeu");
				System.exit(-1);
			}

			while (true) {
				System.out.println(e);
				Scanner scanner = new Scanner(System.in);
				String command = scanner.nextLine();
				switch (command) {
				case "d":
					e.setCommand(Command.Right);
					break;
				case "z":
					e.setCommand(Command.Up);
					break;
				case "q":
					e.setCommand(Command.Left);
					break;
				case "s":
					e.setCommand(Command.Down);
					break;
				case "a":
					e.setCommand(Command.DigL);
					break;
				case "e":
					e.setCommand(Command.DigR);
					break;
				default:
					e.setCommand(Command.Neutral);
				}
				e.step();

				System.out.println(e);

				if (e.getStatus() == Status.Win) {
					System.out.println("vous avez gagné");
					++level;
					break;
				}

				if (e.getStatus() == Status.Loss) {
					System.out.println("vous avez perdu");
					break;
				}

			}
		}

	}
}

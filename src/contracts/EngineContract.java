package contracts;

import decorators.EngineDecorator;
import exceptions.InvariantException;
import exceptions.PostconditionException;
import exceptions.PreconditionException;
import interfaces.Engine;
import interfaces.Environment;
import interfaces.Item;
import screen.Cell;

import java.awt.*;
import java.util.List;

public class EngineContract extends EngineDecorator {
    public EngineContract(Engine engine) {
        super(engine);
    }

    public void checkInvariant() {
        if (!getEnvi().isPlayable()) {
            throw new InvariantException("l'environment n'est pas jouable");
        }
    }

    public void step() {
        checkInvariant();
        boolean playerOntreasure = false;
        for (Item item : getTreasures()) {
            if(item.getX() == getPlayer().getX() && item.getY() == getPlayer().getY()){
                playerOntreasure= true;
            }
        }
        super.step();
        for (Item item : getTreasures()) {
            if (item.getX() == getPlayer().getX() && item.getY() == getPlayer().getY() && playerOntreasure) {
                throw new PostconditionException("le tresor aurait du etre pris par le joueur");
            }
        }
        checkInvariant();
    }

    @Override
    public void init(Environment envi, Point coordPlayer, java.util.List<Point> coordsGuards, List<Point> coordsTreasures) {
        if (!envi.isPlayable()) {
            throw new PreconditionException("environment doit etre jouable");
        }

        if (coordPlayer.x < 0 || coordPlayer.x > envi.getWidth() || coordPlayer.y < 0 || coordPlayer.y > envi.getHeight()) {
            throw new PreconditionException("les coordonnes du perso ne sont pas compatible avec l'environment");
        }

        if (envi.getCellNature(coordPlayer.x, coordPlayer.y) != Cell.EMP) {
            throw new PreconditionException("le perso doit etre creer sur une case vide");
        }

        for (Point guard : coordsGuards) {

            if (guard.x < 0 || guard.x > envi.getWidth() || guard.y < 0 || guard.y > envi.getHeight()) {
                throw new PreconditionException("les coordonnes du perso ne sont pas compatible avec l'environment");
            }

            if (envi.getCellNature(guard.x, guard.y) != Cell.EMP) {
                throw new PreconditionException("le perso doit etre creer sur une case vide");
            }
        }

        for (Point treasure : coordsTreasures) {
            if (treasure.x < 0 || treasure.x > envi.getWidth() || treasure.y < 0 || treasure.y > envi.getHeight()) {
                throw new PreconditionException("les coordonnes du tresor ne sont pas compatible avec l'environment");
            }
        }
        checkInvariant();
        super.init(envi, coordPlayer, coordsGuards, coordsTreasures);
        checkInvariant();
    }
}

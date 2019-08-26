package test;

import impl.EngineImpl;
import impl.EnvironmentImpl;
import impl.ItemImpl;
import interfaces.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import screen.Cell;

import java.awt.*;
import java.util.ArrayList;

public class EngineContractTest {

    Engine engine;
    Environment envi;

    @Before
    public void initialize() {
        envi = new EnvironmentImpl(200, 200);
        ArrayList<Point> guards = new ArrayList<>();
        guards.add(new Point(5, 1));
        ArrayList<Point> treasures = new ArrayList<>();
        treasures.add(new Point(1, 3));
        engine = new EngineImpl(envi, new Point(1, 1), guards, treasures);
    }


    @Test
    public void testRegenHole() {
        engine.getGuards().clear();
        engine.getEnvi().dig(2, 2);
        engine.setCommand(Command.Neutral);
        Assert.assertEquals(Cell.HOL.toString(), engine.getEnvi().getCellNature(2, 2).toString());
        for (int i = 0; i < 15; i++) {
            engine.step();
        }
        Assert.assertEquals(Cell.PLT.toString(), engine.getEnvi().getCellNature(2, 2).toString());
    }

    @Test
    public void testRegenHoleWithPlayerInIt() {
        engine.getGuards().clear();
        engine.getEnvi().dig(1, 1);
        engine.setCommand(Command.Neutral);
        Assert.assertEquals(Cell.HOL.toString(), engine.getEnvi().getCellNature(1, 1).toString());
        for (int i = 0; i < engine.getTIME_HOLE(); i++) {
            engine.step();
        }
        Assert.assertEquals(Cell.PLT.toString(), engine.getEnvi().getCellNature(1, 1).toString());
    }

    @Test
    public void testRegenHoleWithGuardInIt() {
        engine.getGuards().get(0).setX(12);
        engine.getEnvi().dig(12, 1);
        engine.setCommand(Command.Neutral);
        Assert.assertEquals(Cell.HOL.toString(), engine.getEnvi().getCellNature(12, 1).toString());
        for (int i = 0; i < engine.getTIME_HOLE(); i++) {
            engine.getGuards().get(0).setX(12);
            engine.step();
        }
        Assert.assertEquals(Cell.PLT.toString(), engine.getEnvi().getCellNature(12, 1).toString());
        Assert.assertEquals(engine.getGuards().get(0).getInitialX(), engine.getGuards().get(0).getX());
    }


    @Test
    public void testStepPlayerGoRightAndGuardGoLeft() {
        engine.setCommand(Command.Right);
        int x = engine.getPlayer().getX();
        int y = engine.getPlayer().getY();
        int guardX = engine.getGuards().get(0).getX();
        int guardY = engine.getGuards().get(0).getY();
        engine.step();
        Assert.assertEquals(x + 1, engine.getPlayer().getX());
        Assert.assertEquals(y, engine.getPlayer().getY());
        Assert.assertEquals(guardX - 1, engine.getGuards().get(0).getX());
        Assert.assertEquals(guardY, engine.getGuards().get(0).getY());
    }


    @Test
    public void testStepPlayerGoLeftInItemAndGuardGoRight() {
        engine.setCommand(Command.Left);
        engine.getPlayer().setX(8);
        int score = engine.getPlayer().getScore();
        int x = engine.getPlayer().getX();
        int y = engine.getPlayer().getY();
        engine.getGuards().get(0).setX(3);
        int guardX = engine.getGuards().get(0).getX();
        int guardY = engine.getGuards().get(0).getY();
        Item item = new ItemImpl(1, ItemType.Treasure, 7, 1);
        engine.getEnvi().addContent(item);
        engine.getTreasures().add(item);
        int sizeItem = engine.getEnvi().getCharItemList().size();
        engine.step();
        engine.step();
        Assert.assertEquals(x - 2, engine.getPlayer().getX());
        Assert.assertEquals(sizeItem - 1, engine.getEnvi().getCharItemList().size());
        Assert.assertEquals(y, engine.getPlayer().getY());
        Assert.assertEquals(guardX + 2, engine.getGuards().get(0).getX());
        Assert.assertEquals(guardY, engine.getGuards().get(0).getY());
        Assert.assertEquals(score + 1, engine.getPlayer().getScore());
    }

    @Test
    public void testStepPlayerGoUpAndGuardGoLeft() {
        engine.setCommand(Command.Up);
        int x = engine.getPlayer().getX();
        int y = engine.getPlayer().getY();
        engine.getEnvi().setNature(engine.getPlayer().getX(), engine.getPlayer().getY(), Cell.LAD);
        int guardX = engine.getGuards().get(0).getX();
        int guardY = engine.getGuards().get(0).getY();
        engine.step();
        Assert.assertEquals(x, engine.getPlayer().getX());
        Assert.assertEquals(y + 1, engine.getPlayer().getY());
        Assert.assertEquals(guardX - 1, engine.getGuards().get(0).getX());
        Assert.assertEquals(guardY, engine.getGuards().get(0).getY());
    }

    @Test
    public void testStepPlayerGoUpAndGuardInHole() {
        engine.setCommand(Command.Up);
        int x = engine.getPlayer().getX();
        int y = engine.getPlayer().getY();
        engine.getEnvi().setNature(engine.getPlayer().getX(), engine.getPlayer().getY(), Cell.LAD);
        engine.getEnvi().setNature(engine.getGuards().get(0).getX(), engine.getGuards().get(0).getY(), Cell.HOL);
        int guardX = engine.getGuards().get(0).getX();
        int guardY = engine.getGuards().get(0).getY();
        engine.step();
        Assert.assertEquals(x, engine.getPlayer().getX());
        Assert.assertEquals(y + 1, engine.getPlayer().getY());
        Assert.assertEquals(guardX, engine.getGuards().get(0).getX());
        Assert.assertEquals(guardY, engine.getGuards().get(0).getY());
    }


}

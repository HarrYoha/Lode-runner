package test;

import contracts.PlayerContract;
import impl.*;
import interfaces.Command;
import interfaces.Engine;
import interfaces.Environment;
import interfaces.ItemType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import screen.Cell;

import java.awt.*;
import java.util.ArrayList;

public class PlayerContractTest {

    PlayerContract character;
    Environment envi;
    Engine engine;

    @Before
    public void initialize() {
        envi = new EnvironmentImpl(200, 200);
        ArrayList<Point> guards = new ArrayList<>();
        ArrayList<Point> treasures = new ArrayList<>();
        engine = new EngineImpl(envi, new Point(1, 1), guards, treasures);
        character = new PlayerContract(new PlayerImpl(envi, 1, 1, engine));
    }

    @Test
    public void testFallinHOL() {
        character.setY(3);
        int x = character.getX();
        int y = character.getY();
        envi.setNature(1, 2, Cell.PLT);
        envi.setNature(2, 2, Cell.HOL);
        engine.setCommand(Command.Right);
        character.step();
        engine.setCommand(Command.Neutral);
        character.step();
        character.step();
        Assert.assertEquals(character.getX(), x + 1);
        Assert.assertEquals(character.getY(), y - 2);
    }



    @Test
    public void testDigRightFail() {
        engine.setCommand(Command.DigR);
        engine.getEnvi().setNature(character.getX(),character.getY() - 1,Cell.PLT);
        character.step();
        Assert.assertEquals(Cell.MTL, engine.getEnvi().getCellNature(character.getX() + 1, character.getY() - 1));
    }

    @Test
    public void testDigRight(){
        engine.setCommand(Command.DigR);
        character.setY(2);
        engine.getEnvi().setNature(character.getX(),character.getY() - 1,Cell.PLT);
        engine.getEnvi().setNature(character.getX()+1,character.getY() - 1,Cell.PLT);
        character.step();
        Assert.assertEquals(Cell.HOL, engine.getEnvi().getCellNature(character.getX() + 1, character.getY() - 1));
    }

    @Test
    public void testDigLeftFail() {
        engine.setCommand(Command.DigL);
        engine.getEnvi().setNature(character.getX(),character.getY() - 1,Cell.PLT);
        character.step();
        Assert.assertEquals(Cell.MTL, engine.getEnvi().getCellNature(character.getX() - 1, character.getY() - 1));
    }

    @Test
    public void testDigLeft(){
        engine.setCommand(Command.DigL);
        character.setY(2);
        engine.getEnvi().setNature(character.getX(),character.getY() - 1,Cell.PLT);
        engine.getEnvi().setNature(character.getX()-1,character.getY() - 1,Cell.PLT);
        character.step();
        Assert.assertEquals(Cell.HOL, engine.getEnvi().getCellNature(character.getX() - 1, character.getY() - 1));
    }

    @Test
    public void goRightWhileFalling() {
        character.setY(5);
        int x = character.getX();
        int y = character.getY();
        engine.setCommand(Command.Right);
        character.step();
        Assert.assertEquals(x, character.getX());
        Assert.assertEquals(y - 1, character.getY());
    }

    /////////////////////////////////////
    //CC de characterTest j'ai pas reussi a faire de l'heritage avec JUNIT
    @Test
    public void testGoRight() {
        int y = character.getY();
        engine.setCommand(Command.Right);
        character.step();
        Assert.assertEquals(y, character.getY());
    }

    @Test
    public void testGoRightWithItem() {
        int x = character.getX();
        int y = character.getY();
        envi.addContent(new ItemImpl(1, ItemType.Treasure, 2, 1));
        engine.setCommand(Command.Right);
        character.step();
        Assert.assertEquals(x + 1, character.getX());
        Assert.assertEquals(y, character.getY());
    }

    @Test
    public void testGoRightWithChar() {
        int x = character.getX();
        int y = character.getY();
        envi.addContent(new CharacterImpl(envi, 2, 1));
        engine.setCommand(Command.Right);
        character.step();
        Assert.assertEquals(x, character.getX());
        Assert.assertEquals(y, character.getY());
    }


    @Test
    public void testGoRightWithHDRandFall() {
        character.setY(2);
        int x = character.getX();
        int y = character.getY();
        envi.setNature(character.getX(), character.getY(), Cell.HDR);
        System.out.println("x " + character.getX() + " y " + character.getY());
        engine.setCommand(Command.Right);
        character.step();
        engine.setCommand(Command.Neutral);
        character.step();
        Assert.assertEquals(x + 1, character.getX());
        Assert.assertEquals(y - 1, character.getY());
    }


    @Test
    public void testGoRightWithHDR() {
        int x = character.getX();
        int y = character.getY();
        envi.setNature(character.getX(), character.getY(), Cell.HDR);
        engine.setCommand(Command.Right);
        character.step();
        Assert.assertEquals(x + 1, character.getX());
        Assert.assertEquals(y, character.getY());
    }

    @Test
    public void testGoRightWithLAD() {
        int x = character.getX();
        int y = character.getY();
        envi.setNature(character.getX(), character.getY(), Cell.LAD);
        engine.setCommand(Command.Right);
        character.step();
        Assert.assertEquals(x + 1, character.getX());
        Assert.assertEquals(y, character.getY());
    }

    @Test
    public void testGoRightWithLADandFall() {
        character.setY(2);
        int x = character.getX();
        int y = character.getY();
        envi.setNature(character.getX(), character.getY(), Cell.LAD);
        engine.setCommand(Command.Right);
        character.step();
        engine.setCommand(Command.Neutral);
        character.step();
        Assert.assertEquals(x + 1, character.getX());
        Assert.assertEquals(y - 1, character.getY());
    }

    @Test
    public void testGoLeft() {
        int y = character.getY();
        engine.setCommand(Command.Left);
        character.step();
        Assert.assertEquals(y, character.getY());
    }

    @Test
    public void testGoLeftWithChar() {
        int x = character.getX();
        int y = character.getY();
        envi.addContent(new CharacterImpl(envi, 0, 1));
        engine.setCommand(Command.Left);
        character.step();
        Assert.assertEquals(x, character.getX());
        Assert.assertEquals(y, character.getY());
    }

    @Test
    public void testGoLeftWithItem() {
        int x = character.getX();
        int y = character.getY();
        envi.addContent(new ItemImpl(1, ItemType.Treasure, 0, 1));
        engine.setCommand(Command.Left);
        character.step();
        Assert.assertEquals(x - 1, character.getX());
        Assert.assertEquals(y, character.getY());
    }

    @Test
    public void testGoLeftWithHDR() {
        int x = character.getX();
        int y = character.getY();
        envi.setNature(character.getX(), character.getY(), Cell.HDR);
        engine.setCommand(Command.Left);
        character.step();
        Assert.assertEquals(x - 1, character.getX());
        Assert.assertEquals(y, character.getY());
    }


    @Test
    public void testGoLeftWithHDRandFall() {
        character.setY(2);
        int x = character.getX();
        int y = character.getY();
        envi.setNature(character.getX(), character.getY(), Cell.HDR);
        engine.setCommand(Command.Left);
        character.step();
        engine.setCommand(Command.Neutral);
        character.step();
        Assert.assertEquals(x - 1, character.getX());
        Assert.assertEquals(y - 1, character.getY());
    }


    @Test
    public void testGoLeftWithLAD() {
        int x = character.getX();
        int y = character.getY();
        envi.setNature(character.getX(), character.getY(), Cell.LAD);
        engine.setCommand(Command.Left);
        character.step();
        Assert.assertEquals(x - 1, character.getX());
        Assert.assertEquals(y, character.getY());
    }

    @Test
    public void testGoLeftWithLADandFall() {
        character.setY(2);
        int x = character.getX();
        int y = character.getY();
        envi.setNature(character.getX(), character.getY(), Cell.LAD);
        engine.setCommand(Command.Left);
        character.step();
        engine.setCommand(Command.Neutral);
        character.step();
        Assert.assertEquals(x - 1, character.getX());
        Assert.assertEquals(y - 1, character.getY());
    }

    @Test
    public void testGoDown() {
        int x = character.getX();
        engine.setCommand(Command.Down);
        character.step();
        Assert.assertEquals(x, character.getX());
    }

    @Test
    public void testGoDownWithChar() {
        int x = character.getX();
        envi.setNature(character.getX(), character.getY(), Cell.LAD);
        character.setY(2);
        int y = character.getY();
        envi.addContent(new CharacterImpl(envi, 1, 1));
        engine.setCommand(Command.Down);
        character.step();
        Assert.assertEquals(y, character.getY());
        Assert.assertEquals(x, character.getX());
    }

    @Test
    public void testGoDownWithItem() {
        int x = character.getX();
        character.setY(3);
        envi.setNature(character.getX(), character.getY() - 1, Cell.LAD);
        int y = character.getY();
        envi.addContent(new ItemImpl(1, ItemType.Treasure, 1, character.getY() - 1));
        engine.setCommand(Command.Down);
        character.step();
        Assert.assertEquals(x, character.getX());
        Assert.assertEquals(y - 1, character.getY());
    }

    @Test
    public void testGoUp() {
        int x = character.getX();
        int y = character.getY();
        envi.setNature(character.getX(), character.getY(), Cell.LAD);
        engine.setCommand(Command.Up);
        character.step();
        Assert.assertEquals(y + 1, character.getY());
        Assert.assertEquals(x, character.getX());
    }

    @Test
    public void testGoUpWithChar() {
        int x = character.getX();
        int y = character.getY();
        envi.setNature(character.getX(), character.getY(), Cell.LAD);
        envi.addContent(new CharacterImpl(envi, 1, 2));
        engine.setCommand(Command.Up);
        character.step();
        Assert.assertEquals(y, character.getY());
        Assert.assertEquals(x, character.getX());
    }
}

package test;

import contracts.CharacterContract;
import impl.CharacterImpl;
import impl.EnvironmentImpl;
import impl.ItemImpl;
import interfaces.Environment;
import interfaces.ItemType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import screen.Cell;

public class CharacterContractTest {

    CharacterContract character;
    Environment envi;

    @Before
    public void initialize() {
        envi = new EnvironmentImpl(200, 200);

        this.character = new CharacterContract(new CharacterImpl(envi, 1, 1));
    }

    @Test
    public void testGoRight() {
        int y = character.getY();
        character.goRight();
        Assert.assertEquals(y, character.getY());
    }



    @Test
    public void testGoRightWithItem() {
        int x = character.getX();
        int y = character.getY();
        envi.addContent(new ItemImpl(1, ItemType.Treasure, 2, 1));
        character.goRight();
        Assert.assertEquals(x + 1, character.getX());
        Assert.assertEquals(y, character.getY());
    }

    @Test
    public void testGoRightWithChar() {
        int x = character.getX();
        int y = character.getY();
        envi.addContent(new CharacterImpl(envi, 2, 1));
        character.goRight();
        Assert.assertEquals(x, character.getX());
        Assert.assertEquals(y, character.getY());
    }

    @Test
    public void testGoRightWithHDR() {
        int x = character.getX();
        int y = character.getY();
        envi.setNature(character.getX(), character.getY(), Cell.HDR);
        character.goRight();
        Assert.assertEquals(x + 1, character.getX());
        Assert.assertEquals(y, character.getY());
    }

    @Test
    public void testGoRightWithLAD() {
        int x = character.getX();
        int y = character.getY();
        envi.setNature(character.getX(), character.getY(), Cell.LAD);
        character.goRight();
        Assert.assertEquals(x + 1, character.getX());
        Assert.assertEquals(y , character.getY());
    }


    @Test
    public void testGoLeft() {
        int y = character.getY();
        character.goLeft();
        Assert.assertEquals(y, character.getY());
    }

    @Test
    public void testGoLeftWithChar() {
        int x = character.getX();
        int y = character.getY();
        envi.addContent(new CharacterImpl(envi, 0, 1));
        character.goLeft();
        Assert.assertEquals(x, character.getX());
        Assert.assertEquals(y, character.getY());
    }

    @Test
    public void testGoLeftWithItem() {
        int x = character.getX();
        int y = character.getY();
        envi.addContent(new ItemImpl(1, ItemType.Treasure, 0, 1));
        character.goLeft();
        Assert.assertEquals(x - 1, character.getX());
        Assert.assertEquals(y, character.getY());
    }

    @Test
    public void testGoLeftWithHDR() {
        int x = character.getX();
        int y = character.getY();
        envi.setNature(character.getX(), character.getY(), Cell.HDR);
        character.goLeft();
        Assert.assertEquals(x - 1, character.getX());
        Assert.assertEquals(y, character.getY());
    }


  /*  @Test
    public void testGoLeftWithHDRandFall() {
        character.setY(2);
        int x = character.getX();
        int y = character.getY();
        envi.setNature(character.getX(), character.getY(), Cell.HDR);
        character.goLeft();
        Assert.assertEquals(x - 1, character.getX());
        Assert.assertEquals(y - 1, character.getY());
    }*/

    @Test
    public void testGoLeftWithLAD() {
        int x = character.getX();
        int y = character.getY();
        envi.setNature(character.getX(), character.getY(), Cell.LAD);
        character.goLeft();
        Assert.assertEquals(x - 1, character.getX());
        Assert.assertEquals(y, character.getY());
    }


    @Test
    public void testGoDown() {
        int x = character.getX();
        character.goDown();
        Assert.assertEquals(x, character.getX());
    }

    @Test
    public void testGoDownWithChar() {
        int x = character.getX();
        envi.setNature(character.getX(), character.getY(), Cell.LAD);
        character.setY(2);
        int y = character.getY();
        envi.addContent(new CharacterImpl(envi, 1, 1));
        character.goDown();
        Assert.assertEquals(y, character.getY());
        Assert.assertEquals(x, character.getX());
    }

    @Test
    public void testGoDownWithItem() {
        int x = character.getX();
        envi.setNature(character.getX(), character.getY(), Cell.LAD);
        character.setY(2);
        int y = character.getY();
        envi.addContent(new ItemImpl(1, ItemType.Treasure, 1, 1));
        character.goDown();
        Assert.assertEquals(y - 1, character.getY());
        Assert.assertEquals(x, character.getX());
    }

    @Test
    public void testGoUp() {
        int x = character.getX();
        int y = character.getY();
        envi.setNature(character.getX(), character.getY(), Cell.LAD);
        character.goUp();
        Assert.assertEquals(y + 1, character.getY());
        Assert.assertEquals(x, character.getX());
    }

    @Test
    public void testGoUpWithChar() {
        int x = character.getX();
        int y = character.getY();
        envi.setNature(character.getX(), character.getY(), Cell.LAD);
        envi.addContent(new CharacterImpl(envi, 1, 2));
        character.goUp();
        Assert.assertEquals(y, character.getY());
        Assert.assertEquals(x, character.getX());
    }
}

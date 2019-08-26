package decorators;

import interfaces.Engine;
import interfaces.Environment;
import interfaces.Player;

public class PlayerDecorator implements Player {


    protected final Player deleguate;

    public PlayerDecorator(Player deleguate) {
        this.deleguate = deleguate;
    }

    @Override
    public Engine getEngine() {
        return deleguate.getEngine();
    }

    @Override
    public void step() {
        deleguate.step();
    }

    @Override
    public Environment getEnvi() {
        return deleguate.getEnvi();
    }

    @Override
    public void setX(int x) {
        deleguate.setX(x);
    }

    @Override
    public void setY(int y) {
        deleguate.setY(y);
    }

    @Override
    public void init(Environment screen, int x, int y) {
        deleguate.init(screen, x, y);
    }

    @Override
    public void goLeft() {
        deleguate.goLeft();
    }

    @Override
    public void goRight() {
        deleguate.goRight();
    }

    @Override
    public void goUp() {
        deleguate.goUp();
    }

    @Override
    public void goDown() {
        deleguate.goDown();
    }

    @Override
    public boolean containsNoneOrTreasure(int x, int y) {
        return deleguate.containsNoneOrTreasure(x, y);
    }

    @Override
    public int getX() {
        return deleguate.getX();
    }

    @Override
    public int getY() {
        return deleguate.getY();
    }

    @Override
    public int getScore() {
        return deleguate.getScore();
    }

    @Override
    public void setScore(int number) {
        deleguate.setScore(number);
    }
}

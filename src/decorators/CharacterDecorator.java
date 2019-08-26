package decorators;

import interfaces.Character;
import interfaces.Environment;

public class CharacterDecorator implements Character {
    private final Character delegate;

    public CharacterDecorator(Character delegate) {
        this.delegate = delegate;
    }

    @Override
    public void setX(int x) {
        delegate.setX(x);
    }


    @Override
    public void setY(int y) {
        delegate.setY(y);
    }

    @Override
    public Environment getEnvi() {
        return delegate.getEnvi();
    }

    @Override
    public int getY() {
        return delegate.getY();
    }

    @Override
    public int getX() {
        return delegate.getX();
    }

    @Override
    public void init(Environment screen, int y, int x) {
        delegate.init(screen, x, y);
    }

    @Override
    public void goLeft() {
        delegate.goLeft();
    }

    @Override
    public void goRight() {
        delegate.goRight();
    }

    @Override
    public void goUp() {
        delegate.goUp();
    }

    @Override
    public void goDown() {
        delegate.goDown();
    }

    @Override
    public boolean containsNoneOrTreasure(int x, int y) {
        return delegate.containsNoneOrTreasure(x, y);
    }

}

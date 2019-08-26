package decorators;

import interfaces.EditableScreen;
import screen.Cell;

public class EditableScreenDecorator extends ScreenDecorator implements EditableScreen {
    protected final EditableScreen delegate;

    public EditableScreenDecorator(EditableScreen delegate) {
        super(delegate);
        this.delegate = delegate;
    }

    @Override
    public void init(int x, int y) {
        this.delegate.init(x, y);
    }

    @Override
    public boolean playable() {
        return this.delegate.playable();
    }

    @Override
    public void setNature(int x, int y, Cell type) {
        this.delegate.setNature(x, y, type);
    }

    @Override
    public void setPlayable() { this.delegate.setPlayable(); }
}

package contracts;

import java.util.Set;

import decorators.EnvironmentDecorator;
import exceptions.InvariantException;
import exceptions.PostconditionException;
import exceptions.PreconditionException;
import impl.ItemImpl;
import interfaces.CharItem;
import interfaces.EditableScreen;
import interfaces.Environment;
import screen.Cell;

public class EnvironmentContract extends EnvironmentDecorator {

    public EnvironmentContract(Environment delegate, EditableScreen screen) {
        super(delegate, screen);
    }

    @Override
    public int getHeight() {
        return super.getHeight();
    }

    @Override
    public int getWidth() {
        return super.getWidth();
    }

    @Override
    public Cell getCellNature(int x, int y) {
        return super.getCellNature(x, y);
    }

    @Override
    public Set<CharItem> getCellContent(int x, int y) {
        // \pre 0 ≤ y < getHeight() and 0 ≤ x < getWidth()
        if (!((0 <= x) && (x < getWidth()) && (0 <= y) && (y < getHeight()))) {
            throw new InvariantException("0 <= x < getWidth() && 0 <= y < getHeight() ");
        }

        return super.getCellContent(x, y);
    }

    public void checkInvariant() {
        // \forall (x, y) in [0;getWidth()[×[0;getHeight()[, \forall Character c1, c2 in
        // getCellContent(x,y)2, c1 = c2
        for (int x = 0; x < getWidth(); x++) {
            for (int y = 0; y < getHeight(); y++) {
                for (CharItem charItem : getCellContent(x, y)) {
                    for (CharItem charItem2 : getCellContent(x, y)) {
                        if ((charItem instanceof CharacterContract) && (charItem2 instanceof CharacterContract)) {
                            if (!(charItem == charItem2)) {
                                throw new InvariantException(
                                        "\\forall (x, y) in [0;getWidth()[×[0;getHeight()[, \\forall Character c1, c2 in getCellContent(x,y)2, c1 = c2");
                            }
                        }
                    }
                }
            }
        }

        // \forall (x, y) in [0;getWidth()[× [0;getHeight()[, getCellNature(x,y) in
        // {MTL, PLR} => CellContent(x,y) = {}
        for (int x = 0; x < getWidth(); x++) {
            for (int y = 0; y < getHeight(); y++) {
                if (!(((getCellNature(x, y) == Cell.MTL) || (getCellNature(x, y) == Cell.PLT)))
                        && !getCellContent(x, y).isEmpty()) {
                    throw new InvariantException(
                            "\\forall (x, y) in [0;getWidth()[× [0;getHeight()[, getCellNature(x,y) in {MTL, PLR} => CellContent(x,y) = {}");
                }
            }
        }

        // \forall (x, y) in [0;getWidth()[×[0;getHeight()[, exists Treasure t in
        // getCellContent(x,y) implies and getCellNature(x,y-1) in {PLT, MTL})
        for (int x = 0; x < getWidth(); x++) {
            for (int y = 0; y < getHeight(); y++) {
                for (Object o : getCellContent(x, y)) {
                    if (o instanceof ItemImpl) {
                        if (!((getCellNature(x, y) != Cell.PLT) && (getCellNature(x, y) != Cell.MTL))) {
                            throw new InvariantException(
                                    "\\forall (x, y) in [0;getWidth()[×[0;getHeight()[, exists Treasure t in getCellContent(x,y) implies and getCellNature(x,y-1) in {PLT, MTL})");
                        }
                    }
                }
            }
        }

    }

    @Override
    public void init(int x, int y) {
        // \pre 0 < x && 0 < y
        if (!((0 < x) && (0 < y))) {
            throw new PreconditionException("0 < x && 0 < y");
        }

        super.init(x, y);

        checkInvariant();

        // \post getHeight() == y </br>
        if (!(getHeight() == y)) {
            throw new PostconditionException("getHeight() == y");
        }

        // \post getWidth() == x </br>
        if (!(getWidth() == x)) {
            throw new PostconditionException("getWidth() == x");
        }

        // \post \forall (w, h) in [0;getWidth()]×[0;getHeight()], getCellNature(init(x,
        // y), w, h) = EMP
        for (int w = 0; w < getWidth(); ++w) {
            for (int h = 0; h < getHeight(); ++h) {
                if (!(getCellNature(w, h) == Cell.EMP)) {
                    throw new PostconditionException("getCellNature(init(x, y), w, h) = EMP");
                }
            }
        }

    }

    @Override
    public void dig(int x, int y) {
        // \pre getCellNature(x, y) = PLT
        if (!(getCellNature(x, y) == Cell.PLT)) {
            throw new PreconditionException("getCellNature(x, y) = PLT");
        }

        checkInvariant();

        // Capture cellNature_pre
        Cell[][] cellNature_pre = new Cell[getWidth()][getHeight()];
        for (int i = 0; i < getWidth(); ++i) {
            for (int j = 0; j < getHeight(); j++) {
                cellNature_pre[i][j] = getCellNature(i, j);
            }
        }

        super.dig(x, y);

        checkInvariant();

        // \post getCellNature(x, y) == HOL
        if (!(getCellNature(x, y) == Cell.HOL)) {
            throw new PostconditionException("getCellNature(x, y) == HOL");
        }

        // \post \forall (a, b) in [0 ; getWidth()]×[0 ; getHeight()] (x != a or y != b)
        // implies getCellNature(dig(x, y)), a, b) = getCellNature(a, b)@pre
        for (int a = 0; a < getWidth(); ++a) {
            for (int b = 0; b < getHeight(); b++) {
                if ((x != a) && (y != b)) {
                    if (!(getCellNature(a, b) == cellNature_pre[a][b])) {
                        throw new PostconditionException("getCellNature(dig(x, y)), a, b) = getCellNature(a, b)@pre");
                    }
                }
            }
        }
    }

    @Override
    public void fill(int x, int y) {
        // \pre getCellNature(x, y) = HOL
        if (!(getCellNature(x, y) == Cell.HOL)) {
            throw new PreconditionException("getCellNature(x, y) = HOL");
        }

        checkInvariant();

        // Capture cellNature_pre
        Cell[][] cellNature_pre = new Cell[getWidth()][getHeight()];
        for (int i = 0; i < getWidth(); ++i) {
            for (int j = 0; j < getHeight(); j++) {
                cellNature_pre[i][j] = getCellNature(i, j);
            }
        }

        super.fill(x, y);

        checkInvariant();

        // \post getCellNature(x, y) == PLT
        if (!(getCellNature(x, y) == Cell.PLT)) {
            throw new PostconditionException("getCellNature(x, y) == PLT");
        }

        // \post \forall (a, b) in [0 ; getWidth()]×[0 ; getHeight()] (x != a or y != b)
        // implies getCellNature(fill(x, y)), a, b) = getCellNature(a, b)@pre
        for (int a = 0; a < getWidth(); ++a) {
            for (int b = 0; b < getHeight(); b++) {
                if ((x != a) && (y != b)) {
                    if (!(getCellNature(a, b) == cellNature_pre[a][b])) {
                        throw new PostconditionException("getCellNature(fill(x, y)), a, b) = getCellNature(a, b)@pre");
                    }
                }
            }
        }
    }

    @Override
    public void addContent(CharItem charItem) {
        // \pre 0 ≤ charItem.getX() ≤ getWidth() and 0 ≤ charItem.getY() ≤ getHeight()
        if (charItem.getX() < 0 || charItem.getX() > getWidth() || charItem.getY() < 0 || charItem.getY() > getHeight()) {
            throw new PreconditionException("le charItem n'a pas de coordonnées correcte");
        }

        checkInvariant();
        super.addContent(charItem);
        checkInvariant();
        /*
        if (!getCharItemList().contains(charItem)){
            throw new PostconditionException("le charItem n'a pas été ajouté");
        }*/
    }
}

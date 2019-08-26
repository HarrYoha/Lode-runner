package contracts;

import decorators.CharacterDecorator;
import exceptions.InvariantException;
import exceptions.PostconditionException;
import exceptions.PreconditionException;
import interfaces.Character;
import interfaces.Environment;
import screen.Cell;

public class CharacterContract extends CharacterDecorator {
    public CharacterContract(Character delegate) {
        super(delegate);
    }


    //INV Environment::CellNature(getEnvi(),getX(),getY()) in {EMP, HOL, LAD, HDR}

    private void checkInvariant() {
        if (!getEnvi().getCellNature(getX(), getY()).isFree()) {
            throw new InvariantException("le personnage n'est pas sur une case libre");
        }
    }


    @Override
    public boolean containsNoneOrTreasure(int x, int y) {
        // \pre 0 < x < getEnvi().getWidht() && 0 < y < getEnvi().getHeight()
        if (x < 0 || x > getEnvi().getWidth() || y < 0 || y > getEnvi().getHeight()) {
            throw new PreconditionException("0 < x < getEnvi().getWidht() && 0 < y < getEnvi().getHeight() pas respecte ");
        }
        checkInvariant();
        return super.containsNoneOrTreasure(x, y);
    }

    @Override
    public void goLeft() {
        int y = getY();
        int x = getX();
        checkInvariant();
        super.goLeft();
        checkInvariant();


        //\post getX() = 0 => getX(goLeft()) = getX()@pre
        if (x == 0 && getX() != x) {
            throw new PostconditionException(" ne devrait pas avoir bougé si perso tout a gauche de l'ecran");
        }

        //\post Environment::CellNature(getX()-1,getY()) in {MTL, PLT} =>
        //	 * getX(GoLeft()) = getX()@pre
        if (!getEnvi().getCellNature(x - 1, y).isFree() && getX() != x) {
            throw new PostconditionException("ne doit pas se deplacer a gauche si la case a gauche n'est pas libre ");
        }

        /* \postEnvironment::CellNature(getX(),getY()) not in {LAD, HDR} and
         * Environment::CellNature(getX(),getY()-1) not in {PLT, MTL} and not exists
         * Character c in Environment::CellContent(getX(),getY()-1) => getX(GoLeft()) =
         * getX()@pre*/

        if ((getEnvi().getCellNature(x, y) != Cell.LAD && getEnvi().getCellNature(x, y) != Cell.HDR)
                && getEnvi().getCellNature(x, y - 1).isFree() && containsNoneOrTreasure(x, y - 1)) {
            if (getX() != x)
                throw new PostconditionException("le perso ne doit pas bouger s'il est en chute");
        }


        // \post exists Character c in Environment::CellContent(getX()-1,getY()) =>
        // getX(GoLeft()) = getX()@pre
        if (!containsNoneOrTreasure(x - 1, y) && getX() != x) {
            throw new PostconditionException("perso ne doit pas se deplacer a gauche si deja occupe par un autre perso");
        }


	 /* \post (getX() != 0) and Environment::CellNature(getX()-1,getY()) not in {MTL,
	  PLT} and (Environment::CellNature(getX(),getY()) in {LAD, HDR} or
                 Environment::CellNature(getX(),getY()-1) in {PLT, MTL, LAD} or exists
	  Character c in Environment::CellContent(getX(),getY()-1) ) and not (exists
                 Character c in Environment::CellContent(getX()-1,getY())) => getX(GoLeft()) =
	 getX()@pre-1*/
        if (x != 0 && getEnvi().getCellNature(x - 1, y).isFree() && ((getEnvi().getCellNature(x, y) == Cell.LAD
                || getEnvi().getCellNature(x, y) == Cell.HDR) || (!getEnvi().getCellNature(x, y - 1).isFree() ||
                getEnvi().getCellNature(x, y - 1) == Cell.LAD) ||
                !containsNoneOrTreasure(x, y - 1))
                && containsNoneOrTreasure(x - 1, y)) {
            if (getX() == x)
                throw new PostconditionException("le perso ne s'est pas deplace a gauche");

        }


    }

    @Override
    public void goRight() {
        int y = getY();
        int x = getX();
        checkInvariant();
        super.goRight();
        checkInvariant();


        //\post getX() = getEnvi().getWidth() => getX(goRight()) = getX()@pre
        if (x == getEnvi().getWidth() && getX() != x) {
            throw new PostconditionException(" ne devrait pas avoir bougé si perso tout a droite de l'ecran");
        }

        //\post Environment::CellNature(getX()+1,getY()) in {MTL, PLT} =>
        //	 * getX(GoLeft()) = getX()@pre
        if (!getEnvi().getCellNature(x + 1, y).isFree() && getX() != x) {
            throw new PostconditionException("ne doit pas se deplacer a droite si la case a droite n'est pas libre ");
        }

        /* \postEnvironment::CellNature(getX(),getY()) not in {LAD, HDR} and
         * Environment::CellNature(getX(),getY()-1) not in {PLT, MTL} and not exists
         * Character c in Environment::CellContent(getX(),getY()-1) => getX(GoLeft()) =
         * getX()@pre*/

        if ((getEnvi().getCellNature(x, y) != Cell.LAD && getEnvi().getCellNature(x, y) != Cell.HDR)
                && getEnvi().getCellNature(x, y - 1).isFree() && containsNoneOrTreasure(x, y - 1)
        ) {
            if (getX() != x)
                throw new PostconditionException("le perso ne doit pas bouger s'il est en chute");
        }


        // \post exists Character c in Environment::CellContent(getX()+1,getY()) =>
        // getX(GoLeft()) = getX()@pre
        if (!containsNoneOrTreasure(x + 1, y) && getX() != x) {
            throw new PostconditionException("perso ne doit pas se deplacer a droite si deja occupe par un autre perso");
        }


	 /* \post (getX() != getEnvi().getWidth()) and Environment::CellNature(getX()+1,getY()) not in {MTL,
	  PLT} and (Environment::CellNature(getX(),getY()) in {LAD, HDR} or
                 Environment::CellNature(getX(),getY()-1) in {PLT, MTL, LAD} or exists
	  Character c in Environment::CellContent(getX(),getY()-1) ) and not (exists
                 Character c in Environment::CellContent(getX()+1,getY())) => getX(GoLeft()) =
	 getX()@pre-1*/
        if (x != getEnvi().getWidth() && getEnvi().getCellNature(x + 1, y).isFree() && (getEnvi().getCellNature(x, y) == Cell.LAD
                || getEnvi().getCellNature(x, y) == Cell.HDR
                || (!getEnvi().getCellNature(x, y - 1).isFree()) ||
                !containsNoneOrTreasure(x, y - 1))
                && containsNoneOrTreasure(x + 1, y)) {
            if (getX() == x)
                throw new PostconditionException("le perso ne s'est pas deplace a droite");
        }


    }

    @Override
    public void goUp() {
        int y = getY();
        int x = getX();
        checkInvariant();
        super.goUp();
        checkInvariant();


        //\post getX(goUp()) = getX()@pre
        if (getX() != x) {
            throw new PostconditionException("goUp ne doit pas changer l'abscisse du perso");
        }

        //\post getY() = getEnvi().getHeight() =>
        //getY(goUp()) = getY()@pre*/
        if (y == getEnvi().getHeight() && getY() != y) {
            throw new PostconditionException("goUp ne doit pas changer l'ordonne du perso si tout en haut");
        }

          /*\post Environment::CellNature(getX(),getY()+1) in {PLT, MTL} => getY(GoUp())
          = getY()@pre*/
        if (!getEnvi().getCellNature(x, y + 1).isFree() && getY() != y) {
            throw new PostconditionException("goUp ne doit pas changer l'ordonne si la case au dessus n'est pas libre");
        }

        // A SUPPRIMER ?
          /*\post Environment::CellNature(getX(),getY()) not in {LAD, HDR} and
          Environment::CellNature(getX(),getY()+1) not in {PLT, MTL} and not exists
          Character c in Environment::CellContent(getX(),getY()-1) => getX(GoUp()) =
          getX()@pre*/
         /* if( (getEnvi().getCellNature(x,y)!=Cell.LAD ||getEnvi().getCellNature(x,y)!=Cell.HDR)
                  && getEnvi().getCellNature(x,y+1).isFree() && !(getEnvi().getCellContent(x,y-1) instanceof Character)
*/


        //  \post exists Character c in Environment::CellContent(getX(),getY()+1) =>
        //  getY(GoUp()) = getY()@pre
        if (!containsNoneOrTreasure(x, y + 1) && y != getY()) {
            throw new PostconditionException("goUp ne doit pas changer l'ordonne si perso au dessus lui");
        }

        /* \post (getY() != getEnvi().getHeight() and
          Environment::CellNature(getX(),getY()+1) not in {MTL, PLT} and
          (Environment::CellNature(getX(),getY()) in {LAD} or
          Environment::CellNature(getX(),getY()-1) in {PLT, MTL, LAD} or exists
          Character c in Environment::CellContent(getX(),getY()-1) ) and not (exists
          Character c in Environment::CellContent(getX(),getY()+1)) => getY(GoUp()) =
          getY()@pre+1*/

        if (y != getEnvi().getHeight() && getEnvi().getCellNature(x, y + 1).isFree() && (getEnvi().getCellNature(x, y) == Cell.LAD
                && !getEnvi().getCellNature(x, y - 1).isFree() ||
                getEnvi().getCellNature(x, y - 1) == Cell.LAD || !containsNoneOrTreasure(x, y - 1)
        ) && containsNoneOrTreasure(x, y + 1)) {
            if (getY() != y + 1)
                throw new PostconditionException("le perso n'a pas bouge");
        }
    }

    @Override
    public void goDown() {
        int y = getY();
        int x = getX();
        checkInvariant();
        super.goDown();
        System.out.println(" down x " + getX() + " y " + getY());
        checkInvariant();


        //\post getX(goDown()) = getX()@pre
        if (getX() != x) {
            throw new PostconditionException("goDown ne doit pas changer l'abscisse du perso");
        }

        //\post getY() = 0 =>
        //getY(goDown()) = getY()@pre*/
        if (y == 0 && getY() != y) {
            throw new PostconditionException("goDown ne doit pas changer l'ordonne du perso si tout en bas");
        }

          /*\post Environment::CellNature(getX(),getY()-1) in {PLT, MTL} => getY(GoUp())
          = getY()@pre*/
        if (!getEnvi().getCellNature(x, y - 1).isFree() && getY() != y) {
            throw new PostconditionException("goDown ne doit pas changer l'ordonne si la case au dessous n'est pas libre");
        }

        //  \post exists Character c in Environment::CellContent(getX(),getY()-1) =>
        //  getY(GoUp()) = getY()@pre
        if (!containsNoneOrTreasure(x, y - 1) && y != getY()) {
            throw new PostconditionException("goDow ne doit pas changer l'ordonne si perso au dessous lui");
        }

        /* \post (getY() != 0 and
          (Environment::CellNature(getX(),getY()) in {LAD} and
          Environment::CellNature(getX(),getY()-1) in {PLT, MTL, LAD} and not (exists
          Character c in Environment::CellContent(getX(),getY()-1)) => getY(GoDown()) =
          getY()@pre+1*/

        if (y != 0 && getEnvi().getCellNature(x, y) == Cell.LAD
                && (getEnvi().getCellNature(x, y - 1) == Cell.LAD || !getEnvi().getCellNature(x, y - 1).isFree()) &&
                containsNoneOrTreasure(x, y - 1)) {
            if (getY() == y)
                throw new PostconditionException("le perso n'a pas bouge");
        }
    }

    @Override
    public void init(Environment envi, int x, int y) {
        if (!envi.isPlayable()) {
            throw new PreconditionException("environment doit etre jouable");
        }
        if (x < 0 || x > envi.getWidth() || y < 0 || y > envi.getHeight()) {
            throw new PreconditionException("les coordonnes du perso ne sont pas compatible avec l'environment");
        }

        if (envi.getCellNature(x, y) != Cell.EMP) {
            throw new PreconditionException("le perso doit etre creer sur une case vide");
        }
        checkInvariant();

        super.init(envi, x, y);

        checkInvariant();
    }

}

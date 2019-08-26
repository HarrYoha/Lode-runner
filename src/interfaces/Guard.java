package interfaces;

/**
 * inv Environment::getCellNature(getX(),getY()) = LAD and getY() <
 * Character::getY(getTarget()) and (Environment::getCellNature(getX(),getY()-1)
 * not in {PLT, MTL } or exists Character c in
 * Environment::getCellContent(getX(),getY()-1) =>
 * Environment::getHeight(getTarget()) - getY() <
 * |Environment::getWidth(getTarget()) - getX())|) implies getBehaviour(G) = Up
 */

public interface Guard extends Character {

	int getId();

	Character getTarget();

	int getTimeInHole();

	int getInitialX();

	int getInitialY();

	boolean willFall();

	boolean InHoleWithinLimit();

	boolean OutOfHole();

	// renvoie le nb de tour passé dans le trou ? a modifier
	Move getBehaviour();

	/**
	 * \pre Environment::getCellNature(getX(),getY()) = HOL
	 *
	 * \post getX() != 0 implies getX(ClimbLeft()) = getX() and getY(ClimbLeft()) =
	 * getY()
	 *
	 * \post Screen::CellNature(getX()-1,getY() +1) in {MTL, PLT } implies
	 * getX(ClimbLeft()) = getX() and getY(ClimbLeft()) = getY()
	 *
	 * \post exists Character c in Environment::CellContent(getX()-1,getY()+1)
	 * implies getX(ClimbLeft()) = getX() and getY(ClimbLeft()) = getY()
	 *
	 * \post getX() = 0 and Screen::CellNature(getX()-1,getY()+1) not in {MTL, PLT }
	 * and not exists Character c in Environment::CellContent(getX()-1,getY()+1)
	 * implies getX(ClimbLeft()) = getX()-1 and getY(ClimbLeft()) = getY()+1
	 */
	boolean climbLeft();

	/**
	 * \pre Environment::getCellNature(getX(),getY()) = HOL
	 *
	 * \post getX() = Screen::getWidth() implies getX(ClimbRight()) = getX() and
	 * getY(ClimbRight()) = getY()
	 *
	 * \post Screen::CellNature(getX()+1,getY() +1) in {MTL, PLT } implies
	 * getX(ClimbRight()) = getX() and getY(ClimbRight()) = getY()
	 *
	 * \post exists Character c in Environment::CellContent(getX()+1,getY()+1)
	 * implies getX(ClimbLeft()) = getX() and getY(ClimbLeft()) = getY()
	 *
	 * \post getX() != Screen::getWidth() and Screen::CellNature(getX()+1,getY()+1)
	 * not in {MTL, PLT } and not exists Character c in
	 * Environment::CellContent(getX()+1,getY()+1) implies getX(ClimbLeft()) =
	 * getX()+1 and getY(ClimbLeft()) = getY()+1
	 */
	boolean climbRight();

	/**
	 * \post WillFall() and getBehaviour()==RIGHT implies getX(step()) = getX() and
	 * getY(step()) = getY() - 1 </br>
	 * \post WillFall() and getBehaviour()==LEFT implies getX(step()) = getX() and
	 * getY(step()) = getY() - 1 </br>
	 * \post WillFall() and getBehaviour()==UP implies getX(step()) = getX() and
	 * getY(step()) = getY() - 1 </br>
	 * \post WillFall and getBehaviour()==DOWN implies getX(step()) = getX() and
	 * getY(step()) = getY() - 1
	 *
	 * \post InHoleWithinLimit() and getBehaviour()==RIGHT implies getX(step()) =
	 * getX() and getY(step()) = getY() </br>
	 * \post InHoleWithinLimit() and getBehaviour()==LEFT implies getX(step()) =
	 * getX() and getY(step()) = getY() </br>
	 * \post InHoleWithinLimit() and getBehaviour()==UP implies getX(step()) =
	 * getX() and getY(step()) = getY() </br>
	 * \post InHoleWithinLimit() and getBehaviour()==DOWN implies getX(step()) =
	 * getX() and getY(step()) = getY()
	 *
	 * \post outOfHole() and getBehaviour()==RIGHT and implies getX(step()) = getX()
	 * + 1 and getY(step()) = getY() +1 </br>
	 * \post outOfHole() and getBehaviour()==LEFT implies getX(step()) = getX() - 1
	 * and getY(step()) = getY() + 1 </br>
	 * \post outOfHole() and getBehaviour()==UP implies getX(step()) = getX() and
	 * getY(step()) = getY() </br>
	 * \post outOfHole() and getBehaviour()==DOWN implies getX(step()) = getX() and
	 * getY(step()) = getY()
	 */
	void step();
}

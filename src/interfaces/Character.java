package interfaces;

/**
 * \inv Environment::CellNature(getEnvi(),getX(),getY()) in {EMP, HOL, LAD, HDR}
 *
 */
public interface Character extends CharItem {

	Environment getEnvi();

	void setX(int x);

	void setY(int y);

	/**
	 * pre Environment::CellNature(screen,x,y) = EMP
	 */
	void init(Environment screen, int x, int y);

	/**
	 * \post getY(goLeft()) = getY()@pre \post getX() = 0 => getX(goLeft()) =
	 * getX()@pre
	 *
	 * \post Environment::CellNature(getX()-1,getY()) in {MTL, PLT} =>
	 * getX(GoLeft()) = getX()@pre
	 *
	 * \post Environment::CellNature(getX(),getY()) not in {LAD, HDR} and
	 * Environment::CellNature(getX(),getY()-1) not in {PLT, MTL} and not exists
	 * Character c in Environment::CellContent(getX(),getY()-1) => getX(GoLeft()) =
	 * getX()@pre
	 *
	 * \post exists Character c in Environment::CellContent(getX()-1,getY()) =>
	 * getX(GoLeft()) = getX()@pre
	 *
	 * \post (getX() != 0) and Environment::CellNature(getX()-1,getY()) not in {MTL,
	 * PLT} and (Environment::CellNature(getX(),getY()) in {LAD, HDR} or
	 * Environment::CellNature(getX(),getY()-1) in {PLT, MTL, LAD} or exists
	 * Character c in Environment::CellContent(getX(),getY()-1) ) and not (exists
	 * Character c in Environment::CellContent(getX()-1,getY())) => getX(GoLeft()) =
	 * getX()@pre-1
	 **/
	void goLeft();

	/**
	 * \post getY(goRight()) = getY()@pre \post getX() = getEnvi().getWidth() =>
	 * getX(goRight()) = getX()@pre
	 *
	 * \post Environment::CellNature(getX()+1,getY()) in {MTL, PLT} =>
	 * getX(GoRight()) = getX()@pre
	 *
	 * \post Environment::CellNature(getX(),getY()) not in {LAD, HDR} and
	 * Environment::CellNature(getX(),getY()+1) not in {PLT, MTL} and not exists
	 * Character c in Environment::CellContent(getX(),getY()-1) => getX(GoRight()) =
	 * getX()@pre
	 *
	 * \post exists Character c in Environment::CellContent(getX()+1,getY()) =>
	 * getX(GoRight()) = getX()@pre
	 *
	 * \post (getX() != getEnvi().getWidth()) and
	 * Environment::CellNature(getX()+1,getY()) not in {MTL, PLT} and
	 * (Environment::CellNature(getX(),getY()) in {LAD, HDR} or
	 * Environment::CellNature(getX(),getY()-1) in {PLT, MTL, LAD} or exists
	 * Character c in Environment::CellContent(getX(),getY()-1) ) and not (exists
	 * Character c in Environment::CellContent(getX()+1,getY())) => getX(GoRight())
	 * = getX()@pre+1
	 *
	 **/
	void goRight();

	/**
	 * \post getX(goUp()) = getX()@pre \post getY() = getEnvi().getHeight() =>
	 * getY(goUp()) = getY()@pre
	 *
	 * \post Environment::CellNature(getX(),getY()+1) in {PLT, MTL} => getY(GoUp())
	 * = getY()@pre
	 *
	 * \post exists Character c in Environment::CellContent(getX(),getY()+1) =>
	 * getY(GoUp()) = getY()@pre
	 *
	 * \post (getY() != getEnvi().getHeight() and
	 * Environment::CellNature(getX(),getY()+1) not in {MTL, PLT} and
	 * (Environment::CellNature(getX(),getY()) in {LAD} or
	 * Environment::CellNature(getX(),getY()-1) in {PLT, MTL, LAD} or exists
	 * Character c in Environment::CellContent(getX(),getY()-1) ) and not (exists
	 * Character c in Environment::CellContent(getX(),getY()+1)) => getY(GoUp()) =
	 * getY()@pre+1
	 **/
	void goUp();

	/**
	 * \post getX(goDown()) = getX()@pre \post getY() = getEnvi().getHeight() =>
	 * getY(goDown()) = getY()@pre
	 *
	 * \post Environment::CellNature(getX(),getY()-1) in {PLT, MTL} =>
	 * getX(GoDown()) = getX()@pre
	 *
	 * \post Environment::CellNature(getX(),getY()) not in {LAD, HDR} and
	 * Environment::CellNature(getX(),getY()-1) not in {PLT, MTL} and not exists
	 * Character c in Environment::CellContent(getX(),getY()-1) => getX(GoDown()) =
	 * getX()@pre - 1
	 *
	 * \post exists Character c in Environment::CellContent(getX(),getY()-1) =>
	 * getX(GoDown()) = getX()@pre
	 *
	 * \post (getY() != 0 and Environment::CellNature(getX(),getY()-1) not in {MTL,
	 * PLT} and (Environment::CellNature(getX(),getY()) = HDR and not (exists
	 * Character c in Environment::CellContent(getX(),getY()-1)) => getY(GoDown()) =
	 * getY()@pre-1
	 **/
	void goDown();

	/**
	 * \pre 0 < x < getEnvi().getWidht() && 0 < y < getEnvi().getHeight()
	 */
	boolean containsNoneOrTreasure(int x, int y);

}

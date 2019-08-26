package interfaces;

public interface Player extends Character{
    Engine getEngine();
    void step();
    int getScore();
    void setScore(int number);
}

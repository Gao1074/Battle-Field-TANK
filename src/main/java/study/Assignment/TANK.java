package study.Assignment;

public abstract class TANK extends Entity {
    //direction
    boolean UP;
    boolean DOWN;
    boolean LEFT;
    boolean RIGHT;
    TANK(){
        size.width = 20;
        size.height = 20;
    }
}

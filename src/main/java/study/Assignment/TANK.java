package study.Assignment;

public abstract class TANK extends Entity {
    //direction
    protected boolean UP;
    protected boolean DOWN;
    protected boolean LEFT;
    protected boolean RIGHT;
    TANK(){
        size.setWidth(20);
        size.setHeight(20);
    }
}

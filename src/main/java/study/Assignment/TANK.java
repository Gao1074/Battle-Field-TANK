package study.Assignment;

import java.util.ArrayList;

public abstract class TANK extends Entity{
    //direction
    protected boolean UP;
    protected boolean DOWN;
    protected boolean LEFT;
    protected boolean RIGHT;

    TANK(GameEngine gameEngine){
        super(gameEngine);

        size.setWidth(80);
        size.setHeight(80);
    }
}

package study.Assignment;

import java.awt.*;
import java.util.ArrayList;

public abstract class TANK extends Entity{
    //direction
    protected boolean UP;
    protected boolean DOWN;
    protected boolean LEFT;
    protected boolean RIGHT;
    Image Moving = gameEngine.loadImage("Moving.png");

    TANK(GameEngine gameEngine){
        super(gameEngine);
        size.setWidth(80);
        size.setHeight(80);
    }
    public void updateTank(double dt){

    }
}

package study.Assignment;

import java.util.ArrayList;

public abstract class TANK extends Entity implements TankWeapon{
    //direction
    protected boolean UP;
    protected boolean DOWN;
    protected boolean LEFT;
    protected boolean RIGHT;
    protected ArrayList<Ammo> ammo = new ArrayList<>(99);
    TANK(){

        size.setWidth(20);
        size.setHeight(20);

    }
}

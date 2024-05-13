package study.Assignment;

import java.util.ArrayList;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class Spitfire{
    ArrayList<FireAmmo> fires = new ArrayList<>(99);
    private final double LimitDistance;

    public Spitfire(double limitDistance) {
        LimitDistance = limitDistance;
    }

    public void Fire(Entity entity , double dt){
        for (FireAmmo fire : fires) {
            if (!fire.Active) {
                fire.Active = true;
                fire.Angle = entity.Angle;
                Position temp = new Position() {
                    @Override
                    public double getX() {
                        return super.getX();
                    }
                };
                temp.setX(entity.position.getX());
                temp.setY(entity.position.getY());
                fire.setInitialPosition(temp);
                fire.position.setX(entity.position.getX());
                fire.position.setY(entity.position.getY());
                fire.velocity.setX(sin(fire.Angle) * 200);
                fire.velocity.setY(-cos(fire.Angle) * 200);
                break;
            }
        }
    }
    public void updateFire(double dt){
        TankBattle tank= new TankBattle();
        for (FireAmmo fire : fires) {
            if (tank.distance(fire.position.getX(),fire.position.getY(),fire.getInitialPosition().getX(),fire.getInitialPosition().getY()) > LimitDistance){
                fire.Active = false;
            }
        }

    }
}

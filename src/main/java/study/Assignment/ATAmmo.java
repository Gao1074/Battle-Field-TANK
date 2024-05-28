package study.Assignment;

import java.util.ArrayList;

public class ATAmmo extends Ammo{
    ATAmmo(GameEngine gameEngine) {
        super(gameEngine);
        power = 20;
        image = gameEngine.subImage(AmmoImage,70,0,38,103);
    }
    public void draw(){
        if (Active) {
            gameEngine.saveCurrentTransform();
            gameEngine.translate(position.getX(), position.getY());
            gameEngine.rotate(Angle);
            gameEngine.drawImage(image,-size.getRadius()/2, -size.getRadius()/2,size.getRadius()*1,size.getRadius()*3);
            gameEngine.restoreLastTransform();
        }
    }
    public void Damage(ArrayList<TANK> Enemies){
        for (TANK Tank : Enemies){
            if (Active) {
                if (gameEngine.distance(position.getX(), position.getY(), Tank.position.getX(), Tank.position.getY()) <= Tank.size.getWidth()) {
                    Tank.Health -= power;
                    Active = false;
                    if (Tank.Health <=0){
                        Tank.Health = 0;
                    }
                }
            }
        }
    }
}

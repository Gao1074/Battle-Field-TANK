package study.Assignment;

import java.awt.*;

public class RepairStation extends Entity{
    Image repairStation = gameEngine.loadImage("src/main/resources/Repairstation/RepairStation.png");
    public RepairStation(GameEngine gameEngine, double x, double y) {
        super(gameEngine);
        size.setHeight(75);
        size.setWidth(75);
        position.setX(x);
        position.setY(y);
    }

    public void draw(){
        gameEngine.saveCurrentTransform();
        gameEngine.translate(position.getX(), position.getY());
        gameEngine.drawImage(repairStation,-size.getWidth()/2,-size.getHeight()/2,size.getWidth(),size.getHeight());
        gameEngine.restoreLastTransform();
    }

    public double repair(TANK tank ,double score){
        if (gameEngine.distance(position.getX(), position.getY(), tank.position.getX(),tank.position.getY()) < 100 && tank.Health <= tank.FullHealth){
            if(tank.Health < tank.FullHealth){
                tank.Health += 10;
                score -= 10;
            }
            if(tank.Health >= tank.FullHealth){
                tank.Health = tank.FullHealth;
            }
            tank.isrepair = true;
        }else {
            tank.isrepair = false;
        }
        return score;
    }
}

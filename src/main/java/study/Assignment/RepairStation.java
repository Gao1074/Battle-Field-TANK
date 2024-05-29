package study.Assignment;

import java.awt.*;

public class RepairStation extends Entity{
    Image repairStation = gameEngine.loadImage("src/main/resources/Repairstation/RepairStation.png");
    public RepairStation(GameEngine gameEngine) {
        super(gameEngine);
        size.setHeight(75);
        size.setWidth(75);
    }

    public void draw(){
        gameEngine.saveCurrentTransform();
        gameEngine.translate(500, 500);
        gameEngine.drawImage(repairStation,-size.getWidth()/2,-size.getHeight()/2,size.getWidth(),size.getHeight());
        gameEngine.restoreLastTransform();
    }

    public void repair(TANK tank){
        if (gameEngine.distance(500,500, tank.position.getX(),tank.position.getY()) < 100 && tank.Health <= tank.FullHealth){
            tank.Health += 10;
            if(tank.Health >= tank.FullHealth){
                tank.Health = tank.FullHealth;
            }
            tank.isrepair = true;
        }else {
            tank.isrepair = false;
        }

    }
}

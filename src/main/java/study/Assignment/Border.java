package study.Assignment;

import java.awt.*;

public final class Border {


    public Border(Entity entity){

        double width = 1500;
        if (entity.position.getX() >= width - entity.size.getWidth() /2){
            entity.position.setX(width - entity.size.getWidth() /2);
        }
        double height = 1000;
        if (entity.position.getY() >= height - entity.size.getHeight() /2){
            entity.position.setY( height - entity.size.getHeight() /2);
        }
        if (entity.position.getX() <= entity.size.getWidth() /2){
            entity.position.setX(entity.size.getWidth() /2);
        }
        if (entity.position.getY() <= entity.size.getHeight() /2){
            entity.position.setY(entity.size.getHeight() /2);
        }
    }
}

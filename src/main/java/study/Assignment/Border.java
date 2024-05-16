package study.Assignment;

import java.awt.*;

public final class Border {
    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

    public Border(Entity entity){

        double width = dim.width;
        if (entity.position.getX() >= width - entity.size.getWidth() /2){
            entity.position.setX(width - entity.size.getWidth() /2);
        }
        double height = dim.height;
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

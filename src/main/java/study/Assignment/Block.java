package study.Assignment;

public class Block extends Entity{
    private boolean destroyable;
    Block(Position position, Size size , boolean destroyable){
        this.position = position;
        this.size = size;
        this.destroyable = destroyable;
    }
    Block(Size size , boolean destroyable){
        this.size = size;
        this.destroyable = destroyable;
    }
    Block(Position position , boolean destroyable){
        this.position = position;
        this.destroyable = destroyable;
    }
    Block(Position position, Size size){
        this.position = position;
        this.size = size;
    }
    Block(Position position){
        this.position = position;
    }
    Block(Size size){
        this.size = size;
    }
    Block(boolean destroyable){
        this.destroyable = destroyable;
    }
    public void collides(Entity entity){
        if (entity.position.getX() >= position.getX() - size.getWidth() / 2 && entity.position.getX() <= position.getX() + size.getWidth()/2){
            if (entity.position.getY() > position.getY()){
                 if (entity.position.getY() < position.getY() + size.getHeight() / 2 + entity.size.getHeight()/2){
                     entity.position.setY(position.getY() + size.getHeight() / 2 + entity.size.getHeight()/2);
                     System.out.println("下侧");
                 }
            }
            if (entity.position.getY() < position.getY()){
                if (entity.position.getY() > position.getY() - size.getHeight() / 2 - entity.size.getHeight()/2){
                    entity.position.setY(position.getY() - size.getHeight() / 2 - entity.size.getHeight()/2);
                    System.out.println("上侧");
                }
            }
        }
        if (entity.position.getY() >= position.getY() - size.getHeight() / 2 && entity.position.getY() <= position.getY() + size.getHeight()/2){
            if (entity.position.getX() > position.getX()){
                if (entity.position.getX() < position.getX() + size.getWidth() / 2 + entity.size.getWidth()/2){
                    entity.position.setX(position.getX() + size.getWidth() / 2 + entity.size.getWidth()/2);
                    System.out.println("右侧");
                }
            }
            if (entity.position.getX() < position.getX()){
                if (entity.position.getX() > position.getX() - size.getWidth() / 2 - entity.size.getWidth()/2){
                    entity.position.setX(position.getX() - size.getWidth() / 2 - entity.size.getWidth()/2);
                    System.out.println("左侧");
                }
            }
        }
    }
    public void collides(Ammo entity){
        if (entity.position.getX() >= position.getX() - size.getWidth() / 2 && entity.position.getX() <= position.getX() + size.getWidth()/2){
            if (entity.position.getY() > position.getY()){
                if (entity.position.getY() < position.getY() + size.getHeight() / 2 + entity.size.getRadius()/2){
                    entity.position.setY(position.getY() + size.getHeight() / 2 + entity.size.getRadius()/2);
                    entity.velocity.setY(-entity.velocity.getY());
                    System.out.println("下侧");
                }
            }
            if (entity.position.getY() < position.getY()){
                if (entity.position.getY() > position.getY() - size.getHeight() / 2 - entity.size.getRadius()/2){
                    entity.position.setY(position.getY() - size.getHeight() / 2 - entity.size.getRadius()/2);
                    entity.velocity.setY(-entity.velocity.getY());
                    System.out.println("上侧");
                }
            }
        }
        if (entity.position.getY() >= position.getY() - size.getHeight() / 2 && entity.position.getY() <= position.getY() + size.getHeight()/2){
            if (entity.position.getX() > position.getX()){
                if (entity.position.getX() < position.getX() + size.getWidth() / 2 + entity.size.getRadius()/2){
                    entity.position.setX(position.getX() + size.getWidth() / 2 + entity.size.getRadius()/2);
                    entity.velocity.setX(-entity.velocity.getX());
                    System.out.println("右侧");
                }
            }
            if (entity.position.getX() < position.getX()){
                if (entity.position.getX() > position.getX() - size.getWidth() / 2 - entity.size.getRadius()/2){
                    entity.position.setX(position.getX() - size.getWidth() / 2 - entity.size.getRadius()/2);
                    entity.velocity.setX(-entity.velocity.getX());
                    System.out.println("左侧");
                }
            }
        }
    }

    public void setDestroyable(boolean destroyable){
        this.destroyable = destroyable;
    }
    public boolean IsDestroyable(){
        return destroyable;
    }
}

package study.Assignment;

public class MediumTank extends TANK{
    MediumWeapon weapon_M = new MediumWeapon(this, gameEngine,0);
    MediumTank(GameEngine gameEngine) {
        super(gameEngine);
        size.setWidth(60);
        size.setHeight(60);
        image = gameEngine.loadImage("MediumTank0.png");
    }
    public void updateTank(double dt){
        if(UP) {
            velocity.setX(gameEngine.sin(Angle) * 200);
            velocity.setY(-gameEngine.cos(Angle) * 200);
        }
        if(LEFT) {
            // Make the spaceship rotate anti-clockwise
            Angle -= 100 * dt;
        }
        if (RIGHT){
            Angle += 100 * dt;
        }
        if (DOWN){
            velocity.setX(-gameEngine.sin(Angle) * 100);
            velocity.setY(gameEngine.cos(Angle) * 100);
        }
        position.setX(position.getX() + velocity.getX() * dt);
        position.setY(position.getY() + velocity.getY() * dt);
        Border border = new Border(this);

        weapon_M.updateWeapon(dt);
    }
    public void drawTank(){
        gameEngine.saveCurrentTransform();
        gameEngine.translate(position.getX(),position.getY());
        gameEngine.rotate(Angle);
        gameEngine.drawImage(image, - size.getWidth()/2,  - size.getHeight()/2,size.getWidth(),size.getHeight());
        gameEngine.restoreLastTransform();
        weapon_M.drawWeapon();
    }
}

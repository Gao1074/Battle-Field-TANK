package study.Assignment;

public class ATAmmo extends Ammo{
    ATAmmo(GameEngine gameEngine) {
        super(gameEngine);
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
}

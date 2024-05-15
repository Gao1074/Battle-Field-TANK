package study.Assignment;


import java.awt.*;

public class PLAYER extends TANK{
    ATWeapon weapon_M = new ATWeapon(this, gameEngine,0);
    Spitfire weapon_L = new Spitfire(this, gameEngine,-1);
    Spitfire weapon_R = new Spitfire(this, gameEngine,1);


    public PLAYER(GameEngine gameEngine){
        super(gameEngine);
        image = gameEngine.loadImage("Player.png");
    }
    public void drawUI(){
        weapon_M.drawUI( 20 );
        weapon_L.drawUI( 220 );
        weapon_R.drawUI( 420 );
    }
    public void drawTank(){
        weapon_M.drawWeapon();
        gameEngine.saveCurrentTransform();
        gameEngine.translate(position.getX(),position.getY());
        gameEngine.rotate(Angle);
        gameEngine.drawImage(image, - size.getWidth()/2,  - size.getHeight()/2,size.getWidth(),size.getHeight());
        gameEngine.restoreLastTransform();
    }
}

package study.Assignment;

import java.awt.*;
import java.util.ArrayList;

public class MediumTank extends TANK{

    MediumWeapon weapon_M = new MediumWeapon(this, gameEngine,0);
    boolean Playing;
    MediumTank(GameEngine gameEngine) {
        super(gameEngine);
        size.setWidth(60);
        size.setHeight(60);
        FullHealth = 100;
        Health = 100;
        MovingAudio = gameEngine.loadAudio("Medium.wav");
        image = gameEngine.loadImage("MediumTank0.png");
    }
    public void updateTank(double dt){
        if (Health <= 0){
            defeat = true;
        }
        if (!defeat) {
            if (UP) {
                velocity.setX(gameEngine.sin(Angle) * 200);
                velocity.setY(-gameEngine.cos(Angle) * 200);
            }
            if (LEFT) {
                Angle -= 100 * dt;
            }
            if (RIGHT) {
                Angle += 100 * dt;
            }
            if (DOWN) {
                velocity.setX(-gameEngine.sin(Angle) * 100);
                velocity.setY(gameEngine.cos(Angle) * 100);
            }

            if (!Playing && (UP || LEFT || RIGHT || DOWN)) {
                gameEngine.startAudioLoop(MovingAudio);
                Playing = true;
            }
            if (Playing && !(UP || LEFT || RIGHT || DOWN)) {
                gameEngine.stopAudioLoop(MovingAudio);
                Playing = false;
            }

            position.setX(position.getX() + velocity.getX() * dt);
            position.setY(position.getY() + velocity.getY() * dt);
            Border border = new Border(this);

            weapon_M.updateWeapon(dt);
        }
    }
    public void drawTank(){
        if (!defeat) {
            gameEngine.saveCurrentTransform();
            gameEngine.translate(position.getX(), position.getY());
            gameEngine.rotate(Angle);
            gameEngine.drawImage(image, -size.getWidth() / 2, -size.getHeight() / 2, size.getWidth(), size.getHeight());
            gameEngine.restoreLastTransform();
            gameEngine.drawSolidRectangle(position.getX() - size.getWidth() / 2, position.getY() - size.getHeight() / 2 - 20, size.getWidth() * (Health / FullHealth), 10);
            gameEngine.changeColor(Color.BLACK);
            gameEngine.drawRectangle(position.getX() - size.getWidth() / 2, position.getY() - size.getHeight() / 2 - 20, size.getWidth(), 10);
        }
        weapon_M.drawWeapon();
    }
    public void UpdateDamage(ArrayList<TANK> Enemy){
        weapon_M.updateDamage(Enemy);
    }
}

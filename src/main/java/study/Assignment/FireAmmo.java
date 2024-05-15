package study.Assignment;

import java.awt.*;

public class FireAmmo extends Ammo{

    private Position initialPosition = new Position() {
        @Override
        public double getX() {
            return super.getX();
        }
    };
    public FireAmmo(GameEngine gameEngine){
        super(gameEngine);
    }
    public Position getInitialPosition() {
        return initialPosition;
    }

    public void setInitialPosition(Position initialPosition) {
        this.initialPosition = initialPosition;
    }
    public void update(double dt,Wall wall){
        if (Active) {
            this.position.setX(this.position.getX() + this.velocity.getX() * dt);
            this.position.setY(this.position.getY() + this.velocity.getY() * dt);
            wall.setCollidesAmmo(this);
            double limitDistance = 200;
            if (gameEngine.distance(this.position.getX(), this.position.getY(), this.getInitialPosition().getX(), this.getInitialPosition().getY()) > limitDistance) {
                this.Active = false;
            }
        }
    }
    public void draw(){
        if (Active) {
            gameEngine.saveCurrentTransform();
            gameEngine.translate(position.getX(), position.getY());
            gameEngine.rotate(Angle);
            gameEngine.changeColor(Color.RED);
            gameEngine.drawSolidCircle(0, 0,size.getRadius());
            gameEngine.restoreLastTransform();
        }
    }
}

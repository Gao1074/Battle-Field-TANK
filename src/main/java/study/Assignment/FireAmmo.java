package study.Assignment;


public class FireAmmo extends Ammo{
    final double trackingLength = 400;

    private Position initialPosition = new Position() {
        @Override
        public double getX() {
            return super.getX();
        }
    };
    public FireAmmo(GameEngine gameEngine){
        super(gameEngine);
        image = gameEngine.subImage(AmmoImage,0,0,20,103);
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
            if (gameEngine.distance(this.position.getX(), this.position.getY(), this.getInitialPosition().getX(), this.getInitialPosition().getY()) > trackingLength) {
                this.Active = false;
            }
        }
    }
    public void draw(){
        if (Active) {
            gameEngine.saveCurrentTransform();
            gameEngine.translate(position.getX(), position.getY());
            gameEngine.rotate(Angle);
            gameEngine.drawImage(image,-2.5,-12.5,5,25);
            gameEngine.restoreLastTransform();
        }
    }
}

package study.Assignment;

public class FireAmmo extends Ammo{
    private Position initialPosition = new Position() {
        @Override
        public double getX() {
            return super.getX();
        }
    };

    public Position getInitialPosition() {
        return initialPosition;
    }

    public void setInitialPosition(Position initialPosition) {
        this.initialPosition = initialPosition;
    }
}

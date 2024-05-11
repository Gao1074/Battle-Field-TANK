package study.Assignment;

public class Entity {
    protected Position position = new Position() {
        @Override
        public int hashCode() {
            return super.hashCode();
        }
    };
    protected Velocity velocity = new Velocity() {
        @Override
        public int hashCode() {
            return super.hashCode();
        }
    };
    protected Size size = new Size() {
        @Override
        public int hashCode() {
            return super.hashCode();
        }
    };
    protected double Angle;
    public void setPosition(double x,double y) {
        this.position.setX(x);
        this.position.setY(y);
    }
}

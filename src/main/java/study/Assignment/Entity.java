package study.Assignment;

public class Entity {
    Position position = new Position() {
        @Override
        public int hashCode() {
            return super.hashCode();
        }
    };
    Velocity velocity = new Velocity() {
        @Override
        public int hashCode() {
            return super.hashCode();
        }
    };
    double Angle;
    double width;
    double height;
    public void setPosition(double x,double y) {
        this.position.X = x;
        this.position.Y = y;
    }
}

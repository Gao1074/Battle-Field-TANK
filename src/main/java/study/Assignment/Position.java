package study.Assignment;

public abstract class Position {
    private double X;
    private double Y;
    Position(){
        this.X = 0;
        this.Y = 0;
    }

    public double getX() {
        return X;
    }

    public void setX(double x) {
        X = x;
    }

    public double getY() {
        return Y;
    }

    public void setY(double y) {
        Y = y;
    }
}

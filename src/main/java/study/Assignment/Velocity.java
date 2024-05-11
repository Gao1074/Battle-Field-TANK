package study.Assignment;

public abstract class Velocity {
    private double X;
    private double Y;
    private double V;
    Velocity(){
        this.X = 0;
        this.Y = 0;
        this.V = 0;
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

    public double getV() {
        return V;
    }

    public void setV(double v) {
        V = v;
    }
}

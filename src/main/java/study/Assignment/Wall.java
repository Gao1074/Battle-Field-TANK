package study.Assignment;

public class Wall {
    Position From = new Position() {
        @Override
        public int hashCode() {
            return super.hashCode();
        }
    };
    Position To = new Position() {
        @Override
        public int hashCode() {
            return super.hashCode();
        }
    };
    public void setWall(double X, double Y, double TX, double TY){
        From.X = X;
        From.Y = Y;
        To.X = TX;
        To.Y = TY;
    }
}

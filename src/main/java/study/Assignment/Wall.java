package study.Assignment;
import study.Assignment.GameEngine;
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
    public void setWallCollides(Position position , Size size){
        if (From.X == To.X){
            if (position.Y < (Math.max(From.Y, To.Y)) && position.Y > (Math.min(From.Y, To.Y))){
                if (position.X > To.X){
                    if (position.X - To.X <= size.width/2){
                        position.X = size.width/2 + To.X;
                    }
                }
                if (position.X < To.X){
                    if (To.X - position.X <= size.width/2){
                        position.X = To.X - size.width/2;
                    }
                }
            }
        }
        if (From.Y == To.Y){
            if (position.X < (Math.max(From.X, To.X)) && position.X > (Math.min(From.X, To.X))){
                if (position.Y > To.Y){
                    if (position.Y - To.Y <= size.height/2){
                        position.Y = size.height/2 + To.Y;
                    }
                }
                if (position.Y < To.Y){
                    if (To.Y - position.Y <= size.height/2){
                        position.Y = To.Y - size.height/2;
                    }
                }
            }
        }
        //return void;
    }
    public void setWallCollides(Ammo ammo){
        if (From.X == To.X){
            if (ammo.position.Y < (Math.max(From.Y, To.Y)) && ammo.position.Y > (Math.min(From.Y, To.Y))){
                if (ammo.position.X <= To.X) {
                    if (To.X - ammo.position.X <= ammo.size.radius) {
                        ammo.velocity.X = - ammo.velocity.X;
                        ammo.position.X = To.X - ammo.size.radius-1;
                    }
                }if (ammo.position.X > To.X) {
                    if (ammo.position.X - To.X < ammo.size.radius) {
                        ammo.velocity.X = - ammo.velocity.X;
                        ammo.position.X = To.X + ammo.size.radius;
                    }
                }
            }
        }
        if (From.Y == To.Y){
            if (ammo.position.X < (Math.max(From.X, To.X)) && ammo.position.X > (Math.min(From.X, To.X))){
                if (To.Y - ammo.position.Y <= ammo.size.radius || ammo.position.Y - To.Y <= ammo.size.radius){
                    ammo.velocity.Y = -ammo.velocity.Y;
                }
            }
        }
        //return ammo.velocity;
    }
}

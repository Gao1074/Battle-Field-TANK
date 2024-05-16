package study.Assignment;
import study.Assignment.GameEngine;

import java.time.Year;
import java.util.ArrayList;

public class Wall {
    ArrayList<Block> blocks;
    public void newBlock(double X,double Y,double width, double height,boolean destroy){
        Position position = new Position() {
            @Override
            public double getX() {
                return super.getX();
            }
        };
        position.setX(X);
        position.setY(Y);
        Size size = new Size() {
            @Override
            public double getRadius() {
                return super.getRadius();
            }
        };
        size.setWidth(width);
        size.setHeight(height);
        Block block = new Block(position,size,destroy);
        this.blocks.add(block);
    }
    public void removeBlock(int i){
        this.blocks.remove(i);
    }
    public ArrayList<Block> getBlocks(){
        return blocks;
    }
    public void setCollides(Entity entity){
        for (Block block : blocks){
            block.collides(entity);
        }
    }
    public void setCollidesAmmo(Ammo entity){
        for (Block block : blocks){
            block.collides(entity);
        }
    }
    //old
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
    public Wall(){
        blocks = new ArrayList<>();
    }





    //old
    public void setWall(double X, double Y, double TX, double TY){
        From.setX(X);
        From.setY(Y);
        To.setX(TX);
        To.setY(TY);
    }
    public void setWallCollides(TANK tank){
        if (From.getX() == To.getX()){
            if (tank.position.getY() < (Math.max(From.getY(), To.getY())) && tank.position.getY() > (Math.min(From.getY(), To.getY()))){
                if (tank.position.getX() > To.getX()){
                    if (tank.position.getX() - To.getX() <= tank.size.getWidth() /2){
                        tank.position.setX(tank.size.getWidth() /2 + To.getX());
                    }
                }
                if (tank.position.getX() < To.getX()){
                    if (To.getX() - tank.position.getX() <= tank.size.getWidth() /2){
                        tank.position.setX(To.getX() - tank.size.getWidth() /2);
                    }
                }
            }
        }
        if (From.getY() == To.getY()){
            if (tank.position.getX() < (Math.max(From.getX(), To.getX())) && tank.position.getX() > (Math.min(From.getX(), To.getX()))){
                if (tank.position.getY() > To.getY()){
                    if (tank.position.getY() - To.getY() <= tank.size.getHeight() /2){
                        tank.position.setY(tank.size.getHeight() /2 + To.getY());
                    }
                }
                if (tank.position.getY() < To.getY()){
                    if (To.getY() - tank.position.getY() <= tank.size.getHeight() /2){
                        tank.position.setY(To.getY() - tank.size.getHeight() /2);
                    }
                }
            }
        }
        //return void;
    }
    public void setWallCollides(Ammo ammo){
        if (From.getX() == To.getX()){
            if (ammo.position.getY() < (Math.max(From.getY(), To.getY())) && ammo.position.getY() > (Math.min(From.getY(), To.getY()))){
                if (ammo.position.getX() <= To.getX()) {
                    if (To.getX() - ammo.position.getX() <= ammo.size.getRadius()) {
                        ammo.velocity.setX(- ammo.velocity.getX());
                        ammo.position.setX(To.getX() - ammo.size.getRadius() -1);
                    }
                }if (ammo.position.getX() > To.getX()) {
                    if (ammo.position.getX() - To.getX() < ammo.size.getRadius()) {
                        ammo.velocity.setX(- ammo.velocity.getX());
                        ammo.position.setX(To.getX() + ammo.size.getRadius());
                    }
                }
            }
        }
        if (From.getY() == To.getY()){
            if (ammo.position.getX() < (Math.max(From.getX(), To.getX())) && ammo.position.getX() > (Math.min(From.getX(), To.getX()))){
                if (To.getY() - ammo.position.getY() <= ammo.size.getRadius() || ammo.position.getY() - To.getY() <= ammo.size.getRadius()){
                    ammo.velocity.setY(-ammo.velocity.getY());
                }
            }
        }
        //return ammo.velocity;
    }
}

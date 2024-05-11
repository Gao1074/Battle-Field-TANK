package study.Assignment;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class TankBattle extends GameEngine{
    //
    boolean GameOver;
    //ArrayList<Wall> walls = new ArrayList<>();
    public static void main(String[] args) {

        createGame(new TankBattle());

    }
    public Position Border(Position position){

        if (position.getX() >= width() - player.size.getWidth() /2){
            position.setX(width() - player.size.getWidth() /2);
        }
        if (position.getY() >= height() - player.size.getHeight() /2){
            position.setY( height() - player.size.getHeight() /2);
        }
        if (position.getX() <= player.size.getWidth() /2){
            position.setX(player.size.getWidth() /2);
        }
        if (position.getY() <= player.size.getHeight() /2){
            position.setY(player.size.getHeight() /2);
        }
        return position;
    }
    public void updateTank(double dt){
        if(player.UP) {
            player.velocity.setX(sin(player.Angle) * 50);
            player.velocity.setY(-cos(player.Angle) * 50);
        }
        if(player.LEFT) {
            // Make the spaceship rotate anti-clockwise
            player.Angle -= 250 * dt;
        }
        if (player.RIGHT){
            player.Angle += 250 * dt;
        }
        if (player.DOWN){
            player.velocity.setX(-sin(player.Angle) * 50);
            player.velocity.setY(cos(player.Angle) * 50);
        }
        player.velocity.setV(sqrt(player.velocity.getX() * player.velocity.getX() + player.velocity.getY() * player.velocity.getY()));
        double tempX;
        double tempY;
        tempX = player.position.getX();
        tempY = player.position.getY();
        player.position.setX(tempX +player.velocity.getX() * dt);
        player.position.setY(tempY +player.velocity.getY() * dt);
        player.position = Border(player.position);
        /*for (Wall wall : walls) {
            wall.setWallCollides(player);
        }*/
        wall.setCollides(player);
    }
    public void drawWall(){
        /*for (Wall wall : walls) {
            drawLine(wall.From.getX(), wall.From.getY(), wall.To.getX(), wall.To.getY());
        }*/
        for (Block block : wall.getBlocks()){
            drawRectangle(block.position.getX() - block.size.getWidth() / 2,block.position.getY() - block.size.getHeight() / 2,block.size.getWidth(),block.size.getHeight());
        }
    }
    public void updateAmmo(double dt){
        for (int i = 0; i<99; i ++) {
            if (ammo.get(i).Active) {
                double Xtemp = ammo.get(i).position.getX();
                double Ytemp = ammo.get(i).position.getY();
                ammo.get(i).setPosition(Xtemp + ammo.get(i).velocity.getX() * dt,Ytemp + ammo.get(i).velocity.getY() * dt);
                /*for (Wall wall : walls) {
                    //ammo.get(i).velocity =
                            wall.setWallCollides(ammo.get(i));
                }*/
            }
        }
    }
    public void fire (){
        Ammo tempAmmo;
        for (int i = 0; i <99 ;i++){
            if (!ammo.get(i).Active) {
                tempAmmo = new Ammo();
                tempAmmo.Active = true;
                tempAmmo.Angle = player.Angle;
                tempAmmo.position.setX(player.position.getX());
                tempAmmo.position.setY(player.position.getY());
                tempAmmo.velocity.setX(sin(tempAmmo.Angle) * 200);
                tempAmmo.velocity.setY(-cos(tempAmmo.Angle) * 200);
                ammo.set(i, tempAmmo);
                break;
            }
        }
    }
    PLAYER player = new PLAYER();
    public void initTank(){

        player.Angle = 0;
        player.DOWN = false;
        player.UP = false;
        player.LEFT = false;
        player.RIGHT = false;
        player.position.setX(20);
        player.position.setY(20);
        player.velocity.setX(0);
        player.velocity.setY(0);
    }
    ArrayList<Ammo> ammo = new ArrayList<>();
    Ammo initAmmo = new Ammo();
    public void InitAmmo(){
        initAmmo.position.setX(0);
        initAmmo.position.setY(0);
        initAmmo.velocity.setX(0);
        initAmmo.velocity.setY(0);
        initAmmo.Active = false;
        initAmmo.Angle = 0;
        for (int i = 0 ; i < 99 ;i++){
            ammo.add(i,initAmmo);
        }
    }
    public void init(){

        initTank();
        InitAmmo();
    }

    @Override
    public void update(double dt) {
        updateTank(dt);
        updateAmmo(dt);
    }
    public void drawAmmo(){
        for (int i = 0 ;i<99;i++){
            if (ammo.get(i).Active) {
                saveCurrentTransform();

                translate(ammo.get(i).position.getX(), ammo.get(i).position.getY());

                rotate(ammo.get(i).Angle);
                changeColor(Color.BLACK);
                drawSolidCircle(0, 0, ammo.get(i).size.getRadius());
                restoreLastTransform();
            }
        }

    }
    public void drawTank(){
        saveCurrentTransform();

        translate(player.position.getX(),player.position.getY());

        rotate(player.Angle);
        changeColor(Color.BLACK);
        drawSolidRectangle(-10,-10,20,20);
        drawSolidRectangle(-2,-20,4,10);
        restoreLastTransform();
    }
    @Override
    public void paintComponent() {
        changeColor(Color.WHITE);
        clearBackground(width(),height());
        drawTank();
        drawAmmo();
        if (wall.getBlocks().size() !=0) {
            drawWall();
        }
        /*if (walls.size()!=0){
            drawWall();
        }*/

    }
    boolean Pressed = false;
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_SPACE && !GameOver)    {
            if (!Pressed){
                fire();
                Pressed = true;
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE && !GameOver)    {
            System.exit(1);
        }
        if(e.getKeyCode() == KeyEvent.VK_W && !GameOver)    {
            player.UP = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_A && !GameOver)  {
            player.LEFT = true;
            /*if (player.UP){
                player.velocity.X = sin(player.Angle) * 25;
                player.velocity.Y = -cos(player.Angle) * 25;
            }
            if (player.DOWN){
                player.velocity.X = -sin(player.Angle) * 25;
                player.velocity.Y = cos(player.Angle) * 25;
            }*/
        }
        if(e.getKeyCode() == KeyEvent.VK_S && !GameOver)  {
            player.DOWN = true;
            /*player.velocity.X = -sin(player.Angle) * 25;
            player.velocity.Y = cos(player.Angle) * 25;*/
        }
        if(e.getKeyCode() == KeyEvent.VK_D && !GameOver) {
            player.RIGHT = true;
            /*if (player.UP){
                player.velocity.X = sin(player.Angle) * 25;
                player.velocity.Y = -cos(player.Angle) * 25;
            }
            if (player.DOWN){
                player.velocity.X = -sin(player.Angle) * 25;
                player.velocity.Y = cos(player.Angle) * 25;
            }*/
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_W && !GameOver)    {
            player.UP = false;
            player.velocity.setX(0);
            player.velocity.setY(0);
        }
        if(e.getKeyCode() == KeyEvent.VK_A && !GameOver)  {
            player.LEFT = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_S && !GameOver)  {
            player.DOWN = false;
            player.velocity.setX(0);
            player.velocity.setY(0);
        }
        if(e.getKeyCode() == KeyEvent.VK_D && !GameOver) {
            player.RIGHT = false;

        }
        if(e.getKeyCode() == KeyEvent.VK_SPACE && !GameOver)    {
            Pressed = false;
        }
    }
    @Override
    public void setupWindow(int width, int height) {
        super.setupWindow(width, height);
        mFrame.setLocation(0,0);
        mFrame.getGraphicsConfiguration().getDevice().setFullScreenWindow(mFrame);
        ConstructWall();

    }
    Wall wall = new Wall();
    public void ConstructWall(){
        /*Wall wall = new Wall();
        wall.setWall(200,0,200,400);
        walls.add(wall);

        wall = new Wall();
        wall.setWall(400,0,400,600);
        walls.add(wall);*/
        wall.newBlock(100,100,40,40,false);
        wall.newBlock(500,500,40,40,false);
    }
}
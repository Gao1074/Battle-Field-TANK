package study.Assignment;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class TankBattle extends GameEngine{
    //
    boolean GameOver;
    ArrayList<Wall> walls = new ArrayList<>();
    public static void main(String[] args) {

        createGame(new TankBattle());

    }
    public Position Border(Position position){

        if (position.X >= width() - player.size.width/2){
            position.X = width() - player.size.width/2;
        }
        if (position.Y >= height() - player.size.height/2){
            position.Y = height() - player.size.height/2;
        }
        if (position.X <= player.size.width/2){
            position.X = player.size.width/2;
        }
        if (position.Y <= player.size.height/2){
            position.Y = player.size.height/2;
        }
        return position;
    }
    public void updateTank(double dt){
        if(player.UP) {
            player.velocity.X = sin(player.Angle) * 50;
            player.velocity.Y = -cos(player.Angle) * 50;
        }
        if(player.LEFT) {
            // Make the spaceship rotate anti-clockwise
            player.Angle -= 250 * dt;
        }
        if (player.RIGHT){
            player.Angle += 250 * dt;
        }
        if (player.DOWN){
            player.velocity.X = -sin(player.Angle) * 50;
            player.velocity.Y = cos(player.Angle) * 50;
        }
        player.velocity.V = sqrt(player.velocity.X * player.velocity.X + player.velocity.Y * player.velocity.Y);
        player.position.X += player.velocity.X * dt;
        player.position.Y += player.velocity.Y * dt;
        player.position = Border(player.position);
        for (Wall wall : walls) {
            wall.setWallCollides(player);
        }
    }
    public void drawWall(){
        for (Wall wall : walls) {
            drawLine(wall.From.X, wall.From.Y, wall.To.X, wall.To.Y);
        }
    }
    public void updateAmmo(double dt){
        for (int i = 0; i<99; i ++) {
            if (ammo.get(i).Active) {
                double Xtemp = ammo.get(i).position.X;
                double Ytemp = ammo.get(i).position.Y;
                ammo.get(i).setPosition(Xtemp + ammo.get(i).velocity.X * dt,Ytemp + ammo.get(i).velocity.Y * dt);
                for (Wall wall : walls) {
                    //ammo.get(i).velocity =
                            wall.setWallCollides(ammo.get(i));
                }
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
                tempAmmo.position.X = player.position.X;
                tempAmmo.position.Y = player.position.Y;
                tempAmmo.velocity.X = sin(tempAmmo.Angle) * 200;
                tempAmmo.velocity.Y = -cos(tempAmmo.Angle) * 200;
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
        player.position.X = 20;
        player.position.Y = 20;
        player.velocity.X = 0;
        player.velocity.Y = 0;
    }
    ArrayList<Ammo> ammo = new ArrayList<>();
    Ammo initAmmo = new Ammo();
    public void InitAmmo(){
        initAmmo.position.X = 0;
        initAmmo.position.Y = 0;
        initAmmo.velocity.X = 0;
        initAmmo.velocity.Y = 0;
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

                translate(ammo.get(i).position.X, ammo.get(i).position.Y);

                rotate(ammo.get(i).Angle);
                changeColor(Color.BLACK);
                drawSolidCircle(0, 0, ammo.get(i).size.radius);
                restoreLastTransform();
            }
        }

    }
    public void drawTank(){
        saveCurrentTransform();

        translate(player.position.X,player.position.Y);

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
        if (walls.size()!=0){
            drawWall();
        }

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
            player.velocity.X = 0;
            player.velocity.Y = 0;
        }
        if(e.getKeyCode() == KeyEvent.VK_A && !GameOver)  {
            player.LEFT = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_S && !GameOver)  {
            player.DOWN = false;
            player.velocity.X = 0;
            player.velocity.Y = 0;
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
    public void ConstructWall(){
        Wall wall = new Wall();
        wall.setWall(200,0,200,400);
        walls.add(wall);

        wall = new Wall();
        wall.setWall(400,0,400,600);
        walls.add(wall);
    }
}
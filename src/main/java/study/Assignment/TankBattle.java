package study.Assignment;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class TankBattle extends GameEngine{
    boolean GameOver;

    public static void main(String[] args) {
        createGame(new TankBattle());
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
        player.velocity.V = Math.sqrt(player.velocity.X * player.velocity.X + player.velocity.Y * player.velocity.Y);
        player.position.X += player.velocity.X * dt;
        player.position.Y += player.velocity.Y * dt;
    }
    public void updateAmmo(double dt){
        for (int i = 0; i<99; i ++) {
            if (ammo.get(i).Active) {
                double Xtemp = ammo.get(i).position.X;
                double Ytemp = ammo.get(i).position.Y;
                ammo.get(i).setPosition(Xtemp + ammo.get(i).velocity.X * dt,Ytemp + ammo.get(i).velocity.Y * dt);
            }
        }
    }
    public void fire (){
        for (int i = 0; i <99 ;i++){
            if (!ammo.get(i).Active) {
                Ammo tempAmmo = new Ammo();
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
                drawSolidCircle(0, -34, 8);
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
        drawSolidRectangle(-5,-30,10,20);
        restoreLastTransform();
    }
    @Override
    public void paintComponent() {
        changeColor(Color.WHITE);
        clearBackground(width(),height());
        drawTank();
        drawAmmo();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_SPACE && !GameOver)    {
            fire();
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
    }
}
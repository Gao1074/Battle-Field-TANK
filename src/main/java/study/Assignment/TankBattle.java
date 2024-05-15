package study.Assignment;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class TankBattle extends GameEngine{
    //
    boolean GameOver;
    double loadingTime = 3;
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

        player.weapon_M.updateAmmo(dt,wall);
        player.weapon_L.updateAmmo(dt,wall);
        player.weapon_R.updateAmmo(dt,wall);
        /*for (int i = 0; i<99; i ++) {
            if (player.ammo.get(i).Active) {
                double Xtemp = player.ammo.get(i).position.getX();
                double Ytemp = player.ammo.get(i).position.getY();
                player.ammo.get(i).setPosition(Xtemp + player.ammo.get(i).velocity.getX() * dt,Ytemp + player.ammo.get(i).velocity.getY() * dt);
                for (Wall wall : walls) {
                    //player.ammo.get(i).velocity =
                            wall.setWallCollides(player.ammo.get(i));
                }

            }
        }*/
    }
    PLAYER player = new PLAYER(this);
    public void initTank(){

        player.Angle = 90;
        player.DOWN = false;
        player.UP = false;
        player.LEFT = false;
        player.RIGHT = false;
        player.position.setX(player.size.getHeight());
        player.position.setY(Toolkit.getDefaultToolkit().getScreenSize().height / 2.0);
        player.velocity.setX(0);
        player.velocity.setY(0);
    }

    public void init(){
        initTank();
    }

    @Override
    public void update(double dt) {
        updateTank(dt);
        updateAmmo(dt);
        updateWeapon(dt);
    }
    public void updateWeapon(double dt){
        if (JPressed){
            player.weapon_L.FireMode(dt);
        }
        if (KPressed){
            player.weapon_R.FireMode(dt);
        }

        player.weapon_M.updateWeapon(dt);
        player.weapon_R.updateWeapon();
        player.weapon_L.updateWeapon();
    }
    public void drawAmmo(){
        player.weapon_M.drawAmmo();
        player.weapon_L.drawAmmo();
        player.weapon_R.drawAmmo();
        /*for (int i = 0 ;i<99;i++){
            if (player.ammo.get(i).Active) {
                saveCurrentTransform();

                translate(player.ammo.get(i).position.getX(), player.ammo.get(i).position.getY());

                rotate(player.ammo.get(i).Angle);
                changeColor(Color.BLACK);
                drawSolidCircle(0, 0, player.ammo.get(i).size.getRadius());
                restoreLastTransform();
            }
        }*/

    }
    public void drawTank(){

    }
    @Override
    public void paintComponent() {
        changeColor(Color.WHITE);
        clearBackground(width(),height());

        drawAmmo();
        player.drawTank();
        drawWeapon();
        if (wall.getBlocks().size() !=0) {
            drawWall();
        }
        /*if (walls.size()!=0){
            drawWall();
        }*/

    }
    public void drawWeapon(){
        player.drawUI();
        player.weapon_M.drawWeapon();
        player.weapon_R.drawWeapon();
        player.weapon_L.drawWeapon();
    }
    boolean SpacePressed = false;
    boolean JPressed = false;
    boolean KPressed = false;
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_SPACE && !GameOver)    {
            if (!SpacePressed){
                player.weapon_M.Fire();
                SpacePressed = true;
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_J && !GameOver)    {
            if (!JPressed){
                JPressed = true;
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_K && !GameOver)    {
            if (!KPressed){
                KPressed = true;
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
            SpacePressed = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_J && !GameOver)    {
            JPressed = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_K && !GameOver)    {
            KPressed = false;
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
        wall.newBlock(100,100,80,80,false);
        wall.newBlock(500,500,80,80,false);
    }
}
package study.Assignment;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class TankBattle extends GameEngine{
    public Wall wall = new Wall();
    //
    boolean GameOver;
    public ArrayList<TANK> players = new ArrayList<>();
    public ArrayList<TANK> AI = new ArrayList<>();
    int P1Choose = 0;

    public static void main(String[] args) {
        createGame(new TankBattle());
    }
    LightTank P1;
    MediumTank P1M;
    HeavyTank P1H;

    HeavyTank AI1 = new HeavyTank(this);
    public void initTank(){
        P1Choose = 1;
        P1 = new LightTank(this);
        P1.initTank();
        players.add(P1);

        AI1.initTank(900,900);
        AI.add(AI1);
    }

    public void init(){
        initTank();
    }
    public void updateTank(double dt){
        P1.updateTank(dt);
        if (P1Choose == 2) {
            //P1.FindTarget(AI, dt);
        }
        wall.setCollides(P1);


        AI1.updateTank(dt);
        AI1.FindTarget(players,dt);
        wall.setCollides(AI1);

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
        if (P1Choose==2) {
            /*P1.weapon_M.updateAmmo(dt, wall);
            P1.weapon_L.updateAmmo(dt, wall);
            P1.weapon_R.updateAmmo(dt, wall);
            P1.weapon_L_L.updateAmmo(dt, wall);
            P1.weapon_R_R.updateAmmo(dt, wall);*/
        }
        AI1.weapon_M.updateAmmo(dt,wall);
        AI1.weapon_L.updateAmmo(dt,wall);
        AI1.weapon_R.updateAmmo(dt,wall);
        AI1.weapon_L_L.updateAmmo(dt,wall);
        AI1.weapon_R_R.updateAmmo(dt,wall);
    }



    @Override
    public void update(double dt) {
        AI1.AI(players,dt);
        updateTank(dt);
        updateAmmo(dt);
        updateWeapon(dt);
    }
    public void updateWeapon(double dt){
        /*if (JPressed){
            P1.weapon_L.FireMode(dt);
        }
        if (KPressed){
            P1.weapon_R.FireMode(dt);
        }*/
        P1.updateTank(dt);
        AI1.updateTank(dt);
    }
    public void drawAmmo(){

    }
    public void drawTank(){
        P1.drawTank();
        AI1.drawTank();
    }
    @Override
    public void paintComponent() {
        changeColor(Color.WHITE);
        clearBackground(width(),height());

        drawAmmo();
        drawTank();
        drawWeapon();
        if (wall.getBlocks().size() !=0) {
            drawWall();
        }
    }
    public void drawWeapon(){
        /*P1.drawUI();*/
    }
    boolean SpacePressed = false;
    boolean JPressed = false;
    boolean KPressed = false;
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_SPACE && !GameOver)    {
            if (!SpacePressed){
                //P1.weapon_M.Fire();
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
            P1.UP = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_A && !GameOver)  {
            P1.LEFT = true;
            /*if (heavyTank.UP){
                heavyTank.velocity.X = sin(heavyTank.Angle) * 25;
                heavyTank.velocity.Y = -cos(heavyTank.Angle) * 25;
            }
            if (heavyTank.DOWN){
                heavyTank.velocity.X = -sin(heavyTank.Angle) * 25;
                heavyTank.velocity.Y = cos(heavyTank.Angle) * 25;
            }*/
        }
        if(e.getKeyCode() == KeyEvent.VK_S && !GameOver)  {
            P1.DOWN = true;
            /*heavyTank.velocity.X = -sin(heavyTank.Angle) * 25;
            heavyTank.velocity.Y = cos(heavyTank.Angle) * 25;*/
        }
        if(e.getKeyCode() == KeyEvent.VK_D && !GameOver) {
            P1.RIGHT = true;
            /*if (heavyTank.UP){
                heavyTank.velocity.X = sin(heavyTank.Angle) * 25;
                heavyTank.velocity.Y = -cos(heavyTank.Angle) * 25;
            }
            if (heavyTank.DOWN){
                heavyTank.velocity.X = -sin(heavyTank.Angle) * 25;
                heavyTank.velocity.Y = cos(heavyTank.Angle) * 25;
            }*/
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_W && !GameOver)    {
            P1.UP = false;
            P1.velocity.setX(0);
            P1.velocity.setY(0);
        }
        if(e.getKeyCode() == KeyEvent.VK_A && !GameOver)  {
            P1.LEFT = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_S && !GameOver)  {
            P1.DOWN = false;
            P1.velocity.setX(0);
            P1.velocity.setY(0);
        }
        if(e.getKeyCode() == KeyEvent.VK_D && !GameOver) {
            P1.RIGHT = false;

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

    public void ConstructWall(){
        wall.newBlock(100,100,80,80,false);
        wall.newBlock(500,500,80,80,false);
    }
}
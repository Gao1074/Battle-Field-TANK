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
    LightTank P1 = new LightTank(this);
    MediumTank P1M = new MediumTank(this);
    HeavyTank P1H = new HeavyTank(this);
    HeavyTank AI1 = new HeavyTank(this);
    public void initTank(){
        P1Choose = 1;
        if (P1Choose == 0) {
            P1.initTank();
            players.add(P1);
        }
        if (P1Choose == 1) {
            P1M.initTank();
            players.add(P1M);
        }
        if (P1Choose == 2) {
            P1H.initTank();
            players.add(P1H);
        }

        AI1.initTank(900,900);
        AI.add(AI1);
    }

    public void init(){
        initTank();
    }
    public void updateTank(double dt){
        if (P1Choose == 0){
            P1.updateTank(dt);
        }
        if (P1Choose == 1){
            P1M.updateTank(dt);
        }
        if (P1Choose == 2) {
            P1H.FindTarget(AI, dt);
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
        if (P1Choose == 0){
            P1.weapon_M.updateAmmo(dt,wall);
        }else if (P1Choose == 1){
            P1M.weapon_M.updateAmmo(dt,wall);
        }
        if (P1Choose==2) {
            P1H.weapon_M.updateAmmo(dt, wall);
            P1H.weapon_L.updateAmmo(dt, wall);
            P1H.weapon_R.updateAmmo(dt, wall);
            P1H.weapon_L_L.updateAmmo(dt, wall);
            P1H.weapon_R_R.updateAmmo(dt, wall);
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
        if (P1Choose==0){
            P1.updateTank(dt);
        }
        if (P1Choose==1){
            P1M.updateTank(dt);
        }
        if (P1Choose==2){
            P1H.updateTank(dt);
        }
        AI1.updateTank(dt);
    }
    public void drawAmmo(){

    }
    public void drawTank(){
        if (P1Choose == 0) {
            P1.drawTank();
        }
        if (P1Choose == 1) {
            P1M.drawTank();
        }if (P1Choose == 2) {
            P1H.drawTank();
        }
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
    }
    boolean SpacePressed = false;
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_SPACE && !GameOver)    {
            if (!SpacePressed){
                if (P1Choose==0) {
                    P1.weapon_M.Fire();
                    SpacePressed = true;
                }
                if (P1Choose==1) {
                    P1M.weapon_M.Fire();
                    SpacePressed = true;
                }
                if (P1Choose==2) {
                    P1H.weapon_M.Fire();
                    SpacePressed = true;
                }
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE && !GameOver)    {
            System.exit(1);
        }
        if(e.getKeyCode() == KeyEvent.VK_W && !GameOver)    {
            if (P1Choose==0) {
                P1.UP = true;
            }
            if (P1Choose==1) {
                P1M.UP = true;
            }
            if (P1Choose==2) {
                P1H.UP = true;
            }

        }
        if(e.getKeyCode() == KeyEvent.VK_A && !GameOver)  {
            if (P1Choose==0) {
                P1.LEFT = true;
            }
            if (P1Choose==1) {
                P1M.LEFT = true;
            }
            if (P1Choose==2) {
                P1H.LEFT = true;
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_S && !GameOver)  {
            if (P1Choose==0) {
                P1.DOWN = true;
            }
            if (P1Choose==1) {
                P1M.DOWN = true;
            }
            if (P1Choose==2) {
                P1H.DOWN = true;
            }

        }
        if(e.getKeyCode() == KeyEvent.VK_D && !GameOver) {
            if (P1Choose==0) {
                P1.RIGHT = true;
            }
            if (P1Choose==1) {
                P1M.RIGHT = true;
            }
            if (P1Choose==2) {
                P1H.RIGHT = true;
            }

        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_W && !GameOver)    {
            if (P1Choose==0) {
                P1.UP = false;
                P1.velocity.setX(0);
                P1.velocity.setY(0);
            }
            if (P1Choose==1) {
                P1M.UP = false;
                P1M.velocity.setX(0);
                P1M.velocity.setY(0);
            }
            if (P1Choose==2) {
                P1H.UP = false;
                P1H.velocity.setX(0);
                P1H.velocity.setY(0);
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_A && !GameOver)  {
            if (P1Choose==0) {
                P1.LEFT = false;
            }if (P1Choose==1) {
                P1M.LEFT = false;
            }if (P1Choose==2) {
                P1H.LEFT = false;
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_S && !GameOver)  {
            if (P1Choose==0) {
                P1.DOWN = false;
                P1.velocity.setX(0);
                P1.velocity.setY(0);
            }if (P1Choose==1) {
                P1M.DOWN = false;
                P1M.velocity.setX(0);
                P1M.velocity.setY(0);
            }if (P1Choose==2) {
                P1H.DOWN = false;
                P1H.velocity.setX(0);
                P1H.velocity.setY(0);
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_D && !GameOver) {
            if (P1Choose==0) {
                P1.RIGHT = false;
            }if (P1Choose==1) {
                P1M.RIGHT = false;
            }if (P1Choose==2) {
                P1H.RIGHT = false;
            }


        }
        if(e.getKeyCode() == KeyEvent.VK_SPACE && !GameOver)    {
            SpacePressed = false;
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
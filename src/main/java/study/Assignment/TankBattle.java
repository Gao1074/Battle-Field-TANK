package study.Assignment;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class TankBattle extends GameEngine{
    ArrayList<FireEffect> fireEffects = new ArrayList<>();
    public Wall wall = new Wall();
    ArrayList<Image> Images = new ArrayList<>();
    String Resource = "src/main/resources/";
    String BlackSmoke = "Black smoke/blackSmoke";
    //
    boolean GameOver;
    public ArrayList<TANK> factionA = new ArrayList<>();
    public ArrayList<TANK> factionB = new ArrayList<>();
    public ArrayList<TANK> tanks = new ArrayList<>();
    RepairStation repairStation;
    int P1Choose = 0;
    AudioClip Test = loadAudio("Fast.wav");

    public static void main(String[] args) {
        createGame(new TankBattle());
    }
    LightTank P1;
    MediumTank P1M;
    HeavyTank P1H;
    ArrayList<HeavyTank> playerH = new ArrayList<>();
    ArrayList<MediumTank> playerM = new ArrayList<>();
    ArrayList<LightTank> playerL = new ArrayList<>();
    ArrayList<HeavyTank> heavyTanks = new ArrayList<>();
    ArrayList<LightTank> lightTanks = new ArrayList<>();
    ArrayList<MediumTank> mediumTanks = new ArrayList<>();
    public void initTank(){

        startAudioLoop(Test);
        stopAudioLoop(Test);
        P1 = new LightTank(this);
        P1M = new MediumTank(this);
        P1H = new HeavyTank(this);
        repairStation = new RepairStation(this);
        P1Choose = 1;
        if (P1Choose == 0) {
            initPlayerA(P1,250,250);
        }
        if (P1Choose == 1) {
            initPlayerA(P1M,250,250);
        }
        if (P1Choose == 2) {
            initPlayerA(P1H,250,250);
        }


        initHeavyAi(900,900);
        initMediumAi(300,300);
        initLightAi(300,300);
    }
    public void initPlayerA(HeavyTank tank, double x, double y){
        tank.initTank(x,y);
        factionA.add(tank);
        tanks.add(tank);
    }
    public void initPlayerA(MediumTank tank, double x, double y){
        tank.initTank(x,y);
        factionA.add(tank);
        tanks.add(tank);
    }
    public void initPlayerA(LightTank tank, double x, double y){
        tank.initTank(x,y);
        factionA.add(tank);
        tanks.add(tank);
    }
    public void initHeavyAi(double x, double y){
        HeavyTank tank = new HeavyTank(this);
        tank.initTank(x,y);
        heavyTanks.add(tank);
        factionB.add(tank);
        tanks.add(tank);
    }
    public void initMediumAi(double x, double y){
        MediumTank tank = new MediumTank(this);
        tank.initTank(x,y);
        mediumTanks.add(tank);
        factionB.add(tank);
        tanks.add(tank);
    }
    public void initLightAi(double x, double y){
        LightTank tank = new LightTank(this);
        tank.initTank(x,y);
        lightTanks.add(tank);
        factionB.add(tank);
        tanks.add(tank);
    }

    public void init(){
        factionB.clear();
        factionA.clear();
        lightTanks.clear();
        heavyTanks.clear();
        mediumTanks.clear();
        tanks.clear();
        smokes.clear();
        explosions.clear();
        initStartMenu();
        initSectionA();
    }
    public void initStartMenu(){

    }
    public void initSectionA(){
        initTank();
        initSmoke();
        initExplosion();
        ConstructWall();
        initFloor();
    }
    public void updateTank(double dt){
        if (P1Choose == 0){
            P1.updateTank(dt);
            wall.setCollides(P1);
        }
        if (P1Choose == 1){
            P1M.updateTank(dt);
            wall.setCollides(P1M);
        }
        if (P1Choose == 2) {
            P1H.updateTank(dt);
            P1H.FindTarget(factionB, dt);
            wall.setCollides(P1H);
        }
        for (HeavyTank tank : heavyTanks){
            if (!tank.defeat){
                tank.updateTank(dt);
                tank.FindTarget(factionA, dt);
                wall.setCollides(tank);
            }
        }
        for (MediumTank tank : mediumTanks){
            if (!tank.defeat){
                tank.updateTank(dt);
                wall.setCollides(tank);
            }
        }
        for (LightTank tank : lightTanks){
            if (!tank.defeat){
                tank.updateTank(dt);
                wall.setCollides(tank);
            }
        }

    }

    Floor floorTopleft;
    Floor floorDownleft;
    Floor floorTopright;
    Floor floorDownright;
    Floor floorTop;
    Floor floorDown;
    Floor floorLeft ;
    Floor floorRight;
    Floor floorMiddle;
    public void initFloor(){

        floorTopleft = new Floor(loadImage("src/main/resources/Floor/Topleft.png"), 0, 0,this);
        floorDownleft = new Floor(loadImage("src/main/resources/Floor/Downleft.png"), 0, 900,this);
        floorTopright = new Floor(loadImage("src/main/resources/Floor/Topright.png"), 1400, 0,this);
        floorDownright = new Floor(loadImage("src/main/resources/Floor/Downright.png"), 1400, 900,this);
        floorTop = new Floor(loadImage("src/main/resources/Floor/Top.png"), 0, 0,this);
        floorDown = new Floor(loadImage("src/main/resources/Floor/Down.png"), 0, 900,this);
        floorLeft = new Floor(loadImage("src/main/resources/Floor/Left.png"), 0, 0,this);
        floorRight = new Floor(loadImage("src/main/resources/Floor/Right.png"), 1400, 0,this);
        floorMiddle = new Floor(loadImage("src/main/resources/Floor/Middle.png"), 100, 100,this);
    }
    public void drawFloor(){
        floorTopleft.draw();
        floorDownleft.draw();
        floorTopright.draw();
        floorDownright.draw();
        for (int i = 1; i <= 13; i++) {
            floorTop.setPositionX(100*i);
            floorTop.draw();
            floorDown.setPositionX(100*i);
            floorDown.draw();
        }
        for (int i = 1; i <= 10; i++) {
            floorLeft.setPositionY(100*i);
            floorLeft.draw();
            floorRight.setPositionY(100*i);
            floorRight.draw();
        }
        for (int i = 1; i <= 13; i++) {
            floorMiddle.setPositionX(i*100);
            for (int j = 1; j <= 10; j++) {
                floorMiddle.setPositionY(j*100);
                floorMiddle.draw();
            }
            //floorMiddle.draw();
        }
    }

    Image Block = loadImage(Resource + "block/wall.png");
    public void drawWall(){
        changeColor(Color.BLACK);
        for (Block block : wall.getBlocks()){
            drawImage(Block,block.position.getX() - block.size.getWidth() / 2,block.position.getY() - block.size.getHeight() / 2,block.size.getWidth(),block.size.getHeight());
//            drawRectangle(block.position.getX() - block.size.getWidth() / 2,block.position.getY() - block.size.getHeight() / 2,block.size.getWidth(),block.size.getHeight());
        }
    }
    public void updateAmmo(double dt){
        if (P1Choose == 0){
            P1.weapon_M.updateAmmo(dt,wall);
            P1.UpdateDamage(factionB);
        }else if (P1Choose == 1){
            P1M.weapon_M.updateAmmo(dt,wall);
            P1M.UpdateDamage(factionB);
        }
        if (P1Choose==2) {
            P1H.weapon_M.updateAmmo(dt, wall);
            P1H.weapon_L.updateAmmo(dt, wall);
            P1H.weapon_R.updateAmmo(dt, wall);
            P1H.weapon_L_L.updateAmmo(dt, wall);
            P1H.weapon_R_R.updateAmmo(dt, wall);
            P1H.UpdateDamage(factionB);

        }
        for (HeavyTank tank : heavyTanks){
            tank.weapon_M.updateAmmo(dt,wall);
            tank.weapon_L.updateAmmo(dt,wall);
            tank.weapon_R.updateAmmo(dt,wall);
            tank.weapon_L_L.updateAmmo(dt,wall);
            tank.weapon_R_R.updateAmmo(dt,wall);
            tank.UpdateDamage(factionA);
        }
        for (MediumTank tank : mediumTanks){
            tank.weapon_M.updateAmmo(dt,wall);
            tank.UpdateDamage(factionA);
        }
        for (LightTank tank : lightTanks){
            tank.weapon_M.updateAmmo(dt,wall);
            tank.UpdateDamage(factionA);
        }
    }
    ArrayList<Smoke> smokes = new ArrayList<>();

    @Override
    public void update(double dt) {
        updateSectionA(dt);
        repairStation.repair(P1M);
    }
    public void updateSectionA(double dt){
        for (HeavyTank tank : heavyTanks){
            tank.AI(factionA,dt);
        }
        for (MediumTank tank : mediumTanks){
            tank.AI(factionA,dt);
        }
        for (LightTank tank : lightTanks){
            tank.AI(factionA,dt);
        }
        updateTank(dt);
        updateAmmo(dt);
        updateSmoke(dt);
        updateExplosion(dt);
    }
    public void updateSmoke(double dt){
        for (Smoke smoke : smokes){
            if (smoke.Active) {
                smoke.time += dt;
                smoke.scale = 1f + 0.10f * (float) Math.log(smoke.time * 100);
                smoke.alpha = 0.1f * (float) Math.exp(-smoke.time * 2.5f);
                if (smoke.time >= 0.3) {
                    smoke.Active = false;
                }
            }
        }

        for (TANK tank : tanks){
            if (tank.Health<= tank.FullHealth * 0.5&&!tank.defeat){
                boolean smokeActive = false;
                for (Smoke smoke : smokes) {
                    if (!smoke.Active) {
                        smoke.x = tank.position.getX();
                        smoke.y = tank.position.getY();
                        smoke.w = tank.size.getWidth();
                        smoke.h = tank.size.getHeight();
                        smoke.time = 0;
                        smoke.scale = 1;
                        smoke.Active = true;
                        smokeActive = true;
                        break;
                    }
                }
                if (!smokeActive){
                    Smoke smoke = new Smoke();
                    smoke.x = tank.position.getX();
                    smoke.y = tank.position.getY();
                    smoke.w = tank.size.getWidth();
                    smoke.h = tank.size.getHeight();
                    smoke.time = 0;
                    smoke.scale = 1;
                    smoke.Active = true;
                    smokes.add(smoke);
                }
            }
        }
        for (TANK tank : tanks){
            if (tank.defeat && !tank.explode){
                createExplosion(tank.position.getX(),tank.position.getY(),tank.size.getWidth(),tank.size.getHeight());
                tank.explode = true;
                factionB.remove(tank);
                factionA.remove(tank);
            }
        }
    }
    public void drawSmoke(){
        for (Smoke smoke : smokes){
            if (smoke.Active) {
                saveCurrentTransform();
                translate(smoke.x, smoke.y);
                scale(smoke.scale, smoke.scale);
                mGraphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, smoke.alpha));
                drawImage(Images.get(rand(24)), -smoke.w / 2, -smoke.h / 2, smoke.w, smoke.h);
                mGraphics.setComposite(AlphaComposite.SrcOver);
                restoreLastTransform();
            }
        }
    }
    public void drawTank(){
        changeColor(Color.GREEN);
        if (P1Choose == 0) {
            P1.drawTank();
        }
        if (P1Choose == 1) {
            P1M.drawTank();
        }if (P1Choose == 2) {
            P1H.drawTank();
        }
        for (HeavyTank tank : heavyTanks){
            changeColor(Color.RED);
            tank.drawTank();
        }
        for (MediumTank tank : mediumTanks){
            changeColor(Color.RED);
            tank.drawTank();
        }
        for (LightTank tank : lightTanks){
            changeColor(Color.RED);
            tank.drawTank();
        }
    }
    @Override
    public void paintComponent() {
        drawSectionA();
        repairStation.draw();
    }
    public void drawSectionA(){
        changeColor(Color.WHITE);
        clearBackground(width(),height());
        drawFloor();
        drawTank();
        drawWeapon();
        drawSmoke();
        if (wall.getBlocks().size() !=0) {
            drawWall();
        }
        drawExplosion();

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
        if(e.getKeyCode() == KeyEvent.VK_R && !GameOver)    {
            init();
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
        mFrame.setLocation(50,20);
        //mFrame.getGraphicsConfiguration().getDevice().setFullScreenWindow(mFrame);
    }

    public void ConstructWall(){
        wall.newBlock(100,100,80,80,false);
        wall.newBlock(500,500,80,80,false);
    }

    static class Smoke{
        double x;
        double y;
        double w;
        double h;
        float scale;
        float alpha;
        float time;
        boolean Active = false;
    }

    void initSmoke(){
        for (int i = 0;i<=24; i++ ) {
            Images.add(loadImage(Resource+BlackSmoke + i +".png"));
        }
    }
    Image[] explosionImages = new Image[16];
    Image explosionSpriteSheet = loadImage(Resource+"explosion.png");
    public void initExplosion() {

        int n = 0;
        for(int y = 0; y < 128; y += 32) {
            for(int x = 0; x < 128; x += 32) {
                explosionImages[n] = subImage(explosionSpriteSheet, x, y, 32, 32);
                n++;
            }
        }
    }
    ArrayList<Explosion> explosions = new ArrayList<>();
    AudioClip explosionSoundEffect = loadAudio(Resource+"Explosion.WAV");
    public void createExplosion(double x, double y,double w,double h) {
        boolean explosionActive = false;
        for (Explosion explosion : explosions) {
            if (!explosion.Active) {
                explosion.x = x;
                explosion.y = y;
                explosion.w = w;
                explosion.h = h;
                explosion.time = 0;
                explosion.duration = 0.5;
                explosion.Active = true;
                playAudio(explosionSoundEffect);
                explosionActive = true;
                break;
            }
        }
        if (!explosionActive){
            Explosion explosion = new Explosion();

            explosion.x = x;
            explosion.y = y;

            explosion.w = w;
            explosion.h = h;

            explosion.time = 0;
            explosion.duration = 0.5;
            explosion.Active = true;
            explosions.add(explosion);
            playAudio(explosionSoundEffect);
        }
    }

    // Function to update the explosion
    public void updateExplosion(double dt) {
        for (Explosion explosion : explosions) {
            if (explosion.Active) {
                explosion.time += dt;

                if (explosion.time >= explosion.duration) {
                    explosion.Active = false;
                }
            }
        }
    }


    public int getAnimationFrame(double timer, double duration, int numFrames) {

        int i = (int)floor(((timer % duration) / duration) * numFrames);

        if(i >= numFrames) {
            i = numFrames-1;
        }
        return i;
    }

    public void drawExplosion() {
        for (Explosion explosion : explosions) {
            if (explosion.Active) {
                saveCurrentTransform();

                translate(explosion.x, explosion.y);

                int i = getAnimationFrame(explosion.time, explosion.duration, 16);
                drawImage(explosionImages[i], -explosion.w/2, -explosion.h/2, explosion.w, explosion.h);

                restoreLastTransform();
            }
        }
    }


    static class Explosion{
        double x;
        double y;
        double w;
        double h;
        double time;
        double duration;
        boolean Active = false;
    }

}
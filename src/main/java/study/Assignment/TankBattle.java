//Name:Xuanyu Gao ID:22009349
//Name:Junkai Wang ID:22009330
//Name:Xiaohan Liu ID:22009327
//Name:Yu Yang ID:22009383

package study.Assignment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class TankBattle extends GameEngine{
    boolean levelSelection = false;
    boolean tankSelection = false;
    boolean about = false;
    boolean help = false;
    boolean sectionClear = false;
    boolean doubleBattle = false;
    boolean Win = false;
    boolean playerAWin = false;
    boolean playerBWin = false;
    double score = 0;

    int level = 1;
    public Wall wall = new Wall();
    ArrayList<Image> Images = new ArrayList<>();
    String Resource = "src/main/resources/";
    String BlackSmoke = "Black smoke/blackSmoke";
    Image logo = loadImage(Resource+"BackGround/logo.png");
    Image BGA = loadImage(Resource+"BackGround/BG_A.png");
    Image BGB = loadImage(Resource+"BackGround/BG_B.png");
    Image BGC = loadImage(Resource+"BackGround/BG_C.png");
    Image BGD = loadImage(Resource+"BackGround/BG_D.png");
    Image BGE = loadImage(Resource+"BackGround/BG_E.png");
    Image BGF = loadImage(Resource+"BackGround/BG_F.png");
    Image BGG = loadImage(Resource+"BackGround/BG_G.png");
    Image BGH = loadImage(Resource+"BackGround/BG_H.png");
    Image LightTank = loadImage(Resource+"TanksPreview/LightTankPreview.png");
    Image MediumTank = loadImage(Resource+"TanksPreview/MediumTankPreview.png");
    Image HeavyTank = loadImage(Resource+"TanksPreview/HeavyTankPreview.png");
    //
    boolean isGameOver;
    AudioClip MenuBgm = loadAudio(Resource+ "Music/BGM.wav");
    AudioClip Level_1_Bgm = loadAudio(Resource+ "Music/Level 1.wav");
    AudioClip Level_2_Bgm = loadAudio(Resource+ "Music/Level 2.wav");
    AudioClip Level_3_Bgm = loadAudio(Resource+ "Music/Level 3.wav");
    AudioClip End_Bgm = loadAudio(Resource+ "Music/Ending.wav");

    public ArrayList<TANK> factionA = new ArrayList<>();
    public ArrayList<TANK> factionB = new ArrayList<>();
    public ArrayList<TANK> tanks = new ArrayList<>();
    RepairStation repairStation;
    int P1Choose = 0;
    int P2Choose = 0;

    public static void main(String[] args) {
        createGame(new TankBattle());
    }
    LightTank P1;
    MediumTank P1M;
    HeavyTank P1H;
    LightTank P2;
    MediumTank P2M;
    HeavyTank P2H;
    boolean pauseMenu = false;
    boolean gameMenu = true;
    ArrayList<HeavyTank> heavyTanks = new ArrayList<>();
    ArrayList<LightTank> lightTanks = new ArrayList<>();
    ArrayList<MediumTank> mediumTanks = new ArrayList<>();
    double difficult = 1;
    public void initTank(){

        P1 = new LightTank(this,difficult);
        P1M = new MediumTank(this,difficult);
        P1H = new HeavyTank(this,difficult);
        P2 = new LightTank(this,difficult);
        P2M = new MediumTank(this,difficult);
        P2H = new HeavyTank(this,difficult);

        if (P1Choose == 0) {
            initPlayerA(P1,10,20);
        }
        if (P1Choose == 1) {
            if (level == 2) {
                initPlayerA(P1M, 10, 200);
            }else {
                initPlayerA(P1M, 10, 20);
            }
        }
        if (P1Choose == 2) {
            initPlayerA(P1H,10,20);
        }
        if (doubleBattle){
            if (P2Choose == 0) {
                initPlayerB(P2,1400,800);
            }
            if (P2Choose == 1) {
                initPlayerB(P2M,1400,800);
            }
            if (P2Choose == 2) {
                initPlayerB(P2H,1400,800);
            }
        }


        if (!doubleBattle) {
            if (level == 1) {
                initLightAi(1400, 100);
                initLightAi(1400, 600);
                initLightAi(100, 900);
                repairStation = new RepairStation(this, 30, 75);

            }

            if (level == 2) {

                initMediumAi(1400, 300);
                initMediumAi(1400, 600);
                initMediumAi(100, 600);
                repairStation = new RepairStation(this, 50, 300);

            }
            if (level == 3) {
                initHeavyAi(1400, 500);
                initMediumAi(1000, 300);
                initLightAi(300, 250);
                initLightAi(300, 750);
                repairStation = new RepairStation(this, 30, 100);
            }
        }
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
    public void initPlayerB(HeavyTank tank, double x, double y){
        tank.initTank(x,y);
        factionB.add(tank);
        tanks.add(tank);
    }
    public void initPlayerB(MediumTank tank, double x, double y){
        tank.initTank(x,y);
        factionB.add(tank);
        tanks.add(tank);
    }
    public void initPlayerB(LightTank tank, double x, double y){
        tank.initTank(x,y);
        factionB.add(tank);
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
        if (!Win) {
            score = 0;
        }
        factionB.clear();
        factionA.clear();
        lightTanks.clear();
        heavyTanks.clear();
        mediumTanks.clear();
        tanks.clear();
        smokes.clear();
        explosions.clear();
        wall.removeAllBlock();

        buttons.clear();
        if (gameMenu) {
            initStartMenu();
        }else if (about){
            initAbout();
        }else if (help){
            initHelp();
        }
        else if (tankSelection){
            initTankSelection();
        }else if (levelSelection){
            initLevelSelection();
        }else if (gameStart){
            if (Win) {
                initPlayerWin();
            }else {
                initLevel();
            }
        }
    }
    boolean gameStart;
    public void initPlayerWin(){
        initButton(50, 50, 200, 40, "Back to Menu");
        stopAudioLoop(Level_1_Bgm);
        stopAudioLoop(Level_2_Bgm);
        stopAudioLoop(Level_3_Bgm);
        stopAudioLoop(MenuBgm);
        startAudioLoop(End_Bgm);
    }
    public void initTankSelection(){
        if (doubleBattle){
            initButton(50, 50, 200, 40, "Back");
            initButton(250, 730, 100, 40, "LightTank",15);
            initButton(650, 730, 100, 40, "MediumTank",15);
            initButton(1050, 730, 100, 40, "HeavyTank",15);
            initButton(1250, 910, 200, 40, "Confirm");
            initButton(350, 730, 100, 40, "LightTank",15);
            initButton(750, 730, 100, 40, "MediumTank",15);
            initButton(1150, 730, 100, 40, "HeavyTank",15);
        }else {
            initButton(50, 50, 200, 40, "Back");
            initButton(250, 730, 200, 40, "LightTank");
            initButton(650, 730, 200, 40, "MediumTank");
            initButton(1050, 730, 200, 40, "HeavyTank");
            initButton(1250, 910, 200, 40, "Confirm");
        }
    }
    public void initLevelSelection(){
        initButton(50,50,200,40,"Back");
        initButton(250,500,200,40,"Level 1");
        initButton(650,500,200,40,"Level 2");
        initButton(1050,500,200,40,"Level 3");
    }
    public void initAbout(){
        initButton(50,50,200,40,"Back");
    }
    public void initHelp(){
        initButton(50,50,200,40,"Back");
        
    }
    public void initStartMenu(){
        stopAudioLoop(MenuBgm);
        stopAudioLoop(Level_1_Bgm);
        stopAudioLoop(Level_2_Bgm);
        stopAudioLoop(Level_3_Bgm);
        stopAudioLoop(End_Bgm);
        startAudioLoop(MenuBgm);
        initButton(150,600,200,40,"Single Player");
        initButton(400,600,200,40,"Double Battles");
        initButton(650,600,200,40,"Help");
        initButton(900,600,200,40,"About");
        initButton(1150,600,200,40,"Exit");
    }
    public void initLevel(){
        if (level == 1){
            initTank();
            initSmoke();
            initExplosion();
            ConstructWall();
            initFloor();
            stopAudioLoop(Level_1_Bgm);
            stopAudioLoop(Level_2_Bgm);
            stopAudioLoop(Level_3_Bgm);
            stopAudioLoop(MenuBgm);
            stopAudioLoop(End_Bgm);
            startAudioLoop(Level_1_Bgm);
        }
        if (level == 2){
            initTank();
            initSmoke();
            initExplosion();
            ConstructWall();
            initFloor();
            stopAudioLoop(Level_2_Bgm);
            stopAudioLoop(Level_1_Bgm);
            stopAudioLoop(Level_3_Bgm);
            stopAudioLoop(MenuBgm);
            stopAudioLoop(End_Bgm);
            startAudioLoop(Level_2_Bgm);
        }
        if (level == 3){
            initTank();
            initSmoke();
            initExplosion();
            ConstructWall();
            initFloor();
            stopAudioLoop(Level_3_Bgm);
            stopAudioLoop(Level_1_Bgm);
            stopAudioLoop(Level_2_Bgm);
            stopAudioLoop(MenuBgm);
            stopAudioLoop(End_Bgm);
            startAudioLoop(Level_3_Bgm);
        }
        
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
        if (doubleBattle) {
            if (P2Choose == 0) {
                P2.updateTank(dt);
                wall.setCollides(P2);
            }
            if (P2Choose == 1) {
                P2M.updateTank(dt);
                wall.setCollides(P2M);
            }
            if (P2Choose == 2) {
                P2H.updateTank(dt);
                P2H.FindTarget(factionA, dt);
                wall.setCollides(P2H);
            }
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
        floorTopleft = new Floor(loadImage("src/main/resources/Floor/Topleft"+ level +".png"), 0, 0,this);
        floorDownleft = new Floor(loadImage("src/main/resources/Floor/Downleft"+ level +".png"), 0, 900,this);
        floorTopright = new Floor(loadImage("src/main/resources/Floor/Topright"+ level +".png"), 1400, 0,this);
        floorDownright = new Floor(loadImage("src/main/resources/Floor/Downright"+ level +".png"), 1400, 900,this);
        floorTop = new Floor(loadImage("src/main/resources/Floor/Top"+ level +".png"), 0, 0,this);
        floorDown = new Floor(loadImage("src/main/resources/Floor/Down"+ level +".png"), 0, 900,this);
        floorLeft = new Floor(loadImage("src/main/resources/Floor/Left"+ level +".png"), 0, 0,this);
        floorRight = new Floor(loadImage("src/main/resources/Floor/Right"+ level +".png"), 1400, 0,this);
        floorMiddle = new Floor(loadImage("src/main/resources/Floor/Middle"+ level +".png"), 100, 100,this);
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
        for (int i = 1; i <= 8; i++) {
            floorLeft.setPositionY(100*i);
            floorLeft.draw();
            floorRight.setPositionY(100*i);
            floorRight.draw();
        }
        for (int i = 1; i <= 13; i++) {
            floorMiddle.setPositionX(i*100);
            for (int j = 1; j <= 8; j++) {
                floorMiddle.setPositionY(j*100);
                floorMiddle.draw();
            }
        }
    }

    Image Block;
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
        }else if (P1Choose==2) {
            P1H.weapon_M.updateAmmo(dt, wall);
            P1H.weapon_L.updateAmmo(dt, wall);
            P1H.weapon_R.updateAmmo(dt, wall);
            P1H.weapon_L_L.updateAmmo(dt, wall);
            P1H.weapon_R_R.updateAmmo(dt, wall);
            P1H.UpdateDamage(factionB);

        }
        if (doubleBattle){
            if (P2Choose == 0){
                P2.weapon_M.updateAmmo(dt,wall);
                P2.UpdateDamage(factionA);
            }else if (P2Choose == 1){
                P2M.weapon_M.updateAmmo(dt,wall);
                P2M.UpdateDamage(factionA);
            }else if (P2Choose == 2) {
                P2H.weapon_M.updateAmmo(dt, wall);
                P2H.weapon_L.updateAmmo(dt, wall);
                P2H.weapon_R.updateAmmo(dt, wall);
                P2H.weapon_L_L.updateAmmo(dt, wall);
                P2H.weapon_R_R.updateAmmo(dt, wall);
                P2H.UpdateDamage(factionA);
            }
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
        if (gameStart && !pauseMenu) {
            if (!isGameOver&&!sectionClear) {
                updateLevel(dt);
                if (!doubleBattle) {
                    score = repairStation.repair(P1M,score);
                    score = repairStation.repair(P1,score);
                    score = repairStation.repair(P1H,score);
                }
            }
        }
    }

    public void updateLevel(double dt){
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
        if (doubleBattle){
            if (factionA.size() == 0){
                Win = true;
                playerAWin = false;
                playerBWin = true;
            }
            if (factionB.size() == 0){
                Win = true;
                playerAWin = true;
                playerBWin = false;
            }
        }else {
            if (factionA.size() == 0) {
                isGameOver = true;
            }
        }
        if (factionB.size() == 0){
            if (level == 3){
                Win = true;
            }else if (level == 2){
                level = 3;
                sectionClear = true;
            }
            else if (level == 1){
                level = 2;
                sectionClear = true;
            }
        }
        if (Win){
            init();
        }
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
                if (!doubleBattle) {
                    score += tank.FullHealth * (10 - difficult) * tank.IsAI;
                }
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
        changeColor(Color.RED);
        if (doubleBattle) {
            if (P2Choose == 0) {
                P2.drawTank();
            }
            if (P2Choose == 1) {
                P2M.drawTank();
            }
            if (P2Choose == 2) {
                P2H.drawTank();
            }
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
        if (gameMenu){
            drawMenu();
        }else if (about){
            drawAbout();
        }else if (help){
            drawHelp();
        }else if (tankSelection){
            drawTankSelection();
        }else if (levelSelection){
            drawLevelSelection();
        }
        if (gameStart){
            if (isGameOver){
                drawGameOver();
            }else if (sectionClear){
                drawSectionClear();
            } else {
                drawLevel();
                if (!doubleBattle) {
                    repairStation.draw();
                }
            }
            if (Win){
                drawPlayerWin();
            }
        }
    }

    public void drawTriangle(double x,double y){
        saveCurrentTransform();
        translate(x,y);
        drawLine(-10,  10,  10,  10);
        drawLine( 10,  10,   0, -20);
        drawLine(  0, -20, -10,  10);
        restoreLastTransform();
    }
    public void drawTankSelection(){
        changeBackgroundColor(Color.WHITE);
        clearBackground(width(), height());
        drawImage(BGB,0,700);
        if (doubleBattle) {
            changeColor(Color.BLACK);
            drawText(350, 200, "Select Your Tank", "Arial", 100);
            drawImage(LightTank, 250, 500, 200, 200);
            drawImage(MediumTank, 650, 500, 200, 200);
            drawImage(HeavyTank, 1050, 500, 200, 200);
            if (P1Choose == 0){
                changeColor(Color.GREEN);
                drawTriangle(300,800);
            }
            if (P1Choose == 1){
                changeColor(Color.GREEN);
                drawTriangle(700,800);
            }
            if (P1Choose == 2){
                changeColor(Color.GREEN);
                drawTriangle(1100,800);
            }
            if (P2Choose == 0){
                changeColor(Color.RED);
                drawTriangle(400,800);
            }
            if (P2Choose == 1){
                changeColor(Color.RED);
                drawTriangle(800,800);
            }
            if (P2Choose == 2){
                changeColor(Color.RED);
                drawTriangle(1200,800);
            }

        }else {
            changeColor(Color.BLACK);
            drawText(350, 200, "Select Your Tank", "Arial", 100);
            drawImage(LightTank, 250, 500, 200, 200);
            drawImage(MediumTank, 650, 500, 200, 200);
            drawImage(HeavyTank, 1050, 500, 200, 200);
            drawText(650, 290, "Difficulty:", "Arial", 20);
            drawRectangle(650, 300, 200, 20);
            drawSolidRectangle(650, 300, 200 * ((10.0 - difficult) / 10.0), 20);
            if (P1Choose == 0){
                changeColor(Color.GREEN);
                drawTriangle(350,800);
            }
            if (P1Choose == 1){
                changeColor(Color.GREEN);
                drawTriangle(750,800);
            }
            if (P1Choose == 2){
                changeColor(Color.GREEN);
                drawTriangle(1150,800);
            }
        }
        drawButton();
    }
    public void drawLevelSelection(){
        changeBackgroundColor(Color.WHITE);
        clearBackground(width(),height());
        drawImage(BGC,0,500);
        changeColor(Color.BLACK);
        drawText(500,200,"Select Level","Arial",100);
        drawButton();

    }
    public void drawMenu(){
        changeBackgroundColor(Color.WHITE);
        clearBackground(width(),height());
        drawImage(BGA,0,0);
        changeColor(Color.BLACK);
        drawImage(logo,600,250,300,300);
        drawText(350,200,"Battle Field - Tank","Arial",100);
        drawButton();

    }

    public void drawAbout(){
        changeBackgroundColor(Color.WHITE);
        clearBackground(width(),height());
        drawImage(BGE,600,50,900,900);
        changeColor(Color.BLACK);
        drawText(200,200,"Made By Group 2","Arial",100);
        drawText(200,300,"Group Member:","Arial",20);
        drawText(200,350,"Name: Xuanyu Gao ID: 22009349","Arial",20);
        drawText(200,400,"Name: Junkai Wang ID: 22009330","Arial",20);
        drawText(200,450,"Name: yu Yang ID: 22009383","Arial",20);
        drawText(200,500,"Name: Xiaohan Liu ID: 22009327","Arial",20);
        drawButton();
    }

    public void drawGameOver(){
        changeColor(Color.BLACK);
        clearBackground(width(),height());
        drawImage(BGF,700,0,1000,1000);
        drawText(50,400,"Game Over!","Arial",50);
        drawText(50,500,"Score: "+ String.format("%.2f",score),"Arial",50);
        drawText(50,600,"Press 'R' to try again!","Arial",50);
    }
    public void drawPlayerWin(){
        changeBackgroundColor(Color.WHITE);
        clearBackground(width(), height());
        if (doubleBattle){
            drawImage(BGG,0,0);
            if (playerAWin){
                drawText(400, 200, "Player 1 Win", "Arial", 100);
            }else if (playerBWin){
                drawText(400, 200, "Player 2 Win", "Arial", 100);
            }
        }else {
            drawImage(BGH,500,0,1000,1000);
            drawText(50, 400, "Player Win", "Arial", 50);
            drawText(50, 500, "Score: "+String.format("%.2f",score), "Arial", 50);
        }
        drawButton();
    }
    public void drawSectionClear(){
        clearBackground(width(),height());
        changeColor(Color.BLACK);
        drawImage(BGH,500,0,1000,1000);
        drawText(50,400,"Section Clear!","Arial",50);
        drawText(50,500,"Score: "+String.format("%.2f",score),"Arial",50);
        drawText(50,600,"Press 'R' to enter next level!","Arial",50);
    }
    public void drawHelp(){
        changeBackgroundColor(Color.WHITE);
        clearBackground(width(),height());
        changeColor(Color.BLACK);
        drawImage(BGD,600,50,900,900);
        drawText(200,200,"Help","Arial",100);
        drawText(200,300,"Single-player mode:","Arial",40);

        drawText(200,350,"Use 'W,A,S,D' to control the tank.","Arial",20);
        drawText(200,400,"Press SPACE to shoot shells.","Arial",20);
        drawText(200,450,"Destroy all enemies to enter next level.","Arial",20);

        drawText(200,550,"Two-player battle mode:","Arial",40);

        drawText(200,600,"Player 1 use 'W,A,S,D' to control the tank.","Arial",20);
        drawText(200,650,"Player 2 use Arrow keys to control the tank.","Arial",20);
        drawText(200,700,"Player 1 press SPACE to shoot shells.","Arial",20);
        drawText(200,750,"Player 2 press ENTER to shoot shells.","Arial",20);
        drawText(200,800,"Destroy each other to win.","Arial",20);
        drawButton();
    }
    public void initButton(double x, double y , double w, double h, String Text ){
        Button button = new Button();
        button.x = x;
        button.y = y;
        button.w = w;
        button.h = h;
        button.size = 20;
        button.Text = Text;
        button.color = Color.BLACK;
        buttons.add(button);
    }
    public void initButton(double x, double y , double w, double h, String Text ,int size){
        Button button = new Button();
        button.x = x;
        button.y = y;
        button.w = w;
        button.h = h;
        button.size = size;
        button.Text = Text;
        button.color = Color.BLACK;
        buttons.add(button);
    }
    ArrayList<Button> buttons = new ArrayList<>();
    public void drawButton(){
        for (Button button : buttons) {
            changeColor(Color.BLACK);
            drawText(button.x, button.y, button.Text, "Arial", button.size);
            changeColor(button.color);
            drawRectangle(button.x, button.y - 30, button.w, button.h);
        }
    }

    static class Button{
        double x;
        double y;
        double w;
        double h;
        int size;
        String Text;
        Color color;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (buttons!=null) {
            for (Button button : buttons) {
                if (!(e.getX() < button.x + button.w && e.getY() < button.y - 30 + button.h && e.getX() > button.x && e.getY() > button.y - 30)) {
                    button.color = Color.BLACK;
                } else {
                    button.color = Color.WHITE;
                }

            }
        }
    }


    @Override
    public void mousePressed(MouseEvent e) {
        if (gameMenu) {
            if (!buttons.isEmpty()) {
                for (int i = 0; i < buttons.size(); i++) {
                    if (e.getX() < buttons.get(i).x + buttons.get(i).w && e.getY() < buttons.get(i).y - 30 + buttons.get(i).h && e.getX() > buttons.get(i).x && e.getY() > buttons.get(i).y - 30) {
                        if (i == 0) {
                            doubleBattle = false;
                            levelSelection = false;
                            tankSelection = true;
                            help = false;
                            about = false;
                            gameMenu = false;
                            init();
                            break;
                        }
                        else if (i == 1) {
                            difficult = 1;
                            doubleBattle = true;
                            levelSelection = false;
                            tankSelection = true;
                            help = false;
                            about = false;
                            gameMenu = false;
                            init();
                            break;
                        }else if (i == 2) {
                            levelSelection = false;
                            tankSelection = false;
                            help = true;
                            about = false;
                            gameMenu = false;
                            init();
                            break;
                        }
                        else if (i == 3) {
                            levelSelection = false;
                            tankSelection = false;
                            help = false;
                            about = true;
                            gameMenu = false;
                            init();
                            break;
                        }
                        else if (i == 4) {
                            System.exit(0);

                        }
                    }
                }
            }
        }
        if (about) {
            if (!buttons.isEmpty()) {
                for (int i = 0; i < buttons.size(); i++) {
                    if (e.getX() < buttons.get(i).x + buttons.get(i).w && e.getY() < buttons.get(i).y - 30 + buttons.get(i).h && e.getX() > buttons.get(i).x && e.getY() > buttons.get(i).y - 30) {
                        if (i == 0) {
                            levelSelection = false;
                            tankSelection = false;
                            help = false;
                            about = false;
                            gameMenu = true;
                            init();
                            break;
                        }
                    }
                }
            }
        }
        if (help) {
            if (!buttons.isEmpty()) {
                for (int i = 0; i < buttons.size(); i++) {
                    if (e.getX() < buttons.get(i).x + buttons.get(i).w && e.getY() < buttons.get(i).y - 30 + buttons.get(i).h && e.getX() > buttons.get(i).x && e.getY() > buttons.get(i).y - 30) {
                        if (i == 0) {
                            levelSelection = false;
                            tankSelection = false;
                            help = false;
                            about = false;
                            gameMenu = true;
                            init();
                            break;
                        }
                    }
                }
            }
        }
        if (tankSelection) {
            if (!doubleBattle) {
                if (e.getX() > 650 && e.getX() < 650 + 200 && e.getY() > 300 && e.getY() < 300 + 20) {
                    difficult = 10 - ((e.getX() - 650) / 200.0) * 10;
                    if (difficult > 9) {
                        difficult = 9;
                    }
                    if (difficult < 1) {
                        difficult = 1;
                    }
                }
            }
            if (!buttons.isEmpty()) {
                for (int i = 0; i < buttons.size(); i++) {
                    if (e.getX() < buttons.get(i).x + buttons.get(i).w && e.getY() < buttons.get(i).y - 30 + buttons.get(i).h && e.getX() > buttons.get(i).x && e.getY() > buttons.get(i).y - 30) {
                        if (i == 0) {
                            levelSelection = false;
                            tankSelection = false;
                            help = false;
                            about = false;
                            gameMenu = true;
                            init();
                            break;
                        }
                        if (i == 1) {
                            P1Choose = 0;
                            init();
                            break;
                        }
                        if (i == 2) {
                            P1Choose = 1;
                            init();
                            break;
                        }
                        if (i == 3) {
                            P1Choose = 2;
                            init();
                            break;
                        }
                        if (i == 4) {
                            levelSelection = true;
                            tankSelection = false;
                            help = false;
                            about = false;
                            gameMenu = false;
                            init();
                            break;
                        }
                        if (doubleBattle){
                            if (i == 5) {
                                P2Choose = 0;
                                init();
                                break;
                            }
                            if (i == 6) {
                                P2Choose = 1;
                                init();
                                break;
                            }
                            if (i == 7) {
                                P2Choose = 2;
                                init();
                                break;
                            }

                        }
                    }
                }
            }
        }
        if (levelSelection) {
            if (!buttons.isEmpty()) {
                for (int i = 0; i < buttons.size(); i++) {
                    if (e.getX() < buttons.get(i).x + buttons.get(i).w && e.getY() < buttons.get(i).y - 30 + buttons.get(i).h && e.getX() > buttons.get(i).x && e.getY() > buttons.get(i).y - 30) {
                        if (i == 0) {
                            levelSelection = false;
                            tankSelection = true;
                            help = false;
                            about = false;
                            gameMenu = false;
                            isGameOver = false;
                            init();
                            break;
                        }
                        if (i == 1) {
                            levelSelection = false;
                            tankSelection = false;
                            help = false;
                            about = false;
                            gameMenu = false;
                            gameStart = true;
                            isGameOver = false;
                            level = 1;
                            init();
                            break;
                        }
                        if (i == 2) {
                            levelSelection = false;
                            tankSelection = false;
                            help = false;
                            about = false;
                            gameMenu = false;
                            gameStart = true;
                            isGameOver = false;
                            level = 2;
                            init();
                            break;
                        }
                        if (i == 3) {
                            levelSelection = false;
                            tankSelection = false;
                            help = false;
                            about = false;
                            gameMenu = false;
                            gameStart = true;
                            isGameOver = false;
                            level = 3;
                            init();
                            break;
                        }
                    }

                }
            }
        }if (Win){
            for (int i = 0; i < buttons.size(); i++) {
                if (e.getX() < buttons.get(i).x + buttons.get(i).w && e.getY() < buttons.get(i).y - 30 + buttons.get(i).h && e.getX() > buttons.get(i).x && e.getY() > buttons.get(i).y - 30) {
                    if (i == 0){
                        Win = false;
                        startAudioLoop(End_Bgm);
                        playerBWin = false;
                        playerAWin = false;
                        levelSelection = false;
                        tankSelection = false;
                        gameStart = false;
                        isGameOver = false;
                        help = false;
                        about = false;
                        gameMenu = true;
                        init();
                        break;
                    }
                }
            }
        }
        if (pauseMenu){
            for (int i = 0; i < buttons.size(); i++) {
                if (e.getX() < buttons.get(i).x + buttons.get(i).w && e.getY() < buttons.get(i).y - 30 + buttons.get(i).h && e.getX() > buttons.get(i).x && e.getY() > buttons.get(i).y - 30) {
                    if (i == 0){
                        pauseMenu =false;
                        buttons.clear();
                        break;
                    }
                    if (i == 1){
                        pauseMenu =false;
                        gameStart = false;
                        gameMenu = true;
                        init();
                        break;
                    }
                }
            }
        }
    }


        
    public void drawLevel(){
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
        if (pauseMenu){
            drawButton();
        }
        if (!doubleBattle) {
            drawScore();
        }
    }
    public void drawScore(){
        drawText(20,20,"Score: "+ String.format("%.2f",score),"Arial",20);
    }
    public void initPause(){
        initButton(650,500,200,40,"Continue ");
        initButton(650,550,200,40,"Back to menu");
    }
    public void drawWeapon(){
    }
    boolean SpacePressed = false;
    boolean EnterPressed = false;
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_SPACE && !isGameOver)    {
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
        if (doubleBattle) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER && !isGameOver) {
                if (!EnterPressed) {
                    if (P2Choose == 0) {
                        P2.weapon_M.Fire();
                        EnterPressed = true;
                    }
                    if (P2Choose == 1) {
                        P2M.weapon_M.Fire();
                        EnterPressed = true;
                    }
                    if (P2Choose == 2) {
                        P2H.weapon_M.Fire();
                        EnterPressed = true;
                    }
                }
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE && !isGameOver && gameStart && !Win)    {
            pauseMenu = true;
            initPause();
        }
        if(e.getKeyCode() == KeyEvent.VK_R)    {
            if (isGameOver) {
                isGameOver = false;
                init();
            }else if (sectionClear){
                sectionClear = false;
                init();
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_W && !isGameOver)    {
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
        if(e.getKeyCode() == KeyEvent.VK_A && !isGameOver)  {
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
        if(e.getKeyCode() == KeyEvent.VK_S && !isGameOver)  {
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
        if(e.getKeyCode() == KeyEvent.VK_D && !isGameOver) {
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
        if (doubleBattle) {
            if (e.getKeyCode() == KeyEvent.VK_UP && !isGameOver) {
                if (P2Choose == 0) {
                    P2.UP = true;
                }
                if (P2Choose == 1) {
                    P2M.UP = true;
                }
                if (P2Choose == 2) {
                    P2H.UP = true;
                }

            }
            if (e.getKeyCode() == KeyEvent.VK_LEFT && !isGameOver) {
                if (P2Choose == 0) {
                    P2.LEFT = true;
                }
                if (P2Choose == 1) {
                    P2M.LEFT = true;
                }
                if (P2Choose == 2) {
                    P2H.LEFT = true;
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_DOWN && !isGameOver) {
                if (P2Choose == 0) {
                    P2.DOWN = true;
                }
                if (P2Choose == 1) {
                    P2M.DOWN = true;
                }
                if (P2Choose == 2) {
                    P2H.DOWN = true;
                }

            }
            if (e.getKeyCode() == KeyEvent.VK_RIGHT && !isGameOver) {
                if (P2Choose == 0) {
                    P2.RIGHT = true;
                }
                if (P2Choose == 1) {
                    P2M.RIGHT = true;
                }
                if (P2Choose == 2) {
                    P2H.RIGHT = true;
                }

            }
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_W && !isGameOver)    {
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
        if(e.getKeyCode() == KeyEvent.VK_A && !isGameOver)  {
            if (P1Choose==0) {
                P1.LEFT = false;
            }if (P1Choose==1) {
                P1M.LEFT = false;
            }if (P1Choose==2) {
                P1H.LEFT = false;
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_S && !isGameOver)  {
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
        if(e.getKeyCode() == KeyEvent.VK_D && !isGameOver) {
            if (P1Choose==0) {
                P1.RIGHT = false;
            }if (P1Choose==1) {
                P1M.RIGHT = false;
            }if (P1Choose==2) {
                P1H.RIGHT = false;
            }
        }if (doubleBattle) {
            if (e.getKeyCode() == KeyEvent.VK_UP && !isGameOver) {
                if (P2Choose == 0) {
                    P2.UP = false;
                    P2.velocity.setX(0);
                    P2.velocity.setY(0);
                }
                if (P2Choose == 1) {
                    P2M.UP = false;
                    P2M.velocity.setX(0);
                    P2M.velocity.setY(0);
                }
                if (P2Choose == 2) {
                    P2H.UP = false;
                    P2H.velocity.setX(0);
                    P2H.velocity.setY(0);
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_LEFT && !isGameOver) {
                if (P2Choose == 0) {
                    P2.LEFT = false;
                }
                if (P2Choose == 1) {
                    P2M.LEFT = false;
                }
                if (P2Choose == 2) {
                    P2H.LEFT = false;
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_DOWN && !isGameOver) {
                if (P2Choose == 0) {
                    P2.DOWN = false;
                    P2.velocity.setX(0);
                    P2.velocity.setY(0);
                }
                if (P2Choose == 1) {
                    P2M.DOWN = false;
                    P2M.velocity.setX(0);
                    P2M.velocity.setY(0);
                }
                if (P2Choose == 2) {
                    P2H.DOWN = false;
                    P2H.velocity.setX(0);
                    P2H.velocity.setY(0);
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_RIGHT && !isGameOver) {
                if (P2Choose == 0) {
                    P2.RIGHT = false;
                }
                if (P2Choose == 1) {
                    P2M.RIGHT = false;
                }
                if (P2Choose == 2) {
                    P2H.RIGHT = false;
                }
            }
            if(e.getKeyCode() == KeyEvent.VK_ENTER && !isGameOver)    {
                EnterPressed = false;
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_SPACE && !isGameOver)    {
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
        if(level == 1){
            Block = loadImage(Resource + "block/wall"+level+".png");
            for (int i = 0; i <= 14 ; i++) {
                if (i % 2 == 0){
                    wall.newBlock(50+50*i,150,100,100,false);
                    wall.newBlock(750+50*i,450,100,100,false);
                    wall.newBlock(50+50*i,750,100,100,false);
                }
            }

        }
        if(level == 2){
            Block = loadImage(Resource + "block/wall"+level+".png");
            for (int i = 0; i <= 4 ; i++) {

                    wall.newBlock(50+100*i,150,100,100,false);
                    wall.newBlock(50+100*i,550,100,100,false);
                    wall.newBlock(50+100*i,950,100,100,false);
                    wall.newBlock(1450-100*i,150,100,100,false);
                    wall.newBlock(1450-100*i,550,100,100,false);
                    wall.newBlock(1450-100*i,950,100,100,false);

            }

        }
        if(level == 3){
            Block = loadImage(Resource + "block/wall"+level+".png");
            for (int i = 0; i <= 3; i++) {
                wall.newBlock(200,100*i,100,100,false);
                wall.newBlock(750,100*i,100,100,false);
                //wall.newBlock(1300,100*i,100,100,false);
                wall.newBlock(200,650+100*i,100,100,false);
                wall.newBlock(750,650+100*i,100,100,false);
               // wall.newBlock(1300,650+100*i,100,100,false);
            }
        }

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
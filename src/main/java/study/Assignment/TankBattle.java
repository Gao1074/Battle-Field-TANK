package study.Assignment;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class TankBattle extends GameEngine{
    boolean levelSelection = false;
    boolean tankSelection = false;
    boolean about = false;
    boolean help = false;

    int level = 1;
    public Wall wall = new Wall();
    ArrayList<Image> Images = new ArrayList<>();
    String Resource = "src/main/resources/";
    String BlackSmoke = "Black smoke/blackSmoke";
    Image LightTank = loadImage(Resource+"TanksPreview/LightTankPreview.png");
    Image MediumTank = loadImage(Resource+"TanksPreview/MediumTankPreview.png");
    Image HeavyTank = loadImage(Resource+"TanksPreview/HeavyTankPreview.png");
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
    boolean gameMenu = true;
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
        if (level == 1){
            initLightAi(300,300);
            initLightAi(600,300);
            initLightAi(900,300);

        }

        if (level == 2){

            initMediumAi(300,300);
            initMediumAi(600,300);
            initMediumAi(900,300);

        }
        if (level == 3){
            initHeavyAi(900,900);
            initMediumAi(600,300);
            initLightAi(300,300);
            initLightAi(900,300);
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

        buttons.clear();
        if (gameMenu) {
            initStartMenu();
        }else if (about){
            initAbout();
        }else if (help){
            initHelp();
        }else if (tankSelection){
            initTankSelection();
        }else if (levelSelection){
            initLevelSelection();
        }else if (gameStart){
            initLevel();
        }
    }
    boolean gameStart;
    public void initTankSelection(){
        initButton(50,50,200,40,"Back");
        initButton(250,730,200,40,"LightTank");
        initButton(650,730,200,40,"MediumTank");
        initButton(1050,730,200,40,"HeavyTank");
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
        initButton(200,500,200,40,"GameStart");
        initButton(200,550,200,40,"Help");
        initButton(200,600,200,40,"About");
        initButton(200,650,200,40,"Exit");
    }
    public void initLevel(){
        if (level == 1){
            initTank();
            initSmoke();
            initExplosion();
            ConstructWall();
            initFloor();
        }
        if (level == 2){
            initTank();
            initSmoke();
            initExplosion();
            ConstructWall();
            initFloor();
        }
        if (level == 3){
            initTank();
            initSmoke();
            initExplosion();
            ConstructWall();
            initFloor();
        }
        
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
        if (gameStart) {
            updateLevel(dt);
            repairStation.repair(P1M);
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
        if (factionB.size() == 0 && level == 1){
            level = 2;
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
            drawLevel();
            repairStation.draw();
        }

    
    }
    public void drawTankSelection(){
        changeBackgroundColor(Color.WHITE);
        clearBackground(width(),height());
        changeColor(Color.BLACK);
        drawText(200,200,"Select Your Tank","Arial",100);
        drawImage(LightTank,250,500,200,200);
        drawImage(MediumTank,650,500,200,200);
        drawImage(HeavyTank,1050,500,200,200);
        drawButton();

    }
    public void drawLevelSelection(){
        changeBackgroundColor(Color.WHITE);
        clearBackground(width(),height());
        changeColor(Color.BLACK);
        drawText(200,200,"TankBattle","Arial",100);
        drawButton();

    }
    public void drawMenu(){
        changeBackgroundColor(Color.WHITE);
        clearBackground(width(),height());
        changeColor(Color.BLACK);
        drawText(200,200,"TankBattle","Arial",100);
        drawButton();

    }

    public void drawAbout(){
        changeBackgroundColor(Color.WHITE);
        clearBackground(width(),height());
        changeColor(Color.BLACK);
        drawText(200,200,"Made By Group 2","Arial",100);
        drawText(200,300,"Group Member:","Arial",20);
        drawText(200,350,"Name: Xuanyu Gao ID: 22009349","Arial",20);
        drawText(200,400,"Name: Junkai Wang ID: 22009330","Arial",20);
        drawText(200,450,"Name: yu Yang ID: 22009383","Arial",20);
        drawText(200,500,"Name: Xiaohan Liu ID: 22009327","Arial",20);
        drawButton();
    }

    public void drawHelp(){
        changeBackgroundColor(Color.WHITE);
        clearBackground(width(),height());
        changeColor(Color.BLACK);
        drawText(200,200,"Help","Arial",100);

        drawButton();
    }
    public void initButton(double x, double y , double w, double h, String Text ){
        Button button = new Button();
        button.x = x;
        button.y = y;
        button.w = w;
        button.h = h;
        button.Text = Text;
        button.color = Color.BLACK;
        buttons.add(button);
    }
    ArrayList<Button> buttons = new ArrayList<>();
    public void drawButton(){
        for (Button button : buttons) {
            changeColor(Color.BLACK);
            drawText(button.x, button.y, button.Text, "Arial", 20);
            changeColor(button.color);
            drawRectangle(button.x, button.y - 30, button.w, button.h);
        }
    }

    static class Button{
        double x;
        double y;
        double w;
        double h;
        String Text;
        Color color;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        for (Button button : buttons){
            if (!(e.getX() < button.x + button.w && e.getY() < button.y - 30 + button.h && e.getX() > button.x && e.getY() > button.y - 30)){
                button.color = Color.BLACK;
            }else {
                button.color = Color.WHITE;
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
                            levelSelection = false;
                            tankSelection = true;
                            help = false;
                            about = false;
                            gameMenu = false;
                            init();
                            break;
                        }else if (i == 1) {
                            levelSelection = false;
                            tankSelection = false;
                            help = true;
                            about = false;
                            gameMenu = false;
                            init();
                            break;
                        }
                        else if (i == 2) {
                            levelSelection = false;
                            tankSelection = false;
                            help = false;
                            about = true;
                            gameMenu = false;
                            init();
                            break;
                        }
                        else if (i == 3) {
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
                            levelSelection = true;
                            tankSelection = false;
                            help = false;
                            about = false;
                            gameMenu = false;
                            init();
                            break;
                        }
                        if (i == 2) {
                            P1Choose = 1;
                            levelSelection = true;
                            tankSelection = false;
                            help = false;
                            about = false;
                            gameMenu = false;
                            init();
                            break;
                        }
                        if (i == 3) {
                            P1Choose = 2;
                            levelSelection = true;
                            tankSelection = false;
                            help = false;
                            about = false;
                            gameMenu = false;
                            init();
                            break;
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
                            level = 3;
                            init();
                            break;
                        }
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
        if(level == 1){
            Block = loadImage(Resource + "block/wall"+level+".png");
            wall.newBlock(50,50,100,100,false);
            wall.newBlock(500,500,100,100,false);
        }
        if(level == 2){
            Block = loadImage(Resource + "block/wall"+level+".png");
        }
        if(level == 3){
            Block = loadImage(Resource + "block/wall"+level+".png");
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
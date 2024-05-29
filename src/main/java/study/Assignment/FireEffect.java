package study.Assignment;

import java.awt.*;

public class FireEffect {
    final Image[] fireEffectImage = new Image[3];
    Position position = new Position() {
        @Override
        public double getX() {
            return super.getX();
        }
    };
    Size size = new Size() {
        @Override
        public double getRadius() {
            return super.getRadius();
        }
    };
    double time;
    double duration;
    boolean Active = false;
    double Angle;
    FireEffect(GameEngine gameEngine){
        time = 0;
        duration = 0.3;
        Active = false;
        fireEffectImage[0] = gameEngine.loadImage("src/main/resources/FireEffect/Flash_A_1.png");
        fireEffectImage[1] = gameEngine.loadImage("src/main/resources/FireEffect/Flash_A_2.png");
        fireEffectImage[2] = gameEngine.loadImage("src/main/resources/FireEffect/Flash_A_3.png");
    }


}

package study.Assignment;

public class MediumTank extends TANK{
    MediumTank(GameEngine gameEngine) {
        super(gameEngine);
        image = gameEngine.loadImage("Medium");
    }
}

package study.Assignment;

public class ATAmmo extends Ammo{
    ATAmmo(GameEngine gameEngine) {
        super(gameEngine);
        image = gameEngine.subImage(gameEngine.ImageSheet,480,410,45,95);
    }
}

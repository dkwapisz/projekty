package model.enemy;

import javafx.scene.layout.Pane;

import java.util.Random;

public class Crab extends Enemy{

    public Crab(double x, double y, Pane mainLayer) {
        super(x, y, "graphics/enemies/crab.png", "graphics/enemies/crab.png", null, null, mainLayer);
        setFollowing(false);
        setFlying(false);
        setShooting(false);
        randomVel();
        setRemainingHealth(30);
        setDmg(1);
    }

    private void randomVel() {
        Random random = new Random();
        if(random.nextBoolean()) {
            setVelX(6);
            setVelY(0);
        } else {
            setVelX(0);
            setVelY(6);
        }
    }

}

package model.item.guns;

import javafx.scene.layout.Pane;

public class Stg44 extends Gun {

    public Stg44(double x, double y, Pane layer) {
        super(x, y, "graphics/items/guns/stg44.png", "graphics/items/bullets/stg44Bullet.png", layer);
        setAmmo(30);
        setBulletVel(18);
        setCooldownShot(200);
        setDmg(9);
        setGunName("STG-44");
    }

}
package dev;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.hero.Hero;

import java.util.HashMap;

public class HealthBar {

    private final Hero hero;
    private int actualHealth;
    private HashMap<Integer,ImageView> map = new HashMap<>();

    public HealthBar(Hero hero){
        this.hero = hero;
        actualHealth = hero.getRemainingLives();
        generateHealthBar();
    }

    private void generateHealthBar(){
        int x = 0;
        int y = 0;
        int key = 0;
        for (int i = 0; i < hero.getRemainingLives()/2; i++) {
            ImageView health1 = new ImageView(new Image("graphics/interface/halfHeart1.png"));
            ImageView health2 = new ImageView(new Image("graphics/interface/halfHeart2.png"));
            this.map.put(key, health1);
            this.map.put(key + 1, health2);
            key += 2;
            health1.relocate(814 + x, 200 + y);
            health2.relocate(814 + x, 200 + y);
            this.hero.getLayer().getChildren().add(health1);
            this.hero.getLayer().getChildren().add(health2);
            x += 34;
            if (i == 4) {
                y += 35;
                x = 0;
            }
        }
    }

    public void updateHealthBar() {
        int healthDiff = hero.getRemainingLives() - actualHealth;
        if (healthDiff < 0) { // strata hp
            for (int i = hero.getRemainingLives(); i < 20; i++) {
                this.hero.getLayer().getChildren().remove(map.get(i));
            }
        } else if (healthDiff > 0) { // zysk hp
            for (int i = actualHealth; i < hero.getRemainingLives(); i++) {
                this.hero.getLayer().getChildren().add(map.get(i));
            }
        }
        actualHealth = hero.getRemainingLives();
    }
}

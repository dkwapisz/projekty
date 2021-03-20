package map;

import javafx.scene.layout.Pane;
import model.block.Block;
import model.block.SoftBlock;
import model.block.SolidBlock;
import model.enemy.Enemy;
import model.enemy.EnemyTest;
import model.item.Fish;
import model.item.Item;
import model.item.guns.*;

import java.util.ArrayList;
import java.util.Random;

public class MapGenerator {

    private ArrayList<Room> roomList = new ArrayList<>();
    private int nrOfRooms;              //pierwiastek z liczby pokoi
    private Door door1;
    private Door door2;
    private Door door3;
    private Door door4;
    private Random random = new Random();
    private Pane layer;

    public MapGenerator(int nrOfRooms, Pane layer) {
        this.nrOfRooms = nrOfRooms;
        this.layer = layer;

        door1 = new Door(360, 0, "/graphics/doorH.png",  layer, 1);         // góra
        door2 = new Door(0, 360, "/graphics/doorV.png",  layer, 2);        // lewo
        door3 = new Door(360, 768, "/graphics/doorH.png",   layer, 3);        // dół
        door4 = new Door(768, 360, "/graphics/doorV.png",   layer, 4);     // prawo
        door3.getImageView().setRotate(180);
        door4.getImageView().setRotate(180);
        generateMap();
    }


    private void generateMap() {
        int k = 0;
        for(int i=0; i < nrOfRooms; i++) {
            for(int j=0; j < nrOfRooms; j++) {
                ArrayList<Door> doors = new ArrayList<>();
                if((i == 0 || i == nrOfRooms-1) || (j == 0 || j == nrOfRooms-1)) {
                    if((i == 0 || i == nrOfRooms-1) && (j == 0 || j == nrOfRooms-1)) {
                        if(i == 0 && j == 0) {
                            doors.add(door3);
                            doors.add(door4);
                            roomList.add(new Room(doors, false, k, enemiesGenerator(), new ArrayList<>(), new ArrayList<>()));
                        }
                        else if(i == nrOfRooms-1 && j == 0) {
                            doors.add(door2);
                            doors.add(door3);
                            roomList.add(new Room(doors, false, k, enemiesGenerator(), new ArrayList<>(), new ArrayList<>()));
                        }
                        else if(i == 0 && j == nrOfRooms-1) {
                            doors.add(door1);
                            doors.add(door4);
                            roomList.add(new Room(doors, false, k, enemiesGenerator(), new ArrayList<>(), new ArrayList<>()));
                        }
                        else if(i == nrOfRooms-1 && j == nrOfRooms-1) {
                            doors.add(door1);
                            doors.add(door2);
                            roomList.add(new Room(doors, false, k, enemiesGenerator(), new ArrayList<>(), new ArrayList<>()));
                        }
                    } else {
                        if(i == 0) {
                            doors.add(door1);
                            doors.add(door3);
                            doors.add(door4);
                            roomList.add(new Room(doors, false, k, enemiesGenerator(), new ArrayList<>(), new ArrayList<>()));
                        }
                        else if(i == nrOfRooms - 1) {
                            doors.add(door1);
                            doors.add(door2);
                            doors.add(door3);
                            roomList.add(new Room(doors, false, k, enemiesGenerator(), new ArrayList<>(), new ArrayList<>()));
                        }
                        else if(j == 0) {
                            doors.add(door2);
                            doors.add(door3);
                            doors.add(door4);
                            roomList.add(new Room(doors, false, k, enemiesGenerator(), new ArrayList<>(), new ArrayList<>()));
                        }
                        else if(j == nrOfRooms-1) {
                            doors.add(door1);
                            doors.add(door2);
                            doors.add(door4);
                            roomList.add(new Room(doors, false, k, enemiesGenerator(), new ArrayList<>(), new ArrayList<>()));
                        }
                    }
                } else {
                    doors.add(door1);
                    doors.add(door2);
                    doors.add(door3);
                    doors.add(door4);
                    if(k == (nrOfRooms*nrOfRooms-1)/2) {
                        roomList.add(new Room(doors, true, k, new ArrayList<>(), itemsGenerator(), BlockGenerator()));

                    } else {
                        roomList.add(new Room(doors, false, k, enemiesGenerator(), new ArrayList<>(), new ArrayList<>()));
                    }
                }
                k++;
            }
        }
    }

    private ArrayList<Item> itemsGenerator() {
        ArrayList<Item> items = new ArrayList<>();

        items.add(new Uzi(200, 200, layer));
        items.add(new Ak47(200, 300, layer));
        items.add(new SniperRifle(200, 400, layer));
        items.add(new Deagle(200, 500, layer));
        items.add(new PlasmaGun(200, 600, layer));
        items.add(new Fish(100,200, layer));

        return items;
    }

    private ArrayList<Block> BlockGenerator() {
        ArrayList<Block> blocks = new ArrayList<>();

        blocks.add(new SolidBlock(500,500,"graphics/blocks/SolidBlock.png",layer));
        blocks.add(new SolidBlock(500,580,"graphics/blocks/SolidBlock.png",layer));
        blocks.add(new SoftBlock(300,400, layer));

        return blocks;
    }

    private ArrayList<Enemy> enemiesGenerator(){
        ArrayList<Enemy> enemies = new ArrayList<>();

        enemies.add(new EnemyTest(100+random.nextInt(600),100+random.nextInt(600), "/graphics/hero/otterStatic.gif", "/graphics/hero/otterMoving.gif", layer));
        //enemies.add(new EnemyTest(100+random.nextInt(600),100+random.nextInt(600),"/graphics/hero/otterStatic.gif","/graphics/hero/otterMoving.gif", layer));

        return enemies;
    }

    public void updateEnemy(Room actualRoom){
        if (!actualRoom.getEnemies().isEmpty()) {
            for (Enemy enemy : actualRoom.getEnemies()) {
                if(enemy.getX() + enemy.getVelX() < 30 || enemy.getX() + enemy.getVelX() > 770 - enemy.getImageStatic().getHeight()/4){
                    enemy.setVelX(-enemy.getVelX());
                }
                enemy.updateLocation();
            }
        }
        actualRoom.openDoor();
    }


    public ArrayList<Room> getRoomList() {
        return roomList;
    }
    public void setRoomList(ArrayList<Room> roomList) {
        this.roomList = roomList;
    }

    public int getNrOfRooms() {
        return nrOfRooms;
    }
    public void setNrOfRooms(int nrOfRooms) {
        this.nrOfRooms = nrOfRooms;
    }

    public Door getDoor1() {
        return door1;
    }
    public void setDoor1(Door door1) {
        this.door1 = door1;
    }

    public Door getDoor2() {
        return door2;
    }
    public void setDoor2(Door door2) {
        this.door2 = door2;
    }

    public Door getDoor3() {
        return door3;
    }
    public void setDoor3(Door door3) {
        this.door3 = door3;
    }

    public Door getDoor4() {
        return door4;
    }
    public void setDoor4(Door door4) {
        this.door4 = door4;
    }

    public Pane getLayer() {
        return layer;
    }
    public void setLayer(Pane layer) {
        this.layer = layer;
    }
}
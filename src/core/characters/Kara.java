package core.characters;

import javafx.scene.canvas.GraphicsContext;
import libs.Configs;
import libs.Sprite;
import javafx.scene.image.Image;

public class Kara extends Sprite {

    GraphicsContext gc;
    KaraPosition position = KaraPosition.EAST; // default position is east
    int numRun = 0;

    // current coordinates

    public Kara() {
        super.imgPath = "/core/characters/kara_east.png";
        super.setImage(new Image(imgPath));

        this.gc = Configs.gc;
    }

    public void eat() {
        Configs.movements.add(KaraMovement.EATING);
    }

    public void move(KaraMovement karaMovement) {
        switch (karaMovement) {
            case START:
                // do nothing, just render
                break;
            case FORWARD:
                if (!hitTree()) {
                    switch (position) {
                        case EAST:
                            Configs.xCoord++;
                            break;
                        case WEST:
                            Configs.xCoord--;
                            break;
                        case NORTH:
                            Configs.yCoord--;
                            break;
                        case SOUTH:
                            Configs.yCoord++;
                            break;
                    }
                } else {
                    System.out.println("There's tree in front");
                }
                break;
            case MOVELEFT:
                switch (position) {
                    case NORTH:
                        position = KaraPosition.WEST;
                        super.imgPath = "/core/characters/kara_west.png";
                        break;
                    case EAST:
                        position = KaraPosition.NORTH;
                        super.imgPath = "/core/characters/kara_north.png";
                        break;
                    case WEST:
                        position = KaraPosition.SOUTH;
                        super.imgPath = "/core/characters/kara_south.png";
                        break;
                    case SOUTH:
                        position = KaraPosition.EAST;
                        super.imgPath = "/core/characters/kara_east.png";
                        break;
                }
                super.setImage(new Image(imgPath));
                break;
            case MOVERIGHT:
                switch (position) {
                    case NORTH:
                        position = KaraPosition.EAST;
                        super.imgPath = "/core/characters/kara_east.png";
                        break;
                    case EAST:
                        position = KaraPosition.SOUTH;
                        super.imgPath = "/core/characters/kara_south.png";
                        break;
                    case WEST:
                        position = KaraPosition.NORTH;
                        super.imgPath = "/core/characters/kara_north.png";
                        break;
                    case SOUTH:
                        position = KaraPosition.WEST;
                        super.imgPath = "/core/characters/kara_west.png";
                        break;
                }
                super.setImage(new Image(imgPath));
                break;
            case EATING:
                eatMushroom();
                break;
        }
    }

    public boolean hitTree() {
        boolean hit = false;
        switch (position) {
            case SOUTH:
                if (Configs.tiles[Configs.xCoord][Configs.yCoord + 1] == 1)
                    hit = true;
                else
                    hit = false;
                break;
            case WEST:
                if (Configs.tiles[Configs.xCoord - 1][Configs.yCoord] == 1)
                    hit = true;
                else
                    hit = false;
                break;
            case EAST:
                if (Configs.tiles[Configs.xCoord + 1][Configs.yCoord] == 1)
                    hit = true;
                else
                    hit = false;
                break;
            case NORTH:
                if (Configs.tiles[Configs.xCoord][Configs.yCoord - 1] == 1)
                    hit = true;
                else
                    hit = false;
                break;
        }
        return hit;
    }

    public void show() {
        this.resize(Configs.tileSize, Configs.tileSize);
        this.render(gc, Configs.xCoord * Configs.tileSize, Configs.yCoord * Configs.tileSize);
    }

    public boolean onMushroom() {
        if (Configs.tiles[Configs.xCoord][Configs.yCoord] == 2)
            return true;
        else
            return false;
    }

    public void eatMushroom() {
        if (onMushroom()) {
            Configs.tiles[Configs.xCoord][Configs.yCoord] = 0;
            System.out.println("Eating mushroom");
        } else
            System.out.println("No mushroom to eat");
    }

    public void start() {
        Configs.movements.add(KaraMovement.START);
    }

    public void forward() {
        Configs.movements.add(KaraMovement.FORWARD);
    }

    public void turnLeft() {
        Configs.movements.add(KaraMovement.MOVELEFT);
    }

    public void turnRight() {
        Configs.movements.add(KaraMovement.MOVERIGHT);
    }

}

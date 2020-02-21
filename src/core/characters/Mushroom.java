package core.characters;

import javafx.scene.image.Image;
import libs.Sprite;

public class Mushroom extends Sprite {
    public Mushroom() {
        super.imgPath = "/core/characters/mushroom.png";
        super.setImage(new Image(imgPath));
    }
}

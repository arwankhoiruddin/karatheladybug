package core.characters;

import javafx.scene.image.Image;
import libs.Sprite;

public class Field extends Sprite {

    public Field() {
        super.imgPath = "/core/characters/field_white.png";
        super.setImage(new Image(imgPath));
    }

}

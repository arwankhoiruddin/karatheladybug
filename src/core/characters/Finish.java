package core.characters;

import javafx.scene.image.Image;
import libs.Sprite;

public class Finish extends Sprite {
    public Finish() {
        super.imgPath = "/core/characters/finish.png";
        super.setImage(new Image(imgPath));
    }
}

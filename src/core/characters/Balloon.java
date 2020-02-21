package core.characters;

import libs.Sprite;
import javafx.scene.image.Image;


public class Balloon extends Sprite {

    public Balloon()  {
        super.imgPath = "/core/characters/balloon.png";
        super.setImage(new Image(imgPath));
    }

}

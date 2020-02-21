package core.characters;

import javafx.scene.image.Image;
import libs.Sprite;

public class Tree extends Sprite {
    public Tree() {
        super.imgPath = "/core/characters/tree.png";
        super.setImage(new Image(imgPath));

    }
}

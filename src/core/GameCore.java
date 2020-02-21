package core;

import core.characters.Kara;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import libs.Configs;
import libs.CoreFunc;
import libs.GameText;

import java.util.ArrayList;

public class GameCore implements CoreFunc {

    // Main Game variables should be here

    @Override
    public void karaMovements() {
        // put your code here
        Kara kara = new Kara();
        kara.start();
        kara.forward();
        kara.eat();
        kara.turnRight();
        kara.forward();
        kara.turnLeft();
        kara.turnRight();
    }

}

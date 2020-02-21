package core;

import core.characters.Kara;
import core.characters.KaraPosition;
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

        int mushroomFound = 0;
        while (!kara.finish()) {
            // eat the mushroom if any
            if (kara.onMushroom()) {
                kara.eat();
                mushroomFound++;
            }

            // sunnah to turn right first
            if (kara.hitTree()) {
                // sunnah to choose right
                if (kara.canTurnRight()) {
                    kara.turnRight();
                } else {
                    if (kara.canTurnLeft()) {
                        kara.turnLeft();
                    } else {
                        System.out.println("dead end");
                    }
                }
            }

            kara.forward();
        }
        System.out.println("Mushroom found: " + mushroomFound);
    }

}

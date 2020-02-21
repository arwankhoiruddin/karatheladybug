package libs;

import core.characters.KaraStatus;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.LinkedList;

public class Configs {
    public static String appTitle = "Kara The Lady Bug";

    public static int appWidth = 1400;
    public static int appHeight = 1400;
    public static int tileSize = 100;

    // Kara Initial Position
    public static int xCoord = 1;
    public static int yCoord = 1;

    public static String musicPath = "/core/unity.mp3";

    // global variables, don't change this part
    public static GraphicsContext gc;
    public static int[][] tiles;
    public static boolean moveKara = true;

    public static LinkedList movements = new LinkedList();
}

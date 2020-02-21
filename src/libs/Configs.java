package libs;

import javafx.scene.canvas.GraphicsContext;

import java.util.LinkedList;

public class Configs {
    public static String appTitle = "Kara The Lady Bug";

    public static int appWidth = 1400;
    public static int appHeight = 1400;
    public static int tileSize = 50;

    // Kara Initial Position
    public static int xCoord = 1;
    public static int yCoord = 1;

    public static String musicPath = "/core/unity.mp3";

    // global variables, don't change this part
    public static GraphicsContext gc;
    public static int[][] tiles;
    public static int[][] origTiles;
    public static LinkedList movements = new LinkedList();
}

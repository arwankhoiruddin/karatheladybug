import core.GameCore;
import core.characters.*;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;
import libs.Configs;

import java.io.File;
import java.util.Scanner;

public class Main extends Application {
    Canvas canvas = new Canvas(Configs.appWidth, Configs.appHeight);

    public static void main(String[] args) {
        int[][] tempLayout = new int[100][100];
        File file = new File("layout.txt");
        try {
            Scanner sc = new Scanner(file);
            int yTile = 0;
            int xTile = 0;
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] tiles = line.split(" ");

                for (xTile = 0; xTile < tiles.length; xTile++) {
                    tempLayout[xTile][yTile] = Integer.parseInt(tiles[xTile]);
                }
                yTile++;
            }

            Configs.tiles = new int[xTile][yTile];
            Configs.origTiles = new int[xTile][yTile];

            // copy content from temp to tiles
            for (int i=0; i<xTile; i++) {
                for (int j=0; j<yTile; j++) {
                    Configs.tiles[i][j] = tempLayout[i][j];
                    Configs.origTiles[i][j] = tempLayout[i][j];
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle(Configs.appTitle);

        Group root = new Group();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);

        root.getChildren().add(canvas);

        GameCore gameCore = new GameCore();
        gameCore.karaMovements(); // set all the movements

        Configs.gc = canvas.getGraphicsContext2D();
        setBackground(Configs.gc);

        final long startNanoTime = System.nanoTime();

        // reset before starting
        Configs.xCoord = 1;
        Configs.yCoord = 1;

        // reset the location matrix to the original
        for (int x=0; x<Configs.tiles.length; x++) {
            for (int y=0; y<Configs.tiles[0].length; y++) {
                Configs.tiles[x][y] = Configs.origTiles[x][y];
            }
        }

        new AnimationTimer() {
            int counter = 0;
            Kara kara = new Kara();
            public void handle(long currentNanoTime) {
                int t = Math.round((currentNanoTime - startNanoTime) / 1000000000);
                if (t != counter) { // move everything per second
                    setBackground(Configs.gc);
                    if (!Configs.movements.isEmpty()) {
                        KaraMovement movement = (KaraMovement) Configs.movements.getFirst();
                        System.out.println(movement);
                        kara.move(movement);
                        Configs.movements.removeFirst();
                    }
                    kara.show();
                    counter = t;
                }
            }
        }.start();

        primaryStage.show();
    }

    MediaPlayer musicPlayer; {
        Media mp3music = new Media(getClass().getResource(Configs.musicPath).toExternalForm());
        musicPlayer = new MediaPlayer(mp3music);
        musicPlayer.setAutoPlay(true);
        musicPlayer.setVolume(0.9);

        musicPlayer.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                musicPlayer.seek(Duration.ZERO);
            }
        });
    }

    public void setBackground(GraphicsContext gc) {
        int x = 0;
        int y = 0;

        for (x=0; x<Configs.tiles.length; x++) {
            for (y=0; y<Configs.tiles[0].length; y++) {
                Field field = new Field();
                field.resize(Configs.tileSize, Configs.tileSize);
                field.render(gc, x * Configs.tileSize, y * Configs.tileSize);

                switch (Configs.tiles[x][y]) {
                    case 1:
                        Tree tree = new Tree();
                        tree.resize(Configs.tileSize, Configs.tileSize);
                        tree.render(gc, x * Configs.tileSize, y * Configs.tileSize);
                        break;
                    case 2:
                        Mushroom mushroom = new Mushroom();
                        mushroom.resize(Configs.tileSize, Configs.tileSize);
                        mushroom.render(gc, x * Configs.tileSize, y * Configs.tileSize);
                        break;
                    case 9:
                        Finish finishFlag = new Finish();
                        finishFlag.resize(Configs.tileSize, Configs.tileSize);
                        finishFlag.render(gc, x * Configs.tileSize, y * Configs.tileSize);
                        break;
                }
            }
        }
        this.canvas.setHeight(Configs.tileSize * y);
        this.canvas.setWidth(Configs.tileSize * x);
    }

}

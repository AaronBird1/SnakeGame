package classes;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;
import javafx.scene.control.Button; //needed to use Button class
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.application.Platform;

import java.util.HashSet;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class Game extends Application {

  private Grid grid;
  private GameLoop loop;
  private GraphicsContext context;
  private String highscore = "";

  public static void main(String[] args)
  {
    Application.launch(args);
  }

  @Override
  public void start(Stage primaryStage)
  {
    StackPane mainStackPane = new StackPane(); //why use a stackpane vs a pane?
    StackPane startScreenPane = new StackPane();
    StackPane controlsStackPane = new StackPane();
    Canvas mainCanvas = new Canvas(800, 600);
    Canvas startScreenCanvas = new Canvas(800, 600);
    Canvas controlsCanvas = new Canvas(800, 600);

    context = mainCanvas.getGraphicsContext2D();

    System.out.println("Start method called");

    mainCanvas.setFocusTraversable(true); //lets keys be used to move
    mainCanvas.setOnKeyPressed(e -> {
      Character player = grid.getCharacter();
      if (loop.isKeyPressed()) {
        return;
      }
      switch (e.getCode()) {
        case UP:
          System.out.println("\n\n\nUp key pressed");
          player.setUp();
          break;
        case DOWN:
          System.out.println("\n\n\nUp key pressed");
          player.setDown();
          break;
        case LEFT:
          player.setLeft();
          break;
        case RIGHT:
          player.setRight();
          break;
        case ENTER:
          if (loop.getRunningStatus() == true) {
            reset();
            (new Thread(loop)).start();
          }
      }
    });

    if(highscore.equals("")) {
      highscore = this.getHighScore();
    }

    reset();

    Button quitButton = new Button("Quit Game");
    quitButton.setTranslateX(350);
    quitButton.setTranslateY(-280);
    quitButton.setOnAction(e -> {Platform.exit(); loop.stopRunning();});
    mainStackPane.getChildren().add(mainCanvas);
    mainStackPane.getChildren().add(quitButton);
    startScreenPane.getChildren().add(startScreenCanvas);
    controlsStackPane.getChildren().add(controlsCanvas);

    Scene startScreen = new Scene(startScreenPane);
    Scene mainScene = new Scene(mainStackPane);
    Scene controlsMenu = new Scene(controlsStackPane);

    setupStartScreen(startScreenPane, mainScene, controlsMenu, primaryStage);
    setupControlsScreen(controlsStackPane, startScreen, primaryStage);

    primaryStage.setTitle("Snake Game!");
    primaryStage.setResizable(false);
    primaryStage.setScene(startScreen);
    primaryStage.show();

    (new Thread(loop)).start(); //wat is this doing?
  }

  private void setupStartScreen(StackPane sp, Scene mainScene, Scene controlsScene, Stage primaryStage) {
    Image menubg = new Image("newsnakebackgorund.png");
    ImageView menubgiv = new ImageView(menubg);

    CustomButton startButton = new CustomButton("Start Game");
    CustomButton controlsButton = new CustomButton("Controls");
    CustomButton quitButton = new CustomButton("Quit");

    startButton.setOnAction(e -> primaryStage.setScene(mainScene));
    controlsButton.setOnAction(e -> primaryStage.setScene(controlsScene));
    quitButton.setOnAction(e -> {Platform.exit(); loop.stopRunning();});
    controlsButton.setTranslateY(75);
    quitButton.setTranslateY(150);
    sp.getChildren().add(menubgiv);
    sp.getChildren().add(startButton);
    sp.getChildren().add(controlsButton);
    sp.getChildren().add(quitButton);
  }

  private void setupControlsScreen(StackPane sp, Scene startScene, Stage primaryStage) {
    Image controlsbg = new Image("controlsbg.png");
    ImageView controlsbgiv = new ImageView(controlsbg);

    CustomButton mainMenuButton = new CustomButton("Main menu");
    mainMenuButton.setOnAction(e -> primaryStage.setScene(startScene));
    mainMenuButton.setTranslateY(-70);
    sp.getChildren().add(controlsbgiv);
    sp.getChildren().add(mainMenuButton);
  }

  private void reset() {
    grid = new Grid();
    loop = new GameLoop(grid, context, highscore);
    Painter.paint(grid, context, highscore);
  }

  public String getHighScore() {
    FileReader fr = null;
    BufferedReader br = null;
    try {
      fr = new FileReader("highscores.txt");
      br = new BufferedReader(fr);
      return br.readLine();
    }
    catch(Exception e) {
      return "0";
    }
    finally {
      try {
        br.close();
      } catch (IOException e) {
        e.printStackTrace();
      }

    }
  }

}

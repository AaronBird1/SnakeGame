package classes;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;

import java.util.HashSet;

public class GameLoop implements Runnable
{

  private Grid grid;
  private Boolean keyIsPressed;
  private Boolean running;
  private GraphicsContext context;
  private float interval = 1000.0f / 15;
  private String highscore;

  public GameLoop(final Grid grid, final GraphicsContext context, String newHighscore) {
    highscore = newHighscore;
    this.grid = grid;
    this.context = context;
    keyIsPressed = false;
    running = true;
    System.out.println("GameLoop test point 1");
  }

  @Override
  public void run() {
    System.out.println("Game running!");

    while(running == true) {
      float time = System.currentTimeMillis();

      keyIsPressed = false;
      grid.update();
      Painter.paint(grid, context, highscore);

      if(grid.checkCharacterStatus() == false) {
        System.out.println("while loop in gameloop has ended!");
        Painter.paintEndGameMessage(context);
        break;
      }

      time = System.currentTimeMillis() - time;

      if(time<interval) {
        try {
          Thread.sleep((long) (interval - time));
        }
        catch (InterruptedException ignore) {
        }
      }
    }
  }

  public boolean isKeyPressed() {
    return keyIsPressed;
  }

  public void stopRunning() {
    running = false;
  }

  public Boolean getRunningStatus() {
    return running;
  }

}

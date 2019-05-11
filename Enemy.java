package classes;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;

import java.util.HashSet;

public class Enemy {

  private int xPos;
  private int yPos;

  public void changePos(int newXPos, int newYPos) {
    xPos = newXPos;
    yPos = newYPos;
  }

  public int getXPos() {
    return xPos;
  }

  public int getYPos() {
    return yPos;
  }

}

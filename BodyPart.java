package classes;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;

import java.util.HashSet;

public class BodyPart {

  private int xPos;
  private int yPos;

  public BodyPart(int xPosition, int yPosition)
  {
    xPos = xPosition;
    yPos = yPosition;
  }

  public int getXPos() {
    return xPos;
  }

  public int getYPos() {
    return yPos;
  }

}

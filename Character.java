package classes;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;

import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;

public class Character {

  private int xSpeed;
  private int ySpeed;
  private Grid grid;
  private int xPos;
  private int yPos;
  private int EnemiesKilled;
  private ArrayList<BodyPart> bodyParts;
  private int bodyPartCount;
  private boolean alive = true;

  public Character(Grid grid, int initialX, int initialY)
  {
    xSpeed = 0;
    ySpeed = 0;
    EnemiesKilled = 0;
    bodyPartCount = 0;

    xPos = initialX;
    yPos = initialY;

    bodyParts = new ArrayList<>(bodyPartCount);

    this.grid = grid;
  }

  public void move() {
    if(bodyPartCount > 0) {
      if(checkForBodyCollision() == false){
        bodyParts.add(new BodyPart(xPos, yPos));
        bodyPartCount++;
        bodyParts.remove(0);
        bodyPartCount--;
      }
    }
    grid.checkBorder();
    xPos = xPos + xSpeed;
    yPos = yPos + ySpeed;
    System.out.println("Player is at x = " + xPos + " y = " + yPos);
    System.out.println("Player has speed x = " + xSpeed + ", y = " + ySpeed);
  }

  public Boolean checkForBodyCollision() {
    if(bodyPartCount > 2) {
      for(int i=0; i<getNumBodyParts(); i++) {
        if((xPos == bodyParts.get(i).getXPos()) && (bodyParts.get(i).getYPos() == yPos)) {
          killCharacter();
          return true;
        }
      }
    }
    return false;
  }

  public void setUp() {
    System.out.println("setUp method called");

    if (ySpeed == 1) {
      xSpeed = 0;
      ySpeed = 1;
    }
    else {
      xSpeed = 0;
      ySpeed = -1;
    }
  }

    public void setDown() {
      System.out.println("setDown method called");

      if (ySpeed == -1) {
        xSpeed = 0;
        ySpeed = -1;
      }
      else {
        xSpeed = 0;
        ySpeed = 1;
      }
    }

    public void setLeft() {
      System.out.println("setLeft method called");

      if (xSpeed == 1) {
        xSpeed = 1;
        ySpeed = 0;
      }
      else {
        xSpeed = -1;
        ySpeed = 0;
      }
    }

    public void setRight() {
      System.out.println("setRight method called");

      if (xSpeed == -1) {
        xSpeed = -1;
        ySpeed = 0;
      }
      else {
        xSpeed = 1;
        ySpeed = 0;
      }
    }

    public void stopCharacter() {
      xSpeed = 0;
      ySpeed = 0;
    }

    public int getXPos() {
      return xPos;
    }

    public int getYPos() {
      return yPos;
    }

    public int getXSpeed() {
      return xSpeed;
    }

    public int getYSpeed() {
      return ySpeed;
    }

    public void UpdateEnemiesKilled(int newNumber) {
      EnemiesKilled = newNumber;
      bodyParts.add(new BodyPart(xPos - xSpeed, yPos - ySpeed));
      bodyPartCount++;
    }

    public int getNumberEnemiesKilled() {
      return EnemiesKilled;
    }

    public int getNumBodyParts() {
      return bodyPartCount;
    }

    public ArrayList<BodyPart> getBodyPartsList() {
      return bodyParts;
    }

    public void killCharacter() {
      alive = false;
    }

    public Boolean characterStatus() {
      return alive;
    }

}

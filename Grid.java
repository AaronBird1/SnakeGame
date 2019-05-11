package classes;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;

import java.util.HashSet;
import java.util.Random;

public class Grid
{

  private int squareSize = 40;
  private int numRows;
  private int numCols;
  private Character player;
  private Enemy enemy;
  private Random rand = new Random();

  public Grid() {

    numRows = 600/squareSize;
    numCols = 800/squareSize;

    player = new Character(this, 1, 0);


    enemy = new Enemy();
    enemy.changePos(rand.nextInt(20), rand.nextInt(15));
  }

  public void checkBorder() {
    if(player.getXPos() >= ((800-squareSize)/squareSize) && player.getXSpeed() == 1) {
      player.stopCharacter();
      player.killCharacter();
      System.out.println("You collided with the border and DIED! Alive status is " + checkCharacterStatus());
    }
    if(player.getXPos() <= 0 && player.getXSpeed() == -1) {
      player.stopCharacter();
      player.killCharacter();
      System.out.println("You collided with the border and DIED! Alive status is " + checkCharacterStatus());
    }
    if(player.getYPos() <= 0 && player.getYSpeed() == -1) {
      player.stopCharacter();
      player.killCharacter();
      System.out.println("You collided with the border and DIED! Alive status is " + checkCharacterStatus());
    }
    if(player.getYPos() >= ((600-squareSize)/squareSize) && player.getYSpeed() == 1) {
      player.stopCharacter();
      player.killCharacter();
      System.out.println("You collided with the border and DIED! Alive status is " + checkCharacterStatus());
    }
  }

  public Character getCharacter() {
    return player;
  }

  public Enemy getEnemy() {
    return enemy;
  }

  public void update() {
    System.out.println("Grid update method has been called");

    if(player.getXPos() == enemy.getXPos() && player.getYPos() == enemy.getYPos()) {
      enemy.changePos(rand.nextInt(20), rand.nextInt(15));
      player.UpdateEnemiesKilled(player.getNumberEnemiesKilled() + 1);
    }
    player.move();
  }

  public Boolean checkCharacterStatus() {
    return player.characterStatus();
  }

}

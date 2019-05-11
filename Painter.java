package classes;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.HashSet;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

public class Painter
{

  private int squareSize = 40;
  private int numRows;
  private int numCols;
  private Character player;
  private static Boolean newHighscore = false;

  public static void paint(Grid grid, GraphicsContext gc, String highscore) {
    gc.setFill(Color.WHITE); //sets the colour for when we fillRect for example
    gc.fillRect(0, 0, 800, 600);

    Character player = grid.getCharacter();
    if(player.characterStatus() == true) {gc.setFill(Color.BLACK);}
    else {gc.setFill(Color.RED);}
    paintCharacter(player.getXPos(), player.getYPos(), gc);
    for(int i=0; i<player.getNumBodyParts(); i++) {
      paintCharacter(player.getBodyPartsList().get(i).getXPos(), player.getBodyPartsList().get(i).getYPos(), gc);
    }

    Enemy enemy = grid.getEnemy();
    gc.setFill(Color.GREEN);
    paintCharacter(enemy.getXPos(), enemy.getYPos(), gc);

    gc.setFill(Color.RED);
    gc.fillText("Enemies killed: " + player.getNumberEnemiesKilled(), 10, 20);
    if(player.getNumberEnemiesKilled() > Integer.parseInt(highscore)) {
      newHighscore = true;
      highscore = Integer.toString(player.getNumberEnemiesKilled());
      File highscoreFile = new File("highscores.txt");
      if(!highscoreFile.exists()) {
        try {
          highscoreFile.createNewFile();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
      FileWriter fw = null;
      BufferedWriter bw = null;
      try {
        fw = new FileWriter(highscoreFile);
        bw = new BufferedWriter(fw);
        bw.write(highscore);
      } catch(Exception e) {

      }
      finally {
        try {
          if(bw != null) {
            bw.close();
          }
        } catch  (Exception e) {

        }
      }
    }
    gc.fillText("Highscore: " + highscore, 10, 40);

  }

  private static void paintCharacter(int xPos, int yPos, GraphicsContext gc) {
    gc.fillRect(xPos * 40, yPos * 40, 35, 35);
  }

  public static void paintEndGameMessage(GraphicsContext gc) {
    gc.setFill(Color.BLACK);
    gc.setFont(Font.font ("Verdana", 20));
    if(newHighscore.equals(false)) {
      gc.fillText("You died! To play again press 'Enter'", 225, 250);
    }
    else {
      gc.fillText("Nice you got a new High Score!\nTo play again press 'Enter'", 225, 250);
    }


    newHighscore = false;
  }

}

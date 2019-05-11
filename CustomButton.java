package classes;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.control.Button;
import javafx.event.EventHandler;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;

public class CustomButton extends Button {
  private final String BUTTON_UNPRESSED_STYLE = "-fx-background-color: transparent; -fx-background-image: url('/resources/blue_button04.png');";
  private final String BUTTON_PRESSED_STYLE = "-fx-background-color: transparent; -fx-background-image: url('/resources/blue_button03.png');";

  public CustomButton(String text) {
    setText(text);
    setPrefWidth(190);
    setPrefHeight(49);
    setStyle(BUTTON_UNPRESSED_STYLE);
    initializeButtonListeners();
  }

  private void setButtonPressedStyle() {
    setStyle(BUTTON_PRESSED_STYLE);
    setPrefHeight(45);
    setLayoutY(getLayoutY() + 4);
  }

  private void setButtonUnpressedStyle() {
    setStyle(BUTTON_UNPRESSED_STYLE);
    setPrefHeight(49);
    setLayoutY(getLayoutY() - 4);
  }

  private void initializeButtonListeners() {

    setOnMousePressed(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event) {
        if(event.getButton().equals(MouseButton.PRIMARY)) {
          setButtonPressedStyle();
        }
      }
    });

    setOnMouseReleased(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event) {
        if(event.getButton().equals(MouseButton.PRIMARY)) {
          setButtonUnpressedStyle();
        }
      }
    });

    setOnMouseEntered(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event) {
        setEffect(new DropShadow());
      }
    });

    setOnMouseExited(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event) {
        setEffect(null);
      }
    });
  }


}

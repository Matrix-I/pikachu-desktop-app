package com.pika.desktop.app;

import java.awt.*;
import javax.swing.*;

public class Button extends JButton {
  private int x, y;
  private int width, height;
  private Image img;

  @Override
  public int getX() {
    return x;
  }

  @Override
  public int getHeight() {
    return height;
  }

  @Override
  public int getY() {
    return y;
  }

  public void setX(int x) {
    this.x = x;
  }

  public void setY(int y) {
    this.y = y;
  }

  public int getWidth() {
    return width;
  }

  public void setWidth(int width) {
    this.width = width;
  }

  public void setHeight(int height) {
    this.height = height;
  }

  public Image getImg() {
    return img;
  }

  public void setImg(Image img) {
    this.img = img;
  }

  public void setButton(int x, int y, int wigth, int height, Image img) {
    this.x = x;
    this.y = y;
    this.width = wigth;
    this.height = height;
    this.img = img;
  }
}

package com.pika.desktop.app;

import java.awt.*;
import javax.swing.*;

public class Pikachu {
  public static final Image[] IMG_BG_PI =
      new Image[] {
        new ImageIcon(FilePathUtils.getURL("src/imgs/square_1.png")).getImage(),
        new ImageIcon(FilePathUtils.getURL("src/imgs/square_2.png")).getImage(),
        new ImageIcon(FilePathUtils.getURL("src/imgs/square_3.png")).getImage(),
        new ImageIcon((FilePathUtils.getURL("src/imgs/squarec_1.png"))).getImage(),
        new ImageIcon((FilePathUtils.getURL("src/imgs/squarec_3.png"))).getImage()
      };

  private int size;
  private int xAxis, yAxis;
  private Image image;
  private int id;
  private int backgroundId;
  private boolean selected;
  private Position position;

  public Pikachu(Position position) {
    this.position = position;
  }

  public Pikachu(
      int size,
      int xAxis,
      int yAxis,
      Image image,
      int id,
      int backgroundId,
      boolean selected,
      Position position) {
    this.size = size;
    this.xAxis = xAxis;
    this.yAxis = yAxis;
    this.image = image;
    this.id = id;
    this.backgroundId = backgroundId;
    this.selected = selected;
    this.position = position;
  }

  public int getxAxis() {
    return xAxis;
  }

  public int getyAxis() {
    return yAxis;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setBackgroundId(int backgroundId) {
    this.backgroundId = backgroundId;
  }

  public boolean isSelected() {
    return selected;
  }

  public void setSelected(boolean selected) {
    this.selected = selected;
  }

  public Position getPosition() {
    return position;
  }

  public void setPosition(Position position) {
    this.position = position;
  }

  @Override
  public String toString() {
    return "Pikachu{"
        + "size="
        + size
        + ", xAxis="
        + xAxis
        + ", yAxis="
        + yAxis
        + ", image="
        + image
        + ", id="
        + id
        + ", backgroundId="
        + backgroundId
        + ", selected="
        + selected
        + ", position="
        + position
        + '}';
  }

  public void draw(Graphics2D g2d) {
    g2d.drawImage(IMG_BG_PI[backgroundId], xAxis, yAxis, size, size, null);
    g2d.drawImage(image, xAxis, yAxis, size, size, null);
  }

  public boolean checkMouseIn(int xM, int yM) {
    return xM >= xAxis && xM < xAxis + size && yM >= yAxis && yM < yAxis + size;
  }

  public static class PikachuBuilder {
    private int size;
    private int xAxis, yAxis;
    private Image image;
    private int id;
    private int backgroundId;
    private boolean selected;
    private Position position;

    public PikachuBuilder size(int size) {
      this.size = size;
      return this;
    }

    public PikachuBuilder xAxis(int xAxis) {
      this.xAxis = xAxis;
      return this;
    }

    public PikachuBuilder yAxis(int yAxis) {
      this.yAxis = yAxis;
      return this;
    }

    public PikachuBuilder image(Image image) {
      this.image = image;
      return this;
    }

    public PikachuBuilder id(int id) {
      this.id = id;
      return this;
    }

    public PikachuBuilder position(Position position) {
      this.position = position;
      return this;
    }

    public Pikachu build() {
      return new Pikachu(
          this.size,
          this.xAxis,
          this.yAxis,
          this.image,
          this.id,
          this.backgroundId,
          this.selected,
          this.position);
    }
  }
}

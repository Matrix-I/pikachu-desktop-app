package com.pika.desktop.app;

import static com.pika.desktop.app.Constants.COLUMN;
import static com.pika.desktop.app.Constants.PADDING_LEFT;
import static com.pika.desktop.app.Constants.PADDING_TOP;
import static com.pika.desktop.app.Constants.ROW;
import static com.pika.desktop.app.Constants.SIZE_PI;

import java.awt.*;
import java.net.URL;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.*;

public class InitGame {
  private static final String IMAGE_PATH = "src/imgs/p_{0}_cap1.png";

  private final Pikachu[][] pikachus;
  private final List<Pikachu> pikachuList;

  public InitGame() {
    pikachus = new Pikachu[ROW][COLUMN];
    pikachuList = new ArrayList<>();
    createPika();
  }

  public void drawAll(Graphics2D g2d) {
    for (Pikachu pikachu : pikachuList) {
      pikachu.draw(g2d);
    }
  }

  public Pikachu[][] getPikachus() {
    return pikachus;
  }

  public List<Pikachu> getPikachuList() {
    return pikachuList;
  }

  private void createPika() {
    Random rd = new Random();
    int totalImages = ROW * COLUMN;
    int countItems = 0;
    List<Integer> ids = new ArrayList<>();
    for (int rowIndex = 0; rowIndex < ROW; rowIndex++) {
      for (int columnIndex = 0; columnIndex < COLUMN; columnIndex++) {
        int id;
        if (countItems < totalImages / 2) {
          id = rd.nextInt(10) + 1;
          ids.add(id);
        } else {
          int indexRd = rd.nextInt(ids.size());
          id = ids.get(indexRd);
          ids.remove(indexRd);
        }
        Pikachu pi = initPikachu(rowIndex, columnIndex, id);
        pikachus[rowIndex][columnIndex] = pi;
        pikachuList.add(pi);
        countItems++;
      }
    }
  }

  private Pikachu initPikachu(int rowIndex, int columnIndex, int id) {
    URL url = FilePathUtils.getURL(MessageFormat.format(IMAGE_PATH, id));
    Image image = new ImageIcon(url).getImage();
    Position position = new Position(rowIndex, columnIndex, false);
    return new Pikachu.PikachuBuilder()
        .id(id)
        .image(image)
        .size(SIZE_PI)
        .xAxis(getXAxis(columnIndex))
        .yAxis(getYAxis(rowIndex))
        .position(position)
        .build();
  }

  private int getXAxis(int columnIndex) {
    return columnIndex * SIZE_PI + PADDING_LEFT;
  }

  private int getYAxis(int rowIndex) {
    return rowIndex * SIZE_PI + PADDING_TOP;
  }
}

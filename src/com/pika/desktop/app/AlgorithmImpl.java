package com.pika.desktop.app;

import java.awt.*;
import java.net.URL;
import java.text.MessageFormat;
import java.util.*;
import java.util.List;
import javax.swing.*;

import static com.pika.desktop.app.Constants.COLUMN;
import static com.pika.desktop.app.Constants.PADDING_LEFT;
import static com.pika.desktop.app.Constants.PADDING_TOP;
import static com.pika.desktop.app.Constants.ROW;
import static com.pika.desktop.app.Constants.SIZE_PI;

public class AlgorithmImpl implements Algorithm {

  private static final String IMAGE_PATH = "src/imgs/p_{0}_cap1.png";

  private final Pikachu[][] pikachus;
  private final Pikachu[][] map;
  private final Set<Pikachu> pikachuList;
  private int amountSelectedItems = 0;
  private Pikachu selectedFirstItem;
  private Pikachu selectedSecondItem;

  public AlgorithmImpl() {
    map = new Pikachu[ROW + 2][COLUMN + 2];
    pikachus = new Pikachu[ROW][COLUMN];
    pikachuList = new HashSet<>();
    createPika();
    //    printMapImages();
    createMap();
    printMapPaths();
  }

  @Override
  public void drawAll(Graphics2D g2d) {
    for (Pikachu pikachu : pikachuList) {
      pikachu.draw(g2d);
    }
  }

  @Override
  public void moveMouse(int x, int y) {
    for (Pikachu pikachu : pikachuList) {
      if (pikachu == null || pikachu.isSelected()) {
        return;
      }
      setBackgroundForImageMouseIn(x, y, pikachu);
    }
  }

  @Override
  public void mouseClick(int x, int y) {
    for (Pikachu pikachu : pikachuList) {
      if (pikachu.checkMouseIn(x, y)) {
        if (pikachu.isSelected()) {
          unselectItem(pikachu);
        } else {
          selectItem(pikachu);
        }
      }
    }
    //    if (amountSelectedItems == 2) {
    //      this.amountSelectedItems = 0;
    //      this.unselectItem(this.selectedFirstItem);
    //      this.unselectItem(this.selectedSecondItem);
    //    }
  }

  private void createMap() {
    for (int i = 0; i < ROW; i++) {
      System.arraycopy(pikachus[i], 0, map[i + 1], 1, COLUMN - 1);
    }
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

  private void printMapImages() {
    for (int i = 0; i < ROW; i++) {
      for (int j = 0; j < COLUMN; j++) {
        System.out.print(pikachus[i][j].getId() + " ");
        if (j == (COLUMN - 1)) {
          System.out.println();
        }
      }
    }
  }

  private void printMapPaths() {
    for (int i = 0; i < ROW + 2; i++) {
      for (int j = 0; j < COLUMN + 2; j++) {
        if (j == (COLUMN + 1)) {
          System.out.println();
          continue;
        }
        if (map[i][j] == null) {
          System.out.print("+ ");
          continue;
        }
        System.out.print(map[i][j].getId() + " ");
      }
    }
  }

  private Pikachu initPikachu(int rowIndex, int columnIndex, int id) {
    URL url = FilePathUtils.getURL(MessageFormat.format(IMAGE_PATH, id));
    Image image = new ImageIcon(url).getImage();
    Position position = new Position(rowIndex, columnIndex);
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

  private void selectItem(Pikachu pikachu) {
    pikachu.setBackgroundId(4);
    pikachu.setSelected(true);
    this.amountSelectedItems++;
    setSelectedItem(pikachu);
  }

  private void setSelectedItem(Pikachu pikachu) {
    if (amountSelectedItems == 1) {
      this.selectedFirstItem = pikachu;
    } else {
      this.selectedSecondItem = pikachu;
    }
  }

  private void unselectItem(Pikachu pikachu) {
    pikachu.setBackgroundId(0);
    pikachu.setSelected(false);
    this.selectedFirstItem = null;
  }

  private void setBackgroundForImageMouseIn(int x, int y, Pikachu pikachu) {
    if (pikachu.checkMouseIn(x, y)) {
      pikachu.setBackgroundId(1);
    } else {
      pikachu.setBackgroundId(0);
    }
  }

  private void doSomeThing() {
    if (selectedFirstItem.getId() == selectedSecondItem.getId()) {
      pikachuList.remove(selectedFirstItem);
      pikachuList.remove(selectedSecondItem);
      pikachus[(selectedFirstItem.getyAxis() - PADDING_TOP) / SIZE_PI][
              (selectedFirstItem.getxAxis() - PADDING_LEFT) / SIZE_PI] =
          null;
      pikachus[(selectedSecondItem.getyAxis() - PADDING_TOP) / SIZE_PI][
              (selectedSecondItem.getxAxis() - PADDING_LEFT) / SIZE_PI] =
          null;
    }
  }
}

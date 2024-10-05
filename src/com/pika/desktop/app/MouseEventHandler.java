package com.pika.desktop.app;

import static com.pika.desktop.app.Constants.COLUMN;
import static com.pika.desktop.app.Constants.PADDING_LEFT;
import static com.pika.desktop.app.Constants.PADDING_TOP;
import static com.pika.desktop.app.Constants.ROW;
import static com.pika.desktop.app.Constants.SIZE_PI;

import java.util.List;

public class MouseEventHandler implements MouseEvent {
  private final Pikachu[][] map;
  private final FindingPathAlgorithm algorithm;
  private int amountSelectedItems = 0;
  private Pikachu selectedFirstItem;
  private Pikachu selectedSecondItem;

  public MouseEventHandler(InitGame initGame) {
    algorithm = new FindingPathAlgorithm();
    map = new Pikachu[ROW + 2][COLUMN + 2];
    createMap(initGame.getPikachus());
  }

  @Override
  public void moveMouse(int x, int y, List<Pikachu> pikachus) {
    for (Pikachu pikachu : pikachus) {
      if (pikachu == null || pikachu.isSelected()) {
        return;
      }
      setBackgroundForImageMouseIn(x, y, pikachu);
    }
  }

  @Override
  public void mouseClick(int x, int y, Pikachu[][] pikachus, List<Pikachu> pikachuList) {
    toggleSelection(x, y, pikachuList);

    if (amountSelectedItems == 2) {
      handleSelectedItems(pikachus, pikachuList);
    }
  }

  private void toggleSelection(int x, int y, List<Pikachu> pikachuList) {
    for (Pikachu pikachu : pikachuList) {
      if (pikachu.checkMouseIn(x, y)) {
        if (pikachu.isSelected()) {
          unselectItem(pikachu);
        } else {
          selectItem(pikachu);
        }
      }
    }
  }

  private void handleSelectedItems(Pikachu[][] pikachus, List<Pikachu> pikachuList) {
    if (algorithm.find(map, selectedFirstItem, selectedSecondItem)) {
      clearSelectedItems();
      markAllowCross();
      removeMatchItem(pikachus, pikachuList);
    }

    resetSelection();
  }

  private void clearSelectedItems() {
    selectedFirstItem.setId(0);
    selectedSecondItem.setId(0);
  }

  private void resetSelection() {
    unselectItem(selectedFirstItem);
    unselectItem(selectedSecondItem);
    selectedFirstItem = null;
    selectedSecondItem = null;
    amountSelectedItems = 0;
  }

  private void markAllowCross() {
    for (int i = 0; i < ROW + 2; i++) {
      for (int j = 0; j < COLUMN + 2; j++) {
        Pikachu pikachu = map[i][j];
        if (pikachu == selectedFirstItem) {
          pikachu.getPosition().setAllowCross(true);
        }
        if (pikachu == selectedSecondItem) {
          pikachu.getPosition().setAllowCross(true);
        }
      }
    }
  }

  private void removeMatchItem(Pikachu[][] pikachus, List<Pikachu> pikachuList) {
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

  private void createMap(Pikachu[][] pikachus) {
    for (int i = 0; i < ROW; i++) {
      for (int j = 0; j < COLUMN; j++) {
        Position position = new Position(i + 1, j + 1, false);
        Pikachu pika = pikachus[i][j];
        pika.setPosition(position);
        map[i + 1][j + 1] = pika;
      }
      System.arraycopy(pikachus[i], 0, map[i + 1], 1, COLUMN);
    }

    for (int i = 0; i < COLUMN + 2; i++) {
      Position position = new Position(0, i, true);
      Pikachu pikachu = new Pikachu(position);
      map[0][i] = pikachu;
    }

    for (int i = 0; i < COLUMN + 2; i++) {
      Position position = new Position(ROW + 1, i, true);
      Pikachu pikachu = new Pikachu(position);
      map[ROW + 1][i] = pikachu;
    }

    for (int i = 0; i < ROW + 2; i++) {
      Position position = new Position(i, 0, true);
      Pikachu pikachu = new Pikachu(position);
      map[i][0] = pikachu;
    }

    for (int i = 0; i < ROW + 2; i++) {
      Position position = new Position(i, COLUMN + 1, true);
      Pikachu pikachu = new Pikachu(position);
      map[i][COLUMN + 1] = pikachu;
    }
  }

  private void selectItem(Pikachu pikachu) {
    this.amountSelectedItems++;
    pikachu.setBackgroundId(4);
    pikachu.setSelected(true);
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
    amountSelectedItems = 0;
    selectedFirstItem = null;
  }

  private void setBackgroundForImageMouseIn(int x, int y, Pikachu pikachu) {
    if (pikachu.checkMouseIn(x, y)) {
      pikachu.setBackgroundId(1);
    } else {
      pikachu.setBackgroundId(0);
    }
  }
}

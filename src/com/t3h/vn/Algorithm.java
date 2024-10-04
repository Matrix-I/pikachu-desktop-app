package com.t3h.vn;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class Algorithm implements Constants {
    private int notBarrier = 0;
    private Pikachu[][] pikachus;
    private List<Pikachu> pikachuList;
    private int numberClick = 0;
    private Pikachu p1;
    private Pikachu p2;
    private Queue<Pikachu> pikachuQueue = new LinkedList<>();


    public Algorithm() {
        pikachus = new Pikachu[ROW][COLUMN];
        pikachuList = new ArrayList<>();
        createPika();
        printMapPi();
    }

    private void createPika() {
        Random rd = new Random();
        int totalPi = ROW * COLUMN;
        int index = 0;
        List<Integer> ids = new ArrayList<>();
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COLUMN; j++) {
                int id;
                if (index < totalPi / 2) {
                    id = rd.nextInt(10)+1;
                    ids.add(id);
                } else {
                    int indexRd = rd.nextInt(ids.size());
                    id = ids.get(indexRd);
                    ids.remove(indexRd);
                }
                index++;
                Pikachu pi = new Pikachu();
                pi.setId(0);
                pi.setImage((
                        new ImageIcon(
                                Algorithm.class.getResource(
                                        "/imgs/p_" + id +
                                                "_cap1.png")).getImage()
                ));
                pi.setSize(SIZE_PI);
                pi.setId(id);
                pi.setX(j * SIZE_PI + PADDING_LEFT);
                pi.setY(i * SIZE_PI + PADDING_TOP);
                pikachus[i][j] = pi;
                pikachuList.add(pi);
            }

        }
    }

    public void drawAll(Graphics2D g2d) {
        for (int i = 0; i < pikachuList.size(); i++) {
            pikachuList.get(i).draw(g2d);
        }
    }

    public void printMapPi() {
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COLUMN; j++) {
                System.out.print(pikachus[i][j].getId() + " ");
                if (j == (COLUMN - 1)) System.out.println();
            }

        }

    }

    public void moveMouse(int x, int y) {

        for (int i = 0; i < pikachuList.size(); i++) {
            if (pikachuList.get(i) != null) {
                if (pikachuList.get(i).checkMouseIn(x, y)) {
                    pikachuList.get(i).setBgId(1);
                } else {
                    pikachuList.get(i).setBgId(0);
                }
            }

        }
    }

    public void mouseClick(int x, int y) {
        for (int i = 0; i < pikachuList.size(); i++) {
            if (pikachuList.get(i).checkMouseIn(x, y)) {
                pikachuList.get(i).setBgId(4);
                if (numberClick == 1) {
                    if (pikachuList.get(i) == p1) {
                        return;
                    }
                    p2 = pikachuList.get(i);
                } else {
                    p1 = pikachuList.get(i);
                }
                numberClick++;
                break;
            }
        }
        if (numberClick == 2) {
            if (checkOneLine(
                    (p1.getY() - PADDING_TOP) / SIZE_PI,
                    (p1.getX() - PADDING_LEFT) / SIZE_PI,
                    (p2.getY() - PADDING_TOP) / SIZE_PI,
                    (p2.getX() - PADDING_LEFT) / SIZE_PI
            )) {
                if (p1.getId() == p2.getId()){
                pikachuList.remove(p1);
                pikachuList.remove(p2);
                pikachus[(p1.getY() - PADDING_TOP) / SIZE_PI][(p1.getX() - PADDING_LEFT) / SIZE_PI] = null;
                pikachus[(p2.getY() - PADDING_TOP) / SIZE_PI][(p2.getX() - PADDING_LEFT) / SIZE_PI] = null;}
            } else {

                if (checkTwoLine(
                        (p1.getY() - PADDING_TOP) / SIZE_PI,
                        (p1.getX() - PADDING_LEFT) / SIZE_PI,
                        (p2.getY() - PADDING_TOP) / SIZE_PI,
                        (p2.getX() - PADDING_LEFT) / SIZE_PI
                )) {
                    if (p1.getId() == p2.getId()){
                        pikachuList.remove(p1);
                        pikachuList.remove(p2);
                        pikachus[(p1.getY() - PADDING_TOP) / SIZE_PI][(p1.getX() - PADDING_LEFT) / SIZE_PI] = null;
                        pikachus[(p2.getY() - PADDING_TOP) / SIZE_PI][(p2.getX() - PADDING_LEFT) / SIZE_PI] = null;}
                }
                else {
                    if (checkThreeLine(
                            (p1.getY() - PADDING_TOP) / SIZE_PI,
                            (p1.getX() - PADDING_LEFT) / SIZE_PI,
                            (p2.getY() - PADDING_TOP) / SIZE_PI,
                            (p2.getX() - PADDING_LEFT) / SIZE_PI
                    )){
                        if (p1.getId() == p2.getId()){
                            pikachuList.remove(p1);
                            pikachuList.remove(p2);
                            pikachus[(p1.getY() - PADDING_TOP) / SIZE_PI][(p1.getX() - PADDING_LEFT) / SIZE_PI] = null;
                            pikachus[(p2.getY() - PADDING_TOP) / SIZE_PI][(p2.getX() - PADDING_LEFT) / SIZE_PI] = null;}
                    }
                }
            }
            p1 = p2 = null;
            numberClick = 0;
        }
    }


    private boolean checkX(int x1, int x2, int y) {
        int min = Math.min(x1, x2);
        int max = Math.max(x1, x2);
        for (int i = min + 1; i < max; i++) {
            if (pikachus[i][y] != null) {
                return false;
            }
        }
        return true;
    }

    private boolean checkY(int y1, int y2, int x) {
        int min = Math.min(y1, y2);
        int max = Math.max(y1, y2);
        for (int i = min + 1; i < max; i++) {
            if (pikachus[x][i] != null) {
                return false;
            }
        }
        return true;
    }

    private boolean checkOneLine(int x1, int y1, int x2, int y2) {
        if (x1 == x2) {
            return checkY(y1, y2, x1);
        } else {
            if (y1 == y2) {
                return checkX(x1, x2, y1);
            }
        }
        return false;
    }

    private boolean checkTwoLine(int row1, int column1, int row2, int column2)
    {
        if (pikachus[row2][column1] != null && pikachus[row1][column2] != null)
        {
            return false;
        }
        if (pikachus[row2][column1] == null)
        {
            boolean checkLine = checkOneLine(row1, column1, row2, column1);
            if (checkLine) {
                checkLine = checkOneLine(row2, column1, row2, column2);
            }
            return checkLine;

        }
        if (pikachus[row1][column2] == null)
        {
            boolean checkLine = checkOneLine(row1, column1, row1, column2);
            if (checkLine) {
                checkLine = checkOneLine(row1, column2, row2, column2);
            }
            return checkLine;
        }
        return false;
    }

    private boolean checkThreeLine(int row1, int column1, int row2, int column2) {

        if (row1 == row2 && row1 == 0 || row1 == ROW-1) {
            return true;
        }

        if (column1 == column2 && column1 == 0 || column1 == COLUMN-1) {
            return true;
        }

        for(int i = row1 +1; i < ROW ; i++) {
            boolean checkLine = checkOneLine(row1, column1, i, column1);
            if (checkLine)
            {
                checkLine = checkTwoLine(i,column1,row2,column2);
            }
            if (checkLine) return true;
            else continue;
        }

        for(int i = row1 - 1; i > 0 ; i--) {
            boolean checkLine = checkOneLine(row1, column1, i, column1);
            if (checkLine)
            {
                checkLine = checkTwoLine(i,column1,row2,column2);
            }
            if (checkLine) return true;
            else continue;
        }

        for(int i = column1 +1; i < COLUMN ; i++) {
            boolean checkLine = checkOneLine(row1, column1, row1, i);
            if (checkLine)
            {
                checkLine = checkTwoLine(row1,i,row2,column2);
            }
            if (checkLine) return true;
            else continue;
        }

        for(int i = column1 - 1; i > 0 ; i++) {
            boolean checkLine = checkOneLine(row1, column1, row1, i);
            if (checkLine)
            {
                checkLine = checkTwoLine(row1,i,row2,column2);
            }
            if (checkLine) return true;
            else continue;
        }

      return  false;
    }
}


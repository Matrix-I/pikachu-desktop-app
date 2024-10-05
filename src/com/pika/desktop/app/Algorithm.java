package com.pika.desktop.app;

import java.awt.*;

public interface Algorithm {
  void drawAll(Graphics2D g2d);

  void mouseClick(int x, int y);

  void moveMouse(int x, int y);
}

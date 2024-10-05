package com.pika.desktop.app;

public enum Direction {
  RIGHT(0, 1),
  DOWN(1, 0),
  LEFT(0, -1),
  UP(-1, 0);

  private final int dx;
  private final int dy;

  Direction(int dx, int dy) {
    this.dx = dx;
    this.dy = dy;
  }

  public int getDx() {
    return dx;
  }

  public int getDy() {
    return dy;
  }
}

package com.pika.desktop.app;

public final class Constants {
  public static int WIDTH_FRAME = 1200;
  public static int HEIGHT_FRAME = WIDTH_FRAME * 2 / 3;
  public static int HEIGHT_HEADER = HEIGHT_FRAME / 5;
  public static int COLUMN = 6;
  public static int ROW = 6;
  public static int PADDING_LEFT_PRIVATE = WIDTH_FRAME / 12;
  public static int PADDING_TOP_PRIVATE = WIDTH_FRAME / 30;
  public static int SIZE_PI_1 = (WIDTH_FRAME - 2 * PADDING_LEFT_PRIVATE) / COLUMN;
  public static int SIZE_PI_2 = (HEIGHT_FRAME - HEIGHT_HEADER - 2 * PADDING_TOP_PRIVATE) / ROW;

  public static int SIZE_PI = SIZE_PI_1 > SIZE_PI_2 ? SIZE_PI_2 : SIZE_PI_1;
  public static int PADDING_LEFT = (WIDTH_FRAME - SIZE_PI * COLUMN) / 2;
  public static int PADDING_TOP = (HEIGHT_FRAME - HEIGHT_HEADER - ROW * SIZE_PI) / 3;
}

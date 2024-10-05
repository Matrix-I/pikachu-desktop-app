package com.pika.desktop.app;

import java.text.MessageFormat;

public class Position {
  private final int rowIndex;
  private final int columnIndex;
  private boolean allowCross;

  public Position(int rowIndex, int columnIndex, boolean allowCross) {
    this.rowIndex = rowIndex;
    this.columnIndex = columnIndex;
    this.allowCross = allowCross;
  }

  public boolean isAllowCross() {
    return allowCross;
  }

  public int getRowIndex() {
    return rowIndex;
  }

  public int getColumnIndex() {
    return columnIndex;
  }

  public void setAllowCross(boolean allowCross) {
    this.allowCross = allowCross;
  }

  @Override
  public String toString() {
    return MessageFormat.format("[{0}, {1}, {2}]", rowIndex, columnIndex, allowCross);
  }
}

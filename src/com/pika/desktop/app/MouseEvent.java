package com.pika.desktop.app;

import java.util.List;

public interface MouseEvent {
  void mouseClick(int x, int y, Pikachu[][] pikachus, List<Pikachu> pikachuList);

  void moveMouse(int x, int y, List<Pikachu> pikachus);
}

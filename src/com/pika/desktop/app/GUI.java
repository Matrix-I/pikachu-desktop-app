package com.pika.desktop.app;

import java.util.Random;
import javax.swing.*;

public class GUI extends JFrame implements Constants {
  public GUI() {
    setSize(WIDTH_FRAME, HEIGHT_FRAME);
    setTitle("Pikachu");
    setLocationRelativeTo(null);
    setResizable(false);
    setLayout(null);
    setDefaultCloseOperation(EXIT_ON_CLOSE);

    int idBg = new Random().nextInt(13);
    add(new GamePanel(idBg));
    add(new Header(idBg));
  }
}

package com.pika.desktop.app;

import static com.pika.desktop.app.Constants.HEIGHT_FRAME;
import static com.pika.desktop.app.Constants.WIDTH_FRAME;

import java.util.Random;
import javax.swing.*;

public class GUI extends JFrame {
  public GUI() {
    setSize(WIDTH_FRAME, HEIGHT_FRAME);
    setTitle("Pikachu");
    setLocationRelativeTo(null);
    setResizable(false);
    setLayout(null);
    setDefaultCloseOperation(EXIT_ON_CLOSE);

    int backgroundId = new Random().nextInt(13);
    add(new GamePanel(backgroundId));
    add(new Header(backgroundId));
  }
}

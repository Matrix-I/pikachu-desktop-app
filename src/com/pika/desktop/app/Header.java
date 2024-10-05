package com.pika.desktop.app;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class Header extends JPanel implements Constants {
  private final Image imgBg;
  private final List<Button> button;
  private final Image icon = new ImageIcon(FilePathUtils.getURL("src/imgs/next2.png")).getImage();
  private final Image icon2 = new ImageIcon(FilePathUtils.getURL("src/imgs/back2.png")).getImage();
  private Button button1, button2;

  public Header(int idBg) {
    imgBg = new ImageIcon(FilePathUtils.getURL("src/imgs/bg" + idBg + ".png")).getImage();
    setSize(WIDTH_FRAME, HEIGHT_HEADER);
    button = new ArrayList<>();
    button1 = new Button();
    button2 = new Button();
    initButton();
    add(button1);
    add(button2);
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    g2d.drawImage(
        imgBg,
        0,
        0,
        WIDTH_FRAME,
        HEIGHT_HEADER,
        0,
        0,
        imgBg.getWidth(null),
        imgBg.getHeight(null) * HEIGHT_HEADER / HEIGHT_FRAME,
        null);
  }

  public void initButton() {
    button1 = new Button();
    button1.setButton(10, 10, 70, 70, icon);
    button1.setSize(getWidth(), getHeight());
    button1.setLocation(getX(), getY());
    button1.setIcon(new ImageIcon(icon));
    button1.setBorder(null);
    button.add(button1);

    button2 = new Button();
    button2.setButton(100, 10, 20, 60, icon2);
    button2.setSize(getWidth(), getHeight());
    button2.setLocation(getX(), getY());
    button2.setIcon(new ImageIcon(icon2));
    button1.setBorder(null);
    button.add(button2);
  }
}

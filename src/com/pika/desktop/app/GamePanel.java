package com.pika.desktop.app;

import static com.pika.desktop.app.Constants.HEIGHT_FRAME;
import static com.pika.desktop.app.Constants.HEIGHT_HEADER;
import static com.pika.desktop.app.Constants.WIDTH_FRAME;

import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.*;

public class GamePanel extends JPanel implements MouseMotionListener, MouseListener {
  private final MouseEvent mouseEvent;
  private final Image backgroundImage;
  private final InitGame initGame;

  public GamePanel(int idBg) {
    initGame = new InitGame();
    mouseEvent = new MouseEventHandler(initGame);
    backgroundImage = new ImageIcon(FilePathUtils.getURL("src/imgs/bg" + idBg + ".png")).getImage();
    initPanel();
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D graphics2D = (Graphics2D) g;
    graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    graphics2D.setStroke(new BasicStroke(5));
    graphics2D.drawImage(
        backgroundImage,
        0,
        0,
        WIDTH_FRAME,
        HEIGHT_FRAME - HEIGHT_HEADER,
        0,
        backgroundImage.getHeight(null) * HEIGHT_HEADER / HEIGHT_FRAME,
        backgroundImage.getWidth(null),
        backgroundImage.getHeight(null),
        null);

    initGame.drawAll(graphics2D);
  }

  @Override
  public void mouseDragged(java.awt.event.MouseEvent e) {}

  @Override
  public void mouseMoved(java.awt.event.MouseEvent e) {
    mouseEvent.moveMouse(e.getX(), e.getY(), initGame.getPikachuList());
    repaint();
  }

  @Override
  public void mouseClicked(java.awt.event.MouseEvent e) {
    mouseEvent.mouseClick(e.getX(), e.getY(), initGame.getPikachus(), initGame.getPikachuList());
    repaint();
  }

  @Override
  public void mousePressed(java.awt.event.MouseEvent e) {}

  @Override
  public void mouseReleased(java.awt.event.MouseEvent e) {}

  @Override
  public void mouseEntered(java.awt.event.MouseEvent e) {}

  @Override
  public void mouseExited(java.awt.event.MouseEvent e) {}

  private void initPanel() {
    setSize(WIDTH_FRAME, HEIGHT_FRAME - HEIGHT_HEADER);
    setLocation(0, HEIGHT_HEADER);
    setBackground(Color.BLACK);
    setFocusable(true);
    setLayout(null);
    addMouseMotionListener(this);
    addMouseListener(this);
  }
}

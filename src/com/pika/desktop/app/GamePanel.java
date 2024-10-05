package com.pika.desktop.app;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.*;

public class GamePanel extends JPanel implements Constants, MouseMotionListener, MouseListener {
  private final Algorithm algorithm;
  private final Image backgroundImage;

  public GamePanel(int idBg) {
    algorithm = new AlgorithmImpl();
    backgroundImage = new ImageIcon(FilePathUtils.getURL("src/imgs/bg" + idBg + ".png")).getImage();
    initPanel();
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    g2d.drawImage(
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
    algorithm.drawAll(g2d);
  }

  @Override
  public void mouseDragged(MouseEvent e) {}

  @Override
  public void mouseMoved(MouseEvent e) {
    algorithm.moveMouse(e.getX(), e.getY());
    repaint();
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    algorithm.mouseClick(e.getX(), e.getY());
    repaint();
  }

  @Override
  public void mousePressed(MouseEvent e) {}

  @Override
  public void mouseReleased(MouseEvent e) {}

  @Override
  public void mouseEntered(MouseEvent e) {}

  @Override
  public void mouseExited(MouseEvent e) {}

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

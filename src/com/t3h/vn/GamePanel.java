package com.t3h.vn;

import org.w3c.dom.events.EventListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class GamePanel extends JPanel implements Constants, MouseMotionListener, MouseListener {
    private Algorithm algorithm;
    private Image imgBg;

    public GamePanel(int idBg) {
        setSize(WIDTH_FRAME, HEIGHT_FRAME - HEIGHT_HEADER);
        setLocation(0, HEIGHT_HEADER);
        setBackground(Color.BLACK);
        algorithm = new Algorithm();
        setFocusable(true);
        setLayout(null);
        addMouseMotionListener(this);
        addMouseListener(this);

        imgBg = new ImageIcon(
                GamePanel.class.getResource("/imgs/bg" + idBg + ".png")
        ).getImage();
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.drawImage(imgBg, 0, 0,
                WIDTH_FRAME, HEIGHT_FRAME - HEIGHT_HEADER,
                0, imgBg.getHeight(null) * HEIGHT_HEADER / HEIGHT_FRAME,
                imgBg.getWidth(null), imgBg.getHeight(null),
                null);
        algorithm.drawAll(g2d);
    }


    @Override
    public void mouseDragged(MouseEvent e) {

    }

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
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}

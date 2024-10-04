package com.t3h.vn;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Header extends JPanel implements Constants
{
    private Image imgBg;
    private Button button1,button2;
    private List<Button> button;
    Image icon = new ImageIcon(Header.class.getResource("/imgs/next2.png")).getImage();
    Image icon2 = new ImageIcon(Header.class.getResource("/imgs/back2.png")).getImage();


    public Header(int idBg)
    {
        imgBg = new ImageIcon(
                GamePanel.class.getResource("/imgs/bg"+idBg+".png")
        ).getImage();
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
        Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.drawImage(imgBg,
                0, 0, WIDTH_FRAME, HEIGHT_HEADER,
                0,0,
                imgBg.getWidth(null), imgBg.getHeight(null)*HEIGHT_HEADER/HEIGHT_FRAME,
                null);

    }

    public void initButton()
    {
        button1 = new Button();
        button1.setButton(10,10,70,70,icon);
        button1.setSize(getWidth(),getHeight());
        button1.setLocation(getX(),getY());
        button1.setIcon(new ImageIcon(icon));
        button1.setBorder(null);
        button.add(button1);


        button2 = new Button();
        button2.setButton(100,10,20,60,icon2);
        button2.setSize(getWidth(),getHeight());
        button2.setLocation(getX(),getY());
        button2.setIcon(new ImageIcon(icon2));
        button1.setBorder(null);
        button.add(button2);
    }
}

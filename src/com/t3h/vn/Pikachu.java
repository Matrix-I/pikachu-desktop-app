package com.t3h.vn;

import javax.swing.*;
import java.awt.*;

public class Pikachu implements Constants
{
    public static final Image[] IMG_BG_PI = new Image[]
            {
                    new ImageIcon(Pikachu.class.getResource("/imgs/square_1.png")).getImage(),
                    new ImageIcon(Pikachu.class.getResource("/imgs/square_2.png")).getImage(),
                    new ImageIcon(Pikachu.class.getResource("/imgs/square_3.png")).getImage(),
                    new  ImageIcon((Pikachu.class.getResource("/imgs/squarec_1.png"))).getImage(),
                    new  ImageIcon((Pikachu.class.getResource("/imgs/squarec_3.png"))).getImage()
            };


    private int size;
    private int x, y;
    private Image image;
    private int id;
    private int bgId;
    private int bgClick;
    private Pikachu pika1,pika2;

//    public Pikachu(Pikachu pika1,Pikachu pika2)
//    {
//        this.pika1 = pika1;
//        this.pika1 = pika2;
//    }


    public int getBgClick() {
        return bgClick;
    }

    public void setBgClick(int bgClick) {
        this.bgClick = bgClick;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public int setId(int id) {
        this.id = id;
        return id;
    }

    public int getBgId() {
        return bgId;
    }


    public void setBgId(int bgId)
    {
        this.bgId = bgId;
    }

    public void draw(Graphics2D g2d)
    {
        g2d.drawImage(IMG_BG_PI[bgId], x, y, size, size, null);
        g2d.drawImage(image, x, y, size, size, null);
    }

    public boolean checkMouseIn(int xM, int yM)
    {
        return xM >= x && xM < x + size && yM >= y && yM <y+size;
    }
}

package com.t3h.vn;

import javax.swing.*;
import java.awt.*;

public class Button extends JButton
{
    private int x,y;
    private int wigth,height;
    private Image img;

    @Override
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    @Override
    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWigth() {
        return wigth;
    }

    public void setWigth(int wigth) {
        this.wigth = wigth;
    }

    @Override
    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public void setButton(int x,int y,int wigth,int height,Image img)
    {
        this.x = x;
        this.y = y;
        this.wigth = wigth;
        this.height = height;
        this.img = img;
    }

}

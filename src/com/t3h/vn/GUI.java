package com.t3h.vn;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class GUI extends JFrame implements Constants
{
    public GUI()
    {
        setSize(WIDTH_FRAME, HEIGHT_FRAME);
        setTitle("Pikachu");
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        int idBg= new Random().nextInt(13);
        add(new GamePanel(idBg));
        add(new Header(idBg));
    }
}

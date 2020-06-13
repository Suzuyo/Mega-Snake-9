package com.suzuyo.gui;

import com.suzuyo.ImageProvider;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class MenuPanel extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        BufferedImage menuBackgroundImage = ImageProvider.getInstance().getMenuBackgroundImage();
        g.drawImage(menuBackgroundImage, 0, 0, null);

    }
}

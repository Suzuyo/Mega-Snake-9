package com.suzuyo;

import java.awt.*;

public class Application {

    public static void main(String[] args) {
        EventQueue.invokeLater(WindowController::getInstance);
    }
}
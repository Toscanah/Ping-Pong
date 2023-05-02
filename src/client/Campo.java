package client;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class Campo extends Frame implements ActionListener, Runnable, WindowListener {
    Canvas c = new Canvas();
    Graphics g;
    Button btnStart;
    Label lblScore = new Label("0");
    int numost = 20;
    int xship = 100;
    int yship = 200;
    int larship = 30;
    int altship = 10;

    public Campo() {
        Random numerocasuale = new Random();
        setLayout(new BorderLayout());
        add("North", lblScore);
        add("Center", c);
        c.setBackground(Color.GRAY);
        addWindowListener(this);
        btnStart = new Button("Start");
        add("South", btnStart);
        btnStart.addActionListener(this);
        setTitle("Labyrinth");
        setSize(700, 500);
        setVisible(true);
        g = c.getGraphics();
        c.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent evt) {
                muoviship(evt);
            }
        });
    }

    public static void main(String[] args) {
        new Campo();
    }

    public void actionPerformed(ActionEvent evt) {
        (new Thread(this)).start();
        g.setColor(Color.GREEN);
        g.fillRect(xship, yship, larship, altship);
    }

    public void muoviship(KeyEvent evt) {
        switch (evt.getKeyCode()) {
            case KeyEvent.VK_DOWN:
                yship += 5;
                break;
            case KeyEvent.VK_UP:
                yship -= 5;
                break;
        }
    }

    public void run() {
        while (true) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            g.setColor(Color.GRAY);
            g.fillRect(0, 0, 699, 499);
            g.setColor(Color.GREEN);
            g.fillRect(xship, yship, larship, altship);
            g.setColor(Color.RED);
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {

    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
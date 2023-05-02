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
    int dimost = 26;

    public Campo() {
        int tempx;
        int tempy;
        Random numerocasuale = new Random();
        for (int i = 0; i < numost; i++) {
            tempx = 150 + (numerocasuale.nextInt(18) * 30);
            tempy = 5 + (numerocasuale.nextInt(14) * 30);
        }
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
                Thread.currentThread().sleep(1);
            } catch (Exception e) {
            }
            ;
            g.setColor(Color.GRAY);
            g.fillRect(0, 0, 699, 499);
            g.setColor(Color.GREEN);
            g.fillRect(xship, yship, larship, altship);
            g.setColor(Color.RED);
            for (int i = 0; i < numost; i++) {
                g.fillRect(o[i].posizionex(), o[i].posizioney(), o[i].posizionex1() - o[i].posizionex(), o[i].posizioney1() - o[i].posizioney());
                if (o[i].collisione(xship, yship, xship + larship, yship + altship)) Thread.currentThread().stop();
            }
        }
    }

    public void windowClosing(WindowEvent e) {
        System.exit(0);  // terminate the program
    }

    public void windowOpened(WindowEvent e) {
    }

    public void windowClosed(WindowEvent e) {
    }

    public void windowIconified(WindowEvent e) {
    }

    public void windowDeiconified(WindowEvent e) {
    }

    public void windowActivated(WindowEvent e) {
    }

    public void windowDeactivated(WindowEvent e) {
    }

}
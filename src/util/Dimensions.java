package util;

import java.awt.*;

public class Dimensions {
    static GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
    static Rectangle maximumWindowBounds = graphicsEnvironment.getMaximumWindowBounds();
    static Insets screenInsets = Toolkit.getDefaultToolkit().getScreenInsets(
            graphicsEnvironment.getDefaultScreenDevice().getDefaultConfiguration());

    public static final int CANVAS_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width / 2;
    public static final int CANVAS_HEIGHT = maximumWindowBounds.height - screenInsets.bottom;

    public static final int PLAYER_WIDTH = 10;
    public static final int PLAYER_HEIGHT = 100;
    public static final int PLAYER_STEP = 10;

    public static final int BALL_WIDTH = 25;
    public static final int BALL_HEIGHT = 25;
    public static final int BALL_STEP = 5;
}
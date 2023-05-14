package client;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javafx.scene.canvas.Canvas;

import static util.Dimensions.*;

public class Camp extends Application {
    private int playerX;
    private int playerY;
    private int opponentX;
    private int opponentY;
    private int ballX;
    private int ballY;

    private boolean isSecondPlayer;
    private Canvas canvas;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        playerX = 0;
        opponentX = CANVAS_WIDTH - PLAYER_WIDTH;

        playerY = 0;
        opponentY = 0;

        ballX = CANVAS_WIDTH / 2;
        ballY = CANVAS_HEIGHT / 2;

        isSecondPlayer = false;

        canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
        canvas.setFocusTraversable(true);
        canvas.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case UP, W -> {
                    if (playerY - PLAYER_STEP > 0) {
                        playerY -= PLAYER_STEP;
                    }
                }
                case DOWN, S -> {
                    if (playerY + PLAYER_STEP < CANVAS_HEIGHT - PLAYER_HEIGHT) {
                        playerY += PLAYER_STEP;
                    }
                }
            }
            redraw();
        });

        StackPane root = new StackPane(canvas);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Pong");
        stage.show();

        redraw();
    }

    private void drawPlayer(GraphicsContext g) {
        g.setFill(Color.BLUE);
        g.fillRect(playerX, playerY, PLAYER_WIDTH, PLAYER_HEIGHT);
    }

    private void drawOpponent(GraphicsContext g) {
        g.setFill(Color.BLUE);
        g.fillRect(opponentX, opponentY, PLAYER_WIDTH, PLAYER_HEIGHT);
    }

    private void drawBall(GraphicsContext g) {
        int diameter = Math.min(BALL_WIDTH, BALL_HEIGHT);
        g.setFill(Color.BLACK);
        g.fillOval(ballX, ballY, diameter, diameter);
    }

    public void redraw() {
        GraphicsContext g = canvas.getGraphicsContext2D();
        g.clearRect(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT);
        drawPlayer(g);
        drawOpponent(g);
        drawBall(g);
    }

    public int getPlayerX() {
        return playerX;
    }

    public int getPlayerY() {
        return playerY;
    }

    public void setOpponentY(int opponentY) {
        this.opponentY = opponentY;
        redraw();
    }

    public void setBallX(int ballX) {
        this.ballX = ballX;
        redraw();
    }

    public void setBallY(int ballY) {
        this.ballY = ballY;
        redraw();
    }

    public void setSecondPlayer(boolean secondPlayer) {
        isSecondPlayer = secondPlayer;
        playerX = CANVAS_WIDTH - PLAYER_WIDTH;
        opponentX = 0;
        redraw();
    }
}
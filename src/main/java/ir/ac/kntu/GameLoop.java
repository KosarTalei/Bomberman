package ir.ac.kntu;

import ir.ac.kntu.gamecontroller.InputManager;
import ir.ac.kntu.go.Bomb;
import ir.ac.kntu.go.GameObject;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.Iterator;
import java.util.Vector;


public class GameLoop {

    public static enum GameStatus{
        Running,Paused,GameOver
    }

    private static double currentGameTime;
    private static double oldGameTime;
    private static double deltaTime;
    private final static long START_NANO_TIME = System.nanoTime();

    public static double getCurrentGameTime() {
        return currentGameTime;
    }

    public static void start(GraphicsContext gc) {
        GameStatus gameStatus=GameStatus.Running;
        new AnimationTimer() {
            public void handle(long currentNanoTime) {
            	oldGameTime = currentGameTime;
            	currentGameTime = (currentNanoTime - START_NANO_TIME) / 1000000000.0;
            	deltaTime = currentGameTime - oldGameTime;
                gc.clearRect(0, 0, 900, 600);
                updateGame();
                //renderGame();
            }
        }.start();
    }

    public static double getDeltaTime() {
    	return deltaTime * 100;
    }

    public static void updateGame() {
        InputManager.handlePlayerMovements();
        Vector<GameObject> objects = GameEngine.getEntities();
        Iterator<GameObject> it = objects.iterator();
        //remove the current bomb
        while (it.hasNext()) {
            GameObject entity = it.next();
            if(entity instanceof Bomb){
                boolean alive = ((Bomb) entity).isAlive();
                if(!alive){
                    // not removig directly from list to prevent ConcurrentModification
                    it.remove();
                }
            }
        }
    }

    public static void renderGame() {
        /*for (GameObject e : GameEngine.getEntities()) {
            //e.draw();
        }*/
    }

    public static void playAnimation(Image imgs, double speed, int x, int y, double w, double h) {
        double time = GameLoop.getCurrentGameTime();
        GraphicsContext gc = GameEngine.getGc();
        int index = findCurrentFrame(time, speed);
        gc.drawImage(imgs, x, y, w, h);
    }

    private static int findCurrentFrame(double time, double speed) {
        return (int) (time % (speed) / speed);
    }

}

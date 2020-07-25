package ir.ac.kntu.animations;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
public class PlayerAnimations {
    private static Image heroImage;
    private ImageView hero ;
    private Scene scene;
    private double w, h;
    private boolean running, goNorth, goSouth, goEast, goWest;
    public void start(Scene scene,Group group) throws Exception {
        heroImage = idle;
        ImageView imageView = new ImageView(heroImage);
        this.scene = scene;
        w = scene.getWidth();
        h = scene.getHeight();
        imageView.setViewport(new Rectangle2D(0, 0, 30, 30));
        hero = imageView;
        group.getChildren().add(hero);
        moveHeroTo(w / 2, h / 2);
        keyPress(scene);
        keyRealise(scene);
        AnimationTimer timer = getAnimationTimer();
        timer.start();
    }

    private AnimationTimer getAnimationTimer() {
        return new AnimationTimer() {
                @Override
                public void handle(long now) {
                    int dx = 0, dy = 0;
                    if (goNorth){
                        dy -= 1;
                    }
                    if (goSouth) {
                        dy += 1;
                    }
                    if (goEast) {
                        dx += 1;
                    }
                    if (goWest) {
                        dx -= 1;
                    }
                    if (running) {
                        dx *= 3;
                        dy *= 3;
                    }
                    moveHeroBy(dx, dy);
                }
            };
    }

    private void keyRealise(Scene scene) {
        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP:
                        goNorth = false;
                        break;
                    case DOWN:
                        goSouth = false;
                        break;
                    case LEFT:
                        goWest  = false;
                        break;
                    case RIGHT:
                        goEast  = false;
                        break;
                    case SHIFT:
                        running = false;
                        break;
                    default:
                }
            }
        });
    }

    private void keyPress(Scene scene) {
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP:
                        hero.setImage(moveUp);
                        goNorth = true;
                        break;
                    case DOWN:
                        hero.setImage(moveDown);
                        goSouth = true;
                        break;
                    case LEFT:
                        hero.setImage(moveLeft);
                        goWest  = true;
                        break;
                    case RIGHT:
                        hero.setImage(moveRight);
                        goEast  = true;
                        break;
                    case SHIFT:
                        running = true;
                        break;
                    default:
                }
            }
        });
    }

    private void moveHeroBy(int dx, int dy) {
        if (dx == 0 && dy == 0){
            return;
        }
        final double cx = hero.getBoundsInLocal().getWidth()  / 2;
        final double cy = hero.getBoundsInLocal().getHeight() / 2;
        double x = cx + hero.getLayoutX() + dx;
        double y = cy + hero.getLayoutY() + dy;
        moveHeroTo(x, y);
    }
    private void moveHeroTo(double x, double y) {
        final double cx = hero.getBoundsInLocal().getWidth()  / 2;
        final double cy = hero.getBoundsInLocal().getHeight() / 2;
        if (x - cx >= 0 &&
                x + cx <= w &&
                y - cy >= 0 &&
                y + cy <= h) {
            hero.relocate(x - cx, y - cy);
        }
    }
    private Image moveRight;
    private Image moveLeft;
    private Image moveUp;
    private Image moveDown;
    private Image idle;
    private Image die;
    public PlayerAnimations() {
        moveDown = getImage("C:\\Users\\Mohammad\\Desktop\\hw5-fariborz-bomberman\\resources\\assets\\player" +
                "\\player_down_moving.png");
        moveLeft  = getImage("C:\\Users\\Mohammad\\Desktop\\hw5-fariborz-bomberman\\resources\\assets\\player" +
                "\\player_left_moving.png");
        moveUp    = getImage("C:\\Users\\Mohammad\\Desktop\\hw5-fariborz-bomberman\\resources\\assets\\player" +
                "\\player_up_moving.png");
        moveRight = getImage("C:\\Users\\Mohammad\\Desktop\\hw5-fariborz-bomberman\\resources\\assets\\player" +
                "\\player_right_moving.png");
        idle      = getImage("C:\\Users\\Mohammad\\Desktop\\hw5-fariborz-bomberman\\resources\\assets\\player" +
                "\\player_down_standing.png");
        die = getImage("C:\\Users\\Mohammad\\Desktop\\hw5-fariborz-bomberman\\resources\\assets\\map\\normal.png");
    }
    public Image getMoveRightSprite() {
        return moveRight;
    }
    public Image getMoveLeftSprite() {
        return moveLeft;
    }
    public Image getMoveUpSprite() {
        return moveUp;
    }
    public Image getMoveDownSprite() {
        return moveDown;
    }
    public Image getPlayerIdleSprite(){
        return idle;
    }
    public Image getPlayerDying(){
        return die;
    }
    private Image getImage(String s) {
        Image image = null;
        try {
            image = new Image(new FileInputStream(s));
        } catch (FileNotFoundException e) {
            System.out.println("cannot load wall img!");
            e.printStackTrace();
        }
        return image;
    }
}
package ir.ac.kntu.go;
import ir.ac.kntu.GameEngine;
import ir.ac.kntu.animations.PlayerAnimations;
import ir.ac.kntu.boundedbox.RectBoundedBox;
import javafx.scene.image.Image;

public class Player  extends MovableObject{
    private Image currentImage;
    private PlayerAnimations playerAnimations;
    private int health;
    private boolean isAlive;
    private Direction currentDirection;
    private int positionX = 0;
    private int positionY = 0;
    private int layer;
    private String name;
    private int scale = 1;
    private double reduceBoundarySizePercent=0.45;
    private RectBoundedBox playerBoundary;

    public Player() {
        super(64, 64, 30, 30);
        init(64, 64);
    }

    public Player(int posX, int posY,int height,int width) {
        super(posX, posY, height, width);
        init(posX, posY);
        health = 100;
        isAlive = true;
        layer=0;
    }

    private void init(int x, int y) {
        name = "Player";
        setScale(2);
        positionX = x;
        positionY = y;
        playerAnimations = new PlayerAnimations();
        playerBoundary = new RectBoundedBox(positionX,
                positionY,
                (30* getScale())-2*+(int)(30*getReduceBoundarySizePercent()),
                (30* getScale())-2*+(int)(30*getReduceBoundarySizePercent())
        );
        /*positionX+(int)(30*getReduceBoundarySizePercent()),
                positionY+(int)(30*getReduceBoundarySizePercent()),
                (30* getScale())-2*+(int)(30*getReduceBoundarySizePercent()),
                (30* getScale())-2*+(int)(30*getReduceBoundarySizePercent())*/
    }

    public void move(Direction direction) {
        move(1, direction);
    }
    public int getHealth() {
        return health;
    }
    public boolean isAlive() {
        return isAlive;
    }
    public String toString() {
        return name;
    }

    public void move(int steps, Direction direction) {
        if (steps == 0) {
            setCurrentImage(playerAnimations.getPlayerIdleSprite());
            return;
        } else {
            switchDirection(steps, direction);
        }
    }

    private void switchDirection(int steps, Direction direction) {
        switch (direction) {
            case N:
                if(!checkCollisions(positionX, positionY - steps)) {
                    positionY -= steps;
                    setCurrentImage(playerAnimations.getMoveUpSprite());
                    currentDirection = Direction.N;
                }
                break;
            case S:
                if(!checkCollisions(positionX, positionY + steps)) {
                    positionY += steps;
                    setCurrentImage(playerAnimations.getMoveDownSprite());
                    currentDirection = Direction.S;
                }
                break;
            case W:
                if(!checkCollisions(positionX - steps, positionY)) {
                    positionX -= steps;
                    setCurrentImage(playerAnimations.getMoveLeftSprite());
                    currentDirection = Direction.W;
                }
                break;
            case E:
                if(!checkCollisions(positionX + steps, positionY)) {
                    positionX += steps;
                    setCurrentImage(playerAnimations.getMoveRightSprite());
                    currentDirection = Direction.E;
                }
                break;
            default:
                setCurrentImage(playerAnimations.getPlayerIdleSprite());
        }
    }

    private boolean checkCollisions(int x, int y) {
        playerBoundary.setPosition(x, y,getReduceBoundarySizePercent());
        for (GameObject e : GameEngine.getEntities()) {
            if (e != this && isColliding(e) && !(e instanceof MovableObject)) {
                playerBoundary.setPosition(positionX, positionY,getReduceBoundarySizePercent());
                return true;
            }
        }
        playerBoundary.setPosition(positionX, positionY,getReduceBoundarySizePercent());
        return false;
    }
    private void setCurrentImage(Image s) {
        if (s != null) {
            currentImage = s;
        } else {
            System.out.println("Sprite missing!");
        }
    }
    public void reduceHealth(int damage) {
        if (health - damage <= 0) {
            die();
        } else {
            health -= damage;
        }
    }
    public void die() {
        setCurrentImage(playerAnimations.getPlayerDying());
    }
    public void removeFromScene() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods.
    }
    public int getPositionX() {
        return positionX;
    }
    public int getPositionY() {
        return positionY;
    }
    @Override
    public int getLayer() {
		return layer;
	}
    public int getScale() {
        return scale;
    }
    public void setScale(int scale) {
        this.scale = scale;
    }
    public double getReduceBoundarySizePercent() {
        return reduceBoundarySizePercent;
    }

    public boolean isPlayerCollisionFriendly() {
        return true;
    }
}
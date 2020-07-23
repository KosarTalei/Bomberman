/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ir.ac.kntu.animations;

import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class PlayerAnimations {

    private Image moveRight;
    private Image moveLeft;
    private Image moveUp;
    private Image moveDown;
    private Image idle;
    private Image die;
    private double playSpeed;

    public PlayerAnimations(int scale) {
        playSpeed=0.1;
        moveDown = new Image("C:\\Users\\Mohammad\\Desktop\\hw5-fariborz-bomberman\\resources\\assets\\player" +
                "\\player_down_moving.png");
        moveLeft  = new Image("C:\\Users\\Mohammad\\Desktop\\hw5-fariborz-bomberman\\resources\\assets\\player" +
                "\\player_right_moving.png");
        moveUp    = new Image("C:\\Users\\Mohammad\\Desktop\\hw5-fariborz-bomberman\\resources\\assets\\player" +
                "\\player_up_moving.png");
        moveRight =new Image("C:\\Users\\Mohammad\\Desktop\\hw5-fariborz-bomberman\\resources\\assets\\player" +
                "\\player_right_moving.png");
        idle      = new Image("C:\\Users\\Mohammad\\Desktop\\hw5-fariborz-bomberman\\resources\\assets\\player" +
                "\\player_down_standing.png");
        
        List<Rectangle> specs=new ArrayList<>();
        specs.add(new Rectangle(149, 0,20,21));
        specs.add(new Rectangle(179, 1,19,20));
        specs.add(new Rectangle(118, 30,21,21));
        specs.add(new Rectangle(149, 30,20,21));
        specs.add(new Rectangle(179, 30,19,21));
        specs.add(new Rectangle(118, 60,21,21));
        specs.add(new Rectangle(147, 60,23,22));
        die = new Image("C:\\Users\\Mohammad\\Desktop\\hw5-fariborz-bomberman\\resources\\assets\\map\\normal.png");
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
}

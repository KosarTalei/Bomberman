/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ir.ac.kntu.gamecontroller;

import ir.ac.kntu.GameEngine;
import ir.ac.kntu.go.Bomb;
import ir.ac.kntu.go.Direction;
import ir.ac.kntu.go.Player;
import javafx.scene.input.KeyCode;

import java.util.List;

public class InputManager {

    public static void handlePlayerMovements(){

        List keyboardInputs = EventHandler.getInputList();
        Player player = GameEngine.getPlayer();
        //System.err.println(""+keyboardInputs);
        if(keyboardInputs.contains(KeyCode.UP) || keyboardInputs.contains(KeyCode.W)){
            player.move(5, Direction.N);
        }
        if(keyboardInputs.contains(KeyCode.DOWN) || keyboardInputs.contains(KeyCode.S)){
            player.move(5,Direction.S);
        }
        if(keyboardInputs.contains(KeyCode.LEFT) || keyboardInputs.contains(KeyCode.A)){
            player.move(5,Direction.W);
        }
        if(keyboardInputs.contains(KeyCode.RIGHT) || keyboardInputs.contains(KeyCode.D)){
            player.move(5,Direction.E);
        }
        if( !keyboardInputs.contains(KeyCode.LEFT) &&
            !keyboardInputs.contains(KeyCode.RIGHT) &&
            !keyboardInputs.contains(KeyCode.UP) &&
            !keyboardInputs.contains(KeyCode.DOWN) &&
            !keyboardInputs.contains(KeyCode.W) &&
            !keyboardInputs.contains(KeyCode.A) &&
            !keyboardInputs.contains(KeyCode.S) &&
            !keyboardInputs.contains(KeyCode.D)) {
            player.move(0, Direction.S);
        }
        
        //Drop bomb
        if(keyboardInputs.contains(KeyCode.SPACE)){           
            GameEngine.addEntityToGame(new Bomb(player.getPosition().getX(), player.getPosition().getY(),
                    30,30));
        }        
    }

}

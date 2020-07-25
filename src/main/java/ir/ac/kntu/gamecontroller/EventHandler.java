
package ir.ac.kntu.gamecontroller;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;
import java.util.List;

public class EventHandler {

    private static char lastKeyPress;
    private static char lastKeyReleased;
    private static ArrayList<KeyCode> inputList = new ArrayList<KeyCode>();

    public static void attachEventHandlers(Scene s){
        KeyReleaseHandler krh = new KeyReleaseHandler();
        KeyPressedHandler kph = new KeyPressedHandler();
        s.setOnKeyReleased(krh);
        s.setOnKeyPressed(kph);
    }

    public boolean isKeyDown(KeyCode k) {
    	if( inputList.contains(k)){
    		return true;
        }else{
            return false;
        }
    }
    
    public static List getInputList(){
        return new ArrayList(inputList);
    }

    public static char getLastKeyPress() {
        return lastKeyPress;
    }

    public static char getLastKeyReleased() {
        return lastKeyReleased;
    }
}

class KeyReleaseHandler implements javafx.event.EventHandler<KeyEvent> {
    public KeyReleaseHandler() {
    }

    @Override
    public void handle(KeyEvent evt) {
        //System.out.println("The key released is : "+evt.getText()+" with keycode "+evt.getCode().getName());
        KeyCode code = evt.getCode();

        if (EventHandler.getInputList().contains(code)) {
            EventHandler.getInputList().remove(code);
        }
    }
}

class KeyPressedHandler implements javafx.event.EventHandler<KeyEvent> {
    @Override
    public void handle(KeyEvent evt) {
        //System.out.println("The key pressed is : "+evt.getText()+" with keycode "+evt.getCode().getName());
        KeyCode code = evt.getCode();
        // only add once... prevent duplicates
        if (!EventHandler.getInputList().contains(code)) {
            EventHandler.getInputList().add(code);
        }
    }
}
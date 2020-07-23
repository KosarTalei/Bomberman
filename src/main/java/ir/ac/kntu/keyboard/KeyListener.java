package ir.ac.kntu.keyboard;

import java.util.List;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public interface KeyListener {
	List<KeyCode> interestedIn();

	void notify(KeyEvent keyEvent);
}

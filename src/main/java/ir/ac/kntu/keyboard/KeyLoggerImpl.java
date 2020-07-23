package ir.ac.kntu.keyboard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;


public class KeyLoggerImpl implements KeyLogger, EventHandler<KeyEvent> {

	private Map<KeyCode, List<KeyListener>>	map	= new HashMap<>();

	@Override
	public void init(Scene scene) {
		scene.setOnKeyPressed(this);
		scene.setOnKeyReleased(this);
	}

	@Override
	public void registerListener(KeyListener listener) {
		List<KeyCode> list = listener.interestedIn();
		for (KeyCode keyCode : list) {
			if (map.containsKey(keyCode)) {
				map.get(keyCode).add(listener);
			} else {
				List<KeyListener> listeners = new ArrayList<>();
				listeners.add(listener);
				map.put(keyCode, listeners);
			}
		}

	}

	@Override
	public void handle(KeyEvent keyEvent) {
		List<KeyListener> list = map.get(keyEvent.getCode());
		if (null != list) {
			for (KeyListener keyListener : list) {
				keyListener.notify(keyEvent);
			}
		}
	}

	@Override
	public void removeListener(KeyListener listener) {
		List<KeyCode> interestedIn = listener.interestedIn();
		for (KeyCode keyCode : interestedIn) {
			List<KeyListener> list = map.get(keyCode);
			if (null != list) {
				list.remove(listener);
				if (list.size() == 0) {
					map.remove(keyCode);
				}
			}

		}
	}

}

package ir.ac.kntu.go;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GameObjectManager {

	private List<GameObject> actors	= new ArrayList<>();
	private final Set<GameObject>	cleanUpSprites = new HashSet<>();

	public List<GameObject> getActors() {
		return new ArrayList<>(actors);
	}

	public void addGo(GameObject... gameObjects) {
		actors.addAll(Arrays.asList(gameObjects));
	}

	public void removeGo(GameObject... gameObjects) {
		actors.removeAll(Arrays.asList(gameObjects));
	}

	public Set<GameObject> getCleanUpSprites() {
		return new HashSet<>(cleanUpSprites);
	}

	public void addGosToBeRemoved(GameObject... gameObjects) {
		cleanUpSprites.addAll(Arrays.asList(gameObjects));
	}

	public void cleanupGo() {
		actors.removeAll(cleanUpSprites);
		cleanUpSprites.clear();
	}

	public void addActor(GameObject actor) {
		actors.add(actor);
	}
}

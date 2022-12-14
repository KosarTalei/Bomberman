package ir.ac.kntu.factory;

import ir.ac.kntu.go.*;

public interface BombermanObjectFactory {

	MovableObject getMovableObject(int x, int y);

	PlayerEnemy getPlayerEnemy(int x, int y);

	Player getPlayer(int x, int y);

	Wall getWall(int x, int y);

	Bomb getBomb(int x, int y);

	Grass getGrass(int x, int y);

	Brick getBrick(int x, int y);

}

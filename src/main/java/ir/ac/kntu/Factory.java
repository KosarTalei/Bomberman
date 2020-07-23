package ir.ac.kntu;

import ir.ac.kntu.factory.BombermanObjectFactory;
import ir.ac.kntu.factory.BombermanObjectFactoryImpl;
import ir.ac.kntu.factory.GameObjectFactory;
import ir.ac.kntu.keyboard.KeyLogger;
import ir.ac.kntu.keyboard.KeyLoggerImpl;
import ir.ac.kntu.map.BombermanMapGeneratorImpl;
import ir.ac.kntu.map.BombermanMapParserImpl;
import ir.ac.kntu.map.MapGenerator;
import ir.ac.kntu.map.MapParser;

public final class Factory {
	private static final Factory	FACTORY	= new Factory();

	private final KeyLogger			keyLogger;
	private final GameObjectFactory	factory;
	private final MapParser<?>		mapParser;
	private final MapGenerator<?>	generator;

	private Factory() {
		keyLogger = new KeyLoggerImpl();
		factory = new BombermanObjectFactoryImpl(keyLogger);
		mapParser = new BombermanMapParserImpl();
		generator = new BombermanMapGeneratorImpl((BombermanObjectFactory) factory);
	}

	public static KeyLogger getKeyLogger() {
		return FACTORY.keyLogger;
	}

	public static GameObjectFactory getFactory() {
		return FACTORY.factory;
	}

	public static MapGenerator<?> getGenerator() {
		return FACTORY.generator;
	}

	public static MapParser<?> getMapParser() {
		return FACTORY.mapParser;
	}
}

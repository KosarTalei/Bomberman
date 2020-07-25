package ir.ac.kntu.map;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public abstract class MapParser<Entity> {

	public List<List<Entity>> parse(File file) {
		List<List<Entity>> result = new ArrayList<>();

		if (null == file || !file.isFile()) {
			throw new IllegalArgumentException("Suplied argument is not valid file: " + file);
		}

		try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
			String line;
			while ((line = br.readLine()) != null) {
				List<Entity> row = new ArrayList<>();
				int length = line.length();
				for (int i = 0; i < length; i++) {
					row.add(parseEntity(line.charAt(i)));
				}
				result.add(row);
			}

		} catch (IOException e) {
			throw new RuntimeException("Error while reading map file.", e);
		}
		return result;
	}

	protected abstract Entity parseEntity(char charAt);

}

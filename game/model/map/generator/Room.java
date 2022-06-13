package model.map.generator;

import model.map.Case;

import org.json.JSONArray;
import org.json.JSONObject;


public class Room {
	
	int upperLeftX;
	int upperLeftY;

	int width;
	int height;

	Case comp[][];
	int type;


	Room (int x, int y, Case[][] composition, int t) {
		comp = new Case[x][y];

		width = x;
		height = y;

		type = t;

		for (int i = 0; i < x; i++) {

			for (int j = 0; j < y; j++) {
				comp[i][j] = composition[i][j];
			}
		}

	}

	Room (String str) {
		JSONObject json = new JSONObject(str);
		height = json.getInt("height");
		width = json.getInt("width");
		type = json.getInt("type");

		comp = new Case[height][width];

		JSONArray composition = json.getJSONArray("composition");

		for (int i = 0; i < height; i++) {
			JSONArray line = composition.getJSONArray(i);

			for (int j = 0; j < width; j++) {
				comp[i][j] = new Case(line.getInt(j));
			}
		}

	}

	String toJSON () {
		return null;
	}

}

package model.map.generator;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import model.map.Case;


public class JsonDecode {

	FileReader fr;
	JSONArray rooms;
	int nbRooms;


	public JsonDecode (String jsonPath) throws ParseException, IOException {
		fr = new FileReader(jsonPath);
		JSONParser parser = new JSONParser();
		Object o = parser.parse(fr);
		rooms = (JSONArray) o;
		nbRooms = rooms.size();
	}

	/*
	 * A partir de l'ID de la pièce, on récupère dans le fichier rooms.json les
	 * infos pour renvoyer une nouvelle instance de Room
	 */
	public Room newRoom (int roomID) {
		int width, height;
		Case comp[][];
		int type;
		JSONObject room, firstColumn;
		Object[] roomInfos, firstColumnInfos;
		JSONArray columns, firstColumnRows;

		room = (JSONObject) rooms.get(roomID);
		roomInfos = room.values().toArray();
		type = ((Long) roomInfos[1]).intValue();
		columns = (JSONArray) roomInfos[0];
		width = columns.size();

		firstColumn = (JSONObject) columns.get(0);
		firstColumnInfos = firstColumn.values().toArray();
		firstColumnRows = (JSONArray) firstColumnInfos[0];
		height = firstColumnRows.size();

		comp = new Case[width][height];

		for (int i = 0; i < width; i++) {
			JSONObject column = (JSONObject) columns.get(i);
			Object[] columnInfos = column.values().toArray();
			JSONArray columnRows = (JSONArray) columnInfos[0];
			if (columnRows.size() != height)
				throw new IllegalStateException("Wrong row size for room[" + roomID + "] at colmun[" + i + "]");

			for (int j = 0; j < height; j++) {
				JSONObject tile = (JSONObject) columnRows.get(j);
				Object[] tileInfos = tile.values().toArray();
				Case tuile = new Case(((Long) tileInfos[0]).intValue());
				comp[i][j] = tuile;
			}
		}
		return new Room(width, height, comp, type);
	}

	public int getNbRooms () {
		return nbRooms;
	}

}

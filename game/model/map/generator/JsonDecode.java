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
		JSONObject room, firstRow;
		Object[] roomInfos, firstRowInfos;
		JSONArray rows, firstRowColumns;

		room = (JSONObject) rooms.get(roomID);
		roomInfos = room.values().toArray();
		type = ((Long) roomInfos[0]).intValue();
		rows = (JSONArray) roomInfos[1];
		height = rows.size();

		firstRow = (JSONObject) rows.get(0);
		firstRowInfos = firstRow.values().toArray();
		firstRowColumns = (JSONArray) firstRowInfos[0];
		width = firstRowColumns.size();

		comp = new Case[width][height];

		for (int j = 0; j < height; j++) {
			JSONObject row = (JSONObject) rows.get(j);
			Object[] rowInfos = row.values().toArray();
			JSONArray rowColumns = (JSONArray) rowInfos[0];
			if (rowColumns.size() != width)
				throw new IllegalStateException("Wrong column size for room[" + roomID + "] at row[" + j + "]");

			for (int i = 0; i < width; i++) {
				Case tile = new Case(((Long) rowColumns.get(i)).intValue());
				comp[i][j] = tile;
			}
		}
		return new Room(width, height, comp, type);
	}

	public int getNbRooms () {
		return nbRooms;
	}

}
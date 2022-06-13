package model.map;

import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.parser.ParseException;

import model.map.generator.JsonDecode;
import model.map.generator.Room;


public class World {

	ArrayList<Room> rooms; //Totalité des salles pour pouvoir piocher dedans
	JsonDecode jd;


	public World (String jsonPath) throws ParseException, IOException {
		jd = new JsonDecode(jsonPath);
		rooms = new ArrayList<Room>();

		for (int i = 0; i < jd.getNbRooms(); i++) {
			rooms.add(jd.newRoom(i));
		}
	}

}

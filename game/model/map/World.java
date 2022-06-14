package model.map;

import java.io.IOException;

import org.json.simple.parser.ParseException;

import edu.polytech.oop.collections.ArrayList;
import model.map.generator.JsonDecode;


public class World {

	ArrayList rooms; //Totalité des salles pour pouvoir piocher dedans
	JsonDecode jd;


	public World (String jsonPath) throws ParseException, IOException {
		jd = new JsonDecode(jsonPath);
		rooms = new ArrayList();

		for (int i = 0; i < jd.getNbRooms(); i++) {
			rooms.insertAt(rooms.length(), jd.newRoom(i));
		}
	}

}

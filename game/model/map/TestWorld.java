package model.map;

import java.io.IOException;

import org.json.simple.parser.ParseException;


public class TestWorld {

	public static void main (String[] args) throws ParseException, IOException {
		World w = new World("resources/rooms.json");
	}

}

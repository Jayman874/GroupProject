package test.nz.ac.vuw.ecs.swen225.gp21.fuzz;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import nz.ac.vuw.ecs.swen225.gp21.domain.*;
import org.junit.Test;
//import org.junit.jupiter.api.Test;

public class FuzzTest {

	@Test
	public void test1() {
		new Board();
		Chap chap = new Chap();
		Tile[][] tile = Board.getBoard();
		FreeTile freeTile = new FreeTile();
		freeTile.getLocation();
		freeTile.toString();
		double a = Math.random() * 2;
		double b = Math.random() * 2;
		tile[0][0] = chap;
		tile[0][1] = freeTile;
		Location chapLoc = new Location(0, 0);
		Location freeLoc = new Location((int)a, (int)b);
		chap.setLocation(chapLoc);
		freeTile.setLocation(freeLoc);
		freeTile.setLocation(freeLoc);
		if ((chap.isValid(freeLoc))) {
			System.out.println(a + " " + b);
			Board.updateBoard(chap, freeLoc);
			assertTrue(true);
		} else {
			fail("Chap cannot move into this tile");
		}
	}
}
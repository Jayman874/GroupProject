package test.nz.ac.vuw.ecs.swen225.gp21.fuzz;

import nz.ac.vuw.ecs.swen225.gp21.domain.*;
import nz.ac.vuw.ecs.swen225.gp21.persistency.levels.level2.Actor;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;
//import org.junit.jupiter.api.Test;

public class FuzzTest {

	@Test
	public void level1() {
		try {
			Board board = new Board();
			board.makeBoard();
			Chap chap = new Chap();
			Tile[][] tile = Board.getBoard();
			FreeTile freeTile = new FreeTile();
			Door redDoor = new Door("r");
			freeTile.getLocation();
			freeTile.toString();
			double a = Math.random() * 2;
			double b = Math.random() * 2;
			tile[0][0] = chap;
			tile[0][1] = freeTile;
			tile[1][1] = redDoor;
			Location chapLoc = new Location(0, 0);
			Location freeLoc = new Location((int) a, (int) b);
			Location redDoorLoc = new Location(0, 1);
			chap.setLocation(chapLoc);
			freeTile.setLocation(freeLoc);
			freeTile.setLocation(freeLoc);
			redDoor.setLocation(redDoorLoc);
			if ((chap.isValid(freeLoc))) {
				System.out.println(a + " " + b);
				Board.updateBoard(chap, freeLoc);
				assertTrue(true);
			}
			if (chap.isValid(redDoorLoc)) {
				Board.updateBoard(chap, redDoorLoc);

			}
		}	catch (IllegalArgumentException e) {
				fail("Key not picked up");
				assertTrue(true);
			}
	}

	@Test
	public void level2() {
		Board board = new Board();
		board.makeBoard2();
		Chap chap = new Chap();
		Actor actor = new Actor();
		Door door = new Door("Blue");
		Key key = new Key("Blue");

		Tile[][] tile = Board.getBoard();
		FreeTile freeTile = new FreeTile();
		freeTile.getLocation();
		freeTile.toString();
		door.setLockedDoorColour("Green");
		double a = Math.random() * 2;
		double b = Math.random() * 2;
		tile[0][0] = chap;
		tile[0][1] = freeTile;
		tile[2][2] = actor;
		Location chapLoc = new Location(0, 0);
		Location freeLoc = new Location((int)a, (int)b);

		Location actorLoc = new Location(2, 2);

		chap.setLocation(chapLoc);
		freeTile.setLocation(freeLoc);
		freeTile.setLocation(freeLoc);
		actor.setLocation(actorLoc);
		door.setLocation(freeLoc);
		key.setLocation(freeLoc);

		chap.setDead(false);
		if ((chap.isValid(freeLoc))) {
			System.out.println(a + " " + b);
			Board.updateBoard(chap, freeLoc);
			assertTrue(true);
			assertTrue(actor.actorMoveCheck(tile, 2, 3));
		} else {
			fail("Chap cannot move into this tile");
		}

		try {
			new Board();
			Board.setTotalLevelTreasureDirect(2);
			chap = new Chap();
			tile = Board.getBoard();
			Treasure treasure = new Treasure();
			ExitLock exit = new ExitLock();
			ExitTile exitTile = new ExitTile();
			tile[0][0] = chap;
			tile[0][1] = treasure;
			tile[0][2] = exit;
			tile[0][3] = exitTile;
			exitTile.getLocation();
			treasure.getLocation();
			chapLoc = new Location(0, 0);
			chap.setLocation(chapLoc);
			Location treasureLoc = new Location(1, 0);
			Location exitLoc = new Location(2, 0);
			Location exitTileLoc = new Location(3, 0);
			treasure.setLocation(treasureLoc);
			exit.setLocation(exitLoc);
			exitTile.setLocation(exitTileLoc);
			if (chap.isValid(treasureLoc)) {
				Board.updateBoard(chap, treasureLoc);
			}
			if (chap.isValid(exitLoc)) {
				Board.updateBoard(chap, exitLoc);
				fail("Should be able to go through door");
			}
			if (chap.isValid(exitTileLoc)) {
				Board.updateBoard(chap, exitTileLoc);
			}
		} catch (IllegalArgumentException e) {
			assertTrue(true);
		}
	}
}
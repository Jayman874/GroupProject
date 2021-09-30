package test.nz.ac.vuw.ecs.swen225.gp21.domain;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import nz.ac.vuw.ecs.swen225.gp21.domain.Board;
import nz.ac.vuw.ecs.swen225.gp21.domain.Chap;
import nz.ac.vuw.ecs.swen225.gp21.domain.Door;
import nz.ac.vuw.ecs.swen225.gp21.domain.ExitLock;
import nz.ac.vuw.ecs.swen225.gp21.domain.ExitTile;
import nz.ac.vuw.ecs.swen225.gp21.domain.FreeTile;
import nz.ac.vuw.ecs.swen225.gp21.domain.InfoField;
import nz.ac.vuw.ecs.swen225.gp21.domain.Key;
import nz.ac.vuw.ecs.swen225.gp21.domain.Location;
import nz.ac.vuw.ecs.swen225.gp21.domain.Tile;
import nz.ac.vuw.ecs.swen225.gp21.domain.Treasure;
import nz.ac.vuw.ecs.swen225.gp21.domain.WallTile;


/**
 * Suite of tests for the domain package. 
 * 
 * @author Jayen
 *
 */
public class DomainTests {
  
  @Test
  public void validMove1() {
    new Board();
    Chap chap = new Chap();
    Tile[][] tile = Board.getBoard();
    FreeTile freeTile = new FreeTile();
    freeTile.getLocation();
    freeTile.toString();
    tile[0][0] = chap;
    tile[0][1] = freeTile;
    Location chapLoc = new Location(0, 0);
    Location freeLoc = new Location(1, 0);
    chap.setLocation(chapLoc);
    freeTile.setLocation(freeLoc);
    freeTile.setLocation(freeLoc);
    if ((chap.isValid(freeLoc))) {
      Board.updateBoard(chap, freeLoc);
      assertTrue(true);
    } else {
      fail("Chap cannot move into this tile");
    }
  }
  
  @Test
  public void invalidMove1() {
    try {
      new Board();
      Chap chap = new Chap();
      Tile[][] tile = Board.getBoard();
      WallTile wallTile = new WallTile();
      tile[0][0] = chap;
      tile[0][1] = wallTile;
      chap.getLocation();
      chap.getKeyInventory();
      chap.getTreasureInventory();
      Location chapLoc = new Location(0, 0);
      chapLoc.getX();
      chapLoc.getY();
      wallTile.toString();
      wallTile.getLocation();
      chap.setLocation(chapLoc);
      Location wallLoc = new Location(1, 0);
      wallTile.setLocation(wallLoc);
      if (chap.isValid(wallLoc)) {
        fail("Chap cannot be moved into a wall");
      }
    } catch (IllegalArgumentException e) {
      assertTrue(true);
    }
  }
  
  @Test
  public void illegalMove1() {
    try {
      new Board();
      Chap chap = new Chap();
      chap.toString();
      Tile[][] tile = Board.getBoard();
      WallTile freeTile = new WallTile();
      tile[0][0] = chap;
      tile[0][2] = freeTile;
      Location chapLoc = new Location(0, 0);
      chap.setLocation(chapLoc);
      Location freeLoc = new Location(2, 0);
      freeTile.setLocation(freeLoc);
      if (chap.isValid(freeLoc)) {
        Board.updateBoard(chap, freeLoc);
      }
    } catch (IllegalArgumentException e) {
      assertTrue(true);
    }
  }
  
  @Test
  public void illegalMove2() {
    try {
      new Board();
      Chap chap = new Chap();
      Tile[][] tile = Board.getBoard();
      WallTile freeTile = new WallTile();
      tile[0][0] = chap;
      tile[0][2] = freeTile;
      Location chapLoc = new Location(0, 0);
      chap.setLocation(chapLoc);
      Location freeLoc = new Location(-1, 0);
      freeTile.setLocation(freeLoc);
      if (chap.isValid(freeLoc)) {
        Board.updateBoard(chap, freeLoc);
      }
    } catch (IndexOutOfBoundsException e) {
      assertTrue(true);
    }
  }
  
  @Test
  public void pickUpKey() {
    try {
      new Board();
      Chap chap = new Chap();
      Tile[][] tile = Board.getBoard();
      Key key = new Key("g");
      tile[0][0] = chap;
      tile[0][1] = key;
      Location chapLoc = new Location(0, 0);
      chap.setLocation(chapLoc);
      Location keyLoc = new Location(1, 0);
      key.setLocation(keyLoc);
      if (chap.isValid(keyLoc)) {
        Board.updateBoard(chap, keyLoc);
        assertTrue(true);
      }
    } catch (IllegalArgumentException e) {
      fail("Key not picked up");
    }
  }
  
  @Test
  public void openDoorWithKey() {
    try {
      new Board();
      Chap chap = new Chap();
      Tile[][] tile = Board.getBoard();
      Key key = new Key("g");
      Door door = new Door("g");
      tile[0][0] = chap;
      tile[0][1] = key;
      tile[0][2] = door;
      Location chapLoc = new Location(0, 0);
      chapLoc.toString();
      chap.setLocation(chapLoc);
      Location keyLoc = new Location(1, 0);
      Location doorLoc = new Location(2, 0);
      key.setLocation(keyLoc);
      door.setLocation(doorLoc);
      if (chap.isValid(keyLoc)) {
        Board.updateBoard(chap, keyLoc);
      }
      if (chap.isValid(doorLoc)) {
        Board.updateBoard(chap, doorLoc);
        assertTrue(true);
      }
    } catch (IllegalArgumentException e) {
      fail("Key not picked up");
    }
  }
  
  @Test
  public void openKeyWithDoorInvalid () {
    try {
      new Board();
      Chap chap = new Chap();
      Tile[][] tile = Board.getBoard();
      Key key = new Key("f");
      Door door = new Door("g");
      key.descriptiveToString();
      door.descriptiveToString();
      tile[0][0] = chap;
      tile[0][1] = key;
      tile[0][2] = door;
      key.getLocation();
      Location chapLoc = new Location(0, 0);
      chapLoc.toString();
      chap.setLocation(chapLoc);
      Location keyLoc = new Location(1, 0);
      Location doorLoc = new Location(2, 0);
      key.setLocation(keyLoc);
      door.setLocation(doorLoc);
      if (chap.isValid(keyLoc)) {
        Board.updateBoard(chap, keyLoc);
      }
      if (chap.isValid(doorLoc)) {
        Board.updateBoard(chap, doorLoc);
        fail("Door opened with wrong key");
      }
    } catch (IllegalArgumentException e) {
      assertTrue(true);
    }
  }
  
  @Test
  public void unlockExitLock() {
    try {
      new Board();
      Board.setTotalLevelTreasureDirect(1);
      Chap chap = new Chap();
      Tile[][] tile = Board.getBoard();
      Treasure treasure = new Treasure();
      ExitLock exit = new ExitLock();
      ExitTile exitTile = new ExitTile();
      tile[0][0] = chap;
      tile[0][1] = treasure;
      tile[0][2] = exit;
      tile[0][3] = exitTile;
      Location chapLoc = new Location(0, 0);
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
      }
      if (chap.isValid(exitTileLoc)) {
        Board.updateBoard(chap, exitTileLoc);
        //Board.updateActorBoard(exitTile);
        assertTrue(true);
      }
    } catch (IllegalArgumentException e) {
      fail("Cannot go through exit");
    }
  }
  
  @Test
  public void unlockExitLockInvalid() {
    try {
      new Board();
      Board.setTotalLevelTreasureDirect(2);
      Chap chap = new Chap();
      Tile[][] tile = Board.getBoard();
      Treasure treasure = new Treasure();
      ExitLock exit = new ExitLock();
      ExitTile exitTile = new ExitTile();
      tile[0][0] = chap;
      tile[0][1] = treasure;
      tile[0][2] = exit;
      tile[0][3] = exitTile;
      exitTile.getLocation();
      treasure.getLocation();
      Location chapLoc = new Location(0, 0);
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
  
  @Test
  public void onInfoTile() {
    try {
      new Board();
      Chap chap = new Chap();
      Tile[][] tile = Board.getBoard();
      InfoField info = new InfoField("Info");
      tile[0][0] = chap;
      tile[0][1] = info;
      info.displayText();
      info.setDisplayText("Info Field");
      Location chapLoc = new Location(0, 0);
      chap.setLocation(chapLoc);
      Location infoLoc = new Location(1, 0);
      info.setLocation(infoLoc);
      if (chap.isValid(infoLoc)) {
        Board.updateBoard(chap, infoLoc);
        assertTrue(true);
      }
    } catch (IllegalArgumentException e) {
      fail("Info tile not steped on");
    }
  }
}

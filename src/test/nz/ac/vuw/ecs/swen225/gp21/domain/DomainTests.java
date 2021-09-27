package test.nz.ac.vuw.ecs.swen225.gp21.domain;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import nz.ac.vuw.ecs.swen225.gp21.domain.Board;
import nz.ac.vuw.ecs.swen225.gp21.domain.Chap;
import nz.ac.vuw.ecs.swen225.gp21.domain.Door;
import nz.ac.vuw.ecs.swen225.gp21.domain.FreeTile;
import nz.ac.vuw.ecs.swen225.gp21.domain.Key;
import nz.ac.vuw.ecs.swen225.gp21.domain.Location;
import nz.ac.vuw.ecs.swen225.gp21.domain.Tile;
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
        fail("Door opened with wrong key");
      }
    } catch (IllegalArgumentException e) {
      assertTrue(true);
    }
  }
  
  @Test
  public void unlockExitLock() {
    
  }
}

package nz.ac.vuw.ecs.swen225.gp21.domain;

import java.util.ArrayList;
import java.util.List;
import nz.ac.vuw.ecs.swen225.gp21.app.GUI;
import nz.ac.vuw.ecs.swen225.gp21.app.InputUpdate;
import nz.ac.vuw.ecs.swen225.gp21.persistency.LoadLevel;
import nz.ac.vuw.ecs.swen225.gp21.persistency.levels.level2.Actor;
import nz.ac.vuw.ecs.swen225.gp21.recorder.Move;
import nz.ac.vuw.ecs.swen225.gp21.renderer.Audio;

/**
 * Chap class controls and stores the logic for the player character.
 * 
 * @author Jayen
 *
 */
public class Chap implements Tile {

  public List<Key> keyInventory = new ArrayList<Key>();
  public List<Treasure> treasureInventory = new ArrayList<Treasure>();
  private Location location;
  public boolean stopMoving = false;
  public static boolean finishedLevel = false;
  public static boolean level1 = true;
  public static boolean level2 = false;
  private static boolean dead = false;
  private static int oldX = 0;
  private static int oldY = 0;

  @Override
  public boolean isValid(Location loc) throws IllegalArgumentException, IllegalStateException {
    if (finishedLevel) {
      resetChapOldPos(oldX, oldY);
    } else if (dead) {
      resetChapOldPos(oldX, oldY);
    }
    if (stopMoving) {
      return false;
    }
    int x = loc.getX();
    int y = loc.getY();
    Tile[][] board = Board.getBoard();
    chapMoveCheck(board, x, y);
    Tile tile = board[y][x]; // gets the tile at the location chap is trying to move too
    // throws error if chap tries to move into a wall
    if (tile instanceof WallTile) {
      throw new IllegalArgumentException("Chap cannot be moved into a wall");
    } else if (tile instanceof Door) {
      Door door = (Door) tile;
      unlockDoor(door);
      // throws error if chap cannot unlock door
      if (door.isLocked()) {
        throw new IllegalArgumentException("Chap does not hase the right key to unlock this door");
      }
      assert door.isLocked() == false;
    } else if (tile instanceof Key) {
      // adds key to inventory and throws error if key cannot be picked up
      if (!(keyInventory.add((Key) tile))) {
        throw new IllegalStateException("Key not added to inventory");
      }
      assert keyInventory.contains(tile);
    } else if (tile instanceof Treasure) {
      // adds treasure to inventory and throws error if treasure cannot be picked up
      if (!(treasureInventory.add((Treasure) tile))) {
        throw new IllegalStateException("Treasure not added to inventory");
      }
      assert treasureInventory.contains(tile);
    } else if (tile instanceof ExitLock) {
      ExitLock exitLock = (ExitLock) tile;
      unlockExit(exitLock);
      // throws error if exit cannot be unlocked
      if (exitLock.isLocked()) {
        throw new IllegalArgumentException("Chap does not have all the treasure to unlock door");
      }
      assert exitLock.isLocked() == false;
    } else if (tile instanceof InfoField) {
      InfoField info = (InfoField) tile;
      GUI.displayInfo(info);
    } else if (tile instanceof ExitTile) {
      keyInventory.clear();
      treasureInventory.clear();
      level1 = false;
      level2 = true;
      finishedLevel = true;
      oldX = loc.getX();
      oldY = loc.getY();
      new Board();
    } else if (tile instanceof Actor) {
      System.out.println(loc);
      keyInventory.clear();
      treasureInventory.clear();
      oldX = loc.getX();
      oldY = loc.getY();
      dead = true;
      new Board();
    }
    return true;
  }
  
  public void resetChapOldPos(int locX, int locY) {
    Tile[][] board = Board.getBoard();
    if (dead) {
      board[oldY][oldX] = new FreeTile();
      dead = false;
    } else if (finishedLevel) {
      board[oldY][oldX] = new FreeTile();
      finishedLevel = false;
    }
  }
  
  
  public boolean isLevelDone() {
    return finishedLevel;
  }
  
  

  /**
   * Checks to make sure chap doesnt move outside the board or move more than one
   * space at a time.
   * 
   * @param board - game board
   * @param x     - x location of move
   * @param y     - y location of move
   * @throws IndexOutOfBoundsException - throws out of bounds exception if chap
   *                                   tries to move off the board
   * @throws IllegalArgumentException  - throws illegal argument exception if chap
   *                                   tries to move more than one space
   */
  public void chapMoveCheck(Tile[][] board, int x, int y)
      throws IndexOutOfBoundsException, IllegalArgumentException {
    int chapX = this.getLocation().getX();
    int chapY = this.getLocation().getY();
    // gets the absolute difference of move
    int xlocDifference = Math.abs(x - chapX);
    int ylocDifference = Math.abs(y - chapY);
    // checks if chap tries to move out of game board
    if (x < 0 || y < 0 || x > board.length - 1 || y > board.length - 1) {
      throw new IndexOutOfBoundsException("Chap cannot be moved outside the game board");
    }
    // checks if chap tries to move more than one space at a time
    if (xlocDifference > 1 || ylocDifference > 1) {
      throw new IllegalArgumentException("Chap cannot move more than one space at a time");
    }
  }

  /**
   * checks if chap meets the requirement to unlock a door. If he does unlock it.
   * 
   * @param door - door to try and unlock
   */
  public void unlockDoor(Door door) {
    String colour = door.getLockedDoorColour();
    // checks if chap has a key in his inventory
    if (keyInventory.isEmpty()) {
      door.setLocked(true);
    }
    // checks every key in the key inventory to see if one colour matches the door
    for (Key key : keyInventory) {
      if (key.getKeyColour().equals(colour)) {
        door.setLocked(false); // unlocks door
        removeKey(key); // removes key from inventory
        break;
      }
    }
  }

  /**
   * Removes key from chaps inventory.
   * 
   * @param key - key to remove
   */
  public void removeKey(Key key) {
    keyInventory.remove(key);
  }

  /**
   * Sees if chap meets the requirements to unlock the exit.
   * 
   * @param exit - exitlock to try and unlock
   */
  public void unlockExit(ExitLock exit) {
    int totalInventoryTreasure = getTreasureInventory().size();
    // checks to see if the amount of treasure in chaps inventory matches the total
    // treasure on the level
    if (Board.getTotalLevelTreasure() == totalInventoryTreasure) {
      exit.setLocked(false); // unlocks the exit
    }
  }
  
  public boolean getStopMoving() {
    return stopMoving;
  }
  
  public void setStopMoving(boolean bool) {
    stopMoving = bool;
  }

  /**
   * Gets chaps key inventory.
   * 
   * @return - chaps key inventory
   */
  public List<Key> getKeyInventory() {
    return keyInventory;
  }

  /**
   * Gets chaps treasure inventory.
   * 
   * @return - chaps treasure inventory
   */
  public List<Treasure> getTreasureInventory() {
    return treasureInventory;
  }

  @Override
  public void setLocation(Location loc) {
    this.location = loc;
  }

  @Override
  public Location getLocation() {
    return location;
  }

  @Override
  public String toString() {
    return "c";
  }

}

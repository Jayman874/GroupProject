package nz.ac.vuw.ecs.swen225.gp21.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Chap class controls and stores the logic for the player character. 
 * 
 * @author Jayen
 *
 */
public class Chap implements Tile {
  
  public List<Key> key_inventory = new ArrayList<Key>();
  public List<Treasure> treasure_inventory = new ArrayList<Treasure>();
  private boolean pickupable = false;
  private boolean interactable = false;
  private Location location;

  @Override
  public boolean isPickupable() {
    return pickupable;
  }

  @Override
  public boolean isInteractable() {
    return interactable;
  }

  @Override
  public boolean isValid(Location loc) throws IllegalArgumentException, IllegalStateException {
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
      System.out.println(door.toString());
      unlockDoor(door);
      // throws error if chap cannot unlock door 
      if (door.isLocked()) {
        throw new IllegalArgumentException("Chap does not have the right key to unlock this door");
      }
    } else if (tile instanceof Key) {
      // adds key to inventory and throws error if key cannot be picked up
      if (!(key_inventory.add((Key) tile))) {
        throw new IllegalStateException("Key not added to inventory");
      }
    } else if (tile instanceof Treasure) {
      //adds treasure to inventory and throws error if treasure cannot be picked up
      if (!(treasure_inventory.add((Treasure) tile))) {
        throw new IllegalStateException("Treasure not added to inventory");
      }
    } else if (tile instanceof ExitLock) {
      ExitLock exit_lock = (ExitLock) tile;
      unlockExit(exit_lock);
      // throws error if exit cannot be unlocked 
      if (exit_lock.isLocked()) {
        throw new IllegalArgumentException("Chap does not have all the treasure to unlock door");
      }
    } else if (tile instanceof InfoField) {
      InfoField info = (InfoField) tile;
      System.out.println(info.displayText());
    } else if (tile instanceof ExitTile) {
      System.out.println("You win");
    }
    return true;
  }
  
  /**
   * Checks to make sure chap doesnt move outside the board or move more than one space at a time.
   * 
   * @param board - game board
   * @param x - x location of move
   * @param y - y location of move
   * @throws IndexOutOfBoundsException - throws out of bounds exception if chap tries to move off the board
   * @throws IllegalArgumentException - throws illegal argument exception if chap tries to move more than one space 
   */
  public void chapMoveCheck(Tile[][] board, int x, int y) throws IndexOutOfBoundsException, IllegalArgumentException{
    int chapX = this.getLocation().getX();
    int chapY = this.getLocation().getY();
    // gets the absolute difference of move 
    int xDifference = Math.abs(x - chapX);
    int yDifference = Math.abs(y - chapY);
    // checks if chap tries to move out of game board
    if (x < 0 || y < 0 || x > board.length - 1 || y > board.length - 1) {
      throw new IndexOutOfBoundsException("Chap cannot be moved outside the game board");
    }
    // checks if chap tries to move more than one space at a time
    if (xDifference > 1 || yDifference > 1) {
      throw new IllegalArgumentException("Chap cannot move more than one space at a time");
    }
  }
  
  /**
   * checks if chap meets the requirement to unlock a door.
   * If he does unlock it.
   * 
   * @param door - door to try and unlock
   */
  public void unlockDoor(Door door) {
    String colour = door.getLockedDoorColour();
    //checks if chap has a key in his inventory
    if (key_inventory.isEmpty()) {
      door.setLocked(true);
    }
    // checks every key in the key inventory to see if one colour matches the door 
    for (Key key : key_inventory) {
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
    key_inventory.remove(key);
  }
  
  /**
   * Sees if chap meets the requirements to unlock the exit. 
   * 
   * @param exit - exitlock to try and unlock 
   */
  public void unlockExit(ExitLock exit) {
    int totalInventoryTreasure = getTreasureInventory().size();
    // checks to see if the amount of treasure in chaps inventory matches the total treasure on the level
    if (Board.getTotalLevelTreasure() == totalInventoryTreasure) {
      exit.setLocked(false); // unlocks the exit 
    } 
  }
  
  /**
   * Gets chaps key inventory.
   * 
   * @return - chaps key inventory
   */
  public List<Key> getKeyInventory() {
    return key_inventory;
  }
  
  /**
   * Gets chaps treasure inventory. 
   * 
   * @return - chaps treasure inventory
   */
  public List<Treasure> getTreasureInventory() {
    return treasure_inventory;
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

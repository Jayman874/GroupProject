package nz.ac.vuw.ecs.swen225.gp21.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Chap class controls and stores the logic for the player character 
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
  public boolean isValid(Location loc) throws IllegalArgumentException{
    int x = loc.getX();
    int y = loc.getY();
    Tile[][] board = Board.getBoard();
    chapMoveCheck(board, x, y);
    Tile tile = board[y][x];
    if (tile instanceof WallTile) {
      throw new IllegalArgumentException("Chap cannot be moved into a wall");
    } else if (tile instanceof Door) {
      Door door = (Door) tile;
      System.out.println(door.toString());
      unlockDoor(door);
      if (door.isLocked()) {
        throw new IllegalArgumentException("Chap does not have the right key to unlock this door");
      }
    } else if (tile instanceof Key) {
      key_inventory.add((Key) tile);
    } else if (tile instanceof Treasure) {
      treasure_inventory.add((Treasure) tile);
    } else if (tile instanceof ExitLock) {
      ExitLock exit_lock = (ExitLock) tile;
      unlockExit(exit_lock);
      if (exit_lock.isLocked()) {
        throw new IllegalArgumentException("Chap does not have all the treasure to unlock the door");
      }
    } else if (tile instanceof InfoField) {
      InfoField info = (InfoField) tile;
      System.out.println(info.displayText());
    } else if (tile instanceof ExitTile) {
      System.out.println("You win");
    }
    return true;
  }
  
  public void chapMoveCheck(Tile[][] board, int x, int y) throws IndexOutOfBoundsException, IllegalArgumentException{
    int chapX = this.getLocation().getX();
    int chapY = this.getLocation().getY();
    int xDifference = Math.abs(x - chapX);
    int yDifference = Math.abs(y - chapY);
    if (x < 0 || y < 0 || x > board.length-1 || y > board.length-1) {
      throw new IndexOutOfBoundsException("Chap cannot be moved outside the game board");
    }
    if (xDifference > 1 || yDifference > 1) {
      throw new IllegalArgumentException("Chap cannot move more than one space at a time");
    }
  }
  
  public void unlockDoor(Door door) {
    String colour = door.getLockedDoorColour();
    if (key_inventory.isEmpty()) {
      door.setLocked(true);
    }
    for (Key key : key_inventory) {
      if (key.getKeyColour().equals(colour)) {
        System.out.println("Unlocked door");
        door.setLocked(false);
        removeKey(key);
        break;
      }
    }
  }
  
  public void removeKey(Key key) {
    key_inventory.remove(key);
  }
  
  public void unlockExit(ExitLock exit) {
    int totalInventoryTreasure = getTreasureInventory().size();
    if (Board.getTotalLevelTreasure() == totalInventoryTreasure) {
      exit.setLocked(false);
    } 
  }
  
  @Override
  public void setLocation(Location loc) {
    this.location = loc;
  }

  @Override
  public Location getLocation() {
    return location;
  }
  
  public List<Key> getKeyInventory() {
    return key_inventory;
  }
  
  public List<Treasure> getTreasureInventory(){
    return treasure_inventory;
  }
  
  @Override
  public String toString() {
    return "Chap: 'c'";
  }

}

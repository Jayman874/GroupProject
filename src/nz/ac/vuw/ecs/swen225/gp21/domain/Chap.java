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
  public boolean isValid(Location loc) {
    int x = loc.getX();
    int y = loc.getY();
    Tile[][] board = Board.getBoard();
    Tile tile = board[y][x];
    if (tile instanceof WallTile) {
      System.out.println("True");
      throw new IllegalArgumentException("Chap cannot be moved into a wall");
    } else if (tile instanceof Door) {
      unlockDoor();
    }
    System.out.println("False");
    return true;
  }
  
  public boolean unlockDoor() {
    return true;
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
    return "c";
  }

  @Override
  public void setTile(Tile tile) {
    tile.setLocation(getLocation());
  }

}

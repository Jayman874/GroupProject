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
    // TODO Auto-generated method stub
    Location nextLocation = loc;
    if (nextLocation.getTile() instanceof WallTile) {
      throw new IllegalArgumentException("Chap cannot be moved into a wall");
    } else if (nextLocation.getTile() instanceof Door) {
      unlockDoor();
    }
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
}

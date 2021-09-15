package nz.ac.vuw.ecs.swen225.gp21.domain;

import java.util.ArrayList;
import java.util.List;

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
  public boolean isValid() {
    // TODO Auto-generated method stub
    return false;
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

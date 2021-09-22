package nz.ac.vuw.ecs.swen225.gp21.domain;

/**
 * ExitLock class controls the logic for the exit locks 
 * 
 * ExitLock can only be unlocked after all the treasure on the map has been collected
 * 
 * @author Jayen
 *
 */
public class ExitLock implements Tile{

  private boolean pickupable = false;
  private boolean interactable = true;
  private boolean locked = true;
  private Location location;
  
  
  public ExitLock() {
    
  }
  
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
    return false;
  }

  public boolean isLocked() {
    return locked;
  }

  public void setLocked(boolean locked) {
    this.locked = locked;
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
    return "q";
  }
  
}

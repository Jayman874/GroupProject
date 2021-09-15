package nz.ac.vuw.ecs.swen225.gp21.domain;

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
  public boolean isValid() {
    // TODO Auto-generated method stub
    return false;
  }

  public boolean isLocked() {
    return locked;
  }

  public void setLocked(boolean locked) {
    this.locked = locked;
  }
  
  @Override
  public String toString() {
    return "Q";
  }

  @Override
  public void setLocation(Location loc) {
    this.location = loc;
  }

  @Override
  public Location getLocation() {
    return location;
  }
  
}

package nz.ac.vuw.ecs.swen225.gp21.domain;

public class Treasure implements Tile{

  private boolean pickupable = true;
  private boolean interactable = true;
  private Location location;
  
  public Treasure() {
    
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
  
  @Override
  public String toString() {
    return "t";
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

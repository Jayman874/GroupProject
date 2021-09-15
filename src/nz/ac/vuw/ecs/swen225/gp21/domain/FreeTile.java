package nz.ac.vuw.ecs.swen225.gp21.domain;

public class FreeTile implements Tile{
  
  private boolean pickupable = false;
  private boolean interactable = false;
  private Location location;
  
  public FreeTile(){
 
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
    return "f";
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

package nz.ac.vuw.ecs.swen225.gp21.domain;

public class Door implements Tile {

  private boolean pickupable = false;
  private boolean interactable = true;
  private boolean locked = true;
  private String colour;
  
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
  
  public Door(String colour) {
    this.colour = colour;
  }
  
  public String getLockedDoorColour() {
    return colour;
  }
  
  public void setLockedDoorColour(String c) {
    this.colour = c;
  }

  public boolean isLocked() {
    return locked;
  }

  public void setLocked(boolean locked) {
    this.locked = locked;
  }
  
}

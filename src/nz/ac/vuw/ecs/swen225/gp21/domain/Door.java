package nz.ac.vuw.ecs.swen225.gp21.domain;

/**
 * Door class controls logic for the doors in the game
 * 
 * Doors can be different colours and can be unlocked depending on if player has correct colour key
 * 
 * @author Jayen
 *
 */
public class Door implements Tile {

  private boolean pickupable = false;
  private boolean interactable = true;
  private boolean locked = true;
  private Location location;
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
  public boolean isValid(Location loc) {
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
  
  @Override
  public String toString() {
    return "l " + getLockedDoorColour();
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
  public void setTile(Tile tile) {
    tile.setLocation(getLocation());
  }
  
}

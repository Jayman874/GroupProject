package nz.ac.vuw.ecs.swen225.gp21.domain;

/**
 * Treasure is a pickupable object which is needed for the player to advance to the next level.
 * When player walks over treasure tile it puts treasure in inventory then replaces tile.
 * 
 * @author Jayen
 *
 */
public class Treasure implements Tile{

  private boolean pickupable = true;
  private boolean interactable = true;
  private Location location;
  
  /**
   * Treasure Constructor. 
   * 
   */
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
  public boolean isValid(Location loc) {
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
  
  @Override
  public String toString() {
    return "t";
  }
}

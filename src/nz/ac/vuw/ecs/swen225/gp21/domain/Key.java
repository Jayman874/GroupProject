package nz.ac.vuw.ecs.swen225.gp21.domain;

/**
 * Keys are pickupable items which allow the player to go through certainn doors
 * 
 * When player walks over key they pick it up and put it in their inventory
 * Key tile is then replaced with a free tile
 * 
 * @author Jayen 
 *
 */
public class Key implements Tile{

  private boolean pickupable = true;
  private boolean interactable = true;
  private String colour; 
  private Location location;
  
  public Key(String colour) {
    this.colour = colour;
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
  
  public String getKeyColour() {
    return colour;
  }
  
  public void setKeyColour(String c) {
    this.colour = c;
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
    return "k";
  }
}

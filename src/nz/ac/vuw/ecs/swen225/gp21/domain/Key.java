package nz.ac.vuw.ecs.swen225.gp21.domain;

/**
 * Keys are pickupable items which allow the player to go through certain
 * doors. When player walks over key they pick it up and put it in their
 * inventory. Key tile is then replaced with a free tile.
 * 
 * @author Jayen
 *
 */
public class Key implements Tile {

  private boolean pickupable = true;
  private boolean interactable = true;
  private String colour;
  private Location location;

  /**
   * Key constructor.
   * 
   * @param colour - colour of key
   */
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

  /**
   * Gets the colour of the key.
   * 
   * @return - colour of key
   */
  public String getKeyColour() {
    return colour;
  }

  /**
   * Sets the colour of the key.
   * 
   * @param c - colour to set key too
   */
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
  
  public String descriptiveToString() {
    return "Key: 'k' - Colour: " + getKeyColour();
  }

  @Override
  public String toString() {
    return "k";
  }
}

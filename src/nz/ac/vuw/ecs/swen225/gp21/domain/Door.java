package nz.ac.vuw.ecs.swen225.gp21.domain;

/**
 * Door class controls logic for the doors in the game.
 * Doors can be different colours and can be unlocked depending on if player has correct colour key.
 * 
 * @author Jayen
 *
 */
public class Door implements Tile {

  private boolean locked = true;
  private Location location;
  private String colour;


  @Override
  public boolean isValid(Location loc) {
    return false;
  }
  
  /**
   * Door constructor.
   * 
   * @param colour - colour of door
   */
  public Door(String colour) {
    this.colour = colour;
  }
  
  /**
   * Sets the colour of the door. 
   * 
   * @param c - colour to change door too
   */
  public void setLockedDoorColour(String c) {
    this.colour = c;
  }
  
  /**
   * gets the colour of the door.
   * 
   * @return - door colour 
   */
  public String getLockedDoorColour() {
    return colour;
  }
  
  /**
   * checks whether door is locked. 
   * 
   * @return - true or false whether door is locked or unlocked
   */
  public boolean isLocked() {
    return locked;
  }
  
  /**
   * sets door to locked or unlocked. 
   * 
   * @param locked - unlocks or locks door
   */
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
  
  /**
   * descriptive toString displays the colour of the door.
   * 
   * @return - door and its colour
   */
  public String descriptiveToString() {
    return "Door: 'l' - Colour: " + getLockedDoorColour();
  }
  
  @Override
  public String toString() {
    return "l";
  }
}

package nz.ac.vuw.ecs.swen225.gp21.domain;

/**
 * InfoField class displays helpful hints to player when chap walks over the tile. 
 * 
 * @author Jayen
 *
 */
public class InfoField implements Tile{

  private boolean pickupable = false;
  private boolean interactable = true;
  private String info_text;
  private Location location;
  
  /**
   * Info feild constructor. 
   * 
   * @param info_text - text to display to player
   */
  public InfoField(String info_text) {
    this.info_text = info_text;
  }
  
  /**
   * Displays info text to player. 
   * 
   * @return - info text
   */
  public String displayText() {
    return info_text;
  }
  
  /**
   * Sets info text.
   * 
   * @param text - text displayed to player
   */
  public void setDisplayText(String text) {
    this.info_text = text;
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
    return "i";
  }
  
}

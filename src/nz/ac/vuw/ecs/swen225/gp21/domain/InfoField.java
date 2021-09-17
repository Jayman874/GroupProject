package nz.ac.vuw.ecs.swen225.gp21.domain;

/**
 * InfoField class displays helpful hints to player when chap walks over the tile 
 * 
 * @author Jayen
 *
 */
public class InfoField implements Tile{

  private boolean pickupable = false;
  private boolean interactable = true;
  private String info_text;
  private Location location;
  
  public InfoField(String info_text){
    this.info_text = info_text;
  }
  
  public String displayText() {
    return info_text;
  }
  
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
    // TODO Auto-generated method stub
    return false;
  }
  
  @Override
  public String toString() {
    return "i";
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

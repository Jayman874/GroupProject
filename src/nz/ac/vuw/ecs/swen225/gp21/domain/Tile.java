package nz.ac.vuw.ecs.swen225.gp21.domain;

/**
 * Tile class make up all possible object which can be placed on a board.
 * 
 * 
 * @author Jayen
 *
 */
public interface Tile {
  
  public boolean isPickupable();
   
  public boolean isInteractable();
  
  public boolean isValid(Location loc);
  
  public void setLocation(Location loc);
  
  public Location getLocation();
  
  @Override
  public String toString();

  public void setTile(Tile tile);
  
}

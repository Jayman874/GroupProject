package nz.ac.vuw.ecs.swen225.gp21.domain;

/**
 * Tile class make up all possible object which can be placed on a board.
 * 
 * 
 * @author Jayen
 *
 */
public interface Tile {
  
  /**
   * Determines whether an object can be picked up.
   * 
   * @return - true or false whether item is pickupable 
   */
  public boolean isPickupable();
  
  /**
   * Determines whether an item can be interacted with.
   * 
   * @return - true or false whether item can be interacted with
   */
  public boolean isInteractable();
  
  /**
   * Determines whether move made by a tile is a valid move.
   * 
   * @param loc - location tile is being moved too
   * @return
   */
  public boolean isValid(Location loc);
  
  /**
   * Sets the location of the tile.
   * 
   * @param loc - location of the tile
   */
  public void setLocation(Location loc);
  
  /**
   * Gets the location of the tile.
   * 
   * @return - tiles location 
   */
  public Location getLocation();
  
  @Override
  public String toString();
  
}

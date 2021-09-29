package nz.ac.vuw.ecs.swen225.gp21.domain;

/**
 * ExitTile class controls logic for the exit tile. When player walks over
 * ExitTile it will take them to the next level.
 * 
 * @author Jayen
 *
 */
public class ExitTile implements Tile {
  
  private Location location;

  /**
   * Exit tile constructor.
   * 
   */
  public ExitTile() {

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
    return "e";
  }
}

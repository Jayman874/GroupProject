package nz.ac.vuw.ecs.swen225.gp21.domain;

/**
 * Wall tile represents boundaries which player cannot move through.
 * 
 * @author Jayen
 *
 */
public class WallTile implements Tile {

  private Location location;

  /**
   * Walltile Constructor.
   * 
   */
  public WallTile() {

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
    return "w";
  }
}

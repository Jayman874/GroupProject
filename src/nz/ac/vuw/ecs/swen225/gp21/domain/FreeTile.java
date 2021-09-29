package nz.ac.vuw.ecs.swen225.gp21.domain;

/**
 * FreeTile are blank tiles on the game board. Chap is allowed to walk on
 * FreeTiles and turn them into Chap Tiles.
 * 
 * @author Jayen
 *
 */
public class FreeTile implements Tile {

  private Location location;

  /**
   * Free tile constructor.
   * 
   */
  public FreeTile() {

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
    return "f";
  }
}

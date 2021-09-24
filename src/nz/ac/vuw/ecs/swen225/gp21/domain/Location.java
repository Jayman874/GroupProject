package nz.ac.vuw.ecs.swen225.gp21.domain;

/**
 * Location class represents each tiles position on a board.
 * 
 * @author Jayen
 *
 */
public class Location {

  private int xpoint;
  private int ypoint;

  /**
   * Location constructor.
   * 
   * @param x - x co-ordinate of tile
   * @param y - y co-ordinate of tile
   */
  public Location(int x, int y) {
    this.xpoint = x;
    this.ypoint = y;
  }

  /**
   * gets x value of location.
   * 
   * @return - x value of location
   */
  public int getX() {
    return xpoint;
  }

  /**
   * Sets x value of location.
   * 
   * @param x - x value to set
   */
  public void setX(int x) {
    this.xpoint = x;
  }

  /**
   * Gets y value of location.
   * 
   * @return - y value of location
   */
  public int getY() {
    return ypoint;
  }

  /**
   * Sets y value of location.
   * 
   * @param y - y value to set
   */
  public void setY(int y) {
    this.ypoint = y;
  }

  @Override
  public String toString() {
    return "x:" + getX() + " y:" + getY();
  }

}

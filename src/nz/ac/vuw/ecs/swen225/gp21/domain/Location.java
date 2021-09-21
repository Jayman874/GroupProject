package nz.ac.vuw.ecs.swen225.gp21.domain;

/**
 * Location class represents each tiles position on a board
 * 
 * @author Jayen
 *
 */
public class Location {
  
  private int x;
  private int y;
  
  /**
   * Location constructor
   * 
   * @param x - x co-ordinate of tile
   * @param y - y co-ordinate of tile
   */
  public Location(int x, int y) {
    this.x = x;
    this.y = y;
  }
  
  public int getX() {
    return x;
  }
  
  public void setX(int x){
    this.x = x;
  }
  
  public int getY() {
    return y;
  }
  
  public void setY(int y) {
    this.y = y;
  }
  
  public String toString() {
    return "x:" + getX() + " y:" + getY();
  }
 
}

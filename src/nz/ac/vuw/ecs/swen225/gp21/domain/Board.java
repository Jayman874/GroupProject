package nz.ac.vuw.ecs.swen225.gp21.domain;

import java.awt.Point;
import java.util.Map;

import nz.ac.vuw.ecs.swen225.gp21.app.App;
import nz.ac.vuw.ecs.swen225.gp21.persistency.LoadLevel;

public class Board {
  private static Tile[][] board;
  
  public Board() {
    board = makeBoard();
    LoadLevel.printTiles(board);
  }
  
  public static Tile[][] makeBoard() {
    LoadLevel main = new LoadLevel();
    Map<Point, Character> points = main.makeMap("test4.xml"); //creating map of points to characters
    Tile[][] tiles = main.makeTiles(points); //creating board from map
    return tiles;
  }
  
  public static void updateBoard(Chap chap, Location loc) {
    Location chapLocation = chap.getLocation();
    int oldX = chapLocation.getX();
    int oldY = chapLocation.getY();
    int x = loc.getX();
    int y = loc.getY();
    Tile tile = board[x][y];
    System.out.println(tile.getClass());
    Tile oldTile = board[oldX][oldY];
    chap.setLocation(tile.getLocation());
  }
  
  public static Tile[][] getBoard(){
    return board;
  }
  
  public static void main(String[] args) {
    new Board();
    Key key = new Key("none");
    Chap chap = new Chap();
    chap.getKeyInventory().add(key);
    Location infoloc = new Location(0, 1);
    //Location eloc = new Location(0, 2);
    Location loc = new Location(0, 0);
    chap.setLocation(loc);
    updateBoard(chap, infoloc);
    if (chap.isValid(infoloc)) {
      System.out.println(true);
    }
    //if (chap.isValid(eloc)) {
      //System.out.println("Opened door");
    //}
  }
  
}

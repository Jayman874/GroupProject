package nz.ac.vuw.ecs.swen225.gp21.domain;

import java.awt.Point;
import java.util.Map;

import nz.ac.vuw.ecs.swen225.gp21.app.App;
import nz.ac.vuw.ecs.swen225.gp21.persistency.LoadLevel;
import nz.ac.vuw.ecs.swen225.gp21.persistency.WriteLevel;

public class Board {
  private static Tile[][] board;
  
  public Board() {
    board = makeBoard();
    LoadLevel.printTiles(board);
  }
  
  public static Tile[][] makeBoard() {
    LoadLevel main = new LoadLevel();
    Map<Point, String> points = main.makeMap("test4.xml"); //creating map of points to characters
    Tile[][] tiles = main.makeTiles(points); //creating board from map
    return tiles;
  }
  
  public static void updateBoard(Chap chap, Location loc) {
    FreeTile freetile = new FreeTile();
    Location chapLocation = chap.getLocation();
    int oldX = chapLocation.getX();
    int oldY = chapLocation.getY();
    int x = loc.getX();
    int y = loc.getY();
    chap.setLocation(loc);
    board[x][y] = chap;
    board[oldX][oldY] = freetile;
  }
  
  public static Tile[][] getBoard(){
    return board;
  }
  
  public static void main(String[] args) {
    new Board();
    //Key key = new Key("none");
    Chap chap = new Chap();
    //chap.getKeyInventory().add(key);
    Location freeloc = new Location(1, 0);
    Location loc = new Location(0, 0);
    Location doorloc = new Location(2, 0);
    chap.setLocation(loc);
    if (chap.isValid(freeloc)) {
      updateBoard(chap, freeloc);
      System.out.println("-----------------------");
      LoadLevel.printTiles(board);
      System.out.println(chap.getKeyInventory().toString());
    }
    if (chap.isValid(doorloc)) {
      updateBoard(chap, doorloc);
      System.out.println("-----------------------");
      LoadLevel.printTiles(board);
    }
  }
  
}

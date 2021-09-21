package nz.ac.vuw.ecs.swen225.gp21.domain;

import java.awt.Point;
import java.util.Map;

import nz.ac.vuw.ecs.swen225.gp21.persistency.LoadLevel;

public class Board {
  private static Tile[][] board;
  private static boolean info_tile = false;
  
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
    Tile tile = new FreeTile();
    if (info_tile) {
      tile = new InfoField("none");
      info_tile = false;
    }
    Location chapLocation = chap.getLocation();
    int oldX = chapLocation.getX();
    int oldY = chapLocation.getY();
    int x = loc.getX();
    int y = loc.getY();
    chap.setLocation(loc);
    if (board[y][x] instanceof InfoField) {
      info_tile = true;
    }
    board[y][x] = chap;
    board[oldY][oldX] = tile;
  }
  
  public static Tile[][] getBoard(){
    return board;
  }
  
}

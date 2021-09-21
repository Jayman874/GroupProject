package nz.ac.vuw.ecs.swen225.gp21.domain;

import java.awt.Point;
import java.util.Map;

import nz.ac.vuw.ecs.swen225.gp21.persistency.LoadLevel;

public class Board {
  private static Tile[][] board;
  private static boolean info_tile = false;
  private static int totalLevelTreasure = 0;
  
  public Board() {
    board = makeBoard();
    LoadLevel.printTiles(board);
    setTotalLevelTreasure();
  }
  
  public static Tile[][] makeBoard() {
    LoadLevel main = new LoadLevel();
    Map<Point, String> points = main.makeMap("test4.xml"); //creating map of points to characters
    Tile[][] tiles = main.makeTiles(points); //creating board from map
    return tiles;
  }
  
  public void setTotalLevelTreasure() {
    for(int i = 0; i < board.length; i++) {
      for(int j = 0; j < board.length; j++) {
        Tile[][] tiles = board;
        Tile board = tiles[i][j];
        if(board instanceof Treasure) {
          totalLevelTreasure++;
        }
      }
    }
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
  
  public static boolean getInfoTile() {
    return info_tile;
  }
  
  public static Tile[][] getBoard(){
    return board;
  }
  
  public static int getTotalLevelTreasure() {
    return totalLevelTreasure;
  }
  
}

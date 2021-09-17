package nz.ac.vuw.ecs.swen225.gp21.domain;

import java.awt.Point;
import java.util.Map;

import nz.ac.vuw.ecs.swen225.gp21.persistency.LoadLevel;

public class Board {
  private static Tile[][] board;
  
  public Board() {
    
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
    Tile tile = board[y][x];
    Tile oldTile = board[oldY][oldX];
    chap.setLocation(tile.getLocation());
    for(int i = 0; i < board.length; i++){
      for(int j = 0; j < board.length; j++){
        System.out.print(board[i][j] + " ");
      }
    System.out.println();
    }
  }
  
  public static Tile[][] getBoard(){
    return board;
  }
  
  public static void main(String[] args) {
    board = makeBoard();
    LoadLevel.printTiles(board);
    Chap chap = new Chap();
    System.out.println(chap.getLocation().toString());
    Location loc = new Location(0, 1);
    if (chap.isValid(loc)) {
      updateBoard(chap, loc);
    }
    System.out.println(chap.getLocation().toString());
  }
  
}

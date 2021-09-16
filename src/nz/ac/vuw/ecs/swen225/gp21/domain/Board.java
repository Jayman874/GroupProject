package nz.ac.vuw.ecs.swen225.gp21.domain;

import java.awt.Point;
import java.util.Map;

import nz.ac.vuw.ecs.swen225.gp21.persistency.LoadLevel;

public class Board {
  static Tile[][] board;
  
  public Board() {
    makeBoard();
  }
  
  public static Tile[][] makeBoard() {
    LoadLevel main = new LoadLevel();
    Map<Point, Character> points = main.makeMap("test2.xml"); //creating map of points to characters
    Tile[][] tiles = main.makeTiles(points); //creating board from map
    return tiles;
  }
  
  public void updateBoard() {
    
  }
  
  public static void main(String[] args) {
    new Board();
    board = makeBoard();
    LoadLevel.printTiles(board);
  }
  
}

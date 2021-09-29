package nz.ac.vuw.ecs.swen225.gp21.domain;

import java.awt.Point;
import java.util.Map;
import nz.ac.vuw.ecs.swen225.gp21.persistency.LoadLevel;
import nz.ac.vuw.ecs.swen225.gp21.persistency.levels.level2.Actor;

/**
 * Board class reads the xml file and stores the values as a 2d array of tiles.
 * 
 * @author Jayen
 *
 */
public class Board {
  private static Tile[][] board;
  private static boolean info_tile = false;
  private static int totalLevelTreasure = 0;

  /**
   * Board Constructor.
   * 
   */
  public Board() {
    board = makeBoard();
    setTotalLevelTreasure();
  }
  
  

  /**
   * Makes the map using the xml file and assign each value to a tile.
   * 
   * @return - the tiles of the board
   */
  public static Tile[][] makeBoard() {
    LoadLevel main = new LoadLevel();
    Map<Point, String> points = main.makeMap("test4.xml"); // creating map of points to characters
    Tile[][] tiles = main.makeTiles(points); // creating board from map
    return tiles;
  }

  /**
   * Detects the amount of treasure on the board.
   * 
   */
  public void setTotalLevelTreasure() {
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board.length; j++) {
        Tile[][] tiles = board;
        Tile board = tiles[i][j];
        // if the tile is a treasure tile increment the total amount of treasure 
        if (board instanceof Treasure) {
          totalLevelTreasure++;
        }
      }
    }
  }

  /**
   * updates the location of chap and all of the other tiles on the board.
   * 
   * @param chap - the player character
   * @param loc  - location the player is trying to move
   */
  public static void updateBoard(Chap chap, Location loc) {
    Tile tile = new FreeTile();
    if (info_tile) { // checks if chap is on a info tile
      tile = new InfoField("none");
      info_tile = false;
    }
    Location chapLocation = chap.getLocation();
    int x = loc.getX();
    int y = loc.getY();
    chap.setLocation(loc); // sets chaps new location
    tile.setLocation(chapLocation);
    int oldX = chapLocation.getX();
    int oldY = chapLocation.getY();
    if (board[y][x] instanceof InfoField) {
      info_tile = true;
    }
    board[y][x] = chap; // updates chaps place on the board
    board[oldY][oldX] = tile; // sets old tile to freetile or infotile
  }
  
  public static void updateActorBoard(Actor actor, Location loc) {
    Tile tile = new FreeTile();
    if (info_tile) { // checks if chap is on a info tile
      tile = new InfoField("none");
      info_tile = false;
    }
    Location chapLocation = actor.getLocation();
    int x = loc.getX();
    int y = loc.getY();
    actor.setLocation(loc); // sets chaps new location
    tile.setLocation(chapLocation);
    int oldX = chapLocation.getX();
    int oldY = chapLocation.getY();
    if (board[y][x] instanceof InfoField) {
      info_tile = true;
    }
    board[y][x] = actor; // updates chaps place on the board
    board[oldY][oldX] = tile; // sets old tile to freetile or infotile
  }

  /**
   * returns whether chap is on an info tile.
   * 
   * @return - true or false whether chap was on info tile
   */
  public static boolean getInfoTile() {
    return info_tile;
  }

  /**
   * returns the current game board.
   * 
   * @return - game board
   * 
   */
  public static Tile[][] getBoard() {
    return board;
  }
  
  public void setBoard(Tile[][] tile) {
    board = tile;
  }

  /**
   * returns the amount of treasure on the level.
   * 
   * @return - total amount of treasure on level
   */
  public static int getTotalLevelTreasure() {
    return totalLevelTreasure;
  }

}

package nz.ac.vuw.ecs.swen225.gp21.domain;

import nz.ac.vuw.ecs.swen225.gp21.app.GUI;
import nz.ac.vuw.ecs.swen225.gp21.persistency.LoadLevel;
import nz.ac.vuw.ecs.swen225.gp21.persistency.levels.level2.Actor;

import java.awt.*;
import java.util.ArrayList;
import java.util.Map;

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
  private static ArrayList<Actor> actorList = new ArrayList<Actor>();

  /**
   * Board Constructor.
   * 
   */
  public Board() {
    totalLevelTreasure = 0;
    actorList.clear();
    if (Chap.level1) {
      board = makeBoard();
      setTotalLevelTreasure();
      getActors();
    } else if (Chap.level2) {
      board = makeBoard2();
      setTotalLevelTreasure();
      getActors();
    }
  }
  
  

  /**
   * Makes the map using the xml file and assign each value to a tile.
   * 
   * @return - the tiles of the board
   */
  public static Tile[][] makeBoard() {
    LoadLevel main = new LoadLevel();
    Tile[][] tiles = null;
    Map<Point, String> points = main.makeMap("level1.xml"); // creating map of points to characters
    tiles = main.makeTiles(points); // creating board from map
    return tiles;
  }
  
  /**
   * Makes the map using the xml file and assign each value to a tile.
   * 
   * @return - the tiles of the board
   */
  public static Tile[][] makeBoard2() {
    LoadLevel main = new LoadLevel();
    Tile[][] tiles = null;
    Map<Point, String> points = main.makeMap("level1.xml"); // creating map of points to characters
    tiles = main.makeTiles(points); // creating board from map
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
  
  public void getActors() {
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board.length; j++) {
        Tile[][] tiles = board;
        Tile board = tiles[i][j];
        // if the tile is a treasure tile increment the total amount of treasure 
        if (board instanceof Actor) {
          actorList.add((Actor) board);
        }
      }
    }
  }
  
  public ArrayList<Actor> getActorList() {
    return actorList;
  }
  
  public static void setTotalLevelTreasureDirect(int total) {
     Board.totalLevelTreasure = total;
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
      GUI.disappearInfo();
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
  
  /**
   * Update the enemy actor on the board.
   * 
   * @param tile - the state of the actor
   */
  public static void updateActorBoard(Actor actor, Location loc) {
    Tile tile = new FreeTile();
    Location actorLocation = actor.getLocation();
    int x = loc.getX();
    int y = loc.getY();
    actor.setLocation(loc); // sets chaps new location
    tile.setLocation(actorLocation);
    int oldX = actorLocation.getX();
    int oldY = actorLocation.getY();
    board[y][x] = actor; // updates chaps place on the board
    board[oldY][oldX] = tile; // sets old tile to freetile or infotile
  }
  
  public static void clearBoard(Tile[][] board) {
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board.length; j++) {
        board[i][j] = null;
      }
    }
    board = null;
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
  
  /**
   * Sets the game board.
   * 
   * @param tile - new game board
   */
  public void setBoard(Tile[][] tile) {
    for (int i = 0; i < tile.length; i++) {
      for (int j = 0; j < tile.length; j++) {
        board[i][j] = tile[i][j];
      }
    }
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

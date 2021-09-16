package nz.ac.vuw.ecs.swen225.gp21.recorder;

import nz.ac.vuw.ecs.swen225.gp21.domain.*;

/**
 * Move class containing a particular cell and the tile contained within before and after a move is made
 */
public class Move {

    private int x;
    private int y;
    private Tile beforeMoveTile;
    private Tile afterMoveTile;

    public Move(int x, int y, Tile currentTile, Tile afterMoveTile) {
        this.x = x;
        this.y = y;
        this.beforeMoveTile = currentTile;
        this.afterMoveTile = afterMoveTile;
    }

    /**
     * Returns the x value.
     * @return  x integer
     */
    public int getX() {
        return x;
    }

    /**
     * Returns the y value.
     * @return  y integer
     */
    public int getY() {
        return y;
    }

    /**
     * Returns the Tile located within the cell before the move.
     * @return  before move Tile
     */
    public Tile getAfterMoveTile() {
        return afterMoveTile;
    }

    /**
     * Returns the Tile located within the cell before the move.
     * @return  after move Tile
     */
    public Tile getBeforeMoveTile() {
        return beforeMoveTile;
    }
}

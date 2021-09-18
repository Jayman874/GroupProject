package nz.ac.vuw.ecs.swen225.gp21.recorder;

import nz.ac.vuw.ecs.swen225.gp21.domain.*;

/**
 * Move class containing a particular cell and the tile contained within before and after a move is made
 */
public class Move {

    private int preMoveX;
    private int preMoveY;
    private int postMoveX;
    private int postMoveY;
    private String direction;

    public Move(int x1, int y1, int x2, int y2, String direction) {
        this.preMoveX = x1;
        this.preMoveY = y1;
        this.postMoveX = x2;
        this.postMoveY = y2;
        this.direction = direction;
    }

    /**
     * Returns the pre move x value.
     * @return  x integer
     */
    public int getPreMoveX() {
        return preMoveX;
    }

    /**
     * Returns the pre move y value.
     * @return  y integer
     */
    public int getPreMoveY() {
        return preMoveY;
    }

    /**
     * Returns the post move x value
     * @return x integer
     */
    public int getPostMoveX() { return postMoveX; }

    /**
     * Returns the post move y value
     * @return y integer
     */
    public int getPostMoveY() { return postMoveY; }

    /**
     * Returns the direction of the move
     * @return Direction of move
     */
    public String getDirection() { return direction; }
}

package nz.ac.vuw.ecs.swen225.gp21.persistency.levels.level2;

import nz.ac.vuw.ecs.swen225.gp21.domain.*;

import java.security.SecureRandom;
import java.util.List;
import java.util.Random;

public class Actor implements Tile {
    private Location location;

    /**
     * Update all actors in the levels position. To be called each time the board updates (or chap moves)
     * @param actors List of all actors in a level to be updated
     */
    public static void updateActors(List<Actor> actors){
        for(int i = 0; i < actors.size(); i++){
            Actor a = actors.get(i);
            Location newLoc = a.getNewLocation();
            if(a.isValid(newLoc)){
                Board.updateActorBoard(a, newLoc);
            }
        }
    }

    /**
     * Gets a random location for the actor to move to
     * @return new Location for actor to move to given random input (within method)
     */
    public Location getNewLocation(){
        int random = getRandom();
        int x = location.getX();
        int y = location.getY();

        if(random == 1 && (x + 1 < 19)){ x++; }
        else if(random == 2 && (y - 1 > 0)){ y--; }
        else if(random == 3 && (x - 1 > 0)){ x--; }
        else if(random == 4 && (y + 1 < 19)){ y++; }
        Location newLoc = new Location(x, y);
        return newLoc;

    }

    /**
     * Gets a random number representing which direction the actor will move
     * @return random number representing a directino for the actor to move
     */
    public int getRandom(){
        SecureRandom rand = new SecureRandom();
        int max = 4;
        int min = 1;
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }

    /**
     * Checks that the actors move in given board is legal (both within bounds and also a movement of only 1 tile)
     * @param board The board to check on
     * @param x x location of proposed move
     * @param y y location of proposed move
     * @return true if valid move, false if invalid
     */
    public boolean actorMoveCheck(Tile[][] board, int x, int y){
        int actorX = this.getLocation().getX();
        int actorY = this.getLocation().getY();
        // gets the absolute difference of move
        int xlocDifference = Math.abs(x - actorX);
        int ylocDifference = Math.abs(y - actorY);
        // checks if chap tries to move out of game board
        if (x < 0 || y < 0 || x > board.length - 1 || y > board.length - 1) {
            return false;
        }
        // checks if chap tries to move more than one space at a time
        if (xlocDifference > 1 || ylocDifference > 1) {
            return false;
        }
        return true;
    }

    @Override
    public boolean isValid(Location loc) {
        int x = loc.getX();
        int y = loc.getY();
        Tile[][] board = Board.getBoard();
        Tile tile = board[y][x];
        if(actorMoveCheck(board, x, y)){
            if (tile instanceof FreeTile) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void setLocation(Location loc) {
        this.location = loc;
    }

    @Override
    public Location getLocation() {
        return location;
    }

    @Override
    public String toString(){
        return "a";
    }
}

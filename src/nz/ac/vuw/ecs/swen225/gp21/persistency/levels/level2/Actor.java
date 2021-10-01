package nz.ac.vuw.ecs.swen225.gp21.persistency.levels.level2;

import nz.ac.vuw.ecs.swen225.gp21.domain.*;

import java.util.List;
import java.util.Random;

public class Actor implements Tile {
    private Location location;

    public Actor(){

    }

    public static void updateActors(List<Actor> actors){
        for(int i = 0; i < actors.size(); i++){
            Actor a = actors.get(i);
            Location newLoc = a.getNewLocation();
            if(a.isValid(newLoc)){
                Board.updateActorBoard(a, newLoc);
            }
        }
    }

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
     *
     * @return random number representing a directino for the actor to move
     */
    public int getRandom(){
        Random rand = new Random();
        int max = 4;
        int min = 1;
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
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
    public void setLocation(Location loc) {
        this.location = loc;
    }

    @Override
    public Location getLocation() {
        return location;
    }

    public String toString(){
        return "a";
    }
}

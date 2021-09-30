package nz.ac.vuw.ecs.swen225.gp21.persistency.levels.level2;

import nz.ac.vuw.ecs.swen225.gp21.domain.Location;
import nz.ac.vuw.ecs.swen225.gp21.domain.Tile;

import java.util.List;
import java.util.Random;

public class Actor implements Tile {
    private Location location;

    public Actor(){

    }

    public void updateActors(List<Actor> actors){
        for(int i = 0; i < actors.size(); i++){
            Actor a = actors.get(i);
            Location newLoc = a.getNewLocation();
            //Board.updateActorBoard(a, newLoc);
        }

    }

    public Location getNewLocation(){
        int random = getRandom();
        int x = location.getX();
        int y = location.getY();

        if(random == 1){
            x++;
            y++;
        }else if(random == 2){
            x++;
            y--;
        }else if(random == 3){
            x--;
            y++;
        }else if(random == 4){
            x--;
            y--;
        }
        return new Location(x, y);
    }

    public int getRandom(){
        Random rand = new Random();
        int max = 4;
        int min = 1;
        int randomNum = rand.nextInt((max - min) + 1) + min;
        System.out.println(randomNum);
        return randomNum;
    }

    @Override
    public boolean isValid(Location loc) {
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

    public String toString(){
        return "a";
    }
}

package nz.ac.vuw.ecs.swen225.gp21.persistency.levels.level2;

import nz.ac.vuw.ecs.swen225.gp21.domain.Board;
import nz.ac.vuw.ecs.swen225.gp21.domain.FreeTile;
import nz.ac.vuw.ecs.swen225.gp21.domain.Location;
import nz.ac.vuw.ecs.swen225.gp21.domain.Tile;

public class Actor implements Tile {
    Tile tile;
    private boolean isDangerous = true;
    private Location location;
    public int timer = 60;

    public void actorLoop(){
        if(timer % 2 == 0){
            if(isDangerous){
                tile = new Actor();
            }else{
                tile = new FreeTile();
            }
            Board.updateActorBoard(tile);
            this.isDangerous = !isDangerous;
        }
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

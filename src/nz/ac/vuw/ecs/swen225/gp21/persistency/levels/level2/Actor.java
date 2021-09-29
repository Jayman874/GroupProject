package nz.ac.vuw.ecs.swen225.gp21.persistency.levels.level2;

import nz.ac.vuw.ecs.swen225.gp21.domain.Location;
import nz.ac.vuw.ecs.swen225.gp21.domain.Tile;

public class Actor implements Tile {
    private boolean isDangerous = true;
    private Location location;
    public int timer = 60;

    public void renderActor(){
        if(isDangerous){

        }
    }

    public void switchDanger(){
        if(timer % 2 == 0){
            this.isDangerous = !isDangerous;
        }
    }


    @Override
    public boolean isPickupable() {
        return false;
    }

    @Override
    public boolean isInteractable() {
        return false;
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

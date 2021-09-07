package nz.ac.vuw.ecs.swen225.gp21.domain;

public class Key implements Tile{

  private boolean pickupable = true;
  private boolean interactable = true;
  private String colour; 
  
  public Key(String colour) {
    this.colour = colour;
  }
  
  @Override
  public boolean isPickupable() {
    return pickupable;
  }

  @Override
  public boolean isInteractable() {
    return interactable;
  }

  @Override
  public boolean isValid() {
    // TODO Auto-generated method stub
    return false;
  }
  
  public String getKeyColour() {
    return colour;
  }
  
  public void setKeyColour(String c) {
    this.colour = c;
  }

}

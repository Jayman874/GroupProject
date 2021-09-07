package nz.ac.vuw.ecs.swen225.gp21.domain;

public class ExitTile implements Tile{
  
  private boolean pickupable = false;
  private boolean interactable = false;
  
  public ExitTile() {
    
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

}

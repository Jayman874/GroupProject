package nz.ac.vuw.ecs.swen225.gp21.domain;

public class InfoField implements Tile{

  private boolean pickupable = false;
  private boolean interactable = true;
  private String info_text;
  
  public InfoField(String info_text){
    this.info_text = info_text;
  }
  
  public String displayText() {
    return info_text;
  }
  
  public void setDisplayText(String text) {
    this.info_text = text;
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

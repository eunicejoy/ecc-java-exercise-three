public class Horse{
  private String name;
  private boolean isHealthy;
  private int distanceTraveled;
  private String warCry;

  public Horse(String name, boolean isHealthy, int distanceTraveled, String warCry){
    this.name = name;
    this.isHealthy = isHealthy;
    this.distanceTraveled = distanceTraveled;
    this.warCry = warCry;
  }

  public void setName(String name){
    this.name = name;
  }

  public String getName(){
    return name;
  }

  public void setHealth(boolean isHealthy){
    this.isHealthy = isHealthy;
  }

  public Boolean getHealth(){
    return isHealthy;
  }

  public void setDistanceTraveled(int distanceTraveled){
    this.distanceTraveled = distanceTraveled;
  }

  public int getDistanceTraveled(){
    return distanceTraveled;
  }

  public void setWarCry(String warCry){
    this.warCry = warCry;
  }

  public String getWarCry(){
    return warCry;
  }
}

package main.firefighters;

import main.api.CityNode;
import main.api.Firefighter;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class FirefighterImpl implements Firefighter {

  private CityNode location;
  private int distanceTraveled;

  public FirefighterImpl(CityNode location) {
    this.location = location;
    this.distanceTraveled = 0;
  }

  @Override
  public void updateLocationAndDistanceTraveled(CityNode newLocation, int distanceTraveled) {
    this.location = newLocation;
    this.distanceTraveled += distanceTraveled;
  }

  @Override
  public CityNode getLocation() {
    return location;
  }

  @Override
  public int distanceTraveled() {
    return distanceTraveled;
  }
}

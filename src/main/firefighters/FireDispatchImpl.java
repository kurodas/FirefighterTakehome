package main.firefighters;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import main.api.*;
import main.api.exceptions.NoFireFoundException;
import main.api.exceptions.NoValidFirefighterException;

public class FireDispatchImpl implements FireDispatch {

  private final City city;
  private List<Firefighter> firefighters;

  public FireDispatchImpl(City city) {
    this.city = city;
  }

  @Override
  public void setFirefighters(int numFirefighters) {
    this.firefighters =
        IntStream.range(0, numFirefighters)
            .mapToObj((i) -> new FirefighterImpl(city.getFireStation().getLocation()))
            .collect(Collectors.toList());
  }

  @Override
  public List<Firefighter> getFirefighters() {
    return firefighters;
  }

  @Override
  public void dispatchFirefighters(CityNode... burningBuildings) {
    for (CityNode node: burningBuildings) {
        dispatchNearestFirefighter(node);
        markFireAsExtinguished(node);
    }
  }

  private void dispatchNearestFirefighter(CityNode node) {
    int minDispatchDistance = Integer.MAX_VALUE;
    Firefighter closestFirefighter = null;
    for (Firefighter firefighter: firefighters) {
      int distance = distanceBetweenNodes(firefighter.getLocation(), node);
      if (distance < minDispatchDistance) {
        closestFirefighter = firefighter;
        minDispatchDistance = distance;
      }
    }
    if (closestFirefighter == null) {
      // Should only occur if there are no firefighters
      throw new NoValidFirefighterException();
    }
    closestFirefighter.updateLocationAndDistanceTraveled(node, minDispatchDistance);
  }

  private void markFireAsExtinguished(CityNode node) {
    Building building = city.getBuilding(node);
    if (building.isBurning()) {
      try {
        building.extinguishFire();
      } catch (NoFireFoundException e) {
        // Should occur only if building.isBurning() check is faulty
        throw new RuntimeException(e);
      }
    }
  }

  private int distanceBetweenNodes(CityNode node1, CityNode node2) {
    return Math.abs(node1.getX() - node2.getX())
            + Math.abs(node1.getY() - node2.getY());
  }
}

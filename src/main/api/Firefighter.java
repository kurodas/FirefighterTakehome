package main.api;

public interface Firefighter {

  /**
   * Get the firefighter's current location. Initially, the firefighter should be at the FireStation
   *
   * @return {@link CityNode} representing the firefighter's current location
   */
  CityNode getLocation();

  /**
   * Updates the firefighter's location and increments its distanceTraveled
   *
   * @param newLocation The firefighter's new location
   * @param distanceTraveled The distance traveled by the firefighter to reach the new location
   */
  void updateLocationAndDistanceTraveled(CityNode newLocation, int distanceTraveled);

  /**
   * Get the total distance traveled by this firefighter. Distances should be represented using TaxiCab
   * Geometry: https://en.wikipedia.org/wiki/Taxicab_geometry
   *
   * @return the total distance traveled by this firefighter
   */
  int distanceTraveled();
}

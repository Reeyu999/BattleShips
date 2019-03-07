package premium.data;

import java.util.ArrayList;
import java.util.List;

public class Ship {

    private final List<Coordinates> shipCoordinates;
    private List<Coordinates> hitCoordinates = new ArrayList<>();

    public Ship(List<Coordinates> shipCoordinates) {

        if (!validateShipCoordinates(shipCoordinates)) {
            throw new IllegalArgumentException("Invalid coordinates passed to a ship: " + shipCoordinates);
        }

        this.shipCoordinates = shipCoordinates;
    }

    public boolean shoot(Coordinates coordinates) {
        if (occupiesCoordinate(coordinates) && !hitCoordinates.contains(coordinates)) {
            hitCoordinates.add(coordinates);
            return true;
        }
        return false;
    }

    public boolean isDestroyed() {
        return shipCoordinates.equals(hitCoordinates);
    }

    public boolean occupiesCoordinate(Coordinates coordinates) {
        for (int i = 0; i < shipCoordinates.size(); i++) {
            if (shipCoordinates.get(i).equals(coordinates)) {
                return true;
            }
        }

        return false;
    }

    public boolean isHitInCoordinates(Coordinates coordinates) {
        for (int i = 0; i < hitCoordinates.size(); i++) {
            if (hitCoordinates.get(i).equals(coordinates)) {
                return true;
            }
        }

        return false;
    }

    private boolean validateShipCoordinates(List<Coordinates> coordinates) {
        for (int i = 0; i < coordinates.size(); i++) {

            if (i == 0) {
                continue;
            }

            if (!coordinates.get(i - 1).isNextTo(coordinates.get(i))) {
                return false;
            }
        }
        return true;
    }
}

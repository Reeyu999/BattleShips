package premium;

import premium.data.Coordinates;
import premium.data.Ship;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {

    private final int rowsCount;
    private final int columnsCount;

    private List<Ship> shipsInGame;

    private Random random = new Random();

    public Game(int rowsCount, int columnsCount) {
        this.rowsCount = rowsCount;
        this.columnsCount = columnsCount;
    }

    public void startGame(int shipsCount) {

        float averageShipSize = (float)shipsCount / ((float)shipsCount / 2);

        if(shipsCount * averageShipSize > rowsCount * columnsCount) {
            throw new IllegalArgumentException("Game is too small for that many ships!");
        }

        shipsInGame = new ArrayList<>();
        for (int i = 0; i < shipsCount; i++) {
            List<Coordinates> coordinates = generateShipCoordinates(i + 1, random.nextBoolean());
            shipsInGame.add(new Ship(coordinates));
        }
    }

    public boolean shoot(Coordinates coordinates) {
        for (int i = 0; i < shipsInGame.size(); i++) {

            Ship ship = shipsInGame.get(i);
            boolean hit = ship.shoot(coordinates);
            if (hit) {
                return true;
            }

        }
        return false;
    }

    public boolean isGameWon() {
        for (int i = 0; i < shipsInGame.size(); i++) {
            if(!shipsInGame.get(i).isDestroyed()) {
                return false;
            }
        }
        return true;
    }

    public void printCurrentMap() {

        StringBuilder stringBuilder = new StringBuilder();

        for(int i = 0; i < rowsCount; i++) {

            for(int j = 0; j < columnsCount; j++) {

                Coordinates coordinates = new Coordinates(i, j);
                if (areCoordinatesAlreadyHit(coordinates)) {
                    stringBuilder.append("X");
                } else if(areCoordinatesAlreadyOccupied(coordinates)) {
                    stringBuilder.append("O");
                } else {
                    stringBuilder.append("-");
                }

            }

            stringBuilder.append("\n");

        }

        System.out.println(stringBuilder.toString());
    }

    private List<Coordinates> generateShipCoordinates(int size, boolean horizontal) {

        boolean generatedCoordinatesOccupied = false;
        List<Coordinates> result = new ArrayList<>();

        do {

            result.clear();
            int constantAxisValue;
            int variantAxisStartingValue;

            if (horizontal) {
                constantAxisValue = random.nextInt(columnsCount);
                variantAxisStartingValue = random.nextInt(rowsCount - size);
            } else {
                constantAxisValue = random.nextInt(rowsCount);
                variantAxisStartingValue = random.nextInt(columnsCount - size);
            }


            for (int i = 0; i < size; i++) {
                Coordinates coordinates;

                if(horizontal) {
                    coordinates = new Coordinates(constantAxisValue, variantAxisStartingValue + i);
                } else {
                    coordinates = new Coordinates(variantAxisStartingValue + i, constantAxisValue);
                }

                result.add(coordinates);
                if (areCoordinatesAlreadyOccupied(coordinates)) {
                    generatedCoordinatesOccupied = true;
                }
            }

        }while (generatedCoordinatesOccupied);

        return result;

    }

    private boolean areCoordinatesAlreadyOccupied(Coordinates coordinates) {
        for (int i = 0; i < shipsInGame.size(); i++) {
            if (shipsInGame.get(i).occupiesCoordinate(coordinates)) {
                return true;
            }
        }
        return false;
    }

    private boolean areCoordinatesAlreadyHit(Coordinates coordinates) {
        for (int i = 0; i < shipsInGame.size(); i++) {
            if (shipsInGame.get(i).isHitInCoordinates(coordinates)) {
                return true;
            }
        }
        return false;
    }
}

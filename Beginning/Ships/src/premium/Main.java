package premium;

import premium.data.Coordinates;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Game game = new Game(4, 4);
        Scanner scanner = new Scanner(System.in);

        game.startGame(2);
        game.printCurrentMap();

        while (!game.isGameWon()) {
            System.out.println("Shoot!");
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            Coordinates coordinates = new Coordinates(x, y);

            boolean result = game.shoot(coordinates);
            if (result) {
                System.out.println("Hit!");
            } else {
                System.out.println("Miss!");
            }

            game.printCurrentMap();
        }

        System.out.println("You won!");

    }

}

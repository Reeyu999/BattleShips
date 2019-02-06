import java.util.Scanner;
import java.util.Random;

public class Battleground {

    static int[][] playground;
    static int row;
    static int col;
    static int yourGuess;
    static int guessRow;
    static int guessCol;
    static int compRow;
    static int compCol;

    Scanner write = new Scanner(System.in);


    public int[][] setMap(int x, int y) {                        // metoda tworząca mapę

        int[][] battleground = new int[x][y];
        for (int i = 0; i < battleground.length; i++) {
            for (int j = 0; j < battleground[i].length; j++) {
                if (j == y - 1) {
                    System.out.println(battleground[i][j]);
                } else {
                    System.out.print(battleground[i][j]);
                }
            }
        }
        playground = battleground;
        return playground;
    }

    public void setShip(int x, int y) {                          //metoda ustawiająca statek

        playground[x - 1][y - 1] = 1;

    }

    public int guess(int x, int y) {                              //metoda informująca o strzale
        if (playground[x - 1][y - 1] == 1) {
            yourGuess = 1;
            System.out.println("Trafiony");
        } else {
            yourGuess = 0;
            System.out.println("Pudło");
        }
        return yourGuess;
    }

    /*
    public void checkMap (int[][] tab){                         //odświeża mape

        for (int i=0; i<tab.length;i++){
            for (int j=0; j<tab[i].length;j++) {
                if (j == col - 1){
                    System.out.println(tab[i][j]);
                }
                else {
                    System.out.print(tab[i][j]);
                }

            }
        }
    }
    */
    public void shot() {
        System.out.println("który wiersz?");
        guessRow = write.nextInt();                           //zgadywanie wiersza położenia statku
        System.out.println("która kolumna");
        guessCol = write.nextInt();                           //zgadywanie kolumny położenia statku
    }

    public void shotComp() {
        Random compTurn = new Random();
        compRow = compTurn.nextInt(row) + 1;
        compCol = compTurn.nextInt(col) + 1;


    }


    public static void main(String[] args) {
        Battleground player = new Battleground();              // tworzenie obiektu gracza
        Battleground comp = new Battleground();                // tworzenie obiektu komputera

        Scanner write = new Scanner(System.in);                // tworzenie obiektu opcji wejścia


        System.out.println("Podaj liczbę wierszy");
        row = write.nextInt();                                 //podajesz liczbę wierszy
        System.out.println("Podaj liczbę kolumn");
        col = write.nextInt();                                 //podajesz liczbę kolumn


        player.setMap(row, col);                                //tworzenie mapy

        /*System.out.println("Podaj wiersz gdzie chcesz ustawić statek");
        int cord_x = write.nextInt();                                   //podajesz który wiersz
        System.out.println("Podaj kolumne gdzie chcesz ustawić statek");
        int cord_y = write.nextInt();                                   //podajesz która kolumna

        player.setShip(cord_x, cord_y);                        //wywoływanie metody ustawiającej statek
        */

        comp.shotComp();
        comp.setShip(compRow,compCol);
        //System.out.println(compRow + " " + compCol);         //wyświetlenie strzału komputera
        //player.checkMap(playground);                         //odświerzenie mapy

        System.out.println("strzelaj!");

        do {
            player.shot();
            player.guess(guessRow, guessCol);                 //wywoływanie metody sprawdzającej czy próba zgadnięcia była skuteczna
        } while (yourGuess == 0);
    }
}

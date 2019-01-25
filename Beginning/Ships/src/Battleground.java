import java.util.Scanner;

public class Battleground {

    static int[][] playground;
    static int row;
    static int col;
    static int guess;
    static int guess_row;
    static int guess_col;
    Scanner write = new Scanner(System.in);





    public int[][] SetMap(int x, int y){                        // metoda tworząca mapę

        int[][] battleground = new int[x][y];
        for (int i=0; i<battleground.length;i++){
            for (int j=0; j<battleground[i].length;j++) {
                if (j == y - 1) {
                    System.out.println(battleground[i][j]);
                }
                else {
                    System.out.print(battleground[i][j]);
                }
            }
        }
        playground = battleground;
        return playground;
    }

    public void SetShip(int x, int y){                          //metoda ustawiająca statek

       playground[x-1][y-1]=1;

    }

    public int Guess(int x,int y){                              //metoda informująca o strzale
        if (playground[x-1][y-1] == 1){
            guess = 1;
            System.out.println("Trafiony");
        }
        else {
            guess = 0;
            System.out.println("Pudło");
        }
        return guess;
    }

    public void CheckMap (int[][] tab){                         //odświeża mape

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
    public void Shot(int x, int y){
        System.out.println("który wiersz?");
        guess_row = write.nextInt();                   //zgadywanie wiersza położenia statku
        System.out.println("która kolumna");
        guess_col = write.nextInt();                   //zgadywanie kolumny położenia statku
    }


    public static void main(String[] args) {
        Battleground player = new Battleground();              // tworzenie obiektu mapy
        Scanner write = new Scanner(System.in);                // tworzenie obiektu opcji wejścia

        System.out.println("Podaj liczbę wierszy");
        row = write.nextInt();                                 //podajesz liczbę wierszy
        System.out.println("Podaj liczbę kolumn");
        col = write.nextInt();                                 //podajesz liczbę kolumn


        player.SetMap(row,col);                                //wywoływanie metody tworzącej mapę

        System.out.println("Podaj wiersz gdzie chcesz ustawić statek");
        int cord_x = write.nextInt();                          //podajesz który wiersz
        System.out.println("Podaj kolumne gdzie chcesz ustawić statek");
        int cord_y = write.nextInt();                          //podajesz która kolumna

        player.SetShip(cord_x, cord_y);                        //wywoływanie metody ustawiającej statek
        player.CheckMap(playground);                           //sprawdzanie stanu mapy
        System.out.println("strzelaj!");

        do {
            player.Shot(guess_row,guess_col);
            player.Guess(guess_row, guess_col);                 //wywoływanie metody sprawdzającej czy próba zgadnięcia była skuteczna
        }while (guess == 0);
    }
}

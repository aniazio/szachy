package szachy;

import java.io.IOException;

public class Chess {

    public static void main(String[] args) {

        Board board = new Board();

        board.inicjalizacja();

        System.out.println("Witajcie w grze w szachy!");
        System.out.println("To jest plansza. Żółty kolor oznacza białe figury, a fioletowy - czarne.");
        System.out.println("Z boku oraz na dole planszy widnieją współrzędne pól.");
        System.out.println("Wpisujcie ruchy w formacie: (litera startu)(cyfra startu)(spacja)(litera końca)(cyfra końca) np. a2 a3");
        System.out.println("Zaczynajmy!");

        int kol=1;
        int kingx, kingy;

        do {

            board.pokazPlansze();

            kingx = (kol==0)? board.king0x : board.king1x; //król broniący się
            kingy = (kol==0)? board.king0y : board.king1y;



            if(board.czySzach((kol+1)%2, kingx, kingy)) {System.out.print("Szach! ");
            if(board.czyMat((kol+1)%2, kingx, kingy)) {System.out.print("Mat! "); break;}}

            System.out.println();


            try {
                board.wczytajIWykonajRuch(kol);

            kol = (kol+1)%2;
            } catch (IOException exc) {
                System.out.println("Błąd przy wczytywaniu ruchu. Spróbuj wpisać jeszcze raz.");
            }

        } while(1==1);


        if(kol==1) System.out.println("Koniec. Wygrywa czarny!");
        else System.out.println("Koniec. Wygrywa biały!");



    }
}

package szachy;

abstract public class Pole {

    // private int x,y;  to jednak chyba nie jest potrzebne

    int kolor;
    private char figura;
    private int stan;

    Pole(int kol){
        figura = 'P';
        kolor = kol;
        stan = 2;
    }

    Pole(int kol, char fig){ //kolor=0 to czarny, 1 to biały
        figura = fig;
        kolor = kol;
        stan = 2;
    }

    /*
    int getX() {return x;}
    void setX(int kx) {x=kx;}

    int getY() {return y;}
    void setY(int ky) {y=ky;}

     */

    int getKolor() {return kolor;}

    char getFigura() {return figura;}

    int getStan() {return stan;}
    void setStan(int s) {stan=s;}

    abstract boolean ruch(int sx, int sy, int kx, int ky, Pole[][] plansza);
    //będzie pisał, czy poprawny, a jeśli tak, to go wykona

    abstract boolean czyatak(int sx, int sy, int kingx, int kingy, Pole[][] plansza);
    //będzie pisał, czy jest atak na króla


}

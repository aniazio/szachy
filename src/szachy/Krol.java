package szachy;

public class Krol extends Pole {

    //Król musi też przechowywać stany, ze względu na roszadę


    Krol(int kol){
        super(kol,'K');
        setStan(0);
    }

    boolean ruch(int sx, int sy, int kx, int ky, Pole[][] plansza) {

        if(kx<0 || kx>7 || ky<0 || ky>7) return false;
        int pion=kx-sx;
        int poziom=ky-sy;

        if((poziom<=1) && (poziom>=(-1)) && (pion<=1) && (pion>=(-1)) && !((pion==0) && (poziom==0))) {
            for(int i=0;i<8;i++) {
                for(int j=0;j<8;j++) {
                    if(plansza[i][j].getKolor()!=getKolor() && plansza[i][j].czyatak(i, j, kx, ky, plansza)) return false;
                }
            }

            return true;
        }
        return false;
    }


    boolean czyatak(int sx, int sy, int kingx, int kingy, Pole[][] plansza) {

        int pion=kingx-sx;
        int poziom=kingy-sy;

        if((poziom<=1) && (poziom>=(-1)) && (pion<=1) && (pion>=(-1)) && !((pion==0) && (poziom==0))) return true;
        else return false;

    }

}

package szachy;

public class Skoczek extends Pole {

    Skoczek(int kol){
        super(kol,'S');
    }

    boolean ruch(int sx, int sy, int kx, int ky, Pole[][] plansza) {

        if(kx<0 || kx>7 || ky<0 || ky>7) return false;
        int pion=kx-sx;
        int poziom=ky-sy;
        int pionabs = (pion > 0)? pion : (-1 * pion);
        int poziomabs = (poziom>0)?poziom : (-1 * poziom);


        if(poziomabs>0 && pionabs>0 && poziomabs+pionabs==3){return(true);}
        else return false;
    }


    boolean czyatak(int sx, int sy, int kingx, int kingy, Pole[][] plansza) {
        int pion=kingx-sx;
        int poziom=kingy-sy;
        int pionabs = (pion > 0)? pion : (-1 * pion);
        int poziomabs = (poziom>0)?poziom : (-1 * poziom);

        if(poziomabs>0 && pionabs>0 && poziomabs+pionabs==3){return(true);}
        else return false;

    }


}

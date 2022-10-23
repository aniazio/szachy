package szachy;

public class Hetman extends Pole {


    Hetman(int kol){
        super(kol,'H');
    }

    boolean ruch(int sx, int sy, int kx, int ky, Pole[][] plansza) {

        if(kx<0 || kx>7 || ky<0 || ky>7) return false;
        int pion=kx-sx;
        int poziom=ky-sy;
        int pionabs = (pion > 0)? pion : (-1 * pion);
        int poziomabs = (poziom>0)? poziom : (-1 * poziom);
        int pionznak = (pion>0)? 1 : -1;
        int poziomznak = (poziom>0)? 1 : -1;

        if(pion==0) pionznak=0;
        if(poziom==0) poziomznak=0;

        if(poziomabs>0 && poziomabs==pionabs){
            for(int i=1; i<pionabs; i++) {
                if(plansza[sx+(i*pionznak)][sy+(i*poziomznak)].getFigura()!='P') return false;
            }
            if(plansza[sx+pion][sy+poziom].getFigura()!='P' && plansza[sx+pion][sy+poziom].getKolor()==plansza[sx][sy].getKolor()) return false;
            return true;
        }
        else if((poziomabs==0 && pionabs!=0) | (pionabs==0 && poziomabs!=0)){
            for(int i=1; i<(pionabs+poziomabs); i++) {
                if(plansza[sx+(i*pionznak)][sy+(i*poziomznak)].getFigura()!='P') return false;
            }
            if(plansza[kx][ky].getFigura()!='P' && plansza[kx][ky].getKolor()==plansza[sx][sy].getKolor()) return false;
            return true;
        }
        else return false;
    }


    boolean czyatak(int sx, int sy, int kingx, int kingy, Pole[][] plansza) {

        int pion=kingx-sx;
        int poziom=kingy-sy;
        int pionabs = (pion > 0)? pion : (-1 * pion);
        int poziomabs = (poziom>0)?poziom : (-1 * poziom);
        int pionznak = (pion>0)? 1 : -1;
        int poziomznak = (poziom>0)?1 : -1;

        if(pion==0) pionznak=0;
        if(poziom==0) poziomznak=0;

        if(poziomabs>0 && poziomabs==pionabs){
            for(int i=1; i<pionabs; i++) {
                if(plansza[sx+(i*pionznak)][sy+(i*poziomznak)].getFigura()!='P') return false;
            }
            return true;
        } else if((poziomabs==0 && pionabs!=0) | (pionabs==0 && poziomabs!=0)){
            for(int i=1; i<(pionabs+poziomabs); i++) {
                if(plansza[sx+(i*pionznak)][sy+(i*poziomznak)].getFigura()!='P') return false;
            }
            return true;
        }
        else return false;

    }

}

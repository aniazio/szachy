package szachy;

public class Board {

    Pole[][] planszaPusta = new Pole[8][8];     //plansza przechowująca puste pola (na wypadek zbicia)
    Pole[][] plansza = new Pole[8][8];          //plansza przechowująca figury (na pustych odwołuje się do planszaPusta)
    int king0x, king0y, king1x, king1y;         //lokalizacja króla


    //kolory do prezentacji planszy

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";


    //inicjalizacja - wpisuje na plansze odpowiednie figury

   void inicjalizacja() {

       for(int i=0; i<8;i++){
           for(int j=0; j<8; j++) {
               planszaPusta[i][j] = new Puste(0);
               plansza[i][j] = planszaPusta[i][j];
           }
       }

       plansza[0][1] = new Skoczek(1);
       plansza[0][6] = new Skoczek(1);
       plansza[7][1] = new Skoczek(0);
       plansza[7][6] = new Skoczek(0);

       plansza[0][2] = new Goniec(1);
       plansza[0][5] = new Goniec(1);
       plansza[7][2] = new Goniec(0);
       plansza[7][5] = new Goniec(0);

       plansza[0][0] = new Wieza(1);
       plansza[0][7] = new Wieza(1);
       plansza[7][0] = new Wieza(0);
       plansza[7][7] = new Wieza(0);

       plansza[0][3] = new Hetman(1);
       plansza[7][3] = new Hetman(0);
       plansza[0][4] = new Krol(1);
       plansza[7][4] = new Krol(0);

       king1x=0; king1y=4;
       king0x=7; king0y=4;

       for(int i=0; i<8;i++) {
           plansza[1][i] = new Pion(1);
           plansza[6][i] = new Pion(0);
       }


   }

   /*resetStanów - resetuje stan pionów tak, aby pamiętać, czy pion może jeszcze wykonać ruch o dwa pola
   i czy można go zbić w przelocie
   aktualnie wykonywane bezpośrednio w kodzie

   void resetStanow() {

       for(int i=0;i<8;i++) {
           for(int j=0;j<8;j++) {
               if(plansza[i][j].getStan()==3) plansza[i][j].setStan(1);
               if(plansza[i][j].getStan()==1) plansza[i][j].setStan(2);
           }
       }

   }

    */

   //pokażPlanszę - wypisuje aktualną sytuację na planszy

   void pokazPlansze() {

       for(int i=7; i>=0;i--) {
           System.out.print(ANSI_BLUE + (i+1) + "|" + ANSI_RESET);
           for(int j=0; j<8; j++) {
               if(plansza[i][j].getFigura()=='P') System.out.print(ANSI_BLUE + " |" + ANSI_RESET);
               else if(plansza[i][j].getFigura()=='X') {if(plansza[i][j].getKolor()==1)
                   System.out.print(ANSI_YELLOW + "p" + ANSI_BLUE + "|" + ANSI_RESET);
               else System.out.print(ANSI_PURPLE + "p" + ANSI_BLUE + "|" + ANSI_RESET);}
               else {if(plansza[i][j].getKolor()==1)
                   System.out.print(ANSI_YELLOW + plansza[i][j].getFigura() + ANSI_BLUE + "|" + ANSI_RESET);
               else System.out.print(ANSI_PURPLE + plansza[i][j].getFigura() + ANSI_BLUE + "|" + ANSI_RESET);}

           }
           System.out.println();
       }
       System.out.println(ANSI_BLUE + "  a b c d e f g h" + ANSI_RESET);

   }



    boolean czySzach(int kolatakujacy, int kingx, int kingy){
        //trzeba sprawdzić wszystkie figury, nie tylko tę, która się ruszyła, bo mogła odsłonić atak
        // king to król broniący się

        for(int i=0;i<8;i++) {
            for(int j=0; j<8; j++) {
                if(plansza[i][j].getKolor()==kolatakujacy && plansza[i][j].czyatak(i, j, kingx, kingy, plansza)) {
                    //System.out.println("Figura atakująca znajduje się na polu: " + (char) (j + 97) + (char) (i + 49) );
                    return true;}
            }
        }
        return false;
    }


    boolean czyMat(int kolatakujacy, int kingx, int kingy){
        //trzeba sprawdzić, czy któraś figura może zasłonić, nie tylko, czy może uciec król
        // king to król broniący się
        boolean mat=true;
        Pole p;


        //straszna złożoność, ale trzeba (chyba) zbadać wszystkie możliwe ruchy i dla nich, czy zachodzi nadal szach
        for(int i=0;i<8;i++) {
            for(int j=0;j<8;j++) {

                if(plansza[i][j].getKolor()==((kolatakujacy+1)%2)) {
                    for(int k=0;k<8;k++) {
                        for(int l=0;l<8;l++){
                            try{

                            if(k != i && l != j && plansza[i][j].ruch(i,j,k,l,plansza)) {
                                p = plansza[k][l];
                                plansza[k][l] = plansza[i][j];
                                plansza[i][j] = planszaPusta[i][j];

                                if (plansza[k][l].getFigura() == 'K' && plansza[k][l].getKolor() == ((kolatakujacy + 1) % 2)) {
                                    if (czySzach(kolatakujacy, k, l)) mat = false;
                                } else if (czySzach(kolatakujacy, kingx, kingy)) mat = false;

                                plansza[i][j] = plansza[k][l];
                                plansza[k][l] = p;

                            }

                            } catch(ArrayIndexOutOfBoundsException exp) {}
                        }
                    }

                }

            }
        }

        return mat;
    }


    void promocjaPiona(int kx, int ky) throws java.io.IOException {

       System.out.println("Promocja piona. Na jaką figurę chcesz wymienić piona?");


       char figa=1;
       char c;

        do {

            System.out.println("Wpisz jedną z liter: W, S, G, H:");

            figa = (char) System.in.read();
            do {
                c = (char) System.in.read();
            } while (c != '\n');

        }while (!(figa=='W' || figa=='S' || figa=='G' || figa=='H'));

        int kolor = plansza[kx][ky].getKolor();

        switch(figa) {
            case 'W':
                plansza[kx][ky] = new Wieza(kolor);
                break;
            case 'S':
                plansza[kx][ky] = new Skoczek(kolor);
                break;
            case 'G':
                plansza[kx][ky] = new Goniec(kolor);
                break;
            case 'H':
                plansza[kx][ky] = new Hetman(kolor);
                break;

        }

    }


    //wczytajIWykonajRuch - wczytuje ruch do momentu, aż ruch będzie poprawny i odpowiednio aktualizuje planszę

   void wczytajIWykonajRuch(int kol) throws java.io.IOException {

       boolean zlaodp=true;
       char startx, starty, konx, kony;
       int sx, sy, kx,ky;
       char c;
       int kingx, kingy;
       Pole p;

       sx=sy=kx=ky=99;

       if(kol==1) System.out.print("Ruch białych. ");
       else System.out.print("Ruch czarnych. ");

       do {

           //wczytanie ruchu

            startx=starty=konx=kony=99;
            System.out.print("Wpisz swój ruch: ");
           x: {
               starty = (char) System.in.read();
               if(starty=='\n') break x;
               startx = (char) System.in.read();
               if(startx=='\n') break x;
               c = (char) System.in.read();
               if(c=='\n') break x;
               kony = (char) System.in.read();
               if(kony=='\n') break x;
               konx = (char) System.in.read();
               if(konx=='\n') break x;
               c = '\n'; //Na wypadek, gdyby nie było czego czytać


               do {
                   c = (char) System.in.read();
               } while (c != '\n');
           }

           sx = startx-49;
           kx = konx -49;
           sy = starty-97;
           ky = kony - 97;


            //sprawdzenie poprawności

           try {
               zlaodp=false;

               if (!(sx >= 0 && sx <= 7 && sy >= 0 && sy <= 7 && kx >= 0 && kx <= 7 && ky >= 0 && ky <= 7)) throw new NiepoprawneDane();

               if (plansza[sx][sy].getFigura() == 'P') throw new PoczatekPusty();

               if (plansza[sx][sy].getKolor() == ((kol + 1) % 2)) throw new NieTwoje();


               /*
               teraz sprawdzamy, czy ruch wystawia na szacha
               nie można wykonać ruchu, jeśli ten odsłania króla
                */

               if (plansza[sx][sy].ruch(sx, sy, kx, ky, plansza)) {
                   p = plansza[kx][ky];
                   plansza[kx][ky] = plansza[sx][sy];
                   plansza[sx][sy] = planszaPusta[sx][sy];

                    //pokazPlansze();


                   // jeśli wykonany został ruch króla, to trzeba zmienić pozycję
                   if(kol==1) {if(sx==king1x && sy==king1y) {king1x=kx; king1y=ky;}}
                   else {if(sx==king0x && sy==king0y) {king0x=kx; king0y=ky;}}

                   kingx = (kol == 1) ? king1x : king0x;
                   kingy = (kol == 1) ? king1y : king0y;


                   if (czySzach(((kol + 1) % 2), kingx, kingy)) {
                       plansza[sx][sy] = plansza[kx][ky];
                       plansza[kx][ky] = p;

                       if(kol==1) {if(kx==king1x && ky==king1y) {king1x=sx; king1y=sy;}}
                       else {if(kx==king0x && ky==king0y) {king0x=sx; king0y=sy;}}

                       System.out.print("Uważaj na szach! ");
                       throw new ZlyRuch();
                   }
               } else throw new ZlyRuch();

               if(ky==7 && plansza[kx][ky].getFigura()=='X' && plansza[kx][ky].getKolor()==1) promocjaPiona(kx,ky);
               if(ky==0 && plansza[kx][ky].getFigura()=='X' && plansza[kx][ky].getKolor()==0) promocjaPiona(kx,ky);

               if(plansza[kx][ky].getStan()==4) {plansza[kx-1][ky] = planszaPusta[kx-1][ky]; plansza[kx][ky].setStan(2);}


           } catch(NieTwoje | PoczatekPusty | NiepoprawneDane | ZlyRuch exp) {
               zlaodp = true;
               System.out.println(exp);
           }


       } while(zlaodp);


       //po ruchu trzeba zresentować piony


       for(int i=0; i<8;i++) for(int j=0;j<8;j++) {
           if(plansza[i][j].getStan()==1) {plansza[i][j].setStan(2);}
           if(plansza[i][j].getStan()==3) {plansza[i][j].setStan(1);}
       }



   }


}

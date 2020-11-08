import java.util.Random;
public class Zadanie_3 extends Thread {
    static class Traktor {

        static int POLE = 1;
        static int START = 2;
        static int PRACA = 3;
        static int KONIEC_PRACY = 4;
        static int AWARIA = 5;
        static int UZUPELNIJ = 1000;
        static int PUSTY_ZBIORNIK = 0;

        int numer;
        int nawozy;
        int stan;
        POLE p;
        Random rand;

        public Traktor(int numer, int nawozy, POLE p) {
            this.numer = numer;
            this.nawozy = nawozy;
            this.stan = PRACA;
            this.p = p;
            rand = new Random();
        }

    public void run(){
        while(true){
            if(stan==POLE){
                if(rand.nextInt(2)==1){
                    stan=START;
                    nawozy=UZUPELNIJ;
                    System.out.println("Siac nawozy jedzie, traktor "+numer);
                            stan=p.start(numer);
                }
                else{
                    System.out.println("Postoje sobie jeszcze troche");
                }
            }
            else if(stan==START){
                System.out.println("Wyjechal, traktor "+numer);
                stan=PRACA;
            }
            else if(stan==PRACA){
                nawozy-=rand.nextInt(500);
                System.out.println("Traktor "+numer+" na polu");
                if(nawozy<=PUSTY_ZBIORNIK){
                    stan=KONIEC_PRACY;
                }
                else try{
                    sleep(rand.nextInt(1000));
                }
                catch (Exception e){}
            }
            else if(stan==KONIEC_PRACY){
                System.out.println("Traktor wraca do domu "+numer+" ilosc nawozow w siewniku "+nawozy);
                stan=p.laduj();
                if(stan==KONIEC_PRACY){
                    nawozy-=rand.nextInt(500);
                    System.out.println("Reszta nawozow w siewniku:  "+nawozy);
                    if(nawozy<=0) stan=AWARIA;
                }
            }
            else if(stan==AWARIA){
                System.out.println("Awaria traktora "+numer);
                p.zmniejsz();
            }
        }
    }
    }
            static class POLE {
                static int POLE=1;
                static int START=2;
                static int PRACA=3;
                static int KONIEC_PRACY=4;
                static int AWARIA=5;
                int ilosc_siewnikow;
                int ilosc_zajetych;
                int ilosc_traktorow;
                POLE(int ilosc_siewnikow,int ilosc_traktorow){
                    this.ilosc_siewnikow=ilosc_siewnikow;
                    this.ilosc_traktorow=ilosc_traktorow;
                    this.ilosc_zajetych=0;
                }
                synchronized int start(int numer){
                    ilosc_zajetych--;
                    System.out.println("Siewnik podpiety do traktora. Mozna ruszac "+numer);
                    return START;
                }
                synchronized int laduj(){
                    try{
                        Thread.currentThread().sleep(1000);
                    }
                    catch(Exception ie){
                    }
                    if(ilosc_zajetych<ilosc_siewnikow){
                        ilosc_zajetych++;
                        System.out.println("Traktor sprawny, podpiety i gotowy, mozna jechac na pole "+ilosc_zajetych);
                        return POLE;
                    }
                    else
                    {return KONIEC_PRACY;}
                }
                synchronized void zmniejsz(){
                    ilosc_traktorow--;
                    System.out.println("AWARIA");
                    if(ilosc_traktorow==ilosc_siewnikow) System.out.println("Ilosc trkatorow jaka sama jak siewnikow");
                }
            }

    static class Glowna {
                static int ilosc_traktorow = 10;
                static int ilosc_siewnikow = 5;
                static POLE pole;

                public Glowna() {
                }

                public static void main(String[] args) {
                    pole = new POLE(ilosc_siewnikow, ilosc_traktorow);
                    for (int i = 0; i < ilosc_traktorow; i++)
                        new Traktor(i, 2000, pole).run();
                }
            }
    }

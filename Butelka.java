package butelka;

import java.util.Random;
public class Butelka {

    double pojemnosc;
    double zawartosc;

    Butelka() {
    }

    Butelka(double nowaPojemnosc) {
        pojemnosc = nowaPojemnosc;
        zawartosc = 0;
    }

    double getPojemnosc(Butelka butelka) {
        return butelka.pojemnosc;

    }

    double getZawaartosc(Butelka butelka) {
        return butelka.zawartosc;

    }

    /*
        //TODO wypisanie statusu
        *Czy jest jakiś fajniejszy sposób do wypisania tych wartości ?
     */
    String getStatus(int nrButelki) {
        return ("zawartość butelki " + nrButelki + " , " + this.zawartosc + " z " + this.pojemnosc + "litrów");
    }

    boolean wlej(double ile) {
        if (ile > (this.pojemnosc - this.zawartosc)) {
            System.out.println("Wlewam do pełna " + (this.pojemnosc - this.zawartosc) + "litrów");
            this.zawartosc = this.pojemnosc;
            return false;
        } else {
            System.out.println("Wlewam " + ile + "litrów");
            this.zawartosc += ile;
            return true;
        }
    }

    void wylej(double ile) {
        if (ile > this.zawartosc) {
            System.out.println("Sorry ale w tej butelce jest tylko " + this.zawartosc + "litrów. \n" + "Wlewam całość " + ile + "litrów");
            this.zawartosc = 0;
        } else {
            System.out.println("Wylewam " + ile + "litrów");
            this.zawartosc -= ile;
        }
    }

    void przelej(double ile, Butelka gdzie) {

        if (this == gdzie) {
            System.out.println("Ej stary to te same butelki");
        }

        if (this.zawartosc < ile) {
            if (gdzie.pojemnosc - gdzie.zawartosc < ile) {
                this.wylej(ile);
                gdzie.wlej(ile);
            } else //za mało miejsca w docelowej
            {
                System.out.println("Nie mam tyle miejsca w nowej butelce, jest tam tylko " + (gdzie.pojemnosc - gdzie.zawartosc) + " litrów i tyle przelewam");
                this.wylej(gdzie.pojemnosc - gdzie.zawartosc);
                gdzie.wlej(gdzie.pojemnosc - gdzie.zawartosc);
            }
        } else //znie mam tyle w butelce 
        {
            System.out.println("Nie mam tyle w tej bytelce jest tam tylko " + this.zawartosc + "litrów i tyle przeleję");
            this.wylej(this.zawartosc);
            gdzie.wlej(this.zawartosc);
        }
    }

    public static void main(String[] args) {
        int ileButelek = 5; //Ile butelek będzie w ruchu
        int maxOperacji = 10; //ilość operacji testowania 
        int maxPojemnoscButelki = 15;// Max ymalny rozmiar butelki
        

        Butelka[] butelki = new Butelka[ileButelek];
        Random random = new Random();

        for (int i = 0; i < ileButelek; i++) {
            System.out.println("Tworze butelke" + i);
            butelki[i] = new Butelka((random.nextInt((maxPojemnoscButelki))) + 1);
        }

        for (int i = 0; i < ileButelek; i++) {

            System.out.println("Pojemnosc butelki " + (i + 1) + " " + butelki[i].pojemnosc);
        }

        for (int i = 0; i < maxOperacji; i++) {
            /*
            losowanie operacji
                0 - wlej
                    losowanie butelki
                1 - wylej
                    losowanie butelki
                2 - przelej
                    losowanie 2 butelek
            
             */
            int operacja = random.nextInt(2);

            if (operacja == 0) {
                int butelka1 = random.nextInt(ileButelek);
                System.out.println(butelki[butelka1].getStatus(butelka1));
                double ile = random.nextDouble() * maxPojemnoscButelki / 2;
                butelki[butelka1].wlej(ile);

                System.out.println(butelki[butelka1].getStatus(butelka1));
            }
            if (operacja == 1) {
                int butelka1 = random.nextInt(ileButelek);
                System.out.println(butelki[butelka1].getStatus(butelka1));

                double ile = random.nextDouble() * maxPojemnoscButelki / 2;
                butelki[butelka1].wylej(ile);
                System.out.println(butelki[butelka1].getStatus(butelka1));
            }

            if (operacja == 2) {
                int butelka1 = random.nextInt(ileButelek);
                int butelka2 = random.nextInt(ileButelek);
                double ile = random.nextDouble() * maxPojemnoscButelki / 2;
                butelki[butelka1].przelej(ile, butelki[butelka2]);
                System.out.println(butelki[butelka1].getStatus(butelka1));
                System.out.println(butelki[butelka2].getStatus(butelka2));
            }

        }
        //Wypisanie stanu wszystkich butelek
        for (int i = 0; i < ileButelek; i++) {
            System.out.println(butelki[i].getStatus(i));
        }
    }

}

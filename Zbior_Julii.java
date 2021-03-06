import java.awt.image.BufferedImage;
import java.awt.Color;
import javax.swing.JFrame;

public class Zadanie_4 {

    // szerokosc + wysokosc okna
    private static final int WIDTH = 450;
    private static final int HEIGHT = 450;
    private static Complex c;
    // Zakresy x, y
    private double minX = -2;
    private double maxX = 2;
    private double minY = -2;
    private double maxY = 2;


    private BufferedImage obraz = null;

    private double granica = 1;

    private int iteracje = 60;



    public Zadanie_4(Complex c) {
        this.c = c;  //  Liczba zespolona dla ktorej rysujemy zbior Julii

        // Rezerwacja miejsca na obraz buforowany
        obraz = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);

        rysuj( obraz );

        // Otwiera okno i wyswietla obraz
        wyswietl(obraz);
    }



    void wyswietl(BufferedImage obraz) {
        JFrame f = new JFrame("Zadanie 4"){
            // rysujemy
            @Override
            public void paint(java.awt.Graphics g){
                g.drawImage(obraz,0,0,null);
            }
        };
        // wlasnosci okna
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(WIDTH,HEIGHT);
        f.repaint();
        f.setVisible(true);
    }



    void rysuj(BufferedImage obraz) {
        Color kolor;
        for(int i=0;i<WIDTH;i++){
            for(int j=0;j<HEIGHT;j++){

                // od pixela (i,j) obrazu do (x, y)
                double x = xCoord(i);
                double y = yCoord(j);
                Complex z = new Complex(x, y);

                // Punkt zbioru bialy; poza zbiorem czarny
                if (jestWZbiorze(z)) {
                    kolor = Color.WHITE;
                } else {
                    kolor = Color.BLACK;
                }
                obraz.setRGB(i, j, kolor.getRGB());
            }
        }
    }


    double xCoord(int i){
        return (double)i*(maxX-minX)/(double)WIDTH + minX;
    }
    double yCoord(int j){
        return (double)j*(maxY-minY)/(double)HEIGHT + minY;
    }


    private boolean jestWZbiorze(Complex z) {
        for(int i=0;i<iteracje;i++)
            z.razy(z);
        return granica > z.abs();
    }


    // Test
    public static void main(String[] args) {
        double x, y;
        x = -1.1;  y = 0.2535;
        x = -.023; y = .535;

        Complex c = new Complex(x, y);
        if (args.length == 2) {
            x = Double.parseDouble(args[0]);
            y = Double.parseDouble(args[1]);
            c = new Complex(x, y);
        }
        new Zadanie_4(c);
    }
}

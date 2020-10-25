import java.util.ArrayList;

import static java.lang.Math.*;



public class Lab2 {

      static class M_prostokatow extends Thread {
              double a, b;
              public M_prostokatow(double ai, double bi) {
                  a = ai;
                  b = bi;
              }

          public void run() {
                  int n = 10;
                  ArrayList<Double> wi = new ArrayList<>();
                  ArrayList<Double> ti = new ArrayList<>();

                  wi.add(0.2955242247147529	);
                  wi.add(0.2955242247147529);
                  wi.add(0.2692667193099963);
                  wi.add(0.2692667193099963);
                  wi.add(0.2190863625159820);
                  wi.add(0.2190863625159820);
                  wi.add(0.1494513491505806);
                  wi.add(0.1494513491505806);
                  wi.add(0.0666713443086881);
                  wi.add(0.0666713443086881);

                  ti.add(-0.1488743389816312);
                  ti.add(0.1488743389816312);
                  ti.add(-0.4333953941292472);
                  ti.add(0.4333953941292472);
                  ti.add(-0.6794095682990244);
                  ti.add(0.6794095682990244);
                  ti.add(-0.8650633666889845);
                  ti.add(0.8650633666889845);
                  ti.add(-0.9739065285171717);
                  ti.add(0.9739065285171717);




                  double[] fx = new double[n];
                  double suma = 0;
                  double wynik = 0;
                  for(int i = 0; i < n; i++) {
                      double x = (b-a)/2 * ti.get(i) + (b+a)/2;
                      fx[i] = (sin((0.5 * x) + 0.1)) / (2.2 + sqrt(0.7 * pow(x,2) + 1.4));;
                      suma += wi.get(i) * fx[i];
                  }
                  wynik = (b-a)/2 * suma;
                  System.out.println(wynik);
              }
      }



      static class M_Simpsona extends Thread {
              double a, b;
              int n;

              public M_Simpsona(double ai, double bi, int N) {
                  a = ai;
                  b = bi;
                  n = N;
              }

              double fx(double x) {
                  return (pow(x, 3));
              }

             public void run() {
                  double calka = 0, x, ti;
                  double h = (b - a) / n;
                  ti = 0;
                  for (int i = 1; i < n; i++) {
                      x = a + i * h;
                      ti += fx(x - h / 2);
                      calka += fx(x);
                  }
                  ti += fx(b - h / 2);
                  calka = (h / 6) * (fx(a) + fx(b) + 4 * ti + 2 * calka);
                System.out.println(calka);
              }

      }

    static class M_trapezow extends Thread {

        double a, b;
        int n;

        public M_trapezow(double ai, double bi, int N) {
            a = ai;
            b = bi;
            n = N;
        }

        double fx(double x) {
            return (pow(x, 3));
        }

        public void run()
        {
            double calka = 0;
            double h = (b-a)/n;

            for(int i = 1; i <= n-1; i++)
            {
                calka += fx(a + i * h);
            }
            calka += (fx(a) + fx(b)) / 2;
            calka *= h;
            System.out.println(calka);
        }
    }

      public static void main(String[] args) throws Exception {

              M_Simpsona w1 = new M_Simpsona(1,3, 10);
              M_prostokatow w2 = new M_prostokatow(1, 3);
              M_trapezow w3 = new M_trapezow(1, 3, 10);

              w1.start();
              w2.start();
              w3.start();

      }
}

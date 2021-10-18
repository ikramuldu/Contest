package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        new Solver().solve();
        //new Tester().test();
    }


    private static class Solver {
        void solve() throws IOException {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            int cases = Integer.parseInt(reader.readLine());
            for (int caseNo = 1; caseNo <= cases; caseNo++) {
                StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
                int degree1 = Integer.parseInt(tokenizer.nextToken());
                int[] a = new int[degree1 + 1];
                for (int i = 0; i <= degree1; i++) a[i] = Integer.parseInt(tokenizer.nextToken());
                tokenizer = new StringTokenizer(reader.readLine());
                int degree2 = Integer.parseInt(tokenizer.nextToken());
                int[] b = new int[degree2 + 1];
                for (int i = 0; i <= degree2; i++) b[i] = Integer.parseInt(tokenizer.nextToken());


                int n = 1;
                while (n < a.length + b.length)
                    n <<= 1;
                Complex[] fa = new Complex[n];
                Complex[] fb = new Complex[n];
                for (int i = 0; i < a.length; i++) fa[i] = new Complex(a[i]);
                for (int i = a.length; i < n; i++) fa[i] = new Complex();
                for (int i = 0; i < b.length; i++) fb[i] = new Complex(b[i]);
                for (int i = b.length; i < n; i++) fb[i] = new Complex();

                fft(fa, false);
                fft(fb, false);
                for (int i = 0; i < n; i++)
                    fa[i] = fa[i].mul(fb[i]);
                fft(fa, true);

                int[] result = new int[degree1 + degree2 + 1];
                for (int i = 0; i < result.length; i++)
                    result[i] = Math.round(fa[i].real);

                StringBuilder builder = new StringBuilder();
                builder.append("Case ");
                builder.append(caseNo);
                builder.append(": ");
                builder.append(result.length - 1);
                for (int r : result) {
                    builder.append(" ");
                    builder.append(r);
                }
                System.out.println(builder);
            }
        }


        private void fft(Complex[] a, boolean invert) {
            int n = a.length;
            if (n == 1) return;
            n = n / 2;
            Complex[] a0 = new Complex[n];
            Complex[] a1 = new Complex[n];
            for (int i = 0; i < n; i++) {
                a0[i] = a[2 * i];
                a1[i] = a[2 * i + 1];
            }
            fft(a0, invert);
            fft(a1, invert);

            double ang = Math.PI / n * (invert ? 1 : -1);
            Complex w = new Complex(1);
            Complex wn = new Complex((float) Math.cos(ang), (float) Math.sin(ang));
            for (int i = 0; i < n; i++) {
                a[i] = a0[i].add(w.mul(a1[i]));
                a[i + n] = a0[i].sub(w.mul(a1[i]));
                if (invert) {
                    a[i].half();
                    a[i + n].half();
                }
                w = w.mul(wn);
            }
        }
    }

   private static class Complex {
        float real, img;

        Complex(float real, float img) {
            this.real = real;
            this.img = img;
        }

        public Complex(int real) {
            this.real = real;
        }

        public Complex() {

        }

        public Complex mul(Complex c) {
            float r = real * c.real - img * c.img;
            float i = real * c.img + img * c.real;
            return new Complex(r, i);
        }

        public Complex add(Complex c) {
            return new Complex(real + c.real, img + c.img);
        }

        public Complex sub(Complex c) {
            return new Complex(real - c.real, img - c.img);
        }

        public void half() {
            real /= 2;
            img /= 2;
        }
    }
}
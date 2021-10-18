package com.company;

public class FFT {
    static void fft(Complex[] a, boolean invert) {
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
        Complex wn = new Complex((float) Math.cos(ang),(float) Math.sin(ang));
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

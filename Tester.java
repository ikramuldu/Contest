package com.company;

public class Tester {
    public void test() {
        int[] inp = {2, 8};
        Complex[] input = new Complex[inp.length];
        for (int i = 0; i < inp.length; i++) input[i] = new Complex(inp[i]);
        FFT.fft(input, false);
        for (Complex x : input) System.out.println(x.real + ",   " + x.img);
    }
}

package com.company;

class Complex {
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

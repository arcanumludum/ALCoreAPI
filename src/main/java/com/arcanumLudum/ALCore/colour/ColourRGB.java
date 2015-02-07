package com.arcanumLudum.ALCore.colour;

public class ColourRGB extends Colour {
    public ColourRGB(int colour) {
        super((colour >> 24) & 0xFF, (colour >> 16) & 0xFF, (colour >> 8) & 0xFF, 255);
    }

    public ColourRGB(double r, double g, double b) {
        super((int) (255 * r), (int) (255 * g), (int) (255 * b), 255);
    }

    public ColourRGB(int r, int g, int b) {
        super(r, g, b, 255);
    }

    public ColourRGB(ColourRGB colour) {
        super(colour);
    }

    public int pack() {
        return pack(this);
    }

    @Override
    public Colour copy() {
        return new ColourRGB(this);
    }

    public static int pack(Colour colour) {
        return (colour.r & 0xFF) << 24 | (colour.g & 0xFF) << 16 | (colour.b & 0xFF) << 8;
    }

    public static int multiply(int c1, int c2) {
        if(c1 == -1) return c2;
        if(c2 == -1) return c1;
        int r = (((c1 >>> 24) * (c2 >>> 24)) & 0xFF00) << 16;
        int g = (((c1 >> 16 & 0xFF) * (c2 >> 16 & 0xFF)) & 0xFF00) << 8;
        int b = ((c1 >> 8 & 0xFF) * (c2 >> 8 & 0xFF)) & 0xFF00;
        
        return r | g | b;
    }

    public static int multiplyC(int c, float f) {
        int r = (int) ((c >>> 24) * f);
        int g = (int) ((c >> 16 & 0xFF) * f);
        int b = (int) ((c >> 8 & 0xFF) * f);
        
        return r<<24 | g<<16 | b<<8;
    }
}
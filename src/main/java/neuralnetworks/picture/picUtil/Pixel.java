package neuralnetworks.picture.picUtil;

import neuralnetworks.util.Scaler;

/**
 * Created by faiter on 10/11/17.
 */
public class Pixel {

    private int r;
    private int g;
    private int b;
    private int alpha;

    public Pixel(int r, int g, int b, int alpha) {

        this.r = r;
        this.g = g;
        this.b = b;
        this.alpha = alpha;
    }

    public int getR() {

        return r;
    }

    public int getG() {

        return g;
    }

    public int getB() {

        return b;
    }

    public int getAlpha() {

        return alpha;
    }
    public double getBrightness(){

        return 0.2126*r + 0.7152*g + 0.0722*b;

    }
    public double getScaledBrightness(){

        return Scaler.scale(getBrightness(), 0, 256);

    }

    public static void main(String[] args) {

        Pixel pixel = new Pixel(255, 255, 255, 1);
    }
}

package neuralnetworks.picture.X_or_O.app;

import neuralnetworks.picture.Shape;

import java.awt.image.BufferedImage;

/**
 * Created by faiter on 10/13/17.
 */
public class State {

    private BufferedImage compressedImage;
    private Shape guess;

    public BufferedImage getCompressedImage() {

        return compressedImage;
    }

    public void setCompressedImage(BufferedImage compressedImage) {

        this.compressedImage = compressedImage;
    }

    public Shape getGuess() {

        return guess;
    }

    public void setGuess(Shape guess) {

        this.guess = guess;
    }
}

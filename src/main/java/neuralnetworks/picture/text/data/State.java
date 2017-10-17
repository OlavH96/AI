package neuralnetworks.picture.text.data;

import java.awt.image.BufferedImage;

/**
 * Created by faiter on 10/13/17.
 */
public class State <T>{

    private BufferedImage compressedImage;
    private T guess;

    public BufferedImage getCompressedImage() {

        return compressedImage;
    }

    public void setCompressedImage(BufferedImage compressedImage) {

        this.compressedImage = compressedImage;
    }

    public T getGuess() {

        return guess;
    }

    public void setGuess(T guess) {

        this.guess = guess;
    }
}

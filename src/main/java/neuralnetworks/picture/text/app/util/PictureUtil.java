package neuralnetworks.picture.text.app.util;

import neuralnetworks.picture.X_or_O.data.Pixel;
import neuralnetworks.util.Loader;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by faiter on 10/11/17.
 */
public class PictureUtil {

    public static List<Pixel> from(byte[] array){

        List<Pixel> pixels = new ArrayList<>();

        for (int i = 0; i < array.length; i+=4) {

            byte r = array[i];
            byte g = array[i+1];
            byte b = array[i+2];
            byte a = array[i+3];

            pixels.add(new Pixel(r,g,b,a));

        }
        return pixels;
    }
    private static BufferedImage scale(BufferedImage sbi, int imageType, int dWidth, int dHeight, double fWidth, double fHeight) {
        BufferedImage dbi = null;
        if(sbi != null) {
            dbi = new BufferedImage(dWidth, dHeight, imageType);
            Graphics2D g = dbi.createGraphics();
            AffineTransform at = AffineTransform.getScaleInstance(fWidth, fHeight);
            g.drawRenderedImage(sbi, at);
        }
        return dbi;
    }

    public static BufferedImage scale(BufferedImage image, int newSize){

        int width = image.getWidth();

        double factor = (double) newSize/(double) width;

        return scale(image, image.getType(), newSize, newSize, factor, factor);

    }

    public static void main(String[] args) throws IOException {

        BufferedImage bufferedImage = Loader.loadImage("/pictures/test/x_16_test.png");

        BufferedImage scale = scale(bufferedImage, 3);


        ImageIO.write(scale, "png", new File("/home/faiter/IdeaProjects/AI/src/main/resources/pictures/output/scaled.png"));

    }

}

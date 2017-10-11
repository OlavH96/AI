package neuralnetworks.util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by faiter on 10/11/17.
 */
public class Loader {

    public static double[] loadPixelData(BufferedImage image){

        WritableRaster raster = image.getRaster();

        byte[] pixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();

        double[] out = new double[pixels.length];

        int counter = 0;

        int width = image.getWidth();
        int height = image.getHeight();

        for (int j = 0; j < width; j++) {
            for (int k = 0; k < height; k++) {
                out[counter++] = raster.getSample(j, k, 0);
            }
        }

        return out;
    }
    public static BufferedImage loadImage(String path){

        try {
            BufferedImage read = ImageIO.read(Loader.class.getResource(path));

            //byte[] data = ((DataBufferByte) read.getRaster().getDataBuffer()).getData();
            //System.out.println(Arrays.toString(data));
            return read;
        }
        catch (IOException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Illegal path");
        }
    }
    public static List<BufferedImage> loadImages(List<String> paths) throws IOException {

        List<BufferedImage> out = new ArrayList<>();

        paths.forEach(s -> {
            //System.out.println(s);
            out.add(Loader.loadImage(s));

        });

        return out;
    }
    public static List<BufferedImage> loadImagesFromFolder(String folderPath) throws IOException {

        List<BufferedImage> out = new ArrayList<>();

        File folder = new File(folderPath);

        System.out.println(folder);

        File[] listOfFiles = folder.listFiles();
        System.out.println(Arrays.toString(listOfFiles));
        Arrays.stream(listOfFiles).forEach(file -> {

            out.add(Loader.loadImageFromAbsolutePath(file.getPath()));
        });

        return out;
    }
    public static BufferedImage loadImageFromAbsolutePath(String path){

        System.out.println(path.split("/")[(path.split("/").length-1)]);
        try {

            BufferedImage read = ImageIO.read(new File(path));

            //byte[] data = ((DataBufferByte) read.getRaster().getDataBuffer()).getData();
            //System.out.println(Arrays.toString(data));
            return read;
        }
        catch (IOException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Illegal path");
        }
    }}
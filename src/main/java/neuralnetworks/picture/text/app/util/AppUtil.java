package neuralnetworks.picture.text.app.util;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;

/**
 * Created by faiter on 10/13/17.
 */
public class AppUtil {

    public static void clearCanvas(Canvas canvas){

        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.WHITE);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(10);

        gc.fill();
        gc.fillRect(
                0,              //x of the upper left corner
                0,              //y of the upper left corner
                400,    //width of the rectangle
                400);  //height of the rectangle



        gc.setLineWidth(80);
        gc.setStroke(Color.BLACK);
    }

    public static void toggleButtons(Button... buttons){

        for (Button b : buttons){
            try {
                b.setDisable(!b.isDisabled());

            }catch (RuntimeException e){}

        }

    }

}

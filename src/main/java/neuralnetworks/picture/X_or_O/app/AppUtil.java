package neuralnetworks.picture.X_or_O.app;

import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;

/**
 * Created by faiter on 10/13/17.
 */
public class AppUtil {

    public static void clearCanvas(Canvas canvas){
        canvas.getGraphicsContext2D().clearRect(0,0, canvas.getWidth(), canvas.getHeight());
        canvas.getGraphicsContext2D().setLineWidth(10);
        canvas.getGraphicsContext2D().strokeRect(
                0,              //x of the upper left corner
                0,              //y of the upper left corner
                canvas.getWidth(),    //width of the rectangle
                canvas.getHeight());  //height of the rectangle

        canvas.getGraphicsContext2D().setLineWidth(80);
    }

    public static void toggleButtons(Button... buttons){

        for (Button b : buttons){
            try {
                b.setDisable(!b.isDisabled());

            }catch (RuntimeException e){}

        }

    }

}

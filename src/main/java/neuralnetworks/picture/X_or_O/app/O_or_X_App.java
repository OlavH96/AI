package neuralnetworks.picture.X_or_O.app;/**
 * Created by faiter on 10/12/17.
 */

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import neuralnetworks.picture.Shape;
import neuralnetworks.picture.SimpleNeuralNetwork;
import neuralnetworks.picture.picUtil.PictureUtil;
import neuralnetworks.util.Loader;
import neuralnetworks.util.Scaler;
import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.data.DataSetRow;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class O_or_X_App extends Application {

    private static final String OUTPUT_FOLDER = "/home/faiter/IdeaProjects/AI/src/main/resources/app/output/";

    public static void main(String[] args) {

        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {

        NeuralNetwork fromFile = NeuralNetwork.createFromFile("/home/faiter/IdeaProjects/AI/src/main/resources/perceptron.nnet");

        SimpleNeuralNetwork neuralNetwork = new SimpleNeuralNetwork(fromFile);

        State state = new State();

        BorderPane pane = new BorderPane();
        pane.setPadding(new Insets(10));

        Canvas canvas = FXCanvas.getCanvas();

        VBox center = new VBox();
        center.setPadding(new Insets(10));
        center.setSpacing(10);

        Button calculateButton = new Button("Calculate");
        Text outputText = new Text("I think this is: ");
        outputText.setFont(Font.font(20));
        Button correctButton = new Button("Correct");
        correctButton.setDisable(true);
        Button wrongButton = new Button("Wrong");
        wrongButton.setDisable(true);

        calculateButton.setOnAction(actionEvent -> {

            WritableImage image = canvas.snapshot(null, null);
            try {
                ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", new File(OUTPUT_FOLDER+"output.png"));
            }
            catch (IOException e) {
                e.printStackTrace();
            }

            BufferedImage image1 = Loader.loadImageFromAbsolutePath(OUTPUT_FOLDER + "output.png");
            image1 = PictureUtil.scale(image1, (int) Math.sqrt(neuralNetwork.getNeuralNetwork().getInputsCount())); // Scales image to test images size

            try {
                ImageIO.write(image1, "png", new File(OUTPUT_FOLDER+"compressed.png"));
            }
            catch (IOException e) {
                e.printStackTrace();
            }

            double[] test = neuralNetwork.test(Loader.loadPixelData(image1));

            Shape shape = Shape.fromValue((int) test[0]);

            outputText.setText("I think this is: " +shape);

            state.setCompressedImage(image1);
            state.setGuess(shape);

            toggleButtons(correctButton, wrongButton, calculateButton);
        });


        correctButton.setOnAction(actionEvent -> {

            Shape guess = state.getGuess();
            System.out.println("Correct was: "+ guess);

            DataSetRow learn = learn(state.getCompressedImage(), guess);
            neuralNetwork.retrain(learn);

            clearCanvas(canvas);
            toggleButtons(correctButton, wrongButton, calculateButton);
        });

        wrongButton.setOnAction(actionEvent -> {

            Shape guess = state.getGuess().next();

            System.out.println("Correct was: "+ guess);

            DataSetRow learn = learn(state.getCompressedImage(), guess);
            neuralNetwork.retrain(learn);

            clearCanvas(canvas);
            toggleButtons(correctButton, wrongButton, calculateButton);
        });


        center.getChildren().add(calculateButton);
        center.getChildren().add(outputText);

        HBox hBox = new HBox(correctButton, wrongButton);
        hBox.setSpacing(10);

        center.getChildren().add(hBox);
        pane.setCenter(canvas);
        pane.setBottom(center);


        stage.setMinHeight(500);
        stage.setMinWidth(500);
        stage.setScene(new Scene(pane));
        stage.setTitle("O or X");
        stage.show();

        stage.setOnCloseRequest(windowEvent -> {
            neuralNetwork.getNeuralNetwork().save("/home/faiter/IdeaProjects/AI/src/main/resources/app.nnet");

        });
    }


    private void clearCanvas(Canvas canvas){
        canvas.getGraphicsContext2D().clearRect(0,0, canvas.getWidth(), canvas.getHeight());
        canvas.getGraphicsContext2D().strokeRect(
                0,              //x of the upper left corner
                0,              //y of the upper left corner
                canvas.getWidth(),    //width of the rectangle
                canvas.getHeight());  //height of the rectangle

    }
    private void toggleButtons(Button ... buttons){

        for (Button b : buttons)
            b.setDisable(!b.isDisabled());

    }

    private DataSetRow learn(BufferedImage newImage, Shape shape){

        double[] doubles = Loader.loadPixelData(newImage);
        doubles = Scaler.scaleArray(doubles, 0, 255);

        System.out.println(Arrays.toString(doubles) +" - "+shape.getValue());

        return (new DataSetRow(doubles, new double[]{(double) shape.getValue()}));

    }
}

package neuralnetworks.picture.text;/**
 * Created by faiter on 10/12/17.
 */

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import neuralnetworks.picture.X_or_O.app.AppUtil;
import neuralnetworks.picture.X_or_O.app.FXCanvas;
import neuralnetworks.picture.X_or_O.app.State;
import neuralnetworks.picture.picUtil.PictureUtil;
import neuralnetworks.util.DataSetUtil;
import neuralnetworks.util.Loader;
import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.data.DataSetRow;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Text_App extends Application {

    private static final String OUTPUT_FOLDER = "/home/faiter/IdeaProjects/AI/src/main/resources/app/output/";

    private State<Letter> state = new State<>();
    private TextNeuralNetwork neuralNetwork;

    private Canvas canvas = FXCanvas.getCanvas();
    private Button calculateButton = new Button("Calculate");
    private Text outputText = new Text("I think this is: ");
    private Button correctButton = new Button("Correct");
    private Button wrongButton = new Button("Wrong");
    private Label actualAnswerTextFieldLabel = new Label("Actual Answer: ");
    private TextField actualAnswerTextField = new TextField();


    public static void main(String[] args) {

        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {

        canvas.getGraphicsContext2D().setLineWidth(30);

        NeuralNetwork fromFile = NeuralNetwork.createFromFile("/home/faiter/IdeaProjects/AI/src/main/resources/text/textnetwork.nnet");
        neuralNetwork = new TextNeuralNetwork(fromFile);

        BorderPane pane = new BorderPane();
        pane.setPadding(new Insets(10));

        VBox elementsVBox = new VBox();
        elementsVBox.setPadding(new Insets(10));
        elementsVBox.setSpacing(10);

        outputText.setFont(Font.font(20));
        correctButton.setDisable(true);
        wrongButton.setDisable(true);

        wrongButton.disableProperty().bind(Bindings.isEmpty(actualAnswerTextField.textProperty()));

        calculateButton.setOnAction(calculateButtonEvent());
        correctButton.setOnAction(correctButtonEvent());
        wrongButton.setOnAction(wrongButtonEvent());

        VBox calculateVBox = new VBox(calculateButton, outputText);
        calculateVBox.setSpacing(10);
        calculateVBox.setAlignment(Pos.CENTER);
        elementsVBox.getChildren().add(calculateVBox);

        HBox buttonsHBox = new HBox(correctButton, wrongButton);
        buttonsHBox.setSpacing(10);
        buttonsHBox.setAlignment(Pos.CENTER);

        HBox actualAnswerHBox = new HBox(correctButton, wrongButton);
        actualAnswerHBox.setSpacing(10);
        actualAnswerHBox.setAlignment(Pos.CENTER);

        actualAnswerHBox.getChildren().addAll(actualAnswerTextFieldLabel, actualAnswerTextField);

        elementsVBox.getChildren().addAll(buttonsHBox, actualAnswerHBox);
        pane.setCenter(canvas);
        pane.setBottom(elementsVBox);

        stage.setMinHeight(500);
        stage.setMinWidth(500);
        stage.setScene(new Scene(pane));
        stage.setTitle("Letter");
        stage.show();

        stage.setOnCloseRequest(windowEvent -> {
            neuralNetwork.getNeuralNetwork().save("/home/faiter/IdeaProjects/AI/src/main/resources/text/textnetwork.nnet");

            System.out.println("Saved improved neural network to file");
        });
    }


    private EventHandler<ActionEvent> correctButtonEvent(){

        return actionEvent -> {
                Letter guess = state.getGuess();
                System.out.println("Correct was: "+ guess);

                DataSetRow learn = DataSetUtil.learn(state.getCompressedImage(), guess);
                neuralNetwork.retrain(learn);

                AppUtil.clearCanvas(canvas);
            canvas.getGraphicsContext2D().setLineWidth(30);

            AppUtil.toggleButtons(correctButton, wrongButton, calculateButton);

        };
    }
    private EventHandler<ActionEvent> wrongButtonEvent(){

        return actionEvent -> {

            Letter actualLetter = Letter.valueOf(actualAnswerTextField.getText());

            System.out.println("Correct was: "+ actualLetter);

            actualAnswerTextField.setText("");

            DataSetRow learn = DataSetUtil.learn(state.getCompressedImage(), actualLetter);
            neuralNetwork.retrain(learn);
            AppUtil.clearCanvas(canvas);
            canvas.getGraphicsContext2D().setLineWidth(30);

            AppUtil.toggleButtons(correctButton, wrongButton, calculateButton);

        };

    }
    private EventHandler<ActionEvent> calculateButtonEvent(){

        return actionEvent -> {
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


            Letter of = Letter.of(test);

            outputText.setText("I think this is: " +of);

            state.setCompressedImage(image1);
            state.setGuess(of);

            AppUtil.toggleButtons(correctButton, wrongButton, calculateButton);

        };

    }
}

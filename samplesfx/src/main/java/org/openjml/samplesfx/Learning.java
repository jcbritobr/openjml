package org.openjml.samplesfx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.openjml.neuro.functions.SigmoidFunction;
import org.openjml.neuro.learning.BackPropagationLearning;
import org.openjml.neuro.networks.ActivationNetwork;

/**
 * Learning
 * Created by jgardona on 06/06/17.
 */
public class Learning extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Backpropagation Learning Sample");

        HBox box1 = new HBox(10);
        box1.setPadding(new Insets(10, 10, 10, 10));
        Button button = new Button("Learn");
        button.setPrefSize(100, 20);
        box1.getChildren().add(button);


        NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("Executed Epoch");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Mean Error");

        AreaChart areaChart = new AreaChart<>(xAxis, yAxis);
        areaChart.setAnimated(false);

        XYChart.Series sError = new XYChart.Series();
        sError.setName("Error");

        float[][] input = new float[][]{
                new float[]{0.0f, 0.0f}, new float[]{0.0f, 1.0f},
                new float[]{1.0f, 0.0f}, new float[]{1.0f, 1.0f}
        };

        float[][] output = new float[][]{
                new float[]{0.0f}, new float[]{1.0f},
                new float[]{1.0f}, new float[]{0.0f}
        };

        ActivationNetwork network = new ActivationNetwork(
                new SigmoidFunction(2.0f),
                2,
                2,
                1
        );

        final BackPropagationLearning teacher = new BackPropagationLearning(network);
        teacher.setLearningRate(0.1f);
        teacher.setMomentum(0.0f);

        button.setOnAction((event) ->{
            boolean needstop = false;
            int epoch = 0;
            while (!needstop) {
                float error = teacher.runEpoch(input, output);
                if (epoch % 100 == 2 ){
                    sError.getData().add(new XYChart.Data<>(epoch, error));
                }
                epoch++;
                if (error <= 0.01) {
                    needstop = true;
                }
            }
        });

        areaChart.getData().add(sError);

        BorderPane root = new BorderPane();
        root.setCenter(areaChart);
        root.setBottom(box1);

        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}

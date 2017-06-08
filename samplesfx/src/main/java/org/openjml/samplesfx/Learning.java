package org.openjml.samplesfx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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

        VBox vbox1 = new VBox(10);

        HBox box = new HBox(10);
        box.setPadding(new Insets(10, 10, 2, 10));
        Label label = new Label("Learning Rate:");
        box.getChildren().add(label);
        vbox1.getChildren().add(box);


        HBox box1 = new HBox(10);
        Label result = new Label("Result of XOR>>");
        box1.setPadding(new Insets(10, 10, 10, 10));
        Button button = new Button("Learn");
        button.setPrefSize(100, 20);
        box1.getChildren().add(button);
        box1.getChildren().add(result);

        HBox box2 = new HBox(10);
        box2.setPadding(new Insets(2, 2, 10, 10));
        TextField input1 = new TextField("0.1");
        input1.setPrefSize(60, 20);
        box2.getChildren().add(input1);
        vbox1.getChildren().add(box2);


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


        button.setOnAction((event) -> {

            ActivationNetwork network = new ActivationNetwork(
                    new SigmoidFunction(2.0f),
                    2,
                    2,
                    1
            );

            final BackPropagationLearning teacher = new BackPropagationLearning(network);
            sError.getData().clear();
            areaChart.getData().clear();

            teacher.setLearningRate(Float.parseFloat(input1.getText()));
            teacher.setMomentum(0.0f);

            boolean needstop = false;
            int epoch = 0;
            boolean fail = false;

            while (!needstop) {
                float error = teacher.runEpoch(input, output);
                if (epoch % 1000 == 2) {
                    sError.getData().add(new XYChart.Data<>(epoch, error));
                }
                epoch++;
                if (error <= 0.01) {
                    needstop = true;
                }

                if (epoch > 1_500_000) {
                    needstop = true;
                    fail = true;
                }
            }

            areaChart.getData().add(sError);

            float[] inputData1 = {0.0f, 0.0f};
            float[] inputData2 = {0.0f, 1.0f};
            float[] inputData3 = {1.0f, 0.0f};
            float[] inputData4 = {1.0f, 1.0f};

            float[] result1 = network.compute(inputData1);
            float[] result2 = network.compute(inputData2);
            float[] result3 = network.compute(inputData3);
            float[] result4 = network.compute(inputData4);

            if (fail) {
                result.setText("Can't converge learning .. :(");
            } else {
                result.setText(String.format("Result of XOR>> [00,00: %f] [00,01: %f] [10,00: %f] [10,10 %f]", result1[0],
                        result2[0], result3[0], result4[0]));
            }


        });


        BorderPane root = new BorderPane();
        root.setCenter(areaChart);
        root.setBottom(box1);
        root.setLeft(vbox1);

        primaryStage.setScene(new Scene(root, 800, 400));
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}

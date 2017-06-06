package org.openjml.samplesfx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.openjml.fuzzy.LinguisticVariable;
import org.openjml.fuzzy.functions.EdgeType;
import org.openjml.fuzzy.functions.TrapezoidalFunction;
import org.openjml.fuzzy.Set;

/**
 * This sample draws in a area chart all fuzzy set.
 * Created by jgardona on 21/05/17.
 */
@SuppressWarnings("unchecked")
public class FuzzySet extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("FuzzySet Sample");

        NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("Temperature");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Membership");

        AreaChart areaChart = new AreaChart<>(xAxis, yAxis);

        XYChart.Series sCold = new XYChart.Series();
        sCold.setName("Cold");
        XYChart.Series sCool = new XYChart.Series();
        sCool.setName("Cool");
        XYChart.Series sWarm = new XYChart.Series();
        sWarm.setName("Warm");
        XYChart.Series sHot = new XYChart.Series();
        sHot.setName("Hot");

        TrapezoidalFunction function1 = new TrapezoidalFunction(10, 15, EdgeType.RIGHT);
        Set cold = new Set("Cold", function1);
        TrapezoidalFunction function2 = new TrapezoidalFunction(10, 15, 20, 25);
        Set cool = new Set("Cool", function2);
        TrapezoidalFunction function3 = new TrapezoidalFunction(20, 25, 30, 35);
        Set warm = new Set("Warm", function3);
        TrapezoidalFunction function4 = new TrapezoidalFunction(30, 35, EdgeType.LEFT);
        Set hot = new Set("Hot", function4);

        LinguisticVariable variable = new LinguisticVariable("Temperature", 0, 80);

        variable.add(cold);
        variable.add(cool);
        variable.add(warm);
        variable.add(hot);

        for (float i = 0; i < 80; i+= 3.3F) {
            float dcold = variable.membership("Cold", i);
            float dcool = variable.membership("Cool", i);
            float dwarm = variable.membership("Warm", i);
            float dhot = variable.membership("Hot", i);

            sCold.getData().add(new XYChart.Data<>(i, dcold));
            sCool.getData().add(new XYChart.Data<>(i, dcool));
            sWarm.getData().add(new XYChart.Data<>(i, dwarm));
            sHot.getData().add(new XYChart.Data<>(i, dhot));
        }

        areaChart.getData().add(sCold);
        areaChart.getData().add(sCool);
        areaChart.getData().add(sWarm);
        areaChart.getData().add(sHot);

        StackPane root = new StackPane(areaChart);
        primaryStage.setScene(new Scene(root, 600, 300));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

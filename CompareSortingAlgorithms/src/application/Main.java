package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

//All the algorithms take a while to finish running before we can plot,
//So the application prints the results to console as well so you can see the current progress
//Once all the algorithms have finished running, the window with the plot will show up
public class Main extends Application {

  @Override
  public void start(Stage stage) {
    final NumberAxis xAxis = new NumberAxis();
    xAxis.setLowerBound(40000);
    final NumberAxis yAxis = new NumberAxis();
    xAxis.setLabel("Array Size");
    yAxis.setLabel("Time in millisecond(s)");
    final LineChart<Number, Number> lineChart = new LineChart<Number, Number>(
        xAxis, yAxis);

    BorderPane root = new BorderPane();
    
    lineChart.setTitle("Time Complexity Comparison for Various Sorting Algorithms");
    
    //Run all the algorithms and record their times in arraylists
    Test.testAlgs();
    
    XYChart.Series<Number, Number> insertionSort = new XYChart.Series<Number, Number>();
    insertionSort.setName("Insertion Sort");
    insertionSort.getData().add(new XYChart.Data<Number, Number>(50000, Test.insertionS.get(0)));
    insertionSort.getData().add(new XYChart.Data<Number, Number>(100000, Test.insertionS.get(1)));
    insertionSort.getData().add(new XYChart.Data<Number, Number>(150000, Test.insertionS.get(2)));
    insertionSort.getData().add(new XYChart.Data<Number, Number>(200000, Test.insertionS.get(3)));
    insertionSort.getData().add(new XYChart.Data<Number, Number>(250000, Test.insertionS.get(4)));
    insertionSort.getData().add(new XYChart.Data<Number, Number>(300000, Test.insertionS.get(5)));
    
    XYChart.Series<Number, Number> bubbleSort = new XYChart.Series<Number, Number>();
    bubbleSort.setName("Bubble Sort");
    bubbleSort.getData().add(new XYChart.Data<Number, Number>(50000, Test.bubbleS.get(0)));
    bubbleSort.getData().add(new XYChart.Data<Number, Number>(100000, Test.bubbleS.get(1)));
    bubbleSort.getData().add(new XYChart.Data<Number, Number>(150000, Test.bubbleS.get(2)));
    bubbleSort.getData().add(new XYChart.Data<Number, Number>(200000, Test.bubbleS.get(3)));
    bubbleSort.getData().add(new XYChart.Data<Number, Number>(250000, Test.bubbleS.get(4)));
    bubbleSort.getData().add(new XYChart.Data<Number, Number>(300000, Test.bubbleS.get(5)));
    
    XYChart.Series<Number, Number> selectionSort = new XYChart.Series<Number, Number>();
    selectionSort.setName("Selection Sort");
    selectionSort.getData().add(new XYChart.Data<Number, Number>(50000, Test.selectionS.get(0)));
    selectionSort.getData().add(new XYChart.Data<Number, Number>(100000, Test.selectionS.get(1)));
    selectionSort.getData().add(new XYChart.Data<Number, Number>(150000, Test.selectionS.get(2)));
    selectionSort.getData().add(new XYChart.Data<Number, Number>(200000, Test.selectionS.get(3)));
    selectionSort.getData().add(new XYChart.Data<Number, Number>(250000, Test.selectionS.get(4)));
    selectionSort.getData().add(new XYChart.Data<Number, Number>(300000, Test.selectionS.get(5)));
    
    XYChart.Series<Number, Number> mergeSort = new XYChart.Series<Number, Number>();
    mergeSort.setName("Merge Sort");
    mergeSort.getData().add(new XYChart.Data<Number, Number>(50000, Test.mergeS.get(0)));
    mergeSort.getData().add(new XYChart.Data<Number, Number>(100000, Test.mergeS.get(1)));
    mergeSort.getData().add(new XYChart.Data<Number, Number>(150000, Test.mergeS.get(2)));
    mergeSort.getData().add(new XYChart.Data<Number, Number>(200000, Test.mergeS.get(3)));
    mergeSort.getData().add(new XYChart.Data<Number, Number>(250000, Test.mergeS.get(4)));
    mergeSort.getData().add(new XYChart.Data<Number, Number>(300000, Test.mergeS.get(5)));
    
    XYChart.Series<Number, Number> quickSort = new XYChart.Series<Number, Number>();
    quickSort.setName("Quick Sort");
    quickSort.getData().add(new XYChart.Data<Number, Number>(50000, Test.quickS.get(0)));
    quickSort.getData().add(new XYChart.Data<Number, Number>(100000, Test.quickS.get(1)));
    quickSort.getData().add(new XYChart.Data<Number, Number>(150000, Test.quickS.get(2)));
    quickSort.getData().add(new XYChart.Data<Number, Number>(200000, Test.quickS.get(3)));
    quickSort.getData().add(new XYChart.Data<Number, Number>(250000, Test.quickS.get(4)));
    quickSort.getData().add(new XYChart.Data<Number, Number>(300000, Test.quickS.get(5)));
    
    XYChart.Series<Number, Number> countingSort = new XYChart.Series<Number, Number>();
    countingSort.setName("Counting Sort");
    countingSort.getData().add(new XYChart.Data<Number, Number>(50000, Test.countingS.get(0)));
    countingSort.getData().add(new XYChart.Data<Number, Number>(100000, Test.countingS.get(1)));
    countingSort.getData().add(new XYChart.Data<Number, Number>(150000, Test.countingS.get(2)));
    countingSort.getData().add(new XYChart.Data<Number, Number>(200000, Test.countingS.get(3)));
    countingSort.getData().add(new XYChart.Data<Number, Number>(250000, Test.countingS.get(4)));
    countingSort.getData().add(new XYChart.Data<Number, Number>(300000, Test.countingS.get(5)));
    
    XYChart.Series<Number, Number> radixSort = new XYChart.Series<Number, Number>();
    radixSort.setName("Radix Sort");
    radixSort.getData().add(new XYChart.Data<Number, Number>(50000, Test.radixS.get(0)));
    radixSort.getData().add(new XYChart.Data<Number, Number>(100000, Test.radixS.get(1)));
    radixSort.getData().add(new XYChart.Data<Number, Number>(150000, Test.radixS.get(2)));
    radixSort.getData().add(new XYChart.Data<Number, Number>(200000, Test.radixS.get(3)));
    radixSort.getData().add(new XYChart.Data<Number, Number>(250000, Test.radixS.get(4)));
    radixSort.getData().add(new XYChart.Data<Number, Number>(300000, Test.radixS.get(5)));
    
    
    root.setCenter(lineChart);
    root.setMargin(lineChart, new Insets(40));
    
    Scene scene = new Scene(root);
    lineChart.getData().addAll(insertionSort, bubbleSort, selectionSort, mergeSort, quickSort, countingSort, radixSort);
    
    stage.setScene(scene);
    stage.setWidth(1920);
    stage.setHeight(1080);
    stage.setTitle("Time Complexity Graph");
    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
package examples.sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(ResourceUtils.getURL("classpath:RootLayout.fxml"));
//        Parent root = FXMLLoader.load(ResourceUtils.getURL("classpath:PersonOverview.fxml"));
//        Parent root = FXMLLoader.load(ResourceUtils.getURL("classpath:UI.fxml"));
        String s = new String("123");
        s.intern();
        primaryStage.setTitle("Hello World");
        Scene scene = new Scene(root, 300, 275);
//        Scene scene = new Scene(new Browser(),750,500);
        scene.getStylesheets().add(ResourceUtils.getURL("classpath:DarkTheme.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

class Browser extends Region {

    final WebView browser = new WebView();
    final WebEngine webEngine = browser.getEngine();

    public Browser() {
        //apply the styles
        try {
            getStyleClass().add(ResourceUtils.getURL("classpath:DarkTheme.css").toExternalForm());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // load the web page
        try {
            webEngine.load(ResourceUtils.getURL("classpath:FormTest.html").toExternalForm());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
//        webEngine.load("http://www.baidu.com");

        //add the web view to the scene
        getChildren().add(browser);

    }
    private Node createSpacer() {
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        return spacer;
    }

    @Override protected void layoutChildren() {
        double w = getWidth();
        double h = getHeight();
        layoutInArea(browser,0,0,w,h,0, HPos.CENTER, VPos.CENTER);
    }

    @Override protected double computePrefWidth(double height) {
        return 750;
    }

    @Override protected double computePrefHeight(double width) {
        return 500;
    }
}

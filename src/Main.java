import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;
import javafx.stage.Stage;

import java.util.List;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    final static int[] fontSize = {18};
    final static String[] fontFamily = {"arial"};
    final static String[] fontStyle = {"normal"};
    final static String[] fontWeight = {"normal"};

    final static int[] radius = {150};

    @Override
    public void start(Stage primaryStage) throws Exception {


        String defaultText = "Default text";


        //Define borderpane
        BorderPane pane = new BorderPane();
        pane.getStylesheets().add("styles.css");
        //Middle elements
        VBox vBox1 = new VBox();
        HBox hBox1 = new HBox();
        StackPane objectPane = new StackPane();

        //Create circle object
        Circle circle = new Circle(radius[0]);
        circle.setAccessibleText(defaultText);
        circle.setStroke(Color.BLUE);
        circle.setStrokeType(StrokeType.INSIDE);
        circle.setStrokeWidth(3);

        circle.setFill(Color.GREEN);

        //Create text object
        Text text = new Text("Default text");
        text.setFill(Color.BLUE);
        text.setStyle(String.format("-fx-font-size: %d; -fx-font-family: %s", fontSize[0], fontFamily[0]));
        text.setBoundsType(TextBoundsType.VISUAL);


        objectPane.getChildren().addAll(circle,text); //Add elements to StackPane

        hBox1.getChildren().add(objectPane);

        //Set parents properties
        hBox1.setAlignment(Pos.CENTER);
        hBox1.setStyle("-fx-border-color: grey; -fx-border-width: 2; -fx-border-style: solid inside; -fx-background-color: lightgrey");
        hBox1.setPrefSize(500,500);
        hBox1.setMaxSize(500,500);
        vBox1.setAlignment(Pos.TOP_CENTER);

        //Pane properties
        pane.setPadding(new Insets(70,20,20,20));

        //Add elements to parents
        vBox1.getChildren().addAll(hBox1);
        pane.setTop(vBox1);

        //Bottom elements
        VBox vBox2 = new VBox();


        HBox hBox2 = new HBox(); //Text options

        Label title1 = new Label("Text options");
        title1.getStyleClass().add("editTitleLabel");

        //Font size property
        VBox vBox3 = new VBox(); //Main element local
        Label fontSizeLabel = new Label("Font size");
        fontSizeLabel.getStyleClass().add("editLabel");
        Label fontSizeSelectionLabel = new Label("" + fontSize[0]);
        fontSizeSelectionLabel.setStyle("-fx-font-size: 22;-fx-font-weight: bold");
        HBox hBox3 = new HBox();
        hBox3.setAlignment(Pos.CENTER);
        hBox3.setSpacing(30);
        VBox vBox4 = new VBox();
        Button fontSizeUp = new Button("Up");
        fontSizeUp.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            fontSize[0]++;
            fontSizeSelectionLabel.setText(String.format("%d", fontSize[0]));
            setFontEdit(text);
        });
        fontSizeUp.getStyleClass().add("sizeButton");
        Button fontSizeDown = new Button("Down");
        fontSizeDown.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            fontSize[0]--;
            fontSizeSelectionLabel.setText(String.format("%d", fontSize[0]));
            setFontEdit(text);
        });
        fontSizeDown.getStyleClass().add("sizeButton");

        vBox4.getChildren().addAll(fontSizeUp,fontSizeDown);
        hBox3.getChildren().addAll(fontSizeSelectionLabel,vBox4);
        vBox3.getChildren().addAll(fontSizeLabel,hBox3);
        vBox3.getStyleClass().add("textEditBox");

        //Font family ComboBox Selector
        VBox vBox5 = new VBox();
        Label fontFamilyLabel = new Label("Font family");
        fontFamilyLabel.getStyleClass().add("editLabel");
        List<String> families = Font.getFamilies();
        vBox5.setSpacing(17);
        ComboBox familiesComboBox = new ComboBox(FXCollections.observableList(families));
        familiesComboBox.setOnAction(event -> {
                fontFamily[0] = familiesComboBox.getSelectionModel().getSelectedItem().toString();
            setFontEdit(text);
        });
        vBox5.getChildren().addAll(fontFamilyLabel,familiesComboBox);
        vBox5.getStyleClass().add("textEditBox");

        //Font style Checkbox Selector
        VBox vBox6 = new VBox();
        Label fontStyleLabel = new Label("Font style");
        fontStyleLabel.getStyleClass().add("editLabel");
        HBox hBox4 = new HBox();
        CheckBox boldBox = new CheckBox("Bold"); //ADD FUNCTION TO CHECKBOX
        boldBox.setOnAction((event -> {
            if (boldBox.isSelected()) {
                fontWeight[0] = "bold";
            } else {
                fontWeight[0] = "normal";
            }
            setFontEdit(text);
        }));
        CheckBox italicBox = new CheckBox("Italic"); //ADD FUNCTION TO CHECKBOX
        italicBox.setOnAction(event -> {
            if (italicBox.isSelected()) {
                fontStyle[0] = "italic";
            } else {
                fontStyle[0] = "normal";
            }
            setFontEdit(text);
        });

        hBox4.setSpacing(10);
        hBox4.getChildren().addAll(boldBox,italicBox);
        hBox4.setAlignment(Pos.CENTER);
        vBox6.getChildren().addAll(fontStyleLabel,hBox4);
        vBox6.setSpacing(18);
        vBox6.getStyleClass().add("textEditBox");

        //Font color
        VBox vBox7 = new VBox();
        Label fontColorLabel = new Label("Font color");
        fontColorLabel.getStyleClass().add("editLabel");
        final ColorPicker fontColorPicker = new ColorPicker();
        fontColorPicker.setOnAction(event -> {
            text.setFill(fontColorPicker.getValue());
        });

        vBox7.getChildren().addAll(fontColorLabel,fontColorPicker);
        vBox7.setSpacing(16);
        vBox7.getStyleClass().add("textEditBox");



        HBox hBox5 = new HBox(); //Circle properties

        Label title2 = new Label("Circle options");
        title2.getStyleClass().add("editTitleLabel");

        //Circle radius edit
        VBox vBox8 = new VBox();
        Label shapeRadiusLabel = new Label("Circle radius");
        shapeRadiusLabel.getStyleClass().add("editLabel");
        Label shapeRadiusSelectionLabel = new Label("1");
        shapeRadiusSelectionLabel.setStyle("-fx-font-size: 22;-fx-font-weight: bold");
        HBox hBox6 = new HBox();
        hBox6.setAlignment(Pos.CENTER);
        hBox6.setSpacing(30);
        VBox vBox9 = new VBox();
        Button radiusSizeUp = new Button("Up"); //ADD FUNCTION TO BUTTON
        radiusSizeUp.setOnAction(event -> {
            radius[0]++;
            shapeRadiusSelectionLabel.setText(String.format("%d", radius[0]));
            circle.setRadius(radius[0]);
        });
        radiusSizeUp.getStyleClass().add("sizeButton");
        Button radiusSizeDown = new Button("Down");  //ADD FUNCTION TO BUTTON
        radiusSizeDown.setOnAction(event -> {
            radius[0]--;
            shapeRadiusSelectionLabel.setText(String.format("%d", radius[0]));
            circle.setRadius(radius[0]);
        });
        radiusSizeDown.getStyleClass().add("sizeButton");
        vBox9.getChildren().addAll(radiusSizeUp,radiusSizeDown);
        hBox6.getChildren().addAll(shapeRadiusSelectionLabel,vBox9);
        vBox8.getChildren().addAll(shapeRadiusLabel,hBox6);
        vBox8.getStyleClass().add("textEditBox");

        //Circle Stroke Color edit
        VBox vBox10 = new VBox();
        Label shapeColorLabel = new Label("Circle border");
        shapeColorLabel.getStyleClass().add("editLabel");
        final ColorPicker shapeColorPicker = new ColorPicker();
        shapeColorPicker.setOnAction(event -> {
            circle.setStroke(shapeColorPicker.getValue());
        });

        vBox10.getChildren().addAll(shapeColorLabel,shapeColorPicker);
        vBox10.setSpacing(16);
        vBox10.getStyleClass().add("textEditBox");

        //Circle Fill Color edit
        VBox vBox11 = new VBox();
        Label shapeColorFillLabel = new Label("Circle fill");
        shapeColorFillLabel.getStyleClass().add("editLabel");
        final ColorPicker shapeFillColorPicker = new ColorPicker();
        shapeFillColorPicker.setOnAction(event -> {
            circle.setFill(shapeFillColorPicker.getValue());
        });
        vBox11.getChildren().addAll(shapeColorFillLabel,shapeFillColorPicker);
        vBox11.setSpacing(16);
        vBox11.getStyleClass().add("textEditBox");


        //Assign all elements bottom pane
        hBox2.getChildren().addAll(vBox3,vBox5, vBox6, vBox7);
        hBox2.getStyleClass().add("editHBox");
        hBox5.getChildren().addAll(vBox8,vBox10, vBox11);
        hBox5.getStyleClass().add("editHBox");

        vBox2.getChildren().addAll(title1,hBox2,title2, hBox5);


        //Bottom vbox properties
        vBox2.setPrefSize(500,450);
        vBox2.setSpacing(20);
        vBox2.setPadding(new Insets(10));


        pane.setBottom(vBox2);
        Scene scene = new Scene(pane, 1000,1000);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private static void setFontEdit(Text text) {
        text.setStyle(String.format("-fx-font-family: %s; -fx-font-size: %d; -fx-font-weight: %s; -fx-font-style: %s",fontFamily[0],fontSize[0], fontWeight[0],fontStyle[0]));
    }
}

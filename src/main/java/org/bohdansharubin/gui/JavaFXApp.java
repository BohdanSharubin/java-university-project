package org.bohdansharubin.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.bohdansharubin.gui.controllers.ClothesController;
import org.bohdansharubin.models.Clothes;
import org.bohdansharubin.services.ClothesService;
import org.bohdansharubin.utils.Deserializer;
import org.bohdansharubin.utils.Serializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JavaFXApp extends Application {
    private static ClothesService clothesService;
    private static final String FILENAME = "input.txt";

    static {
        clothesService = new ClothesService(loadFromDisk(FILENAME));
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/main.fxml")
        );

        Parent root = loader.load();

        ClothesController controller = loader.getController();
        controller.setClothesService(clothesService);

        stage.setTitle("Clothes Manager");
        stage.setScene(new Scene(root, 600, 700));
        stage.setOnCloseRequest(event -> {
            try {
                Serializer.saveObject(clothesService.getClothesList(), FILENAME);
                System.out.println("Clothes saved to " + FILENAME);
            } catch (IOException e) {
                System.err.println("Error saving clothes: " + e.getMessage());
            }
        });
        stage.show();
    }

    public static void main(String[] args) throws IOException {
        launch(args);
    }

    /**
     * Loads clothes data from file.
     *
     * @param filename name of the file containing serialized clothes list
     * @return list of clothes if file exists and is valid, otherwise empty list
     */
    public static List<Clothes> loadFromDisk(String filename) {
        try {
            List<Clothes> list = (List<Clothes>) Deserializer.loadObject(filename);
            System.out.println("Loaded " + list.size() + " clothes");
            return list;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Can't find file " + filename);
        }
        return new ArrayList<>();
    }
}

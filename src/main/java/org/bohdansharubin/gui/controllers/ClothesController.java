package org.bohdansharubin.gui.controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import org.bohdansharubin.ClothesFactory;
import org.bohdansharubin.enums.AmericanSize;
import org.bohdansharubin.enums.ClothesType;
import org.bohdansharubin.models.Clothes;
import org.bohdansharubin.services.ClothesService;

import java.util.Optional;
import java.util.UUID;

public class ClothesController {

    @FXML private ComboBox<ClothesType> typeBox;
    @FXML
    private TextField colorField;
    @FXML private ComboBox<AmericanSize> americanSizeBox;
    @FXML private TextField europeanSizeField;

    @FXML private TableView<Clothes> collectionTable;

    @FXML private TableColumn<Clothes, String> uuidColumn;
    @FXML private TableColumn<Clothes, String> colorColumn;
    @FXML private TableColumn<Clothes, String> typeColumn;

    @FXML private TextField uuidField;
    @FXML private TextArea detailsArea;
    private ClothesService clothesService;

    public void setClothesService(ClothesService clothesService) {
        this.clothesService = clothesService;
        collectionTable.setItems(FXCollections.observableArrayList(clothesService.getClothesList()));
    }

    @FXML
    private void handleAdd() {
        try {
            ClothesType type = typeBox.getValue();
            String color = colorField.getText();
            AmericanSize size = americanSizeBox.getValue();
            int euro = Integer.parseInt(europeanSizeField.getText());

            Clothes clothes = ClothesFactory.createClothes(color, type, euro, size);
            clothesService.addClothes(clothes);
            collectionTable.getItems().setAll(clothesService.getClothesList());
            clearFields();

        } catch (Exception e) {
            showError("Invalid input");
        }
    }

    @FXML
    private void handleFind() {
        try {
            UUID uuid = UUID.fromString(uuidField.getText());

            Optional<Clothes> result = clothesService.findClothesByUuid(uuid);

            if (result.isPresent()) {
                Clothes c = result.get();
                detailsArea.setText(
                        "UUID: " + c.getUuid() + "\n" +
                                "Type: " + c.getType() + "\n" +
                                "Color: " + c.getColor() + "\n" +
                                "American size: " + c.getAmericanSize() + "\n" +
                                "European size: " + c.getEuropeanSize()
                );
            } else {
                detailsArea.setText("Object not found");
            }

        } catch (IllegalArgumentException e) {
            showError("Invalid UUID format");
        }
    }
    @FXML
    private void handleRefresh() {
        collectionTable.getItems().setAll(clothesService.getClothesList());
    }

    @FXML
    public void initialize() {

        typeBox.getItems().addAll(ClothesType.values());
        americanSizeBox.getItems().addAll(AmericanSize.values());

        uuidColumn.setCellValueFactory(cell ->
                new SimpleStringProperty(cell.getValue().getUuid().toString()));

        colorColumn.setCellValueFactory(cell ->
                new SimpleStringProperty(cell.getValue().getColor()));

        typeColumn.setCellValueFactory(cell ->
                new SimpleStringProperty(cell.getValue().getType().toString()));
        collectionTable.getSelectionModel().setCellSelectionEnabled(true);

        collectionTable.setRowFactory(tv -> {
            TableRow<Clothes> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getClickCount() == 1) {
                    Clothes clicked = row.getItem();
                    ClipboardContent content = new ClipboardContent();
                    content.putString(clicked.getUuid().toString());
                    Clipboard.getSystemClipboard().setContent(content);
                }
            });
            return row;
        });
    }

    private void clearFields() {
        colorField.clear();
        europeanSizeField.clear();
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message);
        alert.showAndWait();
    }
}

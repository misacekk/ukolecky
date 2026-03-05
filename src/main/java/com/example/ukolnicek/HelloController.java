package com.example.ukolnicek;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class HelloController {

    @FXML
    private TextField taskInput;

    @FXML
    private ListView<Ukol> taskListView;

    @FXML
    private ToggleGroup subjectGroup;

    @FXML
    private CheckBox splnenoCheck;

    @FXML
    public void initialize() {
        taskListView.getItems().add(new Ukol("Naučit se JavaFX", "PRG", false));
        taskListView.getItems().add(new Ukol("Slovíčka", "AJ", true));
    }

    @FXML
    protected void addUkol() {
        String text = taskInput.getText();
        RadioButton selectedRB = (RadioButton) subjectGroup.getSelectedToggle();

        if (text != null && !text.trim().isEmpty() && selectedRB != null) {
            String predmet = selectedRB.getText();
            boolean jeSplneno = splnenoCheck.isSelected();

            Ukol novyUkol = new Ukol(text, predmet, jeSplneno);
            taskListView.getItems().add(novyUkol);

            taskInput.clear();
            splnenoCheck.setSelected(false);
        }
    }

    @FXML
    private void removeUkol() {
        int index = taskListView.getSelectionModel().getSelectedIndex();
        if (index >= 0) {
            taskListView.getItems().remove(index);
        }
    }

    @FXML
    private void ulozitZmeny() {
        int index = taskListView.getSelectionModel().getSelectedIndex();
        String novyText = taskInput.getText();
        RadioButton selectedRB = (RadioButton) subjectGroup.getSelectedToggle();

        if (index >= 0 && !novyText.trim().isEmpty() && selectedRB != null) {
            Ukol ukol = taskListView.getItems().get(index);
            ukol.setNazev(novyText);
            ukol.setPredmet(selectedRB.getText());
            ukol.setSplneno(splnenoCheck.isSelected());

            taskListView.refresh();
            taskInput.clear();
            taskListView.getSelectionModel().clearSelection();
        }
    }
}
package com.example.ukolnicek;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class HelloController {

    @FXML
    private TextField taskInput, searchField;

    @FXML
    private ListView<Ukol> taskListView;

    @FXML
    private ToggleGroup subjectGroup;

    @FXML
    private CheckBox splnenoCheck;

    @FXML
    private Label nazevLabel, predmetLabel, splnenoLabel;

    private final ObservableList<Ukol> vsechnyUkoly = FXCollections.observableArrayList();

    private FilteredList<Ukol> filtrovaneUkoly;

    @FXML
    public void initialize() {
        vsechnyUkoly.add(new Ukol("Java test", "PRG", false));
        vsechnyUkoly.add(new Ukol("Cover letter", "AJ", true));

        filtrovaneUkoly = new FilteredList<>(vsechnyUkoly, p -> true);

        taskListView.setItems(filtrovaneUkoly);
    }

    @FXML
    protected void addUkol() {
        String text = taskInput.getText();
        RadioButton selectedRB = (RadioButton) subjectGroup.getSelectedToggle();

        if (text != null && !text.trim().isEmpty() && selectedRB != null) {
            String predmet = selectedRB.getText();
            boolean jeSplneno = splnenoCheck.isSelected();

            Ukol novyUkol = new Ukol(text, predmet, jeSplneno);
            vsechnyUkoly.add(novyUkol);

            taskInput.clear();
            splnenoCheck.setSelected(false);
        }
    }

    @FXML
    private void removeUkol() {
        Ukol vybrany = taskListView.getSelectionModel().getSelectedItem();
        if (vybrany != null) {
            vsechnyUkoly.remove(vybrany);
        }
    }

    @FXML
    private void ulozitZmeny() {
        Ukol vybrany = taskListView.getSelectionModel().getSelectedItem();
        String novyText = taskInput.getText();
        RadioButton selectedRB = (RadioButton) subjectGroup.getSelectedToggle();

        if (vybrany != null && !novyText.trim().isEmpty() && selectedRB != null) {
            vybrany.setNazev(novyText);
            vybrany.setPredmet(selectedRB.getText());
            vybrany.setSplneno(splnenoCheck.isSelected());

            taskListView.refresh();
            taskInput.clear();
            taskListView.getSelectionModel().clearSelection();
        }
    }

    @FXML
    private void zobrazDetail() {
        Ukol vybrany = taskListView.getSelectionModel().getSelectedItem();
        if (vybrany != null) {
            nazevLabel.setText("Název: " + vybrany.getNazev());
            predmetLabel.setText("Předmět: " + vybrany.getPredmet());
            splnenoLabel.setText("Splněno?: " + vybrany.isSplneno());
        }
    }

    @FXML
    private void zobrazVse() {
        taskListView.setItems(vsechnyUkoly);
    }

    @FXML
    private void zobrazSplnene() {
        ObservableList<Ukol> splnene = FXCollections.observableArrayList();

        for (Ukol ukol : vsechnyUkoly) {
            if (ukol.isSplneno()) {
                splnene.add(ukol);
            }
        }
        taskListView.setItems(splnene);
    }

    @FXML
    private void zobrazNesplnene() {
        ObservableList<Ukol> nesplnene = FXCollections.observableArrayList();

        for (Ukol ukol : vsechnyUkoly) {
            if (!ukol.isSplneno()) {
                nesplnene.add(ukol);
            }
        }
        taskListView.setItems(nesplnene);
    }


    @FXML
    private void hledejUkol() {
        String hledane = searchField.getText().toLowerCase();
        filtrovaneUkoly.setPredicate(ukol -> {
            if (hledane.isEmpty()) return true;
            return ukol.getNazev().toLowerCase().contains(hledane);
        });
    }

    @FXML
    private void konec() {
        javafx.application.Platform.exit();
    }

    @FXML
    private void nacist() {
        taskListView.refresh();
    }

    @FXML
    private void ulozit() {
        taskListView.refresh();
    }
}
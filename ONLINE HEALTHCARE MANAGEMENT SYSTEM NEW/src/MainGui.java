//import javafx.application.Application;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.geometry.Insets;
//import javafx.scene.Scene;
//import javafx.scene.control.*;
//import javafx.scene.control.cell.PropertyValueFactory;
//import javafx.scene.layout.GridPane;
//import javafx.scene.layout.VBox;
//import javafx.stage.Stage;
//import model.Appointment;
//import model.Doctor;
//import model.Patient;
//import service.BookingService;
//import service.BookingServiceImpl;
//import service.DoctorService;
//import service.DoctorServiceImpl;
//import service.PatientService;
//import service.PatientServiceImpl;
//import exception.BookingException;
//
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.List;
//
//public class MainGui extends Application {
//
//    private final DoctorService doctorService = new DoctorServiceImpl();
//    private final PatientService patientService = new PatientServiceImpl();
//    private final BookingService bookingService = new BookingServiceImpl();
//
//    private final ObservableList<Doctor> doctorObservable = FXCollections.observableArrayList();
//    private final ObservableList<Patient> patientObservable = FXCollections.observableArrayList();
//    private final ObservableList<AppointmentRow> appointmentObservable = FXCollections.observableArrayList();
//
//    private TableView<AppointmentRow> appointmentTable;
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//
//    @Override
//    public void start(Stage primaryStage) {
//        primaryStage.setTitle("Online Healthcare Management System - GUI");
//
//        TabPane tabPane = new TabPane();
//
//        Tab addDoctorTab = new Tab("Add Doctor", createAddDoctorPane());
//        Tab addPatientTab = new Tab("Add Patient", createAddPatientPane());
//        Tab bookAppointmentTab = new Tab("Book Appointment", createBookAppointmentPane());
//        Tab viewAppointmentsTab = new Tab("View Appointments", createViewAppointmentsPane());
//
//        addDoctorTab.setClosable(false);
//        addPatientTab.setClosable(false);
//        bookAppointmentTab.setClosable(false);
//        viewAppointmentsTab.setClosable(false);
//
//        tabPane.getTabs().addAll(addDoctorTab, addPatientTab, bookAppointmentTab, viewAppointmentsTab);
//
//        // populate observable lists from services (if any existing)
//        doctorObservable.addAll(doctorService.getAllDoctors());
//        patientObservable.addAll(patientService.getAllPatients());
//        refreshAppointments();
//
//        Scene scene = new Scene(tabPane, 800, 600);
//        primaryStage.setScene(scene);
//        primaryStage.show();
//    }
//
//    private VBox createAddDoctorPane() {
//        VBox vbox = new VBox(10);
//        vbox.setPadding(new Insets(16));
//
//        TextField nameField = new TextField();
//        nameField.setPromptText("Doctor name");
//
//        TextField specField = new TextField();
//        specField.setPromptText("Specialization");
//
//        Button addBtn = new Button("Add Doctor");
//        addBtn.setOnAction(e -> {
//            String name = nameField.getText().trim();
//            String spec = specField.getText().trim();
//            if (name.isEmpty() || spec.isEmpty()) {
//                showAlert(Alert.AlertType.WARNING, "Validation", "Please enter name and specialization.");
//                return;
//            }
//            Doctor d = new Doctor();
//            d.setName(name);
//            d.setSpecialization(spec);
//            doctorService.addDoctor(d);
//            doctorObservable.add(d);
//            nameField.clear();
//            specField.clear();
//            showAlert(Alert.AlertType.INFORMATION, "Success", "Doctor added with ID: " + d.getId());
//        });
//
//        vbox.getChildren().addAll(new Label("Add new doctor"), nameField, specField, addBtn);
//        return vbox;
//    }
//
//    private VBox createAddPatientPane() {
//        VBox vbox = new VBox(10);
//        vbox.setPadding(new Insets(16));
//
//        TextField nameField = new TextField();
//        nameField.setPromptText("Patient name");
//
//        TextField ageField = new TextField();
//        ageField.setPromptText("Age");
//
//        Button addBtn = new Button("Add Patient");
//        addBtn.setOnAction(e -> {
//            String name = nameField.getText().trim();
//            String ageText = ageField.getText().trim();
//            if (name.isEmpty() || ageText.isEmpty()) {
//                showAlert(Alert.AlertType.WARNING, "Validation", "Please enter name and age.");
//                return;
//            }
//            int age;
//            try {
//                age = Integer.parseInt(ageText);
//            } catch (NumberFormatException ex) {
//                showAlert(Alert.AlertType.ERROR, "Validation", "Age must be a number.");
//                return;
//            }
//            Patient p = new Patient();
//            p.setName(name);
//            p.setAge(age);
//            patientService.addPatient(p);
//            patientObservable.add(p);
//            nameField.clear();
//            ageField.clear();
//            showAlert(Alert.AlertType.INFORMATION, "Success", "Patient added with ID: " + p.getId());
//        });
//
//        vbox.getChildren().addAll(new Label("Add new patient"), nameField, ageField, addBtn);
//        return vbox;
//    }
//
//    private GridPane createBookAppointmentPane() {
//        GridPane grid = new GridPane();
//        grid.setPadding(new Insets(16));
//        grid.setHgap(10);
//        grid.setVgap(10);
//
//        Label patientLabel = new Label("Patient:");
//        ComboBox<Patient> patientCombo = new ComboBox<>(patientObservable);
//        patientCombo.setPrefWidth(300);
//        patientCombo.setCellFactory(cb -> new ListCell<>() {
//            @Override
//            protected void updateItem(Patient item, boolean empty) {
//                super.updateItem(item, empty);
//                setText(empty || item == null ? null : item.getId() + " - " + item.getName());
//            }
//        });
//        patientCombo.setButtonCell(new ListCell<>() {
//            @Override
//            protected void updateItem(Patient item, boolean empty) {
//                super.updateItem(item, empty);
//                setText(empty || item == null ? null : item.getId() + " - " + item.getName());
//            }
//        });
//
//        Label doctorLabel = new Label("Doctor:");
//        ComboBox<Doctor> doctorCombo = new ComboBox<>(doctorObservable);
//        doctorCombo.setPrefWidth(300);
//        doctorCombo.setCellFactory(cb -> new ListCell<>() {
//            @Override
//            protected void updateItem(Doctor item, boolean empty) {
//                super.updateItem(item, empty);
//                setText(empty || item == null ? null : item.getId() + " - " + item.getName() + " (" + item.getSpecialization() + ")");
//            }
//        });
//        doctorCombo.setButtonCell(new ListCell<>() {
//            @Override
//            protected void updateItem(Doctor item, boolean empty) {
//                super.updateItem(item, empty);
//                setText(empty || item == null ? null : item.getId() + " - " + item.getName());
//            }
//        });
//
//        Label hourLabel = new Label("Hour (0-23):");
//        TextField hourField = new TextField();
//        hourField.setPromptText("e.g. 14");
//
//        Button bookBtn = new Button("Book Appointment");
//        bookBtn.setOnAction(e -> {
//            Patient selectedPatient = patientCombo.getValue();
//            Doctor selectedDoctor = doctorCombo.getValue();
//            String hourText = hourField.getText().trim();
//            if (selectedPatient == null || selectedDoctor == null || hourText.isEmpty()) {
//                showAlert(Alert.AlertType.WARNING, "Validation", "Please select patient, doctor and hour.");
//                return;
//            }
//            int hour;
//            try {
//                hour = Integer.parseInt(hourText);
//                if (hour < 0 || hour > 23) throw new NumberFormatException();
//            } catch (NumberFormatException ex) {
//                showAlert(Alert.AlertType.ERROR, "Validation", "Hour must be integer between 0 and 23.");
//                return;
//            }
//
//            LocalDateTime when = LocalDateTime.now().withHour(hour).withMinute(0).withSecond(0).withNano(0);
//            try {
//                Appointment ap = bookingService.bookAppointment(selectedPatient.getId(), selectedDoctor.getId(), when);
//                showAlert(Alert.AlertType.INFORMATION, "Booked", "Appointment booked with ID: " + ap.getId());
//                refreshAppointments();
//            } catch (BookingException ex) {
//                showAlert(Alert.AlertType.ERROR, "Booking Error", ex.getMessage());
//            }
//        });
//
//        grid.add(patientLabel, 0, 0);
//        grid.add(patientCombo, 1, 0);
//        grid.add(doctorLabel, 0, 1);
//        grid.add(doctorCombo, 1, 1);
//        grid.add(hourLabel, 0, 2);
//        grid.add(hourField, 1, 2);
//        grid.add(bookBtn, 1, 3);
//
//        return grid;
//    }
//
//    private VBox createViewAppointmentsPane() {
//        VBox vbox = new VBox(10);
//        vbox.setPadding(new Insets(16));
//
//        appointmentTable = new TableView<>();
//        appointmentTable.setPrefHeight(450);
//
//        TableColumn<AppointmentRow, Integer> idCol = new TableColumn<>("ID");
//        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
//
//        TableColumn<AppointmentRow, Integer> doctorCol = new TableColumn<>("Doctor ID");
//        doctorCol.setCellValueFactory(new PropertyValueFactory<>("doctorId"));
//
//        TableColumn<AppointmentRow, Integer> patientCol = new TableColumn<>("Patient ID");
//        patientCol.setCellValueFactory(new PropertyValueFactory<>("patientId"));
//
//        TableColumn<AppointmentRow, String> timeCol = new TableColumn<>("Time");
//        timeCol.setCellValueFactory(new PropertyValueFactory<>("time"));
//
//        TableColumn<AppointmentRow, String> statusCol = new TableColumn<>("Status");
//        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
//
//        appointmentTable.getColumns().addAll(idCol, doctorCol, patientCol, timeCol, statusCol);
//        appointmentTable.setItems(appointmentObservable);
//
//        Button refreshBtn = new Button("Refresh");
//        refreshBtn.setOnAction(e -> refreshAppointments());
//
//        vbox.getChildren().addAll(new Label("All Appointments"), appointmentTable, refreshBtn);
//        return vbox;
//    }
//
//    private void refreshAppointments() {
//        appointmentObservable.clear();
//        try {
//            // get all appointments for each doctor (we'll gather from present doctors)
//            for (Doctor d : doctorService.getAllDoctors()) {
//                List<Appointment> apps = bookingService.getAppointmentsForDoctor(d.getId());
//                for (Appointment a : apps) {
//                    appointmentObservable.add(new AppointmentRow(a));
//                }
//            }
//            // also include any appointments that might belong to doctors not in doctorService list (edge cases)
//            // (The in-memory implementation stores and returns by doctor id; this is a simple approach.)
//        } catch (BookingException e) {
//            // show a non-blocking alert
//            System.err.println("Error while refreshing appointments: " + e.getMessage());
//        }
//    }
//
//    private void showAlert(Alert.AlertType type, String title, String message) {
//        Alert a = new Alert(type);
//        a.setTitle(title);
//        a.setHeaderText(null);
//        a.setContentText(message);
//        a.showAndWait();
//    }
//
//    // Small wrapper for TableView display
//    public static class AppointmentRow {
//        private final int id;
//        private final int doctorId;
//        private final int patientId;
//        private final String time;
//        private final String status;
//
//        private static final DateTimeFormatter F = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
//
//        public AppointmentRow(Appointment a) {
//            this.id = a.getId();
//            this.doctorId = a.getDoctorId();
//            this.patientId = a.getPatientId();
//            this.time = a.getAppointmentTime() == null ? "" : a.getAppointmentTime().format(F);
//            this.status = a.getStatus();
//        }
//
//        public int getId() { return id; }
//        public int getDoctorId() { return doctorId; }
//        public int getPatientId() { return patientId; }
//        public String getTime() { return time; }
//        public String getStatus() { return status; }
//    }
//}


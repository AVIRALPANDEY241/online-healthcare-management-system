//package com.healthcare;
//
//import com.healthcare.model.Doctor;
//import com.healthcare.model.Patient;
//import com.healthcare.model.Appointment;
//
//import com.healthcare.service.DoctorService;
//import com.healthcare.service.PatientService;
//import com.healthcare.service.BookingService;
//
//import com.healthcare.service.impl.DoctorServiceImpl;
//import com.healthcare.service.impl.PatientServiceImpl;
//import com.healthcare.service.impl.BookingServiceImpl;
//
//import com.healthcare.exception.BookingException;
//
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.Scanner;
//
//public class MainApp {
//
//    private final DoctorService doctorService;
//    private final PatientService patientService;
//    private final BookingService bookingService;
//
//    public MainApp() {
//        this.doctorService = new DoctorServiceImpl();
//        this.patientService = new PatientServiceImpl();
//        this.bookingService = new BookingServiceImpl();
//    }
//
//    public void start() {
//
//        Scanner sc = new Scanner(System.in);
//
//        while (true) {
//            System.out.println("\n===== ONLINE HEALTHCARE MANAGEMENT SYSTEM =====");
//            System.out.println("1. Add Doctor");
//            System.out.println("2. Add Patient");
//            System.out.println("3. Book Appointment");
//            System.out.println("4. View Doctor Appointments");
//            System.out.println("5. Exit");
//            System.out.print("Enter choice: ");
//
//            int choice = sc.nextInt();
//            sc.nextLine();
//
//            switch (choice) {
//
//                case 1 -> addDoctor(sc);
//                case 2 -> addPatient(sc);
//                case 3 -> bookAppointment(sc);
//                case 4 -> viewDoctorAppointments(sc);
//                case 5 -> {
//                    System.out.println("Exiting system. Goodbye!");
//                    System.exit(0);
//                }
//
//                default -> System.out.println("Invalid choice. Try again.");
//            }
//        }
//    }
//
//    // -------------------- METHODS --------------------
//
//    private void addDoctor(Scanner sc) {
//        System.out.print("Enter doctor name: ");
//        String name = sc.nextLine();
//
//        System.out.print("Enter specialization: ");
//        String spec = sc.nextLine();
//
//        Doctor doc = new Doctor();
//        doc.setName(name);
//        doc.setSpecialization(spec);
//
//        doctorService.addDoctor(doc);
//        System.out.println("Doctor added successfully!");
//    }
//
//    private void addPatient(Scanner sc) {
//        System.out.print("Enter patient name: ");
//        String name = sc.nextLine();
//
//        System.out.print("Enter age: ");
//        int age = sc.nextInt();
//        sc.nextLine();
//
//        Patient p = new Patient();
//        p.setName(name);
//        p.setAge(age);
//
//        patientService.addPatient(p);
//        System.out.println("Patient added successfully!");
//    }
//
//    private void bookAppointment(Scanner sc) {
//        try {
//            System.out.print("Enter patient ID: ");
//            int pid = sc.nextInt();
//
//            System.out.print("Enter doctor ID: ");
//            int did = sc.nextInt();
//
//            System.out.print("Enter appointment hour (0–23): ");
//            int hour = sc.nextInt();
//
//            LocalDateTime when = LocalDateTime.now()
//                    .withHour(hour)
//                    .withMinute(0);
//
//            Appointment ap = bookingService.bookAppointment(pid, did, when);
//
//            System.out.println("Appointment booked! Appointment ID: " + ap.getId());
//        } catch (BookingException e) {
//            System.out.println("ERROR: " + e.getMessage());
//        }
//    }
//
//    private void viewDoctorAppointments(Scanner sc) {
//
//        System.out.print("Enter doctor ID: ");
//        int did = sc.nextInt();
//
//        try {
//            List<Appointment> apps = bookingService.getAppointmentsForDoctor(did);
//
//            System.out.println("\n--- Doctor Appointments ---");
//            for (Appointment a : apps) {
//                System.out.println(
//                        "ID: " + a.getId() +
//                                " | Patient: " + a.getPatientId() +
//                                " | Time: " + a.getAppointmentTime() +
//                                " | Status: " + a.getStatus()
//                );
//            }
//
//        } catch (BookingException e) {
//            System.out.println("ERROR: " + e.getMessage());
//        }
//    }
//
//    // -------------------- MAIN METHOD --------------------
//
//    public static void main(String[] args) {
//        MainApp app = new MainApp();
//        app.start();
//    }
//}

package model;

import java.time.LocalDateTime;

public class Appointment {

    private int id;
    private int doctorId;
    private int patientId;
    private LocalDateTime appointmentTime;
    private String status = "Scheduled";

    private static int counter = 1;

    public Appointment() {
        this.id = counter++;
    }

    public int getId() { return id; }
    public int getDoctorId() { return doctorId; }
    public int getPatientId() { return patientId; }
    public LocalDateTime getAppointmentTime() { return appointmentTime; }
    public String getStatus() { return status; }

    public void setDoctorId(int doctorId) { this.doctorId = doctorId; }
    public void setPatientId(int patientId) { this.patientId = patientId; }
    public void setAppointmentTime(LocalDateTime appointmentTime) { this.appointmentTime = appointmentTime; }
    public void setStatus(String status) { this.status = status; }
}



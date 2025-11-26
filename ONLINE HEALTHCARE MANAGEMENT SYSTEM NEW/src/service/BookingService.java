package service;

import model.Appointment;
import exception.BookingException;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingService {

    Appointment bookAppointment(int patientId, int doctorId, LocalDateTime time)
            throws BookingException;

    List<Appointment> getAppointmentsForDoctor(int doctorId)
            throws BookingException;
}

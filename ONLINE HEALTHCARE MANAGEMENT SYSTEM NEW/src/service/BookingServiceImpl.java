package service;

import dao.AppointmentDao;
import impl.AppointmentDaoImpl;
import model.Appointment;
import exception.BookingException;

import java.time.LocalDateTime;
import java.util.List;

public class BookingServiceImpl implements BookingService {

    private final AppointmentDao appointmentDao = new AppointmentDaoImpl();

    public Appointment bookAppointment(int patientId, int doctorId, LocalDateTime time)
            throws BookingException {

        Appointment ap = new Appointment();
        ap.setDoctorId(doctorId);
        ap.setPatientId(patientId);
        ap.setAppointmentTime(time);

        appointmentDao.save(ap);

        return ap;
    }

    public List<Appointment> getAppointmentsForDoctor(int doctorId)
            throws BookingException {

        LocalDateTime start = LocalDateTime.now().minusYears(1);
        LocalDateTime end = LocalDateTime.now().plusYears(1);

        return appointmentDao.findByDoctorAndTime(doctorId, start, end);
    }
}


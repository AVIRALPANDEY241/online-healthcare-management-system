package dao;

import model.Appointment;
import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentDao extends GenericDao<Appointment> {
    List<Appointment> findByDoctorAndTime(int doctorId, LocalDateTime from, LocalDateTime to);
}


package impl;

import dao.AppointmentDao;
import model.Appointment;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDaoImpl implements AppointmentDao {

    private final List<Appointment> appointments = new ArrayList<>();

    public void save(Appointment ap) { appointments.add(ap); }

    public Appointment findById(int id) {
        return appointments.stream().filter(a -> a.getId() == id).findFirst().orElse(null);
    }

    public List<Appointment> findAll() { return appointments; }

    public List<Appointment> findByDoctorAndTime(int doctorId, LocalDateTime from, LocalDateTime to) {
        List<Appointment> result = new ArrayList<>();
        for (Appointment a : appointments) {
            if (a.getDoctorId() == doctorId
                    && !a.getAppointmentTime().isBefore(from)
                    && !a.getAppointmentTime().isAfter(to)) {
                result.add(a);
            }
        }
        return result;
    }
}

package impl;

import dao.DoctorDao;
import model.Doctor;

import java.util.ArrayList;
import java.util.List;

public class DoctorDaoImpl implements DoctorDao {

    private final List<Doctor> doctors = new ArrayList<>();

    public void save(Doctor d) { doctors.add(d); }

    public Doctor findById(int id) {
        return doctors.stream().filter(d -> d.getId() == id).findFirst().orElse(null);
    }

    public List<Doctor> findAll() { return doctors; }
}

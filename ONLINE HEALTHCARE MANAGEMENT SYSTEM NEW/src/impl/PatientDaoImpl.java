package impl;

import dao.PatientDao;
import model.Patient;

import java.util.ArrayList;
import java.util.List;

public class PatientDaoImpl implements PatientDao {

    private final List<Patient> patients = new ArrayList<>();

    public void save(Patient p) { patients.add(p); }

    public Patient findById(int id) {
        return patients.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }

    public List<Patient> findAll() { return patients; }
}

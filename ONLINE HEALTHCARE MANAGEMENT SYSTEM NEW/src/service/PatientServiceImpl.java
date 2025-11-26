package service;

import dao.PatientDao;
import impl.PatientDaoImpl;
import model.Patient;

import java.util.List;

public class PatientServiceImpl implements PatientService {

    private final PatientDao patientDao = new PatientDaoImpl();

    public void addPatient(Patient patient) { patientDao.save(patient); }

    public Patient getPatient(int id) { return patientDao.findById(id); }

    public List<Patient> getAllPatients() { return patientDao.findAll(); }
}

package service;

import model.Patient;
import java.util.List;

public interface PatientService {
    void addPatient(Patient patient);
    Patient getPatient(int id);
    List<Patient> getAllPatients();
}

package service;

import model.Doctor;
import java.util.List;

public interface DoctorService {
    void addDoctor(Doctor doctor);
    Doctor getDoctor(int id);
    List<Doctor> getAllDoctors();
}


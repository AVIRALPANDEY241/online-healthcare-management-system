package service;

import dao.DoctorDao;
import impl.DoctorDaoImpl;
import model.Doctor;

import java.util.List;

public class DoctorServiceImpl implements DoctorService {

    private final DoctorDao doctorDao = new DoctorDaoImpl();

    public void addDoctor(Doctor doctor) { doctorDao.save(doctor); }

    public Doctor getDoctor(int id) { return doctorDao.findById(id); }

    public List<Doctor> getAllDoctors() { return doctorDao.findAll(); }
}



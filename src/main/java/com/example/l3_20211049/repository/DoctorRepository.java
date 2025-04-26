package com.example.l3_20211049.repository;
import com.example.l3_20211049.entity.Doctor;
import com.example.l3_20211049.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
    List<Doctor> findByHospitalId(Integer hospitalId);

    @Query("SELECT p FROM Paciente p WHERE p.doctor.id = ?1 AND p.fechaCita >= CURRENT_DATE ORDER BY p.fechaCita ASC")
    List<Paciente> findProximasCitas(Integer doctorId);

    @Modifying
    @Query("UPDATE Paciente p SET p.doctor.id = ?2 WHERE p.doctor.id = ?1")
    void derivarPacientes(Integer doctorOrigenId, Integer doctorDestinoId);
}
package com.example.l3_20211049.repository;
import com.example.l3_20211049.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Integer> {
    List<Paciente> findByHospitalId(Integer hospitalId);
    List<Paciente> findByDoctorId(Integer doctorId);
}
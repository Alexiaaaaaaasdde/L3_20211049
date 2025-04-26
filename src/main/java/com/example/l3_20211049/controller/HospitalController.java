package com.example.l3_20211049.controller;
import com.example.l3_20211049.entity.Hospital;
import com.example.l3_20211049.repository.DoctorRepository;
import com.example.l3_20211049.repository.HospitalRepository;
import com.example.l3_20211049.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/hospital")
public class HospitalController {

    @Autowired
    HospitalRepository hospitalRepository;

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    PacienteRepository pacienteRepository;

    @GetMapping("/listar")
    public String listarHospitales(Model model) {
        List<Hospital> listaHospitales = hospitalRepository.findAll();
        model.addAttribute("listaHospitales", listaHospitales);
        return "hospital/lista";
    }

    @GetMapping("/doctores/{id}")
    public String listarDoctoresPorHospital(@PathVariable("id") Integer id, Model model) {
        Optional<Hospital> optHospital = hospitalRepository.findById(id);
        if (optHospital.isPresent()) {
            model.addAttribute("hospital", optHospital.get());
            model.addAttribute("listaDoctores", doctorRepository.findByHospitalId(id));
            return "hospital/doctores";
        } else {
            return "redirect:/hospital/listar";
        }
    }

    @GetMapping("/pacientes/{id}")
    public String listarPacientesPorHospital(@PathVariable("id") Integer id, Model model) {
        Optional<Hospital> optHospital = hospitalRepository.findById(id);
        if (optHospital.isPresent()) {
            model.addAttribute("hospital", optHospital.get());
            model.addAttribute("listaPacientes", pacienteRepository.findByHospitalId(id));
            return "hospital/pacientes";
        } else {
            return "redirect:/hospital/listar";
        }
    }
}
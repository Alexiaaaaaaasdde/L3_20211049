package com.example.l3_20211049.controller;

import com.example.l3_20211049.entity.Doctor;
import com.example.l3_20211049.entity.Paciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    DoctorRepository doctorRepository;

    @GetMapping("/listar")
    public String listarDoctores(Model model) {
        List<Doctor> listaDoctores = doctorRepository.findAll();
        model.addAttribute("listaDoctores", listaDoctores);
        return "doctor/lista";
    }

    @GetMapping("/proximasCitas/{id}")
    public String verProximasCitas(@PathVariable("id") Integer id, Model model) {
        Optional<Doctor> optDoctor = doctorRepository.findById(id);
        if (optDoctor.isPresent()) {
            Doctor doctor = optDoctor.get();
            List<Paciente> proximasCitas = doctorRepository.findProximasCitas(id);

            model.addAttribute("doctor", doctor);
            model.addAttribute("proximasCitas", proximasCitas);
            return "doctor/proximasCitas";
        } else {
            return "redirect:/doctor/listar";
        }
    }
}
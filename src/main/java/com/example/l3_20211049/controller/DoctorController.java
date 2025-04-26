package com.example.l3_20211049.controller;

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
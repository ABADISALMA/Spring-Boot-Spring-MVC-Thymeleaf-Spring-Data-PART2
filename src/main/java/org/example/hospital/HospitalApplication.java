package org.example.hospital;

import org.example.hospital.entities.Patient;
import org.example.hospital.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class HospitalApplication implements CommandLineRunner {

    @Autowired
    private PatientRepository patientRepository;
    public static void main(String[] args) {
        SpringApplication.run(HospitalApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        Patient patient = Patient.builder().id(null).nom("Saad").dateNaissance(new Date()).malade(false).score(233).build();

        patientRepository.save(patient);

        Patient patient1= new Patient(null,"Yassin",new Date(),false,123);
        //utilser buuilder ou constructeur ou set
        patientRepository.save(patient1);
        patientRepository.save(new Patient(null,"Saad",new Date(),true,200));
        patientRepository.save(new Patient(null,"Asmaa",new Date(),false,349));
        patientRepository.save(new Patient(null,"Salma",new Date(),false,456));
        patientRepository.save(new Patient(null,"Imane",new Date(),true,236));

    }
}

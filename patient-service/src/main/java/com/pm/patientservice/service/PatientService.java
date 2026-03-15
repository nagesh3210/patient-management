package com.pm.patientservice.service;

import billing.BillingServiceGrpc;
import com.pm.patientservice.dto.PatientRequestDTO;
import com.pm.patientservice.dto.PatientResponseDTO;
import com.pm.patientservice.exception.EmailAlreadyExistsException;
import com.pm.patientservice.exception.PatientNotFoundException;
import com.pm.patientservice.grpc.BillingServiceGrpcClient;
import com.pm.patientservice.mapper.PatientMapper;
import com.pm.patientservice.model.Patient;
import com.pm.patientservice.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class PatientService
{
    private PatientRepository patientRepository;

    private final BillingServiceGrpcClient billingServiceGrpcClient;

    public PatientService(PatientRepository patientRepository , BillingServiceGrpcClient billingServiceGrpcClient)
    {
        this.patientRepository=patientRepository;
        this.billingServiceGrpcClient=billingServiceGrpcClient;
    }


    public List<PatientResponseDTO> getPatients()
    {

        List<Patient> all = patientRepository.findAll();
        List<PatientResponseDTO> list = all.stream().map(PatientMapper::toDTO).toList();

      return list;
    }

    public PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO)
    {
        if(patientRepository.existsByEmail(patientRequestDTO.getEmail()))
        {
            throw new EmailAlreadyExistsException("A patient with " + patientRequestDTO.getEmail()+ "Alredy Exists");
        }

        Patient model = PatientMapper.toModel(patientRequestDTO);
        Patient save = patientRepository.save(model);

        billingServiceGrpcClient.createBillingAccount(save.getId().toString(),save.getName(),save.getEmail());

        return PatientMapper.toDTO(save);

    }

    public PatientResponseDTO updatePatient(UUID id , PatientRequestDTO patientRequestDTO)
    {

        Patient patient = patientRepository.findById(id).orElseThrow(() -> new PatientNotFoundException("Patient is not found with this is"+ id));

        if(patientRepository.existsByEmailAndIdNot(patientRequestDTO.getEmail(),id))
        {
            throw new EmailAlreadyExistsException("A patient with " + patientRequestDTO.getEmail() + " already exists!");

        }

        patient.setName(patientRequestDTO.getName());
        patient.setEmail(patientRequestDTO.getEmail());
        patient.setAddress(patientRequestDTO.getAddress());
        patient.setDateOfBirth(LocalDate.parse(patientRequestDTO.getDateOfBirth()));

        Patient updatedPatient = patientRepository.save(patient);

        return PatientMapper.toDTO(updatedPatient);

    }

    public void deletePatient(UUID id)
    {
        patientRepository.deleteById(id);
    }





}

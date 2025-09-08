package com.healthcareApi.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name= "TB_APPOINTMENTS")
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AppointmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "APPOINTMENT_ID")
    private Long id;

    private LocalDateTime date;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "HEALTH_PROFESSIONAL_ID")
    private HealthProfessionalEntity healthProfessional;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "PATIENT_ID")
    private PatientEntity patient;

    @OneToOne
    @JoinColumn(name = "PRESCRIPTION_ID", unique = true)
    private PrescriptionEntity prescription;

    @Column(length = 1000)
    private String observation;
}

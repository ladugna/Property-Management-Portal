package edu.miu.cs.cs545.propertymanagementsystem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Logger {

    //storing transaction on database
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long transactionId;
    private LocalDate date;
    private LocalTime time;
    private String principle;
    private String operation;


}

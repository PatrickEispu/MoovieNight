package br.com.trainee.MoovieNight.util;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
@Service
public class DataUtil {

    public int calcIdade(String data)
    {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse(data,dtf);
        Period periodo = Period.between(date,LocalDate.now());
        return periodo.getYears();


    }
    public int calcIdadeCliente(String data)
    {
        //DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date = LocalDate.parse(data);
        Period periodo = Period.between(date,LocalDate.now());
        return periodo.getYears();


    }
}

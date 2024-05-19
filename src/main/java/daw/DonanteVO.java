/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daw;

import java.time.LocalDate;

/**
 *
 * @author aiman
 */
public class DonanteVO {
    private int id_paciente;
    private String nombre;
    private LocalDate fechaNacimiento;
    private String grupoSanguineo ; 
    private String rh; 
    private int numeroDonaciones; 

    public DonanteVO(int id_paciente, String nombre, LocalDate fechaNacimiento, String grupoSanguineo, String rh, int numeroDonaciones) {
        this.id_paciente = id_paciente;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.grupoSanguineo = grupoSanguineo;
        this.rh = rh;
        this.numeroDonaciones = numeroDonaciones;
    }

    public String getRh() {
        return rh;
    }

    public void setRh(String rh) {
        this.rh = rh;
    }

    
    public DonanteVO() {
    }

    
    public int getId_paciente() {
        return id_paciente;
    }

    public void setId_paciente(int id_paciente) {
        this.id_paciente = id_paciente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getGrupoSanguineo() {
        return grupoSanguineo;
    }

    public void setGrupoSanguineo(String grupoSanguineo) {
        this.grupoSanguineo = grupoSanguineo;
    }

    public int getNumeroDonaciones() {
        return numeroDonaciones;
    }

    public void setNumeroDonaciones(int numeroDonaciones) {
        this.numeroDonaciones = numeroDonaciones;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(id_paciente).append(";");
        sb.append(nombre).append(";");
        sb.append(fechaNacimiento).append(";");
        sb.append(grupoSanguineo).append(";");
        sb.append(rh).append(";");
        sb.append(numeroDonaciones).append(";");
        return sb.toString();
    }
}

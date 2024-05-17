/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daw;

import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 *
 * @author aiman
 */
public class ListaPacientes {

    private ArrayList<DonanteVO> pacientes;

    public ListaPacientes(ArrayList<DonanteVO> pacientes) {
        this.pacientes = new ArrayList<>();
    }

    public ArrayList<DonanteVO> getPacientes() {
        return pacientes;
    }

    public DonanteVO getPaciente(String id) {
        System.out.println("id buscado " + id);
        try {
            int idInt = Integer.parseInt(id);
            return pacientes.stream()
                    .filter(p -> p.getId_paciente() == idInt)
                    .findFirst()
                    .orElseThrow(() -> new NoSuchElementException("No se encontró un paciente con el id: " + id));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("El id proporcionado no es válido: " + id, e);
        }
    }

}

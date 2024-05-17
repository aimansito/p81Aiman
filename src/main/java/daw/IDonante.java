/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package daw;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author aiman
 */
public interface IDonante {
    // Método para obtener todos los registros de la tabla
    List<DonanteVO> getAll() throws SQLException;
    
    // Méodo para obtener un registro a partir de la PK
    DonanteVO findByPk(int pk) throws SQLException;
    
    // Método para insertar un registro
    int insertPaciente (DonanteVO paciente) throws SQLException;
    
    // Método para insertar varios registros
    int insertPaciente (List<DonanteVO> lista) throws SQLException;
    
    // Método para borrar un paciente
    int deletePaciente (DonanteVO p) throws SQLException;
    
    // Método para borrar toda la tabla
    int deletePaciente() throws SQLException;
    
    // Método para modificar una persona. Se modifica a la persona que tenga esa 'pk'
    // con los nuevos datos que traiga la persona 'nuevosDatos'
    int updatePaciente (int pk, DonanteVO nuevosDatos) throws SQLException;
}

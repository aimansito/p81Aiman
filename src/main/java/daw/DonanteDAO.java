/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daw;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author aiman
 */
public class DonanteDAO implements IDonante {

    private Connection con = null;

    public DonanteDAO() {
        con = Conexion.getInstance();
    }

    @Override
    public List<DonanteVO> getAll() throws SQLException {
        List<DonanteVO> lista = new ArrayList<>();

        // Preparamos la consulta de datos mediante un objeto Statement
        // ya que no necesitamos parametrizar la sentencia SQL
        try (Statement st = con.createStatement()) {
            // Ejecutamos la sentencia y obtenemos las filas en el objeto ResultSet
            ResultSet res = st.executeQuery("select * from donantes");
            // Ahora construimos la lista, recorriendo el ResultSet y mapeando los datos
            while (res.next()) {
                DonanteVO p = new DonanteVO();
                // Recogemos los datos de la persona, guardamos en un objeto
                p.setId_paciente(res.getInt("id_paciente"));
                p.setNombre(res.getString("nombre"));
                p.setFechaNacimiento(res.getDate("fechaNacimiento").toLocalDate());
                p.setGrupoSanguineo(res.getString("grupoSanguineo"));
                p.setRh(res.getString("rh"));
                p.setNumeroDonaciones(res.getInt("numeroDonaciones"));
                //Añadimos el objeto a la lista
                lista.add(p);
            }
        }

        return lista;
    }

    @Override
    public DonanteVO findByPk(int pk) throws SQLException {

        ResultSet res = null;
        DonanteVO paciente = new DonanteVO();

        String sql = "select * from donantes where id_paciente=?";

        try (PreparedStatement prest = con.prepareStatement(sql)) {
            // Preparamos la sentencia parametrizada
            prest.setInt(1, pk);

            // Ejecutamos la sentencia y obtenemos las filas en el objeto ResultSet
            res = prest.executeQuery();

            // Nos posicionamos en el primer registro del Resultset. Sólo debe haber una fila
            // si existe esa pk
            if (res.next()) {
                // Recogemos los datos de la persona, guardamos en un objeto
                paciente.setId_paciente(res.getInt("id_paciente"));
                paciente.setNombre(res.getString("nombre"));
                paciente.setFechaNacimiento(res.getDate("fechaNacimiento").toLocalDate());
                paciente.setGrupoSanguineo("grupoSanguineo");
                paciente.setRh("rh");
                paciente.setNumeroDonaciones(res.getInt("numeroDonaciones"));
                return paciente;
            }

            return null;
        }
    }

    @Override
    public int insertPaciente(DonanteVO paciente) throws SQLException {

        int numFilas = 0;
        String sql = "insert into donantes values (?,?,?,?,?,?)";

        if (findByPk(paciente.getId_paciente()) != null) {
            // Existe un registro con esa pk
            // No se hace la inserción
            return numFilas;
        } else {
            // Instanciamos el objeto PreparedStatement para inserción
            // de datos. Sentencia parametrizada
            try (PreparedStatement p = con.prepareStatement(sql)) {

                // Establecemos los parámetros de la sentencia
                p.setInt(1, paciente.getId_paciente());
                p.setString(2, paciente.getNombre());
                p.setDate(3, Date.valueOf(paciente.getFechaNacimiento()));
                p.setString(4, paciente.getGrupoSanguineo());
                p.setString(5, paciente.getRh());
                p.setInt(6, paciente.getNumeroDonaciones());
                numFilas = p.executeUpdate();
            }
            return numFilas;
        }

    }

    @Override
    public int insertPaciente(List<DonanteVO> lista) throws SQLException {
        int numFilas = 0;

        for (DonanteVO tmp : lista) {
            numFilas += insertPaciente(tmp);
        }

        return numFilas;
    }

    @Override
    public int deletePaciente() throws SQLException {

        String sql = "delete from donantes";

        int nfilas = 0;

        // Preparamos el borrado de datos  mediante un Statement
        // No hay parámetros en la sentencia SQL
        try (Statement st = con.createStatement()) {
            // Ejecución de la sentencia
            nfilas = st.executeUpdate(sql);
        }

        // El borrado se realizó con éxito, devolvemos filas afectadas
        return nfilas;

    }

    @Override
    public int deletePaciente(DonanteVO paciente) throws SQLException {
        int numFilas = 0;

        String sql = "delete from donantes where id_paciente = ?";

        // Sentencia parametrizada
        try (PreparedStatement prest = con.prepareStatement(sql)) {

            // Establecemos los parámetros de la sentencia
            prest.setInt(1, paciente.getId_paciente());
            // Ejecutamos la sentencia
            numFilas = prest.executeUpdate();
        }
        return numFilas;
    }

    @Override
    public int updatePaciente(int pk, DonanteVO paciente) throws SQLException {

        int numFilas = 0;
        String sql = "update donantes set id_paciente = ?, nombre = ?, fechaNacimiento = ?, grupoSanguineo = ?, rh = ?, numeroDonaciones = ? where id_paciente = ?";

        if (findByPk(pk) == null) {
            // La persona a actualizar no existe
            return numFilas;
        } else {
            // Instanciamos el objeto PreparedStatement para inserción
            // de datos. Sentencia parametrizada
            try (PreparedStatement p = con.prepareStatement(sql)) {

                // Establecemos los parámetros de la sentencia
                p.setInt(1, paciente.getId_paciente());
                p.setString(2, paciente.getNombre());
                p.setDate(3, Date.valueOf(paciente.getFechaNacimiento()));
                p.setString(4, paciente.getGrupoSanguineo());
                p.setString(5, paciente.getRh());
                p.setInt(6, paciente.getNumeroDonaciones());
                p.setInt(7, pk);
                
                numFilas = p.executeUpdate();
            }
            return numFilas;
        }
    }
}

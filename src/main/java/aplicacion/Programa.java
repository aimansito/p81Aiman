/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplicacion;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import daw.DonanteDAO;
import daw.DonanteVO;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author aiman
 */
public class Programa {

    public static void main(String[] args) throws IOException {
        DonanteDAO d = new DonanteDAO();
//        List<String> lineas = leerArchivo("./donantes.json");
        List<DonanteVO> donantes = lecturaFicheroJSON();
//        donantes.forEach(System.out::println);
        try {
            System.out.println("NºDonantes Insertados: " + d.insertPaciente(donantes));

            System.out.println("");
            System.out.println("Comprobamos en una nueva lista que se recogen los datos desde la tabla.");
            List<DonanteVO> nuevaLista = d.getAll();
            nuevaLista.forEach(System.out::println);

            // Buscamos donante con un código
            System.out.println("");
            System.out.println("Donante con primary key 3: ");
            System.out.println(d.findByPk(1070701));

            // Borramos una donante
            System.out.println("");
            System.out.println("Se va a borrar la donante con codigo 6");
            System.out.println("Antes lo inserto");
            d.insertPaciente(new DonanteVO(6, "Aiman", LocalDate.of(2020, 2, 2), "A", "+", 4));
            System.out.println("Nº donantes borrados: "
                    + d.deletePaciente(new DonanteVO(6, "Aiman", LocalDate.of(2020, 2, 2), "A", "+", 4)));

            // Comprobamos que se ha borrado correctamente
            System.out.println("");
            nuevaLista = d.getAll();
            System.out.println("Lista con datos recogidos desde la B.D despues de borrar un donante");
            nuevaLista.forEach(System.out::println);
            
            // Modificamos el donante 10
            System.out.println("");
            System.out.println("Modificación del donante con codigo 10");
            System.out.println("Antes lo inserto");
            d.insertPaciente(new DonanteVO(10, "Aiman", LocalDate.of(2020, 2, 2), "A", "+", 4));
            System.out.println("Nº Donantes modificadas " + 
                    d.updatePaciente(10, new DonanteVO(10, "Aimansito", LocalDate.of(2021, 2, 2), "B", "-", 6)));
            System.out.println(d.findByPk(10));
            
            // Comprobamos que se ha modificado correctamente
            System.out.println("");
            nuevaLista = d.getAll();
            System.out.println("Lista con datos recogidos desde la B.D despues de modificar una factura");
            nuevaLista.forEach(System.out::println);
        } catch (SQLException sqle) {
            System.out.println("No se ha realizado la operación");
            System.out.println(sqle.getMessage());
        }
    }

//    public static List<String> leerArchivo(String ruta) {
//        List<String> lineas = new ArrayList<>();
//        try {
//            lineas = Files.readAllLines(Paths.get(ruta),
//                    StandardCharsets.UTF_8);
//        } catch (IOException ex) {
//            System.out.println("Error leyendo el fichero");
//        }
//        return lineas;
//    }
//
//    public static List<DonanteVO> obtenerListaDonante(List<String> lineas) {
//        List<DonanteVO> donantes = new ArrayList<>();
//        for (String linea : lineas) {
//            String[] partes = linea.split(";");
//            int idPaciente = Integer.parseInt(partes[0]);
//            String nombre = partes[1];
//            LocalDate fechaNacimiento = LocalDate.parse(partes[2]);
//            String grupoSanguineo = partes[3];
//            String rh = partes[4];
//            int numeroDonaciones = Integer.parseInt(partes[5]);
//
//            donantes.add(new DonanteVO(idPaciente, nombre, fechaNacimiento, grupoSanguineo, rh, numeroDonaciones));
//
//        }
//        return donantes;
//    }
    public static List<DonanteVO> lecturaFicheroJSON() throws IOException {
        List<DonanteVO> lista = new ArrayList<>();

        ObjectMapper mapeador = new ObjectMapper();
        JavaTimeModule module = new JavaTimeModule();
        module.addDeserializer(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        mapeador.registerModule(module);

        lista = mapeador.readValue(new File("./donantes.json"),
                mapeador.getTypeFactory().constructCollectionType(List.class, DonanteVO.class));

        return lista;
    }
}

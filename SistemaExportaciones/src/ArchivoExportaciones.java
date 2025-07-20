// Importa clases necesarias para entrada/salida de datos y colecciones
import java.io.*;
import java.util.ArrayList;
import java.util.List;

// Clase encargada de manejar el almacenamiento de objetos Exportacion en un archivo
public class ArchivoExportaciones {

    // Constante que define el nombre del archivo donde se almacenarán los datos
    private static final String NOMBRE_ARCHIVO = "Exportaciones.dat";

    // Método para guardar una nueva exportación en el archivo
    public static void guardarExportacion(Exportacion exp) throws IOException {
        // Se lee la lista actual de exportaciones desde el archivo
        List<Exportacion> lista = leerExportaciones();
        // Se agrega la nueva exportación a la lista
        lista.add(exp);
        // Se guarda la lista actualizada en el archivo
        guardarLista(lista);
    }

    // Método que lee las exportaciones desde el archivo y las retorna como una lista
    public static List<Exportacion> leerExportaciones() {
        // Se crea una lista vacía por defecto
        List<Exportacion> lista = new ArrayList<>();
        // Se intenta abrir el archivo para leer objetos
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(NOMBRE_ARCHIVO))) {
            // Se lee el contenido del archivo y se castea a lista de Exportacion
            lista = (List<Exportacion>) ois.readObject();
        } catch (FileNotFoundException e) {
            // Si el archivo no existe, se devuelve una lista vacía
        } catch (IOException | ClassNotFoundException e) {
            // Si ocurre algún error de E/S o de casting, se imprime el error
            e.printStackTrace();
        }
        // Se retorna la lista (posiblemente vacía)
        return lista;
    }

    // Método que guarda una lista de exportaciones en el archivo
    public static void guardarLista(List<Exportacion> lista) throws IOException {
        // Se abre el archivo para escritura de objetos
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(NOMBRE_ARCHIVO))) {
            // Se escribe la lista completa en el archivo
            oos.writeObject(lista);
        }
    }

    // Método que actualiza una exportación existente, o la agrega si no existe
    public static void actualizarExportacion(Exportacion exp) throws IOException {
        // Se lee la lista actual de exportaciones
        List<Exportacion> lista = leerExportaciones();
        boolean encontrado = false; // Bandera para saber si se encontró la exportación

        // Se recorre la lista para buscar la exportación por ID de cliente
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getIdCliente().equals(exp.getIdCliente())) {
                // Si se encuentra, se reemplaza la exportación
                lista.set(i, exp);
                encontrado = true;
                break;
            }
        }

        // Si no se encontró, se agrega la nueva exportación a la lista
        if (!encontrado) {
            lista.add(exp);
        }

        // Se guarda la lista actualizada
        guardarLista(lista);
    }

    // Método que borra una exportación basada en el ID del cliente
    public static void borrarExportacion(String idCliente) throws IOException {
        // Se obtiene la lista actual de exportaciones
        List<Exportacion> lista = leerExportaciones();
        // Se eliminan todas las exportaciones cuyo ID coincida con el dado
        lista.removeIf(e -> e.getIdCliente().equals(idCliente));
        // Se guarda la lista actualizada sin la exportación eliminada
        guardarLista(lista);
    }
}

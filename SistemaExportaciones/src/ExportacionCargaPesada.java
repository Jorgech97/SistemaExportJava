// Importa la interfaz Serializable para permitir guardar objetos de esta clase en archivos
import java.io.Serializable;

// Clase ExportacionCargaPesada que extiende Exportacion y representa una exportación de carga pesada
public class ExportacionCargaPesada extends Exportacion implements Serializable {
    // ID de versión de serialización
    private static final long serialVersionUID = 1L;

    // Atributo específico para este tipo de exportación: tipo de carga (refrigerado, no refrigerado, embalada)
    private String tipoCarga;

    // Constructor que inicializa todos los atributos, incluyendo los heredados y el nuevo atributo tipoCarga
    public ExportacionCargaPesada(String idCliente, String nombreCompleto, String tipoExportacion,
                                 String zonaEnvio, String tipoServicio, double kilogramos,
                                 String tipoCarga) {
        // Llama al constructor de la clase padre Exportacion
        super(idCliente, nombreCompleto, tipoExportacion, zonaEnvio, tipoServicio, kilogramos);
        // Inicializa el atributo propio de esta subclase
        this.tipoCarga = tipoCarga;
    }

    // Implementación del método abstracto calcularExportacion() heredado de Exportacion
    @Override
    public double calcularExportacion() {
        double costoPorKilo;  // Variable que almacenará el costo por kilogramo según el tipo de carga

        // Evalúa el tipo de carga y asigna la tarifa correspondiente
        switch (tipoCarga) {
            case "Contenedor Refrigerado":
                costoPorKilo = 950; // Aplica para barco o avión
                break;

            case "Contenedor no refrigerado":
                costoPorKilo = 550; // Aplica para barco o avión
                break;

            case "Carga embalada":
                costoPorKilo = 450;
                break;

            default:
                // Si el tipo de carga no coincide con ninguno de los casos válidos, lanza error
                throw new IllegalArgumentException("Tipo de carga no válido.");
        }

        // Retorna el costo total multiplicando el costo por kilo por la cantidad de kilogramos
        return costoPorKilo * kilogramos;
    }

    // Getter para obtener el tipo de carga desde otras clases si se necesita
    public String getTipoCarga() {
        return tipoCarga;
    }
}

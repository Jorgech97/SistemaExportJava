// Importa la interfaz Serializable para permitir que los objetos se puedan guardar en archivos
import java.io.Serializable;

// Clase ExportacionCargaSuelta que extiende Exportacion y representa exportaciones en carga suelta
public class ExportacionCargaSuelta extends Exportacion implements Serializable {
    // Identificador de versión para la serialización (recomendado)
    private static final long serialVersionUID = 1L;

    // Atributo adicional que representa el volumen en pies cúbicos de la carga
    private double piesCarga;

    // Constructor que inicializa todos los atributos, incluyendo los heredados y piesCarga
    public ExportacionCargaSuelta(String idCliente, String nombreCompleto, String tipoExportacion,
                                 String zonaEnvio, String tipoServicio, double kilogramos,
                                 double piesCarga) {
        // Llama al constructor de la clase base Exportacion
        super(idCliente, nombreCompleto, tipoExportacion, zonaEnvio, tipoServicio, kilogramos);
        // Asigna el volumen de carga en pies cúbicos
        this.piesCarga = piesCarga;
    }

    // Implementación del método abstracto calcularExportacion de la clase base
    @Override
    public double calcularExportacion() {
        double costoPorKilo = 0;  // Costo por kilogramo, depende del medio de transporte
        double adicional = 0;     // Cargo adicional si se supera cierto volumen

        // Si el transporte es por avión
        if (tipoServicio.equalsIgnoreCase("Avión")) {
            costoPorKilo = 450;  // Tarifa por kilo vía aérea
            if (piesCarga > 18) {
                adicional = 100; // Cargo adicional por exceso de volumen
            }
        }
        // Si el transporte es por barco
        else if (tipoServicio.equalsIgnoreCase("Barco")) {
            costoPorKilo = 150;  // Tarifa por kilo vía marítima
            if (piesCarga > 18) {
                adicional = 50; // Cargo adicional por exceso de volumen
            }
        }

        // Devuelve el total: costo base más el posible adicional
        return (costoPorKilo * kilogramos) + adicional;
    }

    // Método getter para obtener el volumen de la carga
    public double getPiesCarga() {
        return piesCarga;
    }
}

// Importa las clases necesarias para serialización y manejo de fechas
import java.io.Serializable;
import java.time.LocalDate;

// Clase abstracta Exportacion que implementa Serializable para poder guardarse en archivos binarios
public abstract class Exportacion implements Serializable {
    // Identificador de versión para la serialización (recomendado al implementar Serializable)
    private static final long serialVersionUID = 1L;

    // Atributos protegidos (visibles para clases hijas) que representan los datos de la exportación
    protected String idCliente;           // Identificación del cliente (formato cédula CR: 1-1111-1111)
    protected String nombreCompleto;      // Nombre completo del cliente (mínimo 7 caracteres)
    protected String tipoExportacion;     // Tipo de exportación: ECP (personal) o ECS (comercial)
    protected LocalDate fechaExportacion; // Fecha en que se realiza la exportación (fecha actual)
    protected String zonaEnvio;           // Zona de envío o país destino
    protected String tipoServicio;        // Medio de transporte: Barco o Avión
    protected double kilogramos;          // Peso total de la exportación en kilogramos

    // Constructor que inicializa todos los atributos, excepto la fecha (se asigna automáticamente)
    public Exportacion(String idCliente, String nombreCompleto, String tipoExportacion,
                       String zonaEnvio, String tipoServicio, double kilogramos) {
        this.idCliente = idCliente;                           // Asigna ID del cliente
        this.nombreCompleto = nombreCompleto;                 // Asigna el nombre del cliente
        this.tipoExportacion = tipoExportacion;               // Asigna tipo de exportación
        this.fechaExportacion = LocalDate.now();              // Asigna la fecha actual del sistema
        this.zonaEnvio = zonaEnvio;                           // Asigna la zona de envío
        this.tipoServicio = tipoServicio;                     // Asigna el medio de transporte
        this.kilogramos = kilogramos;                         // Asigna el peso total
    }

    // Método abstracto que debe ser implementado por las subclases para calcular el costo o valor de exportación
    public abstract double calcularExportacion();

    // Métodos getter para acceder a los atributos desde fuera de la clase
    public String getIdCliente() { return idCliente; }                    // Retorna ID del cliente
    public String getNombreCompleto() { return nombreCompleto; }         // Retorna nombre completo
    public LocalDate getFechaExportacion() { return fechaExportacion; }  // Retorna fecha de exportación
    public String getZonaEnvio() { return zonaEnvio; }                   // Retorna zona de envío
    public String getTipoServicio() { return tipoServicio; }             // Retorna tipo de servicio
    public double getKilogramos() { return kilogramos; }                 // Retorna peso en kilogramos
}

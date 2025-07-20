// Importaciones necesarias para componentes gráficos, eventos, formato de fecha y colecciones
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

// Clase principal de la ventana GUI que extiende JFrame
public class VentanaPrincipal extends JFrame {
    // Componentes del formulario
    private JTextField txtIdCliente, txtNombre, txtZonaEnvio, txtKilogramos, txtPiesCarga;
    private JComboBox<String> comboTipoExportacion, comboTipoServicio, comboTipoCarga;
    private JButton btnAgregar, btnActualizar, btnBorrar, btnMostrarReporte;
    private JTable tablaReporte;
    private DefaultTableModel modeloTabla;

    // Constructor principal
    public VentanaPrincipal() {
        initComponents(); // Inicializa componentes de la interfaz
    }

    // Método que construye e inicializa todos los componentes
    private void initComponents() {
        setTitle("Sistema de Exportaciones");
        setSize(900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null); // Diseño absoluto (coordenadas manuales)

        // Etiqueta y campo: ID del cliente
        JLabel lblIdCliente = new JLabel("ID Cliente (1-1111-1111):");
        lblIdCliente.setBounds(20, 20, 180, 25);
        add(lblIdCliente);
        txtIdCliente = new JTextField();
        txtIdCliente.setBounds(200, 20, 150, 25);
        add(txtIdCliente);

        // Nombre completo
        JLabel lblNombre = new JLabel("Nombre Completo:");
        lblNombre.setBounds(20, 60, 180, 25);
        add(lblNombre);
        txtNombre = new JTextField();
        txtNombre.setBounds(200, 60, 250, 25);
        add(txtNombre);

        // Tipo de exportación: ECP o ECS
        JLabel lblTipoExportacion = new JLabel("Tipo Exportación:");
        lblTipoExportacion.setBounds(20, 100, 180, 25);
        add(lblTipoExportacion);
        comboTipoExportacion = new JComboBox<>(new String[]{"ECP", "ECS"});
        comboTipoExportacion.setBounds(200, 100, 100, 25);
        add(comboTipoExportacion);

        // Zona de envío
        JLabel lblZonaEnvio = new JLabel("Zona de Envío (País):");
        lblZonaEnvio.setBounds(20, 140, 180, 25);
        add(lblZonaEnvio);
        txtZonaEnvio = new JTextField();
        txtZonaEnvio.setBounds(200, 140, 150, 25);
        add(txtZonaEnvio);

        // Tipo de servicio: Barco o Avión
        JLabel lblTipoServicio = new JLabel("Tipo de Servicio:");
        lblTipoServicio.setBounds(20, 180, 180, 25);
        add(lblTipoServicio);
        comboTipoServicio = new JComboBox<>(new String[]{"Barco", "Avión"});
        comboTipoServicio.setBounds(200, 180, 100, 25);
        add(comboTipoServicio);

        // Kilogramos
        JLabel lblKilogramos = new JLabel("Kilogramos:");
        lblKilogramos.setBounds(20, 220, 180, 25);
        add(lblKilogramos);
        txtKilogramos = new JTextField();
        txtKilogramos.setBounds(200, 220, 100, 25);
        add(txtKilogramos);

        // Campos para tipo ECP
        JLabel lblTipoCarga = new JLabel("Tipo de Carga (ECP):");
        lblTipoCarga.setBounds(20, 260, 180, 25);
        add(lblTipoCarga);
        comboTipoCarga = new JComboBox<>(new String[]{
            "Contenedor Refrigerado",
            "Contenedor no refrigerado",
            "Carga embalada"
        });
        comboTipoCarga.setBounds(200, 260, 220, 25);
        add(comboTipoCarga);

        // Campos para tipo ECS
        JLabel lblPiesCarga = new JLabel("Pies de la Carga (ECS):");
        lblPiesCarga.setBounds(20, 300, 180, 25);
        add(lblPiesCarga);
        txtPiesCarga = new JTextField();
        txtPiesCarga.setBounds(200, 300, 100, 25);
        add(txtPiesCarga);

        // Botones de acción
        btnAgregar = new JButton("Agregar");
        btnAgregar.setBounds(20, 350, 100, 30);
        add(btnAgregar);

        btnActualizar = new JButton("Actualizar");
        btnActualizar.setBounds(130, 350, 100, 30);
        add(btnActualizar);

        btnBorrar = new JButton("Borrar");
        btnBorrar.setBounds(240, 350, 100, 30);
        add(btnBorrar);

        btnMostrarReporte = new JButton("Mostrar Reporte");
        btnMostrarReporte.setBounds(350, 350, 150, 30);
        add(btnMostrarReporte);

        // Tabla para mostrar el reporte
        modeloTabla = new DefaultTableModel();
        modeloTabla.setColumnIdentifiers(new String[]{"ID Cliente", "Nombre", "Fecha Exportación", "Zona Envío", "Costo Total"});
        tablaReporte = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaReporte);
        scrollPane.setBounds(20, 400, 840, 150);
        add(scrollPane);

        // Acción al cambiar tipo de exportación
        comboTipoExportacion.addActionListener(e -> actualizarCamposSegunTipo());
        // Acción al cambiar tipo de servicio
        comboTipoServicio.addActionListener(e -> actualizarCargaEmbaladaSiAplica());

        // Acciones de los botones
        btnAgregar.addActionListener(e -> agregarExportacion());
        btnActualizar.addActionListener(e -> actualizarExportacion());
        btnBorrar.addActionListener(e -> borrarExportacion());
        btnMostrarReporte.addActionListener(e -> mostrarReporte());

        // Inicializa la visibilidad de campos
        actualizarCamposSegunTipo();

        setLocationRelativeTo(null); // Centrar ventana
    }

    // Habilita o deshabilita campos según el tipo de exportación seleccionado
    private void actualizarCamposSegunTipo() {
        String tipo = (String) comboTipoExportacion.getSelectedItem();
        if ("ECP".equals(tipo)) {
            comboTipoServicio.removeAllItems();
            comboTipoServicio.addItem("Barco");
            comboTipoServicio.addItem("Avión");
            comboTipoCarga.setEnabled(true);
            txtPiesCarga.setEnabled(false);
            txtPiesCarga.setText("");
            actualizarCargaEmbaladaSiAplica();
        } else {
            comboTipoServicio.removeAllItems();
            comboTipoServicio.addItem("Barco");
            comboTipoServicio.addItem("Avión");
            comboTipoCarga.setEnabled(false);
            txtPiesCarga.setEnabled(true);
            comboTipoCarga.setSelectedIndex(0);
        }
    }
    
    private void actualizarCargaEmbaladaSiAplica() {
        String tipoExportacion = (String) comboTipoExportacion.getSelectedItem();
        String tipoServicio = (String) comboTipoServicio.getSelectedItem();

        if ("ECP".equals(tipoExportacion) && "Avión".equals(tipoServicio)) {
            if ("Carga embalada".equals(comboTipoCarga.getSelectedItem())) {
                comboTipoCarga.setSelectedIndex(0);
            }

            DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) comboTipoCarga.getModel();
            if (model.getIndexOf("Carga embalada") != -1) {
                model.removeElement("Carga embalada");
            }
        } else if ("ECP".equals(tipoExportacion)) {
            comboTipoCarga.setModel(new DefaultComboBoxModel<>(new String[]{
                    "Contenedor Refrigerado",
                    "Contenedor no refrigerado",
                    "Carga embalada"
            }));
        }
    }

    // Lógica para agregar una exportación nueva
    private void agregarExportacion() {
        try {
            Exportacion exp = crearExportacionDesdeFormulario();
            ArchivoExportaciones.guardarExportacion(exp);
            JOptionPane.showMessageDialog(this, "Exportación agregada correctamente.");
            limpiarFormulario();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al agregar exportación: " + ex.getMessage());
        }
    }

    // Lógica para actualizar una exportación existente
    private void actualizarExportacion() {
        try {
            Exportacion exp = crearExportacionDesdeFormulario();
            ArchivoExportaciones.actualizarExportacion(exp);
            JOptionPane.showMessageDialog(this, "Exportación actualizada correctamente.");
            limpiarFormulario();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al actualizar exportación: " + ex.getMessage());
        }
    }

    // Lógica para eliminar una exportación por ID
    private void borrarExportacion() {
        try {
            String idCliente = txtIdCliente.getText();
            if (idCliente.isEmpty()) {
                throw new IllegalArgumentException("Debe ingresar ID Cliente para borrar.");
            }
            ArchivoExportaciones.borrarExportacion(idCliente);
            JOptionPane.showMessageDialog(this, "Exportación borrada correctamente.");
            limpiarFormulario();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al borrar exportación: " + ex.getMessage());
        }
    }

    // Muestra los datos en la tabla, con filtros opcionales por ID o zona
    private void mostrarReporte() {
        try {
            String filtroId = txtIdCliente.getText().trim();
            String filtroZona = txtZonaEnvio.getText().trim();

            List<Exportacion> lista = ArchivoExportaciones.leerExportaciones();

            // Filtrado opcional
            if (!filtroId.isEmpty()) {
                lista = lista.stream()
                        .filter(e -> e.getIdCliente().equalsIgnoreCase(filtroId))
                        .collect(Collectors.toList());
            }
            if (!filtroZona.isEmpty()) {
                lista = lista.stream()
                        .filter(e -> e.getZonaEnvio().equalsIgnoreCase(filtroZona))
                        .collect(Collectors.toList());
            }

            // Mostrar en la tabla
            modeloTabla.setRowCount(0); // Limpiar tabla
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            for (Exportacion e : lista) {
                Object[] fila = {
                        e.getIdCliente(),
                        e.getNombreCompleto(),
                        e.getFechaExportacion().format(formatter),
                        e.getZonaEnvio(),
                        String.format("%.2f", e.calcularExportacion())
                };
                modeloTabla.addRow(fila);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al mostrar reporte: " + ex.getMessage());
        }
    }

    // Crea un objeto Exportacion desde los datos del formulario (valida según tipo)
    private Exportacion crearExportacionDesdeFormulario() throws Exception {
        String idCliente = txtIdCliente.getText().trim();
        if (!idCliente.matches("\\d-\\d{4}-\\d{4}")) {
            throw new IllegalArgumentException("Formato de cédula inválido (Ejemplo: 1-1111-1111)");
        }

        String nombre = txtNombre.getText().trim();
        if (nombre.length() < 7) {
            throw new IllegalArgumentException("El nombre debe tener al menos 7 caracteres.");
        }

        String tipoExportacion = (String) comboTipoExportacion.getSelectedItem();
        String zonaEnvio = txtZonaEnvio.getText().trim();
        if (zonaEnvio.isEmpty()) {
            throw new IllegalArgumentException("Zona de envío no puede estar vacía.");
        }

        String tipoServicio = (String) comboTipoServicio.getSelectedItem();

        double kilogramos;
        try {
            kilogramos = Double.parseDouble(txtKilogramos.getText().trim());
            if (kilogramos <= 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Kilogramos debe ser un número positivo.");
        }

        // Instancia según tipo
        if ("ECP".equals(tipoExportacion)) {
            String tipoCarga = (String) comboTipoCarga.getSelectedItem();
            return new ExportacionCargaPesada(idCliente, nombre, tipoExportacion, zonaEnvio, tipoServicio, kilogramos, tipoCarga);
        } else {
            double piesCarga;
            try {
                piesCarga = Double.parseDouble(txtPiesCarga.getText().trim());
                if (piesCarga <= 0) throw new NumberFormatException();
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Pies de la carga debe ser un número positivo.");
            }
            return new ExportacionCargaSuelta(idCliente, nombre, tipoExportacion, zonaEnvio, tipoServicio, kilogramos, piesCarga);
        }
    }

    // Limpia todos los campos del formulario
    private void limpiarFormulario() {
        txtIdCliente.setText("");
        txtNombre.setText("");
        txtZonaEnvio.setText("");
        txtKilogramos.setText("");
        txtPiesCarga.setText("");
        comboTipoExportacion.setSelectedIndex(0);
        comboTipoServicio.setSelectedIndex(0);
        comboTipoCarga.setSelectedIndex(0);
        modeloTabla.setRowCount(0);
        actualizarCamposSegunTipo();
    }

    // Método main: lanza la interfaz gráfica
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new VentanaPrincipal().setVisible(true);
        });
    }
}


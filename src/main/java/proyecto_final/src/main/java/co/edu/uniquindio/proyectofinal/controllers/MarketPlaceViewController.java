package co.edu.uniquindio.proyectofinal.controllers;

import co.edu.uniquindio.proyectofinal.model.Administrador;
import co.edu.uniquindio.proyectofinal.model.Rol;
import co.edu.uniquindio.proyectofinal.model.Usuario;
import co.edu.uniquindio.proyectofinal.model.Vendedor;
import co.edu.uniquindio.proyectofinal.utils.MensajeUtil;
import co.edu.uniquindio.proyectofinal.utils.TextFormatterUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

public class MarketPlaceViewController {
    ModelFactoryController modelFactoryController;
    LoginController loginController;
    Vendedor1Controller vendedor1Controller;
    Vendedor2Controller vendedor2Controller;
    Vendedor3Controller vendedor3Controller;
    Vendedor4Controller vendedor4Controller;
    Vendedor5Controller vendedor5Controller;
    Vendedor6Controller vendedor6Controller;
    Vendedor7Controller vendedor7Controller;
    Vendedor8Controller vendedor8Controller;
    Vendedor9Controller vendedor9Controller;
    Vendedor10Controller vendedor10Controller;
    ObservableList<Vendedor> listaVendedoresData = FXCollections.observableArrayList();
    Vendedor vendedorSeleccionado;
    @FXML
    private ComboBox<?> cbVendedor1;
    @FXML
    private ComboBox<?> cbVendedor2;
    @FXML
    private ComboBox<?> cbVendedorPublicados;
    @FXML
    private TableColumn<Vendedor, String> colListaVendedoresApellido;
    @FXML
    private TableColumn<Vendedor, String> colListaVendedoresCedula;
    @FXML
    private TableColumn<Vendedor, String> colListaVendedoresNombre;
    @FXML
    private DatePicker dateProductosPublicadosAntes;
    @FXML
    private DatePicker dateProductosPublicadosDespues;
    @FXML
    private Pane paneBloqueo;
    @FXML
    private PasswordField pfPass;
    @FXML
    private TableView<Vendedor> tableListaVendedores;
    @FXML
    private TableView<?> tblCantProductosPublicados;
    @FXML
    private TableView<?> tblCantMensajesEnviados;
    @FXML
    private TableView<?> tblCantProductosPublicadosVendedor;
    @FXML
    private TableView<?> tblCantidadContactos;
    @FXML
    private TableView<?> tblLikes;
    @FXML
    private TextField tfUser;
    @FXML
    private TextField txtApellidoVendedor;
    @FXML
    private TextField txtCedulaVendedor;
    @FXML
    private TextField txtContraseñaVendedor;
    @FXML
    private TextField txtDireccionVendedor;
    @FXML
    private TextField txtNombreVendedor;
    @FXML
    private TextField txtUsuarioVendedor;
    public static Administrador admin;

    @FXML
    public void initialize() {
        modelFactoryController = ModelFactoryController.getInstance();
        loginController = new LoginController(modelFactoryController);
        vendedor1Controller = new Vendedor1Controller();
        vendedor2Controller = new Vendedor2Controller();
        vendedor3Controller = new Vendedor3Controller();
        vendedor4Controller = new Vendedor4Controller();
        vendedor5Controller = new Vendedor5Controller();
        vendedor6Controller = new Vendedor6Controller();
        vendedor7Controller = new Vendedor7Controller();
        vendedor8Controller = new Vendedor8Controller();
        vendedor9Controller = new Vendedor9Controller();
        vendedor10Controller = new Vendedor10Controller();
        inicializarVendedoresView();
    }

    public void login(ActionEvent actionEvent) {
        String user = tfUser.getText();
        String pass = pfPass.getText();
        if (loginController.loginAdmin(user, pass))
            paneBloqueo.setVisible(false);
    }

    @FXML
    void onCrearVendedorClick(ActionEvent event) {
        int i = modelFactoryController.getMarketplace().obtenerCampoDisponibleVendedor();
        crearVendedor(i);
    }

    @FXML
    void onEditarVendedorClick(ActionEvent event) {

    }

    @FXML
    void onEliminarVendedorClick(ActionEvent event) {
        String cedula = txtCedulaVendedor.getText();
        eliminarVendedor(cedula);
    }

    private void crearVendedor(int index) {
        String nombre = txtNombreVendedor.getText();
        String apellido = txtApellidoVendedor.getText();
        String cedula = txtCedulaVendedor.getText();
        String direccion = txtDireccionVendedor.getText();
        String usuario = txtUsuarioVendedor.getText();
        String contrasenia = txtContraseñaVendedor.getText();
        if (datosValidosVendedor(nombre, apellido, cedula, direccion, usuario, contrasenia)) {
            Vendedor vendedor = null;
            vendedor = modelFactoryController.crearVendedor(nombre, apellido, cedula, direccion, new Usuario(usuario,
                    contrasenia, Rol.VENDEDOR, index), index);
            if (vendedor != null) {
                listaVendedoresData.set(index, vendedor);
                switch (listaVendedoresData.size()) {
                    case 1:
                        vendedor1Controller.actualizarTablasContactosClientes1();
                        break;
                    case 2:
                        vendedor1Controller.actualizarTablasContactosClientes1();
                        vendedor2Controller.actualizarTablasContactosClientes2();
                        break;
                    case 3:
                        vendedor1Controller.actualizarTablasContactosClientes1();
                        vendedor2Controller.actualizarTablasContactosClientes2();
                        vendedor3Controller.actualizarTablasContactosClientes3();
                        break;
                    case 4:
                        vendedor1Controller.actualizarTablasContactosClientes1();
                        vendedor2Controller.actualizarTablasContactosClientes2();
                        vendedor3Controller.actualizarTablasContactosClientes3();
                        vendedor4Controller.actualizarTablasContactosClientes4();
                        break;
                    case 5:
                        vendedor1Controller.actualizarTablasContactosClientes1();
                        vendedor2Controller.actualizarTablasContactosClientes2();
                        vendedor3Controller.actualizarTablasContactosClientes3();
                        vendedor4Controller.actualizarTablasContactosClientes4();
                        vendedor5Controller.actualizarTablasContactosClientes5();
                        break;
                    case 6:
                        vendedor1Controller.actualizarTablasContactosClientes1();
                        vendedor2Controller.actualizarTablasContactosClientes2();
                        vendedor3Controller.actualizarTablasContactosClientes3();
                        vendedor4Controller.actualizarTablasContactosClientes4();
                        vendedor5Controller.actualizarTablasContactosClientes5();
                        vendedor6Controller.actualizarTablasContactosClientes6();
                        break;
                    case 7:
                        vendedor1Controller.actualizarTablasContactosClientes1();
                        vendedor2Controller.actualizarTablasContactosClientes2();
                        vendedor3Controller.actualizarTablasContactosClientes3();
                        vendedor4Controller.actualizarTablasContactosClientes4();
                        vendedor5Controller.actualizarTablasContactosClientes5();
                        vendedor6Controller.actualizarTablasContactosClientes6();
                        vendedor7Controller.actualizarTablasContactosClientes7();
                        break;
                    case 8:
                        vendedor1Controller.actualizarTablasContactosClientes1();
                        vendedor2Controller.actualizarTablasContactosClientes2();
                        vendedor3Controller.actualizarTablasContactosClientes3();
                        vendedor4Controller.actualizarTablasContactosClientes4();
                        vendedor5Controller.actualizarTablasContactosClientes5();
                        vendedor6Controller.actualizarTablasContactosClientes6();
                        vendedor7Controller.actualizarTablasContactosClientes7();
                        vendedor8Controller.actualizarTablasContactosClientes8();
                        break;
                    case 9:
                        vendedor1Controller.actualizarTablasContactosClientes1();
                        vendedor2Controller.actualizarTablasContactosClientes2();
                        vendedor3Controller.actualizarTablasContactosClientes3();
                        vendedor4Controller.actualizarTablasContactosClientes4();
                        vendedor5Controller.actualizarTablasContactosClientes5();
                        vendedor6Controller.actualizarTablasContactosClientes6();
                        vendedor7Controller.actualizarTablasContactosClientes7();
                        vendedor8Controller.actualizarTablasContactosClientes8();
                        vendedor9Controller.actualizarTablasContactosClientes9();
                        break;
                    case 10:
                        vendedor1Controller.actualizarTablasContactosClientes1();
                        vendedor2Controller.actualizarTablasContactosClientes2();
                        vendedor3Controller.actualizarTablasContactosClientes3();
                        vendedor4Controller.actualizarTablasContactosClientes4();
                        vendedor5Controller.actualizarTablasContactosClientes5();
                        vendedor6Controller.actualizarTablasContactosClientes6();
                        vendedor7Controller.actualizarTablasContactosClientes7();
                        vendedor8Controller.actualizarTablasContactosClientes8();
                        vendedor9Controller.actualizarTablasContactosClientes9();
                        vendedor10Controller.actualizarTablasContactosClientes10();
                        break;
                    default:
                        MensajeUtil.mostrarMensaje("Notificacion vendedor", "Longitud de la Lista No Reconocida", "La Lista de empleados no coincide", Alert.AlertType.ERROR);
                }
                MensajeUtil.mostrarMensaje("Notificación vendedor", "Vendedor creado", "El Vendedor se ha creado con éxito", Alert.AlertType.INFORMATION);
                limpiarCamposVendedor();
            } else {
                MensajeUtil.mostrarMensaje("Notificación vendedor", "Vendedor no creado", "El vendedor no se ha creado con éxito", Alert.AlertType.INFORMATION);
            }
        } else {
            MensajeUtil.mostrarMensaje("Notificación vendedor", "Vendedor no creado", "Los datos ingresados son " +
                    "inválidos", Alert.AlertType.ERROR);
        }
    }

    private void eliminarVendedor(String cedula) {
        boolean vendedorEliminado = false;
        int indiceVendedor = -1;
        if (vendedorSeleccionado != null) {
            indiceVendedor = modelFactoryController.getIndiceVendedor(vendedorSeleccionado);
            //indiceVendedor = modelFactoryController.getMarketplace().obtenerCampoDisponibleVendedor();
            if (MensajeUtil.mostrarMensajeConfirmacion("¿Estás seguro de elmininar al vendedor?")) {
                vendedorEliminado = modelFactoryController.eliminarVendedor(vendedorSeleccionado.getCedula(), indiceVendedor);
                if (vendedorEliminado) {
                    listaVendedoresData.set(indiceVendedor, null);
                    actualizarTodo();
                    vendedorSeleccionado = null;
                    tableListaVendedores.getSelectionModel().clearSelection();
                    limpiarCamposVendedor();
                    MensajeUtil.mostrarMensaje("Notificación vendedor", "Vendedor eliminado", "El Vendedor se ha " +
                            "eliminado con éxito", Alert.AlertType.INFORMATION);
                } else {
                    MensajeUtil.mostrarMensaje("Notificación vendedor", "Vendedor no eliminado", "El Vendedor no se " +
                            "puede eliminar", Alert.AlertType.ERROR);
                }
            }
        } else {
            MensajeUtil.mostrarMensaje("Notificación vendedor", "Vendedor no seleccionado", "Seleccione un vendedor" +
                    " de la lista", Alert.AlertType.WARNING);
        }
    }

    private void inicializarVendedoresView() {
        this.colListaVendedoresNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.colListaVendedoresApellido.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        this.colListaVendedoresCedula.setCellValueFactory(new PropertyValueFactory<>("cedula"));
        tableListaVendedores.getItems().clear();
        tableListaVendedores.setItems(getListaVendedoresData());
        tableListaVendedores.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            vendedorSeleccionado = newSelection;
            mostrarInformacionVendedor(vendedorSeleccionado);
        });
        txtCedulaVendedor.setTextFormatter(new TextFormatter<>(TextFormatterUtil::integerFormat));
    }

    public ObservableList<Vendedor> getListaVendedoresData() {
        listaVendedoresData.addAll(modelFactoryController.obtenerVendedores());
        return listaVendedoresData;
    }

    private void mostrarInformacionVendedor(Vendedor vendedorSeleccionado) {
        if (vendedorSeleccionado != null) {
            txtNombreVendedor.setText(vendedorSeleccionado.getNombre());
            txtApellidoVendedor.setText(vendedorSeleccionado.getApellido());
            txtCedulaVendedor.setText(vendedorSeleccionado.getCedula());
            txtDireccionVendedor.setText(vendedorSeleccionado.getDireccion());
        }
    }

    private void limpiarCamposVendedor() {
        txtNombreVendedor.setText("");
        txtApellidoVendedor.setText("");
        txtCedulaVendedor.setText("");
        txtDireccionVendedor.setText("");
        txtUsuarioVendedor.setText("");
        txtContraseñaVendedor.setText("");
    }

    private boolean datosValidosVendedor(String nombre, String apellido, String cedula, String direccion, String usuario, String contrasenia) {
        String mensaje = "";
        if (nombre == null || nombre.equals(""))
            mensaje += "El nombre es invalido \n";
        if (apellido == null || apellido.equals(""))
            mensaje += "El apellido es invalido \n";
        if (cedula == null || cedula.equals(""))
            mensaje += "El documento es invalido \n";
        if (direccion == null || direccion.equals(""))
            mensaje += "La direccion es invalida \n";
        if (usuario == null || usuario.equals(""))
            mensaje += "La direccion es invalida \n";
        if (contrasenia == null || contrasenia.equals(""))
            mensaje += "La direccion es invalida \n";
        if (mensaje.equals(""))
            return true;
        MensajeUtil.mostrarMensaje("Notificación vendedor", "Datos inválidos", mensaje, Alert.AlertType.WARNING);
        return false;
    }
    public void actualizarTodo(){
        vendedor1Controller.actualizarTablasContactosClientes1();
        vendedor2Controller.actualizarTablasContactosClientes2();
        vendedor3Controller.actualizarTablasContactosClientes3();
        vendedor4Controller.actualizarTablasContactosClientes4();
        vendedor5Controller.actualizarTablasContactosClientes5();
        vendedor6Controller.actualizarTablasContactosClientes6();
        vendedor7Controller.actualizarTablasContactosClientes7();
        vendedor8Controller.actualizarTablasContactosClientes8();
        vendedor9Controller.actualizarTablasContactosClientes9();
        vendedor10Controller.actualizarTablasContactosClientes10();

//        vendedor1Controller.actualizarTablasProductosVendedor1();
//        vendedor2Controller.actualizarTablasProductosVendedor2();
//        vendedor3Controller.actualizarTablasProductosVendedor3();
//        vendedor4Controller.actualizarTablasProductosVendedor4();
//        vendedor5Controller.actualizarTablasProductosVendedor5();
//        vendedor6Controller.actualizarTablasProductosVendedor6();
//        vendedor7Controller.actualizarTablasProductosVendedor7();
//        vendedor8Controller.actualizarTablasProductosVendedor8();
//        vendedor9Controller.actualizarTablasProductosVendedor9();
//        vendedor10Controller.actualizarTablasProductosVendedor10();
    }
}

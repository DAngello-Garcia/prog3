package co.edu.uniquindio.proyectofinal.controllers;

import co.edu.uniquindio.proyectofinal.exceptions.VendedorNoExisteException;
import co.edu.uniquindio.proyectofinal.model.*;
import co.edu.uniquindio.proyectofinal.utils.MensajeUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDateTime;
import java.util.Objects;

public class Vendedor6Controller {
    ModelFactoryController modelFactoryController;
    LoginController loginController;

    ObservableList<Producto> listaProductosMuroData6 = FXCollections.observableArrayList();
    Producto productoMuroSeleccionado6;
    Vendedor vendedorLogueado6;


    ObservableList<Comentario> listaComentariosVendedorMuroData6 = FXCollections.observableArrayList();

    ObservableList<Like> listaLikesVendedorMuroData6 = FXCollections.observableArrayList();

    ObservableList<Vendedor> listaContactosSugeridosContactosData6 = FXCollections.observableArrayList();
    Vendedor contactoSugeridoContactosSeleccionado6;

    ObservableList<Producto> listaProductosGestionData6 = FXCollections.observableArrayList();
    Producto productoGestionSeleccionado6;

    ObservableList<Vendedor> listaContactosSugeridosGestionData6 = FXCollections.observableArrayList();
    Vendedor contactoSugeridoGestionSeleccionado6;

    ObservableList<Vendedor> listaContactosChatData6 = FXCollections.observableArrayList();
    Vendedor contactoChatSeleccionado6;
    @FXML
    private TableColumn<Comentario, String> colComentariosComentario6;

    @FXML
    private TableColumn<Comentario, String> colComentariosEmisor6;

    @FXML
    private TableColumn<Comentario, LocalDateTime> colComentariosFecha6;

    @FXML
    private TableColumn<Comentario, String> colComentariosProducto6;

    @FXML
    private TableColumn<Vendedor, String> colContactosSugeridosApellido6;

    @FXML
    private TableColumn<Vendedor, String> colContactosSugeridosNombre6;

    @FXML
    private TableColumn<Producto, EstadoProducto> colGestionEstado6;

    @FXML
    private TableColumn<Producto, LocalDateTime> colGestionFecha6;
    //gestión vendedor 660 fecha 600 muro fecha 650 vendedor 668 fecha abajo 660

    @FXML
    private TableColumn<Producto, Integer> colGestionLikes6;

    @FXML
    private TableColumn<Producto, String> colGestionNombre6;

    @FXML
    private TableColumn<Producto, Double> colGestionPrecio6;

    @FXML
    private TableColumn<Producto, String> colGestionVendedor6;

    @FXML
    private TableColumn<Like, String> colLikesEmisor6;

    @FXML
    private TableColumn<Comentario, LocalDateTime> colLikesFecha6;

    @FXML
    private TableColumn<Like, String> colLikesProducto6;

    @FXML
    private TableColumn<Vendedor, String> colListaContactosApellido6;

    @FXML
    private TableColumn<Vendedor, String> colListaContactosNombre6;

    @FXML
    private TableColumn<Producto, Integer> colListaLikes6;

    @FXML
    private TableColumn<Producto, EstadoProducto> colListaProductosEstado6;

    @FXML
    private TableColumn<Producto, String> colListaProductosNombre6;

    @FXML
    private TableColumn<Producto, Double> colListaProductosPrecio6;

    @FXML
    private TableColumn<Producto, String> colListaProductosVendedor6;

    @FXML
    private TableColumn<Producto, LocalDateTime> colListaProductosfecha6;

    @FXML
    private TableColumn<Vendedor, String> colListaSugeridosApellido6;

    @FXML
    private TableColumn<Vendedor, String> colListaSugeridosNombre6;

    @FXML
    private TextArea tAComentar6;

    @FXML
    private TableView<Vendedor> tablaContactosSugeridos6;

    @FXML
    private TableView<Comentario> tableComentariosVendedor6;

    @FXML
    private TableView<Like> tableLikeVendedor6;

    @FXML
    private TableView<Vendedor> tableListaContactos6;

    @FXML
    private TableView<Producto> tableListaProductos6;

    @FXML
    private TableView<Producto> tableListaProductosGestion6;

    @FXML
    private TableView<Vendedor> tableListaSugeridosGestion6;

    @FXML
    private TextField tfGestionNombre6;

    @FXML
    private TextField tfGestionPrecio6;
    @FXML
    private TextField tfUser;
    @FXML
    private PasswordField pfPass;
    @FXML
    private Button btnLogin;
    @FXML
    private Tab tabChat;
    @FXML
    private Tab tabContactos;
    @FXML
    private Tab tabGestion;

    @FXML
    private ComboBox cbEstado;
    String userVendedor;

    @FXML
    public void initialize() {
        modelFactoryController = ModelFactoryController.getInstance();
        loginController = new LoginController(modelFactoryController);
        inicializarVendedorView6();
    }

    @FXML
    void loginVendedor(ActionEvent event) {
        String user = tfUser.getText();
        String pass = pfPass.getText();
        if (loginController.loginVendedor(user, pass, 5)) {
            userVendedor = user;
            tabChat.setDisable(false);
            tabGestion.setDisable(false);
            vendedorLogueado6 = modelFactoryController.getMarketplace().getListaVendedores().get(5);

        }
    }

    public void onComentarVendedor6Click(ActionEvent actionEvent) {
        Vendedor vendedorComentario = vendedorLogueado6;
        Producto productoComentario = productoMuroSeleccionado6;
        agregarComentario(productoComentario,vendedorComentario);
        tableComentariosVendedor6.refresh();
        tableListaProductos6.getSelectionModel().clearSelection();
    }

    public void onLikeVendedor6Click(ActionEvent actionEvent) {
        // Obtener el producto y el vendedor logueado correspondientes a la acción
        Producto producto = productoMuroSeleccionado6;
        if(vendedorLogueado6!=null){
            Vendedor vendedorInicializado = vendedorLogueado6;
            agregarLike(producto, vendedorInicializado);
            tableListaProductos6.refresh();
            tableListaProductos6.getSelectionModel().clearSelection();
        } else{
            MensajeUtil.mostrarMensaje("Error","Vendedor no logueado", " Debe loguearse como vendedor",Alert.AlertType.ERROR);
        }

    }

    public void onAgregar6Click(ActionEvent actionEvent) {
    }

    public void onAnadir6Click(ActionEvent actionEvent) throws VendedorNoExisteException {
        crearProducto();
    }

    public void onBuscar6Click(ActionEvent actionEvent) {
    }

    public void onModificar6Click(ActionEvent actionEvent) throws VendedorNoExisteException {
        modificarProducto();
    }

    private void modificarProducto() throws VendedorNoExisteException {
        String nombre = tfGestionNombre6.getText();
        double precio = Double.parseDouble(tfGestionPrecio6.getText());
        EstadoProducto estado = (EstadoProducto) cbEstado.getValue();
        String id = productoGestionSeleccionado6.getId();
        Producto producto = null;
        if (datosValidosProducto(nombre, precio, estado)) {
            Vendedor vendedor = modelFactoryController.retornarVendedor(userVendedor);
            producto = modelFactoryController.actualizarProducto(nombre, precio, estado, id, vendedor);
            if (producto != null) {
                int i1 = listaProductosMuroData6.indexOf(productoGestionSeleccionado6);
                int i6 = listaProductosGestionData6.indexOf(productoGestionSeleccionado6);
                listaProductosMuroData6.set(i1, producto);
                listaProductosGestionData6.set(i6, producto);
                tableListaProductosGestion6.refresh();
                tableListaProductos6.refresh();
                MensajeUtil.mostrarMensaje("Notificación producto", "producto modificado", "El producto se ha modificado " +
                        "con " +
                        "éxito", Alert.AlertType.INFORMATION);
                limpiarCamposProducto();
            } else {
                MensajeUtil.mostrarMensaje("Notificación producto", "producto no modificado", "El producto no se ha " +
                        "modificado con éxito", Alert.AlertType.INFORMATION);
            }
        } else {
            MensajeUtil.mostrarMensaje("Notificación producto", "producto no modificado", "Los datos ingresados son " +
                    "inválidos", Alert.AlertType.ERROR);
        }
    }

    public void onEliminar6Click(ActionEvent actionEvent) throws VendedorNoExisteException {
        eliminarProducto();
    }

    public void onCancelar6Click(ActionEvent actionEvent) {
        tableListaProductosGestion6.getSelectionModel().clearSelection();
        limpiarCamposProducto();
    }

    private void crearProducto() throws VendedorNoExisteException {
        String nombre = tfGestionNombre6.getText();
        double precio = Double.parseDouble(tfGestionPrecio6.getText());
        EstadoProducto estado = (EstadoProducto) cbEstado.getValue();
        if (datosValidosProducto(nombre, precio, estado)) {
            Producto producto = null;
            Vendedor vendedor = modelFactoryController.retornarVendedor(userVendedor);
            producto = modelFactoryController.crearProducto(nombre, precio, estado, vendedor);
            if (producto != null) {
                listaProductosMuroData6.add(producto);
                listaProductosGestionData6.add(producto);
                MensajeUtil.mostrarMensaje("Notificación producto", "producto creado", "El producto se ha creado con " +
                        "éxito", Alert.AlertType.INFORMATION);
                limpiarCamposProducto();
            } else {
                MensajeUtil.mostrarMensaje("Notificación producto", "producto no creado", "El producto no se ha " +
                        "creado con éxito", Alert.AlertType.INFORMATION);
            }
        } else {
            MensajeUtil.mostrarMensaje("Notificación producto", "producto no creado", "Los datos ingresados son " +
                    "inválidos", Alert.AlertType.ERROR);
        }
    }

    private void eliminarProducto() throws VendedorNoExisteException {
        boolean productoEliminado = false;
        if (productoGestionSeleccionado6 != null) {
            String id = productoGestionSeleccionado6.getId();
            if (MensajeUtil.mostrarMensajeConfirmacion("¿Estás seguro de elmininar el producto?")) {
                Vendedor vendedor = modelFactoryController.retornarVendedor(userVendedor);
                int index = modelFactoryController.getIndiceVendedor(vendedor);
                productoEliminado = modelFactoryController.eliminarProducto(id, index);
                if (productoEliminado) {
                    listaProductosGestionData6.remove(productoGestionSeleccionado6);
                    listaProductosMuroData6.remove(productoGestionSeleccionado6);
                    productoGestionSeleccionado6 = null;
                    tableListaProductosGestion6.getSelectionModel().clearSelection();
                    limpiarCamposProducto();
                    MensajeUtil.mostrarMensaje("Notificación producto", "producto eliminado", "El producto se ha " +
                            "eliminado con éxito", Alert.AlertType.INFORMATION);
                } else {
                    MensajeUtil.mostrarMensaje("Notificación producto", "producto no eliminado", "El producto no se " +
                            "puede eliminar", Alert.AlertType.ERROR);
                }
            }
        } else {
            MensajeUtil.mostrarMensaje("Notificación producto", "producto no seleccionado", "Seleccione un producto" +
                    " de la lista", Alert.AlertType.WARNING);
        }
    }
    public void agregarLike(Producto productoMuroSeleccionado6, Vendedor vendedorLogueado6) {
        try{
            Objects.requireNonNull(productoMuroSeleccionado6, "Es necesario que seleccione un producto");
            // Verificar si el vendedor logueado ya ha dado like
            boolean vendedorDioLike = false;
            for (Like like : productoMuroSeleccionado6.getListaLikes()) {
                if (like.getVendedor().getUsuario().equals(vendedorLogueado6.getUsuario())) {
                    vendedorDioLike = true;
                    break;
                }
            }

            if (vendedorDioLike) {
                MensajeUtil.mostrarMensaje("Información","El vendedor ya le dió Like", "El vendedor ya le dió like", Alert.AlertType.INFORMATION);
            } else {
                // Si el vendedor no ha dado like, se incrementa el contador de likes
                productoMuroSeleccionado6.incrementarLikes();
                // Agregar un nuevo like a la lista de likes del producto
                Like nuevoLike = new Like(productoMuroSeleccionado6, vendedorLogueado6, LocalDateTime.now());
                productoMuroSeleccionado6.getListaLikes().add(nuevoLike);
            }}catch (Exception e){
            MensajeUtil.mostrarMensaje("Alerta","Seleccione un producto",e.getMessage(),Alert.AlertType.ERROR);
        }
    }
    public void agregarComentario(Producto productoMuroSeleccionado6,Vendedor vendedorLogueado6){
        try{
            Objects.requireNonNull(productoMuroSeleccionado6,"Es necesario que seleccione un producto");
            String ComentarioTexto  =  tAComentar6.getText();
            if(!ComentarioTexto.isEmpty()){
                LocalDateTime fechaActual = LocalDateTime.now();

                Comentario comentario = new Comentario(productoMuroSeleccionado6,vendedorLogueado6,fechaActual,ComentarioTexto);

                productoMuroSeleccionado6.getListaComentarios().add(comentario);
                tAComentar6.clear();

            }
            else {
                MensajeUtil.mostrarMensaje("Error","Comentario vacio","El comentario no puede estar vacio", Alert.AlertType.ERROR);

            }}catch (Exception e){
            MensajeUtil.mostrarMensaje("Error", "Seleccione un producto,",e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void limpiarCamposProducto() {
        tfGestionNombre6.setText("");
        tfGestionPrecio6.setText("");
        cbEstado.setValue(null);
    }

    private boolean datosValidosProducto(String nombre, double precio, EstadoProducto estado) {
        String mensaje = "";

        if (nombre == null || nombre.equals(""))
            mensaje += "El nombre es inválido \n";

        if (precio <= 0)
            mensaje += "El precio es inválido \n";

        if(estado == null)
            mensaje += "El estado es inválido \n";

        if (mensaje.equals("")) {
            return true;
        } else {
            MensajeUtil.mostrarMensaje("Notificación producto", "Datos inválidos", mensaje, Alert.AlertType.WARNING);
            return false;
        }
    }

    private void inicializarVendedorView6() {
        inicializarProductosMuro6();
        inicializarComentariosMuro6();
        inicializarLikesMuro6();
        inicializarProductosGestion6();
        inicializarVendedoresSugeridosContactos6();
        inicializarListaSugeridosGestion6();
        inicializarContactosChat6();
    }

    private void inicializarProductosMuro6() {

        this.colListaProductosNombre6.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.colListaProductosPrecio6.setCellValueFactory(new PropertyValueFactory<>("precio"));
        this.colListaProductosEstado6.setCellValueFactory(new PropertyValueFactory<>("estado"));
        this.colListaProductosfecha6.setCellValueFactory(new PropertyValueFactory<>("fechaPublicacion"));
        this.colListaProductosVendedor6.setCellValueFactory(new PropertyValueFactory<>("idVendedor"));
        this.colListaLikes6.setCellValueFactory(new PropertyValueFactory<>("contadorLikes"));

        tableListaProductos6.getItems().clear();
        tableListaProductos6.setItems(getListaProductosMuroData6());
        colListaProductosfecha6.setSortType(TableColumn.SortType.ASCENDING);

        tableListaProductos6.getSortOrder().add(colListaProductosfecha6);
        tableListaProductos6.sort();

        tableListaProductos6.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {

            productoMuroSeleccionado6 = newSelection;
            mostrarInformacionComentariosMuro6(productoMuroSeleccionado6);
            mostrarInformacionLikeMuro6(productoMuroSeleccionado6);
        });
    }

    public ObservableList<Producto> getListaProductosMuroData6() {
        listaProductosMuroData6.addAll(modelFactoryController.obtenerProductos());
        return listaProductosMuroData6;
    }

    private void inicializarComentariosMuro6() {

        this.colComentariosFecha6.setCellValueFactory(new PropertyValueFactory<>("fechaComentario"));
        this.colComentariosProducto6.setCellValueFactory(new PropertyValueFactory<>("nombreProducto"));
        this.colComentariosEmisor6.setCellValueFactory(new PropertyValueFactory<>("nombreVendedor"));
        this.colComentariosComentario6.setCellValueFactory(new PropertyValueFactory<>("comentario"));

    }

    private void mostrarInformacionComentariosMuro6(Producto productoSeleccionado) {
        if (productoSeleccionado != null) {
            tableComentariosVendedor6.getItems().clear();
            tableComentariosVendedor6.setItems((getListaComentariosVendedorMuroData6(productoSeleccionado)));
        }
    }

    public ObservableList<Comentario> getListaComentariosVendedorMuroData6(Producto productoSeleccionado) {
        listaComentariosVendedorMuroData6.addAll(productoSeleccionado.getListaComentarios());
        return listaComentariosVendedorMuroData6;
    }

    private void inicializarLikesMuro6() {

        this.colLikesFecha6.setCellValueFactory(new PropertyValueFactory<>("fechaLike"));
        this.colLikesProducto6.setCellValueFactory(new PropertyValueFactory<>("nombreProducto"));
        this.colLikesEmisor6.setCellValueFactory(new PropertyValueFactory<>("nombreVendedor"));

    }

    private void mostrarInformacionLikeMuro6(Producto productoSeleccionado) {
        if (productoSeleccionado != null) {
            tableLikeVendedor6.getItems().clear();
            tableLikeVendedor6.setItems((getListaLikesVendedorMuroData6(productoSeleccionado)));
        }
    }

    public ObservableList<Like> getListaLikesVendedorMuroData6(Producto productoSeleccionado) {
        listaLikesVendedorMuroData6.addAll(productoSeleccionado.getListaLikes());
        return listaLikesVendedorMuroData6;
    }

    private void inicializarVendedoresSugeridosContactos6() {

        this.colContactosSugeridosNombre6.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.colContactosSugeridosApellido6.setCellValueFactory(new PropertyValueFactory<>("apellido"));

        tablaContactosSugeridos6.getItems().clear();
        tablaContactosSugeridos6.setItems((getListaContactosSugeridosContactosData6()));

        tablaContactosSugeridos6.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {

            contactoSugeridoContactosSeleccionado6 = newSelection;

        });
    }

    public ObservableList<Vendedor> getListaContactosSugeridosContactosData6() {
        listaContactosSugeridosContactosData6.addAll(modelFactoryController.obtenerContactosSugeridosVendedorN(5));
        return listaContactosSugeridosContactosData6;
    }

    private void inicializarProductosGestion6() {

        this.colGestionNombre6.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.colGestionPrecio6.setCellValueFactory(new PropertyValueFactory<>("precio"));
        this.colGestionEstado6.setCellValueFactory(new PropertyValueFactory<>("estado"));
        this.colGestionFecha6.setCellValueFactory(new PropertyValueFactory<>("fechaPublicacion"));
        this.colGestionVendedor6.setCellValueFactory(new PropertyValueFactory<>("idVendedor"));
        this.colGestionLikes6.setCellValueFactory(new PropertyValueFactory<>("contadorLikes"));
        cbEstado.setItems(FXCollections.observableArrayList(EstadoProducto.values()));

        tableListaProductosGestion6.getItems().clear();
        tableListaProductosGestion6.setItems(getListaProductosGestionData6());
        colGestionFecha6.setSortType(TableColumn.SortType.ASCENDING);
        tableListaProductosGestion6.getSortOrder().add(colGestionFecha6);
        tableListaProductosGestion6.sort();
        tableListaProductosGestion6.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {

            productoGestionSeleccionado6 = newSelection;
            mostrarInformacionProductosGestion6(productoGestionSeleccionado6);
        });
    }

    public ObservableList<Producto> getListaProductosGestionData6() {
        listaProductosGestionData6.addAll(modelFactoryController.obtenerProductosVendedorN(5));
        return listaProductosGestionData6;
    }

    private void mostrarInformacionProductosGestion6(Producto productoSeleccionado) {
        if (productoSeleccionado != null) {
            tfGestionNombre6.setText(productoSeleccionado.getNombre());
            tfGestionPrecio6.setText(String.valueOf((int) productoSeleccionado.getPrecio()));
            cbEstado.setValue(productoSeleccionado.getEstado());
        }
    }

    private void inicializarListaSugeridosGestion6() {

        this.colListaSugeridosNombre6.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.colListaSugeridosApellido6.setCellValueFactory(new PropertyValueFactory<>("apellido"));

        tableListaSugeridosGestion6.getItems().clear();
        tableListaSugeridosGestion6.setItems((getListaSugeridosGestionData6()));

        tableListaSugeridosGestion6.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {

            contactoSugeridoGestionSeleccionado6 = newSelection;

        });
    }

    public ObservableList<Vendedor> getListaSugeridosGestionData6() {
        listaContactosSugeridosGestionData6.addAll(modelFactoryController.obtenerListaContactosSugeridosVendedor1());
        return listaContactosSugeridosGestionData6;
    }

    private void inicializarContactosChat6() {

        this.colListaContactosNombre6.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.colListaContactosApellido6.setCellValueFactory(new PropertyValueFactory<>("apellido"));

        tableListaContactos6.getItems().clear();
        tableListaContactos6.setItems((getListaContactosChat6()));

        tableListaContactos6.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {

            contactoChatSeleccionado6 = newSelection;

        });
    }

    public ObservableList<Vendedor> getListaContactosChat6() {
        listaContactosChatData6.addAll(modelFactoryController.obtenerContactosSugeridosVendedorN(5));
        return listaContactosChatData6;
    }
    public void actualizarTablasContactosClientes6(){
        tablaContactosSugeridos6.getItems().clear();
        tablaContactosSugeridos6.setItems((getListaContactosSugeridosContactosData6()));
        tableListaSugeridosGestion6.getItems().clear();
        tableListaSugeridosGestion6.setItems((getListaSugeridosGestionData6()));
        tableListaContactos6.getItems().clear();
        tableListaContactos6.setItems((getListaContactosChat6()));
    }
//    public void actualizarTablasProductosVendedor6(){
//        tableListaProductos6.getItems().clear();
//        tableListaProductos6.setItems((getListaProductosMuroData6()));
//        tableListaProductosGestion6.getItems().clear();
//        tableListaProductosGestion6.setItems((getListaProductosGestionData6()));
//    }
}

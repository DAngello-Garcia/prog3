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

public class Vendedor10Controller {
    ModelFactoryController modelFactoryController;
    LoginController loginController;

    ObservableList<Producto> listaProductosMuroData10 = FXCollections.observableArrayList();
    Producto productoMuroSeleccionado10;
    Vendedor vendedorLogueado10;

    ObservableList<Comentario> listaComentariosVendedorMuroData10 = FXCollections.observableArrayList();

    ObservableList<Like> listaLikesVendedorMuroData10 = FXCollections.observableArrayList();

    ObservableList<Vendedor> listaContactosSugeridosContactosData10 = FXCollections.observableArrayList();
    Vendedor contactoSugeridoContactosSeleccionado10;

    ObservableList<Producto> listaProductosGestionData10 = FXCollections.observableArrayList();
    Producto productoGestionSeleccionado10;

    ObservableList<Vendedor> listaContactosSugeridosGestionData10 = FXCollections.observableArrayList();
    Vendedor contactoSugeridoGestionSeleccionado10;

    ObservableList<Vendedor> listaContactosChatData10 = FXCollections.observableArrayList();
    Vendedor contactoChatSeleccionado10;
    @FXML
    private TableColumn<Comentario, String> colComentariosComentario10;

    @FXML
    private TableColumn<Comentario, String> colComentariosEmisor10;

    @FXML
    private TableColumn<Comentario, LocalDateTime> colComentariosFecha10;

    @FXML
    private TableColumn<Comentario, String> colComentariosProducto10;

    @FXML
    private TableColumn<Vendedor, String> colContactosSugeridosApellido10;

    @FXML
    private TableColumn<Vendedor, String> colContactosSugeridosNombre10;

    @FXML
    private TableColumn<Producto, EstadoProducto> colGestionEstado10;

    @FXML
    private TableColumn<Producto, LocalDateTime> colGestionFecha10;
    //gestión vendedor 10100 fecha 1000 muro fecha 1050 vendedor 10108 fecha abajo 10100

    @FXML
    private TableColumn<Producto, Integer> colGestionLikes10;

    @FXML
    private TableColumn<Producto, String> colGestionNombre10;

    @FXML
    private TableColumn<Producto, Double> colGestionPrecio10;

    @FXML
    private TableColumn<Producto, String> colGestionVendedor10;

    @FXML
    private TableColumn<Like, String> colLikesEmisor10;

    @FXML
    private TableColumn<Comentario, LocalDateTime> colLikesFecha10;

    @FXML
    private TableColumn<Like, String> colLikesProducto10;

    @FXML
    private TableColumn<Vendedor, String> colListaContactosApellido10;

    @FXML
    private TableColumn<Vendedor, String> colListaContactosNombre10;

    @FXML
    private TableColumn<Producto, Integer> colListaLikes10;

    @FXML
    private TableColumn<Producto, EstadoProducto> colListaProductosEstado10;

    @FXML
    private TableColumn<Producto, String> colListaProductosNombre10;

    @FXML
    private TableColumn<Producto, Double> colListaProductosPrecio10;

    @FXML
    private TableColumn<Producto, String> colListaProductosVendedor10;

    @FXML
    private TableColumn<Producto, LocalDateTime> colListaProductosfecha10;

    @FXML
    private TableColumn<Vendedor, String> colListaSugeridosApellido10;

    @FXML
    private TableColumn<Vendedor, String> colListaSugeridosNombre10;

    @FXML
    private TextArea tAComentar10;

    @FXML
    private TableView<Vendedor> tablaContactosSugeridos10;

    @FXML
    private TableView<Comentario> tableComentariosVendedor10;

    @FXML
    private TableView<Like> tableLikeVendedor10;

    @FXML
    private TableView<Vendedor> tableListaContactos10;

    @FXML
    private TableView<Producto> tableListaProductos10;

    @FXML
    private TableView<Producto> tableListaProductosGestion10;

    @FXML
    private TableView<Vendedor> tableListaSugeridosGestion10;

    @FXML
    private TextField tfGestionNombre10;

    @FXML
    private TextField tfGestionPrecio10;
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
        inicializarVendedorView10();
    }

    @FXML
    void loginVendedor(ActionEvent event) {
        String user = tfUser.getText();
        String pass = pfPass.getText();
        if (loginController.loginVendedor(user, pass, 9)) {
            userVendedor = user;
            tabChat.setDisable(false);
            tabGestion.setDisable(false);
            vendedorLogueado10 = modelFactoryController.getMarketplace().getListaVendedores().get(9);

        }
    }

    public void onComentarVendedor10Click(ActionEvent actionEvent) {
        Vendedor vendedorComentario = vendedorLogueado10;
        Producto productoComentario = productoMuroSeleccionado10;
        agregarComentario(productoComentario,vendedorComentario);
        tableComentariosVendedor10.refresh();
        tableListaProductos10.getSelectionModel().clearSelection();
    }

    public void onLikeVendedor10Click(ActionEvent actionEvent) {
        // Obtener el producto y el vendedor logueado correspondientes a la acción
        Producto producto = productoMuroSeleccionado10;
        if(vendedorLogueado10!=null){
            Vendedor vendedorInicializado = vendedorLogueado10;
            agregarLike(producto, vendedorInicializado);
            tableListaProductos10.refresh();
            tableListaProductos10.getSelectionModel().clearSelection();
        } else{
            MensajeUtil.mostrarMensaje("Error","Vendedor no logueado", " Debe loguearse como vendedor",Alert.AlertType.ERROR);
        }

    }

    public void onAgregar10Click(ActionEvent actionEvent) {
    }

    public void onAnadir10Click(ActionEvent actionEvent) throws VendedorNoExisteException {
        crearProducto();
    }

    public void onBuscar10Click(ActionEvent actionEvent) {
    }

    public void onModificar10Click(ActionEvent actionEvent) throws VendedorNoExisteException {
        modificarProducto();
    }

    private void modificarProducto() throws VendedorNoExisteException {
        String nombre = tfGestionNombre10.getText();
        double precio = Double.parseDouble(tfGestionPrecio10.getText());
        EstadoProducto estado = (EstadoProducto) cbEstado.getValue();
        String id = productoGestionSeleccionado10.getId();
        Producto producto = null;
        if (datosValidosProducto(nombre, precio, estado)) {
            Vendedor vendedor = modelFactoryController.retornarVendedor(userVendedor);
            producto = modelFactoryController.actualizarProducto(nombre, precio, estado, id, vendedor);
            if (producto != null) {
                int i1 = listaProductosMuroData10.indexOf(productoGestionSeleccionado10);
                int i10 = listaProductosGestionData10.indexOf(productoGestionSeleccionado10);
                listaProductosMuroData10.set(i1, producto);
                listaProductosGestionData10.set(i10, producto);
                tableListaProductosGestion10.refresh();
                tableListaProductos10.refresh();
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

    public void onEliminar10Click(ActionEvent actionEvent) throws VendedorNoExisteException {
        eliminarProducto();
    }

    public void onCancelar10Click(ActionEvent actionEvent) {
        tableListaProductosGestion10.getSelectionModel().clearSelection();
        limpiarCamposProducto();
    }

    private void crearProducto() throws VendedorNoExisteException {
        String nombre = tfGestionNombre10.getText();
        double precio = Double.parseDouble(tfGestionPrecio10.getText());
        EstadoProducto estado = (EstadoProducto) cbEstado.getValue();
        if (datosValidosProducto(nombre, precio, estado)) {
            Producto producto = null;
            Vendedor vendedor = modelFactoryController.retornarVendedor(userVendedor);
            producto = modelFactoryController.crearProducto(nombre, precio, estado, vendedor);
            if (producto != null) {
                listaProductosMuroData10.add(producto);
                listaProductosGestionData10.add(producto);
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
        if (productoGestionSeleccionado10 != null) {
            String id = productoGestionSeleccionado10.getId();
            if (MensajeUtil.mostrarMensajeConfirmacion("¿Estás seguro de elmininar el producto?")) {
                Vendedor vendedor = modelFactoryController.retornarVendedor(userVendedor);
                int index = modelFactoryController.getIndiceVendedor(vendedor);
                productoEliminado = modelFactoryController.eliminarProducto(id, index);
                if (productoEliminado) {
                    listaProductosGestionData10.remove(productoGestionSeleccionado10);
                    listaProductosMuroData10.remove(productoGestionSeleccionado10);
                    productoGestionSeleccionado10 = null;
                    tableListaProductosGestion10.getSelectionModel().clearSelection();
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
    public void agregarLike(Producto productoMuroSeleccionado10, Vendedor vendedorLogueado10) {
        try{
            Objects.requireNonNull(productoMuroSeleccionado10, "Es necesario que seleccione un producto");
            // Verificar si el vendedor logueado ya ha dado like
            boolean vendedorDioLike = false;
            for (Like like : productoMuroSeleccionado10.getListaLikes()) {
                if (like.getVendedor().getUsuario().equals(vendedorLogueado10.getUsuario())) {
                    vendedorDioLike = true;
                    break;
                }
            }

            if (vendedorDioLike) {
                MensajeUtil.mostrarMensaje("Información","El vendedor ya le dió Like", "El vendedor ya le dió like", Alert.AlertType.INFORMATION);
            } else {
                // Si el vendedor no ha dado like, se incrementa el contador de likes
                productoMuroSeleccionado10.incrementarLikes();
                // Agregar un nuevo like a la lista de likes del producto
                Like nuevoLike = new Like(productoMuroSeleccionado10, vendedorLogueado10, LocalDateTime.now());
                productoMuroSeleccionado10.getListaLikes().add(nuevoLike);
            }}catch (Exception e){
            MensajeUtil.mostrarMensaje("Alerta","Seleccione un producto",e.getMessage(),Alert.AlertType.ERROR);
        }
    }
    public void agregarComentario(Producto productoMuroSeleccionado10,Vendedor vendedorLogueado10){
        try{
            Objects.requireNonNull(productoMuroSeleccionado10,"Es necesario que seleccione un producto");
            String ComentarioTexto  =  tAComentar10.getText();
            if(!ComentarioTexto.isEmpty()){
                LocalDateTime fechaActual = LocalDateTime.now();

                Comentario comentario = new Comentario(productoMuroSeleccionado10,vendedorLogueado10,fechaActual,ComentarioTexto);

                productoMuroSeleccionado10.getListaComentarios().add(comentario);
                tAComentar10.clear();

            }
            else {
                MensajeUtil.mostrarMensaje("Error","Comentario vacio","El comentario no puede estar vacio", Alert.AlertType.ERROR);

            }}catch (Exception e){
            MensajeUtil.mostrarMensaje("Error", "Seleccione un producto,",e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void limpiarCamposProducto() {
        tfGestionNombre10.setText("");
        tfGestionPrecio10.setText("");
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

    private void inicializarVendedorView10() {
        inicializarProductosMuro10();
        inicializarComentariosMuro10();
        inicializarLikesMuro10();
        inicializarProductosGestion10();
        inicializarVendedoresSugeridosContactos10();
        inicializarListaSugeridosGestion10();
        inicializarContactosChat10();
    }

    private void inicializarProductosMuro10() {

        this.colListaProductosNombre10.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.colListaProductosPrecio10.setCellValueFactory(new PropertyValueFactory<>("precio"));
        this.colListaProductosEstado10.setCellValueFactory(new PropertyValueFactory<>("estado"));
        this.colListaProductosfecha10.setCellValueFactory(new PropertyValueFactory<>("fechaPublicacion"));
        this.colListaProductosVendedor10.setCellValueFactory(new PropertyValueFactory<>("idVendedor"));
        this.colListaLikes10.setCellValueFactory(new PropertyValueFactory<>("contadorLikes"));

        tableListaProductos10.getItems().clear();
        tableListaProductos10.setItems(getListaProductosMuroData10());
        colListaProductosfecha10.setSortType(TableColumn.SortType.ASCENDING);

        tableListaProductos10.getSortOrder().add(colListaProductosfecha10);
        tableListaProductos10.sort();

        tableListaProductos10.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {

            productoMuroSeleccionado10 = newSelection;
            mostrarInformacionComentariosMuro10(productoMuroSeleccionado10);
            mostrarInformacionLikeMuro10(productoMuroSeleccionado10);
        });
    }

    public ObservableList<Producto> getListaProductosMuroData10() {
        listaProductosMuroData10.addAll(modelFactoryController.obtenerProductos());
        return listaProductosMuroData10;
    }

    private void inicializarComentariosMuro10() {

        this.colComentariosFecha10.setCellValueFactory(new PropertyValueFactory<>("fechaComentario"));
        this.colComentariosProducto10.setCellValueFactory(new PropertyValueFactory<>("nombreProducto"));
        this.colComentariosEmisor10.setCellValueFactory(new PropertyValueFactory<>("nombreVendedor"));
        this.colComentariosComentario10.setCellValueFactory(new PropertyValueFactory<>("comentario"));

    }

    private void mostrarInformacionComentariosMuro10(Producto productoSeleccionado) {
        if (productoSeleccionado != null) {
            tableComentariosVendedor10.getItems().clear();
            tableComentariosVendedor10.setItems((getListaComentariosVendedorMuroData10(productoSeleccionado)));
        }
    }

    public ObservableList<Comentario> getListaComentariosVendedorMuroData10(Producto productoSeleccionado) {
        listaComentariosVendedorMuroData10.addAll(productoSeleccionado.getListaComentarios());
        return listaComentariosVendedorMuroData10;
    }

    private void inicializarLikesMuro10() {

        this.colLikesFecha10.setCellValueFactory(new PropertyValueFactory<>("fechaLike"));
        this.colLikesProducto10.setCellValueFactory(new PropertyValueFactory<>("nombreProducto"));
        this.colLikesEmisor10.setCellValueFactory(new PropertyValueFactory<>("nombreVendedor"));

    }

    private void mostrarInformacionLikeMuro10(Producto productoSeleccionado) {
        if (productoSeleccionado != null) {
            tableLikeVendedor10.getItems().clear();
            tableLikeVendedor10.setItems((getListaLikesVendedorMuroData10(productoSeleccionado)));
        }
    }

    public ObservableList<Like> getListaLikesVendedorMuroData10(Producto productoSeleccionado) {
        listaLikesVendedorMuroData10.addAll(productoSeleccionado.getListaLikes());
        return listaLikesVendedorMuroData10;
    }

    private void inicializarVendedoresSugeridosContactos10() {

        this.colContactosSugeridosNombre10.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.colContactosSugeridosApellido10.setCellValueFactory(new PropertyValueFactory<>("apellido"));

        tablaContactosSugeridos10.getItems().clear();
        tablaContactosSugeridos10.setItems((getListaContactosSugeridosContactosData10()));

        tablaContactosSugeridos10.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {

            contactoSugeridoContactosSeleccionado10 = newSelection;

        });
    }

    public ObservableList<Vendedor> getListaContactosSugeridosContactosData10() {
        listaContactosSugeridosContactosData10.addAll(modelFactoryController.obtenerContactosSugeridosVendedorN(9));
        return listaContactosSugeridosContactosData10;
    }

    private void inicializarProductosGestion10() {

        this.colGestionNombre10.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.colGestionPrecio10.setCellValueFactory(new PropertyValueFactory<>("precio"));
        this.colGestionEstado10.setCellValueFactory(new PropertyValueFactory<>("estado"));
        this.colGestionFecha10.setCellValueFactory(new PropertyValueFactory<>("fechaPublicacion"));
        this.colGestionVendedor10.setCellValueFactory(new PropertyValueFactory<>("idVendedor"));
        this.colGestionLikes10.setCellValueFactory(new PropertyValueFactory<>("contadorLikes"));
        cbEstado.setItems(FXCollections.observableArrayList(EstadoProducto.values()));

        tableListaProductosGestion10.getItems().clear();
        tableListaProductosGestion10.setItems(getListaProductosGestionData10());
        colGestionFecha10.setSortType(TableColumn.SortType.ASCENDING);
        tableListaProductosGestion10.getSortOrder().add(colGestionFecha10);
        tableListaProductosGestion10.sort();
        tableListaProductosGestion10.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {

            productoGestionSeleccionado10 = newSelection;
            mostrarInformacionProductosGestion10(productoGestionSeleccionado10);
        });
    }

    public ObservableList<Producto> getListaProductosGestionData10() {
        listaProductosGestionData10.addAll(modelFactoryController.obtenerProductosVendedorN(9));
        return listaProductosGestionData10;
    }

    private void mostrarInformacionProductosGestion10(Producto productoSeleccionado) {
        if (productoSeleccionado != null) {
            tfGestionNombre10.setText(productoSeleccionado.getNombre());
            tfGestionPrecio10.setText(String.valueOf((int) productoSeleccionado.getPrecio()));
            cbEstado.setValue(productoSeleccionado.getEstado());
        }
    }

    private void inicializarListaSugeridosGestion10() {

        this.colListaSugeridosNombre10.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.colListaSugeridosApellido10.setCellValueFactory(new PropertyValueFactory<>("apellido"));

        tableListaSugeridosGestion10.getItems().clear();
        tableListaSugeridosGestion10.setItems((getListaSugeridosGestionData10()));

        tableListaSugeridosGestion10.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {

            contactoSugeridoGestionSeleccionado10 = newSelection;

        });
    }

    public ObservableList<Vendedor> getListaSugeridosGestionData10() {
        listaContactosSugeridosGestionData10.addAll(modelFactoryController.obtenerListaContactosSugeridosVendedor1());
        return listaContactosSugeridosGestionData10;
    }

    private void inicializarContactosChat10() {

        this.colListaContactosNombre10.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.colListaContactosApellido10.setCellValueFactory(new PropertyValueFactory<>("apellido"));

        tableListaContactos10.getItems().clear();
        tableListaContactos10.setItems((getListaContactosChat10()));

        tableListaContactos10.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {

            contactoChatSeleccionado10 = newSelection;

        });
    }

    public ObservableList<Vendedor> getListaContactosChat10() {
        listaContactosChatData10.addAll(modelFactoryController.obtenerContactosSugeridosVendedorN(9));
        return listaContactosChatData10;
    }
    public void actualizarTablasContactosClientes10(){
        tablaContactosSugeridos10.getItems().clear();
        tablaContactosSugeridos10.setItems((getListaContactosSugeridosContactosData10()));
        tableListaSugeridosGestion10.getItems().clear();
        tableListaSugeridosGestion10.setItems((getListaSugeridosGestionData10()));
        tableListaContactos10.getItems().clear();
        tableListaContactos10.setItems((getListaContactosChat10()));
    }
//    public void actualizarTablasProductosVendedor10(){
//        tableListaProductos10.getItems().clear();
//        tableListaProductos10.setItems((getListaProductosMuroData10()));
//        tableListaProductosGestion10.getItems().clear();
//        tableListaProductosGestion10.setItems((getListaProductosGestionData10()));
//    }
}

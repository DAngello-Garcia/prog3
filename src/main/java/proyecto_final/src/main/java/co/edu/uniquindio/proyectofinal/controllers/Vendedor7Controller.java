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

public class Vendedor7Controller {
    ModelFactoryController modelFactoryController;
    LoginController loginController;

    ObservableList<Producto> listaProductosMuroData7 = FXCollections.observableArrayList();
    Producto productoMuroSeleccionado7;
    Vendedor vendedorLogueado7;


    ObservableList<Comentario> listaComentariosVendedorMuroData7 = FXCollections.observableArrayList();

    ObservableList<Like> listaLikesVendedorMuroData7 = FXCollections.observableArrayList();

    ObservableList<Vendedor> listaContactosSugeridosContactosData7 = FXCollections.observableArrayList();
    Vendedor contactoSugeridoContactosSeleccionado7;

    ObservableList<Producto> listaProductosGestionData7 = FXCollections.observableArrayList();
    Producto productoGestionSeleccionado7;

    ObservableList<Vendedor> listaContactosSugeridosGestionData7 = FXCollections.observableArrayList();
    Vendedor contactoSugeridoGestionSeleccionado7;

    ObservableList<Vendedor> listaContactosChatData7 = FXCollections.observableArrayList();
    Vendedor contactoChatSeleccionado7;
    @FXML
    private TableColumn<Comentario, String> colComentariosComentario7;

    @FXML
    private TableColumn<Comentario, String> colComentariosEmisor7;

    @FXML
    private TableColumn<Comentario, LocalDateTime> colComentariosFecha7;

    @FXML
    private TableColumn<Comentario, String> colComentariosProducto7;

    @FXML
    private TableColumn<Vendedor, String> colContactosSugeridosApellido7;

    @FXML
    private TableColumn<Vendedor, String> colContactosSugeridosNombre7;

    @FXML
    private TableColumn<Producto, EstadoProducto> colGestionEstado7;

    @FXML
    private TableColumn<Producto, LocalDateTime> colGestionFecha7;
    //gestión vendedor 770 fecha 700 muro fecha 750 vendedor 778 fecha abajo 770

    @FXML
    private TableColumn<Producto, Integer> colGestionLikes7;

    @FXML
    private TableColumn<Producto, String> colGestionNombre7;

    @FXML
    private TableColumn<Producto, Double> colGestionPrecio7;

    @FXML
    private TableColumn<Producto, String> colGestionVendedor7;

    @FXML
    private TableColumn<Like, String> colLikesEmisor7;

    @FXML
    private TableColumn<Comentario, LocalDateTime> colLikesFecha7;

    @FXML
    private TableColumn<Like, String> colLikesProducto7;

    @FXML
    private TableColumn<Vendedor, String> colListaContactosApellido7;

    @FXML
    private TableColumn<Vendedor, String> colListaContactosNombre7;

    @FXML
    private TableColumn<Producto, Integer> colListaLikes7;

    @FXML
    private TableColumn<Producto, EstadoProducto> colListaProductosEstado7;

    @FXML
    private TableColumn<Producto, String> colListaProductosNombre7;

    @FXML
    private TableColumn<Producto, Double> colListaProductosPrecio7;

    @FXML
    private TableColumn<Producto, String> colListaProductosVendedor7;

    @FXML
    private TableColumn<Producto, LocalDateTime> colListaProductosfecha7;

    @FXML
    private TableColumn<Vendedor, String> colListaSugeridosApellido7;

    @FXML
    private TableColumn<Vendedor, String> colListaSugeridosNombre7;

    @FXML
    private TextArea tAComentar7;

    @FXML
    private TableView<Vendedor> tablaContactosSugeridos7;

    @FXML
    private TableView<Comentario> tableComentariosVendedor7;

    @FXML
    private TableView<Like> tableLikeVendedor7;

    @FXML
    private TableView<Vendedor> tableListaContactos7;

    @FXML
    private TableView<Producto> tableListaProductos7;

    @FXML
    private TableView<Producto> tableListaProductosGestion7;

    @FXML
    private TableView<Vendedor> tableListaSugeridosGestion7;

    @FXML
    private TextField tfGestionNombre7;

    @FXML
    private TextField tfGestionPrecio7;
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
        inicializarVendedorView7();
    }

    @FXML
    void loginVendedor(ActionEvent event) {
        String user = tfUser.getText();
        String pass = pfPass.getText();
        if (loginController.loginVendedor(user, pass, 6)) {
            userVendedor = user;
            tabChat.setDisable(false);
            tabGestion.setDisable(false);
            vendedorLogueado7 = modelFactoryController.getMarketplace().getListaVendedores().get(6);

        }
    }

    public void onComentarVendedor7Click(ActionEvent actionEvent) {
        Vendedor vendedorComentario = vendedorLogueado7;
        Producto productoComentario = productoMuroSeleccionado7;
        agregarComentario(productoComentario,vendedorComentario);
        tableComentariosVendedor7.refresh();
        tableListaProductos7.getSelectionModel().clearSelection();
    }

    public void onLikeVendedor7Click(ActionEvent actionEvent) {
        // Obtener el producto y el vendedor logueado correspondientes a la acción
        Producto producto = productoMuroSeleccionado7;
        if(vendedorLogueado7!=null){
            Vendedor vendedorInicializado = vendedorLogueado7;
            agregarLike(producto, vendedorInicializado);
            tableListaProductos7.refresh();
            tableListaProductos7.getSelectionModel().clearSelection();
        } else{
            MensajeUtil.mostrarMensaje("Error","Vendedor no logueado", " Debe loguearse como vendedor",Alert.AlertType.ERROR);
        }

    }

    public void onAgregar7Click(ActionEvent actionEvent) {
    }

    public void onAnadir7Click(ActionEvent actionEvent) throws VendedorNoExisteException {
        crearProducto();
    }

    public void onBuscar7Click(ActionEvent actionEvent) {
    }

    public void onModificar7Click(ActionEvent actionEvent) throws VendedorNoExisteException {
        modificarProducto();
    }

    private void modificarProducto() throws VendedorNoExisteException {
        String nombre = tfGestionNombre7.getText();
        double precio = Double.parseDouble(tfGestionPrecio7.getText());
        EstadoProducto estado = (EstadoProducto) cbEstado.getValue();
        String id = productoGestionSeleccionado7.getId();
        Producto producto = null;
        if (datosValidosProducto(nombre, precio, estado)) {
            Vendedor vendedor = modelFactoryController.retornarVendedor(userVendedor);
            producto = modelFactoryController.actualizarProducto(nombre, precio, estado, id, vendedor);
            if (producto != null) {
                int i1 = listaProductosMuroData7.indexOf(productoGestionSeleccionado7);
                int i7 = listaProductosGestionData7.indexOf(productoGestionSeleccionado7);
                listaProductosMuroData7.set(i1, producto);
                listaProductosGestionData7.set(i7, producto);
                tableListaProductosGestion7.refresh();
                tableListaProductos7.refresh();
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

    public void onEliminar7Click(ActionEvent actionEvent) throws VendedorNoExisteException {
        eliminarProducto();
    }

    public void onCancelar7Click(ActionEvent actionEvent) {
        tableListaProductosGestion7.getSelectionModel().clearSelection();
        limpiarCamposProducto();
    }

    private void crearProducto() throws VendedorNoExisteException {
        String nombre = tfGestionNombre7.getText();
        double precio = Double.parseDouble(tfGestionPrecio7.getText());
        EstadoProducto estado = (EstadoProducto) cbEstado.getValue();
        if (datosValidosProducto(nombre, precio, estado)) {
            Producto producto = null;
            Vendedor vendedor = modelFactoryController.retornarVendedor(userVendedor);
            producto = modelFactoryController.crearProducto(nombre, precio, estado, vendedor);
            if (producto != null) {
                listaProductosMuroData7.add(producto);
                listaProductosGestionData7.add(producto);
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
        if (productoGestionSeleccionado7 != null) {
            String id = productoGestionSeleccionado7.getId();
            if (MensajeUtil.mostrarMensajeConfirmacion("¿Estás seguro de elmininar el producto?")) {
                Vendedor vendedor = modelFactoryController.retornarVendedor(userVendedor);
                int index = modelFactoryController.getIndiceVendedor(vendedor);
                productoEliminado = modelFactoryController.eliminarProducto(id, index);
                if (productoEliminado) {
                    listaProductosGestionData7.remove(productoGestionSeleccionado7);
                    listaProductosMuroData7.remove(productoGestionSeleccionado7);
                    productoGestionSeleccionado7 = null;
                    tableListaProductosGestion7.getSelectionModel().clearSelection();
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
    public void agregarLike(Producto productoMuroSeleccionado7, Vendedor vendedorLogueado7) {
        try{
            Objects.requireNonNull(productoMuroSeleccionado7, "Es necesario que seleccione un producto");
            // Verificar si el vendedor logueado ya ha dado like
            boolean vendedorDioLike = false;
            for (Like like : productoMuroSeleccionado7.getListaLikes()) {
                if (like.getVendedor().getUsuario().equals(vendedorLogueado7.getUsuario())) {
                    vendedorDioLike = true;
                    break;
                }
            }

            if (vendedorDioLike) {
                MensajeUtil.mostrarMensaje("Información","El vendedor ya le dió Like", "El vendedor ya le dió like", Alert.AlertType.INFORMATION);
            } else {
                // Si el vendedor no ha dado like, se incrementa el contador de likes
                productoMuroSeleccionado7.incrementarLikes();
                // Agregar un nuevo like a la lista de likes del producto
                Like nuevoLike = new Like(productoMuroSeleccionado7, vendedorLogueado7, LocalDateTime.now());
                productoMuroSeleccionado7.getListaLikes().add(nuevoLike);
            }}catch (Exception e){
            MensajeUtil.mostrarMensaje("Alerta","Seleccione un producto",e.getMessage(),Alert.AlertType.ERROR);
        }
    }
    public void agregarComentario(Producto productoMuroSeleccionado7,Vendedor vendedorLogueado7){
        try{
            Objects.requireNonNull(productoMuroSeleccionado7,"Es necesario que seleccione un producto");
            String ComentarioTexto  =  tAComentar7.getText();
            if(!ComentarioTexto.isEmpty()){
                LocalDateTime fechaActual = LocalDateTime.now();

                Comentario comentario = new Comentario(productoMuroSeleccionado7,vendedorLogueado7,fechaActual,ComentarioTexto);

                productoMuroSeleccionado7.getListaComentarios().add(comentario);
                tAComentar7.clear();

            }
            else {
                MensajeUtil.mostrarMensaje("Error","Comentario vacio","El comentario no puede estar vacio", Alert.AlertType.ERROR);

            }}catch (Exception e){
            MensajeUtil.mostrarMensaje("Error", "Seleccione un producto,",e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void limpiarCamposProducto() {
        tfGestionNombre7.setText("");
        tfGestionPrecio7.setText("");
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

    private void inicializarVendedorView7() {
        inicializarProductosMuro7();
        inicializarComentariosMuro7();
        inicializarLikesMuro7();
        inicializarProductosGestion7();
        inicializarVendedoresSugeridosContactos7();
        inicializarListaSugeridosGestion7();
        inicializarContactosChat7();
    }

    private void inicializarProductosMuro7() {

        this.colListaProductosNombre7.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.colListaProductosPrecio7.setCellValueFactory(new PropertyValueFactory<>("precio"));
        this.colListaProductosEstado7.setCellValueFactory(new PropertyValueFactory<>("estado"));
        this.colListaProductosfecha7.setCellValueFactory(new PropertyValueFactory<>("fechaPublicacion"));
        this.colListaProductosVendedor7.setCellValueFactory(new PropertyValueFactory<>("idVendedor"));
        this.colListaLikes7.setCellValueFactory(new PropertyValueFactory<>("contadorLikes"));

        tableListaProductos7.getItems().clear();
        tableListaProductos7.setItems(getListaProductosMuroData7());
        colListaProductosfecha7.setSortType(TableColumn.SortType.ASCENDING);

        tableListaProductos7.getSortOrder().add(colListaProductosfecha7);
        tableListaProductos7.sort();

        tableListaProductos7.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {

            productoMuroSeleccionado7 = newSelection;
            mostrarInformacionComentariosMuro7(productoMuroSeleccionado7);
            mostrarInformacionLikeMuro7(productoMuroSeleccionado7);
        });
    }

    public ObservableList<Producto> getListaProductosMuroData7() {
        listaProductosMuroData7.addAll(modelFactoryController.obtenerProductos());
        return listaProductosMuroData7;
    }

    private void inicializarComentariosMuro7() {

        this.colComentariosFecha7.setCellValueFactory(new PropertyValueFactory<>("fechaComentario"));
        this.colComentariosProducto7.setCellValueFactory(new PropertyValueFactory<>("nombreProducto"));
        this.colComentariosEmisor7.setCellValueFactory(new PropertyValueFactory<>("nombreVendedor"));
        this.colComentariosComentario7.setCellValueFactory(new PropertyValueFactory<>("comentario"));

    }

    private void mostrarInformacionComentariosMuro7(Producto productoSeleccionado) {
        if (productoSeleccionado != null) {
            tableComentariosVendedor7.getItems().clear();
            tableComentariosVendedor7.setItems((getListaComentariosVendedorMuroData7(productoSeleccionado)));
        }
    }

    public ObservableList<Comentario> getListaComentariosVendedorMuroData7(Producto productoSeleccionado) {
        listaComentariosVendedorMuroData7.addAll(productoSeleccionado.getListaComentarios());
        return listaComentariosVendedorMuroData7;
    }

    private void inicializarLikesMuro7() {

        this.colLikesFecha7.setCellValueFactory(new PropertyValueFactory<>("fechaLike"));
        this.colLikesProducto7.setCellValueFactory(new PropertyValueFactory<>("nombreProducto"));
        this.colLikesEmisor7.setCellValueFactory(new PropertyValueFactory<>("nombreVendedor"));

    }

    private void mostrarInformacionLikeMuro7(Producto productoSeleccionado) {
        if (productoSeleccionado != null) {
            tableLikeVendedor7.getItems().clear();
            tableLikeVendedor7.setItems((getListaLikesVendedorMuroData7(productoSeleccionado)));
        }
    }

    public ObservableList<Like> getListaLikesVendedorMuroData7(Producto productoSeleccionado) {
        listaLikesVendedorMuroData7.addAll(productoSeleccionado.getListaLikes());
        return listaLikesVendedorMuroData7;
    }

    private void inicializarVendedoresSugeridosContactos7() {

        this.colContactosSugeridosNombre7.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.colContactosSugeridosApellido7.setCellValueFactory(new PropertyValueFactory<>("apellido"));

        tablaContactosSugeridos7.getItems().clear();
        tablaContactosSugeridos7.setItems((getListaContactosSugeridosContactosData7()));

        tablaContactosSugeridos7.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {

            contactoSugeridoContactosSeleccionado7 = newSelection;

        });
    }

    public ObservableList<Vendedor> getListaContactosSugeridosContactosData7() {
        listaContactosSugeridosContactosData7.addAll(modelFactoryController.obtenerContactosSugeridosVendedorN(6));
        return listaContactosSugeridosContactosData7;
    }

    private void inicializarProductosGestion7() {

        this.colGestionNombre7.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.colGestionPrecio7.setCellValueFactory(new PropertyValueFactory<>("precio"));
        this.colGestionEstado7.setCellValueFactory(new PropertyValueFactory<>("estado"));
        this.colGestionFecha7.setCellValueFactory(new PropertyValueFactory<>("fechaPublicacion"));
        this.colGestionVendedor7.setCellValueFactory(new PropertyValueFactory<>("idVendedor"));
        this.colGestionLikes7.setCellValueFactory(new PropertyValueFactory<>("contadorLikes"));
        cbEstado.setItems(FXCollections.observableArrayList(EstadoProducto.values()));

        tableListaProductosGestion7.getItems().clear();
        tableListaProductosGestion7.setItems(getListaProductosGestionData7());
        colGestionFecha7.setSortType(TableColumn.SortType.ASCENDING);
        tableListaProductosGestion7.getSortOrder().add(colGestionFecha7);
        tableListaProductosGestion7.sort();
        tableListaProductosGestion7.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {

            productoGestionSeleccionado7 = newSelection;
            mostrarInformacionProductosGestion7(productoGestionSeleccionado7);
        });
    }

    public ObservableList<Producto> getListaProductosGestionData7() {
        listaProductosGestionData7.addAll(modelFactoryController.obtenerProductosVendedorN(1));
        return listaProductosGestionData7;
    }

    private void mostrarInformacionProductosGestion7(Producto productoSeleccionado) {
        if (productoSeleccionado != null) {
            tfGestionNombre7.setText(productoSeleccionado.getNombre());
            tfGestionPrecio7.setText(String.valueOf((int) productoSeleccionado.getPrecio()));
            cbEstado.setValue(productoSeleccionado.getEstado());
        }
    }

    private void inicializarListaSugeridosGestion7() {

        this.colListaSugeridosNombre7.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.colListaSugeridosApellido7.setCellValueFactory(new PropertyValueFactory<>("apellido"));

        tableListaSugeridosGestion7.getItems().clear();
        tableListaSugeridosGestion7.setItems((getListaSugeridosGestionData7()));

        tableListaSugeridosGestion7.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {

            contactoSugeridoGestionSeleccionado7 = newSelection;

        });
    }

    public ObservableList<Vendedor> getListaSugeridosGestionData7() {
        listaContactosSugeridosGestionData7.addAll(modelFactoryController.obtenerListaContactosSugeridosVendedor1());
        return listaContactosSugeridosGestionData7;
    }

    private void inicializarContactosChat7() {

        this.colListaContactosNombre7.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.colListaContactosApellido7.setCellValueFactory(new PropertyValueFactory<>("apellido"));

        tableListaContactos7.getItems().clear();
        tableListaContactos7.setItems((getListaContactosChat7()));

        tableListaContactos7.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {

            contactoChatSeleccionado7 = newSelection;

        });
    }

    public ObservableList<Vendedor> getListaContactosChat7() {
        listaContactosChatData7.addAll(modelFactoryController.obtenerContactosSugeridosVendedorN(6));
        return listaContactosChatData7;
    }
    public void actualizarTablasContactosClientes7(){
        tablaContactosSugeridos7.getItems().clear();
        tablaContactosSugeridos7.setItems((getListaContactosSugeridosContactosData7()));
        tableListaSugeridosGestion7.getItems().clear();
        tableListaSugeridosGestion7.setItems((getListaSugeridosGestionData7()));
        tableListaContactos7.getItems().clear();
        tableListaContactos7.setItems((getListaContactosChat7()));
    }
//    public void actualizarTablasProductosVendedor7(){
//        tableListaProductos7.getItems().clear();
//        tableListaProductos7.setItems((getListaProductosMuroData7()));
//        tableListaProductosGestion7.getItems().clear();
//        tableListaProductosGestion7.setItems((getListaProductosGestionData7()));
//    }
}

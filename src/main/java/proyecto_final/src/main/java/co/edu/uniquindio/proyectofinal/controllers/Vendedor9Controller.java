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

public class Vendedor9Controller {
    ModelFactoryController modelFactoryController;
    LoginController loginController;

    ObservableList<Producto> listaProductosMuroData9 = FXCollections.observableArrayList();
    Producto productoMuroSeleccionado9;
    Vendedor vendedorLogueado9;

    ObservableList<Comentario> listaComentariosVendedorMuroData9 = FXCollections.observableArrayList();

    ObservableList<Like> listaLikesVendedorMuroData9 = FXCollections.observableArrayList();

    ObservableList<Vendedor> listaContactosSugeridosContactosData9 = FXCollections.observableArrayList();
    Vendedor contactoSugeridoContactosSeleccionado9;

    ObservableList<Producto> listaProductosGestionData9 = FXCollections.observableArrayList();
    Producto productoGestionSeleccionado9;

    ObservableList<Vendedor> listaContactosSugeridosGestionData9 = FXCollections.observableArrayList();
    Vendedor contactoSugeridoGestionSeleccionado9;

    ObservableList<Vendedor> listaContactosChatData9 = FXCollections.observableArrayList();
    Vendedor contactoChatSeleccionado9;
    @FXML
    private TableColumn<Comentario, String> colComentariosComentario9;

    @FXML
    private TableColumn<Comentario, String> colComentariosEmisor9;

    @FXML
    private TableColumn<Comentario, LocalDateTime> colComentariosFecha9;

    @FXML
    private TableColumn<Comentario, String> colComentariosProducto9;

    @FXML
    private TableColumn<Vendedor, String> colContactosSugeridosApellido9;

    @FXML
    private TableColumn<Vendedor, String> colContactosSugeridosNombre9;

    @FXML
    private TableColumn<Producto, EstadoProducto> colGestionEstado9;

    @FXML
    private TableColumn<Producto, LocalDateTime> colGestionFecha9;
    //gestión vendedor 990 fecha 900 muro fecha 950 vendedor 998 fecha abajo 990

    @FXML
    private TableColumn<Producto, Integer> colGestionLikes9;

    @FXML
    private TableColumn<Producto, String> colGestionNombre9;

    @FXML
    private TableColumn<Producto, Double> colGestionPrecio9;

    @FXML
    private TableColumn<Producto, String> colGestionVendedor9;

    @FXML
    private TableColumn<Like, String> colLikesEmisor9;

    @FXML
    private TableColumn<Comentario, LocalDateTime> colLikesFecha9;

    @FXML
    private TableColumn<Like, String> colLikesProducto9;

    @FXML
    private TableColumn<Vendedor, String> colListaContactosApellido9;

    @FXML
    private TableColumn<Vendedor, String> colListaContactosNombre9;

    @FXML
    private TableColumn<Producto, Integer> colListaLikes9;

    @FXML
    private TableColumn<Producto, EstadoProducto> colListaProductosEstado9;

    @FXML
    private TableColumn<Producto, String> colListaProductosNombre9;

    @FXML
    private TableColumn<Producto, Double> colListaProductosPrecio9;

    @FXML
    private TableColumn<Producto, String> colListaProductosVendedor9;

    @FXML
    private TableColumn<Producto, LocalDateTime> colListaProductosfecha9;

    @FXML
    private TableColumn<Vendedor, String> colListaSugeridosApellido9;

    @FXML
    private TableColumn<Vendedor, String> colListaSugeridosNombre9;

    @FXML
    private TextArea tAComentar9;

    @FXML
    private TableView<Vendedor> tablaContactosSugeridos9;

    @FXML
    private TableView<Comentario> tableComentariosVendedor9;

    @FXML
    private TableView<Like> tableLikeVendedor9;

    @FXML
    private TableView<Vendedor> tableListaContactos9;

    @FXML
    private TableView<Producto> tableListaProductos9;

    @FXML
    private TableView<Producto> tableListaProductosGestion9;

    @FXML
    private TableView<Vendedor> tableListaSugeridosGestion9;

    @FXML
    private TextField tfGestionNombre9;

    @FXML
    private TextField tfGestionPrecio9;
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
        inicializarVendedorView9();
    }

    @FXML
    void loginVendedor(ActionEvent event) {
        String user = tfUser.getText();
        String pass = pfPass.getText();
        if (loginController.loginVendedor(user, pass, 8)) {
            userVendedor = user;
            tabChat.setDisable(false);
            tabGestion.setDisable(false);
            vendedorLogueado9 = modelFactoryController.getMarketplace().getListaVendedores().get(8);

        }
    }

    public void onComentarVendedor9Click(ActionEvent actionEvent) {
        Vendedor vendedorComentario = vendedorLogueado9;
        Producto productoComentario = productoMuroSeleccionado9;
        agregarComentario(productoComentario,vendedorComentario);
        tableComentariosVendedor9.refresh();
        tableListaProductos9.getSelectionModel().clearSelection();
    }

    public void onLikeVendedor9Click(ActionEvent actionEvent) {
        // Obtener el producto y el vendedor logueado correspondientes a la acción
        Producto producto = productoMuroSeleccionado9;
        if(vendedorLogueado9!=null){
            Vendedor vendedorInicializado = vendedorLogueado9;
            agregarLike(producto, vendedorInicializado);
            tableListaProductos9.refresh();
            tableListaProductos9.getSelectionModel().clearSelection();
        } else{
            MensajeUtil.mostrarMensaje("Error","Vendedor no logueado", " Debe loguearse como vendedor",Alert.AlertType.ERROR);
        }

    }

    public void onAgregar9Click(ActionEvent actionEvent) {
    }

    public void onAnadir9Click(ActionEvent actionEvent) throws VendedorNoExisteException {
        crearProducto();
    }

    public void onBuscar9Click(ActionEvent actionEvent) {
    }

    public void onModificar9Click(ActionEvent actionEvent) throws VendedorNoExisteException {
        modificarProducto();
    }

    private void modificarProducto() throws VendedorNoExisteException {
        String nombre = tfGestionNombre9.getText();
        double precio = Double.parseDouble(tfGestionPrecio9.getText());
        EstadoProducto estado = (EstadoProducto) cbEstado.getValue();
        String id = productoGestionSeleccionado9.getId();
        Producto producto = null;
        if (datosValidosProducto(nombre, precio, estado)) {
            Vendedor vendedor = modelFactoryController.retornarVendedor(userVendedor);
            producto = modelFactoryController.actualizarProducto(nombre, precio, estado, id, vendedor);
            if (producto != null) {
                int i1 = listaProductosMuroData9.indexOf(productoGestionSeleccionado9);
                int i9 = listaProductosGestionData9.indexOf(productoGestionSeleccionado9);
                listaProductosMuroData9.set(i1, producto);
                listaProductosGestionData9.set(i9, producto);
                tableListaProductosGestion9.refresh();
                tableListaProductos9.refresh();
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

    public void onEliminar9Click(ActionEvent actionEvent) throws VendedorNoExisteException {
        eliminarProducto();
    }

    public void onCancelar9Click(ActionEvent actionEvent) {
        tableListaProductosGestion9.getSelectionModel().clearSelection();
        limpiarCamposProducto();
    }

    private void crearProducto() throws VendedorNoExisteException {
        String nombre = tfGestionNombre9.getText();
        double precio = Double.parseDouble(tfGestionPrecio9.getText());
        EstadoProducto estado = (EstadoProducto) cbEstado.getValue();
        if (datosValidosProducto(nombre, precio, estado)) {
            Producto producto = null;
            Vendedor vendedor = modelFactoryController.retornarVendedor(userVendedor);
            producto = modelFactoryController.crearProducto(nombre, precio, estado, vendedor);
            if (producto != null) {
                listaProductosMuroData9.add(producto);
                listaProductosGestionData9.add(producto);
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
        if (productoGestionSeleccionado9 != null) {
            String id = productoGestionSeleccionado9.getId();
            if (MensajeUtil.mostrarMensajeConfirmacion("¿Estás seguro de elmininar el producto?")) {
                Vendedor vendedor = modelFactoryController.retornarVendedor(userVendedor);
                int index = modelFactoryController.getIndiceVendedor(vendedor);
                productoEliminado = modelFactoryController.eliminarProducto(id, index);
                if (productoEliminado) {
                    listaProductosGestionData9.remove(productoGestionSeleccionado9);
                    listaProductosMuroData9.remove(productoGestionSeleccionado9);
                    productoGestionSeleccionado9 = null;
                    tableListaProductosGestion9.getSelectionModel().clearSelection();
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
    public void agregarLike(Producto productoMuroSeleccionado9, Vendedor vendedorLogueado9) {
        try{
            Objects.requireNonNull(productoMuroSeleccionado9, "Es necesario que seleccione un producto");
            // Verificar si el vendedor logueado ya ha dado like
            boolean vendedorDioLike = false;
            for (Like like : productoMuroSeleccionado9.getListaLikes()) {
                if (like.getVendedor().getUsuario().equals(vendedorLogueado9.getUsuario())) {
                    vendedorDioLike = true;
                    break;
                }
            }

            if (vendedorDioLike) {
                MensajeUtil.mostrarMensaje("Información","El vendedor ya le dió Like", "El vendedor ya le dió like", Alert.AlertType.INFORMATION);
            } else {
                // Si el vendedor no ha dado like, se incrementa el contador de likes
                productoMuroSeleccionado9.incrementarLikes();
                // Agregar un nuevo like a la lista de likes del producto
                Like nuevoLike = new Like(productoMuroSeleccionado9, vendedorLogueado9, LocalDateTime.now());
                productoMuroSeleccionado9.getListaLikes().add(nuevoLike);
            }}catch (Exception e){
            MensajeUtil.mostrarMensaje("Alerta","Seleccione un producto",e.getMessage(),Alert.AlertType.ERROR);
        }
    }
    public void agregarComentario(Producto productoMuroSeleccionado9,Vendedor vendedorLogueado9){
        try{
            Objects.requireNonNull(productoMuroSeleccionado9,"Es necesario que seleccione un producto");
            String ComentarioTexto  =  tAComentar9.getText();
            if(!ComentarioTexto.isEmpty()){
                LocalDateTime fechaActual = LocalDateTime.now();

                Comentario comentario = new Comentario(productoMuroSeleccionado9,vendedorLogueado9,fechaActual,ComentarioTexto);

                productoMuroSeleccionado9.getListaComentarios().add(comentario);
                tAComentar9.clear();

            }
            else {
                MensajeUtil.mostrarMensaje("Error","Comentario vacio","El comentario no puede estar vacio", Alert.AlertType.ERROR);

            }}catch (Exception e){
            MensajeUtil.mostrarMensaje("Error", "Seleccione un producto,",e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void limpiarCamposProducto() {
        tfGestionNombre9.setText("");
        tfGestionPrecio9.setText("");
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

    private void inicializarVendedorView9() {
        inicializarProductosMuro9();
        inicializarComentariosMuro9();
        inicializarLikesMuro9();
        inicializarProductosGestion9();
        inicializarVendedoresSugeridosContactos9();
        inicializarListaSugeridosGestion9();
        inicializarContactosChat9();
    }

    private void inicializarProductosMuro9() {

        this.colListaProductosNombre9.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.colListaProductosPrecio9.setCellValueFactory(new PropertyValueFactory<>("precio"));
        this.colListaProductosEstado9.setCellValueFactory(new PropertyValueFactory<>("estado"));
        this.colListaProductosfecha9.setCellValueFactory(new PropertyValueFactory<>("fechaPublicacion"));
        this.colListaProductosVendedor9.setCellValueFactory(new PropertyValueFactory<>("idVendedor"));
        this.colListaLikes9.setCellValueFactory(new PropertyValueFactory<>("contadorLikes"));

        tableListaProductos9.getItems().clear();
        tableListaProductos9.setItems(getListaProductosMuroData9());
        colListaProductosfecha9.setSortType(TableColumn.SortType.ASCENDING);

        tableListaProductos9.getSortOrder().add(colListaProductosfecha9);
        tableListaProductos9.sort();

        tableListaProductos9.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {

            productoMuroSeleccionado9 = newSelection;
            mostrarInformacionComentariosMuro9(productoMuroSeleccionado9);
            mostrarInformacionLikeMuro9(productoMuroSeleccionado9);
        });
    }

    public ObservableList<Producto> getListaProductosMuroData9() {
        listaProductosMuroData9.addAll(modelFactoryController.obtenerProductos());
        return listaProductosMuroData9;
    }

    private void inicializarComentariosMuro9() {

        this.colComentariosFecha9.setCellValueFactory(new PropertyValueFactory<>("fechaComentario"));
        this.colComentariosProducto9.setCellValueFactory(new PropertyValueFactory<>("nombreProducto"));
        this.colComentariosEmisor9.setCellValueFactory(new PropertyValueFactory<>("nombreVendedor"));
        this.colComentariosComentario9.setCellValueFactory(new PropertyValueFactory<>("comentario"));

    }

    private void mostrarInformacionComentariosMuro9(Producto productoSeleccionado) {
        if (productoSeleccionado != null) {
            tableComentariosVendedor9.getItems().clear();
            tableComentariosVendedor9.setItems((getListaComentariosVendedorMuroData9(productoSeleccionado)));
        }
    }

    public ObservableList<Comentario> getListaComentariosVendedorMuroData9(Producto productoSeleccionado) {
        listaComentariosVendedorMuroData9.addAll(productoSeleccionado.getListaComentarios());
        return listaComentariosVendedorMuroData9;
    }

    private void inicializarLikesMuro9() {

        this.colLikesFecha9.setCellValueFactory(new PropertyValueFactory<>("fechaLike"));
        this.colLikesProducto9.setCellValueFactory(new PropertyValueFactory<>("nombreProducto"));
        this.colLikesEmisor9.setCellValueFactory(new PropertyValueFactory<>("nombreVendedor"));

    }

    private void mostrarInformacionLikeMuro9(Producto productoSeleccionado) {
        if (productoSeleccionado != null) {
            tableLikeVendedor9.getItems().clear();
            tableLikeVendedor9.setItems((getListaLikesVendedorMuroData9(productoSeleccionado)));
        }
    }

    public ObservableList<Like> getListaLikesVendedorMuroData9(Producto productoSeleccionado) {
        listaLikesVendedorMuroData9.addAll(productoSeleccionado.getListaLikes());
        return listaLikesVendedorMuroData9;
    }

    private void inicializarVendedoresSugeridosContactos9() {

        this.colContactosSugeridosNombre9.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.colContactosSugeridosApellido9.setCellValueFactory(new PropertyValueFactory<>("apellido"));

        tablaContactosSugeridos9.getItems().clear();
        tablaContactosSugeridos9.setItems((getListaContactosSugeridosContactosData9()));

        tablaContactosSugeridos9.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {

            contactoSugeridoContactosSeleccionado9 = newSelection;

        });
    }

    public ObservableList<Vendedor> getListaContactosSugeridosContactosData9() {
        listaContactosSugeridosContactosData9.addAll(modelFactoryController.obtenerContactosSugeridosVendedorN(8));
        return listaContactosSugeridosContactosData9;
    }

    private void inicializarProductosGestion9() {

        this.colGestionNombre9.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.colGestionPrecio9.setCellValueFactory(new PropertyValueFactory<>("precio"));
        this.colGestionEstado9.setCellValueFactory(new PropertyValueFactory<>("estado"));
        this.colGestionFecha9.setCellValueFactory(new PropertyValueFactory<>("fechaPublicacion"));
        this.colGestionVendedor9.setCellValueFactory(new PropertyValueFactory<>("idVendedor"));
        this.colGestionLikes9.setCellValueFactory(new PropertyValueFactory<>("contadorLikes"));
        cbEstado.setItems(FXCollections.observableArrayList(EstadoProducto.values()));

        tableListaProductosGestion9.getItems().clear();
        tableListaProductosGestion9.setItems(getListaProductosGestionData9());
        colGestionFecha9.setSortType(TableColumn.SortType.ASCENDING);
        tableListaProductosGestion9.getSortOrder().add(colGestionFecha9);
        tableListaProductosGestion9.sort();
        tableListaProductosGestion9.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {

            productoGestionSeleccionado9 = newSelection;
            mostrarInformacionProductosGestion9(productoGestionSeleccionado9);
        });
    }

    public ObservableList<Producto> getListaProductosGestionData9() {
        listaProductosGestionData9.addAll(modelFactoryController.obtenerProductosVendedorN(8));
        return listaProductosGestionData9;
    }

    private void mostrarInformacionProductosGestion9(Producto productoSeleccionado) {
        if (productoSeleccionado != null) {
            tfGestionNombre9.setText(productoSeleccionado.getNombre());
            tfGestionPrecio9.setText(String.valueOf((int) productoSeleccionado.getPrecio()));
            cbEstado.setValue(productoSeleccionado.getEstado());
        }
    }

    private void inicializarListaSugeridosGestion9() {

        this.colListaSugeridosNombre9.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.colListaSugeridosApellido9.setCellValueFactory(new PropertyValueFactory<>("apellido"));

        tableListaSugeridosGestion9.getItems().clear();
        tableListaSugeridosGestion9.setItems((getListaSugeridosGestionData9()));

        tableListaSugeridosGestion9.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {

            contactoSugeridoGestionSeleccionado9 = newSelection;

        });
    }

    public ObservableList<Vendedor> getListaSugeridosGestionData9() {
        listaContactosSugeridosGestionData9.addAll(modelFactoryController.obtenerListaContactosSugeridosVendedor1());
        return listaContactosSugeridosGestionData9;
    }

    private void inicializarContactosChat9() {

        this.colListaContactosNombre9.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.colListaContactosApellido9.setCellValueFactory(new PropertyValueFactory<>("apellido"));

        tableListaContactos9.getItems().clear();
        tableListaContactos9.setItems((getListaContactosChat9()));

        tableListaContactos9.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {

            contactoChatSeleccionado9 = newSelection;

        });
    }

    public ObservableList<Vendedor> getListaContactosChat9() {
        listaContactosChatData9.addAll(modelFactoryController.obtenerContactosSugeridosVendedorN(8));
        return listaContactosChatData9;
    }
    public void actualizarTablasContactosClientes9(){
        tablaContactosSugeridos9.getItems().clear();
        tablaContactosSugeridos9.setItems((getListaContactosSugeridosContactosData9()));
        tableListaSugeridosGestion9.getItems().clear();
        tableListaSugeridosGestion9.setItems((getListaSugeridosGestionData9()));
        tableListaContactos9.getItems().clear();
        tableListaContactos9.setItems((getListaContactosChat9()));
    }
//    public void actualizarTablasProductosVendedor9(){
//        tableListaProductos9.getItems().clear();
//        tableListaProductos9.setItems((getListaProductosMuroData9()));
//        tableListaProductosGestion9.getItems().clear();
//        tableListaProductosGestion9.setItems((getListaProductosGestionData9()));
//    }
}

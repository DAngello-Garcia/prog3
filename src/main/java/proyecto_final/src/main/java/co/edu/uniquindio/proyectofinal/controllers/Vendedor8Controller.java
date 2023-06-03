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

public class Vendedor8Controller {
    ModelFactoryController modelFactoryController;
    LoginController loginController;

    ObservableList<Producto> listaProductosMuroData8 = FXCollections.observableArrayList();
    Producto productoMuroSeleccionado8;
    Vendedor vendedorLogueado8;

    ObservableList<Comentario> listaComentariosVendedorMuroData8 = FXCollections.observableArrayList();

    ObservableList<Like> listaLikesVendedorMuroData8 = FXCollections.observableArrayList();

    ObservableList<Vendedor> listaContactosSugeridosContactosData8 = FXCollections.observableArrayList();
    Vendedor contactoSugeridoContactosSeleccionado8;

    ObservableList<Producto> listaProductosGestionData8 = FXCollections.observableArrayList();
    Producto productoGestionSeleccionado8;

    ObservableList<Vendedor> listaContactosSugeridosGestionData8 = FXCollections.observableArrayList();
    Vendedor contactoSugeridoGestionSeleccionado8;

    ObservableList<Vendedor> listaContactosChatData8 = FXCollections.observableArrayList();
    Vendedor contactoChatSeleccionado8;
    @FXML
    private TableColumn<Comentario, String> colComentariosComentario8;

    @FXML
    private TableColumn<Comentario, String> colComentariosEmisor8;

    @FXML
    private TableColumn<Comentario, LocalDateTime> colComentariosFecha8;

    @FXML
    private TableColumn<Comentario, String> colComentariosProducto8;

    @FXML
    private TableColumn<Vendedor, String> colContactosSugeridosApellido8;

    @FXML
    private TableColumn<Vendedor, String> colContactosSugeridosNombre8;

    @FXML
    private TableColumn<Producto, EstadoProducto> colGestionEstado8;

    @FXML
    private TableColumn<Producto, LocalDateTime> colGestionFecha8;
    //gestión vendedor 880 fecha 800 muro fecha 850 vendedor 888 fecha abajo 880

    @FXML
    private TableColumn<Producto, Integer> colGestionLikes8;

    @FXML
    private TableColumn<Producto, String> colGestionNombre8;

    @FXML
    private TableColumn<Producto, Double> colGestionPrecio8;

    @FXML
    private TableColumn<Producto, String> colGestionVendedor8;

    @FXML
    private TableColumn<Like, String> colLikesEmisor8;

    @FXML
    private TableColumn<Comentario, LocalDateTime> colLikesFecha8;

    @FXML
    private TableColumn<Like, String> colLikesProducto8;

    @FXML
    private TableColumn<Vendedor, String> colListaContactosApellido8;

    @FXML
    private TableColumn<Vendedor, String> colListaContactosNombre8;

    @FXML
    private TableColumn<Producto, Integer> colListaLikes8;

    @FXML
    private TableColumn<Producto, EstadoProducto> colListaProductosEstado8;

    @FXML
    private TableColumn<Producto, String> colListaProductosNombre8;

    @FXML
    private TableColumn<Producto, Double> colListaProductosPrecio8;

    @FXML
    private TableColumn<Producto, String> colListaProductosVendedor8;

    @FXML
    private TableColumn<Producto, LocalDateTime> colListaProductosfecha8;

    @FXML
    private TableColumn<Vendedor, String> colListaSugeridosApellido8;

    @FXML
    private TableColumn<Vendedor, String> colListaSugeridosNombre8;

    @FXML
    private TextArea tAComentar8;

    @FXML
    private TableView<Vendedor> tablaContactosSugeridos8;

    @FXML
    private TableView<Comentario> tableComentariosVendedor8;

    @FXML
    private TableView<Like> tableLikeVendedor8;

    @FXML
    private TableView<Vendedor> tableListaContactos8;

    @FXML
    private TableView<Producto> tableListaProductos8;

    @FXML
    private TableView<Producto> tableListaProductosGestion8;

    @FXML
    private TableView<Vendedor> tableListaSugeridosGestion8;

    @FXML
    private TextField tfGestionNombre8;

    @FXML
    private TextField tfGestionPrecio8;
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
        inicializarVendedorView8();
    }

    @FXML
    void loginVendedor(ActionEvent event) {
        String user = tfUser.getText();
        String pass = pfPass.getText();
        if (loginController.loginVendedor(user, pass, 7)) {
            userVendedor = user;
            tabChat.setDisable(false);
            tabGestion.setDisable(false);
            vendedorLogueado8 = modelFactoryController.getMarketplace().getListaVendedores().get(7);

        }
    }

    public void onComentarVendedor8Click(ActionEvent actionEvent) {
        Vendedor vendedorComentario = vendedorLogueado8;
        Producto productoComentario = productoMuroSeleccionado8;
        agregarComentario(productoComentario,vendedorComentario);
        tableComentariosVendedor8.refresh();
        tableListaProductos8.getSelectionModel().clearSelection();
    }

    public void onLikeVendedor8Click(ActionEvent actionEvent) {
        // Obtener el producto y el vendedor logueado correspondientes a la acción
        Producto producto = productoMuroSeleccionado8;
        if(vendedorLogueado8!=null){
            Vendedor vendedorInicializado = vendedorLogueado8;
            agregarLike(producto, vendedorInicializado);
            tableListaProductos8.refresh();
            tableListaProductos8.getSelectionModel().clearSelection();
        } else{
            MensajeUtil.mostrarMensaje("Error","Vendedor no logueado", " Debe loguearse como vendedor",Alert.AlertType.ERROR);
        }

    }

    public void onAgregar8Click(ActionEvent actionEvent) {
    }

    public void onAnadir8Click(ActionEvent actionEvent) throws VendedorNoExisteException {
        crearProducto();
    }

    public void onBuscar8Click(ActionEvent actionEvent) {
    }

    public void onModificar8Click(ActionEvent actionEvent) throws VendedorNoExisteException {
        modificarProducto();
    }

    private void modificarProducto() throws VendedorNoExisteException {
        String nombre = tfGestionNombre8.getText();
        double precio = Double.parseDouble(tfGestionPrecio8.getText());
        EstadoProducto estado = (EstadoProducto) cbEstado.getValue();
        String id = productoGestionSeleccionado8.getId();
        Producto producto = null;
        if (datosValidosProducto(nombre, precio, estado)) {
            Vendedor vendedor = modelFactoryController.retornarVendedor(userVendedor);
            producto = modelFactoryController.actualizarProducto(nombre, precio, estado, id, vendedor);
            if (producto != null) {
                int i1 = listaProductosMuroData8.indexOf(productoGestionSeleccionado8);
                int i8 = listaProductosGestionData8.indexOf(productoGestionSeleccionado8);
                listaProductosMuroData8.set(i1, producto);
                listaProductosGestionData8.set(i8, producto);
                tableListaProductosGestion8.refresh();
                tableListaProductos8.refresh();
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

    public void onEliminar8Click(ActionEvent actionEvent) throws VendedorNoExisteException {
        eliminarProducto();
    }

    public void onCancelar8Click(ActionEvent actionEvent) {
        tableListaProductosGestion8.getSelectionModel().clearSelection();
        limpiarCamposProducto();
    }

    private void crearProducto() throws VendedorNoExisteException {
        String nombre = tfGestionNombre8.getText();
        double precio = Double.parseDouble(tfGestionPrecio8.getText());
        EstadoProducto estado = (EstadoProducto) cbEstado.getValue();
        if (datosValidosProducto(nombre, precio, estado)) {
            Producto producto = null;
            Vendedor vendedor = modelFactoryController.retornarVendedor(userVendedor);
            producto = modelFactoryController.crearProducto(nombre, precio, estado, vendedor);
            if (producto != null) {
                listaProductosMuroData8.add(producto);
                listaProductosGestionData8.add(producto);
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
        if (productoGestionSeleccionado8 != null) {
            String id = productoGestionSeleccionado8.getId();
            if (MensajeUtil.mostrarMensajeConfirmacion("¿Estás seguro de elmininar el producto?")) {
                Vendedor vendedor = modelFactoryController.retornarVendedor(userVendedor);
                int index = modelFactoryController.getIndiceVendedor(vendedor);
                productoEliminado = modelFactoryController.eliminarProducto(id, index);
                if (productoEliminado) {
                    listaProductosGestionData8.remove(productoGestionSeleccionado8);
                    listaProductosMuroData8.remove(productoGestionSeleccionado8);
                    productoGestionSeleccionado8 = null;
                    tableListaProductosGestion8.getSelectionModel().clearSelection();
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
    public void agregarLike(Producto productoMuroSeleccionado8, Vendedor vendedorLogueado8) {
        try{
            Objects.requireNonNull(productoMuroSeleccionado8, "Es necesario que seleccione un producto");
            // Verificar si el vendedor logueado ya ha dado like
            boolean vendedorDioLike = false;
            for (Like like : productoMuroSeleccionado8.getListaLikes()) {
                if (like.getVendedor().getUsuario().equals(vendedorLogueado8.getUsuario())) {
                    vendedorDioLike = true;
                    break;
                }
            }

            if (vendedorDioLike) {
                MensajeUtil.mostrarMensaje("Información","El vendedor ya le dió Like", "El vendedor ya le dió like", Alert.AlertType.INFORMATION);
            } else {
                // Si el vendedor no ha dado like, se incrementa el contador de likes
                productoMuroSeleccionado8.incrementarLikes();
                // Agregar un nuevo like a la lista de likes del producto
                Like nuevoLike = new Like(productoMuroSeleccionado8, vendedorLogueado8, LocalDateTime.now());
                productoMuroSeleccionado8.getListaLikes().add(nuevoLike);
            }}catch (Exception e){
            MensajeUtil.mostrarMensaje("Alerta","Seleccione un producto",e.getMessage(),Alert.AlertType.ERROR);
        }
    }
    public void agregarComentario(Producto productoMuroSeleccionado8,Vendedor vendedorLogueado8){
        try{
            Objects.requireNonNull(productoMuroSeleccionado8,"Es necesario que seleccione un producto");
            String ComentarioTexto  =  tAComentar8.getText();
            if(!ComentarioTexto.isEmpty()){
                LocalDateTime fechaActual = LocalDateTime.now();

                Comentario comentario = new Comentario(productoMuroSeleccionado8,vendedorLogueado8,fechaActual,ComentarioTexto);

                productoMuroSeleccionado8.getListaComentarios().add(comentario);
                tAComentar8.clear();

            }
            else {
                MensajeUtil.mostrarMensaje("Error","Comentario vacio","El comentario no puede estar vacio", Alert.AlertType.ERROR);

            }}catch (Exception e){
            MensajeUtil.mostrarMensaje("Error", "Seleccione un producto,",e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void limpiarCamposProducto() {
        tfGestionNombre8.setText("");
        tfGestionPrecio8.setText("");
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

    private void inicializarVendedorView8() {
        inicializarProductosMuro8();
        inicializarComentariosMuro8();
        inicializarLikesMuro8();
        inicializarProductosGestion8();
        inicializarVendedoresSugeridosContactos8();
        inicializarListaSugeridosGestion8();
        inicializarContactosChat8();
    }

    private void inicializarProductosMuro8() {

        this.colListaProductosNombre8.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.colListaProductosPrecio8.setCellValueFactory(new PropertyValueFactory<>("precio"));
        this.colListaProductosEstado8.setCellValueFactory(new PropertyValueFactory<>("estado"));
        this.colListaProductosfecha8.setCellValueFactory(new PropertyValueFactory<>("fechaPublicacion"));
        this.colListaProductosVendedor8.setCellValueFactory(new PropertyValueFactory<>("idVendedor"));
        this.colListaLikes8.setCellValueFactory(new PropertyValueFactory<>("contadorLikes"));

        tableListaProductos8.getItems().clear();
        tableListaProductos8.setItems(getListaProductosMuroData8());
        colListaProductosfecha8.setSortType(TableColumn.SortType.ASCENDING);

        tableListaProductos8.getSortOrder().add(colListaProductosfecha8);
        tableListaProductos8.sort();

        tableListaProductos8.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {

            productoMuroSeleccionado8 = newSelection;
            mostrarInformacionComentariosMuro8(productoMuroSeleccionado8);
            mostrarInformacionLikeMuro8(productoMuroSeleccionado8);
        });
    }

    public ObservableList<Producto> getListaProductosMuroData8() {
        listaProductosMuroData8.addAll(modelFactoryController.obtenerProductos());
        return listaProductosMuroData8;
    }

    private void inicializarComentariosMuro8() {

        this.colComentariosFecha8.setCellValueFactory(new PropertyValueFactory<>("fechaComentario"));
        this.colComentariosProducto8.setCellValueFactory(new PropertyValueFactory<>("nombreProducto"));
        this.colComentariosEmisor8.setCellValueFactory(new PropertyValueFactory<>("nombreVendedor"));
        this.colComentariosComentario8.setCellValueFactory(new PropertyValueFactory<>("comentario"));

    }

    private void mostrarInformacionComentariosMuro8(Producto productoSeleccionado) {
        if (productoSeleccionado != null) {
            tableComentariosVendedor8.getItems().clear();
            tableComentariosVendedor8.setItems((getListaComentariosVendedorMuroData8(productoSeleccionado)));
        }
    }

    public ObservableList<Comentario> getListaComentariosVendedorMuroData8(Producto productoSeleccionado) {
        listaComentariosVendedorMuroData8.addAll(productoSeleccionado.getListaComentarios());
        return listaComentariosVendedorMuroData8;
    }

    private void inicializarLikesMuro8() {

        this.colLikesFecha8.setCellValueFactory(new PropertyValueFactory<>("fechaLike"));
        this.colLikesProducto8.setCellValueFactory(new PropertyValueFactory<>("nombreProducto"));
        this.colLikesEmisor8.setCellValueFactory(new PropertyValueFactory<>("nombreVendedor"));

    }

    private void mostrarInformacionLikeMuro8(Producto productoSeleccionado) {
        if (productoSeleccionado != null) {
            tableLikeVendedor8.getItems().clear();
            tableLikeVendedor8.setItems((getListaLikesVendedorMuroData8(productoSeleccionado)));
        }
    }

    public ObservableList<Like> getListaLikesVendedorMuroData8(Producto productoSeleccionado) {
        listaLikesVendedorMuroData8.addAll(productoSeleccionado.getListaLikes());
        return listaLikesVendedorMuroData8;
    }

    private void inicializarVendedoresSugeridosContactos8() {

        this.colContactosSugeridosNombre8.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.colContactosSugeridosApellido8.setCellValueFactory(new PropertyValueFactory<>("apellido"));

        tablaContactosSugeridos8.getItems().clear();
        tablaContactosSugeridos8.setItems((getListaContactosSugeridosContactosData8()));

        tablaContactosSugeridos8.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {

            contactoSugeridoContactosSeleccionado8 = newSelection;

        });
    }

    public ObservableList<Vendedor> getListaContactosSugeridosContactosData8() {
        listaContactosSugeridosContactosData8.addAll(modelFactoryController.obtenerContactosSugeridosVendedorN(7));
        return listaContactosSugeridosContactosData8;
    }

    private void inicializarProductosGestion8() {

        this.colGestionNombre8.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.colGestionPrecio8.setCellValueFactory(new PropertyValueFactory<>("precio"));
        this.colGestionEstado8.setCellValueFactory(new PropertyValueFactory<>("estado"));
        this.colGestionFecha8.setCellValueFactory(new PropertyValueFactory<>("fechaPublicacion"));
        this.colGestionVendedor8.setCellValueFactory(new PropertyValueFactory<>("idVendedor"));
        this.colGestionLikes8.setCellValueFactory(new PropertyValueFactory<>("contadorLikes"));
        cbEstado.setItems(FXCollections.observableArrayList(EstadoProducto.values()));

        tableListaProductosGestion8.getItems().clear();
        tableListaProductosGestion8.setItems(getListaProductosGestionData8());
        colGestionFecha8.setSortType(TableColumn.SortType.ASCENDING);
        tableListaProductosGestion8.getSortOrder().add(colGestionFecha8);
        tableListaProductosGestion8.sort();
        tableListaProductosGestion8.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {

            productoGestionSeleccionado8 = newSelection;
            mostrarInformacionProductosGestion8(productoGestionSeleccionado8);
        });
    }

    public ObservableList<Producto> getListaProductosGestionData8() {
        listaProductosGestionData8.addAll(modelFactoryController.obtenerProductosVendedorN(7));
        return listaProductosGestionData8;
    }

    private void mostrarInformacionProductosGestion8(Producto productoSeleccionado) {
        if (productoSeleccionado != null) {
            tfGestionNombre8.setText(productoSeleccionado.getNombre());
            tfGestionPrecio8.setText(String.valueOf((int) productoSeleccionado.getPrecio()));
            cbEstado.setValue(productoSeleccionado.getEstado());
        }
    }

    private void inicializarListaSugeridosGestion8() {

        this.colListaSugeridosNombre8.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.colListaSugeridosApellido8.setCellValueFactory(new PropertyValueFactory<>("apellido"));

        tableListaSugeridosGestion8.getItems().clear();
        tableListaSugeridosGestion8.setItems((getListaSugeridosGestionData8()));

        tableListaSugeridosGestion8.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {

            contactoSugeridoGestionSeleccionado8 = newSelection;

        });
    }

    public ObservableList<Vendedor> getListaSugeridosGestionData8() {
        listaContactosSugeridosGestionData8.addAll(modelFactoryController.obtenerListaContactosSugeridosVendedor1());
        return listaContactosSugeridosGestionData8;
    }

    private void inicializarContactosChat8() {

        this.colListaContactosNombre8.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.colListaContactosApellido8.setCellValueFactory(new PropertyValueFactory<>("apellido"));

        tableListaContactos8.getItems().clear();
        tableListaContactos8.setItems((getListaContactosChat8()));

        tableListaContactos8.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {

            contactoChatSeleccionado8 = newSelection;

        });
    }

    public ObservableList<Vendedor> getListaContactosChat8() {
        listaContactosChatData8.addAll(modelFactoryController.obtenerContactosSugeridosVendedorN(7));
        return listaContactosChatData8;
    }
    public void actualizarTablasContactosClientes8(){
        tablaContactosSugeridos8.getItems().clear();
        tablaContactosSugeridos8.setItems((getListaContactosSugeridosContactosData8()));
        tableListaSugeridosGestion8.getItems().clear();
        tableListaSugeridosGestion8.setItems((getListaSugeridosGestionData8()));
        tableListaContactos8.getItems().clear();
        tableListaContactos8.setItems((getListaContactosChat8()));
    }
//    public void actualizarTablasProductosVendedor8(){
//        tableListaProductos8.getItems().clear();
//        tableListaProductos8.setItems((getListaProductosMuroData8()));
//        tableListaProductosGestion8.getItems().clear();
//        tableListaProductosGestion8.setItems((getListaProductosGestionData8()));
//    }
}

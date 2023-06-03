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

public class Vendedor3Controller {
    ModelFactoryController modelFactoryController;
    LoginController loginController;

    ObservableList<Producto> listaProductosMuroData3 = FXCollections.observableArrayList();
    Producto productoMuroSeleccionado3;

    Vendedor vendedorLogueado3;


    ObservableList<Comentario> listaComentariosVendedorMuroData3 = FXCollections.observableArrayList();

    ObservableList<Like> listaLikesVendedorMuroData3 = FXCollections.observableArrayList();

    ObservableList<Vendedor> listaContactosSugeridosContactosData3 = FXCollections.observableArrayList();
    Vendedor contactoSugeridoContactosSeleccionado3;

    ObservableList<Producto> listaProductosGestionData3 = FXCollections.observableArrayList();
    Producto productoGestionSeleccionado3;

    ObservableList<Vendedor> listaContactosSugeridosGestionData3 = FXCollections.observableArrayList();
    Vendedor contactoSugeridoGestionSeleccionado3;

    ObservableList<Vendedor> listaContactosChatData3 = FXCollections.observableArrayList();
    Vendedor contactoChatSeleccionado3;
    @FXML
    private TableColumn<Comentario, String> colComentariosComentario3;

    @FXML
    private TableColumn<Comentario, String> colComentariosEmisor3;

    @FXML
    private TableColumn<Comentario, LocalDateTime> colComentariosFecha3;

    @FXML
    private TableColumn<Comentario, String> colComentariosProducto3;

    @FXML
    private TableColumn<Vendedor, String> colContactosSugeridosApellido3;

    @FXML
    private TableColumn<Vendedor, String> colContactosSugeridosNombre3;

    @FXML
    private TableColumn<Producto, EstadoProducto> colGestionEstado3;

    @FXML
    private TableColumn<Producto, LocalDateTime> colGestionFecha3;
    //gestión vendedor 330 fecha 300 muro fecha 350 vendedor 338 fecha abajo 330

    @FXML
    private TableColumn<Producto, Integer> colGestionLikes3;

    @FXML
    private TableColumn<Producto, String> colGestionNombre3;

    @FXML
    private TableColumn<Producto, Double> colGestionPrecio3;

    @FXML
    private TableColumn<Producto, String> colGestionVendedor3;

    @FXML
    private TableColumn<Like, String> colLikesEmisor3;

    @FXML
    private TableColumn<Comentario, LocalDateTime> colLikesFecha3;

    @FXML
    private TableColumn<Like, String> colLikesProducto3;

    @FXML
    private TableColumn<Vendedor, String> colListaContactosApellido3;

    @FXML
    private TableColumn<Vendedor, String> colListaContactosNombre3;

    @FXML
    private TableColumn<Producto, Integer> colListaLikes3;

    @FXML
    private TableColumn<Producto, EstadoProducto> colListaProductosEstado3;

    @FXML
    private TableColumn<Producto, String> colListaProductosNombre3;

    @FXML
    private TableColumn<Producto, Double> colListaProductosPrecio3;

    @FXML
    private TableColumn<Producto, String> colListaProductosVendedor3;

    @FXML
    private TableColumn<Producto, LocalDateTime> colListaProductosfecha3;

    @FXML
    private TableColumn<Vendedor, String> colListaSugeridosApellido3;

    @FXML
    private TableColumn<Vendedor, String> colListaSugeridosNombre3;

    @FXML
    private TextArea tAComentar3;

    @FXML
    private TableView<Vendedor> tablaContactosSugeridos3;

    @FXML
    private TableView<Comentario> tableComentariosVendedor3;

    @FXML
    private TableView<Like> tableLikeVendedor3;

    @FXML
    private TableView<Vendedor> tableListaContactos3;

    @FXML
    private TableView<Producto> tableListaProductos3;

    @FXML
    private TableView<Producto> tableListaProductosGestion3;

    @FXML
    private TableView<Vendedor> tableListaSugeridosGestion3;

    @FXML
    private TextField tfGestionNombre3;

    @FXML
    private TextField tfGestionPrecio3;
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
        inicializarVendedorView3();
    }

    @FXML
    void loginVendedor(ActionEvent event) {
        String user = tfUser.getText();
        String pass = pfPass.getText();
        if (loginController.loginVendedor(user, pass, 2)) {
            userVendedor = user;
            tabChat.setDisable(false);
            tabGestion.setDisable(false);
            vendedorLogueado3 = modelFactoryController.getMarketplace().getListaVendedores().get(2);

        }
    }

    public void onComentarVendedor3Click(ActionEvent actionEvent) {
        Vendedor vendedorComentario = vendedorLogueado3;
        Producto productoComentario = productoMuroSeleccionado3;
        agregarComentario(productoComentario,vendedorComentario);
        tableComentariosVendedor3.refresh();
        tableListaProductos3.getSelectionModel().clearSelection();
    }

    public void onLikeVendedor3Click(ActionEvent actionEvent) {
        // Obtener el producto y el vendedor logueado correspondientes a la acción
        Producto producto = productoMuroSeleccionado3;
        if(vendedorLogueado3!=null){
            Vendedor vendedorInicializado = vendedorLogueado3;
            agregarLike(producto, vendedorInicializado);
            tableListaProductos3.refresh();
            tableListaProductos3.getSelectionModel().clearSelection();
        } else{
            MensajeUtil.mostrarMensaje("Error","Vendedor no logueado", " Debe loguearse como vendedor",Alert.AlertType.ERROR);
        }

    }

    public void onAgregar3Click(ActionEvent actionEvent) {
    }

    public void onAnadir3Click(ActionEvent actionEvent) throws VendedorNoExisteException {
        crearProducto();
    }

    public void onBuscar3Click(ActionEvent actionEvent) {
    }

    public void onModificar3Click(ActionEvent actionEvent) throws VendedorNoExisteException {
        modificarProducto();
    }

    private void modificarProducto() throws VendedorNoExisteException {
        String nombre = tfGestionNombre3.getText();
        double precio = Double.parseDouble(tfGestionPrecio3.getText());
        EstadoProducto estado = (EstadoProducto) cbEstado.getValue();
        String id = productoGestionSeleccionado3.getId();
        Producto producto = null;
        if (datosValidosProducto(nombre, precio, estado)) {
            Vendedor vendedor = modelFactoryController.retornarVendedor(userVendedor);
            producto = modelFactoryController.actualizarProducto(nombre, precio, estado, id, vendedor);
            if (producto != null) {
                int i1 = listaProductosMuroData3.indexOf(productoGestionSeleccionado3);
                int i3 = listaProductosGestionData3.indexOf(productoGestionSeleccionado3);
                listaProductosMuroData3.set(i1, producto);
                listaProductosGestionData3.set(i3, producto);
                tableListaProductosGestion3.refresh();
                tableListaProductos3.refresh();
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

    public void onEliminar3Click(ActionEvent actionEvent) throws VendedorNoExisteException {
        eliminarProducto();
    }

    public void onCancelar3Click(ActionEvent actionEvent) {
        tableListaProductosGestion3.getSelectionModel().clearSelection();
        limpiarCamposProducto();
    }

    private void crearProducto() throws VendedorNoExisteException {
        String nombre = tfGestionNombre3.getText();
        double precio = Double.parseDouble(tfGestionPrecio3.getText());
        EstadoProducto estado = (EstadoProducto) cbEstado.getValue();
        if (datosValidosProducto(nombre, precio, estado)) {
            Producto producto = null;
            Vendedor vendedor = modelFactoryController.retornarVendedor(userVendedor);
            producto = modelFactoryController.crearProducto(nombre, precio, estado, vendedor);
            if (producto != null) {
                listaProductosMuroData3.add(producto);
                listaProductosGestionData3.add(producto);
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
        if (productoGestionSeleccionado3 != null) {
            String id = productoGestionSeleccionado3.getId();
            if (MensajeUtil.mostrarMensajeConfirmacion("¿Estás seguro de elmininar el producto?")) {
                Vendedor vendedor = modelFactoryController.retornarVendedor(userVendedor);
                int index = modelFactoryController.getIndiceVendedor(vendedor);
                productoEliminado = modelFactoryController.eliminarProducto(id, index);
                if (productoEliminado) {
                    listaProductosGestionData3.remove(productoGestionSeleccionado3);
                    listaProductosMuroData3.remove(productoGestionSeleccionado3);
                    productoGestionSeleccionado3 = null;
                    tableListaProductosGestion3.getSelectionModel().clearSelection();
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
    public void agregarLike(Producto productoMuroSeleccionado3, Vendedor vendedorLogueado3) {
        try{
            Objects.requireNonNull(productoMuroSeleccionado3, "Es necesario que seleccione un producto");
            // Verificar si el vendedor logueado ya ha dado like
            boolean vendedorDioLike = false;
            for (Like like : productoMuroSeleccionado3.getListaLikes()) {
                if (like.getVendedor().getUsuario().equals(vendedorLogueado3.getUsuario())) {
                    vendedorDioLike = true;
                    break;
                }
            }

            if (vendedorDioLike) {
                MensajeUtil.mostrarMensaje("Información","El vendedor ya le dió Like", "El vendedor ya le dió like", Alert.AlertType.INFORMATION);
            } else {
                // Si el vendedor no ha dado like, se incrementa el contador de likes
                productoMuroSeleccionado3.incrementarLikes();
                // Agregar un nuevo like a la lista de likes del producto
                Like nuevoLike = new Like(productoMuroSeleccionado3, vendedorLogueado3, LocalDateTime.now());
                productoMuroSeleccionado3.getListaLikes().add(nuevoLike);
            }}catch (Exception e){
            MensajeUtil.mostrarMensaje("Alerta","Seleccione un producto",e.getMessage(),Alert.AlertType.ERROR);
        }
    }
    public void agregarComentario(Producto productoMuroSeleccionado3,Vendedor vendedorLogueado3){
        try{
            Objects.requireNonNull(productoMuroSeleccionado3,"Es necesario que seleccione un producto");
            String ComentarioTexto  =  tAComentar3.getText();
            if(!ComentarioTexto.isEmpty()){
                LocalDateTime fechaActual = LocalDateTime.now();

                Comentario comentario = new Comentario(productoMuroSeleccionado3,vendedorLogueado3,fechaActual,ComentarioTexto);

                productoMuroSeleccionado3.getListaComentarios().add(comentario);
                tAComentar3.clear();

            }
            else {
                MensajeUtil.mostrarMensaje("Error","Comentario vacio","El comentario no puede estar vacio", Alert.AlertType.ERROR);

            }}catch (Exception e){
            MensajeUtil.mostrarMensaje("Error", "Seleccione un producto,",e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void limpiarCamposProducto() {
        tfGestionNombre3.setText("");
        tfGestionPrecio3.setText("");
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

    private void inicializarVendedorView3() {
        inicializarProductosMuro3();
        inicializarComentariosMuro3();
        inicializarLikesMuro3();
        inicializarProductosGestion3();
        inicializarVendedoresSugeridosContactos3();
        inicializarListaSugeridosGestion3();
        inicializarContactosChat3();
    }

    private void inicializarProductosMuro3() {

        this.colListaProductosNombre3.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.colListaProductosPrecio3.setCellValueFactory(new PropertyValueFactory<>("precio"));
        this.colListaProductosEstado3.setCellValueFactory(new PropertyValueFactory<>("estado"));
        this.colListaProductosfecha3.setCellValueFactory(new PropertyValueFactory<>("fechaPublicacion"));
        this.colListaProductosVendedor3.setCellValueFactory(new PropertyValueFactory<>("idVendedor"));
        this.colListaLikes3.setCellValueFactory(new PropertyValueFactory<>("contadorLikes"));

        tableListaProductos3.getItems().clear();
        tableListaProductos3.setItems(getListaProductosMuroData3());
        colListaProductosfecha3.setSortType(TableColumn.SortType.ASCENDING);

        tableListaProductos3.getSortOrder().add(colListaProductosfecha3);
        tableListaProductos3.sort();

        tableListaProductos3.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {

            productoMuroSeleccionado3 = newSelection;
            mostrarInformacionComentariosMuro3(productoMuroSeleccionado3);
            mostrarInformacionLikeMuro3(productoMuroSeleccionado3);
        });
    }

    public ObservableList<Producto> getListaProductosMuroData3() {
        listaProductosMuroData3.addAll(modelFactoryController.obtenerProductos());
        return listaProductosMuroData3;
    }

    private void inicializarComentariosMuro3() {

        this.colComentariosFecha3.setCellValueFactory(new PropertyValueFactory<>("fechaComentario"));
        this.colComentariosProducto3.setCellValueFactory(new PropertyValueFactory<>("nombreProducto"));
        this.colComentariosEmisor3.setCellValueFactory(new PropertyValueFactory<>("nombreVendedor"));
        this.colComentariosComentario3.setCellValueFactory(new PropertyValueFactory<>("comentario"));

    }

    private void mostrarInformacionComentariosMuro3(Producto productoSeleccionado) {
        if (productoSeleccionado != null) {
            tableComentariosVendedor3.getItems().clear();
            tableComentariosVendedor3.setItems((getListaComentariosVendedorMuroData3(productoSeleccionado)));
        }
    }

    public ObservableList<Comentario> getListaComentariosVendedorMuroData3(Producto productoSeleccionado) {
        listaComentariosVendedorMuroData3.addAll(productoSeleccionado.getListaComentarios());
        return listaComentariosVendedorMuroData3;
    }

    private void inicializarLikesMuro3() {

        this.colLikesFecha3.setCellValueFactory(new PropertyValueFactory<>("fechaLike"));
        this.colLikesProducto3.setCellValueFactory(new PropertyValueFactory<>("nombreProducto"));
        this.colLikesEmisor3.setCellValueFactory(new PropertyValueFactory<>("nombreVendedor"));

    }

    private void mostrarInformacionLikeMuro3(Producto productoSeleccionado) {
        if (productoSeleccionado != null) {
            tableLikeVendedor3.getItems().clear();
            tableLikeVendedor3.setItems((getListaLikesVendedorMuroData3(productoSeleccionado)));
        }
    }

    public ObservableList<Like> getListaLikesVendedorMuroData3(Producto productoSeleccionado) {
        listaLikesVendedorMuroData3.addAll(productoSeleccionado.getListaLikes());
        return listaLikesVendedorMuroData3;
    }

    private void inicializarVendedoresSugeridosContactos3() {

        this.colContactosSugeridosNombre3.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.colContactosSugeridosApellido3.setCellValueFactory(new PropertyValueFactory<>("apellido"));

        tablaContactosSugeridos3.getItems().clear();
        tablaContactosSugeridos3.setItems((getListaContactosSugeridosContactosData3()));

        tablaContactosSugeridos3.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {

            contactoSugeridoContactosSeleccionado3 = newSelection;

        });
    }

    public ObservableList<Vendedor> getListaContactosSugeridosContactosData3() {
        listaContactosSugeridosContactosData3.addAll(modelFactoryController.obtenerContactosSugeridosVendedorN(2));
        return listaContactosSugeridosContactosData3;
    }

    private void inicializarProductosGestion3() {

        this.colGestionNombre3.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.colGestionPrecio3.setCellValueFactory(new PropertyValueFactory<>("precio"));
        this.colGestionEstado3.setCellValueFactory(new PropertyValueFactory<>("estado"));
        this.colGestionFecha3.setCellValueFactory(new PropertyValueFactory<>("fechaPublicacion"));
        this.colGestionVendedor3.setCellValueFactory(new PropertyValueFactory<>("idVendedor"));
        this.colGestionLikes3.setCellValueFactory(new PropertyValueFactory<>("contadorLikes"));
        cbEstado.setItems(FXCollections.observableArrayList(EstadoProducto.values()));

        tableListaProductosGestion3.getItems().clear();
        tableListaProductosGestion3.setItems(getListaProductosGestionData3());
        colGestionFecha3.setSortType(TableColumn.SortType.ASCENDING);
        tableListaProductosGestion3.getSortOrder().add(colGestionFecha3);
        tableListaProductosGestion3.sort();
        tableListaProductosGestion3.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {

            productoGestionSeleccionado3 = newSelection;
            mostrarInformacionProductosGestion3(productoGestionSeleccionado3);
        });
    }

    public ObservableList<Producto> getListaProductosGestionData3() {
        listaProductosGestionData3.addAll(modelFactoryController.obtenerProductosVendedorN(2));
        return listaProductosGestionData3;
    }

    private void mostrarInformacionProductosGestion3(Producto productoSeleccionado) {
        if (productoSeleccionado != null) {
            tfGestionNombre3.setText(productoSeleccionado.getNombre());
            tfGestionPrecio3.setText(String.valueOf((int) productoSeleccionado.getPrecio()));
            cbEstado.setValue(productoSeleccionado.getEstado());
        }
    }

    private void inicializarListaSugeridosGestion3() {

        this.colListaSugeridosNombre3.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.colListaSugeridosApellido3.setCellValueFactory(new PropertyValueFactory<>("apellido"));

        tableListaSugeridosGestion3.getItems().clear();
        tableListaSugeridosGestion3.setItems((getListaSugeridosGestionData3()));

        tableListaSugeridosGestion3.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {

            contactoSugeridoGestionSeleccionado3 = newSelection;

        });
    }

    public ObservableList<Vendedor> getListaSugeridosGestionData3() {
        listaContactosSugeridosGestionData3.addAll(modelFactoryController.obtenerListaContactosSugeridosVendedor1());
        return listaContactosSugeridosGestionData3;
    }

    private void inicializarContactosChat3() {

        this.colListaContactosNombre3.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.colListaContactosApellido3.setCellValueFactory(new PropertyValueFactory<>("apellido"));

        tableListaContactos3.getItems().clear();
        tableListaContactos3.setItems((getListaContactosChat3()));

        tableListaContactos3.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {

            contactoChatSeleccionado3 = newSelection;

        });
    }

    public ObservableList<Vendedor> getListaContactosChat3() {
        listaContactosChatData3.addAll(modelFactoryController.obtenerContactosSugeridosVendedorN(2));
        return listaContactosChatData3;
    }
    public void actualizarTablasContactosClientes3(){
        tablaContactosSugeridos3.getItems().clear();
        tablaContactosSugeridos3.setItems((getListaContactosSugeridosContactosData3()));
        tableListaSugeridosGestion3.getItems().clear();
        tableListaSugeridosGestion3.setItems((getListaSugeridosGestionData3()));
        tableListaContactos3.getItems().clear();
        tableListaContactos3.setItems((getListaContactosChat3()));
    }
//    public void actualizarTablasProductosVendedor3(){
//        tableListaProductos3.getItems().clear();
//        tableListaProductos3.setItems((getListaProductosMuroData3()));
//        tableListaProductosGestion3.getItems().clear();
//        tableListaProductosGestion3.setItems((getListaProductosGestionData3()));
//    }
}

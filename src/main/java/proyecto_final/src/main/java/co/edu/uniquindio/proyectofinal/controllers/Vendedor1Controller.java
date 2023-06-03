package co.edu.uniquindio.proyectofinal.controllers;

import co.edu.uniquindio.proyectofinal.exceptions.VendedorNoExisteException;
import co.edu.uniquindio.proyectofinal.model.*;
import co.edu.uniquindio.proyectofinal.utils.MensajeUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDateTime;
import java.util.Objects;

public class Vendedor1Controller {

    ModelFactoryController modelFactoryController;
    LoginController loginController;

    ObservableList<Producto> listaProductosMuroData1 = FXCollections.observableArrayList();
    Producto productoMuroSeleccionado1;

    Vendedor vendedorLogueado;
    ObservableList<Comentario> listaComentariosVendedorMuroData1 = FXCollections.observableArrayList();

    ObservableList<Like> listaLikesVendedorMuroData1 = FXCollections.observableArrayList();

    ObservableList<Vendedor> listaContactosSugeridosContactosData1 = FXCollections.observableArrayList();
    Vendedor contactoSugeridoContactosSeleccionado1;

    ObservableList<Producto> listaProductosGestionData1 = FXCollections.observableArrayList();
    Producto productoGestionSeleccionado1;

    ObservableList<Vendedor> listaContactosSugeridosGestionData1 = FXCollections.observableArrayList();
    Vendedor contactoSugeridoGestionSeleccionado1;

    ObservableList<Vendedor> listaContactosChatData1 = FXCollections.observableArrayList();
    Vendedor contactoChatSeleccionado1;
    @FXML
    private TableColumn<Comentario, String> colComentariosComentario1;

    @FXML
    private TableColumn<Comentario, String> colComentariosEmisor1;

    @FXML
    private TableColumn<Comentario, LocalDateTime> colComentariosFecha1;

    @FXML
    private TableColumn<Comentario, String> colComentariosProducto1;

    @FXML
    private TableColumn<Vendedor, String> colContactosSugeridosApellido1;

    @FXML
    private TableColumn<Vendedor, String> colContactosSugeridosNombre1;

    @FXML
    private TableColumn<Producto, EstadoProducto> colGestionEstado1;

    @FXML
    private TableColumn<Producto, LocalDateTime> colGestionFecha1;
    //gestión vendedor 120 fecha 200 muro fecha 150 vendedor 118 fecha abajo 120

    @FXML
    private TableColumn<Producto, Integer> colGestionLikes1;

    @FXML
    private TableColumn<Producto, String> colGestionNombre1;

    @FXML
    private TableColumn<Producto, Double> colGestionPrecio1;

    @FXML
    private TableColumn<Producto, String> colGestionVendedor1;

    @FXML
    private TableColumn<Like, String> colLikesEmisor1;

    @FXML
    private TableColumn<Comentario, LocalDateTime> colLikesFecha1;

    @FXML
    private TableColumn<Like, String> colLikesProducto1;

    @FXML
    private TableColumn<Vendedor, String> colListaContactosApellido1;

    @FXML
    private TableColumn<Vendedor, String> colListaContactosNombre1;

    @FXML
    private TableColumn<Producto, Integer> colListaLikes1;

    @FXML
    private TableColumn<Producto, EstadoProducto> colListaProductosEstado1;

    @FXML
    private TableColumn<Producto, String> colListaProductosNombre1;

    @FXML
    private TableColumn<Producto, Double> colListaProductosPrecio1;

    @FXML
    private TableColumn<Producto, String> colListaProductosVendedor1;

    @FXML
    private TableColumn<Producto, LocalDateTime> colListaProductosfecha1;

    @FXML
    private TableColumn<Vendedor, String> colListaSugeridosApellido1;

    @FXML
    private TableColumn<Vendedor, String> colListaSugeridosNombre1;

    @FXML
    private TextArea tAComentar1;

    @FXML
    private TableView<Vendedor> tablaContactosSugeridos1;

    @FXML
    private TableView<Comentario> tableComentariosVendedor1;

    @FXML
    private TableView<Like> tableLikeVendedor1;

    @FXML
    private TableView<Vendedor> tableListaContactos1;

    @FXML
    private TableView<Producto> tableListaProductos1;

    @FXML
    private TableView<Producto> tableListaProductosGestion1;

    @FXML
    private TableView<Vendedor> tableListaSugeridosGestion1;

    @FXML
    private TextField tfGestionNombre1;

    @FXML
    private TextField tfGestionPrecio1;
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
        inicializarVendedorView1();
    }

    @FXML
    void loginVendedor(ActionEvent event) {
        String user = tfUser.getText();
        String pass = pfPass.getText();
        if (loginController.loginVendedor(user, pass, 0)) {
            userVendedor = user;
            tabChat.setDisable(false);
            tabGestion.setDisable(false);
            vendedorLogueado = modelFactoryController.getMarketplace().getListaVendedores().get(0);
        }
    }

    public void onComentarVendedor1Click(ActionEvent actionEvent) {
        Vendedor vendedorComentario = vendedorLogueado;
        Producto productoComentario = productoMuroSeleccionado1;
        agregarComentario(productoComentario,vendedorComentario);
        tableComentariosVendedor1.refresh();
        tableListaProductos1.getSelectionModel().clearSelection();



    }

    public void onLikeVendedor1Click(ActionEvent actionEvent) {
        // Obtener el producto y el vendedor logueado correspondientes a la acción
        Producto producto = productoMuroSeleccionado1;
        if(vendedorLogueado!=null){
            Vendedor vendedorInicializado = vendedorLogueado;
            agregarLike(producto, vendedorInicializado);
            tableListaProductos1.refresh();
            tableListaProductos1.getSelectionModel().clearSelection();
        } else{
            MensajeUtil.mostrarMensaje("Error","Vendedor no logueado", " Debe loguearse como vendedor",Alert.AlertType.ERROR);
        }

    }

    public void onAgregar1Click(ActionEvent actionEvent) {
    }

    public void onAnadir1Click(ActionEvent actionEvent) throws VendedorNoExisteException {
        crearProducto();
    }

    public void onBuscar1Click(ActionEvent actionEvent) {
    }

    public void onModificar1Click(ActionEvent actionEvent) throws VendedorNoExisteException {
        modificarProducto();
    }

    private void modificarProducto() throws VendedorNoExisteException {
        String nombre = tfGestionNombre1.getText();
        double precio = Double.parseDouble(tfGestionPrecio1.getText());
        EstadoProducto estado = (EstadoProducto) cbEstado.getValue();
        String id = productoGestionSeleccionado1.getId();
        Producto producto = null;
        if (datosValidosProducto(nombre, precio, estado)) {
            Vendedor vendedor = modelFactoryController.retornarVendedor(userVendedor);
            producto = modelFactoryController.actualizarProducto(nombre, precio, estado, id, vendedor);
            if (producto != null) {
                int i1 = listaProductosMuroData1.indexOf(productoGestionSeleccionado1);
                int i2 = listaProductosGestionData1.indexOf(productoGestionSeleccionado1);
                listaProductosMuroData1.set(i1, producto);
                listaProductosGestionData1.set(i2, producto);
                tableListaProductosGestion1.refresh();
                tableListaProductos1.refresh();
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

    public void onEliminar1Click(ActionEvent actionEvent) throws VendedorNoExisteException {
        eliminarProducto();
    }

    public void onCancelar1Click(ActionEvent actionEvent) {
        tableListaProductosGestion1.getSelectionModel().clearSelection();
        limpiarCamposProducto();
    }

    private void crearProducto() throws VendedorNoExisteException {
        String nombre = tfGestionNombre1.getText();
        double precio = Double.parseDouble(tfGestionPrecio1.getText());
        EstadoProducto estado = (EstadoProducto) cbEstado.getValue();
        if (datosValidosProducto(nombre, precio, estado)) {
            Producto producto = null;
            Vendedor vendedor = modelFactoryController.retornarVendedor(userVendedor);
            producto = modelFactoryController.crearProducto(nombre, precio, estado, vendedor);
            if (producto != null) {
                listaProductosMuroData1.add(producto);
                listaProductosGestionData1.add(producto);
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
        if (productoGestionSeleccionado1 != null) {
            String id = productoGestionSeleccionado1.getId();
            if (MensajeUtil.mostrarMensajeConfirmacion("¿Estás seguro de elmininar el producto?")) {
                Vendedor vendedor = modelFactoryController.retornarVendedor(userVendedor);
                int index = modelFactoryController.getIndiceVendedor(vendedor);
                productoEliminado = modelFactoryController.eliminarProducto(id, index);
                if (productoEliminado) {
                    listaProductosGestionData1.remove(productoGestionSeleccionado1);
                    listaProductosMuroData1.remove(productoGestionSeleccionado1);
                    productoGestionSeleccionado1 = null;
                    tableListaProductosGestion1.getSelectionModel().clearSelection();
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
    public void agregarLike(Producto productoMuroSeleccionado1, Vendedor vendedorLogueado) {
        try{
        Objects.requireNonNull(productoMuroSeleccionado1, "Es necesario que seleccione un producto");
        // Verificar si el vendedor logueado ya ha dado like
        boolean vendedorDioLike = false;
        for (Like like : productoMuroSeleccionado1.getListaLikes()) {
            if (like.getVendedor().getUsuario().equals(vendedorLogueado.getUsuario())) {
                vendedorDioLike = true;
                break;
            }
        }

        if (vendedorDioLike) {
           MensajeUtil.mostrarMensaje("Información","El vendedor ya le dió Like", "El vendedor ya le dió like", Alert.AlertType.INFORMATION);
        } else {
            // Si el vendedor no ha dado like, se incrementa el contador de likes
            productoMuroSeleccionado1.incrementarLikes();
            // Agregar un nuevo like a la lista de likes del producto
            Like nuevoLike = new Like(productoMuroSeleccionado1, vendedorLogueado, LocalDateTime.now());
            productoMuroSeleccionado1.getListaLikes().add(nuevoLike);
        }}catch (Exception e){
            MensajeUtil.mostrarMensaje("Alerta","Seleccione un producto",e.getMessage(),Alert.AlertType.ERROR);
        }
    }
    public void agregarComentario(Producto productoMuroSeleccionado1,Vendedor vendedorLogueado){
        try{
        Objects.requireNonNull(productoMuroSeleccionado1,"Es necesario que seleccione un producto");
        String ComentarioTexto  =  tAComentar1.getText();
        if(!ComentarioTexto.isEmpty()){
            LocalDateTime fechaActual = LocalDateTime.now();

            Comentario comentario = new Comentario(productoMuroSeleccionado1,vendedorLogueado,fechaActual,ComentarioTexto);

            productoMuroSeleccionado1.getListaComentarios().add(comentario);
            tAComentar1.clear();

        }
        else {
            MensajeUtil.mostrarMensaje("Error","Comentario vacio","El comentario no puede estar vacio", Alert.AlertType.ERROR);

        }}catch (Exception e){
            MensajeUtil.mostrarMensaje("Error", "Seleccione un producto,",e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void limpiarCamposProducto() {
        tfGestionNombre1.setText("");
        tfGestionPrecio1.setText("");
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

    private void inicializarVendedorView1() {
        inicializarProductosMuro1();
        inicializarComentariosMuro1();
        inicializarLikesMuro1();
        inicializarProductosGestion1();
        inicializarVendedoresSugeridosContactos1();
        inicializarListaSugeridosGestion1();
        inicializarContactosChat1();
    }

    private void inicializarProductosMuro1() {

        this.colListaProductosNombre1.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.colListaProductosPrecio1.setCellValueFactory(new PropertyValueFactory<>("precio"));
        this.colListaProductosEstado1.setCellValueFactory(new PropertyValueFactory<>("estado"));
        this.colListaProductosfecha1.setCellValueFactory(new PropertyValueFactory<>("fechaPublicacion"));
        this.colListaProductosVendedor1.setCellValueFactory(new PropertyValueFactory<>("idVendedor"));
        this.colListaLikes1.setCellValueFactory(new PropertyValueFactory<>("contadorLikes"));

        tableListaProductos1.getItems().clear();
        tableListaProductos1.setItems(getListaProductosMuroData1());
        colListaProductosfecha1.setSortType(TableColumn.SortType.ASCENDING);

        tableListaProductos1.getSortOrder().add(colListaProductosfecha1);
        tableListaProductos1.sort();

        tableListaProductos1.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {

            productoMuroSeleccionado1 = newSelection;
            mostrarInformacionComentariosMuro1(productoMuroSeleccionado1);
            mostrarInformacionLikeMuro1(productoMuroSeleccionado1);
        });

    }

    public ObservableList<Producto> getListaProductosMuroData1() {
        listaProductosMuroData1.addAll(modelFactoryController.obtenerProductos());
        return listaProductosMuroData1;
    }

    private void inicializarComentariosMuro1() {

        this.colComentariosFecha1.setCellValueFactory(new PropertyValueFactory<>("fechaComentario"));
        this.colComentariosProducto1.setCellValueFactory(new PropertyValueFactory<>("nombreProducto"));
        this.colComentariosEmisor1.setCellValueFactory(new PropertyValueFactory<>("nombreVendedor"));
        this.colComentariosComentario1.setCellValueFactory(new PropertyValueFactory<>("comentario"));

    }

    private void mostrarInformacionComentariosMuro1(Producto productoSeleccionado) {
        if (productoSeleccionado != null) {
            tableComentariosVendedor1.getItems().clear();
            tableComentariosVendedor1.setItems((getListaComentariosVendedorMuroData1(productoSeleccionado)));
        }
    }

    public ObservableList<Comentario> getListaComentariosVendedorMuroData1(Producto productoSeleccionado) {
        listaComentariosVendedorMuroData1.addAll(productoSeleccionado.getListaComentarios());
        return listaComentariosVendedorMuroData1;
    }

    private void inicializarLikesMuro1() {

        this.colLikesFecha1.setCellValueFactory(new PropertyValueFactory<>("fechaLike"));
        this.colLikesProducto1.setCellValueFactory(new PropertyValueFactory<>("nombreProducto"));
        this.colLikesEmisor1.setCellValueFactory(new PropertyValueFactory<>("nombreVendedor"));

    }


    private void mostrarInformacionLikeMuro1(Producto productoSeleccionado) {
        if (productoSeleccionado != null) {
            tableLikeVendedor1.getItems().clear();
            tableLikeVendedor1.setItems((getListaLikesVendedorMuroData1(productoSeleccionado)));
        }
    }

    public ObservableList<Like> getListaLikesVendedorMuroData1(Producto productoSeleccionado) {
        listaLikesVendedorMuroData1.addAll(productoSeleccionado.getListaLikes());
        return listaLikesVendedorMuroData1;
    }

    private void inicializarVendedoresSugeridosContactos1() {

        this.colContactosSugeridosNombre1.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.colContactosSugeridosApellido1.setCellValueFactory(new PropertyValueFactory<>("apellido"));

        tablaContactosSugeridos1.getItems().clear();
        tablaContactosSugeridos1.setItems((getListaContactosSugeridosContactosData1()));

        tablaContactosSugeridos1.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {

            contactoSugeridoContactosSeleccionado1 = newSelection;

        });
    }

    public ObservableList<Vendedor> getListaContactosSugeridosContactosData1() {
//        listaContactosSugeridosContactosData1.addAll(modelFactoryController.obtenerContactosSugeridosVendedorN(0));
        return listaContactosSugeridosContactosData1;
    }

    private void inicializarProductosGestion1() {

        this.colGestionNombre1.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.colGestionPrecio1.setCellValueFactory(new PropertyValueFactory<>("precio"));
        this.colGestionEstado1.setCellValueFactory(new PropertyValueFactory<>("estado"));
        this.colGestionFecha1.setCellValueFactory(new PropertyValueFactory<>("fechaPublicacion"));
        this.colGestionVendedor1.setCellValueFactory(new PropertyValueFactory<>("idVendedor"));
        this.colGestionLikes1.setCellValueFactory(new PropertyValueFactory<>("contadorLikes"));
        cbEstado.setItems(FXCollections.observableArrayList(EstadoProducto.values()));

        tableListaProductosGestion1.getItems().clear();
//        tableListaProductosGestion1.setItems(getListaProductosGestionData1());
        colGestionFecha1.setSortType(TableColumn.SortType.ASCENDING);
        tableListaProductosGestion1.getSortOrder().add(colGestionFecha1);
        tableListaProductosGestion1.sort();
        tableListaProductosGestion1.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {

            productoGestionSeleccionado1 = newSelection;
            mostrarInformacionProductosGestion1(productoGestionSeleccionado1);
        });
    }

//    public ObservableList<Producto> getListaProductosGestionData1() {
//        listaProductosGestionData1.addAll(modelFactoryController.obtenerProductosVendedorN(0));
//        return listaProductosGestionData1;
//    }

    private void mostrarInformacionProductosGestion1(Producto productoSeleccionado) {
        if (productoSeleccionado != null) {
            tfGestionNombre1.setText(productoSeleccionado.getNombre());
            tfGestionPrecio1.setText(String.valueOf((int) productoSeleccionado.getPrecio()));
            cbEstado.setValue(productoSeleccionado.getEstado());
        }
    }

    private void inicializarListaSugeridosGestion1() {

        this.colListaSugeridosNombre1.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.colListaSugeridosApellido1.setCellValueFactory(new PropertyValueFactory<>("apellido"));

        tableListaSugeridosGestion1.getItems().clear();
        tableListaSugeridosGestion1.setItems((getListaSugeridosGestionData1()));

        tableListaSugeridosGestion1.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {

            contactoSugeridoGestionSeleccionado1 = newSelection;

        });
    }

    public ObservableList<Vendedor> getListaSugeridosGestionData1() {
        listaContactosSugeridosGestionData1.addAll(modelFactoryController.obtenerListaContactosSugeridosVendedor1());
        return listaContactosSugeridosGestionData1;
    }

    private void inicializarContactosChat1() {

        this.colListaContactosNombre1.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.colListaContactosApellido1.setCellValueFactory(new PropertyValueFactory<>("apellido"));

        tableListaContactos1.getItems().clear();
        tableListaContactos1.setItems((getListaContactosChat1()));

        tableListaContactos1.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {

            contactoChatSeleccionado1 = newSelection;

        });
    }

    public ObservableList<Vendedor> getListaContactosChat1() {
//        listaContactosChatData1.addAll(modelFactoryController.obtenerContactosSugeridosVendedorN(0));
        return listaContactosChatData1;
    }
    public void actualizarTablasContactosClientes1(){
        tablaContactosSugeridos1.getItems().clear();
        tablaContactosSugeridos1.setItems((getListaContactosSugeridosContactosData1()));
        tableListaSugeridosGestion1.getItems().clear();
        tableListaSugeridosGestion1.setItems((getListaSugeridosGestionData1()));
        tableListaContactos1.getItems().clear();
        tableListaContactos1.setItems((getListaContactosChat1()));
    }
//    public void actualizarTablasProductosVendedor1(){
//        tableListaProductos1.getItems().clear();
//        tableListaProductos1.setItems((getListaProductosMuroData1()));
//        tableListaProductosGestion1.getItems().clear();
//        tableListaProductosGestion1.setItems((getListaProductosGestionData1()));
//    }
}

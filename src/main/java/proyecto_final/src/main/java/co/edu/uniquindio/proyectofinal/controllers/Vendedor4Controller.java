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

public class Vendedor4Controller {

    ModelFactoryController modelFactoryController;
    LoginController loginController;

    ObservableList<Producto> listaProductosMuroData4 = FXCollections.observableArrayList();
    Producto productoMuroSeleccionado4;
    Vendedor vendedorLogueado4;


    ObservableList<Comentario> listaComentariosVendedorMuroData4 = FXCollections.observableArrayList();

    ObservableList<Like> listaLikesVendedorMuroData4 = FXCollections.observableArrayList();

    ObservableList<Vendedor> listaContactosSugeridosContactosData4 = FXCollections.observableArrayList();
    Vendedor contactoSugeridoContactosSeleccionado4;

    ObservableList<Producto> listaProductosGestionData4 = FXCollections.observableArrayList();
    Producto productoGestionSeleccionado4;

    ObservableList<Vendedor> listaContactosSugeridosGestionData4 = FXCollections.observableArrayList();
    Vendedor contactoSugeridoGestionSeleccionado4;

    ObservableList<Vendedor> listaContactosChatData4 = FXCollections.observableArrayList();
    Vendedor contactoChatSeleccionado4;
    @FXML
    private TableColumn<Comentario, String> colComentariosComentario4;

    @FXML
    private TableColumn<Comentario, String> colComentariosEmisor4;

    @FXML
    private TableColumn<Comentario, LocalDateTime> colComentariosFecha4;

    @FXML
    private TableColumn<Comentario, String> colComentariosProducto4;

    @FXML
    private TableColumn<Vendedor, String> colContactosSugeridosApellido4;

    @FXML
    private TableColumn<Vendedor, String> colContactosSugeridosNombre4;

    @FXML
    private TableColumn<Producto, EstadoProducto> colGestionEstado4;

    @FXML
    private TableColumn<Producto, LocalDateTime> colGestionFecha4;
    //gestión vendedor 440 fecha 400 muro fecha 450 vendedor 448 fecha abajo 440

    @FXML
    private TableColumn<Producto, Integer> colGestionLikes4;

    @FXML
    private TableColumn<Producto, String> colGestionNombre4;

    @FXML
    private TableColumn<Producto, Double> colGestionPrecio4;

    @FXML
    private TableColumn<Producto, String> colGestionVendedor4;

    @FXML
    private TableColumn<Like, String> colLikesEmisor4;

    @FXML
    private TableColumn<Comentario, LocalDateTime> colLikesFecha4;

    @FXML
    private TableColumn<Like, String> colLikesProducto4;

    @FXML
    private TableColumn<Vendedor, String> colListaContactosApellido4;

    @FXML
    private TableColumn<Vendedor, String> colListaContactosNombre4;

    @FXML
    private TableColumn<Producto, Integer> colListaLikes4;

    @FXML
    private TableColumn<Producto, EstadoProducto> colListaProductosEstado4;

    @FXML
    private TableColumn<Producto, String> colListaProductosNombre4;

    @FXML
    private TableColumn<Producto, Double> colListaProductosPrecio4;

    @FXML
    private TableColumn<Producto, String> colListaProductosVendedor4;

    @FXML
    private TableColumn<Producto, LocalDateTime> colListaProductosfecha4;

    @FXML
    private TableColumn<Vendedor, String> colListaSugeridosApellido4;

    @FXML
    private TableColumn<Vendedor, String> colListaSugeridosNombre4;

    @FXML
    private TextArea tAComentar4;

    @FXML
    private TableView<Vendedor> tablaContactosSugeridos4;

    @FXML
    private TableView<Comentario> tableComentariosVendedor4;

    @FXML
    private TableView<Like> tableLikeVendedor4;

    @FXML
    private TableView<Vendedor> tableListaContactos4;

    @FXML
    private TableView<Producto> tableListaProductos4;

    @FXML
    private TableView<Producto> tableListaProductosGestion4;

    @FXML
    private TableView<Vendedor> tableListaSugeridosGestion4;

    @FXML
    private TextField tfGestionNombre4;

    @FXML
    private TextField tfGestionPrecio4;
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
        inicializarVendedorView4();
    }

    @FXML
    void loginVendedor(ActionEvent event) {
        String user = tfUser.getText();
        String pass = pfPass.getText();
        if (loginController.loginVendedor(user, pass, 3)) {
            userVendedor = user;
            tabChat.setDisable(false);
            tabGestion.setDisable(false);
            vendedorLogueado4 = modelFactoryController.getMarketplace().getListaVendedores().get(3);

        }
    }

    public void onComentarVendedor4Click(ActionEvent actionEvent) {
        Vendedor vendedorComentario = vendedorLogueado4;
        Producto productoComentario = productoMuroSeleccionado4;
        agregarComentario(productoComentario,vendedorComentario);
        tableComentariosVendedor4.refresh();
        tableListaProductos4.getSelectionModel().clearSelection();
    }

    public void onLikeVendedor4Click(ActionEvent actionEvent){
        // Obtener el producto y el vendedor logueado correspondientes a la acción
        Producto producto = productoMuroSeleccionado4;
        if(vendedorLogueado4!=null){
            Vendedor vendedorInicializado = vendedorLogueado4;
            agregarLike(producto, vendedorInicializado);
            tableListaProductos4.refresh();
            tableListaProductos4.getSelectionModel().clearSelection();
        } else{
            MensajeUtil.mostrarMensaje("Error","Vendedor no logueado", " Debe loguearse como vendedor",Alert.AlertType.ERROR);
        }

    }

    public void onAgregar4Click(ActionEvent actionEvent) {
    }

    public void onAnadir4Click(ActionEvent actionEvent) throws VendedorNoExisteException {
        crearProducto();
    }

    public void onBuscar4Click(ActionEvent actionEvent) {
    }

    public void onModificar4Click(ActionEvent actionEvent) throws VendedorNoExisteException {
        modificarProducto();
    }

    private void modificarProducto() throws VendedorNoExisteException {
        String nombre = tfGestionNombre4.getText();
        double precio = Double.parseDouble(tfGestionPrecio4.getText());
        EstadoProducto estado = (EstadoProducto) cbEstado.getValue();
        String id = productoGestionSeleccionado4.getId();
        Producto producto = null;
        if (datosValidosProducto(nombre, precio, estado)) {
            Vendedor vendedor = modelFactoryController.retornarVendedor(userVendedor);
            producto = modelFactoryController.actualizarProducto(nombre, precio, estado, id, vendedor);
            if (producto != null) {
                int i1 = listaProductosMuroData4.indexOf(productoGestionSeleccionado4);
                int i4 = listaProductosGestionData4.indexOf(productoGestionSeleccionado4);
                listaProductosMuroData4.set(i1, producto);
                listaProductosGestionData4.set(i4, producto);
                tableListaProductosGestion4.refresh();
                tableListaProductos4.refresh();
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

    public void onEliminar4Click(ActionEvent actionEvent) throws VendedorNoExisteException {
        eliminarProducto();
    }

    public void onCancelar4Click(ActionEvent actionEvent) {
        tableListaProductosGestion4.getSelectionModel().clearSelection();
        limpiarCamposProducto();
    }

    private void crearProducto() throws VendedorNoExisteException {
        String nombre = tfGestionNombre4.getText();
        double precio = Double.parseDouble(tfGestionPrecio4.getText());
        EstadoProducto estado = (EstadoProducto) cbEstado.getValue();
        if (datosValidosProducto(nombre, precio, estado)) {
            Producto producto = null;
            Vendedor vendedor = modelFactoryController.retornarVendedor(userVendedor);
            producto = modelFactoryController.crearProducto(nombre, precio, estado, vendedor);
            if (producto != null) {
                listaProductosMuroData4.add(producto);
                listaProductosGestionData4.add(producto);
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
        if (productoGestionSeleccionado4 != null) {
            String id = productoGestionSeleccionado4.getId();
            if (MensajeUtil.mostrarMensajeConfirmacion("¿Estás seguro de elmininar el producto?")) {
                Vendedor vendedor = modelFactoryController.retornarVendedor(userVendedor);
                int index = modelFactoryController.getIndiceVendedor(vendedor);
                productoEliminado = modelFactoryController.eliminarProducto(id, index);
                if (productoEliminado) {
                    listaProductosGestionData4.remove(productoGestionSeleccionado4);
                    listaProductosMuroData4.remove(productoGestionSeleccionado4);
                    productoGestionSeleccionado4 = null;
                    tableListaProductosGestion4.getSelectionModel().clearSelection();
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
    public void agregarLike(Producto productoMuroSeleccionado4, Vendedor vendedorLogueado4) {
        try{
            Objects.requireNonNull(productoMuroSeleccionado4, "Es necesario que seleccione un producto");
            // Verificar si el vendedor logueado ya ha dado like
            boolean vendedorDioLike = false;
            for (Like like : productoMuroSeleccionado4.getListaLikes()) {
                if (like.getVendedor().getUsuario().equals(vendedorLogueado4.getUsuario())) {
                    vendedorDioLike = true;
                    break;
                }
            }

            if (vendedorDioLike) {
                MensajeUtil.mostrarMensaje("Información","El vendedor ya le dió Like", "El vendedor ya le dió like", Alert.AlertType.INFORMATION);
            } else {
                // Si el vendedor no ha dado like, se incrementa el contador de likes
                productoMuroSeleccionado4.incrementarLikes();
                // Agregar un nuevo like a la lista de likes del producto
                Like nuevoLike = new Like(productoMuroSeleccionado4, vendedorLogueado4, LocalDateTime.now());
                productoMuroSeleccionado4.getListaLikes().add(nuevoLike);
            }}catch (Exception e){
            MensajeUtil.mostrarMensaje("Alerta","Seleccione un producto",e.getMessage(),Alert.AlertType.ERROR);
        }
    }
    public void agregarComentario(Producto productoMuroSeleccionado4,Vendedor vendedorLogueado4){
        try{
            Objects.requireNonNull(productoMuroSeleccionado4,"Es necesario que seleccione un producto");
            String ComentarioTexto  =  tAComentar4.getText();
            if(!ComentarioTexto.isEmpty()){
                LocalDateTime fechaActual = LocalDateTime.now();

                Comentario comentario = new Comentario(productoMuroSeleccionado4,vendedorLogueado4,fechaActual,ComentarioTexto);

                productoMuroSeleccionado4.getListaComentarios().add(comentario);
                tAComentar4.clear();

            }
            else {
                MensajeUtil.mostrarMensaje("Error","Comentario vacio","El comentario no puede estar vacio", Alert.AlertType.ERROR);

            }}catch (Exception e){
            MensajeUtil.mostrarMensaje("Error", "Seleccione un producto,",e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void limpiarCamposProducto() {
        tfGestionNombre4.setText("");
        tfGestionPrecio4.setText("");
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

    private void inicializarVendedorView4() {
        inicializarProductosMuro4();
        inicializarComentariosMuro4();
        inicializarLikesMuro4();
        inicializarProductosGestion4();
        inicializarVendedoresSugeridosContactos4();
        inicializarListaSugeridosGestion4();
        inicializarContactosChat4();
    }

    private void inicializarProductosMuro4() {

        this.colListaProductosNombre4.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.colListaProductosPrecio4.setCellValueFactory(new PropertyValueFactory<>("precio"));
        this.colListaProductosEstado4.setCellValueFactory(new PropertyValueFactory<>("estado"));
        this.colListaProductosfecha4.setCellValueFactory(new PropertyValueFactory<>("fechaPublicacion"));
        this.colListaProductosVendedor4.setCellValueFactory(new PropertyValueFactory<>("idVendedor"));
        this.colListaLikes4.setCellValueFactory(new PropertyValueFactory<>("contadorLikes"));

        tableListaProductos4.getItems().clear();
        tableListaProductos4.setItems(getListaProductosMuroData4());
        colListaProductosfecha4.setSortType(TableColumn.SortType.ASCENDING);

        tableListaProductos4.getSortOrder().add(colListaProductosfecha4);
        tableListaProductos4.sort();

        tableListaProductos4.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {

            productoMuroSeleccionado4 = newSelection;
            mostrarInformacionComentariosMuro4(productoMuroSeleccionado4);
            mostrarInformacionLikeMuro4(productoMuroSeleccionado4);
        });
    }

    public ObservableList<Producto> getListaProductosMuroData4() {
        listaProductosMuroData4.addAll(modelFactoryController.obtenerProductos());
        return listaProductosMuroData4;
    }

    private void inicializarComentariosMuro4() {

        this.colComentariosFecha4.setCellValueFactory(new PropertyValueFactory<>("fechaComentario"));
        this.colComentariosProducto4.setCellValueFactory(new PropertyValueFactory<>("nombreProducto"));
        this.colComentariosEmisor4.setCellValueFactory(new PropertyValueFactory<>("nombreVendedor"));
        this.colComentariosComentario4.setCellValueFactory(new PropertyValueFactory<>("comentario"));

    }

    private void mostrarInformacionComentariosMuro4(Producto productoSeleccionado) {
        if (productoSeleccionado != null) {
            tableComentariosVendedor4.getItems().clear();
            tableComentariosVendedor4.setItems((getListaComentariosVendedorMuroData4(productoSeleccionado)));
        }
    }

    public ObservableList<Comentario> getListaComentariosVendedorMuroData4(Producto productoSeleccionado) {
        listaComentariosVendedorMuroData4.addAll(productoSeleccionado.getListaComentarios());
        return listaComentariosVendedorMuroData4;
    }

    private void inicializarLikesMuro4() {

        this.colLikesFecha4.setCellValueFactory(new PropertyValueFactory<>("fechaLike"));
        this.colLikesProducto4.setCellValueFactory(new PropertyValueFactory<>("nombreProducto"));
        this.colLikesEmisor4.setCellValueFactory(new PropertyValueFactory<>("nombreVendedor"));

    }

    private void mostrarInformacionLikeMuro4(Producto productoSeleccionado) {
        if (productoSeleccionado != null) {
            tableLikeVendedor4.getItems().clear();
            tableLikeVendedor4.setItems((getListaLikesVendedorMuroData4(productoSeleccionado)));
        }
    }

    public ObservableList<Like> getListaLikesVendedorMuroData4(Producto productoSeleccionado) {
        listaLikesVendedorMuroData4.addAll(productoSeleccionado.getListaLikes());
        return listaLikesVendedorMuroData4;
    }

    private void inicializarVendedoresSugeridosContactos4() {

        this.colContactosSugeridosNombre4.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.colContactosSugeridosApellido4.setCellValueFactory(new PropertyValueFactory<>("apellido"));

        tablaContactosSugeridos4.getItems().clear();
        tablaContactosSugeridos4.setItems((getListaContactosSugeridosContactosData4()));

        tablaContactosSugeridos4.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {

            contactoSugeridoContactosSeleccionado4 = newSelection;

        });
    }

    public ObservableList<Vendedor> getListaContactosSugeridosContactosData4() {
        listaContactosSugeridosContactosData4.addAll(modelFactoryController.obtenerContactosSugeridosVendedorN(3));
        return listaContactosSugeridosContactosData4;
    }

    private void inicializarProductosGestion4() {

        this.colGestionNombre4.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.colGestionPrecio4.setCellValueFactory(new PropertyValueFactory<>("precio"));
        this.colGestionEstado4.setCellValueFactory(new PropertyValueFactory<>("estado"));
        this.colGestionFecha4.setCellValueFactory(new PropertyValueFactory<>("fechaPublicacion"));
        this.colGestionVendedor4.setCellValueFactory(new PropertyValueFactory<>("idVendedor"));
        this.colGestionLikes4.setCellValueFactory(new PropertyValueFactory<>("contadorLikes"));
        cbEstado.setItems(FXCollections.observableArrayList(EstadoProducto.values()));

        tableListaProductosGestion4.getItems().clear();
        tableListaProductosGestion4.setItems(getListaProductosGestionData4());
        colGestionFecha4.setSortType(TableColumn.SortType.ASCENDING);
        tableListaProductosGestion4.getSortOrder().add(colGestionFecha4);
        tableListaProductosGestion4.sort();
        tableListaProductosGestion4.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {

            productoGestionSeleccionado4 = newSelection;
            mostrarInformacionProductosGestion4(productoGestionSeleccionado4);
        });
    }

    public ObservableList<Producto> getListaProductosGestionData4() {
        listaProductosGestionData4.addAll(modelFactoryController.obtenerProductosVendedorN(3));
        return listaProductosGestionData4;
    }

    private void mostrarInformacionProductosGestion4(Producto productoSeleccionado) {
        if (productoSeleccionado != null) {
            tfGestionNombre4.setText(productoSeleccionado.getNombre());
            tfGestionPrecio4.setText(String.valueOf((int) productoSeleccionado.getPrecio()));
            cbEstado.setValue(productoSeleccionado.getEstado());
        }
    }

    private void inicializarListaSugeridosGestion4() {

        this.colListaSugeridosNombre4.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.colListaSugeridosApellido4.setCellValueFactory(new PropertyValueFactory<>("apellido"));

        tableListaSugeridosGestion4.getItems().clear();
        tableListaSugeridosGestion4.setItems((getListaSugeridosGestionData4()));

        tableListaSugeridosGestion4.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {

            contactoSugeridoGestionSeleccionado4 = newSelection;

        });
    }

    public ObservableList<Vendedor> getListaSugeridosGestionData4() {
        listaContactosSugeridosGestionData4.addAll(modelFactoryController.obtenerListaContactosSugeridosVendedor1());
        return listaContactosSugeridosGestionData4;
    }

    private void inicializarContactosChat4() {

        this.colListaContactosNombre4.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.colListaContactosApellido4.setCellValueFactory(new PropertyValueFactory<>("apellido"));

        tableListaContactos4.getItems().clear();
        tableListaContactos4.setItems((getListaContactosChat4()));

        tableListaContactos4.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {

            contactoChatSeleccionado4 = newSelection;

        });
    }

    public ObservableList<Vendedor> getListaContactosChat4() {
        listaContactosChatData4.addAll(modelFactoryController.obtenerContactosSugeridosVendedorN(3));
        return listaContactosChatData4;
    }
    public void actualizarTablasContactosClientes4(){
        tablaContactosSugeridos4.getItems().clear();
        tablaContactosSugeridos4.setItems((getListaContactosSugeridosContactosData4()));
        tableListaSugeridosGestion4.getItems().clear();
        tableListaSugeridosGestion4.setItems((getListaSugeridosGestionData4()));
        tableListaContactos4.getItems().clear();
        tableListaContactos4.setItems((getListaContactosChat4()));
    }
//    public void actualizarTablasProductosVendedor4(){
//        tableListaProductos4.getItems().clear();
//        tableListaProductos4.setItems((getListaProductosMuroData4()));
//        tableListaProductosGestion4.getItems().clear();
//        tableListaProductosGestion4.setItems((getListaProductosGestionData4()));
//    }
}

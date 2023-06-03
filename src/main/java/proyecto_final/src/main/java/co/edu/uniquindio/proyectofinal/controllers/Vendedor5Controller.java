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

public class Vendedor5Controller {
    ModelFactoryController modelFactoryController;
    LoginController loginController;

    ObservableList<Producto> listaProductosMuroData5 = FXCollections.observableArrayList();
    Producto productoMuroSeleccionado5;
    Vendedor vendedorLogueado5;


    ObservableList<Comentario> listaComentariosVendedorMuroData5 = FXCollections.observableArrayList();

    ObservableList<Like> listaLikesVendedorMuroData5 = FXCollections.observableArrayList();

    ObservableList<Vendedor> listaContactosSugeridosContactosData5 = FXCollections.observableArrayList();
    Vendedor contactoSugeridoContactosSeleccionado5;

    ObservableList<Producto> listaProductosGestionData5 = FXCollections.observableArrayList();
    Producto productoGestionSeleccionado5;

    ObservableList<Vendedor> listaContactosSugeridosGestionData5 = FXCollections.observableArrayList();
    Vendedor contactoSugeridoGestionSeleccionado5;

    ObservableList<Vendedor> listaContactosChatData5 = FXCollections.observableArrayList();
    Vendedor contactoChatSeleccionado5;
    @FXML
    private TableColumn<Comentario, String> colComentariosComentario5;

    @FXML
    private TableColumn<Comentario, String> colComentariosEmisor5;

    @FXML
    private TableColumn<Comentario, LocalDateTime> colComentariosFecha5;

    @FXML
    private TableColumn<Comentario, String> colComentariosProducto5;

    @FXML
    private TableColumn<Vendedor, String> colContactosSugeridosApellido5;

    @FXML
    private TableColumn<Vendedor, String> colContactosSugeridosNombre5;

    @FXML
    private TableColumn<Producto, EstadoProducto> colGestionEstado5;

    @FXML
    private TableColumn<Producto, LocalDateTime> colGestionFecha5;
    //gestión vendedor 550 fecha 500 muro fecha 550 vendedor 558 fecha abajo 550

    @FXML
    private TableColumn<Producto, Integer> colGestionLikes5;

    @FXML
    private TableColumn<Producto, String> colGestionNombre5;

    @FXML
    private TableColumn<Producto, Double> colGestionPrecio5;

    @FXML
    private TableColumn<Producto, String> colGestionVendedor5;

    @FXML
    private TableColumn<Like, String> colLikesEmisor5;

    @FXML
    private TableColumn<Comentario, LocalDateTime> colLikesFecha5;

    @FXML
    private TableColumn<Like, String> colLikesProducto5;

    @FXML
    private TableColumn<Vendedor, String> colListaContactosApellido5;

    @FXML
    private TableColumn<Vendedor, String> colListaContactosNombre5;

    @FXML
    private TableColumn<Producto, Integer> colListaLikes5;

    @FXML
    private TableColumn<Producto, EstadoProducto> colListaProductosEstado5;

    @FXML
    private TableColumn<Producto, String> colListaProductosNombre5;

    @FXML
    private TableColumn<Producto, Double> colListaProductosPrecio5;

    @FXML
    private TableColumn<Producto, String> colListaProductosVendedor5;

    @FXML
    private TableColumn<Producto, LocalDateTime> colListaProductosfecha5;

    @FXML
    private TableColumn<Vendedor, String> colListaSugeridosApellido5;

    @FXML
    private TableColumn<Vendedor, String> colListaSugeridosNombre5;

    @FXML
    private TextArea tAComentar5;

    @FXML
    private TableView<Vendedor> tablaContactosSugeridos5;

    @FXML
    private TableView<Comentario> tableComentariosVendedor5;

    @FXML
    private TableView<Like> tableLikeVendedor5;

    @FXML
    private TableView<Vendedor> tableListaContactos5;

    @FXML
    private TableView<Producto> tableListaProductos5;

    @FXML
    private TableView<Producto> tableListaProductosGestion5;

    @FXML
    private TableView<Vendedor> tableListaSugeridosGestion5;

    @FXML
    private TextField tfGestionNombre5;

    @FXML
    private TextField tfGestionPrecio5;
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
        inicializarVendedorView5();
    }

    @FXML
    void loginVendedor(ActionEvent event) {
        String user = tfUser.getText();
        String pass = pfPass.getText();
        if (loginController.loginVendedor(user, pass, 4)) {
            userVendedor = user;
            tabChat.setDisable(false);
            tabGestion.setDisable(false);
            vendedorLogueado5 = modelFactoryController.getMarketplace().getListaVendedores().get(4);

        }
    }

    public void onComentarVendedor5Click(ActionEvent actionEvent) {
        Vendedor vendedorComentario = vendedorLogueado5;
        Producto productoComentario = productoMuroSeleccionado5;
        agregarComentario(productoComentario,vendedorComentario);
        tableComentariosVendedor5.refresh();
        tableListaProductos5.getSelectionModel().clearSelection();
    }

    public void onLikeVendedor5Click(ActionEvent actionEvent) {
        // Obtener el producto y el vendedor logueado correspondientes a la acción
        Producto producto = productoMuroSeleccionado5;
        if(vendedorLogueado5!=null){
            Vendedor vendedorInicializado = vendedorLogueado5;
            agregarLike(producto, vendedorInicializado);
            tableListaProductos5.refresh();
            tableListaProductos5.getSelectionModel().clearSelection();
        } else{
            MensajeUtil.mostrarMensaje("Error","Vendedor no logueado", " Debe loguearse como vendedor",Alert.AlertType.ERROR);
        }

    }

    public void onAgregar5Click(ActionEvent actionEvent) {
    }

    public void onAnadir5Click(ActionEvent actionEvent) throws VendedorNoExisteException {
        crearProducto();
    }

    public void onBuscar5Click(ActionEvent actionEvent) {
    }

    public void onModificar5Click(ActionEvent actionEvent) throws VendedorNoExisteException {
        modificarProducto();
    }

    private void modificarProducto() throws VendedorNoExisteException {
        String nombre = tfGestionNombre5.getText();
        double precio = Double.parseDouble(tfGestionPrecio5.getText());
        EstadoProducto estado = (EstadoProducto) cbEstado.getValue();
        String id = productoGestionSeleccionado5.getId();
        Producto producto = null;
        if (datosValidosProducto(nombre, precio, estado)) {
            Vendedor vendedor = modelFactoryController.retornarVendedor(userVendedor);
            producto = modelFactoryController.actualizarProducto(nombre, precio, estado, id, vendedor);
            if (producto != null) {
                int i1 = listaProductosMuroData5.indexOf(productoGestionSeleccionado5);
                int i5 = listaProductosGestionData5.indexOf(productoGestionSeleccionado5);
                listaProductosMuroData5.set(i1, producto);
                listaProductosGestionData5.set(i5, producto);
                tableListaProductosGestion5.refresh();
                tableListaProductos5.refresh();
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

    public void onEliminar5Click(ActionEvent actionEvent) throws VendedorNoExisteException {
        eliminarProducto();
    }

    public void onCancelar5Click(ActionEvent actionEvent) {
        tableListaProductosGestion5.getSelectionModel().clearSelection();
        limpiarCamposProducto();
    }

    private void crearProducto() throws VendedorNoExisteException {
        String nombre = tfGestionNombre5.getText();
        double precio = Double.parseDouble(tfGestionPrecio5.getText());
        EstadoProducto estado = (EstadoProducto) cbEstado.getValue();
        if (datosValidosProducto(nombre, precio, estado)) {
            Producto producto = null;
            Vendedor vendedor = modelFactoryController.retornarVendedor(userVendedor);
            producto = modelFactoryController.crearProducto(nombre, precio, estado, vendedor);
            if (producto != null) {
                listaProductosMuroData5.add(producto);
                listaProductosGestionData5.add(producto);
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
        if (productoGestionSeleccionado5 != null) {
            String id = productoGestionSeleccionado5.getId();
            if (MensajeUtil.mostrarMensajeConfirmacion("¿Estás seguro de elmininar el producto?")) {
                Vendedor vendedor = modelFactoryController.retornarVendedor(userVendedor);
                int index = modelFactoryController.getIndiceVendedor(vendedor);
                productoEliminado = modelFactoryController.eliminarProducto(id, index);
                if (productoEliminado) {
                    listaProductosGestionData5.remove(productoGestionSeleccionado5);
                    listaProductosMuroData5.remove(productoGestionSeleccionado5);
                    productoGestionSeleccionado5 = null;
                    tableListaProductosGestion5.getSelectionModel().clearSelection();
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
    public void agregarLike(Producto productoMuroSeleccionado5, Vendedor vendedorLogueado5) {
        try{
            Objects.requireNonNull(productoMuroSeleccionado5, "Es necesario que seleccione un producto");
            // Verificar si el vendedor logueado ya ha dado like
            boolean vendedorDioLike = false;
            for (Like like : productoMuroSeleccionado5.getListaLikes()) {
                if (like.getVendedor().getUsuario().equals(vendedorLogueado5.getUsuario())) {
                    vendedorDioLike = true;
                    break;
                }
            }

            if (vendedorDioLike) {
                MensajeUtil.mostrarMensaje("Información","El vendedor ya le dió Like", "El vendedor ya le dió like", Alert.AlertType.INFORMATION);
            } else {
                // Si el vendedor no ha dado like, se incrementa el contador de likes
                productoMuroSeleccionado5.incrementarLikes();
                // Agregar un nuevo like a la lista de likes del producto
                Like nuevoLike = new Like(productoMuroSeleccionado5, vendedorLogueado5, LocalDateTime.now());
                productoMuroSeleccionado5.getListaLikes().add(nuevoLike);
            }}catch (Exception e){
            MensajeUtil.mostrarMensaje("Alerta","Seleccione un producto",e.getMessage(),Alert.AlertType.ERROR);
        }
    }
    public void agregarComentario(Producto productoMuroSeleccionado5,Vendedor vendedorLogueado5){
        try{
            Objects.requireNonNull(productoMuroSeleccionado5,"Es necesario que seleccione un producto");
            String ComentarioTexto  =  tAComentar5.getText();
            if(!ComentarioTexto.isEmpty()){
                LocalDateTime fechaActual = LocalDateTime.now();

                Comentario comentario = new Comentario(productoMuroSeleccionado5,vendedorLogueado5,fechaActual,ComentarioTexto);

                productoMuroSeleccionado5.getListaComentarios().add(comentario);
                tAComentar5.clear();

            }
            else {
                MensajeUtil.mostrarMensaje("Error","Comentario vacio","El comentario no puede estar vacio", Alert.AlertType.ERROR);

            }}catch (Exception e){
            MensajeUtil.mostrarMensaje("Error", "Seleccione un producto,",e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void limpiarCamposProducto() {
        tfGestionNombre5.setText("");
        tfGestionPrecio5.setText("");
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

    private void inicializarVendedorView5() {
        inicializarProductosMuro5();
        inicializarComentariosMuro5();
        inicializarLikesMuro5();
        inicializarProductosGestion5();
        inicializarVendedoresSugeridosContactos5();
        inicializarListaSugeridosGestion5();
        inicializarContactosChat5();
    }

    private void inicializarProductosMuro5() {

        this.colListaProductosNombre5.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.colListaProductosPrecio5.setCellValueFactory(new PropertyValueFactory<>("precio"));
        this.colListaProductosEstado5.setCellValueFactory(new PropertyValueFactory<>("estado"));
        this.colListaProductosfecha5.setCellValueFactory(new PropertyValueFactory<>("fechaPublicacion"));
        this.colListaProductosVendedor5.setCellValueFactory(new PropertyValueFactory<>("idVendedor"));
        this.colListaLikes5.setCellValueFactory(new PropertyValueFactory<>("contadorLikes"));

        tableListaProductos5.getItems().clear();
        tableListaProductos5.setItems(getListaProductosMuroData5());
        colListaProductosfecha5.setSortType(TableColumn.SortType.ASCENDING);

        tableListaProductos5.getSortOrder().add(colListaProductosfecha5);
        tableListaProductos5.sort();

        tableListaProductos5.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {

            productoMuroSeleccionado5 = newSelection;
            mostrarInformacionComentariosMuro5(productoMuroSeleccionado5);
            mostrarInformacionLikeMuro5(productoMuroSeleccionado5);
        });
    }

    public ObservableList<Producto> getListaProductosMuroData5() {
        listaProductosMuroData5.addAll(modelFactoryController.obtenerProductos());
        return listaProductosMuroData5;
    }

    private void inicializarComentariosMuro5() {

        this.colComentariosFecha5.setCellValueFactory(new PropertyValueFactory<>("fechaComentario"));
        this.colComentariosProducto5.setCellValueFactory(new PropertyValueFactory<>("nombreProducto"));
        this.colComentariosEmisor5.setCellValueFactory(new PropertyValueFactory<>("nombreVendedor"));
        this.colComentariosComentario5.setCellValueFactory(new PropertyValueFactory<>("comentario"));

    }

    private void mostrarInformacionComentariosMuro5(Producto productoSeleccionado) {
        if (productoSeleccionado != null) {
            tableComentariosVendedor5.getItems().clear();
            tableComentariosVendedor5.setItems((getListaComentariosVendedorMuroData5(productoSeleccionado)));
        }
    }

    public ObservableList<Comentario> getListaComentariosVendedorMuroData5(Producto productoSeleccionado) {
        listaComentariosVendedorMuroData5.addAll(productoSeleccionado.getListaComentarios());
        return listaComentariosVendedorMuroData5;
    }

    private void inicializarLikesMuro5() {

        this.colLikesFecha5.setCellValueFactory(new PropertyValueFactory<>("fechaLike"));
        this.colLikesProducto5.setCellValueFactory(new PropertyValueFactory<>("nombreProducto"));
        this.colLikesEmisor5.setCellValueFactory(new PropertyValueFactory<>("nombreVendedor"));

    }

    private void mostrarInformacionLikeMuro5(Producto productoSeleccionado) {
        if (productoSeleccionado != null) {
            tableLikeVendedor5.getItems().clear();
            tableLikeVendedor5.setItems((getListaLikesVendedorMuroData5(productoSeleccionado)));
        }
    }

    public ObservableList<Like> getListaLikesVendedorMuroData5(Producto productoSeleccionado) {
        listaLikesVendedorMuroData5.addAll(productoSeleccionado.getListaLikes());
        return listaLikesVendedorMuroData5;
    }

    private void inicializarVendedoresSugeridosContactos5() {

        this.colContactosSugeridosNombre5.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.colContactosSugeridosApellido5.setCellValueFactory(new PropertyValueFactory<>("apellido"));

        tablaContactosSugeridos5.getItems().clear();
        tablaContactosSugeridos5.setItems((getListaContactosSugeridosContactosData5()));

        tablaContactosSugeridos5.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {

            contactoSugeridoContactosSeleccionado5 = newSelection;

        });
    }

    public ObservableList<Vendedor> getListaContactosSugeridosContactosData5() {
        listaContactosSugeridosContactosData5.addAll(modelFactoryController.obtenerContactosSugeridosVendedorN(4));
        return listaContactosSugeridosContactosData5;
    }

    private void inicializarProductosGestion5() {

        this.colGestionNombre5.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.colGestionPrecio5.setCellValueFactory(new PropertyValueFactory<>("precio"));
        this.colGestionEstado5.setCellValueFactory(new PropertyValueFactory<>("estado"));
        this.colGestionFecha5.setCellValueFactory(new PropertyValueFactory<>("fechaPublicacion"));
        this.colGestionVendedor5.setCellValueFactory(new PropertyValueFactory<>("idVendedor"));
        this.colGestionLikes5.setCellValueFactory(new PropertyValueFactory<>("contadorLikes"));
        cbEstado.setItems(FXCollections.observableArrayList(EstadoProducto.values()));

        tableListaProductosGestion5.getItems().clear();
        tableListaProductosGestion5.setItems(getListaProductosGestionData5());
        colGestionFecha5.setSortType(TableColumn.SortType.ASCENDING);
        tableListaProductosGestion5.getSortOrder().add(colGestionFecha5);
        tableListaProductosGestion5.sort();
        tableListaProductosGestion5.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {

            productoGestionSeleccionado5 = newSelection;
            mostrarInformacionProductosGestion5(productoGestionSeleccionado5);
        });
    }

    public ObservableList<Producto> getListaProductosGestionData5() {
        listaProductosGestionData5.addAll(modelFactoryController.obtenerProductosVendedorN(4));
        return listaProductosGestionData5;
    }

    private void mostrarInformacionProductosGestion5(Producto productoSeleccionado) {
        if (productoSeleccionado != null) {
            tfGestionNombre5.setText(productoSeleccionado.getNombre());
            tfGestionPrecio5.setText(String.valueOf((int) productoSeleccionado.getPrecio()));
            cbEstado.setValue(productoSeleccionado.getEstado());
        }
    }

    private void inicializarListaSugeridosGestion5() {

        this.colListaSugeridosNombre5.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.colListaSugeridosApellido5.setCellValueFactory(new PropertyValueFactory<>("apellido"));

        tableListaSugeridosGestion5.getItems().clear();
        tableListaSugeridosGestion5.setItems((getListaSugeridosGestionData5()));

        tableListaSugeridosGestion5.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {

            contactoSugeridoGestionSeleccionado5 = newSelection;

        });
    }

    public ObservableList<Vendedor> getListaSugeridosGestionData5() {
        listaContactosSugeridosGestionData5.addAll(modelFactoryController.obtenerListaContactosSugeridosVendedor1());
        return listaContactosSugeridosGestionData5;
    }

    private void inicializarContactosChat5() {

        this.colListaContactosNombre5.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.colListaContactosApellido5.setCellValueFactory(new PropertyValueFactory<>("apellido"));

        tableListaContactos5.getItems().clear();
        tableListaContactos5.setItems((getListaContactosChat5()));

        tableListaContactos5.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {

            contactoChatSeleccionado5 = newSelection;

        });
    }

    public ObservableList<Vendedor> getListaContactosChat5() {
        listaContactosChatData5.addAll(modelFactoryController.obtenerContactosSugeridosVendedorN(4));
        return listaContactosChatData5;
    }
    public void actualizarTablasContactosClientes5(){
        tablaContactosSugeridos5.getItems().clear();
        tablaContactosSugeridos5.setItems((getListaContactosSugeridosContactosData5()));
        tableListaSugeridosGestion5.getItems().clear();
        tableListaSugeridosGestion5.setItems((getListaSugeridosGestionData5()));
        tableListaContactos5.getItems().clear();
        tableListaContactos5.setItems((getListaContactosChat5()));
    }
//    public void actualizarTablasProductosVendedor5(){
//        tableListaProductos5.getItems().clear();
//        tableListaProductos5.setItems((getListaProductosMuroData5()));
//        tableListaProductosGestion5.getItems().clear();
//        tableListaProductosGestion5.setItems((getListaProductosGestionData5()));
//    }
}

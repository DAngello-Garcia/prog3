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

public class Vendedor2Controller {

    ModelFactoryController modelFactoryController;
    LoginController loginController;

    ObservableList<Producto> listaProductosMuroData2 = FXCollections.observableArrayList();
    Producto productoMuroSeleccionado2;

    Vendedor vendedorLogueado2;

    ObservableList<Comentario> listaComentariosVendedorMuroData2 = FXCollections.observableArrayList();

    ObservableList<Like> listaLikesVendedorMuroData2 = FXCollections.observableArrayList();

    ObservableList<Vendedor> listaContactosSugeridosContactosData2 = FXCollections.observableArrayList();
    Vendedor contactoSugeridoContactosSeleccionado2;

    ObservableList<Producto> listaProductosGestionData2 = FXCollections.observableArrayList();
    Producto productoGestionSeleccionado2;

    ObservableList<Vendedor> listaContactosSugeridosGestionData2 = FXCollections.observableArrayList();
    Vendedor contactoSugeridoGestionSeleccionado2;

    ObservableList<Vendedor> listaContactosChatData2 = FXCollections.observableArrayList();
    Vendedor contactoChatSeleccionado2;
    @FXML
    private TableColumn<Comentario, String> colComentariosComentario2;

    @FXML
    private TableColumn<Comentario, String> colComentariosEmisor2;

    @FXML
    private TableColumn<Comentario, LocalDateTime> colComentariosFecha2;

    @FXML
    private TableColumn<Comentario, String> colComentariosProducto2;

    @FXML
    private TableColumn<Vendedor, String> colContactosSugeridosApellido2;

    @FXML
    private TableColumn<Vendedor, String> colContactosSugeridosNombre2;

    @FXML
    private TableColumn<Producto, EstadoProducto> colGestionEstado2;

    @FXML
    private TableColumn<Producto, LocalDateTime> colGestionFecha2;
    //gestión vendedor 220 fecha 200 muro fecha 250 vendedor 228 fecha abajo 220

    @FXML
    private TableColumn<Producto, Integer> colGestionLikes2;

    @FXML
    private TableColumn<Producto, String> colGestionNombre2;

    @FXML
    private TableColumn<Producto, Double> colGestionPrecio2;

    @FXML
    private TableColumn<Producto, String> colGestionVendedor2;

    @FXML
    private TableColumn<Like, String> colLikesEmisor2;

    @FXML
    private TableColumn<Comentario, LocalDateTime> colLikesFecha2;

    @FXML
    private TableColumn<Like, String> colLikesProducto2;

    @FXML
    private TableColumn<Vendedor, String> colListaContactosApellido2;

    @FXML
    private TableColumn<Vendedor, String> colListaContactosNombre2;

    @FXML
    private TableColumn<Producto, Integer> colListaLikes2;

    @FXML
    private TableColumn<Producto, EstadoProducto> colListaProductosEstado2;

    @FXML
    private TableColumn<Producto, String> colListaProductosNombre2;

    @FXML
    private TableColumn<Producto, Double> colListaProductosPrecio2;

    @FXML
    private TableColumn<Producto, String> colListaProductosVendedor2;

    @FXML
    private TableColumn<Producto, LocalDateTime> colListaProductosfecha2;

    @FXML
    private TableColumn<Vendedor, String> colListaSugeridosApellido2;

    @FXML
    private TableColumn<Vendedor, String> colListaSugeridosNombre2;

    @FXML
    private TextArea tAComentar2;

    @FXML
    private TableView<Vendedor> tablaContactosSugeridos2;

    @FXML
    private TableView<Comentario> tableComentariosVendedor2;

    @FXML
    private TableView<Like> tableLikeVendedor2;

    @FXML
    private TableView<Vendedor> tableListaContactos2;

    @FXML
    private TableView<Producto> tableListaProductos2;

    @FXML
    private TableView<Producto> tableListaProductosGestion2;

    @FXML
    private TableView<Vendedor> tableListaSugeridosGestion2;

    @FXML
    private TextField tfGestionNombre2;

    @FXML
    private TextField tfGestionPrecio2;
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
        inicializarVendedorView2();
    }

    @FXML
    void loginVendedor(ActionEvent event) {
        String user = tfUser.getText();
        String pass = pfPass.getText();
        if (loginController.loginVendedor(user, pass, 1)) {
            userVendedor = user;
            tabChat.setDisable(false);
            tabGestion.setDisable(false);
            vendedorLogueado2 = modelFactoryController.getMarketplace().getListaVendedores().get(1);
        }
    }

    public void onComentarVendedor2Click(ActionEvent actionEvent) {
        Vendedor vendedorComentario = vendedorLogueado2;
        Producto productoComentario = productoMuroSeleccionado2;
        agregarComentario(productoComentario,vendedorComentario);
        tableComentariosVendedor2.refresh();
        tableListaProductos2.getSelectionModel().clearSelection();
    }

    public void onLikeVendedor2Click(ActionEvent actionEvent) {
        // Obtener el producto y el vendedor logueado correspondientes a la acción
        Producto producto = productoMuroSeleccionado2;
        if(vendedorLogueado2!=null){
            Vendedor vendedorInicializado = vendedorLogueado2;
            agregarLike(producto, vendedorInicializado);
            tableListaProductos2.refresh();
            tableListaProductos2.getSelectionModel().clearSelection();
        } else{
            MensajeUtil.mostrarMensaje("Error","Vendedor no logueado", " Debe loguearse como vendedor",Alert.AlertType.ERROR);
        }

    }

    public void onAgregar2Click(ActionEvent actionEvent) {
    }

    public void onAnadir2Click(ActionEvent actionEvent) throws VendedorNoExisteException {
        crearProducto();
    }

    public void onBuscar2Click(ActionEvent actionEvent) {
    }

    public void onModificar2Click(ActionEvent actionEvent) throws VendedorNoExisteException {
        modificarProducto();
    }

    private void modificarProducto() throws VendedorNoExisteException {
        String nombre = tfGestionNombre2.getText();
        double precio = Double.parseDouble(tfGestionPrecio2.getText());
        EstadoProducto estado = (EstadoProducto) cbEstado.getValue();
        String id = productoGestionSeleccionado2.getId();
        Producto producto = null;
        if (datosValidosProducto(nombre, precio, estado)) {
            Vendedor vendedor = modelFactoryController.retornarVendedor(userVendedor);
            producto = modelFactoryController.actualizarProducto(nombre, precio, estado, id, vendedor);
            if (producto != null) {
                int i1 = listaProductosMuroData2.indexOf(productoGestionSeleccionado2);
                int i2 = listaProductosGestionData2.indexOf(productoGestionSeleccionado2);
                listaProductosMuroData2.set(i1, producto);
                listaProductosGestionData2.set(i2, producto);
                tableListaProductosGestion2.refresh();
                tableListaProductos2.refresh();
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

    public void onEliminar2Click(ActionEvent actionEvent) throws VendedorNoExisteException {
        eliminarProducto();
    }

    public void onCancelar2Click(ActionEvent actionEvent) {
        tableListaProductosGestion2.getSelectionModel().clearSelection();
        limpiarCamposProducto();
    }

    private void crearProducto() throws VendedorNoExisteException {
        String nombre = tfGestionNombre2.getText();
        double precio = Double.parseDouble(tfGestionPrecio2.getText());
        EstadoProducto estado = (EstadoProducto) cbEstado.getValue();
        if (datosValidosProducto(nombre, precio, estado)) {
            Producto producto = null;
            Vendedor vendedor = modelFactoryController.retornarVendedor(userVendedor);
            producto = modelFactoryController.crearProducto(nombre, precio, estado, vendedor);
            if (producto != null) {
                listaProductosMuroData2.add(producto);
                listaProductosGestionData2.add(producto);
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
        if (productoGestionSeleccionado2 != null) {
            String id = productoGestionSeleccionado2.getId();
            if (MensajeUtil.mostrarMensajeConfirmacion("¿Estás seguro de elmininar el producto?")) {
                Vendedor vendedor = modelFactoryController.retornarVendedor(userVendedor);
                int index = modelFactoryController.getIndiceVendedor(vendedor);
                productoEliminado = modelFactoryController.eliminarProducto(id, index);
                if (productoEliminado) {
                    listaProductosGestionData2.remove(productoGestionSeleccionado2);
                    listaProductosMuroData2.remove(productoGestionSeleccionado2);
                    productoGestionSeleccionado2 = null;
                    tableListaProductosGestion2.getSelectionModel().clearSelection();
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
    public void agregarLike(Producto productoMuroSeleccionado2, Vendedor vendedorLogueado2) {
        try{
            Objects.requireNonNull(productoMuroSeleccionado2, "Es necesario que seleccione un producto");
            // Verificar si el vendedor logueado ya ha dado like
            boolean vendedorDioLike = false;
            for (Like like : productoMuroSeleccionado2.getListaLikes()) {
                if (like.getVendedor().getUsuario().equals(vendedorLogueado2.getUsuario())) {
                    vendedorDioLike = true;
                    break;
                }
            }

            if (vendedorDioLike) {
                MensajeUtil.mostrarMensaje("Información","El vendedor ya le dió Like", "El vendedor ya le dió like", Alert.AlertType.INFORMATION);
            } else {
                // Si el vendedor no ha dado like, se incrementa el contador de likes
                productoMuroSeleccionado2.incrementarLikes();
                // Agregar un nuevo like a la lista de likes del producto
                Like nuevoLike = new Like(productoMuroSeleccionado2, vendedorLogueado2, LocalDateTime.now());
                productoMuroSeleccionado2.getListaLikes().add(nuevoLike);
            }}catch (Exception e){
            MensajeUtil.mostrarMensaje("Alerta","Seleccione un producto",e.getMessage(),Alert.AlertType.ERROR);
        }
    }
    public void agregarComentario(Producto productoMuroSeleccionado2,Vendedor vendedorLogueado2){
        try{
            Objects.requireNonNull(productoMuroSeleccionado2,"Es necesario que seleccione un producto");
            String ComentarioTexto  =  tAComentar2.getText();
            if(!ComentarioTexto.isEmpty()){
                LocalDateTime fechaActual = LocalDateTime.now();

                Comentario comentario = new Comentario(productoMuroSeleccionado2,vendedorLogueado2,fechaActual,ComentarioTexto);

                productoMuroSeleccionado2.getListaComentarios().add(comentario);
                tAComentar2.clear();

            }
            else {
                MensajeUtil.mostrarMensaje("Error","Comentario vacio","El comentario no puede estar vacio", Alert.AlertType.ERROR);

            }}catch (Exception e){
            MensajeUtil.mostrarMensaje("Error", "Seleccione un producto,",e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void limpiarCamposProducto() {
        tfGestionNombre2.setText("");
        tfGestionPrecio2.setText("");
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

    private void inicializarVendedorView2() {
        inicializarProductosMuro2();
        inicializarComentariosMuro2();
        inicializarLikesMuro2();
        inicializarProductosGestion2();
        inicializarVendedoresSugeridosContactos2();
        inicializarListaSugeridosGestion2();
        inicializarContactosChat2();
    }

    private void inicializarProductosMuro2() {

        this.colListaProductosNombre2.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.colListaProductosPrecio2.setCellValueFactory(new PropertyValueFactory<>("precio"));
        this.colListaProductosEstado2.setCellValueFactory(new PropertyValueFactory<>("estado"));
        this.colListaProductosfecha2.setCellValueFactory(new PropertyValueFactory<>("fechaPublicacion"));
        this.colListaProductosVendedor2.setCellValueFactory(new PropertyValueFactory<>("idVendedor"));
        this.colListaLikes2.setCellValueFactory(new PropertyValueFactory<>("contadorLikes"));

        tableListaProductos2.getItems().clear();
        tableListaProductos2.setItems(getListaProductosMuroData2());
        colListaProductosfecha2.setSortType(TableColumn.SortType.ASCENDING);

        tableListaProductos2.getSortOrder().add(colListaProductosfecha2);
        tableListaProductos2.sort();

        tableListaProductos2.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {

            productoMuroSeleccionado2 = newSelection;
            mostrarInformacionComentariosMuro2(productoMuroSeleccionado2);
            mostrarInformacionLikeMuro2(productoMuroSeleccionado2);
        });
    }

    public ObservableList<Producto> getListaProductosMuroData2() {
        listaProductosMuroData2.addAll(modelFactoryController.obtenerProductos());
        return listaProductosMuroData2;
    }

    private void inicializarComentariosMuro2() {

        this.colComentariosFecha2.setCellValueFactory(new PropertyValueFactory<>("fechaComentario"));
        this.colComentariosProducto2.setCellValueFactory(new PropertyValueFactory<>("nombreProducto"));
        this.colComentariosEmisor2.setCellValueFactory(new PropertyValueFactory<>("nombreVendedor"));
        this.colComentariosComentario2.setCellValueFactory(new PropertyValueFactory<>("comentario"));

    }

    private void mostrarInformacionComentariosMuro2(Producto productoSeleccionado) {
        if (productoSeleccionado != null) {
            tableComentariosVendedor2.getItems().clear();
            tableComentariosVendedor2.setItems((getListaComentariosVendedorMuroData2(productoSeleccionado)));
        }
    }

    public ObservableList<Comentario> getListaComentariosVendedorMuroData2(Producto productoSeleccionado) {
        listaComentariosVendedorMuroData2.addAll(productoSeleccionado.getListaComentarios());
        return listaComentariosVendedorMuroData2;
    }

    private void inicializarLikesMuro2() {

        this.colLikesFecha2.setCellValueFactory(new PropertyValueFactory<>("fechaLike"));
        this.colLikesProducto2.setCellValueFactory(new PropertyValueFactory<>("nombreProducto"));
        this.colLikesEmisor2.setCellValueFactory(new PropertyValueFactory<>("nombreVendedor"));

    }

    private void mostrarInformacionLikeMuro2(Producto productoSeleccionado) {
        if (productoSeleccionado != null) {
            tableLikeVendedor2.getItems().clear();
            tableLikeVendedor2.setItems((getListaLikesVendedorMuroData2(productoSeleccionado)));
        }
    }

    public ObservableList<Like> getListaLikesVendedorMuroData2(Producto productoSeleccionado) {
        listaLikesVendedorMuroData2.addAll(productoSeleccionado.getListaLikes());
        return listaLikesVendedorMuroData2;
    }

    private void inicializarVendedoresSugeridosContactos2() {

        this.colContactosSugeridosNombre2.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.colContactosSugeridosApellido2.setCellValueFactory(new PropertyValueFactory<>("apellido"));

        tablaContactosSugeridos2.getItems().clear();
        tablaContactosSugeridos2.setItems((getListaContactosSugeridosContactosData2()));

        tablaContactosSugeridos2.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {

            contactoSugeridoContactosSeleccionado2 = newSelection;

        });
    }

    public ObservableList<Vendedor> getListaContactosSugeridosContactosData2() {
        listaContactosSugeridosContactosData2.addAll(modelFactoryController.obtenerContactosSugeridosVendedorN(1));
        return listaContactosSugeridosContactosData2;
    }

    private void inicializarProductosGestion2() {

        this.colGestionNombre2.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.colGestionPrecio2.setCellValueFactory(new PropertyValueFactory<>("precio"));
        this.colGestionEstado2.setCellValueFactory(new PropertyValueFactory<>("estado"));
        this.colGestionFecha2.setCellValueFactory(new PropertyValueFactory<>("fechaPublicacion"));
        this.colGestionVendedor2.setCellValueFactory(new PropertyValueFactory<>("idVendedor"));
        this.colGestionLikes2.setCellValueFactory(new PropertyValueFactory<>("contadorLikes"));
        cbEstado.setItems(FXCollections.observableArrayList(EstadoProducto.values()));

        tableListaProductosGestion2.getItems().clear();
        tableListaProductosGestion2.setItems(getListaProductosGestionData2());
        colGestionFecha2.setSortType(TableColumn.SortType.ASCENDING);
        tableListaProductosGestion2.getSortOrder().add(colGestionFecha2);
        tableListaProductosGestion2.sort();
        tableListaProductosGestion2.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {

            productoGestionSeleccionado2 = newSelection;
            mostrarInformacionProductosGestion2(productoGestionSeleccionado2);
        });
    }

    public ObservableList<Producto> getListaProductosGestionData2() {
        listaProductosGestionData2.addAll(modelFactoryController.obtenerProductosVendedorN(1));
        return listaProductosGestionData2;
    }

    private void mostrarInformacionProductosGestion2(Producto productoSeleccionado) {
        if (productoSeleccionado != null) {
            tfGestionNombre2.setText(productoSeleccionado.getNombre());
            tfGestionPrecio2.setText(String.valueOf((int) productoSeleccionado.getPrecio()));
            cbEstado.setValue(productoSeleccionado.getEstado());
        }
    }

    private void inicializarListaSugeridosGestion2() {

        this.colListaSugeridosNombre2.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.colListaSugeridosApellido2.setCellValueFactory(new PropertyValueFactory<>("apellido"));

        tableListaSugeridosGestion2.getItems().clear();
        tableListaSugeridosGestion2.setItems((getListaSugeridosGestionData2()));

        tableListaSugeridosGestion2.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {

            contactoSugeridoGestionSeleccionado2 = newSelection;

        });
    }

    public ObservableList<Vendedor> getListaSugeridosGestionData2() {
        listaContactosSugeridosGestionData2.addAll(modelFactoryController.obtenerListaContactosSugeridosVendedor1());
        return listaContactosSugeridosGestionData2;
    }

    private void inicializarContactosChat2() {

        this.colListaContactosNombre2.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.colListaContactosApellido2.setCellValueFactory(new PropertyValueFactory<>("apellido"));

        tableListaContactos2.getItems().clear();
        tableListaContactos2.setItems((getListaContactosChat2()));

        tableListaContactos2.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {

            contactoChatSeleccionado2 = newSelection;

        });
    }

    public ObservableList<Vendedor> getListaContactosChat2() {
        listaContactosChatData2.addAll(modelFactoryController.obtenerContactosSugeridosVendedorN(1));
        return listaContactosChatData2;
    }
    public void actualizarTablasContactosClientes2(){
        tablaContactosSugeridos2.getItems().clear();
        tablaContactosSugeridos2.setItems((getListaContactosSugeridosContactosData2()));
        tableListaSugeridosGestion2.getItems().clear();
        tableListaSugeridosGestion2.setItems((getListaSugeridosGestionData2()));
        tableListaContactos2.getItems().clear();
        tableListaContactos2.setItems((getListaContactosChat2()));
    }
//    public void actualizarTablasProductosVendedor2(){
//        tableListaProductos2.getItems().clear();
//        tableListaProductos2.setItems((getListaProductosMuroData2()));
//        tableListaProductosGestion2.getItems().clear();
//        tableListaProductosGestion2.setItems((getListaProductosGestionData2()));
//    }
}

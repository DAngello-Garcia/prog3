package co.edu.uniquindio.proyectofinal.controllers;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

import co.edu.uniquindio.proyectofinal.exceptions.*;
import co.edu.uniquindio.proyectofinal.services.IModelFactoryService;
import co.edu.uniquindio.proyectofinal.model.*;
import co.edu.uniquindio.proyectofinal.persistencia.Persistencia;

public class ModelFactoryController implements IModelFactoryService, Runnable {

    Marketplace marketplace;
    Thread hiloServicio1_guardarResourceXml;
    Thread hiloServicio2_RegistraLog;
    String mensaje;
    int nivel;
    String accion;
    BoundedSemaphore semaphore = new BoundedSemaphore(1);

    //------------------------------  Singleton ------------------------------------------------
    // Clase estatica oculta. Tan solo se instanciara el singleton una vez
    private static class SingletonHolder {
        // El constructor de Singleton puede ser llamado desde aqu� al ser protected
        private final static ModelFactoryController eINSTANCE = new ModelFactoryController();
    }

    // M�todo para obtener la instancia de nuestra clase
    public static ModelFactoryController getInstance() {
        return SingletonHolder.eINSTANCE;
    }

    public ModelFactoryController() {
        //1. inicializar datos y luego guardarlo en archivos
        iniciarSalvarDatosPrueba();

        //2. Cargar los datos de los archivos
        //cargarDatosDesdeArchivos();

        //3. Guardar y Cargar el recurso serializable binario
        //guardarResourceBinario();
        //cargarResourceBinario();

        //4. Guardar y Cargar el recurso serializable XML
        guardarResourceXML();
        //cargarResourceXML();

        //Siempre se debe verificar si la raiz del recurso es null
        if (marketplace == null) {
            inicializarDatos();
            //guardarResourceSerializable();
            //guardarResourceXML();
        }
        //guardarCopiaXML();
        registrarAccionesSistema("Inicio de sesión", 1, "inicioSesión");

    }

    private void iniciarSalvarDatosPrueba() {

        inicializarDatos();
        try {
            Persistencia.guardarVendedores(getMarketplace().getListaVendedores());
            Persistencia.guardarProductos(getMarketplace().getListaProductos());
            Persistencia.guardarUsuarios(getMarketplace().getListaUsuarios());
            Persistencia.cargarDatosArchivos(getMarketplace());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void cargarDatosDesdeArchivos() {
        marketplace = new Marketplace();

        Administrador admin = new Administrador("Admin");
        marketplace.getListaUsuarios().add(admin.getUsuario());
        try {
            Persistencia.cargarDatosArchivos(getMarketplace());
            for (int i = getMarketplace().getListaVendedores().size(); i < 10; i++) {
                marketplace.getListaVendedores().add(new Vendedor("-1"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void registrarAccionesSistema(String mensaje, int nivel, String accion) {
        this.mensaje = mensaje;
        this.nivel = nivel;
        this.accion = accion;
        hiloServicio2_RegistraLog = new Thread(this);
        hiloServicio2_RegistraLog.start();
    }

    public void cargarResourceBinario() {
        marketplace = (Marketplace) Persistencia.cargar().orElse(new Marketplace());
    }

    public void guardarResourceBinario() {
        Persistencia.guardar(marketplace);
    }

    public void cargarResourceXML() {
        marketplace = Persistencia.cargarRecursoXML();
    }

    public void guardarResourceXML() {
        Persistencia.guardarRecursoXML(marketplace);
    }

    public void guardarCopiaXML() {
        Persistencia.guardarCopiaXML(marketplace);
    }

    private void inicializarDatos() {

        marketplace = new Marketplace();

        Usuario usuarioVendedor = new Usuario("vendedor1@marketplace", "vendedor1", Rol.VENDEDOR, 0);
        Vendedor vendedor = new Vendedor("Juan", "Arias", "1234", "Armenia", usuarioVendedor);
        vendedor.getListaContactosVendedor().add(vendedor);
        marketplace.getListaUsuarios().add(usuarioVendedor);
        for(int i = 1; i<10; i++) {
            marketplace.getListaUsuarios().add(new Usuario(-1));
        }

        Producto producto = new Producto("Producto 1", EstadoProducto.PUBLICADO, 5000, vendedor);
        marketplace.getListaProductos().add(producto);

        vendedor.getListaProductos().add(producto);

        marketplace.getListaVendedores().add(vendedor);
        for(int i = 1; i<10; i++) {
            marketplace.getListaVendedores().add(new Vendedor("-1"));
        }

        Administrador admin = new Administrador("Admin");
        marketplace.getListaUsuarios().add(admin.getUsuario());
    }

    public Marketplace getMarketplace() {
        return marketplace;
    }

    public void setMarketplace(Marketplace marketplace) {
        this.marketplace = marketplace;
    }

    @Override
    public Vendedor crearVendedor(String nombre, String apellido, String cedula, String direccion, Usuario usuario,
                                  int index) {
        Vendedor vendedor = null;
        try {
            vendedor = getMarketplace().crearVendedor(nombre, apellido, cedula, direccion, usuario, index);
            Persistencia.guardarVendedores(getMarketplace().getListaVendedores());
        } catch (VendedorException e) {
            registrarAccionesSistema(e.getMessage(), 2, "Crear vendedor");
        } catch (IOException e) {
            registrarAccionesSistema(e.getMessage(), 2, "Crear vendedor");
        }
        return vendedor;
    }

    @Override
    public boolean actualizarVendedor(String cedulaActual, String nombre, String apellido, String cedula, String direccion, Usuario usuario) {
        boolean resultado = false;
        try {
            resultado = getMarketplace().actualizarVendedor(cedulaActual, nombre, apellido, cedula, direccion, usuario);
            if (resultado) {
                Persistencia.guardarVendedores(getMarketplace().getListaVendedores());
                registrarAccionesSistema("Vendedor actualizado", 1, "Actualizar vendedor");
            }
        } catch (ValorRequeridoException | VendedorException e) {
            registrarAccionesSistema(e.getMessage(), 2, "Actualizar vendedor");
        } catch (IOException e) {
            registrarAccionesSistema(e.getMessage(), 2, "Actualizar vendedor");
        }
        return resultado;
    }

    @Override
    public Producto actualizarProducto(String nombre, double precio, EstadoProducto estado, String id, Vendedor vendedor) {
        Producto producto = null;
        try {
            producto = getMarketplace().actualizarProducto(nombre, precio, estado, id, vendedor);
            if (producto != null) {
                Persistencia.guardarProductos(getMarketplace().getListaProductos());
                registrarAccionesSistema("Producto actualizado", 1, "Actualizar Producto");
            }
        } catch (IOException e) {
            registrarAccionesSistema(e.getMessage(), 2, "Actualizar Producto");
        } catch (ProductoNoExisteException e) {
            throw new RuntimeException(e);
        }
        return producto;
    }

    @Override
    public Boolean eliminarVendedor(String cedula, int index) {
        boolean flagEmpleadoExiste = false;
        try {
            flagEmpleadoExiste = getMarketplace().eliminarVendedor(cedula, index);
            if (flagEmpleadoExiste) {
                Persistencia.guardarVendedores(getMarketplace().getListaVendedores());
                registrarAccionesSistema("Vendedor eliminado", 1, "Eliminar vendedor");
            }
        } catch (VendedorException | ValorRequeridoException e) {
            registrarAccionesSistema(e.getMessage(), 2, "Eliminar vendedor");
        } catch (IOException e2) {
            registrarAccionesSistema(e2.getMessage(), 2, "Eliminar vendedor");
        }
        return flagEmpleadoExiste;
    }

    @Override
    public Boolean eliminarProducto(String id, int index) {
        boolean flagProducto = false;
        try {
            flagProducto = getMarketplace().eliminarProducto(id, index);
            if (flagProducto) {
                Persistencia.guardarProductos(getMarketplace().getListaProductos());
                registrarAccionesSistema("Producto eliminado", 1, "Eliminar producto");
            }
        } catch (IOException e2) {
            registrarAccionesSistema(e2.getMessage(), 2, "Eliminar producto");
        } catch (ProductoNoExisteException e) {
            registrarAccionesSistema(e.getMessage(), 2, "Eliminar producto");
        } catch (ValorRequeridoException e) {
            registrarAccionesSistema(e.getMessage(), 2, "Eliminar producto");
        }
        return flagProducto;
    }

    @Override
    public ArrayList<Vendedor> obtenerVendedores() {
        ArrayList<Vendedor> vend = new ArrayList<>();
        for (Vendedor v : marketplace.getListaVendedores()) {
            //if (!v.getDireccion().equals("-1"))
                vend.add(v);
        }
        return vend;
    }

    @Override
    public Vendedor retornarVendedor(String user) throws VendedorNoExisteException {
        return marketplace.retornarVendedor(user);
    }

    @Override
    public boolean loginAdmin(String user, String pass) {
        Usuario userAdmin = null;
        try {
            userAdmin = getMarketplace().buscarUsuario(user);
            if (userAdmin.getPassword().equals(pass)) {
                registrarAccionesSistema("Ingreso de usuario administrador", 1, "Login admin");
                return true;
            }
        } catch (UsuarioNoExtisteException e) {
            registrarAccionesSistema(e.getMessage(), 2, "Login admin");
        }
        return false;
    }

    @Override
    public ArrayList<Producto> obtenerProductos() {
        return marketplace.getListaProductos();
    }

    @Override
    public Producto crearProducto(String nombre, double precio, EstadoProducto estado, Vendedor vendedor) {
        Producto producto = null;
        try {
            producto = getMarketplace().crearProducto(nombre, precio, estado, vendedor);
            if (producto != null) {
                Persistencia.guardarProductos(getMarketplace().getListaProductos());
                registrarAccionesSistema("Producto creado por: " + vendedor.getNombre(), 1, "Creación de producto");
            }
        } catch (ProductoExisteException e) {
            registrarAccionesSistema(e.getMessage(), 2, "Creación de producto");
        } catch (IOException e) {
            registrarAccionesSistema(e.getMessage(), 2, "Creación de producto");
        }
        return producto;
    }

    public Optional<Vendedor> buscarVendedor(String cedula) {
        return marketplace.buscarVendedorPorCedula(cedula);
    }

    public ArrayList<Vendedor> obtenerContactosSugeridosVendedor1() {
        return marketplace.getListaContactosSugeridosVendedor1();
    }

    public ArrayList<Producto> obtenerProductosContactosSugeridosVendedor1() {
        return marketplace.getProductosContactosSugeridosVendedor1();
    }

    public ArrayList<Vendedor> obtenerListaContactosSugeridosVendedor1() {
        return marketplace.getListaVendedores();
    }

    public ArrayList<Producto> obtenerProductosVendedor1() {
        return marketplace.getProductosVendedor1();
    }

    public int getIndiceVendedor(Vendedor vendedor) {
        return this.marketplace.getListaVendedores().indexOf(vendedor);
    }

    @Override
    public boolean loginVendedor(String user, String pass, int i) {
        Usuario usuarioVendedor = null;
        try {
            usuarioVendedor = getMarketplace().buscarUsuario(user);
            if (usuarioVendedor.getPassword().equals(pass) && usuarioVendedor.getIndex()==i) {
                registrarAccionesSistema("Ingreso de usuario vendedor " + user, 1, "Login vendedor");
                return true;
            }
            Persistencia.guardaRegistroLog("Vendedor "+i+1+" intentó entrar a ventana "+usuarioVendedor.getIndex(), 2, "Login vendedor");
        } catch (UsuarioNoExtisteException e) {
            registrarAccionesSistema(e.getMessage(), 2, "Login vendedor");
        }
        return false;
    }

    //---------------------- xd---------------------- xd---------------------- xd
    public ArrayList<Vendedor> obtenerContactosSugeridosVendedorN(int indice) {
        return marketplace.getListaContactosSugeridosVendedorN(indice);
    }

    public ArrayList<Producto> obtenerProductosVendedorN(int indice) {
        return marketplace.getProductosVendedorN(indice);
    }

    @Override
    public void run() {

        Thread hiloActualThread =  Thread.currentThread();

        try {
            semaphore.ocupar();
        } catch (InterruptedException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        if(hiloActualThread == hiloServicio1_guardarResourceXml){
            Persistencia.guardarRecursoXML(marketplace);
            try {
                semaphore.liberar();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }


        if(hiloActualThread == hiloServicio2_RegistraLog){
            Persistencia.guardaRegistroLog(mensaje, nivel, accion);
            try {
                semaphore.liberar();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }
}

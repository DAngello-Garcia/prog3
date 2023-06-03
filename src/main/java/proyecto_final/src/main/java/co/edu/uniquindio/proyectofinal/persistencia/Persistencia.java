package co.edu.uniquindio.proyectofinal.persistencia;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Optional;

import co.edu.uniquindio.proyectofinal.model.*;


public class Persistencia {

	public static final String RUTA_ARCHIVO_VENDEDORES = "src/main/resources/persistencia/archivos/archivoVendedores" +
			".txt";
	public static final String RUTA_ARCHIVO_PRODUCTOS = "src/main/resources/persistencia/archivos/archivoProductos.txt";
	public static final String RUTA_ARCHIVO_USUARIOS = "src/main/resources/persistencia/archivos/archivoUsuarios.txt";
	public static final String RUTA_ARCHIVO_LOG = "src/main/resources/persistencia/log/MarketPlaceLog.txt";
	public static final String RUTA_ARCHIVO_OBJETOS = "src/main/resources/persistencia/archivos/archivoObjetos.txt";
	public static final String RUTA_ARCHIVO_MODELO_MARKETPLACE_BINARIO = "src/main/resources/persistencia/model.dat";
	public static final String RUTA_ARCHIVO_MODELO_MARQUETPLACE_XML = "src/main/resources/persistencia/model.xml";
	public static final String RUTA_ARCHIVO_COPIA_XML = "src/main/resources/persistencia/respaldo/";

	public static void cargarDatosArchivos(Marketplace marketplace) throws FileNotFoundException, IOException {

		ArrayList<Vendedor> vendedoresCargados = cargarVendedores();
		if(vendedoresCargados.size() > 0)
			marketplace.getListaVendedores().addAll(vendedoresCargados);

		ArrayList<Producto> productosCargados = cargarProductos();
		if(productosCargados.size() > 0)
			marketplace.getListaProductos().addAll(productosCargados);

		ArrayList<Usuario> usuariosCargados = cargarUsuarios();
		if(usuariosCargados.size() > 0)
			marketplace.getListaUsuarios().addAll(usuariosCargados);
		
	}

	public static void guardarVendedores(ArrayList<Vendedor> listaVenderores) throws IOException {
		String contenido = "";
		
		for(Vendedor vendedor: listaVenderores) {
			if(!vendedor.getDireccion().equals("-1")) {
			contenido+= vendedor.getNombre()+"@@"+vendedor.getApellido()+"@@"+vendedor.getCedula()+"@@"+vendedor.getDireccion()
		     +"@@"+vendedor.getUsuario().getUser()+"@@"+vendedor.getUsuario().getPassword()+"@@"+vendedor.getUsuario().getRol()+"@@"+vendedor.getUsuario().getIndex()+"\n";
			}
		}
		ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_VENDEDORES, contenido, false);
	}

	public static void guardarProductos(ArrayList<Producto> listaProductos) throws IOException {
		String contenido = "";
		
		for(Producto producto:listaProductos) {
			contenido+= producto.getNombre()+"@@"+producto.getId()+"@@"+producto.getCategoria()+"@@"+producto.getPrecio()+"@@"+producto.getEstado()+"@@"+producto.getFechaPublicacion()+ "\n";
		}
		ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_PRODUCTOS, contenido, false);
	}

	public static void guardarUsuarios(ArrayList<Usuario> listaUsuarios) throws IOException {
		String contenido = "";

		for(Usuario usuario:listaUsuarios) {
			if(usuario.getIndex() != -1)
				contenido+= usuario.getUser()+"@@"+usuario.getPassword()+"@@"+usuario.getRol()+"@@"+usuario.getIndex()+"\n";
		}
		ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_USUARIOS, contenido, false);
	}
//	----------------------LOADS------------------------

	public static ArrayList<Vendedor> cargarVendedores() throws FileNotFoundException, IOException {
		ArrayList<Vendedor> vendedores =new ArrayList<Vendedor>();
		
		ArrayList<String> contenido = ArchivoUtil.leerArchivo(RUTA_ARCHIVO_VENDEDORES);
		String linea="";
		
		for (int i = 0; i < contenido.size(); i++) {
			linea = contenido.get(i);
			Vendedor vendedor = new Vendedor();
			Usuario usuario = new Usuario(linea.split("@@")[4], linea.split("@@")[5],
					Rol.valueOf(linea.split("@@")[6]), Integer.parseInt(linea.split("@@")[7]));
			vendedor.setNombre(linea.split("@@")[0]);
			vendedor.setApellido(linea.split("@@")[1]);
			vendedor.setCedula(linea.split("@@")[2]);
			vendedor.setDireccion(linea.split("@@")[3]);
			vendedor.setUsuario(usuario);
			vendedores.add(vendedor);
		}
		return vendedores;
	}
	
	private static ArrayList<Producto> cargarProductos() throws IOException {
		
		ArrayList<Producto> productos =new ArrayList<Producto>();
		
		ArrayList<String> contenido = ArchivoUtil.leerArchivo(RUTA_ARCHIVO_PRODUCTOS);
		String linea="";
		
		for (int i = 0; i < contenido.size(); i++) {
			linea = contenido.get(i);
			Producto producto = new Producto();
			producto.setNombre(linea.split("@@")[0]);
			producto.setId(linea.split("@@")[1]);
			producto.setCategoria(CategoriaProducto.valueOf(linea.split("@@")[2]));
			producto.setPrecio(Double.parseDouble(linea.split("@@")[3]));
			producto.setEstado(EstadoProducto.valueOf(linea.split("@@")[4]));
			producto.setFechaPublicacion(LocalDateTime.parse(linea.split("@@")[5]));
			productos.add(producto);
		}
		return productos;
	}

	public static ArrayList<Usuario> cargarUsuarios() throws FileNotFoundException, IOException {
		ArrayList<Usuario> usuarios =new ArrayList<Usuario>();

		ArrayList<String> contenido = ArchivoUtil.leerArchivo(RUTA_ARCHIVO_USUARIOS);
		String linea="";

		for (int i = 0; i < contenido.size(); i++) {
			linea = contenido.get(i);

			Usuario usuario = new Usuario();
			usuario.setUser(linea.split("@@")[0]);
			usuario.setPassword(linea.split("@@")[1]);
			usuario.setRol(Rol.valueOf(linea.split("@@")[2]));
			usuario.setIndex(Integer.parseInt(linea.split("@@")[3]));

			usuarios.add(usuario);
		}
		return usuarios;
	}

	public static void guardaRegistroLog(String mensajeLog, int nivel, String accion) {
		ArchivoUtil.guardarRegistroLog(mensajeLog, nivel, accion, RUTA_ARCHIVO_LOG);
	}

//	----------------------SAVES------------------------
	public static void guardarObjetos(ArrayList<Vendedor> listaVendedores, String ruta) throws IOException  {
		String contenido = "";
		
		for(Vendedor vendedorAux:listaVendedores) {
			contenido+= vendedorAux.getNombre()+"@@"+vendedorAux.getApellido()+"@@"+vendedorAux.getCedula()+"@@"+vendedorAux.getDireccion()
					+"@@"+vendedorAux.getUsuario().getUser()+"@@"+vendedorAux.getUsuario().getRol()+"\n";
		}
		ArchivoUtil.guardarArchivo(ruta, contenido, true);
	}
	//------------------------------------SERIALIZACI�N  y XML
	public static void guardar(Serializable objeto){
		try(ObjectOutputStream archivo = new ObjectOutputStream(new FileOutputStream(RUTA_ARCHIVO_MODELO_MARKETPLACE_BINARIO))) {
			archivo.writeObject(objeto);
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	public static Optional cargar(){
		Object objeto = null;
		try(ObjectInputStream archivo = new ObjectInputStream(new FileInputStream(RUTA_ARCHIVO_MODELO_MARKETPLACE_BINARIO))) {
			objeto = archivo.readObject();
		}catch (Exception e){
			e.printStackTrace();
		}
		return Optional.ofNullable(objeto);
	}

	public static Marketplace cargarRecursoXML() {
		
		Marketplace marketplace = null;
		
		try {
			marketplace = (Marketplace) ArchivoUtil.cargarRecursoSerializadoXML(RUTA_ARCHIVO_MODELO_MARQUETPLACE_XML);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return marketplace;
	}

	public static void guardarRecursoXML(Marketplace marketplace) {

		try {
			ArchivoUtil.salvarRecursoSerializadoXML(RUTA_ARCHIVO_MODELO_MARQUETPLACE_XML, marketplace);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void guardarCopiaXML(Marketplace marketplace) {
		String nombreArchivo = "CopiaXML_";
		LocalDate hoy = LocalDate.now();
		LocalTime hora = LocalTime.now();
		int hh = hora.getHour();
		int mm = hora.getMinute();
		int ss = hora.getSecond();
		String fecha = hoy.toString();
		String aa = fecha.split("-")[0];
		String MM = fecha.split("-")[1];
		String dd = fecha.split("-")[2];
		String fechaFinal = dd+MM+aa+"_"+hh+"_"+mm+"_"+ss;

		try {
			ArchivoUtil.salvarRecursoSerializadoXML(RUTA_ARCHIVO_COPIA_XML+nombreArchivo+fechaFinal, marketplace);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

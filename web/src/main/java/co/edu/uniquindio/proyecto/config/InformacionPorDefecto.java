package co.edu.uniquindio.proyecto.config;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.servicios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class InformacionPorDefecto implements CommandLineRunner {

    @Autowired
    private AdministradorServicio administradorServicio;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private TrabajadorServicio trabajadorServicio;

    @Autowired
    private TipoServicio tipoServicio;

    @Autowired
    private ServicioServicio servicioServicio;

    @Autowired
    private ImagenServicio imagenServicio;

    @Autowired
    private CiudadServicio ciudadServicio;

    @Autowired
    private ProductoServicio productoServicio;


    @Override
    public void run(String... args) throws Exception{

        if (administradorServicio.listarAdministradores().isEmpty()){

            //ADMINISTRADORES//
            Administrador admin1= new Administrador("123","Danna Maya","311","d123","danna23197@gmail.com");
            administradorServicio.registrarAdministrador(admin1);

            //USUARIOS//
            Usuario u = new Usuario("12","Viviana","311","v123","marcelamayachavez@gmail.com");
            usuarioServicio.registrarUsuario(u);

            //CIUDADES//
            Ciudad ciudad3 = new Ciudad("Medellin");
            ciudadServicio.registrarCiudad(ciudad3);
            Ciudad ciudad4 = new Ciudad("Pereira");
            ciudadServicio.registrarCiudad(ciudad4);

            Ciudad ciudad5 = new Ciudad("Armenia");
            ciudadServicio.registrarCiudad(ciudad5);

            Ciudad ciudad6 = new Ciudad("Bogota");
            ciudadServicio.registrarCiudad(ciudad6);

            Ciudad ciudad8 = new Ciudad("Villavicencio");
            ciudadServicio.registrarCiudad(ciudad8);

            Ciudad ciudad9 = new Ciudad("Cali");
            ciudadServicio.registrarCiudad(ciudad9);

            //Tipo trabajador//
            TipoTrabajador t1 = new TipoTrabajador("Entrenador","Guia a los usuarios",admin1);
            admin1.getTipos().add(t1);
            tipoServicio.registrarTipo(t1);

            //Trabajador//
            Trabajador trabajador1 = new Trabajador("1","David","302","d123","davidtrejoscortes@gmail.com",admin1);
            admin1.getTrabajadores().add(trabajador1);
            trabajadorServicio.registrarTrabajador(trabajador1);

            //Servicios//
            Servicio servicio1 = new Servicio("ZUMBA","Zumba is a fitness discipline created focused on the one hand on maintaining a healthy body and on the other to develop, strengthen and give flexibility the body through dance movements",admin1,trabajador1);
            admin1.getServicios().add(servicio1);
            trabajador1.getServicios().add(servicio1);
            servicioServicio.registrarServicio(servicio1);

            Servicio servicio2 = new Servicio("SPINNING","A trip where the music makes the bike vibrate, it doesn't matter distance or calories",admin1,trabajador1);
            admin1.getServicios().add(servicio2);
            trabajador1.getServicios().add(servicio2);
            servicioServicio.registrarServicio(servicio2);

            trabajadorServicio.actualizarTrabajador(trabajador1,trabajador1.getEmail(),trabajador1.getPassword());
            administradorServicio.actualizarAdministrador(admin1,admin1.getEmail(),admin1.getPassword());

            //PRODUCTOS//
            Producto producto1 = new Producto("Supplement - Bcaa Polvo + Gym Powder Shaker","OPTIMIZES MUSCLE TISSUE GROWTH - Branched Chain Amino Acid (BCAA) supplement powder activates muscle protein synthesis pathways to enhance muscle growth.",293.099,admin1);
            producto1.setUnidades(3);
            admin1.getProductos().add(producto1);
            Imagen img1 = new Imagen("Bcca.png");
            img1.setProducto(producto1);
            producto1.getImagenes().add(img1);
            productoServicio.registrarProducto(producto1);
            administradorServicio.actualizarAdministrador(admin1,admin1.getEmail(),admin1.getPassword());
            imagenServicio.registrarImagen(img1);

            Producto producto2 = new Producto("Mydeal Products Sportgym Fitness Stretch Band","Portable Exercise Set 4X Latex Flat 48 x ***** Elastic Fitness Bands Door Anchor Carrying FUNDA PARA for Stretching Flexibility Training Rehabilitation",108.999,admin1);
            producto2.setUnidades(3);
            admin1.getProductos().add(producto2);
            Imagen img2 = new Imagen("mydeal.png");
            img2.setProducto(producto2);
            producto2.getImagenes().add(img2);
            productoServicio.registrarProducto(producto2);
            administradorServicio.actualizarAdministrador(admin1,admin1.getEmail(),admin1.getPassword());
            imagenServicio.registrarImagen(img2);

            Producto producto3 = new Producto("Gloves With Wrist Wrap For Gym Weights","They are designed in high quality Neoprene, a light, flexible and super resistant material.",72.883,admin1);
            producto3.setUnidades(3);
            admin1.getProductos().add(producto3);
            Imagen img3 = new Imagen("gozone.png");
            img3.setProducto(producto3);
            producto3.getImagenes().add(img3);
            productoServicio.registrarProducto(producto3);
            administradorServicio.actualizarAdministrador(admin1,admin1.getEmail(),admin1.getPassword());
            imagenServicio.registrarImagen(img3);


        }
    }
}

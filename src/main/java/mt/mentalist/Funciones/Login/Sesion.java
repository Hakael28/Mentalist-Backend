package mt.mentalist.Funciones.Login;

import mt.mentalist.modelo.Usuario;

public class Sesion {

    public static Usuario usuarioActual;

    public static Integer getIdUsuario(){
        return  (usuarioActual!=null) ? usuarioActual.getIdUsuario(): null;
    }

    public static String getNombreUsuario(){
        return (usuarioActual!= null) ? usuarioActual.getNombre():"Invitado";
    }

    public static void cerrarSesion(){
        usuarioActual= null;
    }
    public static boolean sesionActiva(){
        return usuarioActual != null;
    }
}

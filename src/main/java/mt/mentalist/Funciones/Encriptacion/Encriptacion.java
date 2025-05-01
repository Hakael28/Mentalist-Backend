package mt.mentalist.Funciones.Encriptacion;


import org.jasypt.util.text.BasicTextEncryptor;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


public class Encriptacion {

    //BCrypt para contraseñas
//    private static final BCryptPasswordEncoder encriptarContraseña = new BCryptPasswordEncoder();

    //Jasypt para encriptar datos sensibles
    private static final String CLAVE_JASYPT = System.getenv("JASYPT_ENCRYPTION_PASSWORD");
    private static final BasicTextEncryptor textEncryptor = new BasicTextEncryptor();

    static {
        if (CLAVE_JASYPT ==null || CLAVE_JASYPT.isBlank()){
            throw new IllegalStateException("Falta la variable de entorno JASYPT_ENCRYPTION_PASSWORD");
        }
        textEncryptor.setPassword(CLAVE_JASYPT);
        System.out.println("Clave cargada correctamente.");
    }

//    //Encriptacion para contraseñas
//    public static String encriptarContraseña(String plano){
//        return encriptarContraseña.encode(plano);
//    }

//    public static boolean verificarContraseña(String plano, String hashed){
//        return encriptarContraseña.matches(plano, hashed);
//    }

    //Jasypt para datos reversibles
    //Para encriptar datos String
    public static String encriptarTexto(String texto){
        return textEncryptor.encrypt(texto);
    }
    //Para desencriptar datos String
    public static String desencriptarTexto(String textoEncriptado){
        return textEncryptor.decrypt(textoEncriptado);
    }
}

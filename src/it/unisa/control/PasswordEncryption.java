package it.unisa.control;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

//classe fatta da chatgpt per l'encoding delle password
public class PasswordEncryption {

    public static String encryptPassword(String password) {
        try {
            // Ottieni un'istanza di MessageDigest con l'algoritmo SHA-256
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            // Applica l'algoritmo di hashing alla password
            byte[] hashedBytes = digest.digest(password.getBytes());

            // Converti i byte hash in una stringa Base64
            String hashedPassword = Base64.getEncoder().encodeToString(hashedBytes);

            return hashedPassword;
        } catch (NoSuchAlgorithmException e) {
            // Gestione dell'eccezione nel caso in cui l'algoritmo di hashing non sia disponibile
            e.printStackTrace();
            return null;
        }
    }
}
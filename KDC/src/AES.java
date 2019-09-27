import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class AES
{
    private static String ALGORITMO = "AES";

    private static byte[] cifra(String texto, String chave)
            throws IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, UnsupportedEncodingException, InvalidKeyException
    {
        Key key =
                new SecretKeySpec(chave.getBytes(StandardCharsets.UTF_8), ALGORITMO);
        Cipher cifrador = Cipher.getInstance(ALGORITMO);
        cifrador.init(Cipher.ENCRYPT_MODE, key);
        byte[] textoCifrado = cifrador.doFinal(texto.getBytes());
        return textoCifrado;
    }

    private static String decifra(byte[] texto, String chave)
            throws IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, UnsupportedEncodingException, InvalidKeyException
    {
        Key key =
                new SecretKeySpec(chave.getBytes(StandardCharsets.UTF_8), ALGORITMO);
        Cipher decifrador = Cipher.getInstance(ALGORITMO);
        decifrador.init(Cipher.DECRYPT_MODE, key);
        byte[] textoDecifrado = decifrador.doFinal(texto);
        return new String(textoDecifrado);
    }

    private static void Imprimir(String texto)
    {
        System.out.println(texto);
    }

    private static void Imprimir(byte[] texto)
    {
        System.out.println(new String(texto));
    }

    public static byte[] Encrypt(String chave,String texto)throws Exception {
        try
        {
            byte[] textoCifrado = AES.cifra(texto, chave);

            return textoCifrado;
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        return new byte[0];
    }

    public static String Decrypt(String chave, byte[] texto)throws Exception {
        try
        {
            String textoDecifrado = AES.decifra(texto, chave);

            return textoDecifrado;
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        return "Algo Deu Errado";
    }
}
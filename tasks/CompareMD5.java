package jr.testing;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.security.MessageDigest;

/* 
Целостность информации
*/

public class CompareMD5 {
    public static void main(String... args) throws Exception {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(new String("test string"));
        oos.flush();
        System.out.println(compareMD5(bos, "5a47d12a2e3f9fecf2d9ba1fd98152eb")); //true

    }

    public static boolean compareMD5(ByteArrayOutputStream byteArrayOutputStream, String md5) throws Exception {

       MessageDigest mdAlgorithm = MessageDigest.getInstance("MD5");
       mdAlgorithm.update(byteArrayOutputStream.toByteArray());
       byte[] digest =mdAlgorithm.digest();

       return bytesToHex(digest).equals(md5);
    }

    /**
     * Тут мы каждый байт преобразуем в строку, выполняя побитовое объединение этого байта с байтом 0xff.
     *
     * @param bytes
     * @return Байты в виде строки представленные в шестнадцатеричной системе счисления.
     */
    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b & 0xff));
        }
        return sb.toString();
    }
}

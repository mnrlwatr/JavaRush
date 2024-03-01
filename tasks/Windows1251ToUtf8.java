package jr.testing;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;

/* 
Смена кодировки
В метод main первым параметром приходит имя файла, тело которого в кодировке Windows-1251.
В метод main вторым параметром приходит имя файла, в который записывается содержимое первого файла в кодировке UTF-8.
*/
public class Windows1251ToUtf8 {
    static String win1251TestString = "РќР°СЂСѓС€РµРЅРёРµ РєРѕРґРёСЂРѕРІРєРё РєРѕРЅСЃРѕР»Рё?"; //only for your testing

    public static void main(String[] args) throws IOException {

        Charset utf8 = Charset.forName("UTF-8");
        Charset windows1251 = Charset.forName("Windows-1251");

        //Манипуляции с тестовой строкой
        byte[] buf = win1251TestString.getBytes(windows1251);
        String str = new String(buf, utf8);
        //Убеждаемся что перевод кодировки работает
        System.out.println(str);

        try(
            FileInputStream inputStream = new FileInputStream(args[0]);
            FileOutputStream outputStream = new FileOutputStream(args[1])
        )
        {
            //создаем buffer размером содержимого первого сайта
            byte[] buffer = new byte[inputStream.available()];
            // читаем содержимое в переменную buffer
            inputStream.read(buffer);
            // читаем сожержимое buffer в строку в кодировке UTF-8
            String s = new String(buffer, utf8);

            // Меняем кодировку
            // записываем строку в buffer в кодировке Windows1251
            buffer = s.getBytes(windows1251);
            // пишем в файл
            outputStream.write(buffer);
        }

    }
}

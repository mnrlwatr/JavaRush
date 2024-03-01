package jr.testing;


import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Генератор паролей состоящий из 8 символов.
 * Пароль будет содержать минимум одну латинскую букву нижнего и минимум одну букву верхнего регистра.
 * Пароль будет содержать минимум одну цифру.
*/

public class PasswordGenerator {
    public static void main(String[] args) {
        ByteArrayOutputStream password = getPassword();
        System.out.println(password.toString());
    }

    public static ByteArrayOutputStream getPassword() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Basket[] baskets = new Basket[3];

        baskets[0] = new Basket('0', '9');
        baskets[1] = new Basket('a', 'z');
        baskets[2] = new Basket('A', 'Z');

        for (int i = 0; i < 5; i++) {
            int index = (int) (Math.random() * 3);
            generateAndWriteChar(baos,baskets,index);
        }

        for (int i = 0; i < baskets.length; i++) {
            generateAndWriteChar(baos,baskets,i);
        }

        try {
            baos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return baos;
    }

    private static void generateAndWriteChar(ByteArrayOutputStream outputStream, Basket[] baskets, int index) {
        Basket basket = baskets[index];
        outputStream.write(basket.getRandom());
    }

    static class Basket {
        int begin;
        int quantity;

        private Basket(char begin, char end) {
            this.begin = begin;
            this.quantity = (end - begin) + 1;
        }

        /**
         *
         * @return Псевдослучайное число в диапазоне begin (включительно) и end (включительно)
         * (end - с.м параметр конструктора)
         */

        private int getRandom() {
            return (int) ((Math.random() * quantity) + begin);
        }
    }
}

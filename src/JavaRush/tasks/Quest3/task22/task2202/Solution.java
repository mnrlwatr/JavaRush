package JavaRush.tasks.Quest3.task22.task2202;

import java.util.ArrayList;
import java.util.List;

/*
Найти подстроку

Метод getPartOfString должен возвращать подстроку начиная с символа после 1-го пробела и до конца слова,
которое следует после 4-го пробела.

Пример:
«JavaRush — лучший сервис обучения Java.»

Результат:
«— лучший сервис обучения»

На некорректные данные бросить исключение TooShortStringException (сделать исключением).


Требования:
1. Класс TooShortStringException должен быть потомком класса RuntimeException.
2. Метод getPartOfString должен принимать строку в качестве параметра.
3. В случае, если строка, переданная в метод getPartOfString содержит менее 4 пробелов должно возникнуть исключение TooShortStringException.
4. Метод getPartOfString должен возвращать подстроку начиная с символа после 1-го пробела и до конца слова, которое следует после 4-го пробела.
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(getPartOfString("JavaRush - лучший сервис обучения Java."));
    }

    public static String getPartOfString(String string) {
        if (string == null || string.isEmpty()) {
            throw new TooShortStringException();
        }
        int firstSpace = string.indexOf(" ") + 1;
        char[] chars = string.toCharArray();
        int spacesCounter = 0;
        int indexOfLastSpace = 0;
        for (int i = 0; i < string.length(); i++) {
            if (chars[i] == ' ') {
                spacesCounter++;
                if (spacesCounter == 4) {
                    indexOfLastSpace = string.length();
                }
                else if (spacesCounter == 5) {
                    indexOfLastSpace = i;
                    break;
                }
            }
        }
        if (spacesCounter < 4) {
            throw new TooShortStringException();
        }
        return string.substring(firstSpace, indexOfLastSpace);
    }

    public static class TooShortStringException extends RuntimeException{
    }
}

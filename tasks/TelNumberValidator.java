package jr.testing;

/* 
Проверка номера телефона (Узбекистан)

Метод checkTelNumber должен проверять, является ли аргумент telNumber валидным номером телефона.

Критерии валидности:
1) если номер начинается с ‘+‘, то он содержит 12 цифр
2) если номер начинается с цифры или открывающей скобки, то он содержит 9 цифр
3) может содержать 0-2 знаков ‘—‘, которые не могут идти подряд
4) может содержать 1 пару скобок ‘(‘ и ‘)‘ , причем если она есть, то она расположена левее знаков ‘-‘
5) скобки внутри содержат четко 2 цифры
6) номер не содержит букв
7) номер заканчивается на цифру

Примеры:
+998971407550 - true
+998(97)1407550 - true
+99897140-75-50 - true
97140-7550 - true
+998)97(1407550 - false
+998(97)1-40-75-5-0 - false
97ххх7550 - false
97140755 - false
(9)71407550 - false
(971)407550 - false

*/
public class TelNumberValidator {
    public static boolean checkTelNumber(String telNumber) {
        if (telNumber == null)
            return false;

        if (telNumber.isEmpty())
            return false;

        int digits = telNumber.replaceAll("\\D", "").length();
        if ((telNumber.charAt(0) == '+' && digits == 12) || (telNumber.charAt(0) != '+' && digits == 9))
            return telNumber.matches("(\\+\\d+)?\\d*(\\(\\d{2}\\))?\\d+(-?\\d+){0,2}");

        return false;
    }

    public static void main(String[] args) {
        System.out.println(checkTelNumber("+998971407550"));
        System.out.println(checkTelNumber("+998(97)1407550"));
        System.out.println(checkTelNumber("+99897140-75-50"));
        System.out.println(checkTelNumber("97140-7550"));
        System.out.println(checkTelNumber("+998)97(1407550"));
        System.out.println(checkTelNumber("+998(97)1-40-75-5-0"));
        System.out.println(checkTelNumber("97ххх7550"));
        System.out.println(checkTelNumber("97140755"));
        System.out.println(checkTelNumber("(9)71407550"));
        System.out.println(checkTelNumber("(971)407550"));
    }
}

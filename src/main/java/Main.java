import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    //    считывание строки, введенной пользователем
    public static String readString() {
        String readString = null;

        System.out.println("Введите строку, которую необходимо распаковать: ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            readString = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return readString;
    }

    //    получение числа перед скобками
    public static int getCount(String text) {
        int count = 0;
        Pattern beforeBrackets = Pattern.compile("\\d+\\[");
        Matcher matcher = beforeBrackets.matcher(text);

        if (matcher.find()) {
            count = Integer.parseInt(matcher.group().substring(0, matcher.end() - 1));
        }

        return count;
    }

    //    распаковка строки из одной пары скобок, например: 2[xyz] = xyzxyz
    public static StringBuilder getFromBraces(String text) {
        StringBuilder resultString = new StringBuilder("");
        int count = getCount(text);
        String betweenBraces = null;

        Pattern pattern = Pattern.compile("\\[[a-zA-Z]+\\]");
        Matcher matcher = pattern.matcher(text);

        if (matcher.find()) {
            betweenBraces = matcher.group().substring(1, matcher.end() - 2);
        }

        while (count > 0) {
            resultString.append(betweenBraces);
            count--;
        }

        return resultString;
    }

    //    распаковка всей строки из любого количества скобок
    public static String getResultString(String text) {
        Pattern pattern = Pattern.compile("\\d+\\[[a-zA-Z]+\\]");
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            text = text.replace(matcher.group(), getFromBraces(matcher.group()));
            text = getResultString(text);
        }

        return text;
    }

    //    проверка на валидность
    public static boolean checkString(String enterString) {
        boolean checkString = true;
        String testString = getResultString(enterString);

        Pattern pattern = Pattern.compile("^[a-zA-Z]+$");
        Matcher matcher = pattern.matcher(testString);

        if (!matcher.find()) {
            System.out.println("Введённая строка не прошла проверку на валидность.");
            checkString = false;
        }

        return checkString;
    }

    public static void main(String[] args) {
        String enterString = readString();

        while (!checkString(enterString)) {
            enterString = readString();
        }

        System.out.println("Распакованная строка: \n" + getResultString(enterString));
    }
}

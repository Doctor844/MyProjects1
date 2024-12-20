import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String stringInput = new Scanner(System.in).nextLine();
        calc(stringInput);
        in.close();
    }

    public static void calc(String input)  {

        String string = "";

        for (byte i = 0; i < input.length(); i++) {

            if (!String.valueOf(input.charAt(i)).isBlank()) {
                string = string + (input.charAt(i));
            }

        }

        byte symbols = numOfSymbols(string);

        if (symbols < 1) {

            try {
                throw new Exception();
            } catch (Exception e) {
                System.out.println("throws Exception //т.к. строка не является математической операцией");
            }

        } else if (symbols > 1) {

            try {
                throw new Exception();
            } catch (Exception e) {
                System.out.println("throws Exception //т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
            }

        } else {

            String symbol = findSymbol(string);
            String[] array = string.split(symbol);
            byte a = Byte.parseByte(array[0]);
            byte b = Byte.parseByte(array[1]);

            if ((a < 11 & a > -1) && (b < 11 & b > -1)) {

                byte x = switch (symbol) {
                    case "/" -> (byte) (a / b);
                    case "\\*" -> (byte) (a * b); //обход исключения с регулярными выражениями "*" и "+". Фича
                    case "\\+" -> (byte) (a + b); //обход исключения с регулярными выражениями "*" и "+". Фича
                    case "-" -> (byte) (a - b);
                    default -> 0;
                };

                System.out.println(x);

            } else {

                try {
                    throw new Exception();
                } catch (Exception e) {
                    System.out.println("throws Exception");
                }
            }
        }
    }

    static byte numOfSymbols(String string) {
        byte x = 0;
        for (byte i = 0; i < string.length(); i++) {
            if (String.valueOf(string.charAt(i)).equals("/")) {
                x++;
            } else if (String.valueOf(string.charAt(i)).equals("*")) {
                x++;
            } else if (String.valueOf(string.charAt(i)).equals("+")) {
                x++;
            } else if (String.valueOf(string.charAt(i)).equals("-")) {
                x++;
            }
        }
        return x;
    }

    static String findSymbol(String string) {
        byte x = 0;
        String symbol = "";
        for (byte i = 0; i < string.length(); i++) {
            if (String.valueOf(string.charAt(i)).equals("/")) {
                symbol = "/";
            } else if (String.valueOf(string.charAt(i)).equals("*")) {
                symbol = "\\*";//обход исключения с регулярными выражениями "*" и "+". Фича
            } else if (String.valueOf(string.charAt(i)).equals("+")) {
                symbol = "\\+";//обход исключения с регулярными выражениями "*" и "+". Фича
            } else if (String.valueOf(string.charAt(i)).equals("-")) {
                symbol = "-";
            }
        }
        return symbol;
    }
}
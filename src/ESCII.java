public class ESCII {
    public static int bit(char character) {
        if (Character.isUpperCase(character)) {
            return character - 'A' + 1;
        } else if (Character.isLowerCase(character)) {
            return character - 'a' + 26;
        } else if (character == ' ') {
            return 53;
        } else if (character == 'é') {
            return 54;
        } else if (character == '!') {
            return 55;
        } else if (character == '\'') {
            return 56;
        } else if (character == '^') {
            return 57;
        } else if (character == '#') {
            return 58;
        } else if (character == '+') {
            return 59;
        } else if (character == '$') {
            return 60;
        } else if (character == '%') {
            return 61;
        } else if (character == '&') {
            return 62;
        } else if (character == '/') {
            return 63;
        } else if (character == '{') {
            return 64;
        } else if (character == '(') {
            return 65;
        } else if (character == '[') {
            return 66;
        } else if (character == ')') {
            return 67;
        } else if (character == ']') {
            return 68;
        } else if (character == '=') {
            return 69;
        } else if (character == '}') {
            return 70;
        } else if (character == '?') {
            return 71;
        } else if (character == '\\') {
            return 72;
        } else if (character == '_') {
            return 73;
        } else if (character == '-') {
            return 74;
        } else if (character == '|') {
            return 75;
        } else if (character == ',') {
            return 76;
        } else if (character == ';') {
            return 77;
        } else if (character == '.') {
            return 78;
        } else if (character == ':') {
            return 79;
        } else if (character == '<') {
            return 80;
        } else if (character == '>') {
            return 81;
        } else {
            return 0;
        }
    }

    public static void main(String[] args) {
        System.out.println("Karakter   | Kod");
        System.out.println("-----------|-----");

        for (char c = ' '; c <= '~'; c++) {
            int code = bit(c);
            if (code != 0) {
                System.out.printf("%-11s| %3d%n", "'" + c + "'", code);
            }
        }
    }
}

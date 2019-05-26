

public class Test {

    public static void main(String[] args) {
        String S = "aaabbbcccdddee";
        int K = 3;
        System.out.println(solution(S, K));
    }

    public static String solution(String S, int K) {
        // Removes all dashes currently in the string.
        StringBuilder string = new StringBuilder();
        for (int i = 0; i < S.length(); i++) {
            char character = S.charAt(i);
            if (character == '-') continue;
            string.append(character);
        }
        // Builds a new string with hyphens between characters,
        // separating string into substrings of size K.
        StringBuilder output = new StringBuilder(string.length());
        for (int i = 0; i < string.length(); i++) {
            char character = string.charAt(i);
            if (i != 0 && i % K == 0) {
                output.append('-');
                output.append(character);
            } else {
                output.append(character);
            }
        }

        char character = output.charAt(output.length() - 1);
        int count = 0;
        while (character != '-') {
            count++;
            character = output.charAt(output.length() - (count + 1));
        }

        String result = output.toString();
        while (count < 3) {
            result = moveHypensLeft(result);
            count++;
        }

        return result;
    }

    private static String moveHypensLeft(String string) {
        StringBuilder result = new StringBuilder(string.length());
        for (int i = 0; i < string.length(); i++) {
            if (i + 1 < string.length() && string.charAt(i + 1) == '-') {
                result.append('-');
                result.append(string.charAt(i));
                i++;
            } else {
                result.append(string.charAt(i));
            }
        }
        return result.toString();
    }


}

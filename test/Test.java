public class Test {

    @SuppressWarnings("Duplicates")
    public static boolean inRange(int num1, int num2, int range) {
        int max = num1 + range;
        int min = num1 - range;
        if (num2 >= min && num2 <= max)
            return true;
        return false;
    }

    private static void printHex(int value) {
        System.out.printf("%x\n", value);
    }

    private static void printBin(byte value) {
        System.out.println(String.format("%8s", Integer.toBinaryString(value & 0xFF)).replace(' ', '0'));
    }

    public static void colorChannels(int color) {

        int r = (color & 0xFF0000) >> 16;
        int g = (color & 0xFF00) >> 8;
        int b = color & 0xFF;

        r -= 50;
        g += 28;
        b -= 50;

        int result = r << 16 | g << 8 | b;

        printHex(result);
        printHex(0xdf0000 & 0xcc0000);
    }

    public static void main(String[] args) {
        byte a = (byte) 127;
        System.out.println(a);
        printBin(a);
        printBin((byte) ~a);
    }

}

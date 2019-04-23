public class Test {

    @SuppressWarnings("Duplicates")
    public static boolean inRange(int num1, int num2, int range) {
        int max = num1 + range;
        int min = num1 - range;
        if (num2 >= min && num2 <= max)
            return true;
        return false;
    }

    public static void main(String[] args) {
        System.out.println(inRange(10, 5, 6));
    }

}

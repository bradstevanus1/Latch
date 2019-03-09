
public class Test {

    public static void main(String[] args) {
        System.out.println(expOfBase2(128));
    }

    private static int expOfBase2(int num) {
        int count = 1;
        while (num != 2) {
            num /= 2;
            count++;
        }
        return count;
    }
}

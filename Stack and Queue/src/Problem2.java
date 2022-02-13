import java.util.Scanner;

public class Problem2 {
    public static String makeNewString(String string_old) {
        MyQueue<Character> queue = new MyQueue<>();
        int[] charCounter = new int[26];
        char lastNonRepeatingChar = '#';
        String string_new = "";

        for(int i=0; i<string_old.length(); i++) {
            char currChar = string_old.charAt(i);

            if(charCounter[currChar - 'a'] == 0) {
                queue.enQueue(currChar);
                charCounter[currChar - 'a'] = 1;
            } else {
                charCounter[currChar - 'a']++;
            }
            if(charCounter[currChar - 'a'] == 1 && lastNonRepeatingChar == '#') {
                lastNonRepeatingChar = currChar;
            } else if(currChar == lastNonRepeatingChar) {
                while (true) {
                    if(queue.isEmpty()) {
                        lastNonRepeatingChar = '#';
                        break;
                    }
                    lastNonRepeatingChar = queue.deQueue();
                    if(!(charCounter[lastNonRepeatingChar - 'a'] > 1)) {
                        break;
                    }
                }
            }
            string_new = string_new + lastNonRepeatingChar;
        }
        return string_new;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while(true) {
            String string_old = scanner.nextLine();
            if(string_old.equals("")) {
                continue;
            }
            System.out.println(makeNewString(string_old));
        }

    }
}

import java.util.Scanner;

public class BlackjackTutor {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Prompt player to select program mode
        while (true) {
            System.out.println("Select program mode: ");
            System.out.println("1: Analyze single hand");
            System.out.println("2: Random hand quiz");
            System.out.println("3: Quit");
            String selection = input.nextLine();

            if (selection.equals("3")) {
                break;
            }
            else if (selection.equals("1")) {
                Hand singleHand = new Hand();
            }
        }
        input.close();
    }

    public static void singleHand(Scanner input) {
        
        System.out.println("");
        System.out.println("Valid entries: A, 2, 3, 4, 5, 6, 7, 8, 9, 10, J, Q, K");
        System.out.print("Enter your first card: ");
    }
}

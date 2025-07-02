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
        }
        input.close();
    }

    public void singleHand(Scanner input) {
        
    }
}

import java.util.Scanner;

public class RuleBasedChatbot {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RuleBasedChatbot chatbot = new RuleBasedChatbot();
        
        System.out.println("Hello! I am a simple chatbot. Type 'exit' to end the chat.");
        
        while (true) {
            System.out.print("You: ");
            String userInput = scanner.nextLine();
            
            if (userInput.equalsIgnoreCase("exit")) {
                System.out.println("Chatbot: Goodbye!");
                break;
            }
            
            String response = chatbot.getResponse(userInput);
            System.out.println("Chatbot: " + response);
        }
        
        scanner.close();
    }

    private String getResponse(String input) {
        String response;

        // Simple rule-based responses
        if (input.contains("hello") || input.contains("hi")) {
            response = "Hello! How can I assist you today?";
        } else if (input.contains("your name")) {
            response = "I am a chatbot created to help you.";
        } else if (input.contains("how are you")) {
            response = "I'm just a program, but I'm doing well! How can I help you?";
        } else if (input.contains("bye") || input.contains("goodbye")) {
            response = "Goodbye! Have a great day!";
        } else {
            response = "Sorry, I don't understand that.";
        }

        return response;
    }
}

import java.util.Collection;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Enter a comma-separated list of integers:");
            String input = scanner.nextLine();

            NumberRangeSummarizer summarizer = new CommaDelimiter(); // Using the CommaDelimiter implementation
            
            try {
                // Split collect and summarize calls to separate concerns
                // and to handle potential parsing errors
                Collection<Integer> numbers = summarizer.collect(input);
                String summarizedOutput = summarizer.summarizeCollection(numbers);
                
                System.out.println("Summarized output: " + summarizedOutput);

            } catch (NumberFormatException e) {
                System.err.println("Error: Invalid input. Contains non-numeric values.");
                e.printStackTrace(); // error log
            }

        }
    }
}
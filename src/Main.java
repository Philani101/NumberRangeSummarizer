import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a comma-separated list of integers:");
        String input = scanner.nextLine();

        // Here you would create an instance of your implementation of NumberRangeSummarizer
        // and use it to process the input.
        // For example:
        // NumberRangeSummarizer summarizer = new YourImplementation();
        // Collection<Integer> numbers = summarizer.collect(input);
        // String result = summarizer.summarizeCollection(numbers);
        // System.out.println("Summarized output: " + result);

        scanner.close();
    }
}

import java.util.Collection;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a comma-separated list of integers:");
        String input = scanner.nextLine();

        // Here you would create an instance of your implementation of NumberRangeSummarizer
        // and use it to process the input.
        CommaDelimiter summarizer = new CommaDelimiter();
        System.out.println("Summarized output: " + summarizer.summarizeCollection(summarizer.collect(input)));

        scanner.close();
    }
}

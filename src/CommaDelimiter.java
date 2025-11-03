import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class CommaDelimiter implements NumberRangeSummarizer {
    
  
    // Collects and parses the input string into a collection of integers.
    @Override
    public Collection<Integer> collect(String input) {
        // Handle null or empty input
        if (input == null || input.trim().isEmpty()) {
            return Collections.emptyList();
        }

        // Stream API for processing the input
        return Arrays.stream(input.split(",")) // "1, 2, 3" -> ["1", " 2", " 3"]
                .map(String::trim)               // ["1", " 2", " 3"] -> ["1", "2", "3"]
                .filter(s -> !s.isEmpty())       // Handles cases like "1,,2"
                .map(Integer::parseInt)          // ["1", "2", "3"] -> [1, 2, 3]
                .collect(Collectors.toList());   // Collect results into a List
    }


    // Summarizes a collection of integers into a range string.
    @Override
    public String summarizeCollection(Collection<Integer> input) {
        if (input == null || input.isEmpty()) {
            return "";
        }

        // Using a TreeSet automatically handles sorting and de-duplication
        // in one step. This is much cleaner than sorting a List and
        // using a separate 'visited' Set.
        List<Integer> sortedUniqueNumbers = new ArrayList<>(new TreeSet<>(input));

        StringBuilder summarizedString = new StringBuilder();
        
        // Start the first range
        int rangeStart = sortedUniqueNumbers.get(0);
        int rangeEnd = sortedUniqueNumbers.get(0);

        // Iterate from the second number to compare with the previous
        for (int i = 1; i < sortedUniqueNumbers.size(); i++) {
            int currentNum = sortedUniqueNumbers.get(i);

            if (currentNum == rangeEnd + 1) {
                // Number is sequential, extend the current range
                rangeEnd = currentNum;
            } else {
                // Not sequential, finalize the previous range and add it
                appendRange(summarizedString, rangeStart, rangeEnd);
                
                // Start a new range
                rangeStart = currentNum;
                rangeEnd = currentNum;
            }
        }

        // After the loop, append the last pending range
        appendRange(summarizedString, rangeStart, rangeEnd);

        return summarizedString.toString();
    }

    // Helper method to append a formatted range
    private void appendRange(StringBuilder sb, int start, int end) {
        // Add a comma and space if this is not the first item
        if (sb.length() > 0) {
            sb.append(", ");
        }

        if (start == end) {
            // Single number, "1"
            sb.append(start);
        } else {
            // A range, "6-8"
            sb.append(start).append("-").append(end);
        }
    }
}
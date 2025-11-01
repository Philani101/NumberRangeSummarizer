import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;

public class CommaDelimiter implements NumberRangeSummarizer {
    @Override
    public Collection<Integer> collect(String input) {
        // Implementation here
        String[] strNumbers = input.split(",");
        Collection<Integer> numberCollection = new ArrayList<>(); // To hold the collected numbers

        for (String str : strNumbers) {
            numberCollection.add(Integer.parseInt(str.trim())); // Convert to integer and add to "numbers" array
        }
        return numberCollection;
    }
    public String summarizeCollection(Collection<Integer> input) {
        // Implementation here
        List<Integer> numbers = new ArrayList<>(input); // Convert collection to sorted list for easier processing
        Collections.sort(numbers);

        ArrayList<ArrayList<Integer>> twoDArrayList = new ArrayList<>(); // To hold the final ranges, it now holds ArrayLists of ranges
        int arrIndex = 0; // Index for the outer ArrayList
        Set<Integer> visited = new HashSet<>(); // To keep track of visited elements

        for (int i = 0; i < numbers.size(); i++) {
            int currentNum = numbers.get(i);
            // Skip already visited elements
            if (!visited.contains(numbers.get(i))) {
                twoDArrayList.add(new ArrayList<>()); // Create a new sublist for a new range
                twoDArrayList.get(arrIndex).add(numbers.get(i)); // Add the starting number of the range
                visited.add(numbers.get(i));
               for(int j = i+1; j < numbers.size(); j++){
                    int nextNum = numbers.get(j);
                    if ( nextNum == currentNum + 1 ){ // Check for sequential numbers
                        twoDArrayList.get(arrIndex).add(nextNum); // Add to range
                        visited.add(nextNum); // Mark as visited
                        currentNum = nextNum; // Update current number
                    }
                    else{
                        break; // Break if not sequential
                    }
                }
                arrIndex++; // Move to next sublist for new range
            }
            
        }
        // Build the summarized string
        StringBuilder summarizedString = new StringBuilder();
        for (int m = 0; m < arrIndex; m++) {
            ArrayList<Integer> rangeList = twoDArrayList.get(m);
            if (rangeList.size() == 1) {
                summarizedString.append(rangeList.get(0)); // Single number, no range
            } else {
                summarizedString.append(rangeList.get(0)).append("-").append(rangeList.get(rangeList.size() - 1)); // Format as range
            }
            if (m < arrIndex - 1) {
                summarizedString.append(", "); // Add comma separator except after the last range
            }
        }
        return summarizedString.toString();
    }
}

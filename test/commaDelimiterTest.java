import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

// My test class for the CommaDelimiter
class CommaDelimiterTest {

    // an instance of the class for each test
    private NumberRangeSummarizer summarizer;

    // Set up a fresh instance before each test runs
    @BeforeEach
    void setUp() {
        summarizer = new CommaDelimiter();
    }

    // --- Tests for the collect() method ---

    @Test
    @DisplayName("Should collect a simple list of numbers")
    void testCollectBasic() {
        String input = "1,2,3,4,5";
        Collection<Integer> expected = List.of(1, 2, 3, 4, 5);
        assertEquals(expected, summarizer.collect(input), "Should parse a simple comma-separated string");
    }

    @Test
    @DisplayName("Should handle spaces around numbers")
    void testCollectWithSpaces() {
        String input = " 1, 2 ,3 , 4,5 ";
        Collection<Integer> expected = List.of(1, 2, 3, 4, 5);
        assertEquals(expected, summarizer.collect(input), "Should trim spaces from numbers");
    }

    @Test
    @DisplayName("Should return an empty list for empty input")
    void testCollectEmpty() {
        String input = "";
        Collection<Integer> expected = Collections.emptyList();
        assertEquals(expected, summarizer.collect(input), "Empty string should result in an empty collection");
    }

    @Test
    @DisplayName("Should return an empty list for null input")
    void testCollectNull() {
        String input = null;
        Collection<Integer> expected = Collections.emptyList();
        assertEquals(expected, summarizer.collect(input), "Null input should result in an empty collection");
    }

    @Test
    @DisplayName("Should fail on non-numeric input")
    void testCollectInvalidInput() {
        String input = "1,2,a,4";
        // Make sure it throws a NumberFormatException if it tries to parse "a"
        assertThrows(NumberFormatException.class, () -> {
            summarizer.collect(input);
        }, "Should throw NumberFormatException for non-numeric input");
    }

    // --- Tests for the summarizeCollection() method ---

    @Test
    @DisplayName("Should pass the main example from the prompt")
    void testSummarizeExample() {
        Collection<Integer> input = List.of(1, 3, 6, 7, 8, 12, 13, 14, 15, 21, 22, 23, 24, 31);
        String expected = "1, 3, 6-8, 12-15, 21-24, 31";
        assertEquals(expected, summarizer.summarizeCollection(input));
    }

    @Test
    @DisplayName("Should handle a single number")
    void testSummarizeSingleNumber() {
        Collection<Integer> input = List.of(5);
        String expected = "5";
        assertEquals(expected, summarizer.summarizeCollection(input));
    }

    @Test
    @DisplayName("Should handle a single continuous range")
    void testSummarizeSingleRange() {
        Collection<Integer> input = List.of(1, 2, 3, 4);
        String expected = "1-4";
        assertEquals(expected, summarizer.summarizeCollection(input));
    }

    @Test
    @DisplayName("Should handle non-sequential numbers")
    void testSummarizeNoRanges() {
        Collection<Integer> input = List.of(1, 3, 5, 7);
        String expected = "1, 3, 5, 7";
        assertEquals(expected, summarizer.summarizeCollection(input));
    }

    @Test
    @DisplayName("Should handle duplicates and out-of-order lists")
    void testSummarizeWithDuplicatesAndUnsorted() {
        // The implementation should sort and de-duplicate (which mine does via TreeSet)
        Collection<Integer> input = List.of(5, 1, 3, 2, 1, 5, 7, 6);
        String expected = "1-3, 5-7";
        assertEquals(expected, summarizer.summarizeCollection(input));
    }

    @Test
    @DisplayName("Should return an empty string for an empty collection")
    void testSummarizeEmpty() {
        Collection<Integer> input = Collections.emptyList();
        String expected = "";
        assertEquals(expected, summarizer.summarizeCollection(input));
    }
    
    @Test
    @DisplayName("Should return an empty string for a null collection")
    void testSummarizeNull() {
        Collection<Integer> input = null;
        String expected = "";
        assertEquals(expected, summarizer.summarizeCollection(input));
    }

    @Test
    @DisplayName("Should handle negative numbers and ranges")
    void testSummarizeWithNegatives() {
        Collection<Integer> input = List.of(-5, -4, -3, 0, 1, 3, 5, 6);
        String expected = "-5--3, 0-1, 3, 5-6";
        assertEquals(expected, summarizer.summarizeCollection(input));
    }
}

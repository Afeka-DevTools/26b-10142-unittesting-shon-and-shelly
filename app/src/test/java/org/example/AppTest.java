package org.example;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class AppTest {

    @Test
    void addShouldReturnSumOfTwoIntegers() {
        assertEquals(5, App.add(2, 3));
        assertEquals(-1, App.add(2, -3));
        assertEquals(-5, App.add(-2, -3));
        assertEquals(1, App.add(-2, 3));
        assertEquals(0, App.add(0, 0));
        // Integer overflow: Integer.MAX_VALUE + 1 wraps to Integer.MIN_VALUE
        assertEquals(Integer.MIN_VALUE, App.add(Integer.MAX_VALUE, 1));
        assertEquals(Integer.MAX_VALUE, App.add(Integer.MIN_VALUE, -1));
    }

    @Test
    void isPrimeShouldIdentifyPrimesAndNonPrimes() {
        assertFalse(App.isPrime(-10));
        assertFalse(App.isPrime(-3));
        assertFalse(App.isPrime(-1));
        assertFalse(App.isPrime(0));
        assertFalse(App.isPrime(1));
        assertTrue(App.isPrime(2));
        assertTrue(App.isPrime(3));
        assertFalse(App.isPrime(4));
        assertTrue(App.isPrime(17));
        assertFalse(App.isPrime(100));
        // Edge cases: perfect squares
        assertFalse(App.isPrime(9));
        assertFalse(App.isPrime(25));
        assertFalse(App.isPrime(49));
        // Larger primes
        assertTrue(App.isPrime(7));
        assertTrue(App.isPrime(11));
        assertTrue(App.isPrime(7919));
        // Larger composites
        assertFalse(App.isPrime(1_000_000));
        assertFalse(App.isPrime(999_999));
    }

    @Test
    void reverseShouldReturnReversedString() {
        assertEquals("cba", App.reverse("abc"));
        assertEquals("", App.reverse(""));
        assertEquals("a", App.reverse("a"));
        // Test with spaces: characters and spaces are all reversed
        assertEquals("dlrow olleh", App.reverse("hello world"));
        assertEquals("c b a", App.reverse("a b c"));
        // Test with tabs: tabs are reversed along with characters
        assertEquals("dlrow\tolleh", App.reverse("hello\tworld"));
        // Test with leading/trailing spaces
        assertEquals("  cba  ", App.reverse("  abc  "));
        // Test with only spaces
        assertEquals("   ", App.reverse("   "));
        // Test with uppercase letters
        assertEquals("CBA", App.reverse("ABC"));
        assertEquals("olleH", App.reverse("Hello"));
        // Test with numbers
        assertEquals("321", App.reverse("123"));
        assertEquals("54321", App.reverse("12345"));
        // Test with special characters
        assertEquals("!@#", App.reverse("#@!"));
        assertEquals(")(", App.reverse("()"));
        // Test with mixed: uppercase, numbers, special characters
        assertEquals("!Z9yX", App.reverse("Xy9Z!"));
        assertEquals("321cBa", App.reverse("aBc123"));
    }

    @Test
    void factorialShouldComputeFactorialsAndRejectNegatives() {
        assertEquals(1, App.factorial(0));
        assertEquals(1, App.factorial(1));
        assertEquals(2, App.factorial(2));
        assertEquals(6, App.factorial(3));
        assertEquals(24, App.factorial(4));
        assertEquals(120, App.factorial(5));
        
        // Test larger numbers within safe range
        assertEquals(362880, App.factorial(9));
        assertEquals(479001600, App.factorial(12));
        
        // Test overflow cases
        ArithmeticException overflowException = assertThrows(ArithmeticException.class, () -> App.factorial(13));
        assertEquals("Factorial overflow", overflowException.getMessage());
        
        ArithmeticException overflowExceptionLarge = assertThrows(ArithmeticException.class, () -> App.factorial(Integer.MAX_VALUE));
        assertEquals("Factorial overflow", overflowExceptionLarge.getMessage());
        
        // Test negative numbers
        IllegalArgumentException exception1 = assertThrows(IllegalArgumentException.class, () -> App.factorial(-1));
        assertEquals("Negative number", exception1.getMessage());
        
        IllegalArgumentException exception2 = assertThrows(IllegalArgumentException.class, () -> App.factorial(-5));
        assertEquals("Negative number", exception2.getMessage());
        
        IllegalArgumentException exception3 = assertThrows(IllegalArgumentException.class, () -> App.factorial(-100));
        assertEquals("Negative number", exception3.getMessage());
        
        // Test Integer.MIN_VALUE (negative, will throw)
        IllegalArgumentException exceptionMin = assertThrows(IllegalArgumentException.class, () -> App.factorial(Integer.MIN_VALUE));
        assertEquals("Negative number", exceptionMin.getMessage());
    }

    @Test
    void isPalindromeShouldIgnoreCaseAndNonAlphanumericCharacters() {
        assertTrue(App.isPalindrome("racecar"));
        assertTrue(App.isPalindrome("RaceCar"));
        assertTrue(App.isPalindrome("A man, a plan, a canal: Panama"));
        assertFalse(App.isPalindrome("hello"));
    }

    @Test
    void fibonacciUpToShouldReturnSequenceUpToLimit() {
        assertEquals(List.of(0), App.fibonacciUpTo(0));
        assertEquals(List.of(0, 1, 1), App.fibonacciUpTo(1));
        assertEquals(List.of(0, 1, 1, 2, 3), App.fibonacciUpTo(3));
        assertEquals(List.of(0, 1, 1, 2, 3, 5, 8), App.fibonacciUpTo(8));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> App.fibonacciUpTo(-1));
        assertEquals("Negative input", exception.getMessage());
    }

    @Test
    void charFrequencyShouldCountCharactersCorrectly() {
        Map<Character, Integer> frequency = App.charFrequency("aabbc");
        assertEquals(3, frequency.size());
        assertEquals(2, frequency.get('a')); 
        assertEquals(2, frequency.get('b')); 
        assertEquals(1, frequency.get('c'));

        Map<Character, Integer> emptyFrequency = App.charFrequency("");
        assertTrue(emptyFrequency.isEmpty());
    }

    @Test
    void isAnagramShouldDetectAnagramsIgnoringWhitespaceAndCase() {
        assertTrue(App.isAnagram("listen", "silent"));
        assertTrue(App.isAnagram("The eyes", "They see"));
        assertFalse(App.isAnagram("apple", "pale"));
    }

    @Test
    void averageShouldComputeMeanAndRejectEmptyArray() {
        assertEquals(2.0, App.average(new int[]{1, 2, 3}), 0.0001);
        assertEquals(2.5, App.average(new int[]{2, 3}), 0.0001);
        assertEquals(-1.0, App.average(new int[]{-2, 0}), 0.0001);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> App.average(new int[]{}));
        assertEquals("Empty array", exception.getMessage());
    }

    @Test
    void filterEvensShouldReturnOnlyEvenNumbers() {
        assertEquals(List.of(2, 4, 6), App.filterEvens(List.of(1, 2, 3, 4, 5, 6)));
        assertEquals(List.of(), App.filterEvens(List.of(1, 3, 5)));
        assertEquals(List.of(0), App.filterEvens(List.of(0, -2, -3)));
    }

    @Test
    void mostCommonWordShouldReturnCorrectWord() {
        String text = "apple banana apple orange banana apple";
        assertEquals("apple", App.mostCommonWord(text));

        String textWithPunctuation = "Hello! Hello? world, world world.";
        assertEquals("world", App.mostCommonWord(textWithPunctuation));
    }
}

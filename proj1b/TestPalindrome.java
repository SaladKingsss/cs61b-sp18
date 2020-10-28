import org.junit.Test;

import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    } //Uncomment this class once you've created your Palindrome class.

    @Test
    public void testisPalindrome() {
        assertTrue(palindrome.isPalindrome("a"));
        assertTrue(palindrome.isPalindrome("racecar"));
        assertTrue(palindrome.isPalindrome(""));
        assertTrue(palindrome.isPalindrome("aabaa"));

        assertFalse(palindrome.isPalindrome("Racecar"));
        assertFalse(palindrome.isPalindrome("aaaaab"));
        assertFalse(palindrome.isPalindrome("aabcaa"));
        assertFalse(palindrome.isPalindrome("persiflage"));
    } //Uncomment this class once you've created your Palindrome class.

    @Test
    public void testisPalindromecc() {
        OffByOne offByOne = new OffByOne();
        assertTrue(palindrome.isPalindrome("", offByOne));
        assertTrue(palindrome.isPalindrome("flake", offByOne));
        assertTrue(palindrome.isPalindrome("cad", offByOne));
        assertTrue(palindrome.isPalindrome("acegijhfdb", offByOne));
        assertTrue(palindrome.isPalindrome("aaaaaabbbbbb", offByOne));
        assertTrue(palindrome.isPalindrome("aaaaa%&bbbbb", offByOne));

        assertFalse(palindrome.isPalindrome("abcdefg", offByOne));
        assertFalse(palindrome.isPalindrome("false", offByOne));
        assertFalse(palindrome.isPalindrome("true", offByOne));
    }

}

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
        Deque a = palindrome.wordToDeque("persiflage");
        boolean actual1 = false;
        boolean expected1 = palindrome.isPalindrome(a);
        assertEquals(expected1, actual1);

        Deque b = palindrome.wordToDeque("a");
        boolean actual2 = true;
        boolean expected2 = palindrome.isPalindrome(b);
        assertEquals(expected2, actual2);

        Deque c = palindrome.wordToDeque("racecar");
        boolean actual3 = true;
        boolean expected3 = palindrome.isPalindrome(c);
        assertEquals(expected3, actual3);

        Deque d = palindrome.wordToDeque("Racecar");
        boolean actual4 = false;
        boolean expected4 = palindrome.isPalindrome(d);
        assertEquals(expected4, actual4);

        Deque e = palindrome.wordToDeque("aaaaab");
        boolean actual5 = false;
        boolean expected5 = palindrome.isPalindrome(e);
        assertEquals(expected5, actual5);

        Deque f = palindrome.wordToDeque("");
        boolean actual6 = true;
        boolean expected6 = palindrome.isPalindrome(f);
        assertEquals(expected6, actual6);

        Deque g = palindrome.wordToDeque("aabcaa");
        boolean actual7 = false;
        boolean expected7 = palindrome.isPalindrome(g);
        assertEquals(expected7, actual7);

        Deque h = palindrome.wordToDeque("aabaa");
        boolean actual8 = true;
        boolean expected8 = palindrome.isPalindrome(h);
        assertEquals(expected8, actual8);
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

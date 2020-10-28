public class Palindrome {
    /*
    Given a String, wordToDeque should return a Deque
    where the characters appear in the same order as in the String.

    For example, if the word is “persiflage”,
    then the returned Deque should have ‘p’ at the front,
    followed by ‘e’, and so forth. Don’t implement wordToDeque yet!

    Tip: Search the web to see how to get the i-th character in a String.

    Tip: Inserting chars into a Deque<Character>
    is just like inserting ints into a LinkedListDeque<Integer>.
    */
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> res = new ArrayDeque<>();
        for (int i = 0; i < word.length(); i++) {
            res.addLast(word.charAt(i));
        }
        return res;
    }

    /*
    The isPalindrome method should return true
    if the given word is a palindrome, and false otherwise.

    Tip: Consider recursion. There’s a really beautiful solution that uses recursion.
    You’ll need to create a private helper method for this to work.

    Tip: Don’t use the get method of Deque.
    That will just make things unnecessarily complicated.
    */
    public boolean isPalindrome(Deque word) {
        if (word.size() <= 1) {
            return true;
        } else if (word.removeFirst() == word.removeLast()) {
            return isPalindrome(word);
        } else {
            return false;
        }
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        if (word.length() <= 1) {
            return true;
        } else if (cc.equalChars(word.charAt(0), word.charAt(word.length() - 1))) {
            word = word.substring(1, word.length() - 1);
            return isPalindrome(word, cc);
        } else {
            return false;
        }
    }

}

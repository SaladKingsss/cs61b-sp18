import org.junit.Test;

import static org.junit.Assert.*;

public class TestOffByOne {

    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();


    // Your tests go here.
    @Test
    public void testOffByOne() {
        boolean actual1 = true;
        boolean expected1 = offByOne.equalChars('a', 'b');
        assertEquals(expected1, actual1);

        boolean actual2 = true;
        boolean expected2 = offByOne.equalChars('&', '%');
        assertEquals(expected2, actual2);

        boolean actual3 = false;
        boolean expected3 = offByOne.equalChars('a', 'z');
        assertEquals(expected3, actual3);

        boolean actual4 = false;
        boolean expected4 = offByOne.equalChars('a', 'a');
        assertEquals(expected4, actual4);

        boolean actual5 = false;
        boolean expected5 = offByOne.equalChars('a', 'B');
        assertEquals(expected5, actual5);

        boolean actual6 = true;
        boolean expected6 = offByOne.equalChars('r', 'q');
        assertEquals(expected6, actual6);
    }

}

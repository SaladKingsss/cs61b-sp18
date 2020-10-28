import org.junit.Test;

import static org.junit.Assert.*;

public class TestOffByN {
    static CharacterComparator offByN = new OffByN(5);
    // Your tests go here.
    @Test
    public void testOffByN() {
        boolean actual1 = false;
        boolean expected1 = offByN.equalChars('a', 'b');
        assertEquals(expected1, actual1);

        boolean actual2 = false;
        boolean expected2 = offByN.equalChars('&', '%');
        assertEquals(expected2, actual2);

        boolean actual3 = true;
        boolean expected3 = offByN.equalChars('a', 'f');
        assertEquals(expected3, actual3);

        boolean actual4 = false;
        boolean expected4 = offByN.equalChars('a', 'a');
        assertEquals(expected4, actual4);

        boolean actual5 = false;
        boolean expected5 = offByN.equalChars('a', 'B');
        assertEquals(expected5, actual5);

        boolean actual6 = true;
        boolean expected6 = offByN.equalChars('r', 'w');
        assertEquals(expected6, actual6);
    } //Uncomment this class once you've created your CharacterComparator interface and OffByOne class.
}

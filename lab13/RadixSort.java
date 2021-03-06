import java.util.Arrays;

/**
 * Class for doing Radix sort
 *
 * @author Akhil Batra, Alexander Hwang
 */
public class RadixSort {
    /**
     * Does LSD radix sort on the passed in array with the following restrictions:
     * The array can only have ASCII Strings (sequence of 1 byte characters)
     * The sorting is stable and non-destructive
     * The Strings can be variable length (all Strings are not constrained to 1 length)
     *
     * @param asciis String[] that needs to be sorted
     * @return String[] the sorted array
     */
    public static String[] sort(String[] asciis) {
        // Implement LSD Sort
        int maxLen = 0;
        for (String s : asciis) {
            if (s.length() > maxLen) {
                maxLen = s.length();
            }
        }

        String[] res = Arrays.copyOf(asciis, asciis.length);
        for (int i = maxLen - 1; i >= 0; i--) {
            res = sortHelperLSD(res, i);
        }
        return res;
    }

    /**
     * LSD helper method that performs a destructive counting sort the array of
     * Strings based off characters at a specific index.
     *
     * @param asciis Input array of Strings
     * @param index  The position to sort the Strings on.
     */
    /*
    private static void sortHelperLSD(String[] asciis, int index, int maxLen) {
        // Optional LSD helper method for required LSD radix sort

        int R = 256;

        // gather all the counts for each value
        int[] counts = new int[R + 1];
        for (String i : asciis) {
            int pos = getNum(index, i, maxLen);
            counts[pos]++;
        }

        int[] starts = new int[R + 1];
        int pos = 0;
        for (int i = 0; i < starts.length; i += 1) {
            starts[i] = pos;
            pos += counts[i];
        }

        String[] sorted = new String[asciis.length];
        for (int i = 0; i < asciis.length; i += 1) {
            String item = asciis[i];
            int c = getNum(index, item, maxLen);
            int place = starts[c];
            sorted[place] = item;
            starts[c] += 1;
        }

        for (int i = 0; i < asciis.length; i += 1) {
            asciis[i] = sorted[i];
        }

    }
*/

    /*private static int getNum(int index, String item) {
        if (index < item.length() && index >= 0) {
            return item.charAt(index) + 1;
        } else {
            return 0;
        }
    }*/

    // 1 < 5 index=4
    private static int getNum(int index, String item, int maxLen) {
        int pos = index - (maxLen - item.length());
        if (pos >= 0) {
            return item.charAt(pos);
        } else {
            return 0;
        }
    }

    private static String[] sortHelperLSD(String[] asciis, int index) {
        int R = 256;

        int[] counts = new int[R];
        for (String s : asciis) {
            if (s.length() - 1 < index) {
                counts[0]++;
            } else {
                counts[s.charAt(index)]++;
            }
        }

        int[] starts = new int[R];
        int pos = 0;
        for (int i = 0; i < starts.length; i += 1) {
            starts[i] = pos;
            pos += counts[i];
        }

        String[] sorted = new String[asciis.length];
        for (String s : asciis) {
            if (s.length() - 1 < index) {
                int place = starts[0];
                sorted[place] = s;
                starts[0] += 1;
            } else {
                int place = starts[s.charAt(index)];
                sorted[place] = s;
                starts[s.charAt(index)] += 1;
            }
        }
        return sorted;
    }


    /**
     * MSD radix sort helper function that recursively calls itself to achieve the sorted array.
     * Destructive method that changes the passed in array, asciis.
     *
     * @param asciis String[] to be sorted
     * @param start  int for where to start sorting in this method (includes String at start)
     * @param end    int for where to end sorting in this method (does not include String at end)
     * @param index  the index of the character the method is currently sorting on
     **/
    private static void sortHelperMSD(String[] asciis, int start, int end, int index) {
        // Optional MSD helper method for optional MSD radix sort
        return;
    }

    public static void main(String[] args) {

        String[] asciis = new String[]{"<K", "_ù&8¤", "|mÀ©", "ªåe@B"};
        String[] res = RadixSort.sort(asciis);
        for (String s : res) {
            System.out.print(s + " ");
        }

        System.out.println();

        String[] asciis2 = new String[]{"  ", "      ", "    ", " "};
        String[] res2 = RadixSort.sort(asciis2);
        for (String s : res2) {
            System.out.print(s + ",");
        }

        System.out.println();

        String[] asciis3 = new String[]{"111", "26", "44", "67", "126", "90", "22", "3", "22", "115", "216", "97", "99", "116"};
        String[] res3 = RadixSort.sort(asciis3);
        for (String s : res3) {
            System.out.print(s + " ");
        }

    }
}

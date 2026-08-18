package StringMethods.Patterns;

// Alternative suitable class names: DescendingTriangle, DecreasingStarTriangle, RightAngleTriangleReverse
public class DecreasingTriangle {

    public static void main(String[] args) {

        int n = 5;

        // Outer loop starts from n and decreases to 1, so each row prints one less star than the previous row.
        for (int i = n; i >= 1; i--) {

            // Inner loop prints '*' exactly 'i' times for the current row, creating the decreasing pattern.
            for (int j = 1; j <= i; j++) {
                System.out.print("*");
            }

            // Moves to the next line after printing each row.
            System.out.println();

        }
    }
}
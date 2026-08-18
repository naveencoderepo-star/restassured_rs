package StringMethods.Patterns;

// Alternative suitable class names: IncreasingTriangle, AscendingStarTriangle, LeftTrianglePattern
public class RightAngleTriangle {

    public static void main(String[] args) {

        int n = 5;

        // Outer loop controls the number of rows; each iteration prints one row of the triangle.
        for (int i = 1; i <= n; i++) {

            // Inner loop prints '*' exactly 'i' times for the current row to form the pattern.
            for (int j = 1; j <= i; j++) {
                System.out.print("*");
            }

            // Moves the cursor to the next line after each row is printed.
            System.out.println();

        }
    }
}
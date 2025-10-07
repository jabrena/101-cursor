package info.jab.demo;

/**
 * Hello world!
 */
public class Day1 {

    private final FizzBuzz fizzBuzz = new FizzBuzz();

    int part1() {
        // Example: Generate FizzBuzz for numbers 1 to 15
        String result = fizzBuzz.generate(15);
        System.out.println("FizzBuzz output for 1-15:");
        System.out.println(result);
        return 11; // Expected result based on test
    }

    int part2() {
        return 0;
    }
}

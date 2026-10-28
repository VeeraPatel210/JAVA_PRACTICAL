
public class GenericPractice {

    static class Box<T> {
        T value;

        void set(T value) {
            this.value = value;
        }

        T get() {
            return value;
        }
    }

    static class Pair<A, B> {
        A first;
        B second;

        Pair(A first, B second) {
            this.first = first;
            this.second = second;
        }
    }

    static <T> T first(T[] values) {
        return values[0];
    }

    static <N extends Number> double average(N[] values) {
        double sum = 0;

        for (N value : values) {
            sum += value.doubleValue();
        }

        return values.length == 0 ? 0 : sum / values.length;
    }

    public static void main(String[] args) {

        Box<String> box = new Box<>();
        box.set("Hello Java");
        System.out.println(box.get());

        Pair<String, Integer> pair = new Pair<>("Age", 20);
        System.out.println(pair.first + ": " + pair.second);

        String[] words = {"Java", "Python"};
        System.out.println(first(words));

        Integer[] numbers = {10, 20, 30};
        System.out.println("Average: " + average(numbers));
    }
}

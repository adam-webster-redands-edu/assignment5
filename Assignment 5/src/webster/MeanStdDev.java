package webster;

public class MeanStdDev {
    public static double calculateMean(CustomLinkedList list) {
        int size = list.size();
        if (size == 0) {
            throw new ArithmeticException("Empty list, cannot calculate mean.");
        }

        double sum = 0;
        for (int i = 0; i < size; i++) {
            sum += list.get(i);
        }

        return sum / size;
    }

    public static double calculateStdDev(CustomLinkedList list) {
        int size = list.size();
        if (size == 0) {
            throw new ArithmeticException("Empty list, cannot calculate standard deviation.");
        }

        double mean = calculateMean(list);

        double sumOfSquares = 0;
        for (int i = 0; i < size; i++) {
            double diff = list.get(i) - mean;
            sumOfSquares += diff * diff;
        }

        return Math.sqrt(sumOfSquares / size);
    }
}

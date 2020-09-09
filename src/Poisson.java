abstract class Poisson {

    private static double getFactorial(double number) {
        double result = 1;
        if (number == 0) {
            result = 1;
        } else {
            for (int i = 1; i <= number; i++) {
                result = result * i;
            }
        }

        return CSVReader.roundThreePlaces(result);

    }

    public static double getPoissonPrediction(double numberOfGoals, double teamPrediction) {
        double result = ((Math.pow(teamPrediction, numberOfGoals) * Math.pow(Math.E, (-teamPrediction)) / getFactorial(numberOfGoals)));
        return CSVReader.roundThreePlaces(result);
    }

    public static void main(String[] args) {
    }
}
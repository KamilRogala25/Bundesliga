import java.util.ArrayList;
import java.util.List;

abstract class ProbabilityCalculator {

    public static List<Double> getTeamsPredictions(String homeTeam, String awayTeam, boolean isHome) {

        Team home = new Team(homeTeam);
        Team away = new Team(awayTeam);

        double homeTeamPrediction = CSVReader.roundThreePlaces(home.getHomeAttackStrength() * away.getAwayDefenceStrength() * CSVReader.getAverageLeagueHomeGoals());
        double awayTeamPrediction = CSVReader.roundThreePlaces(away.getAwayAttackStrength() * home.getHomeDefenceStrength() * CSVReader.getAverageLeagueAwayGoals());

        List<Double> homeTeamPredictions = new ArrayList<>();
        for (int i = 0; i <= 5; i++) {
            homeTeamPredictions.add(Poisson.getPoissonPrediction(i, homeTeamPrediction));
        }

        List<Double> awayTeamPredictions = new ArrayList<>();
        for (int i = 0; i <= 5; i++) {
            awayTeamPredictions.add(Poisson.getPoissonPrediction(i, awayTeamPrediction));
        }
        if (isHome == true) {
            return homeTeamPredictions;
        }
        if (isHome == false) {
            return awayTeamPredictions;
        }
        return null;

    }


    public static void calculateProbability(String homeTeam, String awayTeam) {

        List<Double> homeTeamPredictions = ProbabilityCalculator.getTeamsPredictions(homeTeam, awayTeam, true);
        List<Double> awayTeamPredictions = ProbabilityCalculator.getTeamsPredictions(homeTeam, awayTeam, false);

        double cacher = 0;
        int homeTeamExpectedScore = 0;
        int awayTeamExpectedScore = 0;
        double highestProbableScore = 0;

        int i = 0;
        if (i <= 5) {
            for (int j = 0; j <= 5; j++) {
                cacher = CSVReader.roundThreePlaces(homeTeamPredictions.get(i) * awayTeamPredictions.get(j));
                if (cacher >= highestProbableScore) {
                    highestProbableScore = cacher;
                    homeTeamExpectedScore = i;
                    awayTeamExpectedScore = j;
                }

                if (j == 5) {
                    j = -1;
                    i++;
                }
                if (i == 6) {
                    break;
                }
            }

        }

        System.out.println(homeTeam + " vs " + awayTeam + " - expected score is " + homeTeamExpectedScore + " : " + awayTeamExpectedScore);
    }


    public static void main(String[] args) {
        ProbabilityCalculator.calculateProbability("Padeborn", "Dortmund");
    }
}



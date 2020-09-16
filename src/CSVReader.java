
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

abstract class CSVReader {

    public static double roundThreePlaces(double toRound) {
        toRound = Math.round(toRound * 1000.0) / 1000.0;
        return toRound;

    }

    public static List<List<String>> readTeamsFromCSV( String csvFile) {

        List<List<String>> seasonData = new ArrayList<>();

        String csvSplitBy = ",";
        Scanner scanner = new Scanner(csvFile);

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(csvSplitBy);
                seasonData.add(Arrays.asList(values));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return seasonData;
    }

    public static List<List<String>> getSeasonData() {
        List<List<String>> seasonData = readTeamsFromCSV("D1.csv");

        return seasonData;

    }

    public static int getTotalHomeGoals() {
        List<List<String>> seasonData = getSeasonData();
        int homeGoals = 0;
        for (int i = 1; i < seasonData.size(); i++) {
            homeGoals += Integer.parseInt(seasonData.get(i).get(5));
        }


        return homeGoals;

    }

    public static int getTotalAwayGoals() {
        List<List<String>> seasonData = getSeasonData();
        int awaygoals = 0;
        for (int i = 1; i < seasonData.size(); i++) {
            awaygoals += Integer.parseInt(seasonData.get(i).get(6));
        }
        return awaygoals;
    }

    public static int getTotalHomeGames() {
        List<String> teams = getTeams();
        int totalHomeGames = 0;
        for (String team : teams
        ) {
            totalHomeGames += getNumberOfHomeMatches(team);
        }
        return totalHomeGames;
    }

    public static int getTotalAwayGames() {
        List<String> teams = getTeams();
        int totalAwayGames = 0;
        for (String team : teams
        ) {
            totalAwayGames += getNumberOfAwayMatches(team);
        }
        return totalAwayGames;
    }

    //     equals goals conceded away
    public static double getAverageLeagueHomeGoals() {
        double averageLeagueHomeGoals = getTotalHomeGoals() / getTotalHomeGames();
        averageLeagueHomeGoals = CSVReader.roundThreePlaces(averageLeagueHomeGoals);
        return averageLeagueHomeGoals;
    }

    //     equals goals conceded home
    public static double getAverageLeagueAwayGoals() {
        double averageLeagueAwayGoals = getTotalAwayGoals() / getTotalAwayGames();
        averageLeagueAwayGoals = CSVReader.roundThreePlaces(averageLeagueAwayGoals);
        return averageLeagueAwayGoals;
    }


    public static List<String> getTeams() {
        List<List<String>> seasonData = getSeasonData();
        List<String> results = new ArrayList<>();
        for (int i = 1; i < seasonData.size(); i++) {
            if (!results.contains(seasonData.get(i).get(3))) {
                results.add(seasonData.get(i).get(3));
            }


        }
        return results;
    }

    public static boolean isOnTeamList(String team) {
        List<String> teams = getTeams();
        boolean validator;
        if (teams.contains(team)) {
            validator = true;
        } else validator = false;
        return validator;

    }

    public static int getHomeGoals(String team) {
        List<List<String>> seasonData = getSeasonData();
        int homeGoals = 0;
        boolean validator = isOnTeamList(team);
        if (validator) {
            for (int i = 1; i < seasonData.size(); i++) {
                if (seasonData.get(i).get(3).contains(team)) {
                    homeGoals += Integer.parseInt(seasonData.get(i).get(5));
                }
            }
        } else homeGoals = -1;
        return homeGoals;

    }

    public static int getAwayGoals(String team) {
        List<List<String>> seasonData = getSeasonData();
        int awaygoals = 0;
        boolean validator = isOnTeamList(team);
        if (validator) {
            for (int i = 1; i < seasonData.size(); i++) {
                if (seasonData.get(i).get(4).contains(team)) {
                    awaygoals += Integer.parseInt(seasonData.get(i).get(6));
                }
            }
        } else awaygoals = -1;
        return awaygoals;

    }

    public static int getHomeGoalsConceded(String team) {
        List<List<String>> seasonData = getSeasonData();
        int homeGoalsConceded = 0;
        boolean validator = isOnTeamList(team);
        if (validator) {
            for (int i = 1; i < seasonData.size(); i++) {
                if (seasonData.get(i).get(3).contains(team)) {
                    homeGoalsConceded += Integer.parseInt(seasonData.get(i).get(6));
                }
            }
        } else homeGoalsConceded = -1;
        return homeGoalsConceded;
    }

    public static int getAwayGoalsConceded(String team) {
        List<List<String>> seasonData = getSeasonData();
        int homeGoalsConceded = 0;
        boolean validator = isOnTeamList(team);
        if (validator) {
            for (int i = 1; i < seasonData.size(); i++) {
                if (seasonData.get(i).get(4).contains(team)) {
                    homeGoalsConceded += Integer.parseInt(seasonData.get(i).get(5));
                }
            }
        } else homeGoalsConceded = -1;
        return homeGoalsConceded;
    }


    public static int getNumberOfHomeMatches(String team) {
        List<List<String>> seasonData = getSeasonData();
        int homeMatchesCounter = 0;
        boolean validator = isOnTeamList(team);
        if (validator) {
            for (int i = 1; i < seasonData.size(); i++) {
                if (seasonData.get(i).get(3).contains(team)) {
                    homeMatchesCounter++;
                }
            }
        } else homeMatchesCounter = -1;
        return homeMatchesCounter;
    }

    public static int getNumberOfAwayMatches(String team) {
        List<List<String>> seasonData = getSeasonData();
        int homeMatchesCounter = 0;
        boolean validator = isOnTeamList(team);
        if (validator) {
            for (int i = 1; i < seasonData.size(); i++) {
                if (seasonData.get(i).get(4).contains(team)) {
                    homeMatchesCounter++;
                }
            }
        } else homeMatchesCounter = -1;
        return homeMatchesCounter;
    }

    public static double getAverageHomeGoals(String team) {
        int homeGoals = getHomeGoals(team);
        int homeGames = getNumberOfHomeMatches(team);
        double averageHomeGoals;
        boolean validator = isOnTeamList(team);
        if (validator) {
            averageHomeGoals = (double) homeGoals / (double) homeGames;
        } else averageHomeGoals = -1;
        averageHomeGoals = CSVReader.roundThreePlaces(averageHomeGoals);
        return averageHomeGoals;
    }

    public static double getAverageAwayGoals(String team) {
        int awayGoals = getAwayGoals(team);
        int awayGames = getNumberOfAwayMatches(team);
        double averageAwayGoals;
        boolean validator = isOnTeamList(team);

        if (validator) {
            averageAwayGoals = (double) awayGoals / (double) awayGames;
        } else averageAwayGoals = -1;
        averageAwayGoals = CSVReader.roundThreePlaces(averageAwayGoals);
        return averageAwayGoals;
    }

    public static double getHomeAttackStrength(String team) {
        double teamHomeStrength = (double) CSVReader.getHomeGoals(team) / (double) CSVReader.getNumberOfHomeMatches(team);
        double secondStrength = teamHomeStrength / CSVReader.getAverageLeagueHomeGoals();
        return CSVReader.roundThreePlaces(secondStrength);


    }

    public static double getAwayAttackStrength(String team) {
        double teamAwayStrength = (double) CSVReader.getAwayGoals(team) / (double) CSVReader.getNumberOfAwayMatches(team);
        double secondStrength = teamAwayStrength / CSVReader.getAverageLeagueAwayGoals();
        return CSVReader.roundThreePlaces(secondStrength);

    }

    public static double getHomeDefenceStrength(String team) {
        double teamHomeStrength = (double) CSVReader.getHomeGoalsConceded(team) / (double) CSVReader.getNumberOfHomeMatches(team);
        double secondStrength = teamHomeStrength / CSVReader.getAverageLeagueAwayGoals();
        return CSVReader.roundThreePlaces(secondStrength);
    }

    public static double getAwayDefenceStrength(String team) {
        double teamAwayStrength = (double) CSVReader.getAwayGoalsConceded(team) / (double) CSVReader.getNumberOfAwayMatches(team);
        double secondStrength = teamAwayStrength / CSVReader.getAverageLeagueHomeGoals();
        return CSVReader.roundThreePlaces(secondStrength);
    }


    public static void main(String[] args) {
        int homegoals = getHomeGoals("Bayern Munich");
        int awaygoals = getAwayGoals("Bayern Munich");
        int homeMatches = getNumberOfHomeMatches("Bayern Munich");
        int awayMatches = getNumberOfAwayMatches("Bayern Munich");
        int concededHome = getHomeGoalsConceded("Bayern Munich");
        int concededAway = getAwayGoalsConceded("Bayern Munich");
        System.out.println(getAwayAttackStrength("Bayern Munich"));

        System.out.println(CSVReader.getAverageLeagueHomeGoals());


    }
}



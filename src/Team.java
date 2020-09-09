public class Team {


    private String name;
    private double homeAttackStrength;
    private double awayAttackStrength;
    private double homeDefenceStrength;
    private double awayDefenceStrength;


    public Team(String teamName) {
        this.name = teamName;
    }


    public double getHomeAttackStrength() {
        homeAttackStrength = CSVReader.getHomeAttackStrength(name);
        return homeAttackStrength;
    }


    public double getAwayAttackStrength() {
        awayAttackStrength = CSVReader.getAwayAttackStrength(name);
        return awayAttackStrength;
    }


    public double getHomeDefenceStrength() {
        homeDefenceStrength = CSVReader.getHomeDefenceStrength(name);
        return homeDefenceStrength;
    }


    public double getAwayDefenceStrength() {
        awayDefenceStrength = CSVReader.getAwayDefenceStrength(name);
        return awayDefenceStrength;
    }


    public String getTeamName() {
        return name;
    }

    public void setTeamName(String teamName) {
        this.name = teamName;
    }

    @Override
    public String toString() {
        return "Team{" +
                "name='" + name + '\'' +
                '}';
    }
}

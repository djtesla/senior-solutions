package euro;

public class Game {

    private String firstCountry;
    private String secondCountry;
    private int firstCountryScore;
    private int secondCountryScore;

    public Game(String firstCountry, String secondCountry, int firstCountryScore, int secondCountryScore) {
        this.firstCountry = firstCountry;
        this.secondCountry = secondCountry;
        this.firstCountryScore = firstCountryScore;
        this.secondCountryScore = secondCountryScore;
    }

    public String getFirstCountry() {
        return firstCountry;
    }

    public String getSecondCountry() {
        return secondCountry;
    }

    public int getFirstCountryScore() {
        return firstCountryScore;
    }

    public int getSecondCountryScore() {
        return secondCountryScore;
    }

    public String getNameOfWinner() {
        if (firstCountryScore > secondCountryScore) {
            return firstCountry;
        } else if (firstCountryScore < secondCountryScore) {
            return secondCountry;
        } else {
            return "draw";
        }
    }

    public int getDifferenceInAbsoluteValue() {
        return Math.abs(firstCountryScore-secondCountryScore);
    }

    @Override
    public String toString() {
        return
                "firstCountry='" + firstCountry + '\'' +
                ", secondCountry='" + secondCountry + '\'' +
                ", firstCountryScore=" + firstCountryScore +
                ", secondCountryScore=" + secondCountryScore;

    }
}

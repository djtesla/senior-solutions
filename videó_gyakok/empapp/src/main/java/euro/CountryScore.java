package euro;

public class CountryScore {

    private String name;
    private int score;

    public CountryScore(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    @Override
    public String toString() {
        return
                "name='" + name +  ", score=" + score;

    }
}

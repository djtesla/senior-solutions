package euro;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class GameRepository {

    private List<Game> games = new ArrayList<>();

    public List<Game> getGames() {
        return games;
    }

    public void addGame(Game game){
        games.add(game);
    }

    public void loadGamesFromFile(String pathStr){
        try (BufferedReader reader = Files.newBufferedReader(Path.of(pathStr))) {
            String line;
            while((line = reader.readLine())!= null) {
                games.add(getGamePerLine(line));
            }
        } catch (IOException ioe) {
            throw new IllegalArgumentException("Cannot find file", ioe);
        }
    }


    private Game getGamePerLine(String line) {
        String firstCountry = line.split(";")[0];
        String secondCountry = line.split(";")[1];
        int firstCountryScore = Integer.parseInt(line.split(";")[2]);
        int secondCountryScore = Integer.parseInt(line.split(";")[3]);

        Game gamePerLine =  new Game(firstCountry, secondCountry, firstCountryScore, secondCountryScore);
        return gamePerLine;
    }


}

package euro;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GameService {

    private GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public Game getGameWithBiggestDifference(){
        return gameRepository.getGames().stream()
                .max(Comparator.comparing(Game::getDifferenceInAbsoluteValue)).get();
    }

    public int getNumberOfScoredGoalsOfCountry(String country){
        return
                gameRepository.getGames().stream()
                        .filter(game -> game.getFirstCountry().equals(country))
                        .mapToInt(Game::getFirstCountryScore)
                        .sum()
                +
                        gameRepository.getGames().stream()
                                .filter(game -> game.getSecondCountry().equals(country))
                                .mapToInt(Game::getSecondCountryScore)
                                .sum();

    }

    public String getCountyWithMostScoredGoals(){
        List<CountryScore> countryScoreList1 = gameRepository.getGames().stream()
                .map(game -> new CountryScore(game.getFirstCountry(), game.getFirstCountryScore()))
                .collect(Collectors.toList());

        List<CountryScore> countryScoreList2 = gameRepository.getGames().stream()
                .map(game -> new CountryScore(game.getSecondCountry(), game.getSecondCountryScore()))
                .collect(Collectors.toList());

        List<CountryScore> union = Stream.of(countryScoreList1, countryScoreList2)
                .flatMap(countryScores -> countryScores.stream())
                .collect(Collectors.toList());


        Map<String, Integer> scoresMap = union.stream()
                .collect(Collectors
                        .toMap(CountryScore::getName, countryScore -> countryScore.getScore(), ((sc1, sc2) -> sc1+sc2), TreeMap::new));

        System.out.println(scoresMap);
        int min = Integer.MIN_VALUE;
        String country = "";
        for (Map.Entry<String, Integer> entry: scoresMap.entrySet()) {
            if (entry.getValue() > min) {
                min = entry.getValue();
                country = entry.getKey();
            }
        }
        return country;
    }
}

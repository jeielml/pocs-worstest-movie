package br.com.lopes.worstestmovie.api.representation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TopTailWinnersInterval {

    private List<WinnerInterval> min;
    List<WinnerInterval> max;

    public static TopTailWinnersInterval fromWinnersInterval(final Set<WinnerInterval> winners){
        Integer minInterval = winners.stream().min(Comparator.naturalOrder())
                .map(WinnerInterval::getInterval)
                .orElse(0);

        Integer maxInterval = winners.stream().max(Comparator.naturalOrder())
                .map(WinnerInterval::getInterval)
                .orElse(0);

        List<WinnerInterval> min = winners.stream()
                .filter(winnerInterval -> winnerInterval.getInterval().compareTo(minInterval) == 0).collect(Collectors.toList());

        List<WinnerInterval> max = winners.stream()
                .filter(winnerInterval -> winnerInterval.getInterval().compareTo(maxInterval) == 0).collect(Collectors.toList());

        return new TopTailWinnersInterval(min, max);
    }

}

/**
 * {
 * "min": [
 * {
 * "producer": "Producer 1",
 * "interval": 1,
 * "previousWin": 2008,
 * "followingWin": 2009
 * },
 * {
 * "producer": "Producer 2",
 * "interval": 1,
 * "previousWin": 2018,
 * "followingWin": 2019
 * }
 * ],
 * "max": [
 * {
 * "producer": "Producer 1",
 * "interval": 99,
 * "previousWin": 1900,
 * "followingWin": 1999
 * },
 * {
 * "producer": "Producer 2",
 * "interval": 99,
 * "previousWin": 2000,
 * "followingWin": 2099
 * }
 * ]
 * }
 */
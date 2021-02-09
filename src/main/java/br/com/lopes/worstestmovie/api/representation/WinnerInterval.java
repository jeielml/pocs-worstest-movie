package br.com.lopes.worstestmovie.api.representation;

import br.com.lopes.worstestmovie.model.producer.Producer;
import br.com.lopes.worstestmovie.model.producer.Win;
import lombok.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

@AllArgsConstructor
@Getter
@ToString
@Builder
@NoArgsConstructor
@EqualsAndHashCode
public class WinnerInterval implements Comparable<WinnerInterval> {
    private String producer;
    private Integer interval;
    private Integer previousWin;
    private Integer followingWin;

    public static Stream<WinnerInterval> of(Producer producer) {
        if(producer.getWins().size() < 2) {
            throw new IllegalArgumentException("Producer must be an winner more then once!");
        }
        List<WinnerInterval> winnerIntervalList = new ArrayList<>();
        Iterator<Win> iterator = producer.getWins()
                .stream()
                .sorted(Win::compareTo)
                .iterator();

        Win previousWin = null;
        while (iterator.hasNext()) {
            Win current = iterator.next();
            if(previousWin != null && !previousWin.equals(current)){
                WinnerInterval winnerInterval = getWinnerInterval(producer, previousWin,current);
                winnerIntervalList.add(winnerInterval);
            }
            previousWin = current;
        }

        return winnerIntervalList.stream();
    }

    private static WinnerInterval getWinnerInterval(Producer producer, Win previousWin,Win followingWin) {
        WinnerInterval winnerInterval = WinnerInterval
                .builder()
                .producer(producer.getName())
                .interval(followingWin.getYear() - previousWin.getYear())
                .previousWin(previousWin.getYear())
                .followingWin(followingWin.getYear())
                .build();
        return winnerInterval;
    }


    @Override
    public int compareTo(WinnerInterval o) {
        return this.getInterval().compareTo(o.getInterval());
    }

}

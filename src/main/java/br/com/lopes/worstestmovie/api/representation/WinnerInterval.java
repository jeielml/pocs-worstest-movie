package br.com.lopes.worstestmovie.api.representation;

import br.com.lopes.worstestmovie.model.producer.Producer;
import br.com.lopes.worstestmovie.model.producer.Win;
import lombok.*;

import java.util.ArrayList;
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
        ArrayList<Win> wins = new ArrayList<>(producer.getWins());
        List<WinnerInterval> winnerIntervalList = new ArrayList<>();

        for (int i = 1; i < wins.size(); i++) {
            Win previousWin = wins.get(i-1);
            Win followingWin = wins.get(i);
            WinnerInterval winnerInterval = WinnerInterval
                    .builder()
                    .producer(producer.getName())
                    .interval(followingWin.getYear() - previousWin.getYear())
                    .previousWin(previousWin.getYear())
                    .followingWin(followingWin.getYear())
                    .build();
            winnerIntervalList.add(winnerInterval);
        }

        return winnerIntervalList.stream();
    }


    @Override
    public int compareTo(WinnerInterval o) {
        return this.getInterval().compareTo(o.getInterval());
    }

}

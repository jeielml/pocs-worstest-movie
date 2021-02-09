package br.com.lopes.worstestmovie.model.producer;


import br.com.lopes.worstestmovie.api.representation.WinnerInterval;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "producers")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Builder
@EqualsAndHashCode(of = {"id"})
public class Producer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "producer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Win> wins;

    @Override
    public String toString() {
        return "Producer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @JsonIgnore
    public List<WinnerInterval> generateWinnerIntervals() {
        List<WinnerInterval> winnerIntervalList = new ArrayList<>();

        if (this.wins.size() < 2) {
            return winnerIntervalList;
        }

        Iterator<Win> iterator = wins.stream()
                .sorted(Win::compareTo)
                .iterator();

        Win previousWin = null;
        while (iterator.hasNext()) {
            Win current = iterator.next();
            if (previousWin != null && !previousWin.equals(current)) {
                WinnerInterval winnerInterval = generateWinnerInterval(previousWin, current);
                winnerIntervalList.add(winnerInterval);
            }
            previousWin = current;
        }

        return winnerIntervalList;
    }

    private WinnerInterval generateWinnerInterval(Win previousWin, Win followingWin) {
        return WinnerInterval
                .builder()
                .producer(this.name)
                .interval(followingWin.getYear() - previousWin.getYear())
                .previousWin(previousWin.getYear())
                .followingWin(followingWin.getYear())
                .build();
    }

}

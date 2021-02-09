package br.com.lopes.worstestmovie.api.representation;

import lombok.*;

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

    @Override
    public int compareTo(WinnerInterval o) {
        return this.getInterval().compareTo(o.getInterval());
    }

}

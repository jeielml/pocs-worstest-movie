package br.com.lopes.worstestmovie.model.producer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Builder
@Entity
@Table(name = "producers_wins")
@Embeddable
@EqualsAndHashCode(of = {"id"})
public class Win implements Comparable<Win> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer year;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY , optional = false)
    @JoinColumn(name = "producer_id", nullable = false)
    private Producer producer;

    void setProducer(Producer producer) {
        this.producer = producer;
    }

    @Override
    public int compareTo(Win other) {
        return this.year.compareTo(other.getYear());
    }
}

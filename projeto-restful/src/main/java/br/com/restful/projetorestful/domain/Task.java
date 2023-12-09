package br.com.restful.projetorestful.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "task")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Task {

    @ManyToOne
    @JoinColumn(name="user_id", nullable = false, updatable = false)
    private User user;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;


    @Column(name = "description", length = 255, nullable = false)
    @NotNull
    @NotEmpty
    private String descricao;



    @Override
    public boolean equals(Object object) {
        if (object == this)
            return true;
        if (object == null)
            return false;
        if (!(object instanceof Task))
            return false;

        Task outro = (Task) object;
        if (this.id == null)
            if (outro.id != null)
                return false;
            else if(!this.id.equals(outro.id))
                return false;

        return Objects.equals(this.id, outro.id) && Objects.equals(this.descricao, outro.descricao);

    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result  + ( this.id == null ? 0 : this.id.hashCode() );
        return result;
    }

}


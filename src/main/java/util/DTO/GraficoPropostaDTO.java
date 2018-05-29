package util.DTO;

import java.util.Objects;

/**
 *
 * @author Gabriela Santos
 */
public class GraficoPropostaDTO {
    
    private String data;
    private Integer quantidade;

    public GraficoPropostaDTO(String data, Integer quantidade) {
        this.data = data;
        this.quantidade = quantidade;
    }

    public String getData() {
        return data;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final GraficoPropostaDTO other = (GraficoPropostaDTO) obj;
        if (!Objects.equals(this.data, other.data)) {
            return false;
        }
        return true;
    }
   
    
    
}

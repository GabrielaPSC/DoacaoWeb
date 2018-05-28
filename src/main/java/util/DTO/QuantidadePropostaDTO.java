package util.DTO;

import java.util.Objects;

/**
 *
 * @author Gabriela Santos
 */
public class QuantidadePropostaDTO {
    
    private String data;
    private Integer quantidade;

    public QuantidadePropostaDTO(String data, Integer quantidade) {
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
        final QuantidadePropostaDTO other = (QuantidadePropostaDTO) obj;
        if (!Objects.equals(this.data, other.data)) {
            return false;
        }
        return true;
    }
   
    
    
}

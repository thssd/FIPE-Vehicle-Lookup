package br.com.tabelafipe.Consulta.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Veiculo (@JsonProperty("Valor") String valor,
                       @JsonProperty("Marca") String marca,
                       @JsonProperty("Modelo") String modelo,
                       @JsonProperty("AnoModelo") Integer ano,
                       @JsonProperty("Combustivel") String combustivel){
}

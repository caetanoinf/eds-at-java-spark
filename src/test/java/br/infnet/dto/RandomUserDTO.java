package br.infnet.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;

import java.io.Serializable;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class RandomUserDTO implements Serializable {
    private Integer id;
    private String name;
    private String password;

    public void setResults(JsonNode node) {
        JsonNode user = node.get(0);

        var firstName = user.get("name").get("first").asText();
        var lastName = user.get("name").get("last").asText();

        this.name = firstName + " " + lastName;
        this.password = user.get("login").get("password").asText();
        this.id = 0; // Não identifiquei um campo que pudesse ser usado como id númerico
    }
}

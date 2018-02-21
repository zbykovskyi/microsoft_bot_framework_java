package com.ipland.azure.bot.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ivan Zbykovskyi on 12/4/17.
 */
@Data
public class AdaptiveFactSet implements AdaptiveItem  {
    @JsonProperty("type")
    private String type = "FactSet";

    @JsonProperty("facts")
    private List<Fact> facts = new ArrayList<>();

    @Data
    @AllArgsConstructor
    private class Fact {
        @JsonProperty("title")
        private String title;
        @JsonProperty("value")
        private String value;
    }

    public AdaptiveFactSet addFact(String title, String value){
        facts.add(new Fact(title, value));
        return this;
    }
}

package com.ipland.azure.bot.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ivan Zbykovskyi on 12/4/17.
 */
@Data
public class AdaptiveCard {
    @JsonProperty("type")
    private String type = "AdaptiveCard";

    @JsonProperty("version")
    private String version = "1.0";

    @JsonProperty("$schema")
    private String schema = "http://adaptivecards.io/schemas/adaptive-card.json";

    @JsonProperty("body")
    private List<AdaptiveItem> body = null;

    @JsonProperty("actions")
    private List<CardAction> actions = new ArrayList<>();

    public AdaptiveCard addAction(CardAction cardAction) {
        actions.add(cardAction);
        return this;
    }
}

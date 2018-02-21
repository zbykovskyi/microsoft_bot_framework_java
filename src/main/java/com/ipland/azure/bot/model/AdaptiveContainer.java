package com.ipland.azure.bot.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ivan Zbykovskyi on 12/4/17.
 */
@Data
public class AdaptiveContainer implements AdaptiveItem {
    @JsonProperty("type")
    private String type = "Container";

    @JsonProperty("items")
    private List<AdaptiveItem> items = new ArrayList<>();

    public void addItem(AdaptiveItem item) {
        items.add(item);
    }
}
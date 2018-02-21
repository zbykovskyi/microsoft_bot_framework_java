package com.ipland.azure.bot.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * Created by Ivan Zbykovskyi on 12/4/17.
 */
@Data
@RequiredArgsConstructor
public class AdaptiveTextBlock implements AdaptiveItem {
    @JsonProperty("type")
    private String type = "TextBlock";

    @JsonProperty("text")
    @NonNull private final String text;

    @JsonProperty("size")
    @NonNull private final String size;

    @JsonProperty("weight")
    @NonNull private final String weight;

}

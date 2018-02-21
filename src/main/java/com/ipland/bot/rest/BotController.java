package com.ipland.bot.rest;

import com.ipland.azure.bot.api.ConversationsApi;
import com.ipland.azure.bot.model.*;
import com.ipland.bot.rest.auth.AuthenticationException;
import com.ipland.bot.rest.auth.Authenticator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import javax.inject.Inject;
import java.util.Arrays;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Created by Ivan Zbykovskyi on 11/29/17.
 */
@RestController
@Slf4j
public class BotController {

    @Inject
    private ConversationsApi api;

    @Inject
    private Authenticator authenticator;

    @RequestMapping(value = "/callback", method = RequestMethod.POST, consumes = APPLICATION_JSON_VALUE)
    public void callback(@RequestBody Activity activity, @RequestHeader("Authorization") String authorization) throws AuthenticationException {

        authenticator.doAuthenticate(authorization, activity);

        try {
            Activity reply = new Activity();
            reply.setFrom(activity.getRecipient());
            reply.setRecipient(activity.getFrom());
            reply.setLocale("ru-RU");
            reply.setType("message");
            reply.setConversation(activity.getConversation());
            log.info("message from id = {}; name = {}", activity.getFrom().getId(), activity.getFrom().getName());
            String text = "";
            if (activity.getText() != null) {
                text = activity.getText().toLowerCase();
            }

            log.info("Received text '{}' ", text);
            if (text.startsWith("adaptive")) {
                Attachment attachment = new Attachment();
                attachment.setContentType("application/vnd.microsoft.card.adaptive");

                AdaptiveCard adaptiveCard = new AdaptiveCard();

                AdaptiveTextBlock title = new AdaptiveTextBlock("Header", "large", "bolder");

                AdaptiveFactSet facts = new AdaptiveFactSet();
                facts.addFact("Title", "value");

                AdaptiveContainer container = new AdaptiveContainer();
                container.addItem(title);
                container.addItem(facts);
                adaptiveCard.setBody(Arrays.asList(container));

                CardAction action = new CardAction();
                action.setTitle("Submit");
                action.setData("/title");
                action.setType("Action.Submit");
                adaptiveCard.addAction(action);
                attachment.setContent(adaptiveCard);
                reply.addAttachmentsItem(attachment);
            } else if (text.startsWith("/title")) {
                reply.setText("Submitted title");
            }

            log.info("Sending reply to {}", activity.getServiceUrl());
            api.getApiClient().setBasePath(activity.getServiceUrl());
            api.conversationsReplyToActivity(activity.getConversation().getId(), activity.getId(), reply);

        } catch (HttpClientErrorException e) {
            log.error("Unexpected error : {}", ExceptionUtils.getStackTrace(e));
        }
    }


}

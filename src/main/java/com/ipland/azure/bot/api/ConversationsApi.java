package com.ipland.azure.bot.api;

import com.ipland.azure.bot.invoker.ApiClient;
import com.ipland.azure.bot.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2018-01-31T17:19:19.373+02:00")
@Component("ConversationsApi")
public class ConversationsApi {
    private ApiClient apiClient;

    private String[] authNames;

    @Autowired
    public ConversationsApi(ApiClient apiClient) {
        this.apiClient = apiClient;
        authNames = new String[]{apiClient.getOAuth().getClass().getName()};
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * CreateConversation
     * Create a new Conversation.    POST to this method with a  * Bot being the bot creating the conversation  * IsGroup set to true if this is not a direct message (default is false)  * Members array containing the members you want to have be in the conversation.    The return value is a ResourceResponse which contains a conversation id which is suitable for use  in the message payload and REST API uris.    Most channels only support the semantics of bots initiating a direct message conversation.  An example of how to do that would be:    &#x60;&#x60;&#x60;  var resource &#x3D; await connector.conversations.CreateConversation(new ConversationParameters(){ Bot &#x3D; bot, members &#x3D; new ChannelAccount[] { new ChannelAccount(\&quot;user1\&quot;) } );  await connect.Conversations.SendToConversationAsync(resource.Id, new Activity() ... ) ;    &#x60;&#x60;&#x60;
     * <p><b>200</b> - An object will be returned containing   * the ID for the conversation  * ActivityId for the activity if provided.  If ActivityId is null then the channel doesn&#39;t support returning resource id&#39;s for activity.
     * <p><b>201</b> - An object will be returned containing   * the ID for the conversation  * ActivityId for the activity if provided.  If ActivityId is null then the channel doesn&#39;t support returning resource id&#39;s for activity.
     * <p><b>202</b> - An object will be returned containing   * the ID for the conversation  * ActivityId for the activity if provided.  If ActivityId is null then the channel doesn&#39;t support returning resource id&#39;s for activity.
     * <p><b>400</b> - The request was malformed or otherwise incorrect. Inspect the message for a more detailed description.
     * <p><b>401</b> - The bot is not authorized to make this request. Please check your Microsoft App ID and Microsoft App Password.
     * <p><b>403</b> - The bot is not authorized to make this request. Please check your Microsoft App ID and Microsoft App Password.
     * <p><b>404</b> - The resource was not found.
     * <p><b>405</b> - The method and URI you are trying to use is not allowed on this service.  For example, not all services support the DELETE or PUT verbs on an activity URI.
     * <p><b>429</b> - Too many requests have been submitted to this API. This error may be accompanied by a Retry-After header, which includes the suggested retry interval.
     * <p><b>500</b> - An internal server has occurred. Inspect the message for a more detailed description.
     * <p><b>503</b> - The service you are trying to communciate with is unavailable.
     *
     * @param parameters Parameters to create the conversation from
     * @return ConversationResourceResponse
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ConversationResourceResponse conversationsCreateConversation(ConversationParameters parameters) throws RestClientException {
        Object postBody = parameters;

        // verify the required parameter 'parameters' is set
        if (parameters == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'parameters' when calling conversationsCreateConversation");
        }

        String path = UriComponentsBuilder.fromPath("/v3/conversations").build().toUriString();

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        final String[] accepts = {
                "application/json", "text/json", "application/xml", "text/xml"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = {
                "application/json", "text/json", "application/xml", "text/xml", "application/x-www-form-urlencoded"
        };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        ParameterizedTypeReference<ConversationResourceResponse> returnType = new ParameterizedTypeReference<ConversationResourceResponse>() {
        };
        return apiClient.invokeAPI(path, HttpMethod.POST, queryParams, postBody, headerParams, formParams, accept, contentType, authNames, returnType);
    }

    /**
     * DeleteActivity
     * Delete an existing activity.    Some channels allow you to delete an existing activity, and if successful this method will remove the specified activity.
     * <p><b>200</b> - The operation succeeded, there is no response.
     * <p><b>202</b> - The request has been accepted for processing, but the processing has not been completed
     * <p><b>400</b> - The request was malformed or otherwise incorrect. Inspect the message for a more detailed description.
     * <p><b>401</b> - The bot is not authorized to make this request. Please check your Microsoft App ID and Microsoft App Password.
     * <p><b>403</b> - The bot is not authorized to make this request. Please check your Microsoft App ID and Microsoft App Password.
     * <p><b>404</b> - The resource was not found.
     * <p><b>405</b> - The method and URI you are trying to use is not allowed on this service.  For example, not all services support the DELETE or PUT verbs on an activity URI.
     * <p><b>429</b> - Too many requests have been submitted to this API. This error may be accompanied by a Retry-After header, which includes the suggested retry interval.
     * <p><b>500</b> - An internal server has occurred. Inspect the message for a more detailed description.
     * <p><b>503</b> - The service you are trying to communciate with is unavailable.
     *
     * @param conversationId Conversation ID
     * @param activityId     activityId to delete
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void conversationsDeleteActivity(String conversationId, String activityId) throws RestClientException {
        Object postBody = null;

        // verify the required parameter 'conversationId' is set
        if (conversationId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'conversationId' when calling conversationsDeleteActivity");
        }

        // verify the required parameter 'activityId' is set
        if (activityId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'activityId' when calling conversationsDeleteActivity");
        }

        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("conversationId", conversationId);
        uriVariables.put("activityId", activityId);
        String path = UriComponentsBuilder.fromPath("/v3/conversations/{conversationId}/activities/{activityId}").buildAndExpand(uriVariables).toUriString();

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        final String[] accepts = {
                "application/json", "text/json", "application/xml", "text/xml"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = {};
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        ParameterizedTypeReference<Void> returnType = new ParameterizedTypeReference<Void>() {
        };
        apiClient.invokeAPI(path, HttpMethod.DELETE, queryParams, postBody, headerParams, formParams, accept, contentType, authNames, returnType);
    }

    /**
     * GetActivityMembers
     * Enumerate the members of an activity.     This REST API takes a ConversationId and a ActivityId, returning an array of ChannelAccount objects representing the members of the particular activity in the conversation.
     * <p><b>200</b> - An array of ChannelAccount objects
     * <p><b>400</b> - The request was malformed or otherwise incorrect. Inspect the message for a more detailed description.
     * <p><b>401</b> - The bot is not authorized to make this request. Please check your Microsoft App ID and Microsoft App Password.
     * <p><b>403</b> - The bot is not authorized to make this request. Please check your Microsoft App ID and Microsoft App Password.
     * <p><b>404</b> - The resource was not found.
     * <p><b>405</b> - The method and URI you are trying to use is not allowed on this service.  For example, not all services support the DELETE or PUT verbs on an activity URI.
     * <p><b>429</b> - Too many requests have been submitted to this API. This error may be accompanied by a Retry-After header, which includes the suggested retry interval.
     * <p><b>500</b> - An internal server has occurred. Inspect the message for a more detailed description.
     * <p><b>503</b> - The service you are trying to communciate with is unavailable.
     *
     * @param conversationId Conversation ID
     * @param activityId     Activity ID
     * @return List&lt;ChannelAccount&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<ChannelAccount> conversationsGetActivityMembers(String conversationId, String activityId) throws RestClientException {
        Object postBody = null;

        // verify the required parameter 'conversationId' is set
        if (conversationId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'conversationId' when calling conversationsGetActivityMembers");
        }

        // verify the required parameter 'activityId' is set
        if (activityId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'activityId' when calling conversationsGetActivityMembers");
        }

        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("conversationId", conversationId);
        uriVariables.put("activityId", activityId);
        String path = UriComponentsBuilder.fromPath("/v3/conversations/{conversationId}/activities/{activityId}/members").buildAndExpand(uriVariables).toUriString();

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        final String[] accepts = {
                "application/json", "text/json", "application/xml", "text/xml"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = {};
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        ParameterizedTypeReference<List<ChannelAccount>> returnType = new ParameterizedTypeReference<List<ChannelAccount>>() {
        };
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, formParams, accept, contentType, authNames, returnType);
    }

    /**
     * GetConversationMembers
     * Enumerate the members of a converstion.     This REST API takes a ConversationId and returns an array of ChannelAccount objects representing the members of the conversation.
     * <p><b>200</b> - An array of ChannelAccount objects
     * <p><b>400</b> - The request was malformed or otherwise incorrect. Inspect the message for a more detailed description.
     * <p><b>401</b> - The bot is not authorized to make this request. Please check your Microsoft App ID and Microsoft App Password.
     * <p><b>403</b> - The bot is not authorized to make this request. Please check your Microsoft App ID and Microsoft App Password.
     * <p><b>404</b> - The resource was not found.
     * <p><b>405</b> - The method and URI you are trying to use is not allowed on this service.  For example, not all services support the DELETE or PUT verbs on an activity URI.
     * <p><b>429</b> - Too many requests have been submitted to this API. This error may be accompanied by a Retry-After header, which includes the suggested retry interval.
     * <p><b>500</b> - An internal server has occurred. Inspect the message for a more detailed description.
     * <p><b>503</b> - The service you are trying to communciate with is unavailable.
     *
     * @param conversationId Conversation ID
     * @return List&lt;ChannelAccount&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<ChannelAccount> conversationsGetConversationMembers(String conversationId) throws RestClientException {
        Object postBody = null;

        // verify the required parameter 'conversationId' is set
        if (conversationId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'conversationId' when calling conversationsGetConversationMembers");
        }

        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("conversationId", conversationId);
        String path = UriComponentsBuilder.fromPath("/v3/conversations/{conversationId}/members").buildAndExpand(uriVariables).toUriString();

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        final String[] accepts = {
                "application/json", "text/json", "application/xml", "text/xml"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = {};
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        ParameterizedTypeReference<List<ChannelAccount>> returnType = new ParameterizedTypeReference<List<ChannelAccount>>() {
        };
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, formParams, accept, contentType, authNames, returnType);
    }

    /**
     * ReplyToActivity
     * This method allows you to reply to an activity.    This is slightly different from SendToConversation().  * SendToConverstion(conversationId) - will append the activity to the end of the conversation according to the timestamp or semantics of the channel.  * ReplyToActivity(conversationId,ActivityId) - adds the activity as a reply to another activity, if the channel supports it. If the channel does not support nested replies, ReplyToActivity falls back to SendToConversation.    Use ReplyToActivity when replying to a specific activity in the conversation.    Use SendToConversation in all other cases.
     * <p><b>200</b> - An object will be returned containing the ID for the resource.
     * <p><b>201</b> - A ResourceResponse object will be returned containing the ID for the resource.
     * <p><b>202</b> - An object will be returned containing the ID for the resource.
     * <p><b>400</b> - The request was malformed or otherwise incorrect. Inspect the message for a more detailed description.
     * <p><b>401</b> - The bot is not authorized to make this request. Please check your Microsoft App ID and Microsoft App Password.
     * <p><b>403</b> - The bot is not authorized to make this request. Please check your Microsoft App ID and Microsoft App Password.
     * <p><b>404</b> - The resource was not found.
     * <p><b>429</b> - Too many requests have been submitted to this API. This error may be accompanied by a Retry-After header, which includes the suggested retry interval.
     * <p><b>500</b> - An internal server has occurred. Inspect the message for a more detailed description.
     * <p><b>503</b> - The service you are trying to communciate with is unavailable.
     *
     * @param conversationId Conversation ID
     * @param activityId     activityId the reply is to (OPTIONAL)
     * @param activity       Activity to send
     * @return ResourceResponse
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResourceResponse conversationsReplyToActivity(String conversationId, String activityId, Activity activity) throws RestClientException {
        Object postBody = activity;

        // verify the required parameter 'conversationId' is set
        if (conversationId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'conversationId' when calling conversationsReplyToActivity");
        }

        // verify the required parameter 'activityId' is set
        if (activityId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'activityId' when calling conversationsReplyToActivity");
        }

        // verify the required parameter 'activity' is set
        if (activity == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'activity' when calling conversationsReplyToActivity");
        }

        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("conversationId", conversationId);
        uriVariables.put("activityId", activityId);
        String path = UriComponentsBuilder.fromPath("/v3/conversations/{conversationId}/activities/{activityId}").buildAndExpand(uriVariables).toUriString();

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        final String[] accepts = {
                "application/json", "text/json"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = {
                "application/json", "text/json", "application/xml", "text/xml", "application/x-www-form-urlencoded"
        };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        ParameterizedTypeReference<ResourceResponse> returnType = new ParameterizedTypeReference<ResourceResponse>() {
        };
        return apiClient.invokeAPI(path, HttpMethod.POST, queryParams, postBody, headerParams, formParams, accept, contentType, authNames, returnType);
    }

    /**
     * SendToConversation
     * This method allows you to send an activity to the end of a conversation.    This is slightly different from ReplyToActivity().  * SendToConverstion(conversationId) - will append the activity to the end of the conversation according to the timestamp or semantics of the channel.  * ReplyToActivity(conversationId,ActivityId) - adds the activity as a reply to another activity, if the channel supports it. If the channel does not support nested replies, ReplyToActivity falls back to SendToConversation.    Use ReplyToActivity when replying to a specific activity in the conversation.    Use SendToConversation in all other cases.
     * <p><b>200</b> - An object will be returned containing the ID for the resource.
     * <p><b>201</b> - A ResourceResponse object will be returned containing the ID for the resource.
     * <p><b>202</b> - An object will be returned containing the ID for the resource.
     * <p><b>400</b> - The request was malformed or otherwise incorrect. Inspect the message for a more detailed description.
     * <p><b>401</b> - The bot is not authorized to make this request. Please check your Microsoft App ID and Microsoft App Password.
     * <p><b>403</b> - The bot is not authorized to make this request. Please check your Microsoft App ID and Microsoft App Password.
     * <p><b>404</b> - The resource was not found.
     * <p><b>429</b> - Too many requests have been submitted to this API. This error may be accompanied by a Retry-After header, which includes the suggested retry interval.
     * <p><b>500</b> - An internal server has occurred. Inspect the message for a more detailed description.
     * <p><b>503</b> - The service you are trying to communciate with is unavailable.
     *
     * @param activity       Activity to send
     * @param conversationId Conversation ID
     * @return ResourceResponse
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResourceResponse conversationsSendToConversation(Activity activity, String conversationId) throws RestClientException {
        Object postBody = activity;

        // verify the required parameter 'activity' is set
        if (activity == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'activity' when calling conversationsSendToConversation");
        }

        // verify the required parameter 'conversationId' is set
        if (conversationId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'conversationId' when calling conversationsSendToConversation");
        }

        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("conversationId", conversationId);
        String path = UriComponentsBuilder.fromPath("/v3/conversations/{conversationId}/activities").buildAndExpand(uriVariables).toUriString();

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        final String[] accepts = {
                "application/json", "text/json"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = {
                "application/json", "text/json", "application/xml", "text/xml", "application/x-www-form-urlencoded"
        };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        ParameterizedTypeReference<ResourceResponse> returnType = new ParameterizedTypeReference<ResourceResponse>() {
        };
        return apiClient.invokeAPI(path, HttpMethod.POST, queryParams, postBody, headerParams, formParams, accept, contentType, authNames, returnType);
    }

    /**
     * UpdateActivity
     * Edit an existing activity.    Some channels allow you to edit an existing activity to reflect the new state of a bot conversation.    For example, you can remove buttons after someone has clicked \&quot;Approve\&quot; button.
     * <p><b>200</b> - An object will be returned containing the ID for the resource.
     * <p><b>201</b> - A ResourceResponse object will be returned containing the ID for the resource.
     * <p><b>202</b> - An object will be returned containing the ID for the resource.
     * <p><b>400</b> - The request was malformed or otherwise incorrect. Inspect the message for a more detailed description.
     * <p><b>401</b> - The bot is not authorized to make this request. Please check your Microsoft App ID and Microsoft App Password.
     * <p><b>403</b> - The bot is not authorized to make this request. Please check your Microsoft App ID and Microsoft App Password.
     * <p><b>404</b> - The resource was not found.
     * <p><b>405</b> - The method and URI you are trying to use is not allowed on this service.  For example, not all services support the DELETE or PUT verbs on an activity URI.
     * <p><b>429</b> - Too many requests have been submitted to this API. This error may be accompanied by a Retry-After header, which includes the suggested retry interval.
     * <p><b>500</b> - An internal server has occurred. Inspect the message for a more detailed description.
     * <p><b>503</b> - The service you are trying to communciate with is unavailable.
     *
     * @param conversationId Conversation ID
     * @param activityId     activityId to update
     * @param activity       replacement Activity
     * @return ResourceResponse
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResourceResponse conversationsUpdateActivity(String conversationId, String activityId, Activity activity) throws RestClientException {
        Object postBody = activity;

        // verify the required parameter 'conversationId' is set
        if (conversationId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'conversationId' when calling conversationsUpdateActivity");
        }

        // verify the required parameter 'activityId' is set
        if (activityId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'activityId' when calling conversationsUpdateActivity");
        }

        // verify the required parameter 'activity' is set
        if (activity == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'activity' when calling conversationsUpdateActivity");
        }

        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("conversationId", conversationId);
        uriVariables.put("activityId", activityId);
        String path = UriComponentsBuilder.fromPath("/v3/conversations/{conversationId}/activities/{activityId}").buildAndExpand(uriVariables).toUriString();

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        final String[] accepts = {
                "application/json", "text/json"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = {
                "application/json", "text/json", "application/xml", "text/xml", "application/x-www-form-urlencoded"
        };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        ParameterizedTypeReference<ResourceResponse> returnType = new ParameterizedTypeReference<ResourceResponse>() {
        };
        return apiClient.invokeAPI(path, HttpMethod.PUT, queryParams, postBody, headerParams, formParams, accept, contentType, authNames, returnType);
    }

    /**
     * UploadAttachment
     * Upload an attachment directly into a channel&#39;s blob storage.    This is useful because it allows you to store data in a compliant store when dealing with enterprises.    The response is a ResourceResponse which contains an AttachmentId which is suitable for using with the attachments API.
     * <p><b>200</b> - An object will be returned containing the ID for the resource.
     * <p><b>201</b> - A ResourceResponse object will be returned containing the ID for the resource.
     * <p><b>202</b> - An object will be returned containing the ID for the resource.
     * <p><b>400</b> - The request was malformed or otherwise incorrect. Inspect the message for a more detailed description.
     * <p><b>401</b> - The bot is not authorized to make this request. Please check your Microsoft App ID and Microsoft App Password.
     * <p><b>403</b> - The bot is not authorized to make this request. Please check your Microsoft App ID and Microsoft App Password.
     * <p><b>404</b> - The resource was not found.
     * <p><b>405</b> - The method and URI you are trying to use is not allowed on this service.  For example, not all services support the DELETE or PUT verbs on an activity URI.
     * <p><b>429</b> - Too many requests have been submitted to this API. This error may be accompanied by a Retry-After header, which includes the suggested retry interval.
     * <p><b>500</b> - An internal server has occurred. Inspect the message for a more detailed description.
     * <p><b>503</b> - The service you are trying to communciate with is unavailable.
     *
     * @param conversationId   Conversation ID
     * @param attachmentUpload Attachment data
     * @return ResourceResponse
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResourceResponse conversationsUploadAttachment(String conversationId, AttachmentData attachmentUpload) throws RestClientException {
        Object postBody = attachmentUpload;

        // verify the required parameter 'conversationId' is set
        if (conversationId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'conversationId' when calling conversationsUploadAttachment");
        }

        // verify the required parameter 'attachmentUpload' is set
        if (attachmentUpload == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'attachmentUpload' when calling conversationsUploadAttachment");
        }

        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("conversationId", conversationId);
        String path = UriComponentsBuilder.fromPath("/v3/conversations/{conversationId}/attachments").buildAndExpand(uriVariables).toUriString();

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        final String[] accepts = {
                "application/json", "text/json"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = {
                "application/json", "text/json", "application/xml", "text/xml", "application/x-www-form-urlencoded"
        };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        ParameterizedTypeReference<ResourceResponse> returnType = new ParameterizedTypeReference<ResourceResponse>() {
        };
        return apiClient.invokeAPI(path, HttpMethod.POST, queryParams, postBody, headerParams, formParams, accept, contentType, authNames, returnType);
    }
}

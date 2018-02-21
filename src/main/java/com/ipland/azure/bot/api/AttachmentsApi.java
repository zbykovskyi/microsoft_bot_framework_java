package com.ipland.azure.bot.api;

import com.ipland.azure.bot.invoker.ApiClient;
import com.ipland.azure.bot.model.AttachmentInfo;
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
@Component("AttachmentsApi")
public class AttachmentsApi {
    private ApiClient apiClient;

    private String[] authNames;

    @Autowired
    public AttachmentsApi(ApiClient apiClient) {
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
     * GetAttachment
     * Get the named view as binary content
     * <p><b>200</b> - An array of bytes which represent the content.
     * <p><b>301</b> - The Location header describes where the content is now.
     * <p><b>302</b> - The Location header describes where the content is now.
     * <p><b>400</b> - The request was malformed or otherwise incorrect. Inspect the message for a more detailed description.
     * <p><b>401</b> - The bot is not authorized to make this request. Please check your Microsoft App ID and Microsoft App Password.
     * <p><b>403</b> - The bot is not authorized to make this request. Please check your Microsoft App ID and Microsoft App Password.
     * <p><b>404</b> - The resource was not found.
     * <p><b>429</b> - Too many requests have been submitted to this API. This error may be accompanied by a Retry-After header, which includes the suggested retry interval.
     * <p><b>500</b> - An internal server has occurred. Inspect the message for a more detailed description.
     * <p><b>503</b> - The service you are trying to communciate with is unavailable.
     *
     * @param attachmentId attachment id
     * @param viewId       View id from attachmentInfo
     * @return byte[]
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public byte[] attachmentsGetAttachment(String attachmentId, String viewId) throws RestClientException {
        Object postBody = null;

        // verify the required parameter 'attachmentId' is set
        if (attachmentId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'attachmentId' when calling attachmentsGetAttachment");
        }

        // verify the required parameter 'viewId' is set
        if (viewId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'viewId' when calling attachmentsGetAttachment");
        }

        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("attachmentId", attachmentId);
        uriVariables.put("viewId", viewId);
        String path = UriComponentsBuilder.fromPath("/v3/attachments/{attachmentId}/views/{viewId}").buildAndExpand(uriVariables).toUriString();

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        final String[] accepts = {
                "application/json", "text/json", "application/xml", "text/xml"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = {};
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        ParameterizedTypeReference<byte[]> returnType = new ParameterizedTypeReference<byte[]>() {
        };
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, formParams, accept, contentType, authNames, returnType);
    }

    /**
     * GetAttachmentInfo
     * Get AttachmentInfo structure describing the attachment views
     * <p><b>200</b> - An attachmentInfo object is returned which describes the:  * type of the attachment  * name of the attachment      and an array of views:  * Size - size of the object  * ViewId - View Id which can be used to fetch a variation on the content (ex: original or thumbnail)
     * <p><b>400</b> - The request was malformed or otherwise incorrect. Inspect the message for a more detailed description.
     * <p><b>401</b> - The bot is not authorized to make this request. Please check your Microsoft App ID and Microsoft App Password.
     * <p><b>403</b> - The bot is not authorized to make this request. Please check your Microsoft App ID and Microsoft App Password.
     * <p><b>404</b> - The resource was not found.
     * <p><b>429</b> - Too many requests have been submitted to this API. This error may be accompanied by a Retry-After header, which includes the suggested retry interval.
     * <p><b>500</b> - An internal server has occurred. Inspect the message for a more detailed description.
     * <p><b>503</b> - The service you are trying to communciate with is unavailable.
     *
     * @param attachmentId attachment id
     * @return AttachmentInfo
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public AttachmentInfo attachmentsGetAttachmentInfo(String attachmentId) throws RestClientException {
        Object postBody = null;

        // verify the required parameter 'attachmentId' is set
        if (attachmentId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'attachmentId' when calling attachmentsGetAttachmentInfo");
        }

        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("attachmentId", attachmentId);
        String path = UriComponentsBuilder.fromPath("/v3/attachments/{attachmentId}").buildAndExpand(uriVariables).toUriString();

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        final String[] accepts = {
                "application/json", "text/json", "application/xml", "text/xml"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = {};
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        ParameterizedTypeReference<AttachmentInfo> returnType = new ParameterizedTypeReference<AttachmentInfo>() {
        };
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, formParams, accept, contentType, authNames, returnType);
    }
}

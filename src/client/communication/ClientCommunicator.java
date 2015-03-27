/**
 * ClientCommunicator.java
 * JRE v1.8.0_40
 *
 * Created by William Myers on Mar 24, 2015.
 * Copyright (c) 2015 William Myers. All Rights reserved.
 */
package client.communication;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import server.ServerException;

import shared.communication.*;

/**
 * The Class ClientCommunicator.
 */
public class ClientCommunicator {
  private XStream       xs = new XStream(new DomDriver());

  private String        URL_PREFIX;
  private String        host;
  private int           port;

  public String getHost() {
    return host;
  }

  public void setHost(String host) {
    this.host = host;
  }

  public int getPort() {
    return port;
  }

  public void setPort(int port) {
    this.port = port;
  }

  /**
   * Instantiates a new ClientCommunicator.
   *
   */
  public ClientCommunicator() {
    host = "localhost";
    port = 39640;
    URL_PREFIX = String.format("http://%s:%d", host, port);
  }

  /**
   * Instantiates a new client communicator.
   *
   * @param host the host
   * @param port the port
   */
  public ClientCommunicator(String host, String port) {
    this.port = Integer.parseInt(port);
    this.host = host;
    URL_PREFIX = String.format("http://%s:%s", host, port);
  }

  /**
   * Validate user.
   *
   * @param params the params
   * @return the validate user response
   */
  public ValidateUserResponse validateUser(ValidateUserRequest params) throws ServerException {
    return (ValidateUserResponse) doPost("/ValidateUser", params);
  }

  /**
   * Gets the projects.
   *
   * @param creds the creds
   * @return the projects
   * @throws ServerException the client exception
   */
  public GetProjectsResponse getProjects(GetProjectsRequest creds) throws ServerException {
    return (GetProjectsResponse) doPost("/GetProjects", creds);
  }

  /**
   * Gets the sample batch.
   *
   * @param params the params
   * @return the sample batch
   * @throws ServerException the client exception
   * @throws MalformedURLException
   */
  public GetSampleBatchResponse getSampleBatch(GetSampleBatchRequest params)
      throws ServerException, MalformedURLException {
    GetSampleBatchResponse result = (GetSampleBatchResponse) doPost("/GetSampleImage", params);
    URL url = new URL(URL_PREFIX);
    result.setUrlPrefix(url);
    return result;
  }

  /**
   * Download batch.
   *
   * @param params the params
   * @return the download batch response
   * @throws ServerException the client exception
   * @throws MalformedURLException
   */
  public DownloadBatchResponse downloadBatch(DownloadBatchRequest params) throws ServerException,
      MalformedURLException {
    DownloadBatchResponse result = (DownloadBatchResponse) doPost("/DownloadBatch", params);
    URL url = new URL(URL_PREFIX);
    result.setUrlPrefix(url);
    return result;
  }

  /**
   * Submit batch.
   *
   * @param params the params
   * @return the submit batch response
   * @throws ServerException the client exception
   */
  public SubmitBatchResponse submitBatch(SubmitBatchRequest params) throws ServerException {
    return (SubmitBatchResponse) doPost("/SubmitBatch", params);
  }

  /**
   * Gets the fields.
   *
   * @param params the params
   * @return the fields
   * @throws ServerException the client exception
   */
  public GetFieldsResponse getFields(GetFieldsRequest params) throws ServerException {
    return (GetFieldsResponse) doPost("/GetFields", params);
  }

  /**
   * Search.
   *
   * @param params the params
   * @return the search response
   * @throws ServerException the client exception
   */
  public SearchResponse search(SearchRequest params) throws ServerException {
    SearchResponse result = (SearchResponse) doPost("/Search", params);
    List<String> urls = new ArrayList<String>();
    for (String s : result.getUrls()) {
      String url = URL_PREFIX + "/" + s;
      urls.add(url);
      System.out.println(s);
    }
    result.setUrls(urls);
    return result;
  }

  /**
   * Download file.
   *
   * @param params the params
   * @return the download file response
   * @throws ServerException the client exception
   */
  public DownloadFileResponse downloadFile(DownloadFileRequest params) throws ServerException {
    return new DownloadFileResponse(doGet(URL_PREFIX + File.separator + params.getUrl()));
  }

  /**
   * Do get.
   *
   * @param urlPath the url path
   * @return the byte[]
   * @throws ServerException the client exception
   */
  public byte[] doGet(String urlPath) throws ServerException {
    byte[] result = null;
    try {
      URL url = new URL(urlPath);
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
      connection.setRequestMethod("GET");
      connection.setDoOutput(true);
      connection.connect();

      if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
        InputStream response = connection.getInputStream();
        result = IOUtils.toByteArray(response);
        response.close();
      }
    } catch (Exception e) {
      throw new ServerException(e);
    }
    return result;
  }

  private Response doPost(String postCommand, Request request) throws ServerException {
    assert postCommand != null;
    assert request != null;
    try {
      URL url = new URL(URL_PREFIX + postCommand);
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
      connection.setRequestMethod("POST");
      connection.setDoInput(true);
      connection.setDoOutput(true);
      connection.setRequestProperty("Accept", "html/text");

      connection.connect();

      OutputStream requestBody = connection.getOutputStream();
      xs.toXML(request, requestBody);
      requestBody.close();

      if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
        Response r = (Response) xs.fromXML(connection.getInputStream());
        connection.getInputStream().close();
        return r;
      } else {
        connection.getInputStream().close();
        throw new ServerException(String.format("POST FAILED: %s HTTP code: %d", postCommand,
            connection.getResponseCode()));
      }
    } catch (Exception e) {
      throw new ServerException(e);
    }
  }
}

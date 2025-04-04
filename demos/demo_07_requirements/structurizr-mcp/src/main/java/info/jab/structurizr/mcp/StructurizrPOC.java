package info.jab.structurizr.mcp;

import com.structurizr.api.WorkspaceApiClient;
import com.structurizr.api.StructurizrClientException;
import com.structurizr.Workspace;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

public class StructurizrPOC {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) throws StructurizrClientException {
        
        var structurizrUrl = "http://localhost:9000";
        var structurizrApiUrl = structurizrUrl + "/api";
        ApiDetails response = getWorkspaceWithCurl(structurizrUrl);

        System.out.println("2. Get workspace");
        WorkspaceApiClient client = new WorkspaceApiClient(structurizrApiUrl, response.apiKey(), response.apiSecret());
        Workspace workspace = client.getWorkspace(7);
        System.out.println(workspace.toString());
        //TODO: Update the workspace DSL
    }

    private static ApiDetails getWorkspaceWithCurl(String baseUrl) {
        System.out.println("1. Authenticate into Structurizr");
        try (HttpClient httpClient = HttpClient.newHttpClient()) {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(baseUrl + "/api/workspace"))
                    .header("X-Authorization", "1234567890")
                    .GET()
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            var responseBody = response.body();

            WorkspacesResponse workspacesResponse = mapper.readValue(responseBody, WorkspacesResponse.class);
            
            var apiKey = workspacesResponse.workspaces().get(0).apiKey();
            var apiSecret = workspacesResponse.workspaces().get(0).apiSecret();

            //System.out.println("Workspace apiKey: " + apiKey);
            //System.out.println("Workspace apiSecret: " + apiSecret);

            return new ApiDetails(apiKey, apiSecret);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Error making request: " + e.getMessage());
        }
    }
}

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

public class StructurizrMcp {

    private static ObjectMapper mapper = new ObjectMapper();
    public static void main(String[] args) throws StructurizrClientException {
        System.out.println("Hello World!");

        String structurizrUrl = "http://localhost:9000";
        String structurizrApiUrl = structurizrUrl + "/api";
        String response = getWorkspaceWithCurl(structurizrUrl);
        System.out.println(response);

        try {
            WorkspacesResponse workspacesResponse = mapper.readValue(response, WorkspacesResponse.class);
            
            var apiKey = workspacesResponse.workspaces().get(0).apiKey();
            var apiSecret = workspacesResponse.workspaces().get(0).apiSecret();

            System.out.println("Workspace apiKey: " + apiKey);
            System.out.println("Workspace apiSecret: " + apiSecret);

            WorkspaceApiClient client;
            client = new WorkspaceApiClient(structurizrApiUrl, apiKey, apiSecret);
            //client.setWorkspaceArchiveLocation(new File("structurizr-mcp/structurizr-local"));
            Workspace workspace = client.getWorkspace(7);
    
        } catch (IOException e) {
            System.err.println("Error deserializing response: " + e.getMessage());
        }
    }

    public static String getWorkspaceWithCurl(String baseUrl) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + "/api/workspace"))
                .header("X-Authorization", "1234567890")
                .GET()
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (IOException | InterruptedException e) {
            return "Error making request: " + e.getMessage();
        }
    }
}

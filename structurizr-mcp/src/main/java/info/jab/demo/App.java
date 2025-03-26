package info.jab.demo;

import com.structurizr.api.WorkspaceApiClient;
import com.structurizr.api.StructurizrClientException;
import com.structurizr.Workspace;
import java.io.File;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws StructurizrClientException {
        System.out.println("Hello World!");

        WorkspaceApiClient client;
        client = new WorkspaceApiClient("http://localhost:9000/api","9cbe63db-012b-4ddd-bd34-2d6577d35e7b", "08b47c68-c1ea-43e3-b4e0-6361e9ed879d");
        //client.setWorkspaceArchiveLocation(new File("./", "structurizr-local"));
        Workspace workspace = client.getWorkspace(1);
        //String dsl = workspace.getDsl();
        //System.out.println("Workspace DSL:");
        //System.out.println(dsl);

        System.out.println("Bye World!");
    }
}


///usr/bin/env jbang "$0" "$@" ; exit $?
//DEPS io.quarkus:quarkus-bom:3.19.2@pom
//DEPS io.quarkiverse.mcp:quarkus-mcp-server-stdio:1.0.0.Beta4

import io.quarkiverse.mcp.server.Tool;
import io.quarkiverse.mcp.server.ToolArg;

// WIP
public class MCPStructurizr {

    @Tool(description = "Update the Structurizr workspace")
    public String improveDesign(
            @ToolArg(description = "Workspace Id") long workspaceId, 
            @ToolArg(description = "Idea to improve the workspace") String ideaToImproveStructurizrDSL) {
        return processOperation(workspaceId, ideaToImproveStructurizrDSL);
    }

    private String processOperation(long workspaceId, String ideaToImproveStructurizrDSL) {
        return "OK";
    }
}

package info.jab.structurizr.mcp;

public record WorkspaceResponse(
    long id,
    String name,
    String description,
    String apiKey,
    String apiSecret,
    String privateUrl,
    String publicUrl,
    String shareableUrl
) {}

package api;

public enum FormParamEnum {
    FORM_PARAM("message", "owner_id", "photo", "server", "hash", "type", "post", "item_id", "attachments", "post_id", "v","access_token");

    String message;
    String ownerId;
    String photo;
    String server;
    String hash;
    String type;
    String post;
    String itemId;
    String attachments;
    String postId;
    String versionApi;
    String versionToken;

    FormParamEnum(String message, String ownerId, String photo, String server, String hash, String type, String post,
                  String itemId, String attachments, String postId, String versionApi, String versionToken) {
        this.message = message;
        this.ownerId = ownerId;
        this.photo = photo;
        this.server = server;
        this.hash = hash;
        this.type = type;
        this.post = post;
        this.itemId = itemId;
        this.attachments = attachments;
        this.postId = postId;
        this.versionApi = versionApi;
        this.versionToken = versionToken;
    }

    public String getMessage() {
        return message;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public String getPhoto() {
        return photo;
    }

    public String getServer() {
        return server;
    }

    public String getHash() {
        return hash;
    }

    public String getType() {
        return type;
    }

    public String getPost() {
        return post;
    }

    public String getItemId() {
        return itemId;
    }

    public String getAttachments() {
        return attachments;
    }

    public String getPostId (){
        return postId;
    }

    public String getVersionApi() {
        return versionApi;
    }

    public String getVersionToken() {
        return versionToken;
    }
}

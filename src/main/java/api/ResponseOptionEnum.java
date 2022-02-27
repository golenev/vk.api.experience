package api;

public enum ResponseOptionEnum {
    RESPONSE_OPTION ("response[0].id", "response.liked", "response.upload_url", "response.post_id");

    String listId;
    String isLiked;
    String uploadUrl;
    String postId;

    ResponseOptionEnum(String listId, String isLiked, String uploadUrl, String postId) {
        this.listId = listId;
        this.isLiked = isLiked;
        this.uploadUrl = uploadUrl;
        this.postId = postId;
    }

    public String getListId() {
        return listId;
    }

    public String getIsLiked() {
        return isLiked;
    }

    public String getUploadUrl() {
        return uploadUrl;
    }

    public String getPostId() {
        return postId;
    }
}

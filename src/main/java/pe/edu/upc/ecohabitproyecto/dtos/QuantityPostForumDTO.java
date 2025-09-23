package pe.edu.upc.ecohabitproyecto.dtos;

public class QuantityPostForumDTO {
    private String nameForum;
    private int quantityPost;

    public QuantityPostForumDTO() {}

    public String getNameForum() {
        return nameForum;
    }

    public void setNameForum(String nameForum) {
        this.nameForum = nameForum;
    }

    public int getQuantityPost() {
        return quantityPost;
    }

    public void setQuantityPost(int quantityPost) {
        this.quantityPost = quantityPost;
    }
}

package id.sch.smktelkom_mlg.privateassignment.xirpl115.CinemaXXIV;

/**
 * Created by Smktelkom on 5/11/2017.
 */

public class Page1ListItem {

    private String imageUrl;
    private String title;
    private String content;

    public Page1ListItem(String imageUrl, String title, String content) {
        this.imageUrl = imageUrl;
        this.title = title;
        this.content = content;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}

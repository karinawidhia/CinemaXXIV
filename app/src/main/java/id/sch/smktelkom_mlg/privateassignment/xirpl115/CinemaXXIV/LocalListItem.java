package id.sch.smktelkom_mlg.privateassignment.xirpl115.CinemaXXIV;

import java.io.Serializable;

/**
 * Created by Smktelkom on 5/14/2017.
 */

public class LocalListItem implements Serializable {
    public String imageUrl;
    public String head;

    public LocalListItem(String imageUrl, String head) {
        this.imageUrl = imageUrl;
        this.head = head;
    }
}

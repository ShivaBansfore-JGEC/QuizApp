package com.example.shiva.quizapp1;

/**
 * Created by Shiva on 9/29/2018.
 */

public class rv_model {
    public rv_model(String name, int image_id) {
        this.name = name;
        this.image_id = image_id;
    }

    public String getName() {
        return name;
    }

    public int getImage_id() {
        return image_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage_id(int image_id) {
        this.image_id = image_id;
    }

    String name;
    int image_id;

}

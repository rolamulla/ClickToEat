
package com.clickandeat.finalproject5.Model;

public class Recommended {


    //    @SerializedName("name")
//    @Expose
    private String name;
    //    @SerializedName("image")
//    @Expose
    private String image;
    //    @SerializedName("rating")
//    @Expose
    private String rating;
    //    @SerializedName("price")
//    @Expose
    private String price;
    //    @SerializedName("note")
//    @Expose
    private String note;

    private String category;

    private String restaurant;

    public String getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(String restaurant) {
        this.restaurant = restaurant;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}

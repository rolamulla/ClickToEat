package com.clickandeat.finalproject5.Model;
import java.util.List;

public class foodData {

    //    @SerializedName("popular")
//    @Expose
    private List<Popular> popular;
    //    @SerializedName("recommended")
//    @Expose
    private List<Recommended> recommended;
    //    @SerializedName("allMenu")
//    @Expose
    private List<com.clickandeat.finalproject5.Model.allMenu> allMenu;

    public List<Popular> getPopular() {
        return popular;
    }

    public void setPopular(List<Popular> popular) {
        this.popular = popular;
    }

    public List<Recommended> getRecommended() {
        return recommended;
    }

    public void setRecommended(List<Recommended> recommended) {
        this.recommended = recommended;
    }

    public List<com.clickandeat.finalproject5.Model.allMenu> getAllMenu() {
        return allMenu;
    }

    public void setAllMenu(List<com.clickandeat.finalproject5.Model.allMenu> allMenu) {
        this.allMenu = allMenu;
    }
}
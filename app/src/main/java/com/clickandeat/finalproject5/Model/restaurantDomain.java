package com.clickandeat.finalproject5.Model;

import android.graphics.drawable.Drawable;

public class restaurantDomain {

        private String title;
        private Drawable pic;

        public restaurantDomain(){}
        public restaurantDomain(String title, Drawable pic) {
            this.title = title;
            this.pic = pic;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Drawable getPic() {
            return pic;
        }

        public void setPic(Drawable pic) {
            this.pic = pic;
        }
    }


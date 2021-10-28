package com.clickandeat.finalproject5.Model;

public class categoryModel {


        private String title;
        private String pic;

        public categoryModel(String title, String pic) {
            this.title = title;
            this.pic = pic;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }


}

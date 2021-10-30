package anningtex.entity;

import java.util.List;

/**
 * 获取花布图片详情
 */
public class OrderPicEntity {
    private boolean is_have_finished;
    private String ggy;
    private List<ImageListBean> image_list;

    public boolean isIs_have_finished() {
        return is_have_finished;
    }

    public void setIs_have_finished(boolean is_have_finished) {
        this.is_have_finished = is_have_finished;
    }

    public String getGgy() {
        return ggy;
    }

    public void setGgy(String ggy) {
        this.ggy = ggy;
    }

    public List<ImageListBean> getImage_list() {
        return image_list;
    }

    public void setImage_list(List<ImageListBean> image_list) {
        this.image_list = image_list;
    }

    public static class ImageListBean {
        /**
         * OFLID : 75599
         * request_content : 花型参照图片,配色参照色卡:C18019,C19018,WHITE,BLACK
         * flower_no : AF1507004A
         * flower_url_original : https://www.africatex.com:706/order_flower_make_image/org/20210802/e59c015779513fb19c58afcb5b414063.jpg
         * flower_url_medium : https://www.africatex.com:706/order_flower_make_image/medium/20210802/e59c015779513fb19c58afcb5b414063.jpg
         * flower_url_mini : https://www.africatex.com:706/order_flower_make_image/mini/20210802/e59c015779513fb19c58afcb5b414063.jpg
         * color_url_original :
         * color_url_medium :
         * color_url_mini :
         * flower_finished_url :
         * drawing_image_url :
         * color_card_list : [{"id":2719,"type":1,"color_code":"BLACK","color_name":"BLACK","system_id":45,"image_url":"","md5":"5f866a3aced23c882487f7601d65323a","qr_img":"/temp_QR/5f866a3aced23c882487f7601d65323a.png","palette":[{"id":2719,"element_id":2719,"color_id":2719,"color":{"id":2719,"type":1,"color_code":"BLACK","color_name":"BLACK","html_color":"000000","rgb_r":0,"rgb_g":0,"rgb_b":0}}],"color_system":{"id":45,"name":"黑色"}},{"id":2572,"type":1,"color_code":"C18019","color_name":"C18019","system_id":31,"image_url":"","md5":"0beb8f54c87ff2e0d4c3205f618e1a57","qr_img":"/temp_QR/0beb8f54c87ff2e0d4c3205f618e1a57.png","palette":[{"id":2572,"element_id":2572,"color_id":2572,"color":{"id":2572,"type":1,"color_code":"C18019","color_name":"C18019","html_color":"769531","rgb_r":118,"rgb_g":149,"rgb_b":49}}],"color_system":{"id":31,"name":"C18"}},{"id":2596,"type":1,"color_code":"C19018","color_name":"C19018","system_id":32,"image_url":"","md5":"d2cf73db26936fe093739566bba56ed5","qr_img":"/temp_QR/d2cf73db26936fe093739566bba56ed5.png","palette":[{"id":2596,"element_id":2596,"color_id":2596,"color":{"id":2596,"type":1,"color_code":"C19018","color_name":"C19018","html_color":"2A75B8","rgb_r":42,"rgb_g":117,"rgb_b":184}}],"color_system":{"id":32,"name":"C19"}},{"id":2720,"type":1,"color_code":"WHITE","color_name":"WHITE","system_id":46,"image_url":"","md5":"387171d16cc7eaf042b954aec8d66b32","qr_img":"/temp_QR/387171d16cc7eaf042b954aec8d66b32.png","palette":[{"id":2720,"element_id":2720,"color_id":2720,"color":{"id":2720,"type":1,"color_code":"WHITE","color_name":"WHITE","html_color":"FFFFFF","rgb_r":255,"rgb_g":255,"rgb_b":255}}],"color_system":{"id":46,"name":"白色"}}]
         */

        private String seq;
        private int OFLID;
        private String request_content;
        private String flower_no;
        private String flower_url_original;
        private String flower_url_medium;
        private String flower_url_mini;
        private String color_url_original;
        private String color_url_medium;
        private String color_url_mini;
        private String flower_finished_url;
        private String drawing_image_url;
        private List<ColorCardListBean> color_card_list;

        public String getSeq() {
            return seq;
        }

        public void setSeq(String seq) {
            this.seq = seq;
        }

        public int getOFLID() {
            return OFLID;
        }

        public void setOFLID(int OFLID) {
            this.OFLID = OFLID;
        }

        public String getRequest_content() {
            return request_content;
        }

        public void setRequest_content(String request_content) {
            this.request_content = request_content;
        }

        public String getFlower_no() {
            return flower_no;
        }

        public void setFlower_no(String flower_no) {
            this.flower_no = flower_no;
        }

        public String getFlower_url_original() {
            return flower_url_original;
        }

        public void setFlower_url_original(String flower_url_original) {
            this.flower_url_original = flower_url_original;
        }

        public String getFlower_url_medium() {
            return flower_url_medium;
        }

        public void setFlower_url_medium(String flower_url_medium) {
            this.flower_url_medium = flower_url_medium;
        }

        public String getFlower_url_mini() {
            return flower_url_mini;
        }

        public void setFlower_url_mini(String flower_url_mini) {
            this.flower_url_mini = flower_url_mini;
        }

        public String getColor_url_original() {
            return color_url_original;
        }

        public void setColor_url_original(String color_url_original) {
            this.color_url_original = color_url_original;
        }

        public String getColor_url_medium() {
            return color_url_medium;
        }

        public void setColor_url_medium(String color_url_medium) {
            this.color_url_medium = color_url_medium;
        }

        public String getColor_url_mini() {
            return color_url_mini;
        }

        public void setColor_url_mini(String color_url_mini) {
            this.color_url_mini = color_url_mini;
        }

        public String getFlower_finished_url() {
            return flower_finished_url;
        }

        public void setFlower_finished_url(String flower_finished_url) {
            this.flower_finished_url = flower_finished_url;
        }

        public String getDrawing_image_url() {
            return drawing_image_url;
        }

        public void setDrawing_image_url(String drawing_image_url) {
            this.drawing_image_url = drawing_image_url;
        }

        public List<ColorCardListBean> getColor_card_list() {
            return color_card_list;
        }

        public void setColor_card_list(List<ColorCardListBean> color_card_list) {
            this.color_card_list = color_card_list;
        }

        public static class ColorCardListBean {
            /**
             * id : 2719
             * type : 1
             * color_code : BLACK
             * color_name : BLACK
             * system_id : 45
             * image_url :
             * md5 : 5f866a3aced23c882487f7601d65323a
             * qr_img : /temp_QR/5f866a3aced23c882487f7601d65323a.png
             * palette : [{"id":2719,"element_id":2719,"color_id":2719,"color":{"id":2719,"type":1,"color_code":"BLACK","color_name":"BLACK","html_color":"000000","rgb_r":0,"rgb_g":0,"rgb_b":0}}]
             * color_system : {"id":45,"name":"黑色"}
             */

            private int id;
            private int type;
            private String color_code;
            private String color_name;
            private int system_id;
            private String image_url;
            private String md5;
            private String qr_img;
            private ColorSystemBean color_system;
            private List<PaletteBean> palette;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getColor_code() {
                return color_code;
            }

            public void setColor_code(String color_code) {
                this.color_code = color_code;
            }

            public String getColor_name() {
                return color_name;
            }

            public void setColor_name(String color_name) {
                this.color_name = color_name;
            }

            public int getSystem_id() {
                return system_id;
            }

            public void setSystem_id(int system_id) {
                this.system_id = system_id;
            }

            public String getImage_url() {
                return image_url;
            }

            public void setImage_url(String image_url) {
                this.image_url = image_url;
            }

            public String getMd5() {
                return md5;
            }

            public void setMd5(String md5) {
                this.md5 = md5;
            }

            public String getQr_img() {
                return qr_img;
            }

            public void setQr_img(String qr_img) {
                this.qr_img = qr_img;
            }

            public ColorSystemBean getColor_system() {
                return color_system;
            }

            public void setColor_system(ColorSystemBean color_system) {
                this.color_system = color_system;
            }

            public List<PaletteBean> getPalette() {
                return palette;
            }

            public void setPalette(List<PaletteBean> palette) {
                this.palette = palette;
            }

            public static class ColorSystemBean {
                /**
                 * id : 45
                 * name : 黑色
                 */

                private int id;
                private String name;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }
            }

            public static class PaletteBean {
                /**
                 * id : 2719
                 * element_id : 2719
                 * color_id : 2719
                 * color : {"id":2719,"type":1,"color_code":"BLACK","color_name":"BLACK","html_color":"000000","rgb_r":0,"rgb_g":0,"rgb_b":0}
                 */

                private int id;
                private int element_id;
                private int color_id;
                private ColorBean color;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public int getElement_id() {
                    return element_id;
                }

                public void setElement_id(int element_id) {
                    this.element_id = element_id;
                }

                public int getColor_id() {
                    return color_id;
                }

                public void setColor_id(int color_id) {
                    this.color_id = color_id;
                }

                public ColorBean getColor() {
                    return color;
                }

                public void setColor(ColorBean color) {
                    this.color = color;
                }

                public static class ColorBean {
                    /**
                     * id : 2719
                     * type : 1
                     * color_code : BLACK
                     * color_name : BLACK
                     * html_color : 000000
                     * rgb_r : 0
                     * rgb_g : 0
                     * rgb_b : 0
                     */

                    private int id;
                    private int type;
                    private String color_code;
                    private String color_name;
                    private String html_color;
                    private int rgb_r;
                    private int rgb_g;
                    private int rgb_b;

                    public int getId() {
                        return id;
                    }

                    public void setId(int id) {
                        this.id = id;
                    }

                    public int getType() {
                        return type;
                    }

                    public void setType(int type) {
                        this.type = type;
                    }

                    public String getColor_code() {
                        return color_code;
                    }

                    public void setColor_code(String color_code) {
                        this.color_code = color_code;
                    }

                    public String getColor_name() {
                        return color_name;
                    }

                    public void setColor_name(String color_name) {
                        this.color_name = color_name;
                    }

                    public String getHtml_color() {
                        return html_color;
                    }

                    public void setHtml_color(String html_color) {
                        this.html_color = html_color;
                    }

                    public int getRgb_r() {
                        return rgb_r;
                    }

                    public void setRgb_r(int rgb_r) {
                        this.rgb_r = rgb_r;
                    }

                    public int getRgb_g() {
                        return rgb_g;
                    }

                    public void setRgb_g(int rgb_g) {
                        this.rgb_g = rgb_g;
                    }

                    public int getRgb_b() {
                        return rgb_b;
                    }

                    public void setRgb_b(int rgb_b) {
                        this.rgb_b = rgb_b;
                    }
                }
            }
        }
    }
}
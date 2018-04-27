package universal.universalthought.model;

/**
 * Created by Sandhiya on 8/29/2017.
 */

public class ProductEnglish {

    private String products, productimage;
     String pname;
     int pimage;
     String pprice;
     String pquantity;
    int pid;
    String id,titleoffundraising,photo,amount,city;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitleoffundraising() {
        return titleoffundraising;
    }

    public void setTitleoffundraising(String titleoffundraising) {
        this.titleoffundraising = titleoffundraising;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public ProductEnglish() {
    }

    public ProductEnglish(String name, String thumbnailUrl) {
        this.products = name;
        this.productimage = thumbnailUrl;
    }

    public ProductEnglish(String s, String i, int cover, String q) {

        this.pname = s;
        this.pprice = i;
        this.pimage = cover;
        this.pquantity = q;

    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getProducts() {
        return products;
    }

    public void setProducts(String products) {
        this.products = products;
    }

    public String getProductimage() {
        return productimage;
    }

    public void setProductimage(String productimage) {
        this.productimage = productimage;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }


    public int getPimage() {
        return pimage;
    }

    public void setPimage(int pimage) {
        this.pimage = pimage;
    }

    public String getPprice() {
        return pprice;
    }

    public void setPprice(String pprice) {
        this.pprice = pprice;
    }

    public String getPquantity() {
        return pquantity;
    }

    public void setPquantity(String pquantity) {
        this.pquantity = pquantity;
    }
}

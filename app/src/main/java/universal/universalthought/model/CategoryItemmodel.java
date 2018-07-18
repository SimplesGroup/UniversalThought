package universal.universalthought.model;

/**
 * Created by Kuppusamy on 4/12/2018.
 */

public class CategoryItemmodel {
    String id,titleoffundraising,photo,amount,city,formoneid;

    public String getFormoneid() {
        return formoneid;
    }

    public void setFormoneid(String formoneid) {
        this.formoneid = formoneid;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getTitleoffundraising() {
        return titleoffundraising;
    }

    public void setTitleoffundraising(String titleoffundraising) {
        this.titleoffundraising = titleoffundraising;
    }
}

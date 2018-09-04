package universal.universalthought.model;

/**
 * Created by Kuppusamy on 4/12/2018.
 */

public class CategoryItemmodel {
    String id,titleoffundraising,photo, amountraised,city,formoneid,raisingamount,name,url;

    public String getFormoneid() {
        return formoneid;
    }

    public void setFormoneid(String formoneid) {
        this.formoneid = formoneid;
    }

    public String getAmountraised() {
        return amountraised;
    }

    public void setAmountraised(String amountraised) {
        this.amountraised = amountraised;
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

    public String getRaisingamount() {
        return raisingamount;
    }

    public void setRaisingamount(String raisingamount) {
        this.raisingamount = raisingamount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

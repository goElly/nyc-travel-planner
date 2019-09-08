package sp.senior.wd..Entity;

import java.util.Vector;

public class Attraction {
    private Vector title = new Vector();
    private Vector rating = new Vector();
    private Vector price = new Vector();
    private Vector hours = new Vector();
    private Vector address = new Vector();
    private Vector tags = new Vector();

    public Vector getTags() {
        return tags;
    }

    public void setTags(Vector tags) {
        this.tags = tags;
    }

    public Vector getTitle() {
        return title;
    }

    public void setTitle(Vector title) {
        this.title = title;
    }

    public Vector getRating() {
        return rating;
    }

    public void setRating(Vector rating) {
        this.rating = rating;
    }

    public Vector getPrice() {
        return price;
    }

    public void setPrice(Vector price) {
        for (int i = 0; i < price.size(); i ++){
            if(price.get(i).equals("1"))
                price.set(i, "$");
            else if(price.get(i).equals("2"))
                price.set(i, "$$");
            else if(price.get(i).equals("3"))
                price.set(i, "$$$");
            else if(price.get(i).equals("4"))
                price.set(i, "$$$$");
        }
        this.price = price;
    }

    public Vector getHours() {
        return hours;
    }

    public void setHours(Vector hours) {
        this.hours = hours;
    }

    public Vector getAddress() {
        return address;
    }

    public void setAddress(Vector address) {
        this.address = address;
    }

}

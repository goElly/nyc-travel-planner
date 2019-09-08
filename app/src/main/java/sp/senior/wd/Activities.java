package sp.senior.wd;
public  class Activities {
    private String name;
    private int imageId;
    public Activities(String name,int imageId){
        this.name=name;
        this.imageId=imageId;
    }
    public String getName(){
        return name;
    }
    public int getImageId(){
        return imageId;
    }
}

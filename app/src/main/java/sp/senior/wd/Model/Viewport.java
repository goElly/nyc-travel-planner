package sp.senior.wd.Model;

public class Viewport
{
    private com.example.myapplication.Model.Southwest southwest;

    private Northeast northeast;

    public com.example.myapplication.Model.Southwest getSouthwest ()
    {
        return southwest;
    }

    public void setSouthwest (com.example.myapplication.Model.Southwest southwest)
    {
        this.southwest = southwest;
    }

    public Northeast getNortheast ()
    {
        return northeast;
    }

    public void setNortheast (Northeast northeast)
    {
        this.northeast = northeast;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [southwest = "+southwest+", northeast = "+northeast+"]";
    }
}


package imagebrowser.Model;

import java.awt.image.BufferedImage;


public abstract class Image {

    public abstract Dimension getDimension();
    
    public abstract BufferedImage getImage();

    public abstract Image getNext();

    public abstract Image getPrev();

    public abstract void setNext(Image image);

    public abstract void setPrev(Image image);
    
}

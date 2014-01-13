package imagebrowser.Model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class RealImage extends Image {

    public Image next;
    public Image prev;
    public Dimension dimension;
    File file;
    BufferedImage image = null;

    public RealImage(File file) throws IOException {
        this.file = file;
        image = ImageIO.read(file);
    }

    public RealImage(Dimension dimension) {
        this.dimension = dimension;
    }

    @Override
    public Dimension getDimension() {
        return dimension;
    }

    @Override
    public Image getNext() {
        return next;
    }

    @Override
    public Image getPrev() {
        return prev;
    }

    @Override
    public void setNext(Image next) {
        this.next = next;
    }

    @Override
    public void setPrev(Image prev) {
        this.prev = prev;
    }

    @Override
    public BufferedImage getImage() {
        return image;
    }
}
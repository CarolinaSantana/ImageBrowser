package imagebrowser;

import imagebrowser.Control.ApplicationFrame;
import imagebrowser.Control.NextImageCommand;
import imagebrowser.Control.PrevImageCommand;
import imagebrowser.Model.Image;
import imagebrowser.Persistence.ImageLoader;
import imagebrowser.Persistence.ProxyImage;
import imagebrowser.Model.RealImage;
import imagebrowser.View.ImageViewer;
import imagebrowser.View.ImageViewerPanel;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    String path = "C:\\Users\\Public\\Pictures\\t";
    File folder = new File(path);
    File[] list = folder.listFiles();

    public static void main(String[] args) throws IOException {
        new Main().execute();
    }

    private void execute() throws IOException {
        Image[] images = linkImages(createImages());
        ImageViewer viewer = createImageViewer(images[0]);
        createApplicationFrame(createCommands(viewer));
    }

     private Image[] createImages() throws IOException{
        Image[] images = new Image[numberOfImages()];
        for (int i = 0, j = 0; i < list.length; i++) {
           if (isImage(list[i])){
               images[j]=createImage(list[i]);
               j++;
           }
        }
        return images;
    }
   private Image createImage(final File file){
        return new ProxyImage(new ImageLoader() {

            @Override
            public Image load() {
                try {
                    return new RealImage(file);
                } catch (IOException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
                return null;
            }
        });
    }
    private Image[] linkImages(Image[] images) {
        for (int i = 0; i < images.length; i++) {
            Image image = images[i];
            Image next = images[(i + 1) % images.length];
            Image prev = images[(i + images.length - 1) % images.length];
            image.setNext(next);
            image.setPrev(prev);
        }
        return images;
    }

    private ImageViewer createImageViewer(Image image) {
        ImageViewer viewer = new ImageViewerPanel();
        viewer.setImage(image);
        return viewer;
    }

    private ApplicationFrame createApplicationFrame(ActionListener[] listeners) {
        return new ApplicationFrame(listeners);
    }

    private ActionListener[] createCommands(ImageViewer viewer) {
        return new ActionListener[]{
            new PrevImageCommand(viewer),
            new NextImageCommand(viewer)
        };
    }
     private int numberOfImages() throws IOException {
        int count=0;
        for (int i = 0; i < list.length; i++) {
            if (isImage(list[i])) count++;
        }
        return count;
    }

    private boolean isImage(File file) throws IOException {
        String type = Files.probeContentType(file.toPath());
        if(type != null && type.startsWith("image")) return true;
        else return false;
    }
}

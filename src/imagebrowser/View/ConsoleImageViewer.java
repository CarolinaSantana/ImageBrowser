package imagebrowser.View;

public class ConsoleImageViewer extends ImageViewer {
    
   @Override
    public void refresh() {
        System.out.println(getImage().getImage());
    }


}
package prak_27_28;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Minimum implements Runnable{
    public Foto foto;
    public String dstFolder;

    public Minimum(Foto foto, String dstFolder)
    {
        this.foto = foto;
        this.dstFolder = dstFolder;
    }

    public void run()
    {
        synchronized (foto) {
            for(File file : foto.getFiles()) {
                BufferedImage image = null;
                try {
                    image = ImageIO.read(file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if(image == null) {
                    continue;
                }
                int newWidth = image.getWidth() / 2;
                int newHeight = (int) Math.round(
                        image.getHeight() / (image.getWidth() / (double) newWidth)
                );
                BufferedImage newImage = new BufferedImage(
                        newWidth, newHeight, BufferedImage.TYPE_INT_RGB
                );

                int widthStep = image.getWidth() / newWidth;
                int heightStep = image.getHeight() / newHeight;

                for (int x = 0; x < newWidth; x++) {
                    for (int y = 0; y < newHeight; y++) {
                        int rgb = image.getRGB(x * widthStep, y * heightStep);
                        newImage.setRGB(x, y, rgb);
                    }
                }

                File newFile = new File(dstFolder + "/" + file.getName());
                try {
                    ImageIO.write(newImage, "jpg", newFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        try{
            Thread.sleep(100);
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}
import java.util.Scanner;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Color;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the path to the image file:");
        String imagePath = scanner.nextLine();

        try {
            File file = new File(imagePath);
            BufferedImage image = ImageIO.read(file);

            if (image != null) {
                System.out.println("Image loaded successfully!");
                // Add your image processing logic here if needed

            } else {
                System.out.println("Failed to load the image. Please check the file path.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the image file: " + e.getMessage());
        } finally {
            scanner.close();
        }

    }

public static void hideMessage(BufferedImage image,String message ){
    int pixelIndex=0;
    int x = pixelIndex % image.getWidth();
    int y = pixelIndex / image.getWidth();
    int color = image.getRGB(x,y);
    int blue = color & 0xff;
    int green = (color & 0xff00) >> 8;
    int red = (color & 0xff0000) >> 16;

    Color setColor = new Color(255,255,255);

    System.out.println(blue);
    System.out.println(green);
    System.out.println(red);

    image.setRGB(0, 0, setColor.getRGB());

    color = image.getRGB(0, 0);
    blue = color & 0xff;
    green = (color & 0xff00) >> 8;
    red = (color & 0xff0000) >> 16;

    System.out.println(blue);
    System.out.println(green);
    System.out.println(red);

}
}
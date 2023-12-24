
import java.util.HashMap;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Encode {

    //Message length can't be greater than 100
    final int messageLength = 100;

    public void hideText(BufferedImage img, String text) {
        HashMap<String, Integer> map = new HashMap<>();
        map.put(" ", 0); map.put("c", 1); map.put("2", 2); map.put("h", 3); map.put("i", 4); map.put("j", 5); map.put("!", 6); map.put("s", 7);
        map.put("\"", 8); map.put("#", 9); map.put("$", 10); map.put("g", 11); map.put("%", 12); map.put("&", 13); map.put("t", 14); map.put("r", 15);
        map.put("'", 16); map.put("b", 17); map.put("(", 18); map.put("f", 19); map.put(")", 20); map.put("*", 21); map.put("u", 22); map.put("+", 23);
        map.put("a", 24); map.put("6", 25); map.put("p", 26); map.put("e", 27); map.put(",", 28); map.put("x", 29); map.put("v", 30); map.put("-", 31);
        map.put(".", 32); map.put("/", 33); map.put("o", 34); map.put("1", 35); map.put(":", 36); map.put("3", 37); map.put("q", 38); map.put(";", 39);
        map.put("<", 40); map.put("n", 41); map.put("=", 42); map.put("d", 43); map.put("7", 44); map.put("8", 45); map.put("k", 46); map.put("5", 47);
        map.put(">", 48); map.put("z", 49); map.put("0", 50); map.put("?", 51); map.put("@", 52); map.put("[", 53); map.put("9", 54); map.put("\\", 55);
        map.put("m", 56); map.put("l", 57); map.put("]", 58); map.put("^", 59); map.put("y", 60); map.put("4", 61); map.put("_", 62); map.put("", 63);

        int[] startingPoints = Detection(img);


        int index = 0;

        if(text.length()<=messageLength) {
            for(int i=startingPoints[1]; index<text.length() && i<img.getHeight(); i++) {
                for(int j=startingPoints[0]; index<text.length() && j<img.getWidth(); j++) {
                    //img.setRGB(j, i, newColor(img.getRGB(j, i)& 0xff0000 >> 16, img.getRGB(j, i)& 0xff00 >> 8, img.getRGB(j,i)& 0xff, map.get(text.substring(index,index+1))).getRGB());
                    img.setRGB(j, i, newColor(img.getRGB(j, i), map.get(text.substring(index, index+1))).getRGB());
                    index++;

                    if((index==text.length()) && (j+1 != img.getWidth())) img.setRGB(j+1, i, newColor(img.getRGB(j+1, i), 63).getRGB());
                    if((index==text.length()) && (j+1 == img.getWidth())) img.setRGB(0, i+1, newColor(img.getRGB(0, i+1), 63).getRGB());

                    System.out.println(j + " , " + i);
                }
                startingPoints[0] = 0;
            }
        }

        try {
            File output = new File("image.png");
            ImageIO.write(img, "png", output);
        } catch (IOException e) {System.out.println("An error occured!" + e.getMessage());}
    }

    //Checking the image for a suitable starting point and if not creating it
    private int[] Detection(BufferedImage img) {

        for(int i=0; i<img.getHeight()-1; i++) {
            for(int j=0; j<img.getWidth(); j++) {
                //int color = img.getRGB(j,i);
                //int red = color & 0xff0000 >> 16;
                //int green = color & 0xff00 >> 8;
                //int blue = color & 0xff;

                int red = new Color(img.getRGB(j,i), true).getRed() & 0b11;
                int green = new Color(img.getRGB(j, i), true).getGreen() & 0b11;
                int blue = new Color(img.getRGB(j, i), true).getBlue() & 0b11;

                red = red & 0b11;
                green = green & 0b11;
                blue = blue &0b11;

                if(((red == 3) && (green == 3) && (blue == 3)) && (j!=img.getWidth()-1)) return new int[] {j+1, i};
                else if(((red == 3) && (green == 3) && (blue == 3)) && (j==img.getWidth()-1)) return new int[] {0, i+1};
            }
        }

        return new int[] {0,0};
    }

    //Changing the RGB values to hide characters in them
    private Color newColor(int RGB, int key) {
        int newR = new Color(RGB, true).getRed() & 0b11111100;
        int newG = new Color(RGB, true).getGreen() & 0b11111100;
        int newB = new Color(RGB, true).getBlue() & 0b11111100;

        if(key >= 32) {newR += 2; key -= 32;}
        if(key >= 16) {newR += 1; key -= 16;}
        if(key >= 8) {newG += 2; key -= 8;}
        if(key >= 4) {newG += 1; key -= 4;}
        if(key >= 2) {newB += 2; key -= 2;}
        if(key >= 1) {newB += 1; key -= 1;}

        return new Color(newR, newG, newB);
    }
}

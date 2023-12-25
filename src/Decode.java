
import javax.swing.*;
import java.util.HashMap;
import java.awt.image.BufferedImage;
import java.awt.Color;


public class Decode {
    private JTextField outputTextArea;
    public Decode(JTextField textField){
        this.outputTextArea=textField;
    }

    public Decode() {

    }


    public void showText(BufferedImage img) {
        String decodedText=findText(img);
        if (outputTextArea!=null){
            outputTextArea.setText(decodedText);
        }

    }

    private String findText(BufferedImage img) {
        HashMap<Integer, String> map = new HashMap<>();
        map.put(0, " "); map.put(1, "c"); map.put(2, "2"); map.put(3, "h"); map.put(4, "i"); map.put(5, "j"); map.put(6, "!"); map.put(7, "s");
        map.put(8, "\""); map.put(9, "#"); map.put(10, "$"); map.put(11, "g"); map.put(12, "%"); map.put(13, "&"); map.put(14, "t"); map.put(15, "r");
        map.put(16, "'"); map.put(17, "b"); map.put(18, "("); map.put(19, "f"); map.put(20, ")"); map.put(21, "*"); map.put(22, "u"); map.put(23, "+");
        map.put(24, "a"); map.put(25, "6"); map.put(26, "p"); map.put(27, "e"); map.put(28, ","); map.put(29, "x"); map.put(30, "v"); map.put(31, "-");
        map.put(32, "."); map.put(33, "/"); map.put(34, "o"); map.put(35, "1"); map.put(36, ":"); map.put(37, "3"); map.put(38, "q"); map.put(39, ";");
        map.put(40, "<"); map.put(41, "n"); map.put(42, "="); map.put(43, "d"); map.put(44, "7"); map.put(45, "8"); map.put(46, "k"); map.put(47, "5");
        map.put(48, ">"); map.put(49, "z"); map.put(50, "0"); map.put(51, "?"); map.put(52, "@"); map.put(53, "["); map.put(54, "9"); map.put(55, "\\");
        map.put(56, "m"); map.put(57, "l"); map.put(58, "]"); map.put(59, "^"); map.put(60, "y"); map.put(61, "4"); map.put(62, "_"); map.put(63, "");

        String text = "";
        boolean process = false;

        for(int i=0; i<img.getHeight(); i++) {
            for(int j=0; j<img.getWidth(); j++) {
                if(process) {text += map.get(binaryToCharacter(img.getRGB(j, i))); if(binaryToCharacter(img.getRGB(j, i)) == 63) {i = img.getHeight(); j = img.getWidth();}}

                if(process == false) {if(Detection(img, j, i)) {process = true;}};
            }
        }

        return text;
    }

    private boolean Detection(BufferedImage img, int posX, int posY) {


        int red = new Color(img.getRGB(posX, posY), true).getRed();
        int green = new Color(img.getRGB(posX, posY), true).getGreen();
        int blue = new Color(img.getRGB(posX, posY), true).getBlue();

        red = red & 0b11;
        green = green & 0b11;
        blue = blue & 0b11;

        if((red == 3) && (green == 3) && (blue == 3)) return true;

        return false;
    }

    private int binaryToCharacter(int RGB) {
        int value = 0;



        int red = new Color(RGB, true).getRed();
        int green = new Color(RGB, true).getGreen();
        int blue = new Color(RGB, true).getBlue();

        red = red & 0b11;
        green = green & 0b11;
        blue = blue & 0b11;

        if(red>1) { value += 32; red -= 2;}
        if(red>0) { value += 16; red -= 1;}
        if(green>1) { value += 8; green -= 2;}
        if(green>0) { value += 4; green -= 1;}
        if(blue>1) { value += 2; blue -= 2;}
        if(blue>0) { value += 1; blue -= 1;}

        return value;
    }

}

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.*;

public class MyPanel extends JPanel {
    private JButton encodeButton;
    private JButton decodeButton;
    private JButton uploadImage;
    private JTextField uploadDestination;
    private JButton downloadImage;
    private JTextField downloadDestination;
    private JLabel txto;
    private JTextField textArea;

    private JButton presstoEncode;

    private  JMenuBar help;
    private JButton presstoDecode;

    private JButton completeButton;

    private BufferedImage image;

    public MyPanel() {
        encodeButton = new JButton ("ENCODE");
        decodeButton = new JButton ("DECODE");
        uploadImage = new JButton ("Upload Image");
        uploadDestination = new JTextField (5);
        downloadImage = new JButton ("Download Image");
        downloadDestination = new JTextField (5);
        txto = new JLabel ("Choose Encode or Decode");
        textArea = new JTextField (5);
        presstoEncode= new JButton("Press");
        presstoEncode.setVisible(false);
        presstoDecode= new JButton("Press");
        presstoDecode.setVisible(false);
        completeButton=new JButton("Complete");
        completeButton.setVisible(false);

        textArea.setEnabled (false);

        JMenu helpMenu = new JMenu("Help");
        JMenuItem how=new JMenuItem("How to Use");
        helpMenu.add(how);
        JMenuItem createdby=new JMenuItem("Created by");
        helpMenu.add(createdby);
        help=new JMenuBar();
        help.add(helpMenu);

        setPreferredSize (new Dimension (665, 517));
        setLayout (null);

        add (encodeButton);
        add (decodeButton);
        add (uploadImage);
        add (uploadDestination);
        add (downloadImage);
        add (downloadDestination);
        add (txto);
        add (textArea);
        add (presstoEncode);
        add(presstoDecode);
        add(help);
        add(completeButton);

        encodeButton.setBounds (100, 50, 150, 150);
        decodeButton.setBounds (400, 50, 150, 150);
        uploadImage.setBounds (100, 430, 175, 25);
        uploadDestination.setBounds (285, 430, 270, 25);
        downloadImage.setBounds (100, 460, 175, 25);
        downloadDestination.setBounds (285, 460, 270, 25);
        txto.setBounds (100, 220, 300, 30);
        textArea.setBounds (100, 250, 450, 150);
        presstoEncode.setBounds(200,405,200,20);
        help.setBounds(0,0,100,25);
        presstoDecode.setBounds(200,405,200,20);
        completeButton.setBounds(475, 405, 100, 20);





        encodeButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            txto.setText("Text to Encode : ");
            textArea.setEnabled(true);
            downloadImage.setVisible(true);
            downloadDestination.setVisible(true);
            presstoEncode.setVisible(true);
            presstoEncode.setText("Press to Encode");
            presstoDecode.setVisible(false);
            completeButton.setVisible(true);



        }
    });
    decodeButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            txto.setText("Decoded Text : ");
            downloadImage.setVisible(false);
            downloadDestination.setVisible(false);
            textArea.setEnabled(false);
            presstoDecode.setVisible(true);
            presstoDecode.setText("Press to Decode");
            presstoEncode.setVisible(false);
            completeButton.setVisible(false);
            textArea.setText(" ");

        }
    });


        uploadImage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser(System.getProperty("user.home") + "/Desktop");
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();

                    BufferedImage image= null;
                    try {
                        image = ImageIO.read(selectedFile);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    int widht = image.getWidth();
                    int height=image.getHeight();
                    if (widht<100||height<100){
                        JOptionPane.showMessageDialog(null, "Error: The selected image is smaller than 100x100 pixels.",
                                "Image Size Error", JOptionPane.ERROR_MESSAGE);
                    }
                    else {
                        uploadDestination.setText(((File) selectedFile).getAbsolutePath());

                    }
                }
            }
        });
        downloadImage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser(System.getProperty("user.home") + "/Desktop");
                int returnValue = fileChooser.showSaveDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();

                    downloadDestination.setText(((File) selectedFile).getAbsolutePath());
                }
            }
        });
        createdby.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String infoMessage = "Ayberk Aydın\nEren Bceren\nİbrahim Tarhan";
                JOptionPane.showMessageDialog(null,infoMessage,"Created by",JOptionPane.INFORMATION_MESSAGE);

            }
        });
        how.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String infoMessage = "To embed a hidden message into an image, click on the button labeled ENCODE, upload an image into the application, type the message you want to hide, and then press the 'Press to Encode' button.\n To decode a hidden message from an image, click on the button labeled DECODE, upload the image containing the hidden message into the application, and then press the 'Press to Decode' button. ";
                JOptionPane.showMessageDialog(null,infoMessage,"How to Use",JOptionPane.INFORMATION_MESSAGE);

            }
        });
        presstoDecode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String imagePath = uploadDestination.getText();
                if (!imagePath.isEmpty()) {
                    image = loadImage(imagePath);
                    Decode decoder=new Decode(textArea);
                    decoder.showText(image);
                } else {
                    String infoMessage = "Please upload an image first.";
                    JOptionPane.showMessageDialog(null, infoMessage, "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        });
        presstoEncode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textArea.getText().length()>100){
                    String message = "Maximum character limit exceeded (100 characters).";
                    JOptionPane.showMessageDialog(null, message, "Warning", JOptionPane.WARNING_MESSAGE);
                }
                else {
                String imagePath =uploadDestination.getText();
                String texttoEncode = textArea.getText();
                texttoEncode=texttoEncode.toLowerCase();
                if (!imagePath.isEmpty()&&!texttoEncode.isEmpty()){
                    image=loadImage(imagePath);
                    Encode encoder = new Encode();
                    encoder.hideText(image,texttoEncode);
                }}

            }
        });
        completeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String savePath=downloadDestination.getText();
                if (image !=null && !savePath.isEmpty()){
                    saveImage(image,savePath);
                    textArea.setText(" ");
                    uploadDestination.setText(" ");
                    downloadDestination.setText(" ");
                    String infoMessage = "Succesfully Completed";
                    JOptionPane.showMessageDialog(null,infoMessage," ",JOptionPane.INFORMATION_MESSAGE);

                }
                else if (image!=null&&savePath.isEmpty()){
                    String infoMessage = "Download Destination is EMPTY !!!";
                    JOptionPane.showMessageDialog(null,infoMessage,"ERROR",JOptionPane.INFORMATION_MESSAGE);
                } else if (image==null&&!savePath.isEmpty()) {
                    String infoMessage = "No Image";
                    JOptionPane.showMessageDialog(null,infoMessage,"ERROR",JOptionPane.INFORMATION_MESSAGE);


                }
                else {
                    String infoMessage = "Try Again";
                    JOptionPane.showMessageDialog(null,infoMessage,"ERROR",JOptionPane.INFORMATION_MESSAGE);

                }
            }
        });
    }
    private static BufferedImage loadImage(String imagePath){
        BufferedImage img=null;
        try {
            img= ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return img;
    }


    private void saveImage(BufferedImage image,String savePath){
        try {
            File output = new File(savePath);
            if (!savePath.toLowerCase().endsWith(".png")) {
                output = new File(savePath + ".png");
            }
            ImageIO.write(image,"png",output);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static void main (String[] args) {
        JFrame frame = new JFrame ("Encryption");
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add (new MyPanel());
        frame.pack();
        frame.setVisible (true);
    }
}

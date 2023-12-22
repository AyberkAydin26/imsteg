import java.awt.*;
import java.awt.event.*;
import java.io.File;
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

    public MyPanel() {
        encodeButton = new JButton ("ENCODE");
        decodeButton = new JButton ("DECODE");
        uploadImage = new JButton ("Upload Image");
        uploadDestination = new JTextField (5);
        downloadImage = new JButton ("Download Image");
        downloadDestination = new JTextField (5);
        txto = new JLabel ("Choose Encode or Decode");
        textArea = new JTextField (5);

        textArea.setEnabled (false);

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

        encodeButton.setBounds (100, 50, 150, 150);
        decodeButton.setBounds (400, 50, 150, 150);
        uploadImage.setBounds (100, 430, 175, 25);
        uploadDestination.setBounds (285, 430, 270, 25);
        downloadImage.setBounds (100, 460, 175, 25);
        downloadDestination.setBounds (285, 460, 270, 25);
        txto.setBounds (100, 220, 300, 30);
        textArea.setBounds (100, 250, 450, 150);



    encodeButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            txto.setText("Text to Encode : ");
            textArea.setEnabled(true);
            downloadImage.setVisible(true);
            downloadDestination.setVisible(true);


        }
    });
    decodeButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            txto.setText("Decoded Text : ");
            downloadImage.setVisible(false);
            downloadDestination.setVisible(false);
            textArea.setEnabled(false);

        }
    });


        uploadImage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser(System.getProperty("user.home") + "/Desktop");
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();

                    uploadDestination.setText(((File) selectedFile).getAbsolutePath());
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
    }


    public static void main (String[] args) {
        JFrame frame = new JFrame ("MyPanel");
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add (new MyPanel());
        frame.pack();
        frame.setVisible (true);
    }
}

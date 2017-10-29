/*
    LINKS TO ORIGINAL IMAGES:
    
    scared-kitten: http://www.petmd.com/sites/default/files/scared-kitten-shutterstock_191443322.jpg
    doge: http://i0.kym-cdn.com/entries/icons/mobile/000/013/564/doge.jpg
    giraffe: https://upload.wikimedia.org/wikipedia/commons/thumb/c/c0/Giraffe-Necking-Etosha.JPG/220px-Giraffe-Necking-Etosha.JPG
    rose: https://upload.wikimedia.org/wikipedia/commons/thumb/c/c7/Roses_Boutons_FR_2012.jpg/220px-Roses_Boutons_FR_2012.jpg
    daffodil: https://www.theflowerexpert.com/media/images/mostpopularflowers/morepopularflowers/daffodil/daffodil2.jpg
    cherry-blossom: https://upload.wikimedia.org/wikipedia/commons/thumb/1/11/Cherry_blossoms_in_Vancouver_3_crop.jpg/220px-Cherry_blossoms_in_Vancouver_3_crop.jpg
    apple: https://staticdelivery.nexusmods.com/mods/110/images/74627-0-1459502036.jpg
    banana: https://www.bbcgoodfood.com/sites/default/files/glossary/banana-crop.jpg
    orange: http://www.dropinbrewing.com/wp-content/uploads/2017/06/240_F_99562611_EIZ4AssrFTz1UNyOmM8KAChSeQNu5nMA.jpg
 */
package assignment6;

/**
 *
 * @author Eamonn Hannon
 */
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import javax.swing.*;
public class Slideshow extends JFrame{
    private int imageSize = 200;
    private int slideshowDelay = 1000;
    private int windowSize = 600;
    private int minimumWindowXSize = 600;
    private int minimumWindowYSize = 300;
    private JButton animalColourButton, flowerColourButton, foodColourButton;
    private JLabel imageLabel;//abuusive - images might be better
    private JComboBox pickSlideshow;
    private JPanel comboPanel, buttonPanel;
    private String dropDownNames[] = {"Animals", "Flowers", "Foods"};
    private String animalImagesNames[] = {"scared-kitten.jpg", "doge.jpg", "giraffe.JPG"};
    private String flowerImagesNames[] = {"rose.jpg", "daffodil.jpg", "cherry-blossom.jpg"};
    private String foodImagesNames[] = {"apple.jpg", "banana.jpg", "orange.jpg"};
    private Icon animalImages[] = {resizeImage(new ImageIcon(animalImagesNames[0])),
        resizeImage(new ImageIcon(animalImagesNames[1])), 
        resizeImage(new ImageIcon(animalImagesNames[2]))};
    private Icon flowerImages[] = {resizeImage(new ImageIcon(flowerImagesNames[0])), 
        resizeImage(new ImageIcon(flowerImagesNames[1])), 
        resizeImage(new ImageIcon(flowerImagesNames[2]))};
    private Icon foodImages[] = {resizeImage(new ImageIcon(foodImagesNames[0])), 
        resizeImage(new ImageIcon(foodImagesNames[1])), 
        resizeImage(new ImageIcon(foodImagesNames[2]))};
    private HashMap<String, Icon[]> slideshows;
    private Timer timer;
    private int loopValue = 0;
    private Icon currentSlideshow[];
    public Slideshow()
    {
        
        super("Assignment 6: Slideshows");
        currentSlideshow = animalImages;//default start
        timer = new Timer(slideshowDelay, new ActionListener()
                {
                    public void actionPerformed(ActionEvent e)
                    {
                        imageLabel.setIcon(currentSlideshow[loopValue]);
                        loopValue = (loopValue+1)% currentSlideshow.length;
                    }
                });
        animalColourButton = createBackgroundColourButton("Animal background colour", Color.BLUE);
        flowerColourButton = createBackgroundColourButton("Flower background colour", Color.BLACK);
        foodColourButton = createBackgroundColourButton("Food background colour", Color.RED);
        imageLabel = new JLabel(currentSlideshow[0]);
        slideshows = new HashMap<String, Icon[]>();
        slideshows.put(dropDownNames[0], animalImages);
        slideshows.put(dropDownNames[1], flowerImages);
        slideshows.put(dropDownNames[2], foodImages);
        
        pickSlideshow = new JComboBox(dropDownNames);//need images
        pickSlideshow.setMaximumRowCount(dropDownNames.length);
        pickSlideshow.addItemListener(
                new ItemListener()
                {
                    @Override
                    public void itemStateChanged(ItemEvent event)
                    {
                        if(event.getStateChange() == ItemEvent.SELECTED)
                        {
                            currentSlideshow = slideshows.get((String)pickSlideshow.getSelectedItem());
                            imageLabel.setIcon(currentSlideshow[0]);
                        }
                    }
                }
        );
        comboPanel = new JPanel();
        comboPanel.setLayout(new GridLayout(1, 1));
        comboPanel.add(pickSlideshow);
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 3));
        buttonPanel.add(animalColourButton);
        buttonPanel.add(flowerColourButton);
        buttonPanel.add(foodColourButton);
        Container container = getContentPane();
        container.add(comboPanel, BorderLayout.PAGE_START);
        container.add(imageLabel, BorderLayout.CENTER);
        container.add(buttonPanel, BorderLayout.PAGE_END);
        setSize(windowSize, windowSize);
        setMinimumSize(new Dimension(minimumWindowXSize, minimumWindowYSize));
        setVisible(true);
        timer.start();
    }
    public JButton createBackgroundColourButton
        (String buttonName, Color backgroundColour)
    {
        JButton button = new JButton(buttonName);
        button.addActionListener(
            new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent event)
                {
                    getContentPane().setBackground(backgroundColour);
                }
            }
        );
        return button;
    }
    public ImageIcon resizeImage(ImageIcon image)
    {
        return new ImageIcon(image.getImage().getScaledInstance(imageSize, 
                imageSize, Image.SCALE_DEFAULT));
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Slideshow application = new Slideshow();
        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
}

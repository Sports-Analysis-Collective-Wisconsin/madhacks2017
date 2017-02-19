

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
 

public class Display extends JPanel {
    ImageIcon[] images;
    String[] teams = { "Golden State", "Cleveland", "San Antonio", "Houston",
    		"Boston", "Washington", "Toronto", "Los Angeles Clippers", "Detroit",
    		"Milwaukee", "Philadelphia", "Denver", "Dallas", "Memphis", "Utah", 
    		"Oklahoma City", "Sacramento", "Minnesota", "Portland", "Phoenix",
    		"New Orleans", "Charlotte", "Miami", "Indiana", "Chicago", "New York"
    		, "Brooklyn", "Atlanta", "Orlando", "Los Angeles Lakers" };


    public Display() {
        super(new BorderLayout());
 
        //Load the team images and create an array of indexes.
        images = new ImageIcon[teams.length];
        Integer[] intArray = new Integer[teams.length];
        for (int i = 0; i < teams.length; i++) {
            intArray[i] = new Integer(i);
            images[i] = createImageIcon("images/" + teams[i] + ".jpg");
            if (images[i] != null) {
                images[i].setDescription(teams[i]);
            }
        }
 
        //Create the combo box.
        JComboBox list = new JComboBox(intArray);
        ComboBoxRenderer renderer= new ComboBoxRenderer();
        renderer.setPreferredSize(new Dimension(200, 200));
        list.setRenderer(renderer);
        list.setMaximumRowCount(3);
 
        
        add(list, BorderLayout.PAGE_START);
        setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
    }
 
    
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = Display.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
                return null;
        }
    }
 

    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Home Team Select");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());
 
        //Create and set up the content pane.
        JComponent newContentPane = new Display();
        JComponent secondContentPane = new Display();
        newContentPane.setOpaque(true);
        secondContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);
        frame.setContentPane(secondContentPane);
 
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
 
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
 
    class ComboBoxRenderer extends JLabel
                           implements ListCellRenderer {
        private Font uhOhFont;
 
        public ComboBoxRenderer() {
            setOpaque(true);
            setHorizontalAlignment(CENTER);
            setVerticalAlignment(CENTER);
        }
 

        public Component getListCellRendererComponent(
                                           JList list,
                                           Object value,
                                           int index,
                                           boolean isSelected,
                                           boolean cellHasFocus) {
            //Get the selected index. 
            int selectedIndex = ((Integer)value).intValue();
 
            if (isSelected) {
                setBackground(list.getSelectionBackground());
                setForeground(list.getSelectionForeground());
            } else {
                setBackground(list.getBackground());
                setForeground(list.getForeground());
            }
 
            //Set the icon and text.  If icon was null, say so
            ImageIcon icon = images[selectedIndex];
            //String team = teams[selectedIndex];
            setIcon(icon);
            /*if (icon != null) {
                setText(team);
                setFont(list.getFont());
            } else {
                setUhOhText(team + " (no image available)",
                            list.getFont());
            }*/
 
            return this;
        }
 
        //Set the font and text when no image was found.
        protected void setUhOhText(String uhOhText, Font normalFont) {
            if (uhOhFont == null) { 
                uhOhFont = normalFont.deriveFont(Font.ITALIC);
            }
            setFont(uhOhFont);
            setText(uhOhText);
        }
    }
}
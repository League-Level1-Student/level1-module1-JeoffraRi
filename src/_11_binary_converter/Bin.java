package _11_binary_converter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Bin implements ActionListener {
public static void main(String[] args) {
	Bin bin = new Bin();
}


JTextField bigBird = new JTextField(10);
JLabel label = new JLabel("answer:");

Bin() {
	JFrame frame = new JFrame();
	frame.setVisible(true);
	JPanel panel = new JPanel();
	JButton button = new JButton("Pingu");
	panel.add(button);
	frame.add(panel);
	panel.add(label);
	button.addActionListener(this);
	panel.add(bigBird);
	frame.pack();
	convert("Asher");
	
}

String convert(String input) {
    if(input.length() != 8){
        JOptionPane.showMessageDialog(null, "Enter 8 bits, silly!!!");
        return "-";
    }
    String binary = "[0-1]+";//must contain numbers in the given range
    if (!input.matches(binary)) {
        JOptionPane.showMessageDialog(null, "Binary can only contain 1s or 0s, silly!!!");
        return "-";   
    }
    try {
        int asciiValue = Integer.parseInt(input, 2);
        char theLetter = (char) asciiValue;
        return "" + theLetter;
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Enter a binary, silly!!!");
        return "-";
    }
}


@Override
public void actionPerformed(ActionEvent e) {

	
	String elmo = convert(bigBird.getText());
	System.out.println(elmo);
	label.setText(elmo);
}

           




}

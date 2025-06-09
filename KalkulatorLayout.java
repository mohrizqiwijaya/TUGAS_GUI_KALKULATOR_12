package Kalkulator;

import javax.swing.*;
import java.awt.*;

public class KalkulatorLayout extends JFrame {
    private JTextField textField;
    private String operator = "";
    private double num1 = 0;

    public KalkulatorLayout() {
        setTitle("Kalkulator");
        setSize(400, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Text field di atas
        textField = new JTextField();
        textField.setEditable(false);
        textField.setPreferredSize(new Dimension(0, 40));
        textField.setFont(new Font("Arial", Font.PLAIN, 18));
        textField.setMargin(new Insets(10, 10, 10, 10));
        add(textField, BorderLayout.NORTH);

        // Panel tombol dengan layout 3 baris x 6 kolom
        JPanel buttonPanel = new JPanel(new GridLayout(3, 6, 5, 5));
        String[] buttons = {
            "1", "2", "3", "4", "5", "6",
            "7", "8", "9", "0", "+", "-",
            "*", "/", "=", "%", "Mod", "Exit"
        };

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.PLAIN, 14));
            button.addActionListener(e -> handleButtonClick(e.getActionCommand()));
            buttonPanel.add(button);
        }

        add(buttonPanel, BorderLayout.CENTER);
    }

    private void handleButtonClick(String command) {
        switch (command) {
            case "Exit":
                System.exit(0);
                break;
            case "+": case "-": case "*": case "/": case "%": case "Mod":
                operator = command;
                try {
                    num1 = Double.parseDouble(textField.getText());
                    textField.setText("");
                } catch (NumberFormatException e) {
                    textField.setText("Error");
                }
                break;
            case "=":
                try {
                    double num2 = Double.parseDouble(textField.getText());
                    double result = switch (operator) {
                        case "+" -> num1 + num2;
                        case "-" -> num1 - num2;
                        case "*" -> num1 * num2;
                        case "/" -> num2 != 0 ? num1 / num2 : 0;
                        case "%" -> num1 % num2;
                        case "Mod" -> num1 % num2;
                        default -> 0;
                    };
                    if (result == (int) result) {
                        textField.setText(Integer.toString((int) result));
                    } else {
                        textField.setText(Double.toString(result));
                    }
                } catch (NumberFormatException e) {
                    textField.setText("Error");
                }
                break;
            default:
                textField.setText(textField.getText() + command);
                break;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            KalkulatorLayout kalkulator = new KalkulatorLayout();
            kalkulator.setVisible(true);
        });
    }
}
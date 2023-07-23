import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CurrencyConverterApp extends JFrame {
    private JTextField inrTextField;
    private JComboBox<String> currencyComboBox;
    private JLabel resultLabel;

    public CurrencyConverterApp() {
        setTitle("Currency Converter");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 1));

        JLabel inrLabel = new JLabel("INR:");
        inrTextField = new JTextField();
        JLabel currencyLabel = new JLabel("Currency:");
        currencyComboBox = new JComboBox<>(new String[]{"USD", "EUR", "GBP"}); 
        JButton convertButton = new JButton("Convert");
        convertButton.addActionListener(new ConvertButtonListener());
        resultLabel = new JLabel();

        JPanel panel1 = new JPanel(new FlowLayout());
        panel1.add(inrLabel);
        panel1.add(inrTextField);

        JPanel panel2 = new JPanel(new FlowLayout());
        panel2.add(currencyLabel);
        panel2.add(currencyComboBox);

        JPanel panel3 = new JPanel(new FlowLayout());
        panel3.add(convertButton);

        JPanel panel4 = new JPanel(new FlowLayout());
        panel4.add(resultLabel);

        add(panel1);
        add(panel2);
        add(panel3);
        add(panel4);
    }

    private class ConvertButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                double amountINR = Double.parseDouble(inrTextField.getText());
                double exchangeRate = getExchangeRate(currencyComboBox.getSelectedItem().toString());

                double convertedAmount = convertCurrency(amountINR, exchangeRate);
                String resultText = String.format("%.2f INR is equal to %.2f %s", amountINR, convertedAmount,
                        currencyComboBox.getSelectedItem().toString());
                resultLabel.setText(resultText);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number for INR.");
            }
        }

        private double getExchangeRate(String currency) {
            switch (currency) {
                case "USD":
                    return 0.014; // 1 INR = 0.014 USD 
                case "EUR":
                    return 0.012; // 1 INR = 0.012 EUR 
                case "GBP":
                    return 0.011; // 1 INR = 0.011 GBP 
                default:
                    return 0.0;
            }
        }
    }

    public static double convertCurrency(double amount, double exchangeRate) {
        return amount * exchangeRate;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CurrencyConverterApp app = new CurrencyConverterApp();
            app.setVisible(true);
        });
    }
}
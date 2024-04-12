import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App {
    public static void main(String[] args) throws Exception {
        Wallet wallet = new Wallet(500);
        Bmw bmw = new Bmw("BMW", "M4 Competition Coupé", 510, 2022, 150.0, true);
        Audi audi = new Audi("Audi", "RS e-tron GT", 598, 2021, 200.0, true);
        Kia kia = new Kia("Kia", "EV6 GT", 585, 2021, 175.0, true);
        Toyota toyota = new Toyota("Toyota", "Corolla", 132, 2019, 100.0, true);
        Lambo lambo = new Lambo("Lamborghini", "Huracan", 640, 2020, 250.0, true);
        
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel walletLabel = new JLabel("Your balance: " + wallet.getBalance() + "$");
        JButton addFundsButton = new JButton("Add Funds");
        addFundsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddFundsWindow(wallet, walletLabel);
            }
        });
        
        panel.add(walletLabel);
        panel.add(addFundsButton);
        panel.add(createCarPanel(bmw, wallet, walletLabel));
        panel.add(createCarPanel(audi, wallet, walletLabel));
        panel.add(createCarPanel(kia, wallet, walletLabel));
        panel.add(createCarPanel(toyota, wallet, walletLabel));
        panel.add(createCarPanel(lambo, wallet, walletLabel));
        
        JFrame frame = new JFrame("Rental Cars System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(panel);
        frame.setPreferredSize(new Dimension(400, 650));
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
    }

    private static JPanel createCarPanel(RentalCar car, Wallet wallet, JLabel walletLabel) {
        JPanel carPanel = new JPanel();
        carPanel.setLayout(new BorderLayout());

        JLabel carLabel = new JLabel("<html><b>" + car.getBrand() + " " + car.getModel() + "</b><br>" +
                "Power: " + car.getPower() + "KM<br>" +
                "Year: " + car.getYear() + "<br>" +
                "Price / Day: " + car.getPricePerDay() + "$" + "<br>" +
                "Availability: " + car.displayAvailability() + "<br>"
                + "Enter number of days: " + "</html>");
        carPanel.add(carLabel, BorderLayout.NORTH);

        JPanel rentPanel = new JPanel(new FlowLayout());

        JTextField daysField = new JTextField(5);
        rentPanel.add(daysField);

        JButton rentButton = new JButton("Rent");
        car.setRentButton(rentButton);

        JButton returButton = new JButton("Return");
        returButton.setEnabled(false);
        returButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Hope you had fun!", "Message", JOptionPane.INFORMATION_MESSAGE);
                car.setAvailability(true);
                rentButton.setEnabled(true);
                returButton.setEnabled(false);
                carLabel.setText("<html><b>" + car.getBrand() + " " + car.getModel() + "</b><br>" +
                "Power: " + car.getPower() + "KM<br>" +
                "Year: " + car.getYear() + "<br>" +
                "Price / Day: " + car.getPricePerDay() + "$" + "<br>" +
                "Availability: " + car.displayAvailability() + "<br>"
                + "Enter number of days: " + "</html>");
                daysField.setText("");
            }
        });

        rentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String input = daysField.getText();
                if(isNumeric(input)) {
                    int days = Integer.parseInt(daysField.getText());
                    double finalPrice = days * car.getPricePerDay();
    
                    if(finalPrice <= wallet.getBalance()) {
                        wallet.deductFunds(finalPrice);
                        car.setAvailability(false);
                        rentButton.setEnabled(false);
                        returButton.setEnabled(true);
                        JOptionPane.showMessageDialog(null, "You rented " + car.getBrand(), "Message", JOptionPane.INFORMATION_MESSAGE);
                        walletLabel.setText("Your balance: " + wallet.getBalance() + "$");
                        carLabel.setText("<html><b>" + car.getBrand() + " " + car.getModel() + "</b><br>" +
                        "Power: " + car.getPower() + "KM<br>" +
                        "Year: " + car.getYear() + "<br>" +
                        "Price / Day: " + car.getPricePerDay() + "$" + "<br>" +
                        "Availability: " + car.displayAvailability() + "<br>"
                        + "Enter number of days: " + "</html>");
                        daysField.setText("");
                    } else {
                        JOptionPane.showMessageDialog(null, "You can't afford rental. Add funds to your wallet first!", "Message", JOptionPane.INFORMATION_MESSAGE);
                    }   
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter a valid number of days!", "Error", JOptionPane.ERROR_MESSAGE);
                    daysField.setText("");
                }
            }
        });
        rentPanel.add(rentButton);
        rentPanel.add(returButton);
        carPanel.add(rentPanel, BorderLayout.CENTER);

        return carPanel;
    }

    private static boolean isNumeric(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }
}

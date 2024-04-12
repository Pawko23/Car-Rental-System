import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddFundsWindow extends JFrame {
    private Wallet wallet;
    private JLabel walletLabel;

    public AddFundsWindow(Wallet wallet, JLabel walletLabel) {
        this.wallet = wallet;
        this.walletLabel = walletLabel;
        initUI();
    }

    private void initUI() {
        setTitle("Add Funds");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300, 150);

        JPanel addFundsPanel = new JPanel();
        addFundsPanel.setLayout(new FlowLayout());

        JTextField addFundsField = new JTextField(10);
        JButton addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double amount = Double.parseDouble(addFundsField.getText());
                    if (amount <= 0) {
                        JOptionPane.showMessageDialog(null, "Please enter a positive amount", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        wallet.addFunds(amount);
                        walletLabel.setText("Your balance: " + wallet.getBalance() + "$");
                        dispose();
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid amount", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        addFundsPanel.add(new JLabel("Enter amount: $"));
        addFundsPanel.add(addFundsField);
        addFundsPanel.add(addButton);

        getContentPane().add(addFundsPanel);
        setVisible(true);
    }
}
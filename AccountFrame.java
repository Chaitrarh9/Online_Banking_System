import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

class City {
    String cityName;
    String regionName;

    public City(String cityName, String regionName) {
        this.cityName = cityName;
        this.regionName = regionName;
    }

    @Override
    public String toString() {
        return cityName + " - " + regionName;
    }
}

class Account implements Comparable<Account> {
    static int counter = 1;
    int accNo;
    String owner;
    City city;
    char gender;
    double balance;

    public Account(String owner, City city, char gender) {
        this.accNo = counter++;
        this.owner = owner;
        this.city = city;
        this.gender = gender;
        this.balance = 0;
    }

    public Account(int accNo, String owner, City city, char gender, double balance) {
        this.accNo = accNo;
        this.owner = owner;
        this.city = city;
        this.gender = gender;
        this.balance = balance;
    }

    // Methods for deposit and withdraw
    public void deposit(double amount) {
        balance += amount;
    }

    public void withdraw(double amount) {
        balance -= amount;
    }

    @Override
    public String toString() {
        return owner + " - " + accNo;
    }

    @Override
    public int compareTo(Account other) {
        // Compare based on account number
        return Integer.compare(this.accNo, other.accNo);
    }
}

class Transaction {
    private static int transactionCounter = 1;

    private final int transactionNo;
    private final Date transactionDate;
    private final String transactionType;
    private final double transactionAmount;
    private final int accountNo;

    public Transaction(int accountNo, String transactionType, double transactionAmount) {
        this.accountNo = accountNo;
        this.transactionNo = transactionCounter++;
        this.transactionDate = new Date(); // Use the current date and time
        this.transactionType = transactionType;
        this.transactionAmount = transactionAmount;
    }

    public int getTransactionNo() {
        return transactionNo;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public double getTransactionAmount() {
        return transactionAmount;
    }

    public int getAccountNo() {
        return accountNo;
    }
}

public class AccountFrame extends JFrame {
    JLabel accnNoLBL, ownerLBL, balanceLBL, cityLBL, genderLBL, amountLBL;
    JTextField accNoTXT, ownerTXT, balanceTXT, amountTXT;
    JComboBox<City> citiesCMB;

    JButton newBTN, saveBTN, showBTN, quitBTN, depositBTN, withdrawBTN,checkBalanceBTN;
    JRadioButton maleRDB, femaleRDB;
    ButtonGroup genderBTNGRP;

    JList<Account> accountList;
    JPanel p1, p2, p3, p4, p5;

    Set<Account> accountSet = new TreeSet<>();
    Account acc;
    boolean newRec = true;

    // Table
    DefaultComboBoxModel<City> citiesCMBMDL;
    DefaultListModel<Account> accountsLSTMDL;

    // Table data
    JTable table;
    DefaultTableModel tableModel;
    ArrayList<Transaction> translist = new ArrayList<>();

    public AccountFrame() {
        super("Account Operations");
        setLayout(null);
        setSize(600, 400);

        // Adding Components to Frame
        // 1-Labels
        accnNoLBL = new JLabel("Account No.");
        ownerLBL = new JLabel("Owner");
        balanceLBL = new JLabel("Balance");
        cityLBL = new JLabel("City");
        genderLBL = new JLabel("Gender");
        amountLBL = new JLabel("Amount");


        // 2-TextFields
        accNoTXT = new JTextField();
        accNoTXT.setEnabled(false);
        ownerTXT = new JTextField();
        balanceTXT = new JTextField();
        balanceTXT.setEnabled(false);
        amountTXT = new JTextField();
        amountTXT.setPreferredSize(new Dimension(150, 25));

        // Combobox
        citiesCMBMDL = new DefaultComboBoxModel<>();
        citiesCMBMDL.addElement(null);
        citiesCMBMDL.addElement(new City("Bangalore", "Karnataka"));
        citiesCMBMDL.addElement(new City("Mysore", "Karntaka"));
        citiesCMBMDL.addElement(new City("Sirsi", "Kanataka"));
        citiesCMBMDL.addElement(new City("Davanagere", "Karnataka"));

        // Adding data to JComboBox
        citiesCMB = new JComboBox<>(citiesCMBMDL);

        // 4-RadioButtons
        maleRDB = new JRadioButton("Male", true);
        femaleRDB = new JRadioButton("Female");
        genderBTNGRP = new ButtonGroup();
        genderBTNGRP.add(maleRDB);
        genderBTNGRP.add(femaleRDB);

        // 5- Buttons
        newBTN = new JButton("New");
        saveBTN = new JButton("Save");
        showBTN = new JButton("Show");
        quitBTN = new JButton("Quit");
        depositBTN = new JButton("Deposit");
        withdrawBTN = new JButton("Withdraw");
        checkBalanceBTN = new JButton("Check Balance");

        // 6- Table
        accountsLSTMDL = new DefaultListModel<>();
        accountList = new JList<>(accountsLSTMDL);

        // 7- Panels
        p1 = new JPanel();
        p1.setBounds(5, 5, 300, 150);
        p1.setLayout(new GridLayout(5, 2));

        p2 = new JPanel();
        p2.setBounds(5, 155, 300, 40);
        p2.setLayout(new FlowLayout());

        p3 = new JPanel();
        p3.setBounds(5, 195, 600, 40);
        p3.setLayout(new FlowLayout());

        p4 = new JPanel();
        p4.setBounds(305, 5, 300, 190);
        p4.setLayout(new BorderLayout());

        p5 = new JPanel();
        p5.setBounds(5, 240, 580, 120);
        p5.setLayout(new BorderLayout());

        // Adding components to Frame
        p1.add(accnNoLBL);
        p1.add(accNoTXT);
        p1.add(ownerLBL);
        p1.add(ownerTXT);
        p1.add(balanceLBL);
        p1.add(balanceTXT);
        p1.add(cityLBL);
        p1.add(citiesCMB);
        p1.add(maleRDB);
        p1.add(femaleRDB);

        p2.add(newBTN);
        p2.add(saveBTN);
        p2.add(showBTN);
        p2.add(quitBTN);

        p3.add(amountLBL);
        p3.add(amountTXT);
        p3.add(depositBTN);
        p3.add(withdrawBTN);
        p3.add(checkBalanceBTN);

        p4.add(accountList);

        // Adding Panels to Frame
        add(p1);
        add(p2);
        add(p3);
        add(p4);
        add(p5);

        // Table Creation
        tableModel = new DefaultTableModel();
        table = new JTable(tableModel);
        tableModel.addColumn("Transaction No.");
        tableModel.addColumn("Transaction Date");
        tableModel.addColumn("Transaction Type");
        tableModel.addColumn("Transaction Amount");

        JScrollPane scrollPane = new JScrollPane(table);
        p5.add(scrollPane);

        // ************************* Functionality ************************/
        quitBTN.addActionListener(e -> {
            dispose(); // Close the JFrame
            System.exit(0); // Exit the application
        });

        newBTN.addActionListener(e -> {
            accNoTXT.setText("");
            ownerTXT.setText("");
            citiesCMB.setSelectedIndex(0);
            maleRDB.setSelected(true);
            balanceTXT.setText("");
            amountTXT.setText("");
            newRec = true;

            // Enable necessary components for new record
            accNoTXT.setEnabled(true);
            ownerTXT.setEnabled(true);
            citiesCMB.setEnabled(true);
            maleRDB.setEnabled(true);
            femaleRDB.setEnabled(true);
        });

        saveBTN.addActionListener(e -> {
            if (!validateFields()) {
                JOptionPane.showMessageDialog(null, "Please fill all required fields", "Validation Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (newRec) {
                acc = new Account(
                        ownerTXT.getText(),
                        (City) citiesCMB.getSelectedItem(),
                        maleRDB.isSelected() ? 'M' : 'F');

                accNoTXT.setText(String.valueOf(acc.accNo));
                accountSet.add(acc);
                accountsLSTMDL.addElement(acc);
                newRec = false;
            } else {
                // Update the selected account
                int selectedIndex = accountList.getSelectedIndex();
                if (selectedIndex != -1) {
                    accountSet.remove(acc);

                    int a = Integer.parseInt(accNoTXT.getText());
                    String o = ownerTXT.getText();
                    City c = (City) citiesCMB.getSelectedItem();

                    char g = maleRDB.isSelected() ? 'M' : 'F';
                    double b = Double.parseDouble(balanceTXT.getText());
                    acc = new Account(a, o, c, g, b);
                    accountSet.add(acc);
                    accountsLSTMDL.setElementAt(acc, selectedIndex);
                    newRec = false;
                }
            }
        });


        showBTN.addActionListener(e -> {
            StringBuilder s = new StringBuilder("Account Details:\n");

            for (Account account : accountSet) {
                s.append("Account No.: ").append(account.accNo).append("\n");
                s.append("Owner: ").append(account.owner).append("\n");
                s.append("City: ").append(account.city).append("\n");
                s.append("Gender: ").append(account.gender).append("\n");
                s.append("Balance: ").append(account.balance).append("\n");

                // Display transactions for the current account
                s.append("Transactions:\n");
                for (Transaction transaction : translist) {
                    if (transaction.getAccountNo() == account.accNo) {
                        s.append("\tTransaction No.: ").append(transaction.getTransactionNo()).append("\n");
                        s.append("\tTransaction Date: ").append(transaction.getTransactionDate()).append("\n");
                        s.append("\tTransaction Type: ").append(transaction.getTransactionType()).append("\n");
                        s.append("\tTransaction Amount: ").append(transaction.getTransactionAmount()).append("\n");
                    }
                }

                s.append("------------------------------\n");
            }

            JOptionPane.showMessageDialog(null, s.toString(), "Account Details", JOptionPane.INFORMATION_MESSAGE);
        });

        depositBTN.addActionListener(e -> {
            try {
                double amount = Double.parseDouble(amountTXT.getText());

                if (amount <= 0) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid amount for deposit", "Invalid Amount", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                int selectedIndex = accountList.getSelectedIndex();
                if (selectedIndex != -1) {
                    Account selectedAccount = accountsLSTMDL.getElementAt(selectedIndex);
                    selectedAccount.deposit(amount);

                    balanceTXT.setText(String.valueOf(selectedAccount.balance));
                    accountsLSTMDL.setElementAt(selectedAccount, selectedIndex);

                    Transaction depositTransaction = new Transaction(selectedAccount.accNo, "Deposit", amount);
                    translist.add(depositTransaction);

                    updateTransactionTable(selectedAccount.accNo);

                    JOptionPane.showMessageDialog(null, "Deposit successful", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Please select an account to deposit", "No Account Selected", JOptionPane.WARNING_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter a valid numeric amount", "Invalid Amount", JOptionPane.ERROR_MESSAGE);
            }
        });

        withdrawBTN.addActionListener(e -> {
            try {
                double amount = Double.parseDouble(amountTXT.getText());

                if (amount <= 0) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid amount for withdrawal", "Invalid Amount", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                int selectedIndex = accountList.getSelectedIndex();
                if (selectedIndex != -1) {
                    Account selectedAccount = accountsLSTMDL.getElementAt(selectedIndex);

                    if (selectedAccount.balance >= amount) {
                        selectedAccount.withdraw(amount);

                        balanceTXT.setText(String.valueOf(selectedAccount.balance));
                        accountsLSTMDL.setElementAt(selectedAccount, selectedIndex);

                        Transaction withdrawalTransaction = new Transaction(selectedAccount.accNo, "Withdrawal", amount);
                        translist.add(withdrawalTransaction);

                        updateTransactionTable(selectedAccount.accNo);

                        JOptionPane.showMessageDialog(null, "Withdrawal successful", "Success", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Insufficient funds for withdrawal", "Insufficient Funds", JOptionPane.WARNING_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please select an account to withdraw", "No Account Selected", JOptionPane.WARNING_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter a valid numeric amount", "Invalid Amount", JOptionPane.ERROR_MESSAGE);
            }
        });

        checkBalanceBTN.addActionListener(e -> {
            int selectedIndex = accountList.getSelectedIndex();
            if (selectedIndex != -1) {
                Account selectedAccount = accountsLSTMDL.getElementAt(selectedIndex);
                JOptionPane.showMessageDialog(
                        null,
                        "Current Balance: Rs." + selectedAccount.balance,
                        "Current Balance",
                        JOptionPane.INFORMATION_MESSAGE
                );
            } else {
                JOptionPane.showMessageDialog(
                        null,
                        "Please select an account to check the balance",
                        "No Account Selected",
                        JOptionPane.WARNING_MESSAGE
                );
            }
        });

        accountList.addListSelectionListener(e -> {
            int selectedIndex = accountList.getSelectedIndex();
            if (selectedIndex != -1) {
                Account selectedAccount = accountsLSTMDL.getElementAt(selectedIndex);

                accNoTXT.setText(String.valueOf(selectedAccount.accNo));
                ownerTXT.setText(selectedAccount.owner);
                citiesCMB.setSelectedItem(selectedAccount.city);
                if (selectedAccount.gender == 'M') {
                    maleRDB.setSelected(true);
                } else {
                    femaleRDB.setSelected(true);
                }
                balanceTXT.setText(String.valueOf(selectedAccount.balance));

                newRec = false;
                accNoTXT.setEnabled(false);
                ownerTXT.setEnabled(false);
                citiesCMB.setEnabled(false);
                maleRDB.setEnabled(false);
                femaleRDB.setEnabled(false);

                updateTransactionTable(selectedAccount.accNo);
            }
        });
    }

    private boolean validateFields() {
        return !ownerTXT.getText().isEmpty() && citiesCMB.getSelectedItem() != null;
    }

    private void updateTransactionTable(int accountNo) {
        tableModel.setRowCount(0);
        for (Transaction transaction : translist) {
            if (transaction.getAccountNo() == accountNo) {
                Object[] row = {transaction.getTransactionNo(), transaction.getTransactionDate(),
                        transaction.getTransactionType(), transaction.getTransactionAmount()};
                tableModel.addRow(row);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AccountFrame af = new AccountFrame();
            af.setVisible(true);
            af.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        });
    }
}
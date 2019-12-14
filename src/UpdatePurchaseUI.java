import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class UpdatePurchaseUI {

    public JFrame view;

    public JButton btnLoad = new JButton("Load Purchase");
    public JButton btnSave = new JButton("Save Purchase");
    public JButton btnCancel = new JButton("Cancel");

    public JTextField txtPurchaseID = new JTextField(20);
    public JTextField txtCustomerID = new JTextField(20);
    public JTextField txtProductID = new JTextField(20);
    public JTextField txtPrice = new JTextField(20);
    public JTextField txtQuantity = new JTextField(20);
    public JTextField txtCost = new JTextField(20);
    public JTextField txtTax = new JTextField(20);
    public JTextField txtTotal = new JTextField(20);
    public JTextField txtDate = new JTextField(20);


    public UpdatePurchaseUI() {
        this.view = new JFrame();
        
        view.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        view.setTitle("Update Purchase Information");
        view.setSize(650, 700);
        view.getContentPane().setLayout(new BoxLayout(view.getContentPane(), BoxLayout.PAGE_AXIS));

        JPanel panelButtons = new JPanel(new FlowLayout());
        panelButtons.add(btnLoad);
        panelButtons.add(btnSave);
        panelButtons.add(btnCancel);
        view.getContentPane().add(panelButtons);

        JPanel line1 = new JPanel(new FlowLayout());
        line1.add(new JLabel("PurchaseID "));
        line1.add(txtPurchaseID);
        view.getContentPane().add(line1);

        JPanel line2 = new JPanel(new FlowLayout());
        line2.add(new JLabel("CustomerID "));
        line2.add(txtCustomerID);
        view.getContentPane().add(line2);

        JPanel line3 = new JPanel(new FlowLayout());
        line3.add(new JLabel("ProductID "));
        line3.add(txtProductID);
        view.getContentPane().add(line3);

        JPanel line4 = new JPanel(new FlowLayout());
        line4.add(new JLabel("Price "));
        line4.add(txtPrice);
        view.getContentPane().add(line4);
        
        JPanel line5 = new JPanel(new FlowLayout());
        line5.add(new JLabel("Quantity "));
        line5.add(txtQuantity);
        view.getContentPane().add(line5);
        
        JPanel line6 = new JPanel(new FlowLayout());
        line6.add(new JLabel("Cost "));
        line6.add(txtCost);
        view.getContentPane().add(line6);
        
        JPanel line7 = new JPanel(new FlowLayout());
        line7.add(new JLabel("Tax "));
        line7.add(txtTax);
        view.getContentPane().add(line7);
        
        JPanel line8 = new JPanel(new FlowLayout());
        line8.add(new JLabel("Total "));
        line8.add(txtTotal);
        view.getContentPane().add(line8);
        
        JPanel line9 = new JPanel(new FlowLayout());
        line9.add(new JLabel("Date "));
        line9.add(txtDate);
        view.getContentPane().add(line9);


        btnLoad.addActionListener(new LoadButtonListerner());

        btnSave.addActionListener(new SaveButtonListener());
        
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                view.dispose();
            }
        });

    }

    public void run() {
        PurchaseServer server = new PurchaseServer();
        view.setVisible(true);
    }

    class LoadButtonListerner implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            PurchaseModel purchase = new PurchaseModel();
            
            String id = txtPurchaseID.getText();

            if (id.length() == 0) {
                JOptionPane.showMessageDialog(null, "PurchaseID cannot be null!");
                return;
            }

            try {
                purchase.mPurchaseID = Integer.parseInt(id);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "PurchaseID is invalid!");
                return;
            }

            // do client/server
            
            try {
                Socket link = new Socket("localhost", 3000);
                Scanner input = new Scanner(link.getInputStream());
                PrintWriter output = new PrintWriter(link.getOutputStream(), true);

                output.println("GET");
                output.println(purchase.mPurchaseID);

                purchase.mCustomerID = input.nextInt();

                /**if (purchase.mCustomerID.equals("null")) {
                    System.out.println("Purchase with id = " + id + " does not exist!");
                    JOptionPane.showMessageDialog(null, "Purchase DOES NOT exist!");
                    return;
                }*/

                txtCustomerID.setText(Integer.toString(purchase.mCustomerID));

                purchase.mProductID = input.nextInt();
                txtProductID.setText(Integer.toString(purchase.mProductID));

                purchase.mPrice = input.nextDouble();
                txtPrice.setText(Double.toString(purchase.mPrice));
                
                purchase.mQuantity = input.nextDouble();
                txtQuantity.setText(Double.toString(purchase.mQuantity));
                
                purchase.mCost = input.nextDouble();
                txtCost.setText(Double.toString(purchase.mCost));
                
                purchase.mTax = input.nextDouble();
                txtTax.setText(Double.toString(purchase.mTax));
                
                purchase.mTotal = input.nextDouble();
                txtTotal.setText(Double.toString(purchase.mTotal));
                
                purchase.mDate = input.next() + input.nextLine();
                txtDate.setText(purchase.mDate);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    class SaveButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            PurchaseModel purchase = new PurchaseModel();
            String id = txtPurchaseID.getText();

            if (id.length() == 0) {
                JOptionPane.showMessageDialog(null, "PurchaseID cannot be null!");
                return;
            }

            try {
                purchase.mPurchaseID = Integer.parseInt(id);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "PurchaseID is invalid!");
                return;
            }

            String customerID = txtCustomerID.getText();
            if (customerID.length() == 0) {
                JOptionPane.showMessageDialog(null, "CustomerID cannot be empty!");
                return;
            }
            
            try {
                purchase.mCustomerID = Integer.parseInt(customerID);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "CustomerID is invalid!");
                return;
            }

            String productID = txtProductID.getText();
            if (productID.length() == 0) {
                JOptionPane.showMessageDialog(null, "ProductID cannot be empty!");
                return;
            }
            
            try {
                purchase.mProductID = Integer.parseInt(productID);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "ProductID is invalid!");
                return;
            }

            String price = txtPrice.getText();
            try {
                purchase.mPrice = Double.parseDouble(price);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Price is invalid!");
                return;
            }
            
            String quantity = txtQuantity.getText();
            try {
                purchase.mQuantity = Double.parseDouble(quantity);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Quantity is invalid!");
                return;
            }
            
            String cost = txtCost.getText();
            try {
                purchase.mCost = Double.parseDouble(cost);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Cost is invalid!");
                return;
            }
            
            String tax = txtTax.getText();
            try {
                purchase.mTax = Double.parseDouble(tax);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Tax is invalid!");
                return;
            }
            
            String total = txtTotal.getText();
            try {
                purchase.mTotal = Double.parseDouble(total);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Total is invalid!");
                return;
            }
            
            String date = txtDate.getText();
            purchase.mDate = date;

            // all customer info is ready! Send to Server!

            try {
                Socket link = new Socket("localhost", 3000);
                Scanner input = new Scanner(link.getInputStream());
                PrintWriter output = new PrintWriter(link.getOutputStream(), true);

                output.println("PUT");
                output.println(purchase.mPurchaseID);
                output.println(purchase.mCustomerID);
                output.println(purchase.mProductID);
                output.println(purchase.mPrice);
                output.println(purchase.mQuantity);
                output.println(purchase.mCost);
                output.println(purchase.mTax);
                output.println(purchase.mTotal);
                output.println(purchase.mDate);

            } catch (Exception e) {
                e.printStackTrace();
            }
            
            JOptionPane.showMessageDialog(null, "Purchase updated successfully!" + purchase);
        }
    }
}
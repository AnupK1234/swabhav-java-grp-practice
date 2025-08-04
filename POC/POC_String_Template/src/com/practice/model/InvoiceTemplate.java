package com.practice.model;

public class InvoiceTemplate {
    public static void main(String[] args) {
        String customerName = "Ravi Sharma";
        String item = "Wireless Mouse";
        int quantity = 2;
        double price = 599.997;
        double total = quantity * price;

        String invoice = String.format("""
                ========================
                  🧾 Invoice
                ========================
                Customer : %s
                Item     : %s
                Quantity : %d
                Unit Price: ₹%.2f
                ------------------------
                Total    : ₹%.2f
                ========================
                Thank you for shopping!
                """, customerName, item, quantity, price, total);

        System.out.println(invoice);
    }
}
package com.twu.refactoring;

/**
 * OrderReceipt prints the details of order including customer name, address, description, quantity,
 * price and amount. It also calculates the sales tax @ 10% and prints as part
 * of order. It computes the total order amount (amount of individual lineItems +
 * total sales tax) and prints it.
 * 
 */
public class OrderReceipt {
    private final Order order;

    public OrderReceipt(Order order) {
        this.order = order;
	}

	public String printReceipt() {
		StringBuilder output = new StringBuilder();

		printHeader(output);

		for (LineItem lineItem : order.getLineItems()) {
			output.append(lineItem.getDescription());
			output.append('\t');
			output.append(lineItem.getPrice());
			output.append('\t');
			output.append(lineItem.getQuantity());
			output.append('\t');
			output.append(lineItem.totalAmount());
			output.append('\n');
		}

		double totalSalesTax = getTotalSalesTax();

		double totalAmount = 0d;
		for (LineItem lineItem : order.getLineItems()) {
            double salesTax = lineItem.totalAmount() * .10;

            totalAmount += lineItem.totalAmount() + salesTax;
		}

		output.append("Sales Tax").append('\t').append(totalSalesTax);

		output.append("Total Amount").append('\t').append(totalAmount);
		return output.toString();
	}

	private double getTotalSalesTax() {
		double totalSalesTax = 0d;
		for (LineItem lineItem : order.getLineItems()) {
			double salesTax = lineItem.totalAmount() * .10;
			totalSalesTax += salesTax;
		}
		return totalSalesTax;
	}

	private void printHeader(StringBuilder output) {
		output.append("======Printing Orders======\n");

		output.append(order.getCustomerName());
		output.append(order.getCustomerAddress());
	}
}
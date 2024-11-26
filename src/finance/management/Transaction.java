package finance.management;

import java.util.Date;
import java.text.SimpleDateFormat;

	public class Transaction {
	    protected double amount;
	    protected Date date;
	    protected String description;
	    protected String category;

	    public Transaction(double amount, String description, String category) {
	        this.amount = amount;
	        this.date = new Date();
	        this.description = description;
	        this.category = category; // Initialize category
	    }

	    public double getAmount() {
	        return amount;
	    }

	    public String getDescription() {
	        return description;
	    }

	    public String getDate() {
	        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	        return sdf.format(date);
	    }
	    
	    public String getCategory() {
	        return category; 
	    }

	}



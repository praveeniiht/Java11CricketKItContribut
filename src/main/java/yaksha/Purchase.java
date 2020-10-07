package yaksha;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Purchase implements Comparable<Object> {
	private long id;
	private Date purchaseDate;
	private double totalAmount;
	private String user;

	public Purchase(long id, Date purchaseDate, double totalAmount, String user) {
		super();
		this.id = id;
		this.purchaseDate = purchaseDate;
		this.totalAmount = totalAmount;
		this.user = user;
	}

	public Purchase() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return String.format("%-10d %-15s %.1f", this.getId(), this.getUser(), this.getTotalAmount());
	}

	static Purchase obtainPurchaseWithAmount(String str) throws InvalidWholeSaleException, ParseException {
		String d[] = str.split(",");
		long idh = Long.parseLong(d[0]);
		if (((d.length - 3) / 3) < 5) {
			throw new InvalidWholeSaleException("Purchase " + idh + " is not a whole sale");
		}
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		sdf.setLenient(false);
		Date date = sdf.parse(d[1]);
		double totalAmount = 0;
		for (int i = 5; i < d.length; i = i + 3) {
			int a = Integer.parseInt(d[i - 1]);
			int b = Integer.parseInt(d[i]);
			totalAmount = totalAmount + (a * b);
		}

		Purchase purchase = new Purchase(idh, date, totalAmount, d[2]);
		return purchase;
	}

	@Override
	public int compareTo(Object arg0) {
		Purchase p = (Purchase) arg0;
		if (this.getTotalAmount() < p.getTotalAmount()) {
			return -1;
		} else if (this.getTotalAmount() == p.getTotalAmount()) {
			return this.getUser().compareTo(p.getUser());
		} else {
			return 1;
		}
	}
}

package yaksha;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainClass {

	public static void main(String[] args) throws IOException, NumberFormatException, ParseException {
		BufferedReader buff = new BufferedReader(new InputStreamReader(System.in));
		List<Purchase> purchases = new ArrayList<Purchase>();
		System.out.println("Enter the number of purchase:");
		Integer purchaseCount = Integer.parseInt(buff.readLine());
		System.out.println("Enter the purchase details:");
		for (int i = 0; i < purchaseCount; i++) {
			String str = buff.readLine();
			try {
				Purchase p = Purchase.obtainPurchaseWithAmount(str);
				purchases.add(p);
				System.out.println("Purchase " + p.getId() + " is added to the list");
			} catch (InvalidWholeSaleException e) {
				System.out.println(e);
			}

		}
		Collections.sort(purchases);
		System.out.println("Whole sale purchases:");
		System.out.format("%-10s %-15s %s\n", "ID", "User", "Amount");

		for (int i = 0; i < purchases.size(); i++) {
			System.out.println(purchases.get(i));
		}
	}
}

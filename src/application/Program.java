package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import model.CarRental;
import model.Vehicle;
import service.BrazilTaxService;
import service.RentalService;

public class Program {

	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		System.out.print("Enter rental data:");
		System.out.println("Car model:");
		String model = sc.nextLine();

		System.out.println("Pickup (dd/MM/yyyy hh:mm):");
		Date start = sdf.parse(sc.nextLine());

		System.out.println("Return (dd/MM/yyyy hh:mm):");
		Date finish = sdf.parse(sc.nextLine());

		CarRental cr = new CarRental(start, finish, new Vehicle(model));

		System.out.println("Enter price per hour:");
		Double pricePerHour = sc.nextDouble();

		System.out.println("Enter price per day:");
		Double pricePerDay = sc.nextDouble();

		RentalService rs = new RentalService(pricePerHour, pricePerDay, new BrazilTaxService());

		rs.processInvoice(cr);

		System.out.println("INVOICE:");
		System.out.println("Basic payment: " + String.format("%.2f", cr.getInvoice().getBasicPayment()));
		System.out.println("Tax: " + String.format("%.2f", cr.getInvoice().getTax()));
		System.out.println("Total payment: " + String.format("%.2f", cr.getInvoice().totalPayment()));

		sc.close();
	}

}

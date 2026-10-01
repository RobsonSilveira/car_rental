package service;

import model.CarRental;
import model.Invoice;

public class RentalService {

	private Double pricePerHour;
	private Double pricePerDay;

	private TaxService taxService;

	public RentalService() {
	}

	public RentalService(Double pricePerHour, Double pricePerDay, TaxService taxService) {
		this.pricePerHour = pricePerHour;
		this.pricePerDay = pricePerDay;
		this.taxService = taxService;
	}

	public Double getPricePerHour() {
		return pricePerHour;
	}

	public void setPricePerHour(Double pricePerHour) {
		this.pricePerHour = pricePerHour;
	}

	public Double getPricePerDay() {
		return pricePerDay;
	}

	public void setPricePerDay(Double pricePerDay) {
		this.pricePerDay = pricePerDay;
	}

	public TaxService getTaxService() {
		return taxService;
	}

	public void setTaxService(TaxService taxService) {
		this.taxService = taxService;
	};

	public void processInvoice(CarRental carRental) {

		long ts = carRental.getStart().getTime();
		long tf = carRental.getFinish().getTime();

		double hours = (double) (tf - ts) / 1000 / 60 / 60;

		double basicPayment;

		if (hours <= 12) {
			basicPayment = Math.ceil(hours) * pricePerHour;
		} else {
			basicPayment = Math.ceil(hours / 24) * pricePerDay;
		}

		double tax = taxService.tax(basicPayment);

		carRental.setInvoice(new Invoice(basicPayment, tax));

	}

}

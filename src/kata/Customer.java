package kata;


import java.util.Enumeration;
import java.util.Vector;

class Customer {
	private String _name;
	private Vector<Rental> _rentals = new Vector<Rental>();

	public Customer(String name) {
		_name = name;
	};

	public void addRental(Rental arg) {
		_rentals.addElement(arg);
	}

	public String getName() {
		return _name;
	};

	public String statement() {
		double totalAmount = 0;
		int frequentRenterPoints = 0;
		Enumeration<Rental> rentals = _rentals.elements();
		String result = "Rental Record for " + getName() + "\n";
		while (rentals.hasMoreElements()) {
			Rental each = (Rental) rentals.nextElement();
			frequentRenterPoints += getFrequentRentalPoints(each);
			// show figures for this rental
			result += "\t" + each.getMovie().getTitle() + "\t"
					+ String.valueOf(each.getCharge()) + "\n";
			totalAmount += each.getCharge();
		}
		// add footer lines
		result += "Amount owed is " + String.valueOf(totalAmount) + "\n";
		result += "You earned " + String.valueOf(frequentRenterPoints)
				+ " frequent renter points";
		return result;
	}

	/**
	 * Get frequent rental points
	 * @param frequentRenterPoints
	 * @param each
	 * @return
	 */
	private int getFrequentRentalPoints(Rental each) {
		// add frequent renter points
		// add bonus for a two day new release rental
		if (isSpecialRentalPolicy(each))
			return 2;
		else return 1;
	}

	private boolean isSpecialRentalPolicy(Rental aRent) {
		return (aRent.getMovie().getPriceCode() == Movie.NEW_RELEASE)
				&& aRent.getDaysRented() > 1;
	}

	
}


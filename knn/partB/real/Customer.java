/**
 * The customer object. The field similarity is used to store similarity between
 * this customer and a test customer. Created by baixue on 16/3/31.
 */
public class Customer implements Comparable<Customer> {

	/**
	 * Configure the weight factors for similarity calculating here.
	 */
	private static double WEIGHT_SERVICE_TYPE = 1;
	private static double WEIGHT_CUSTOEMR = 1;
	private static double WEIGHT_MONTHLY_FEE = 1.5;
	private static double WEIGHT_BUDGET = 5;
	private static double WEIGHT_SIZE = 1;
	private static double WEIGHT_PROMOTION = 0.8;
	private static double WEIGHT_INTEREST_RATE = 1;
	private static double WEIGHT_PERIOD = 0.8;

	private int serviceType;
	private int customer;
	private double monthlyFee;
	private double budget;
	private int size;
	private int promotion;
	private double interest_rate;
	private double period;
	private String inputString;

	private double score;

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	private double similarity;

	private static double[][] distanceService = { { 1.0, 0.0, 0.0, 0.7, 0.5 },
	    { 0.0, 1.0, 0.8, 0.5, 0.6 }, { 0.0, 0.8, 1.0, 0.0, 0.5 },
	    { 0.7, 0.5, 0.0, 1.0, 0.0 }, { 0.5, 0.6, 0.5, 0.0, 1.0 } };
	private static double[][] distanceCustomer = { { 1.0, 0.8, 0.3, 0.6, 0.1 },
	    { 0.8, 1.0, 0.8, 0.8, 0.5 }, { 0.3, 0.8, 1.0, 0.0, 0.0 },
	    { 0.6, 0.8, 0.0, 1.0, 0.0 }, { 0.1, 0.5, 0.0, 0.0, 1.0 } };
	private static double[][] distanceSize = { { 1.0, 0.5, 0.0 },
	    { 0.5, 1.0, 0.5 }, { 0.0, 0.5, 1.0 } };
	private static double[][] distanceAPromotion = { { 1.0, 0.8, 0.5, 0.0 },
	    { 0.8, 1.0, 0.0, 0.0 }, { 0.5, 0.5, 1.0, 0.0 }, { 0.0, 0.0, 0.0, 1.0 } };

	/**
	 * Constructor of Customer object.
	 * 
	 * @param inputRow
	 *          a line in the input file the professor gave us
	 */
	public Customer(String inputRow) {
		inputString = inputRow;
		String[] input = inputRow.split(",");
		switch (input[0]) {
		case "Loan":
			serviceType = 0;
			break;
		case "Bank_Account":
			serviceType = 1;
			break;
		case "CD":
			serviceType = 2;
			break;
		case "Mortgage":
			serviceType = 3;
			break;
		case "Fund":
			serviceType = 4;

		}
		// System.out.println(input.length);
		switch (input[1]) {
		case "Business":
			customer = 0;
			break;
		case "Professional":
			customer = 1;
			break;
		case "Student":
			customer = 2;
			break;
		case "Doctor":
			customer = 3;
			break;
		case "Other":
			customer = 4;
		}
		monthlyFee = (Double.parseDouble(input[2]) - 0.34) / (17 - 0.34);
		budget = (Double.parseDouble(input[3]) - 0.16) / (8.94 - 0.16);
		switch (input[4]) {
		case "Small":
			size = 0;
			break;
		case "Medium":
			size = 1;
			break;
		case "Large":
			size = 2;
		}
		switch (input[5]) {
		case "Full":
			promotion = 0;
			break;
		case "Web&Email":
			promotion = 1;
			break;
		case "Web":
			promotion = 2;
			break;
		case "None":
			promotion = 3;
		}
		interest_rate = (Double.parseDouble(input[6]) - 0) / (6 - 0);
		period = (Double.parseDouble(input[7]) - 4) / (116 - 4);
		if (input.length == 9) {
			score = Double.parseDouble(input[8]);
		}
	}

	// /**
	// * initialize the weights factors.
	// * @param weights
	// */
	// public Customer(double[] weights) {
	// if (weights.length!=6) {
	// System.out.println("Please input 6 weights. \nNow use default weight.");
	// return;
	// }
	// WEIGHT_TYPE = weights[0];
	// WEIGHT_LIFESTYLE = weights[1];
	// WEIGHT_VACATION = weights[2];
	// WEIGHT_ECREDIT = weights[3];
	// WEIGHT_SALARY = weights[4];
	// WEIGHT_PROPERTY = weights[5];
	// }

	/**
	 * To update the similarity in the training customer object, by calculate the
	 * similarity between test customer and training customer.
	 * 
	 * @param test
	 */
	public void updateSimilarity(Customer test) {
		double d1 = distanceService[this.serviceType][test.serviceType];
		double d2 = distanceCustomer[this.customer][test.customer];
		double d3 = test.monthlyFee - this.monthlyFee;
		double d4 = test.budget - this.budget;
		double d5 = distanceSize[this.size][test.size];
		double d6 = distanceAPromotion[this.promotion][test.promotion];
		double d7 = test.interest_rate - this.interest_rate;
		double d8 = test.period - this.period;
		this.similarity = 1 / Math.sqrt(WEIGHT_SERVICE_TYPE * (1 - d1)
		    + WEIGHT_CUSTOEMR * (1 - d2) + WEIGHT_MONTHLY_FEE * d3 * d3
		    + WEIGHT_BUDGET * d4 * d4 + WEIGHT_SIZE * (1 - d5) + WEIGHT_PROMOTION
		    * (1 - d6) + WEIGHT_INTEREST_RATE * d7 * d7 + WEIGHT_PERIOD * d8 * d8);

	}

	public double getSimilarity() {
		return similarity;
	}

	public static void printWeights() {
		System.out.println("WEIGHT_SERVICE_TYPE: " + WEIGHT_SERVICE_TYPE);
		System.out.println("WEIGHT_CUSTOEMR: " + WEIGHT_CUSTOEMR);
		System.out.println("WEIGHT_MONTHLY_FEE: " + WEIGHT_MONTHLY_FEE);
		System.out.println("WEIGHT_BUDGET: " + WEIGHT_BUDGET);
		System.out.println("WEIGHT_SIZE: " + WEIGHT_SIZE);
		System.out.println("WEIGHT_PROMOTION: " + WEIGHT_PROMOTION);
		System.out.println("WEIGHT_INTEREST_RATE: " + WEIGHT_INTEREST_RATE);
		System.out.println("WEIGHT_PERIOD: " + WEIGHT_PERIOD);
	}

	@Override
	public String toString() {
		// return serviceType + "\t" + customer + "\t" + monthlyFee + "\t" + budget
		// + "\t" + size + "\t" + promotion + "\t" + interest_rate + "\t" + period
		// + "\t" + score + "\tSimilarity: " + similarity;
		return inputString;
	}

	@Override
	public int compareTo(Customer o) {
		if (o.similarity != this.similarity) {
			return Double.compare(o.similarity, this.similarity);
		}
		System.out.println("There is a tie similarity" + this.similarity);
		return 0;
	}
}

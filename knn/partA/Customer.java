/**
 * The customer object. The field similarity is used to store similarity between
 * this customer and a test customer. Created by baixue on 16/3/31.
 */
public class Customer implements Comparable<Customer> {

	/**
	 * Configure the weight factors for similarity calculating here.
	 */
	private static double WEIGHT_TYPE = 1;
	private static double WEIGHT_LIFESTYLE = 0.1;
	private static double WEIGHT_VACATION = 2;
	private static double WEIGHT_ECREDIT = 5;
	private static double WEIGHT_SALARY = 4;
	private static double WEIGHT_PROPERTY = 5;

	private String type;
	private String lifestyle;
	private double vacation;
	private double eCredit;
	private double salary;
	private double property;
	private int classNum;
	private double similarity;
	private String inputString;

	/**
	 * Constructor of Customer object.
	 * 
	 * @param inputRow
	 *          a line in the input file the professor gave us
	 */
	public Customer(String inputRow) {
		inputString = inputRow;
		String[] input = inputRow.split(",");
		type = input[0];
		lifestyle = input[1];
		vacation = (Double.parseDouble(input[2]) - 1) / (64 - 1);
		eCredit = (Double.parseDouble(input[3]) - 3) / (347 - 3);
		salary = (Double.parseDouble(input[4]) - 8.5076) / (31.75 - 8.5076);
		property = (Double.parseDouble(input[5]) - 0.008) / (17.8737 - 0.008);
		if (input.length == 7) {
			classNum = Integer.parseInt(input[6].substring(1));
		}
	}

	/**
	 * To update the similarity in the training customer object, by calculate the
	 * similarity between test customer and training customer.
	 * 
	 * @param test
	 */
	public void updateSimilarity(Customer test) {
		int d1 = 0;
		int d2 = 0;
		if (this.type.equals(test.type)) {
			d1 = 1;
		}
		if (this.lifestyle.equals(test.lifestyle)) {
			d2 = 1;
		}
		double d3 = test.vacation - this.vacation;
		double d4 = test.eCredit - this.eCredit;
		double d5 = test.salary - this.salary;
		double d6 = test.property - this.property;
		this.similarity = 1 / Math.sqrt(WEIGHT_TYPE * (1 - d1) + WEIGHT_LIFESTYLE
		    * (1 - d2) + WEIGHT_VACATION * d3 * d3 + WEIGHT_ECREDIT * d4 * d4
		    + WEIGHT_SALARY * d5 * d5 + WEIGHT_PROPERTY * d6 * d6);

	}

	public int getClassNum() {
		return classNum;
	}

	public double getSimilarity() {
		return similarity;
	}

	@Override
	public String toString() {
		// return type + "\t" + lifestyle + "\t" + vacation + "\t" + eCredit + "\t"
		// + salary + "\t" + property + "\tC" + classNum + "\tSimilarity: "
		// + similarity;
		return inputString;
	}

	public static void printWeights() {
		System.out.println("WEIGHT_TYPE: " + WEIGHT_TYPE);
		System.out.println("WEIGHT_LIFESTYLE: " + WEIGHT_LIFESTYLE);
		System.out.println("WEIGHT_VACATION: " + WEIGHT_VACATION);
		System.out.println("WEIGHT_ECREDIT: " + WEIGHT_ECREDIT);
		System.out.println("WEIGHT_SALARY: " + WEIGHT_SALARY);
		System.out.println("WEIGHT_PROPERTY: " + WEIGHT_PROPERTY);
	}

	@Override
	public int compareTo(Customer o) {
		if (o.similarity != this.similarity) {
			return Double.compare(o.similarity, this.similarity);
		}
		return Integer.compare(this.classNum, o.classNum);
	}
}

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Predict the Class data of given customer data by KNN method. You can modify
 * the file names in the main method in order to test. The default test method
 * is referring to knn pseudo code document, if you wish to use the method in
 * knn step, just do comment and uncomment in KNN.testData method. Created by
 * baixue on 16/3/31.
 */
public class KNN {
	private static final int K = 3;

	/**
	 * To put the customer data in the file into a given list.
	 * 
	 * @param list
	 *          a list to hold the data
	 * @param filename
	 *          input file name
	 */
	private void loadDataSet(List<Customer> list, String filename) {
		BufferedReader sbr = new BufferedReader(new InputStreamReader(
		    KNN.class.getResourceAsStream(filename)));
		String line;
		try {
			while ((line = sbr.readLine()) != null) {
				if (!line.startsWith("@data")) {
					continue;
				} else {
					break;
				}
			}
			while ((line = sbr.readLine()) != null) {
				list.add(new Customer(line));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Test data classification in KNN method.
	 * 
	 * @param trainList
	 *          train data list
	 * @param testList
	 *          test data list
	 * @throws UnsupportedEncodingException
	 * @throws FileNotFoundException
	 */
	private void testData(List<Customer> trainList, List<Customer> testList)
	    throws FileNotFoundException, UnsupportedEncodingException {
		double mseSum = 0;
		for (Customer test : testList) {
			double sum = 0;

			for (Customer train : trainList) {
				train.updateSimilarity(test);
			}
			Collections.sort(trainList);

			for (int i = 0; i < K; i++) {
				Customer c = trainList.get(i);
				sum += c.getScore();
			}

			double ave = sum * 1.0 / K;

			mseSum += Math.pow((test.getScore() - ave), 2);
			System.out.println("test case: \n" + test.toString());
			System.out.printf("Predicted result: %.2f\n", ave);
		}
		Customer.printWeights();
		System.out.println(mseSum * 1.0 / testList.size());

	}

	/**
	 * main method.
	 * 
	 * @param args
	 *          weight of type, lifestyle, vacation , eCredit, salary, property
	 * @throws UnsupportedEncodingException
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException,
	    UnsupportedEncodingException {
		KNN k = new KNN();
		LinkedList<Customer> trainList = new LinkedList<>();
		LinkedList<Customer> testList = new LinkedList<>();
		k.loadDataSet(trainList, args[0]);
		k.loadDataSet(testList, args[1]);
		k.testData(trainList, testList);

	}
}

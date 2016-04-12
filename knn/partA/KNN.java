import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
	 */
	private void testData(List<Customer> trainList, List<Customer> testList) {
		double totalTestAmount = testList.size();
		double correctTestAmount = 0;
		for (Customer test : testList) {
			System.out.println("test case: \n" + test.toString());
			for (Customer train : trainList) {
				train.updateSimilarity(test);
			}
			Collections.sort(trainList);
			double[] counting = new double[6];
			for (int i = 0; i < K; i++) {
				Customer c = trainList.get(i);

				/*
				 * Uncomment this line and comment the next line if you want to try
				 * predicting by sum of number instead of similarity
				 */
				// counting[c.getClassNum()]++;
				counting[c.getClassNum()] += c.getSimilarity();
			}

			double max = 0;
			int maxClassNum = 0;
			for (int i = 1; i < counting.length; i++) {
				if (counting[i] > max) {
					maxClassNum = i;
					max = counting[i];
				}
			}
			System.out.println("Predicted result: C" + maxClassNum + "\n");
			if (test.getClassNum() == maxClassNum) {
				// Prediction is correct
				correctTestAmount++;
			}
		}
		Customer.printWeights();
		double correctRate = correctTestAmount / totalTestAmount * 100;
		System.out.printf("Correct Rate : %.2f%% \n", correctRate);
	}

	public static void main(String[] args) {
		// args[0] should be path to train data
		// args[1] should be path to test data

		KNN k = new KNN();
		LinkedList<Customer> trainList = new LinkedList<>();
		LinkedList<Customer> testList = new LinkedList<>();
		k.loadDataSet(trainList, args[0]);
		k.loadDataSet(testList, args[1]);
		k.testData(trainList, testList);

	}
}

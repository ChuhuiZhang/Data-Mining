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

			for (Customer train : trainList) {
				train.updateSimilarity(test);
			}
			Collections.sort(trainList);
			double[] counting = new double[2];
			for (int i = 0; i < K; i++) {
				Customer c = trainList.get(i);

				/*
				 * Comment this line and uncomment the next line if you want to try
				 * predicting by sum of similarity
				 */
				// counting[c.getLabel()]++;
				counting[c.getLabel()] += c.getSimilarity();
			}

			int predictedLabel = 0;
			predictedLabel = counting[0] > counting[1] ? 0 : 1;
			System.out.println("test case: \n" + test.toString());
			System.out.println("Predicted result: " + predictedLabel);

			if (test.getLabel() == predictedLabel) {
				// Prediction is correct

				correctTestAmount++;
			}

		}
		double correctRate = correctTestAmount / totalTestAmount * 100;
		Customer.printWeights();
		System.out.printf("Correct Rate : %.2f%%\n", correctRate);
	}

	/**
	 * main method.
	 * 
	 * @param args
	 *          weight of type, lifestyle, vacation , eCredit, salary, property
	 */
	public static void main(String[] args) {

		KNN k = new KNN();
		LinkedList<Customer> trainList = new LinkedList<>();
		LinkedList<Customer> testList = new LinkedList<>();
		k.loadDataSet(trainList, args[0]);
		k.loadDataSet(testList, args[1]);
		k.testData(trainList, testList);

	}
}

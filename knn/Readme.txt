1. How to Run	
	- Under partA or binary or real folder, input command line:
		sh makefile.sh

	It will compile the java files and run java with train file path and test file path.

2.	Cross Validation Results
	-partA
		test#	correctness
		1		94.59% 
		2		83.78% 
		3		94.59% 
		4		94.59% 
		5		84.21% 

	-partB binary
		test#	correctness
		1		90.63%
		2		90.63%
		3		90.63%
		4		93.75%
		5		90.63%

	-partB real
		test#	MSE
		1 		21.45142534722223
		2		15.450097569444436
		3		30.31269201388889
		4		32.42311979166667
		5		24.20842604166667

3.	Folder Structure
	-|partA
		-Customer.java
		-KNN.java
		-randomProdSelect.py
		-makefile.sh
	
	-|partB

		-|binary
			-Customer.java
			-KNN.java
			-randomProdIntro_binary.py
			-makefile.sh
			

		-|real
			-Customer.java
			-KNN.java
			-randomProdIntro_real.py
			-makefile.sh
4. 	Data Generation
	
	We firstly normalized each attribute value to the scale [0,1]. In part A, the transformation is based on given transformation matrix. In part B, we generated the transformation matrix by ourselves.

	After data normalization, we prepared datasets for cross validation. We shuffled the data and divided them evenly into 5 smaller dataset. In each train-test folder, there is a train dataset (4 in 5 sets) and a test dataset (rest 1 in 5 sets).

5.	Prediction Tuning
	
	- For part A and label prediction in part B, at the beginning, we used the class with most neighbors among k nearest neighbors as the predicted class. After many rounds of benchmarks, we found measuring similarity is more accurate.
	
	- For score prediction in part B, we used the average score of k nearest neighbors’ scores to predict the score and used the MSE to illustrate prediction accuracy.
	
	- To increase the accuracy, we benchmarked with different weights many times.
	
	- We also conducted cross validation with different input dataset combination.




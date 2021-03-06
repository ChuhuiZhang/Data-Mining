from random import randint
import random

infile = open("trainProdIntro_binary.txt", "r")
# create 5 share of data (evenly distributed)
output1 = open("randomProdIntro_binary_1.txt", "w")
output2 = open("randomProdIntro_binary_2.txt", "w")
output3 = open("randomProdIntro_binary_3.txt", "w")
output4 = open("randomProdIntro_binary_4.txt", "w")
output5 = open("randomProdIntro_binary_5.txt", "w")

lines = infile.read().splitlines()
# using random shuffle to make the pick randomized
random.shuffle(lines)

i = 0
while i < 32:
    output1.write(lines[i] + '\n')
    i += 1
    
while i < 64:
    output2.write(lines[i] + '\n')
    i += 1

while i < 96:
    output3.write(lines[i] + '\n')
    i += 1

while i < 128:
    output4.write(lines[i] + '\n')
    i += 1

while i < 160:
    output5.write(lines[i] + '\n')
    i += 1

output1.close()
output2.close()
output3.close()
output4.close()
output5.close()
infile.close()


# perform combination to merge 
# need to comment out one file at a time, then merge the other four
# repeat this procedure 5 times to make sure cover the whole data file
output1 = open("randomProdIntro_binary_1.txt", "r")
output2 = open("randomProdIntro_binary_2.txt", "r")
output3 = open("randomProdIntro_binary_3.txt", "r")
output4 = open("randomProdIntro_binary_4.txt", "r")
output5 = open("randomProdIntro_binary_5.txt", "r")

lines1 = output1.read().splitlines()
lines2 = output2.read().splitlines()
lines3 = output3.read().splitlines()
lines4 = output4.read().splitlines()
lines5 = output5.read().splitlines()
result_train = open("randomProdIntro_binary_train.txt", "w")

for j in range(0, 32):
    result_train.write(lines1[j] + '\n')
for j in range(0, 32):
   result_train.write(lines2[j] + '\n')
for j in range(0, 32):
    result_train.write(lines3[j] + '\n')
for j in range(0, 32):
    result_train.write(lines4[j] + '\n')
for j in range(0, 32):
    result_train.write(lines5[j] + '\n')

output1.close()
output2.close()
output3.close()
output4.close()
output5.close()
result_train.close()


import csv
import math
import random


class Wine:  

    def __init__(self, catagory, Alcohol, Malic, Ash, Alcalinity, Magnesium, phenols, Flavanoids, Nonflavanoid, Proanthocyanins, Color, Hue, OD280, Proline):  
        self.catagory = catagory
        self.Alcohol = Alcohol
        self.Malic = Malic
        self.Ash = Ash
        self.Alcalinity = Alcalinity
        self.Magnesium = Magnesium
        self.phenols = phenols
        self.Flavanoids = Flavanoids
        self.Nonflavanoid = Nonflavanoid
        self.Proanthocyanins = Proanthocyanins
        self.Color = Color
        self.Hue = Hue 
        self.OD280 = OD280
        self.Proline = Proline
    
class Probability:  
    mean = 0
    stdDiv = 0
    def __init__(self, mean,  stdDiv):  
        self.mean = mean
        self.stdDiv = stdDiv

    
    def probability(self,x):  
        return (1.0/(self.stdDiv*math.sqrt(2*math.pi))) * math.exp((-1.0/2)*((x-self.mean)/self.stdDiv)**2)


def substanceData(data, substance, start, end):
    subData = []
    if substance == 1:
        for i in range(start,end+1):
            subData.append(data[i].Alcohol)
    elif substance == 2:
        for i in range(start,end+1):
            subData.append(data[i].Malic)
    elif substance == 3:
        for i in range(start,end+1):
            subData.append(data[i].Ash)
    elif substance == 4:
        for i in range(start,end+1):
            subData.append(data[i].Alcalinity)
    elif substance == 5:
        for i in range(start,end+1):
            subData.append(data[i].Magnesium)
    elif substance == 6:
        for i in range(start,end+1):
            subData.append(data[i].phenols)
    elif substance == 7:
        for i in range(start,end+1):
            subData.append(data[i].Flavanoids)
    elif substance == 8:
        for i in range(start,end+1):
            subData.append(data[i].Nonflavanoid)
    elif substance == 9:
        for i in range(start,end+1):
            subData.append(data[i].Proanthocyanins)
    elif substance == 10:
        for i in range(start,end+1):
            subData.append(data[i].Color)
    elif substance == 11:
        for i in range(start,end+1):
            subData.append(data[i].Hue)
    elif substance == 12:
        for i in range(start,end+1):
            subData.append(data[i].OD280)
    elif substance == 13:
        for i in range(start,end+1):
            subData.append(data[i].Proline)
    return subData

def getMean(values):
    mean = 0
    for i in range(0,len(values)):
        mean += values[i]
    return mean/len(values)

def getStdDiv(values):
    mean = getMean(values)
    sum = 0
    for i in range(0,len(values)):
        sum += (values[i] - mean)**2
    return math.sqrt(sum/(len(values)-1))


#main code starts here
#list of wine instances
type1 = []
type2 = []
type3 = []
#list of normal distribution instances
type1_prob = []
type2_prob = []
type3_prob = []

#read in entire data set
f = open('Wine.csv', 'r')
with f:

    reader = csv.reader(f,quoting=csv.QUOTE_NONNUMERIC)

    for row in reader:
        wine = Wine(row[0],row[1],row[2],row[3],row[4],row[5],row[6],row[7],row[8],row[9],row[10],row[11],row[12],row[13])
        if row[0] == 1:
          type1.append(wine)
        elif row[0] == 2:
          type2.append(wine)
        else:
          type3.append(wine)
'''
prior1 = 59.0/178
prior2 = 71.0/178
prior3 = 47.0/178
'''
prior1 = len(type1)/(len(type1)+len(type2)+len(type3))
prior2 = len(type2)/(len(type1)+len(type2)+len(type3))
prior3 = len(type3)/(len(type1)+len(type2)+len(type3))
#spilt data into train and test
f2 = open('test.csv', 'w')
with f2:

    writer = csv.writer(f2)
    
    for i in range(18):
        r = random.randint(0,len(type1)-1)
        w = type1.pop(r)
        instance = []
        instance.append(w.catagory)
        instance.append(w.Alcohol)
        instance.append(w.Malic)
        instance.append(w.Ash)
        instance.append(w.Alcalinity)
        instance.append(w.Magnesium)
        instance.append(w.phenols)
        instance.append(w.Flavanoids)
        instance.append(w.Nonflavanoid)
        instance.append(w.Proanthocyanins)
        instance.append(w.Color)
        instance.append(w.Hue)
        instance.append(w.OD280)
        instance.append(w.Proline)
        writer.writerow(instance)
    for i in range(18):
        r = random.randint(0,len(type2)-1)
        w = type2.pop(r)
        instance = []
        instance.append(w.catagory)
        instance.append(w.Alcohol)
        instance.append(w.Malic)
        instance.append(w.Ash)
        instance.append(w.Alcalinity)
        instance.append(w.Magnesium)
        instance.append(w.phenols)
        instance.append(w.Flavanoids)
        instance.append(w.Nonflavanoid)
        instance.append(w.Proanthocyanins)
        instance.append(w.Color)
        instance.append(w.Hue)
        instance.append(w.OD280)
        instance.append(w.Proline)
        writer.writerow(instance)
    for i in range(18):
        r = random.randint(0,len(type3)-1)
        w = type3.pop(r)
        instance = []
        instance.append(w.catagory)
        instance.append(w.Alcohol)
        instance.append(w.Malic)
        instance.append(w.Ash)
        instance.append(w.Alcalinity)
        instance.append(w.Magnesium)
        instance.append(w.phenols)
        instance.append(w.Flavanoids)
        instance.append(w.Nonflavanoid)
        instance.append(w.Proanthocyanins)
        instance.append(w.Color)
        instance.append(w.Hue)
        instance.append(w.OD280)
        instance.append(w.Proline)
        writer.writerow(instance)
f2 = open('train.csv', 'w')
with f2:

    writer = csv.writer(f2)
    
    for i in range(len(type1)):
        w = type1[i]
        instance = []
        instance.append(w.catagory)
        instance.append(w.Alcohol)
        instance.append(w.Malic)
        instance.append(w.Ash)
        instance.append(w.Alcalinity)
        instance.append(w.Magnesium)
        instance.append(w.phenols)
        instance.append(w.Flavanoids)
        instance.append(w.Nonflavanoid)
        instance.append(w.Proanthocyanins)
        instance.append(w.Color)
        instance.append(w.Hue)
        instance.append(w.OD280)
        instance.append(w.Proline)
        writer.writerow(instance)
    for i in range(len(type2)):
        w = type2[i]
        instance = []
        instance.append(w.catagory)
        instance.append(w.Alcohol)
        instance.append(w.Malic)
        instance.append(w.Ash)
        instance.append(w.Alcalinity)
        instance.append(w.Magnesium)
        instance.append(w.phenols)
        instance.append(w.Flavanoids)
        instance.append(w.Nonflavanoid)
        instance.append(w.Proanthocyanins)
        instance.append(w.Color)
        instance.append(w.Hue)
        instance.append(w.OD280)
        instance.append(w.Proline)
        writer.writerow(instance)
    for i in range(len(type3)):
        w = type3[i]
        instance = []
        instance.append(w.catagory)
        instance.append(w.Alcohol)
        instance.append(w.Malic)
        instance.append(w.Ash)
        instance.append(w.Alcalinity)
        instance.append(w.Magnesium)
        instance.append(w.phenols)
        instance.append(w.Flavanoids)
        instance.append(w.Nonflavanoid)
        instance.append(w.Proanthocyanins)
        instance.append(w.Color)
        instance.append(w.Hue)
        instance.append(w.OD280)
        instance.append(w.Proline)
        writer.writerow(instance)
#get probability distributions (Normal)
for i in range(1,14):
    data = substanceData(type1, i, 0, len(type1)-1)
    p = Probability(getMean(data),getStdDiv(data))
    type1_prob.append(p)
    
for i in range(1,14):
    data = substanceData(type2, i, 0, len(type2)-1)
    p = Probability(getMean(data),getStdDiv(data))
    type2_prob.append(p)
    
for i in range(1,14):
    data = substanceData(type3, i, 0, len(type3)-1)
    p = Probability(getMean(data),getStdDiv(data))
    type3_prob.append(p)
    
#start inference
print('start inference input data')
correct = 0
count = 0
f = open('test.csv', 'r')
with f:

    reader = csv.reader(f,quoting=csv.QUOTE_NONNUMERIC)
    
    for row in reader:
        count += 1.0
        prior1 = 59.0/178
        prior2 = 71.0/178
        prior3 = 47.0/178
        for i in range(1,14):
            prior1 *= type1_prob[i-1].probability(x=row[i])
            prior2 *= type2_prob[i-1].probability(x=row[i])
            prior3 *= type3_prob[i-1].probability(x=row[i])
        if prior1 > prior2 and prior1 > prior3:
            if row[0] == 1:
                correct += 1
                print("actual: {} classified as {} correct".format(row[0],1))
            else:
                print("actual: {} classified as {} wrong".format(row[0],1))
        elif prior2 > prior1 and prior2 > prior3:
            if row[0] == 2:
                correct += 1
                print("actual: {} classified as {} correct".format(row[0],2))
            else:
                print("actual: {} classified as {} wrong".format(row[0],2))
        else:
            if row[0] == 3:
                correct += 1
                print("actual: {} classified as {} correct".format(row[0],3))
            else:
                print("actual: {} classified as {} wrong".format(row[0],3))
accuracy = correct/count
print("accuracy = {}".format(accuracy))

#plot figures
import matplotlib.pyplot as plt
import numpy as np
import scipy.stats as stats
mu = type1_prob[0].mean
variance = type1_prob[0].stdDiv**2
sigma = type1_prob[0].stdDiv
x = np.linspace(mu - 3*sigma, mu + 3*sigma, 100)
plt.plot(x, stats.norm.pdf(x, mu, sigma),label = "prior")

mu = type1_prob[1].mean
variance = type1_prob[1].stdDiv**2
sigma = type1_prob[1].stdDiv
x = np.linspace(mu - 3*sigma, mu + 3*sigma, 100)
plt.plot(x, stats.norm.pdf(x, mu, sigma),label = "likelihood")

mu = (type1_prob[1].mean*(type1_prob[0].stdDiv)**2+type1_prob[0].mean*(type1_prob[1].stdDiv)**2)/((type1_prob[0].stdDiv)**2+(type1_prob[1].stdDiv)**2)
variance = (math.sqrt(((type1_prob[1].stdDiv**2)*(type1_prob[0].stdDiv**2))/((type1_prob[1].stdDiv**2)+(type1_prob[0].stdDiv**2))))**2
sigma = (math.sqrt(((type1_prob[1].stdDiv**2)*(type1_prob[0].stdDiv**2))/((type1_prob[1].stdDiv**2)+(type1_prob[0].stdDiv**2))))
x = np.linspace(mu - 3*sigma, mu + 3*sigma, 100)
plt.plot(x, stats.norm.pdf(x, mu, sigma),label = "posterior")
plt.xlabel('x')
plt.ylabel('Normal Distribution')
plt.legend()
plt.show()
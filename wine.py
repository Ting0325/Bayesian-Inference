import csv
import math
f = open('Wine.csv', 'r')

class Wine:  
    '''
    catagory
    Alcohol
    Malic
    Ash
    Alcalinity
    Magnesium
    phenols
    Flavanoids
    Nonflavanoid
    Proanthocyanins
    Color
    Hue
    OD280
    Proline  
    ''' 
    # init method or constructor   
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
    
    # Sample Method   
    def say_hi(self):  
        print('Hello, my name is')  
'''    
p = Wine('Nikhil')  
p.say_hi()  

'''
 
class Probability:  
    mean = 0
    stdDiv = 0
    # init method or constructor   
    def __init__(self, mean,  stdDiv):  
        self.mean = mean
        self.stdDiv = stdDiv

    
    # Sample Method   
    def probability(self,x):  
        return (1.0/(self.stdDiv*math.sqrt(2*math.pi))) * math.exp(pow(((x-self.mean)/self.stdDiv),2)*(-1.0/2))
'''    
p = Wine('Nikhil')  
p.say_hi()  

'''
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
        sum += (values[i] - mean)**2 #* (values.get[i]] - mean);
    return mean/len(values)
type1 = []
type2 = []
type3 = []
type1_prob = []
type2_prob = []
type3_prob = []
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

        #print(row)
prior1 = 59.0/178
prior2 = 71.0/178
prior3 = 47.0/178

# get joint probilities
for i in range(1,14):
    data = substanceData(type1, i, 0, len(type1)-1)
    #print(len(data))
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
print('start inference input data')
correct = 0
count = 0
f = open('Wine.csv', 'r')
with f:

    reader = csv.reader(f,quoting=csv.QUOTE_NONNUMERIC)
    
    for row in reader:
        count += 1
        prior1 = 59.0/178
        prior2 = 71.0/178
        prior3 = 47.0/178
        #wine = Wine(row[0],row[1],row[2],row[3],row[4],row[5],row[6],row[7],row[8],row[9],row[10],row[11],row[12],row[13])
        for i in range(13):
            prior1 *= type1_prob[i].probability(row[i])
            prior2 *= type2_prob[i].probability(row[i])
            prior3 *= type3_prob[i].probability(row[i])
            print(type1_prob[i].probability(row[i]))

        if prior1 > prior2 and prior1 > prior3:
            if row[0] == 1:
                correct += 1
                print('correct')
            else:
                print('wrong')
        elif prior2 > prior1 and prior2 > prior3:
            if row[0] == 2:
                correct += 1
                print('correct')
            else:
                print('wrong')
        else:
            if row[0] == 3:
                correct += 1
                print('correct')
            else:
                print('wrong')
print('accuracy : ' ,correct/count)
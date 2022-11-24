import cv2
import sys

'''
* Parameter (This File Directory, Image File Directory Uploaded ) 
'''

img = cv2.imread(sys.argv[1])
cv2.imshow('img', img)
cv2.waitKey()

'''
implement Machine Learning ...
'''

carName0 = 'sm3 2017'
carName1 = 'BMW 5시리즈 2020'
carName2 = '현대 아반떼 2019'

print(carName0)
print(carName1)
print(carName2)

import cv2
import sys
img = cv2.imread(sys.argv[1])
cv2.imshow('img', img)
cv2.waitKey()

'''
implement Machine Learning ...
'''

carName = 'BMW 5시리즈 2020'
print(carName)
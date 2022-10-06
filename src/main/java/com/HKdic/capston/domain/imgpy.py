import cv2

img = cv2.imread("C:/Users/whipbaek/Projects/upload/src/main/resources/static/image/testFile.jpg")
cv2.imshow('img',img)
cv2.waitKey()
print('이미지 불러오기 완료')
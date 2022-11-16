import time
import sys
from selenium.webdriver.common.keys import Keys
from selenium import webdriver
from webdriver_manager.chrome import ChromeDriverManager
from selenium.webdriver.chrome.service import Service
from selenium.webdriver.common.by import By
import urllib.request  # ----동근

# ----동근


class car_info():
    def __init__(self, name, year, price, exterior, fuel, displacement, efficiency, capacity):
        self.name = name
        self.year = year
        self.price = price
        self.exterior = exterior
        self.fuel = fuel
        self.displacement = displacement
        self.efficiency = efficiency
        self.capacity = capacity


options = webdriver.ChromeOptions()
options.add_argument('--headless') # BackGround 작업
driver = webdriver.Chrome(service=Service(ChromeDriverManager().install()),options=options) # driver 자동 Download
# options.add_argument('--window-size=1800,800') # Window Size
#driver = webdriver.Chrome(service=Service(ChromeDriverManager().install()))

# -----동근, time.sleep대신 사용. 드라이버가 요소를 찾기까지 10초의 시간을 줌. 10초전에 찾으면 대기가 끝남
driver.implicitly_wait(10)
driver.get('https://www.google.com/')
driver.maximize_window()


daum = '다음 자동차 '
keyword = sys.argv[1]
# keyword = "sm3 2017"

driver.find_element(
    By.XPATH, '/html/body/div[1]/div[3]/form/div[1]/div[1]/div[1]/div/div[2]/input').send_keys(daum + keyword)
driver.find_element(
    By.XPATH, '/html/body/div[1]/div[3]/form/div[1]/div[1]/div[1]/div/div[2]/input').send_keys(Keys.RETURN)


driver.find_element(
    By.XPATH, '/html/body/div[7]/div/div[11]/div/div[2]/div[2]/div/div/div[1]/div/div/div[1]/div/a').click()


element = driver.find_element(By.CLASS_NAME, 'box_model')
element_png = element.screenshot_as_png
with open(sys.argv[2]+'car.png', "wb") as file:
    file.write(element_png)               #-----동근
# with open("./car.png", "wb") as file:
#     file.write(element_png)

# -----동근
# 모델명, 연식
title_model = driver.find_element(By.CLASS_NAME, "tit_model").text
year = driver.find_element(By.CLASS_NAME, "link_selected").text


# 이미지 저장 (car_img.jpg)
image = driver.find_element(
    By.CSS_SELECTOR, "#mArticle > div.section_photoview > div > div > div.photo_body > div > a.link_thumb.image_view.\#thumbnail > img")
car_img = image.get_attribute('src')
urllib.request.urlretrieve(car_img, sys.argv[2]+'car_img.jpg')

# 가격, 외장, 연로, 배기량, 연비, 정원
price = driver.find_element(
    By.CSS_SELECTOR, "#cSub > div > div > div.box_model > div.info_model > table:nth-child(1) > tbody > tr:nth-child(1) > td").text
exterior = driver.find_element(
    By.CSS_SELECTOR, "#cSub > div > div > div.box_model > div.info_model > table:nth-child(2) > tbody > tr:nth-child(1) > td").text
fuel = driver.find_element(
    By.CSS_SELECTOR, "#cSub > div > div > div.box_model > div.info_model > table:nth-child(1) > tbody > tr:nth-child(2) > td").text
displacement = driver.find_element(
    By.CSS_SELECTOR, "#cSub > div > div > div.box_model > div.info_model > table:nth-child(2) > tbody > tr:nth-child(2) > td").text
efficiency = driver.find_element(
    By.CSS_SELECTOR, "#cSub > div > div > div.box_model > div.info_model > table:nth-child(1) > tbody > tr:nth-child(3) > td").text
capacity = driver.find_element(
    By.CSS_SELECTOR, "#cSub > div > div > div.box_model > div.info_model > table:nth-child(2) > tbody > tr:nth-child(3) > td").text

car = car_info(title_model, year, price, exterior,
               fuel, displacement, efficiency, capacity)

# print(car.name+car.year)
# print("가격: "+car.price)
# print("외장: " + car.exterior)
# print("연료: "+car.fuel)
# print("배기량: "+car.displacement)
# print("연비: "+car.efficiency)
# print("정원: "+car.capacity+"명")

print(car.name+car.year,',',car.price,',',car.exterior,',',car.fuel,',',car.displacement,',',car.efficiency,',',car.capacity);

driver.close()
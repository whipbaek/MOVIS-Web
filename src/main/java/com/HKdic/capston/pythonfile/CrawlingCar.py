import time
import sys
from selenium.webdriver.common.keys import Keys
from selenium import webdriver
from webdriver_manager.chrome import ChromeDriverManager
from selenium.webdriver.chrome.service import Service
from selenium.webdriver.common.by import By



options = webdriver.ChromeOptions()
# options.add_argument('--headless') # BackGround 작업
driver = webdriver.Chrome(service=Service(ChromeDriverManager().install()),options=options) # driver 자동 Download
# options.add_argument('--window-size=1800,800') # Window Size
#driver = webdriver.Chrome(service=Service(ChromeDriverManager().install()))


driver.get('https://www.google.com/')
driver.maximize_window()
time.sleep(2)
daum = '다음 자동차 '
keyword = sys.argv[1]

driver.find_element(By.XPATH, '/html/body/div[1]/div[3]/form/div[1]/div[1]/div[1]/div/div[2]/input').send_keys(daum + keyword)
driver.find_element(By.XPATH, '/html/body/div[1]/div[3]/form/div[1]/div[1]/div[1]/div/div[2]/input').send_keys(Keys.RETURN)
time.sleep(2)

driver.find_element(By.XPATH,'/html/body/div[7]/div/div[11]/div/div[2]/div[2]/div/div/div[1]/div/div/div[1]/div/a').click()
time.sleep(4)

element = driver.find_element(By.CLASS_NAME, 'box_model')
element_png = element.screenshot_as_png
with open("C:\\Users\\jibae\\Projects\\capston\\images\\car.png", "wb") as file:
    file.write(element_png)

print('end!')

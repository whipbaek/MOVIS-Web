#import time
import sys
from selenium.webdriver.common.keys import Keys
from selenium import webdriver
from webdriver_manager.chrome import ChromeDriverManager
from selenium.webdriver.chrome.service import Service
from selenium.webdriver.common.by import By
import urllib.request  # ----동근
from multiprocessing import Queue
from threading import Thread

# ----동근


class car_info():
    def __init__(self, name, year, price, exterior, fuel, displacement, efficiency, capacity, rank):
        self.name = name
        self.year = year
        self.price = price
        self.exterior = exterior
        self.fuel = fuel
        self.displacement = displacement
        self.efficiency = efficiency
        self.capacity = capacity
        self.rank = rank


def multi_crawling(keyword, path, rank, carlist):
    options = webdriver.ChromeOptions()
    options.add_argument('--headless') # BackGround 작업
    options.add_argument("disable-gpu")
    options.add_argument("disable-infobars")
    options.add_argument("--disable-extensions")
    options.add_argument('--no-sandbox')
    options.add_argument("--single-process")
    options.add_argument("--disable-dev-shm-usage")
    prefs = {'profile.default_content_setting_values': {'cookies' : 2, 'images': 2, 'plugins' : 2, 'popups': 2, 'geolocation': 2, 'notifications' : 2, 'auto_select_certificate': 2, 'fullscreen' : 2, 'mouselock' : 2, 'mixed_script': 2, 'media_stream' : 2, 'media_stream_mic' : 2, 'media_stream_camera': 2, 'protocol_handlers' : 2, 'ppapi_broker' : 2, 'automatic_downloads': 2, 'midi_sysex' : 2, 'push_messaging' : 2, 'ssl_cert_decisions': 2, 'metro_switch_to_desktop' : 2, 'protected_media_identifier': 2, 'app_banner': 2, 'site_engagement' : 2, 'durable_storage' : 2}}
    options.add_experimental_option('prefs', prefs)
    #driver = webdriver.Chrome(service=Service(ChromeDriverManager().install()), options=options)  # driver 자동 Download
    driver = webdriver.Chrome("/home/hojun/chromedriver", options=options)    # 로컬 driver
    # options.add_argument('--window-size=1800,800') # Window Size
    #driver = webdriver.Chrome(service=Service(ChromeDriverManager().install()))

    # -----동근, time.sleep대신 사용. 드라이버가 요소를 찾기까지 10초의 시간을 줌. 10초전에 찾으면 대기가 끝남
    driver.implicitly_wait(10)
    driver.get('https://www.google.com/')
    driver.maximize_window()

    daum = '다음 자동차 '

    driver.find_element(
        By.XPATH, '/html/body/div[1]/div[3]/form/div[1]/div[1]/div[1]/div/div[2]/input').send_keys(daum + keyword)
    driver.find_element(
        By.XPATH, '/html/body/div[1]/div[3]/form/div[1]/div[1]/div[1]/div/div[2]/input').send_keys(Keys.RETURN)

    driver.find_element(
        By.XPATH, '/html/body/div[7]/div/div[11]/div/div[2]/div[2]/div/div/div[1]/div/div/div[1]/div/a').click()

    element = driver.find_element(By.CLASS_NAME, 'box_model')
    element_png = element.screenshot_as_png

    # -----동근
    # 모델명, 연식
    title_model = driver.find_element(By.CLASS_NAME, "tit_model").text
    year = driver.find_element(By.CLASS_NAME, "link_selected").text
    # 이미지 저장 (car_img.jpg)
    image = driver.find_element(
        By.CSS_SELECTOR, "#mArticle > div.section_photoview > div > div > div.photo_body > div > a.link_thumb.image_view.\#thumbnail > img")
    car_img = image.get_attribute('src')
    urllib.request.urlretrieve(car_img, path+'car_img'+str(rank)+'.jpg')

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
                   fuel, displacement, efficiency, capacity, rank)
    carlist.append(car)

    driver.close()
    return



if __name__ == "__main__":

    key1, key2, key3 = sys.argv[1], sys.argv[2], sys.argv[3]
    path = sys.argv[4]
    carlist = []
    # start = time.time()

    th1 = Thread(target=multi_crawling, args=(key1, path, 0, carlist))
    th2 = Thread(target=multi_crawling, args=(key2, path, 1, carlist))
    th3 = Thread(target=multi_crawling, args=(key3, path, 2, carlist))

    th1.start()
    th2.start()
    th3.start()
    th1.join()
    th2.join()
    th3.join()

    carlist.sort(key=lambda x: x.rank)

    for car in carlist:
        print(car.name+car.year)
        print("가격: "+car.price)
        print("외장: " + car.exterior)
        print("연료: "+car.fuel)
        print("배기량: "+car.displacement)
        print("연비: "+car.efficiency)
        print("정원: "+car.capacity+"명")

    # end = time.time()
    # print(f"{end - start:.5f} sec")

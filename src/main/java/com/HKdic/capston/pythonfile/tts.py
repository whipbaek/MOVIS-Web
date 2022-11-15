# from selenium.webdriver.common.keys import Keys
from selenium import webdriver
# from webdriver_manager.chrome import ChromeDriverManager
# from selenium.webdriver.chrome.service import Service
from selenium.webdriver.common.by import By

from selenium.webdriver.support import expected_conditions as EC
import os


def click_infinity(driver, selector):
    while True:
        try:
            driver.find_element(
                By.CSS_SELECTOR, selector).click()
            break
        except:
            continue


ID = 'tyt0815@naver.com'
PWD = 'rudqnreowhdvm2'
model_name = '자동차5'  # 자동차 모델명
txt = '5이'   # 내용(자동차 설명 등)
# 현재경로의 tts_mp3폴더에 저장(맥이랑 윈도우랑 경로적는 양식이 다를수도...?)
download_path = os.getcwd() + '/tts_mp3'

if os.path.isfile('./tts_mp3/'+model_name+'.mp3') == True:
    exit()

options = webdriver.ChromeOptions()
options.add_experimental_option("prefs", {      # 다운로드 경로 설정
    "download.default_directory": download_path,
    "download.prompt_for_download": False,
    "download.directory_upgrade": True,
    "safebrowsing.enabled": True
})
options.add_argument('--headless')  # BackGround 작업
# driver = webdriver.Chrome(service=Service(
#     ChromeDriverManager().install()), options=options)  # driver 자동 Download
driver = webdriver.Chrome(
    "/opt/homebrew/bin/chromedriver", options=options)    # 로컬 driver

driver.implicitly_wait(10)
driver.get('https://app.typecast.ai/ko/editor/636b2513b58379d5c6b6b437')

# 로그인
click_infinity(driver, '#app > div.no-gutters.h-100.d-flex.justify-content-center.view > section > form > div > section > div:nth-child(3) > button')
driver.find_element(By.CSS_SELECTOR, '#email').send_keys(ID)
driver.find_element(By.CSS_SELECTOR, '#password').send_keys(PWD)
click_infinity(driver, '#app > div.no-gutters.h-100.d-flex.justify-content-center.view > section > form > section > div.login-btn.login-btn-primary.d-flex.align-items-center.justify-content-center > button > span')

# 무료버전 팝업창 닫기
# click_infinity(driver, '#app > div:nth-child(5) > div > div:nth-child(2) > div > div.content.inform-free-license-modal > div.button-wrapper > button.t-button.secondary.small')
try:
    driver.find_element(
        By.CSS_SELECTOR, '#app > div:nth-child(5) > div > div:nth-child(2) > div > div.content.inform-free-license-modal > div.button-wrapper > button.t-button.secondary.small').click()
except:
    print('Not free version')

# 스크립트 비우기 및 쓰기
try:
    driver.find_element(By.CSS_SELECTOR, "#app > div.tool-wrapper.view > div > div.tool-container > div > div.tool > div.editor-container.editor-layout.editor-view > div > div > div > div > div.editor__wrapper.is-editor-page > div > div > p > span").clear()
except:
    print('Text box is already empty')
driver.find_element(By.CSS_SELECTOR, '#app > div.tool-wrapper.view > div > div.tool-container > div > div.tool > div.editor-container.editor-layout.editor-view > div > div > div > div > div.editor__wrapper.is-editor-page > div > div > p').send_keys(txt)

# 다운로드 클릭
driver.implicitly_wait(0.1)
while True:
    try:
        driver.find_element(By.CSS_SELECTOR, '#app > div.tool-wrapper.view > div > div.tool-container > div > div.player-bar-container.editor-container.player-bar > div.player-bar-container-box.editor-container-box.ns-fill-grey.ns-column > div.player-content > div.player-controller-container > button:nth-child(6)').click()
        break
    except:
        try:
            driver.find_element(By.CSS_SELECTOR, '#app > div.tool-wrapper.view > div > div.tool-container > div > div.player-bar-container.editor-container.player-bar > div.player-bar-container-box.editor-container-box.ns-fill-grey.ns-column > div > button').click()
            break
        except:
            continue
driver.implicitly_wait(10)

# 다운로드 형식(오디오, 파일명) 선택
click_infinity(
    driver, '#app > div:nth-child(5) > div > div:nth-child(2) > div > div.content-wrapper > ul > li:nth-child(1)')
click_infinity(driver, '#app > div.tool-wrapper.view > div > div:nth-child(3) > div > div > div:nth-child(2) > div > div.content-wrapper > div.select-download-option > div:nth-child(1) > form > input')
click_infinity(driver, '#app > div.tool-wrapper.view > div > div:nth-child(3) > div > div > div:nth-child(2) > div > div.content-wrapper > div.select-download-option > div:nth-child(1) > form > button')
driver.find_element(By.CSS_SELECTOR, '#app > div.tool-wrapper.view > div > div:nth-child(3) > div > div > div:nth-child(2) > div > div.content-wrapper > div.select-download-option > div:nth-child(1) > form > input').send_keys(model_name)
click_infinity(driver, '#app > div.tool-wrapper.view > div > div:nth-child(3) > div > div > div:nth-child(2) > div > div.button-wrapper > button.t-button.confirm.primary.small')

# 다운로드 완료될때까지 대기
while os.path.isfile('./tts_mp3/'+model_name+'.mp3') == False:
    pass

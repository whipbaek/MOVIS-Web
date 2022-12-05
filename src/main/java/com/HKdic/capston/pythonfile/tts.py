# import speech_recognition as sr
from threading import Thread
from gtts import gTTS
import os
import time
import playsound
import sys




def saveTTS(voiceText, rank, path):
    tts = gTTS(text=voiceText, lang='ko')
    filename='voice' + str(rank) + '.mp3'
    tts.save(path + filename)



if __name__ == "__main__":

    key1, key2, key3 = sys.argv[1], sys.argv[2], sys.argv[3]
    path = sys.argv[4]

    th1 = Thread(target=saveTTS, args=(key1, 0, path))
    th2 = Thread(target=saveTTS, args=(key2, 1, path))
    th3 = Thread(target=saveTTS, args=(key3, 2, path))

    th1.start()
    th2.start()
    th3.start()
    th1.join()
    th2.join()
    th3.join()


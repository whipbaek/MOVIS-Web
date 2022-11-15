package com.HKdic.capston.domain;

public enum DIR {
    /**
     * Config
     *      resourceHandler (static 밑)
     *      localResourceLocation (실제 크롤링에서 파일이 저장되는 위치)
     * Controller
     *      file Dir -> 업로드 하는 사진 파일이 저장될 위치
     *      pythonDir : 파이썬이 설치되어있는 위치
     *      pythonML : 머신 러닝 파이썬 파일 위치
     *      pythonCrawling : 크롤링 파이썬 파일 위치
     *      uploadedImg : 업로드 되어진 이미지 경로 (이미지 파일 경로)
     *
     */


    // Gram Side
    RESOURCE_HANDLER("/images/**"),
    LOCAL_RESOURCE_LOCATION("file:///C:/Users/jibae/Projects/capston/images/"),
    FILE_DIR("/C:/Users/jibae/Projects/capston/src/main/resources/static/images/"),
    PYTHON_DIR("C:\\Users\\jibae\\AppData\\Local\\Programs\\Python\\Python39\\python.exe"),
    PYTHON_ML("C:\\Users\\jibae\\Projects\\capston\\src\\main\\java\\com\\HKdic\\capston\\pythonfile\\ML.py"),
    PYTHON_CRAWLING("C:\\Users\\jibae\\Projects\\capston\\src\\main\\java\\com\\HKdic\\capston\\pythonfile\\CrawlingCar.py"),
    UPLOADED_IMG_DIR("C:\\Users\\jibae\\Projects\\capston\\src\\main\\resources\\static\\images\\testFile.jpg");
/*
    // Desktop Side
    RESOURCE_HANDLER("/images/**"),
    LOCAL_RESOURCE_LOCATION("file:///C:/Users/whipbaek/Projects/MOVIS_Web/images/"), -> 확인 필요
    FILE_DIR("/C:/Users/jibae/Projects/capston/src/main/resources/static/images/"),
    PYTHON_DIR("C:\\Users\\whipbaek\\AppData\\Local\\Programs\\Python\\Python39\\python.exe"),
    PYTHON_ML("C:\\Users\\whipbaek\\Projects\\MOVIS-Web\\src\\main\\java\\com\\HKdic\\capston\\pythonfile\\ML.py"),
    PYTHON_CRAWLING("C:\\Users\\whipbaek\\Projects\\MOVIS-Web\\src\\main\\java\\com\\HKdic\\capston\\pythonfile\\CrawlingCar.py"),
    UPLOADED_IMG_DIR("C:\\Users\\whipbaek\\Projects\\MOVIS-Web\\src\\main\\resources\\static\\images\\testFile.jpg");
*/


    private String val;

    DIR(String val) {
        this.val = val;
    }

    public String getVal() {
        return this.val;
    }

}

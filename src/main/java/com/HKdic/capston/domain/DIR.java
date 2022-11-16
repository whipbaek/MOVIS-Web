package com.HKdic.capston.domain;

public enum DIR {
    /**
     * Config
     *      RESOURCE_HANDLER (static 밑)
     *      LOCAL_RESOURCE_LOCATION (실제 크롤링에서 파일이 저장되는 위치)
     *
     * PythonImplement
     *      FILE_DIR -> 업로드 하는 사진 파일이 저장될 위치
     *      PYTHON_DIR : 파이썬이 설치되어있는 위치
     *      PYTHON_ML_DIR : 머신 러닝 파이썬 파일 위치
     *      PYTHON_CRAWLING_DIR : 크롤링 파이썬 파일 위치
     *      UPLOADED_IMG_DIR : 업로드 되어진 이미지 경로 (이미지 파일 경로)
     *
     *  Controller
     *      FILE_SAVE_DIR : 컨트롤러에서 파일저장시 사용하는 경로
     *      CAR_IMAGE_DIR : 컨트롤러에서 크롤링으로 저장된 자동차 사진 경로
     *
     *  Python File
     *      PYTHON_IMAGE_DIR : 파이선내에서 크롤링 후, 저장할 파일 경로
     */

//    // Gram Side
//    RESOURCE_HANDLER("/images/**"),
//    LOCAL_RESOURCE_LOCATION("file:///C:/Users/jibae/Projects/capston/images/"),
//    FILE_DIR("/C:/Users/jibae/Projects/capston/src/main/resources/static/images/"),
//    PYTHON_DIR("C:\\Users\\jibae\\AppData\\Local\\Programs\\Python\\Python39\\python.exe"),
//    PYTHON_ML_DIR("C:\\Users\\jibae\\Projects\\capston\\src\\main\\java\\com\\HKdic\\capston\\pythonfile\\ML.py"),
//    PYTHON_CRAWLING_DIR("C:\\Users\\jibae\\Projects\\capston\\src\\main\\java\\com\\HKdic\\capston\\pythonfile\\CrawlingCar_DG.py"),
//    UPLOADED_IMG_DIR("C:\\Users\\jibae\\Projects\\capston\\src\\main\\resources\\static\\images\\testFile.jpg"),
//    FILE_SAVE_DIR("/C:/Users/jibae/Projects/capston/src/main/resources/static/images/"),
//    CAR_IMAGE_DIR("C:/Users/jibae/Projects/capston/images/car.png"),
//    PYTHON_IMAGE_DIR("C:\\Users\\jibae\\Projects\\capston\\images\\");

    // Desktop Side
    RESOURCE_HANDLER("/images/**"),
    LOCAL_RESOURCE_LOCATION("file:///C:/Users/whipbaek/Projects/MOVIS_Web/images/"),
    FILE_DIR("/C:/Users/whipbaek/Projects/MOVIS-Web/src/main/resources/static/images/"),
    PYTHON_DIR("C:\\Users\\whipbaek\\AppData\\Local\\Programs\\Python\\Python39\\python.exe"),
    PYTHON_ML_DIR("C:\\Users\\whipbaek\\Projects\\MOVIS-Web\\src\\main\\java\\com\\HKdic\\capston\\pythonfile\\ML.py"),
    PYTHON_CRAWLING_DIR("C:\\Users\\whipbaek\\Projects\\MOVIS-Web\\src\\main\\java\\com\\HKdic\\capston\\pythonfile\\CrawlingCar_DG.py"),
    UPLOADED_IMG_DIR("C:\\Users\\whipbaek\\Projects\\MOVIS-Web\\src\\main\\resources\\static\\images\\testFile.jpg"),
    FILE_SAVE_DIR("/C:/Users/whipbaek/Projects/MOVIS-Web/src/main/resources/static/images/"),
    CAR_IMAGE_DIR("C:/Users/whipbaek/Projects/MOVIS-Web/images/car.png"),
    PYTHON_IMAGE_DIR("C:\\Users\\whipbaek\\Projects\\MOVIS-Web\\images\\");



    private final String val;

    DIR(String val) {
        this.val = val;
    }

    public String getVal() {
        return this.val;
    }

}

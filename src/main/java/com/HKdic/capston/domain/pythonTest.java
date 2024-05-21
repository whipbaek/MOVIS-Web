package com.HKdic.capston.domain;
import static com.HKdic.capston.domain.DIR.*;

public class pythonTest {
    public static void main(String[] args) throws Exception {
        PythonImplement pythonImplement = new PythonImplement();
        Process process = pythonImplement.makeProcess(PYTHON_DIR.getVal(), PYTHON_ML_DIR.getVal(), UPLOADED_IMG_DIR.getVal());
        pythonImplement.getCarName(process);
    }

}

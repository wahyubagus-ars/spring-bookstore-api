package com.example.demo.util;

import com.example.demo.TechnicalAssessmentApplication;
import com.example.demo.domain.dto.ApiResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static com.example.demo.constant.AppConstant.ResponseKey.SUCCESS;
import static com.example.demo.constant.AppConstant.ResponseMessage.SUCCESS_MSG;

@SpringBootTest(classes = TechnicalAssessmentApplication.class)
public class ResponseUtilsTest {

    @Test
    public void buildResponse_Test() {
        ResponseEntity<Object> responseEntity = ResponseUtils.buildResponse(SUCCESS, SUCCESS_MSG, new Object(), HttpStatus.OK);
        ApiResponse apiResponse = (ApiResponse) responseEntity.getBody();
        Assertions.assertEquals(SUCCESS, apiResponse.getResponseKey());
    }

}

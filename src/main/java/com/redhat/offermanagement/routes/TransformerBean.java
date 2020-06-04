package com.redhat.offermanagement.routes;

import com.google.gson.Gson;
import com.redhat.bian.servicedomain.models.*;
import org.apache.camel.Exchange;

import java.util.Date;
import java.util.Map;

public class TransformerBean {

    public String evaluateEligibility(Exchange exchange){
        System.out.println(exchange.getIn().getBody());
        BianResponse bianResponse = new BianResponse();
        BianRequest bianRequest = new Gson().fromJson(exchange.getIn().getBody().toString(),BianRequest.class);
        Map dataMap = (Map) bianRequest.getData();
        CRCustomerProductDeploymentInstanceRecord crRecord = new Gson().fromJson(dataMap.get("customerEligibilityAssessmentInstanceRecord").toString(),CRCustomerProductDeploymentInstanceRecord.class);
        //Return Customer Eligibility Assessment
        CRCustomerEligibilityAssessmentEvaluateOutputModelCustomerEligibilityAssessmentInstanceRecord output = new CRCustomerEligibilityAssessmentEvaluateOutputModelCustomerEligibilityAssessmentInstanceRecord();
        output.setCustomerProductServiceTypeEligibility("Eligible: "+crRecord.getCustomerReference()+" is in Good Standing");
        CRCustomerEligibilityAssessmentEvaluateOutputModel crCustomerEligibilityAssessmentEvaluateOutputModel = new CRCustomerEligibilityAssessmentEvaluateOutputModel();
        crCustomerEligibilityAssessmentEvaluateOutputModel.setCustomerEligibilityAssessmentEvaluateActionReference("CEAIR780662");
        crCustomerEligibilityAssessmentEvaluateOutputModel.setCustomerEligibilityAssessmentEvaluateActionRecord(output);
        crCustomerEligibilityAssessmentEvaluateOutputModel.setCustomerEligibilityAssessmentInstanceReference("CEAEAR781025");
        crCustomerEligibilityAssessmentEvaluateOutputModel.setDate(new Date().toString());
        bianResponse.setData(crCustomerEligibilityAssessmentEvaluateOutputModel);
        return new Gson().toJson(bianResponse);
    }
}

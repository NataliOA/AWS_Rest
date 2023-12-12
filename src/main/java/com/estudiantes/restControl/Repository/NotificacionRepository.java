package com.estudiantes.restControl.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;

@Service
public class NotificacionRepository{
    
    @Autowired
    private AmazonSNS sns;

    public void enviarCorreo(String cuerpo, String asunto){
        PublishResult res = sns.publish(new PublishRequest()
                .withTopicArn("arn:aws:sns:us-east-1:944048634042:Proyecto-AWS")
                .withMessage(cuerpo)
        );
        System.out.println(res);
    }

}
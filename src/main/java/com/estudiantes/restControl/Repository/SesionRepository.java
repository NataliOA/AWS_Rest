package com.estudiantes.restControl.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.estudiantes.restControl.dto.Model.Sesion;

@Service
public class SesionRepository{
    
    @Autowired
    private DynamoDBMapper ddbMap;

    private void save(Sesion sesion) {
        ddbMap.save(sesion);
    }

    public Sesion getSesionByString(String seString){
        Sesion sesion = null;
        Map<String, AttributeValue> eav= new HashMap<String ,AttributeValue>();
        eav.put(":sessionString", new AttributeValue().withS(seString));

        DynamoDBScanExpression scanExpression=new DynamoDBScanExpression()
                .withFilterExpression("sessionString = :sessionString")
                .withExpressionAttributeValues(eav);
                List<Sesion> useResult = ddbMap.scan(Sesion.class, scanExpression);
        if(!useResult.isEmpty() && useResult.size()>0) {
            sesion=useResult.get(0);
        }

        return sesion;
    }

    public Sesion iniciarSesion(Sesion sesion){
        String uuid = UUID.randomUUID().toString();
        sesion.setId(uuid);
        sesion.setActive(true);
        sesion.setFecha(System.currentTimeMillis());
        save(sesion);
        return sesion;
    }

    public Sesion cerrarSesion(String seString){
        Sesion sesionAux = getSesionByString(seString);
        if (sesionAux != null && sesionAux.getActive()) {
            sesionAux.setActive(false);
            save(sesionAux);
            return sesionAux;
        }
        return null;
    }


}
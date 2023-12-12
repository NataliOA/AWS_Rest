package com.estudiantes.restControl.dto.Model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamoDBTable(tableName = "Student-Access")
public class Sesion {
    @DynamoDBHashKey
    private String id;

    @DynamoDBAttribute
    private Long fecha;

    @DynamoDBAttribute
    private int alumnoId;

    @DynamoDBAttribute
    private Boolean active;

    @DynamoDBAttribute
    private String sessionString; 

}


package com.estudiantes.restControl.Repository;

import java.io.File;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;

@Service
public class BucketRepository{
    
    @Autowired
    private AmazonS3 bucket;
    
    public void uploadFileToS3(String buck,String nombre,File archivo){
        String key_name = Paths.get(archivo.getAbsolutePath()).getFileName().toString();
        try {
            bucket.putObject(new PutObjectRequest(buck, key_name, archivo));
        } catch (AmazonServiceException e) {
            System.out.println("Error al subir el archivo al bucket:");
            System.out.println(e.getErrorCode());
            System.out.println("Mensaje de error: " + e.getErrorMessage());
            throw e;
        }
    }

}
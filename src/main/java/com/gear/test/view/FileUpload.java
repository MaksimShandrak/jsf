package com.gear.test.view;

import com.gear.test.domain.Product;
import com.gear.test.domain.Products;
import com.gear.test.service.ProductService;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;

import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;

@Named
@RequestScoped
public class FileUpload {

    @Autowired
    private ProductService productService;

    @Transactional
    public void upload() throws JAXBException, IOException {
        String fileName = "products.xml";
        ClassLoader classLoader = new FileUpload().getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());

        JAXBContext jaxbContext = JAXBContext.newInstance(Products.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        Products products = (Products) unmarshaller.unmarshal(file);

        productService.deleteAll();
        productService.save(products.getProducts());
    }

}

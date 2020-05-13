package com.gear.test.controller;

import com.gear.test.domain.Product;
import com.gear.test.domain.Products;
import com.gear.test.repository.ProductRepository;
import com.gear.test.service.ProductService;
import com.gear.test.service.SoapService;
import com.gear.test.util.TimeUtil;
import com.gear.test.view.FileUpload;
import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.annotation.RequestAction;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.ocpsoft.rewrite.faces.annotation.Deferred;
import org.ocpsoft.rewrite.faces.annotation.IgnorePostback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.XMLGregorianCalendar;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Scope (value = "session")
@Component (value = "productList")
@ELBeanName(value = "productList")
@Join(path = "/", to = "/product-list.jsf")
public class ProductListController {

    @Autowired
    private ProductService productService;

    @Autowired
    private SoapService soapService;

    private List<Product> products;

    private String time;

    @Deferred
    @RequestAction
    @IgnorePostback
    public void loadData() throws DatatypeConfigurationException {
        time = TimeUtil.getTimeFromXMLGregorianCalendar(soapService.getTime());
    }

    @Transactional
    public void updateProducts() throws JAXBException, IOException {
        String fileName = "products.xml";
        ClassLoader classLoader = new FileUpload().getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());

        JAXBContext jaxbContext = JAXBContext.newInstance(Products.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        Products products = (Products) unmarshaller.unmarshal(file);

        productService.deleteAll();
        productService.save(products.getProducts());

        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
    }

    public List<Product> getProducts() {
        return productService.findAll();
    }

    public String getTime() {
        return time;
    }
}

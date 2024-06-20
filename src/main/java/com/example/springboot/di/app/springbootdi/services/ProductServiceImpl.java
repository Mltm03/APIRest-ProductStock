package com.example.springboot.di.app.springbootdi.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.example.springboot.di.app.springbootdi.models.Product;
import com.example.springboot.di.app.springbootdi.repositories.ProductRepository;


//Esta clase tendra los mismos metodos que la clase ProductRepository sin embargo aca es donde se podran  realizar las operaciones de consulta y manipulacion de datos en base de datos

@Service
public class ProductServiceImpl implements ProductService{
    
    @Autowired  //principio holliwood , inyeccion de dependencias
    private ProductRepository repository;

    //DE ESTA FORMA Y SIN EL CLONEABLE DE PRODUCT.JAVA LOS DATOS NO TIENEN INMUTABILIDAD
    // public List<Product> findAll(){
    //     return repository.findAll().stream().map(p -> {
    //         Double priceImp=p.getPrice()*1.25d;  //todo esta logica de negocio y        operaciones 
    //         //siempre deben ir en el Service , no en el  Repository 
    //         Product newProd= new Product(p.getId(),p.getName(),priceImp.longValue());   //creamos un nuevo producto con los datos del original pero con precio impuesto
    //         return newProd;  //aqui ocurre lo interesnate y es que creamos el nuevo producto para evitar la mutabilidad
    //         // ya que originalmente se sobreescribiasn los datos  del producto, ahora solo lee
    //     }).collect(Collectors.toList());
    // }

        @Override
    public List<Product> findAll(){
        return repository.findAll().stream().map(p -> {
            Double priceTaxs=p.getPrice()*1.25d;
            Product newProd=(Product)p.clone();
            newProd.setPrice(priceTaxs.longValue());
            return newProd;
        }).collect(Collectors.toList());
    }

    @Override
    public Product findById(Long id){
        return repository.findById(id); 
    }
   
}

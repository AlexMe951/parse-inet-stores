package com.alexme951.parseinetstores.service.db.impl;

import com.alexme951.parseinetstores.repository.dto.parsing.CategoryLinkParsingDto;
import com.alexme951.parseinetstores.repository.dto.parsing.CategoryParsingDto;
import com.alexme951.parseinetstores.service.db.CategoryDatabaseSearchService;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Service;


@Service
public class CategoryDatabaseSearchServiceImpl implements CategoryDatabaseSearchService {
  SessionFactory factory;
  Session session;


  @Override
  public List<CategoryLinkParsingDto> readAllCategoryLinks() {
    createFactoryParsingDto(CategoryLinkParsingDto.class);
    List<CategoryLinkParsingDto> allCategoryLinksList = session
        .createQuery("SELECT '*' from CategoryLinkParsingDto").getResultList();
    session.getTransaction().commit();
    factory.close();
    return allCategoryLinksList;
  }


  @Override
  public List<CategoryLinkParsingDto> readSubCategoryLinksByCategory(String category) {
    createFactoryParsingDto(CategoryLinkParsingDto.class);
    List<CategoryLinkParsingDto> allCategoryLinksList = session
        .createQuery("select '*' from CategoryLinkParsingDto where linkUrl like :category")
        .setParameter("category", "%" + category + "%").getResultList();
    session.getTransaction().commit();
    factory.close();
    return allCategoryLinksList;
  }

  @Override
  public List<CategoryParsingDto> readAllCategories() {
    createFactoryParsingDto(CategoryParsingDto.class);
    List<CategoryParsingDto> allCategoriesList = session
        .createQuery("select distinct '*' from CategoryParsingDto").getResultList();
    session.getTransaction().commit();
    factory.close();
    return allCategoriesList;
  }

  @Override
  public List<CategoryParsingDto> readSubCategoriesByCategory(String category) {
    createFactoryParsingDto(CategoryParsingDto.class);
    List<CategoryParsingDto> allSubCategoriesByCategoryList = session
        .createQuery("select '*' from CategoryParsingDto where name like :category")
        .setParameter("category", "%" + category + "%").getResultList();
    session.getTransaction().commit();
    factory.close();
    return allSubCategoriesByCategoryList;

  }

  @Override
  public CategoryParsingDto readCategoryByName(String categoryName) {
    createFactoryParsingDto(CategoryParsingDto.class);
    CategoryParsingDto categoryParsingDtoByName = (CategoryParsingDto) session
        .createQuery("select distinct '*' from CategoryParsingDto where name = :categoryName")
        .setParameter("categoryName", "%" + categoryName + "%" ).getSingleResult();
    session.getTransaction().commit();
    factory.close();
    return categoryParsingDtoByName;
  }

  @Override
  public CategoryParsingDto readCategoryById(Long categoryId) {
    createFactoryParsingDto(CategoryParsingDto.class);
    CategoryParsingDto categoryParsingDtoByName = (CategoryParsingDto) session
        .createQuery("select distinct '*' from CategoryParsingDto where id = :categoryId")
        .setParameter("categoryId", categoryId).getSingleResult();
    session.getTransaction().commit();
    factory.close();
    return categoryParsingDtoByName;
  }


  public void createFactoryParsingDto(Class clazz){
    factory = new Configuration()
        .configure("hibernate.properties")
        .addAnnotatedClass(clazz)
        .buildSessionFactory();
    session = factory.getCurrentSession();
    session.beginTransaction();
  }
}

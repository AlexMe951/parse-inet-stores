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
    createFactoryCategoryLinkParsingDto();
    List<CategoryLinkParsingDto> allCategoryLinksList = session
        .createQuery("SELECT '*' from CategoryLinkParsingDto").getResultList();
    session.getTransaction().commit();
    factory.close();
    return allCategoryLinksList;
  }


  @Override
  public List<CategoryLinkParsingDto> readSubCategoryLinksByCategory(String category) {
    createFactoryCategoryLinkParsingDto();
    List<CategoryLinkParsingDto> allCategoryLinksList = session
        .createQuery("select '*' from CategoryLinkParsingDto where linkUrl like :category")
        .setParameter("category", "%" + category + "%").getResultList();
    session.getTransaction().commit();
    factory.close();
    return allCategoryLinksList;
  }

  @Override
  public List<CategoryParsingDto> readAllCategories() {
    createFactoryCategoryParsingDto();
    List<CategoryParsingDto> allCategoriesList = session
        .createQuery("select distinct '*' from CategoryParsingDto").getResultList();
    session.getTransaction().commit();
    factory.close();
    return allCategoriesList;
  }

  @Override
  public List<CategoryParsingDto> readSubCategoriesByCategory(String category) {
    createFactoryCategoryParsingDto();
    List<CategoryParsingDto> allSubCategoriesByCategoryList = session
        .createQuery("select '*' from CategoryParsingDto where name like :category")
        .setParameter("category", "%" + category + "%").getResultList();
    session.getTransaction().commit();
    factory.close();
    return allSubCategoriesByCategoryList;

  }

  @Override
  public CategoryParsingDto readCategoryByName(String categoryName) {
    createFactoryCategoryParsingDto();
    CategoryParsingDto categoryParsingDtoByName = (CategoryParsingDto) session
        .createQuery("select distinct '*' from CategoryParsingDto where name = :categoryName")
        .setParameter("categoryName", "%" + categoryName + "%" ).getSingleResult();
    session.getTransaction().commit();
    factory.close();
    return categoryParsingDtoByName;
  }

  @Override
  public CategoryParsingDto readCategoryById(Long categoryId) {
    createFactoryCategoryParsingDto();
    CategoryParsingDto categoryParsingDtoByName = (CategoryParsingDto) session
        .createQuery("select distinct '*' from CategoryParsingDto where id = :categoryId")
        .setParameter("categoryId", categoryId).getSingleResult();
    session.getTransaction().commit();
    factory.close();
    return categoryParsingDtoByName;
  }

  public void createFactoryCategoryLinkParsingDto(){
    factory = new Configuration()
        .configure("hibernate.properties")
        .addAnnotatedClass(CategoryLinkParsingDto.class)
        .buildSessionFactory();
    session = factory.getCurrentSession();
    session.beginTransaction();
  }

  public void createFactoryCategoryParsingDto(){
    factory = new Configuration()
        .configure("hibernate.properties")
        .addAnnotatedClass(CategoryParsingDto.class)
        .buildSessionFactory();
    session = factory.getCurrentSession();
    session.beginTransaction();
  }
}

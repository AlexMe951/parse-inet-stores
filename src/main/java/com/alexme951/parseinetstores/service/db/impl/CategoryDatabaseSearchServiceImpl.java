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
  private final SessionFactory factory = new Configuration()
      .configure("hibernate.properties")
      .addAnnotatedClass(CategoryLinkParsingDto.class)
      .addAnnotatedClass(CategoryParsingDto.class)
      .buildSessionFactory();;



  @Override
  public List<CategoryLinkParsingDto> readAllCategoryLinks() {
    Session session = factory.getCurrentSession();
    session.beginTransaction();
    List<CategoryLinkParsingDto> allCategoryLinksList = session
        .createQuery("SELECT '*' from CategoryLinkParsingDto").getResultList();
    session.getTransaction().commit();
    return allCategoryLinksList;
  }


  @Override
  public List<CategoryLinkParsingDto> readSubCategoryLinksByCategory(String category) {
    if(category == null || category.isBlank()){
      throw new NullPointerException();
    }
    Session session = factory.getCurrentSession();
    session.beginTransaction();
    List<CategoryLinkParsingDto> allCategoryLinksList = session
        .createQuery("select '*' from CategoryLinkParsingDto where linkUrl like :category")
        .setParameter("category", "%" + category + "%").getResultList();
    session.getTransaction().commit();
    return allCategoryLinksList;
  }

  @Override
  public List<CategoryParsingDto> readAllCategories() {
    Session session = factory.getCurrentSession();
    session.beginTransaction();
    List<CategoryParsingDto> allCategoriesList = session
        .createQuery("select distinct '*' from CategoryParsingDto").getResultList();
    session.getTransaction().commit();
    return allCategoriesList;
  }

  @Override
  public List<CategoryParsingDto> readSubCategoriesByCategory(String category) {
    if(category == null || category.isBlank()){
      throw new NullPointerException();
    }
    Session session = factory.getCurrentSession();
    session.beginTransaction();
    List<CategoryParsingDto> allSubCategoriesByCategoryList = session
        .createQuery("select '*' from CategoryParsingDto where name like :category")
        .setParameter("category", "%" + category + "%").getResultList();
    session.getTransaction().commit();
    return allSubCategoriesByCategoryList;

  }

  @Override
  public CategoryParsingDto readCategoryByName(String categoryName) {
    if(categoryName == null || categoryName.isBlank()){
      throw new NullPointerException();
    }
    Session session = factory.getCurrentSession();
    session.beginTransaction();
    CategoryParsingDto categoryParsingDtoByName = (CategoryParsingDto) session
        .createQuery("select distinct '*' from CategoryParsingDto where name = :categoryName")
        .setParameter("categoryName", "%" + categoryName + "%" ).getSingleResult();
    session.getTransaction().commit();
    return categoryParsingDtoByName;
  }

  @Override
  public CategoryParsingDto readCategoryById(Long categoryId) {
    if(categoryId == null){
      throw new NullPointerException();
    }
    Session session = factory.getCurrentSession();
    session.beginTransaction();
    CategoryParsingDto categoryParsingDtoByName = (CategoryParsingDto) session
        .createQuery("select distinct '*' from CategoryParsingDto where id = :categoryId")
        .setParameter("categoryId", categoryId).getSingleResult();
    session.getTransaction().commit();
    return categoryParsingDtoByName;
  }


}

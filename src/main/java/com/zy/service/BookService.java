package com.zy.service;

import com.zy.pojo.Books;

import java.util.List;

/**
 * ssmbuild
 *
 * @author zy
 * @date 2021/6/30
 */
public interface BookService {
    //增加一本书
    int addBook(Books books);

    //删除一本书
    int deleteBookById(int id);

    //更新一本书
    int updateBook(Books books);

    //查询一本书
    Books queryBookById(int id);

    //查询所有的书
    List<Books> queryAllBook();

    //根据用户输入的书籍名称查询书籍
    List<Books> queryBookByName(String bookName);
}
